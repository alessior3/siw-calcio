package it.uniroma3.siw;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StopWatch;

import java.util.Arrays;
import java.util.List;

import it.uniroma3.siw.model.Squadra;
import it.uniroma3.siw.repository.SquadraRepository;

@SpringBootApplication
public class SiwCalcioApplication implements CommandLineRunner {
    private SquadraRepository squadraRepository;

    public SiwCalcioApplication(SquadraRepository squadraRepository) {
        this.squadraRepository = squadraRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(SiwCalcioApplication.class, args);
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

//        List<Long> idsSquadre = Arrays.asList(101L, 102L, 103L);
//
//        System.out.println("\n==========================================================");
//        System.out.println("--- INIZIO ANALISI SPERIMENTALE FETCH (Requisito 8.2) ---");
//        System.out.println("==========================================================\n");
//
//        StopWatch watch = new StopWatch();
//
//        // ------------------------------------------------------------------
//        // CASO D'USO 1: L'Elenco delle Squadre (Senza accesso ai giocatori) (da
//        // verificare differenze cambiando tra eager e lazy)
//        // ------------------------------------------------------------------
//        System.out.println(">>> ESECUZIONE CASO D'USO 1: Caricamento lista (Senza getGiocatori)");
//        watch.start("Caso d'Uso 1 (Solo Squadre)");
//        Iterable<Squadra> squadreCaso1 = squadraRepository.findAllById(idsSquadre);
//        for (Squadra s : squadreCaso1) {
//            // Simuliamo la vista elenco: leggiamo solo i dati base, ignoriamo i giocatori
//            String nome = s.getNome();
//        }
//        watch.stop();
//
//        // ------------------------------------------------------------------
//        // CASO D'USO 2 (Scenario C): Dettaglio Squadra (N+1 Query)
//        // ------------------------------------------------------------------
//        System.out.println(">>> ESECUZIONE CASO D'USO 2 (Scen. C): Dettaglio con N+1 Query");
//        watch.start("Caso d'Uso 2C (Problema N+1)");
//        Iterable<Squadra> squadreCaso2C = squadraRepository.findAllById(idsSquadre);
//        for (Squadra s : squadreCaso2C) {
//            // Simuliamo il dettaglio: dobbiamo stampare i giocatori
//            s.getGiocatori().size();
//        }
//        watch.stop();

        // ------------------------------------------------------------------
        // CASO D'USO 2 (Scenario D): Dettaglio Squadra (JOIN FETCH)
        // ------------------------------------------------------------------
//        System.out.println(">>> ESECUZIONE CASO D'USO 2 (Scen. D): Dettaglio con JOIN FETCH");
//        watch.start("Caso d'Uso 2D (JOIN FETCH Ottimizzata)");
//        Iterable<Squadra> squadreCaso2D = squadraRepository.findAllByIdWithGiocatori(idsSquadre);
//        for (Squadra s : squadreCaso2D) {
//            s.getGiocatori().size();
//        }
//        watch.stop();
//
//        System.out.println("\n==========================================================");
//        System.out.println("--- RISULTATI CRONOMETRO STOPWATCH ---");
//        System.out.println(watch.prettyPrint());
//        System.out.println("==========================================================");
//        
//
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//        System.out.println(encoder.encode("admin")); 
//        System.out.println(encoder.encode("user"));
    }
    
}
