
# Analisi Sperimentale sulle Strategie di Fetch (Requisito 8.2)

## 1. Obiettivo dell'Analisi
Questa relazione confronta le strategie di caricamento dati (LAZY, EAGER, JOIN FETCH) della relazione `@OneToMany` tra `Squadra` e `Giocatore`, come richiesto esplicitamente dalla **Sezione 8.2 delle specifiche di progetto**. 
Per valutare appieno il comportamento dell'ORM aggirando la cache, le misurazioni tramite `StopWatch` sono state effettuate su un campione di 3 squadre eseguendo test isolati, modellati su **due scenari d'uso reali e distinti**.

---

## CASO D'USO 1: L'Elenco delle Squadre (Senza accesso ai giocatori)
*Simulazione del caricamento della pagina `/squadre`, dove viene mostrato solo l'elenco generale. Nel codice del test non viene mai invocato il metodo `getGiocatori()`.*

### Scenario A: EAGER
Nonostante i giocatori non vengano richiesti dal codice, l'impostazione EAGER forza Hibernate a pre-caricare tutto. 
* **Risultato:** Si verifica il problema N+1. Vengono eseguite 1 query per le squadre e 3 query aggiuntive per i giocatori. Grave spreco di memoria e banda.
* **Tempo registrato:** `0.0756 secondi`
* **Log SQL di Hibernate:** 
```sql
Hibernate: select s1_0.id,s1_0.anno_di_fondazione,s1_0.citta,s1_0.nome from squadra s1_0 where s1_0.id in (?,?,?) 
Hibernate: select g1_0.squadra_id,g1_0.id,g1_0.altezza,g1_0.cognome,g1_0.data_di_nascita,g1_0.nome,g1_0.ruolo from giocatore g1_0 where g1_0.squadra_id=? 
Hibernate: select g1_0.squadra_id,g1_0.id,g1_0.altezza,g1_0.cognome,g1_0.data_di_nascita,g1_0.nome,g1_0.ruolo from giocatore g1_0 where g1_0.squadra_id=? 
Hibernate: select g1_0.squadra_id,g1_0.id,g1_0.altezza,g1_0.cognome,g1_0.data_di_nascita,g1_0.nome,g1_0.ruolo from giocatore g1_0 where g1_0.squadra_id=?
````

### Scenario B: LAZY
Essendo i giocatori mappati in LAZY, Hibernate si limita a caricare solo i dati della tabella principale `Squadra`.

- **Risultato:** Viene eseguita **1 singola query**. Il database non viene sovraccaricato.
- **Tempo registrato:** `0.0734 secondi`
- **Log SQL di Hibernate:**

```sql
Hibernate: select s1_0.id,s1_0.anno_di_fondazione,s1_0.citta,s1_0.nome from squadra s1_0 where s1_0.id in (?,?,?)
```

- **Conclusione parziale:** Per le liste e le visualizzazioni generiche, il caricamento LAZY è obbligatorio per garantire le performance.

---

## CASO D'USO 2: Il Dettaglio della Squadra (Con accesso ai giocatori)

_Simulazione del caricamento della pagina `/squadre/{id}`, dove è richiesto visualizzare sia i dati della squadra che la rosa completa dei giocatori._

### Scenario C: LAZY ed EAGER a confronto
Poiché in questo scenario il codice richiede esplicitamente la lista dei giocatori, la differenza prestazionale tra EAGER e LAZY si annulla. Entrambe le strategie innescano il problema N+1, generando **4 query a cascata** (1 per la squadra, 3 per i giocatori).

- **Tempi registrati:** `0.0761 secondi` (LAZY) e `0.0834 secondi` (EAGER).
- **Log SQL di Hibernate (identico per entrambi gli approcci in questo scenario):**

```sql
Hibernate: select s1_0.id,s1_0.anno_di_fondazione,s1_0.citta,s1_0.nome from squadra s1_0 where s1_0.id in (?,?,?) 
Hibernate: select g1_0.squadra_id,g1_0.id,g1_0.altezza,g1_0.cognome,g1_0.data_di_nascita,g1_0.nome,g1_0.ruolo from giocatore g1_0 where g1_0.squadra_id=? 
Hibernate: select g1_0.squadra_id,g1_0.id,g1_0.altezza,g1_0.cognome,g1_0.data_di_nascita,g1_0.nome,g1_0.ruolo from giocatore g1_0 where g1_0.squadra_id=? 
Hibernate: select g1_0.squadra_id,g1_0.id,g1_0.altezza,g1_0.cognome,g1_0.data_di_nascita,g1_0.nome,g1_0.ruolo from giocatore g1_0 where g1_0.squadra_id=?
```

### Scenario D: Ottimizzazione con JOIN FETCH
Per risolvere il problema N+1 nel caso d'uso specifico del dettaglio, è stata creata la Custom Query JPQL: `@Query("SELECT s FROM Squadra s JOIN FETCH s.giocatori")`.

- **Risultato:** Il database unisce le tabelle a monte eseguendo **1 singola query ottimizzata**. È la soluzione in assoluto più performante per estrarre l'albero degli oggetti.
- **Tempo registrato:** `0.0654 secondi`
- **Log SQL di Hibernate:**

```sql
Hibernate: select distinct s1_0.id,s1_0.anno_di_fondazione,s1_0.citta,g1_0.squadra_id,g1_0.id,g1_0.altezza,g1_0.cognome,g1_0.data_di_nascita,g1_0.nome,g1_0.ruolo,s1_0.nome from squadra s1_0 left join giocatore g1_0 on s1_0.id=g1_0.squadra_id where s1_0.id in (?,?,?)
```

---

## Conclusioni Architetturali

L'analisi empirica dimostra che non esiste un'unica strategia perfetta, ma la soluzione ingegneristica risiede nella combinazione di due tecniche:

1. Lasciare **sempre la strategia di default LAZY** sulle collezioni per proteggere le performance dei caricamenti generici (Caso d'Uso 1).
2. Utilizzare query mirate con **JOIN FETCH** esclusivamente nei metodi dei Repository e nelle pagine che richiedono l'estrazione completa delle dipendenze per l'interfaccia utente (Caso d'Uso 2).

