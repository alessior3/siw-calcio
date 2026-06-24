-- ==========================================
-- POPOLAMENTO TABELLA UTENTI E CREDENZIALI
-- ==========================================
-- NOTA BENE: Ho rimosso le credenziali orfane e allineato correttamente utente e credentials
INSERT INTO utente (id, nome, cognome, email) VALUES (1, 'Super', 'Admin', 'admin@siwcalcio.it');
INSERT INTO utente (id, nome, cognome, email) VALUES (2, 'Alessio', 'Studente', 'alessio@siwcalcio.it');

-- Passwords in chiaro per facilitare (admin: admin, user: user) - cifrate con BCrypt
INSERT INTO credentials (id, username, password, ruolo_utente, utente_id) VALUES (1, 'admin', '$2a$10$uj.KWeEYV.m21IGDzpLAt.Fq4JsHbYBPceH58gMvn/nA/r5usnFBS', 'ADMIN', 1);
INSERT INTO credentials (id, username, password, ruolo_utente, utente_id) VALUES (2, 'user', '$2a$10$PfO6OsfQkViCesJFgqbFHe5tb7.A7jjRwRDuWYqgjS7HRrYZNwsyC', 'USER', 2);

-- ==========================================
-- POPOLAMENTO TABELLA TORNEI (AMATORIALI)
-- ==========================================
INSERT INTO torneo (id, nome, anno, descrizione) VALUES (1, 'Coppa dei Quartieri Romani', 2026, 'Il torneo amatoriale di calcetto più sudato di Roma.');
INSERT INTO torneo (id, nome, anno, descrizione) VALUES (2, 'Torneo Nazionale Dopolavoro', 2026, 'Competizione post-lavoro per ex leggende appesantite di tutta Italia.');
INSERT INTO torneo (id, nome, anno, descrizione) VALUES (3, 'Supercoppa del Futuro', 2027, 'Torneo di preparazione per la prossima stagione amatoriale. Le partite non si possono giocare prima!');

-- ==========================================
-- POPOLAMENTO TABELLA SQUADRE (Amatoriali e goliardiche)
-- ==========================================
-- L'EASTER EGG (La squadra ispirata alla Roma)
INSERT INTO squadra (id, nome, citta, anno_di_fondazione) VALUES (1, 'Lupi Invincibili Amatori', 'Roma', 1927);

-- Squadre amatoriali fittizie ma basate su città reali
INSERT INTO squadra (id, nome, citta, anno_di_fondazione) VALUES (2, 'Testaccio United', 'Roma', 2010);
INSERT INTO squadra (id, nome, citta, anno_di_fondazione) VALUES (3, 'Bauscia Milano', 'Milano', 2005);
INSERT INTO squadra (id, nome, citta, anno_di_fondazione) VALUES (4, 'Scusate il Ritardo Napoli', 'Napoli', 1988);
INSERT INTO squadra (id, nome, citta, anno_di_fondazione) VALUES (5, 'Torino Granata Amatori', 'Torino', 1999);
INSERT INTO squadra (id, nome, citta, anno_di_fondazione) VALUES (6, 'Fiorentina Bistecca FC', 'Firenze', 2015);
INSERT INTO squadra (id, nome, citta, anno_di_fondazione) VALUES (7, 'I Butei di Verona', 'Verona', 2020);
INSERT INTO squadra (id, nome, citta, anno_di_fondazione) VALUES (8, 'Amatori Tortellino Bologna', 'Bologna', 1975);
INSERT INTO squadra (id, nome, citta, anno_di_fondazione) VALUES (9, 'Borgata Palermo', 'Palermo', 2018);
INSERT INTO squadra (id, nome, citta, anno_di_fondazione) VALUES (10, 'Cagliari Salsiccia e Mirto', 'Cagliari', 1990);

-- ==========================================
-- ASSOCIAZIONE TORNEI - SQUADRE (ManyToMany)
-- ==========================================
-- Coppa dei Quartieri Romani
INSERT INTO torneo_squadre (torneo_id, squadre_id) VALUES (1, 1);
INSERT INTO torneo_squadre (torneo_id, squadre_id) VALUES (1, 2);

-- Torneo Nazionale Dopolavoro
INSERT INTO torneo_squadre (torneo_id, squadre_id) VALUES (2, 1);
INSERT INTO torneo_squadre (torneo_id, squadre_id) VALUES (2, 3);
INSERT INTO torneo_squadre (torneo_id, squadre_id) VALUES (2, 4);
INSERT INTO torneo_squadre (torneo_id, squadre_id) VALUES (2, 5);
INSERT INTO torneo_squadre (torneo_id, squadre_id) VALUES (2, 6);
INSERT INTO torneo_squadre (torneo_id, squadre_id) VALUES (2, 7);
INSERT INTO torneo_squadre (torneo_id, squadre_id) VALUES (2, 8);
INSERT INTO torneo_squadre (torneo_id, squadre_id) VALUES (2, 9);
INSERT INTO torneo_squadre (torneo_id, squadre_id) VALUES (2, 10);

-- Supercoppa del Futuro (2027)
INSERT INTO torneo_squadre (torneo_id, squadre_id) VALUES (3, 1);
INSERT INTO torneo_squadre (torneo_id, squadre_id) VALUES (3, 3);
INSERT INTO torneo_squadre (torneo_id, squadre_id) VALUES (3, 4);
INSERT INTO torneo_squadre (torneo_id, squadre_id) VALUES (3, 6);

-- ==========================================
-- POPOLAMENTO TABELLA ARBITRI
-- ==========================================
INSERT INTO arbitro (id, nome, cognome, codice_arbitrale) VALUES (1, 'Pierluigi', 'Collinetta', 1001);
INSERT INTO arbitro (id, nome, cognome, codice_arbitrale) VALUES (2, 'Nicola', 'Rizzolino', 1002);
INSERT INTO arbitro (id, nome, cognome, codice_arbitrale) VALUES (3, 'Daniele', 'Orsacchiotto', 1003);

-- ==========================================
-- POPOLAMENTO TABELLA PARTITE (EASTER EGG ROMA)
-- ==========================================
-- I Lupi Invincibili (squadra 1) vincono SEMPRE, demolendo le altre squadre
INSERT INTO partita (id, data_ora, luogo, goal_casa, goal_trasferta, stato_partita, torneo_id, arbitro_id, squadra_casa_id, squadra_trasferta_id) VALUES (1, '2026-06-01 20:45:00', 'Campetto Testaccio', 5, 0, 'GIOCATA', 1, 1, 1, 2);
INSERT INTO partita (id, data_ora, luogo, goal_casa, goal_trasferta, stato_partita, torneo_id, arbitro_id, squadra_casa_id, squadra_trasferta_id) VALUES (2, '2026-06-08 18:00:00', 'Campo Parrocchiale Bologna', 1, 4, 'GIOCATA', 2, 2, 8, 1);
INSERT INTO partita (id, data_ora, luogo, goal_casa, goal_trasferta, stato_partita, torneo_id, arbitro_id, squadra_casa_id, squadra_trasferta_id) VALUES (3, '2026-06-15 20:45:00', 'Campetto Colosseo (In affitto)', 6, 1, 'GIOCATA', 2, 3, 1, 3);

-- Altre partite equilibrate
INSERT INTO partita (id, data_ora, luogo, goal_casa, goal_trasferta, stato_partita, torneo_id, arbitro_id, squadra_casa_id, squadra_trasferta_id) VALUES (4, '2026-06-05 20:45:00', 'San Siro Amatoriale', 2, 2, 'GIOCATA', 2, 2, 3, 4);
INSERT INTO partita (id, data_ora, luogo, goal_casa, goal_trasferta, stato_partita, torneo_id, arbitro_id, squadra_casa_id, squadra_trasferta_id) VALUES (5, '2026-06-10 18:00:00', 'Stadio Comunale', 1, 0, 'GIOCATA', 2, 3, 5, 6);
INSERT INTO partita (id, data_ora, luogo, goal_casa, goal_trasferta, stato_partita, torneo_id, arbitro_id, squadra_casa_id, squadra_trasferta_id) VALUES (6, '2026-06-12 21:00:00', 'Campo Sportivo Sud', NULL, NULL, 'PROGRAMMATA', 2, 1, 9, 10);

-- Partite nel FUTURO (Torneo 2027) per dimostrare i vincoli temporali
INSERT INTO partita (id, data_ora, luogo, goal_casa, goal_trasferta, stato_partita, torneo_id, arbitro_id, squadra_casa_id, squadra_trasferta_id) VALUES (7, '2027-05-15 21:00:00', 'Stadio Del Domani', NULL, NULL, 'PROGRAMMATA', 3, 1, 1, 4);
INSERT INTO partita (id, data_ora, luogo, goal_casa, goal_trasferta, stato_partita, torneo_id, arbitro_id, squadra_casa_id, squadra_trasferta_id) VALUES (8, '2027-05-22 21:00:00', 'Campo di Marte', NULL, NULL, 'PROGRAMMATA', 3, 2, 6, 3);

-- ==========================================
-- POPOLAMENTO TABELLA GIOCATORI
-- ==========================================
-- Lupi Invincibili (Roma - 11 giocatori)
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (1, 'Francesco', 'Il Capitano', '1976-09-27', 'Attaccante', 180, 1);
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (2, 'Daniele', 'Il Guerriero', '1983-07-24', 'Centrocampista', 184, 1);
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (3, 'Aldair', 'Pluto', '1965-11-30', 'Difensore', 183, 1);
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (4, 'Vincenzo', 'L''Aeroplanino', '1974-06-18', 'Attaccante', 172, 1);
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (5, 'Marcos', 'Il Pendolino', '1970-06-07', 'Difensore', 176, 1);
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (6, 'Walter', 'Il Muro', '1978-03-23', 'Difensore', 183, 1);
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (7, 'Vincent', 'Candela', '1973-10-24', 'Difensore', 180, 1);
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (8, 'Emerson', 'Il Puma', '1976-04-04', 'Centrocampista', 184, 1);
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (9, 'Damiano', 'Anima Candi', '1974-05-17', 'Centrocampista', 180, 1);
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (10, 'Marco', 'Supermarco', '1973-06-18', 'Attaccante', 186, 1);
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (11, 'Francesco', 'Batman', '1969-09-14', 'Portiere', 188, 1);

-- Bauscia Milano (11 giocatori)
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (12, 'Pippo', 'Il Rapace', '1973-08-09', 'Attaccante', 181, 3);
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (13, 'Christian', 'Il Bobo', '1973-07-12', 'Attaccante', 185, 3);
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (14, 'Paolo', 'Capitano Storico', '1968-06-26', 'Difensore', 186, 3);
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (15, 'Javier', 'Il Trattore', '1973-08-10', 'Difensore', 178, 3);
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (16, 'Andriy', 'Lo Zar', '1976-09-29', 'Attaccante', 183, 3);
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (17, 'Ronaldo', 'Il Fenomeno', '1976-09-18', 'Attaccante', 183, 3);
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (18, 'Ricardo', 'Bambino D''Oro', '1982-04-22', 'Centrocampista', 186, 3);
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (19, 'Clarence', 'Il Professore', '1976-04-01', 'Centrocampista', 176, 3);
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (20, 'Gennaro', 'Ringhio', '1978-01-09', 'Centrocampista', 177, 3);
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (21, 'Alessandro', 'Billy', '1966-04-24', 'Difensore', 183, 3);
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (22, 'Nelson', 'La Pantera', '1973-10-07', 'Portiere', 195, 3);

-- Scusate il Ritardo Napoli (Leggende napoletane)
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (23, 'Diego', 'Il D10S', '1960-10-30', 'Attaccante', 165, 4);
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (24, 'Antonio', 'Careca', '1960-10-05', 'Attaccante', 183, 4);
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (25, 'Marek', 'Marechiaro', '1987-07-27', 'Centrocampista', 183, 4);
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (26, 'Ciro', 'Ciruzzo', '1987-05-06', 'Attaccante', 169, 4);
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (27, 'Kalidou', 'Il Muro', '1991-06-20', 'Difensore', 187, 4);

-- Torino Granata Amatori (Leggende di Torino)
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (28, 'Alex', 'Pinturicchio', '1974-11-09', 'Attaccante', 174, 5);
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (29, 'Pavel', 'Furia Ceca', '1972-08-30', 'Centrocampista', 177, 5);
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (30, 'Gigi', 'Muro di Carrara', '1978-01-28', 'Portiere', 192, 5);
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (31, 'Paolo', 'Puliciclone', '1950-04-27', 'Attaccante', 174, 5);

-- Fiorentina Bistecca FC
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (32, 'Gabriel', 'Re Leone 2', '1969-02-01', 'Attaccante', 185, 6);
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (33, 'Rui', 'Il Maestro', '1972-03-29', 'Centrocampista', 180, 6);

-- Amatori Tortellino Bologna
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (34, 'Roberto', 'Il Divin Codino', '1967-02-18', 'Attaccante', 174, 8);
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (35, 'Beppe', 'Il Gol', '1968-02-17', 'Attaccante', 171, 8);

-- Borgata Palermo
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (36, 'Totò', 'Notti Magiche', '1964-12-01', 'Attaccante', 173, 9);
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (37, 'Luca', 'Il Cigno', '1964-07-09', 'Attaccante', 180, 9);

-- Cagliari Salsiccia e Mirto
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (38, 'Gigi', 'Rombo di Tuono', '1944-11-07', 'Attaccante', 180, 10);
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (39, 'Gianfranco', 'Magic Box', '1966-07-05', 'Attaccante', 168, 10);

-- I Butei di Verona
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (40, 'Dario', 'Il Bisonte', '1967-04-28', 'Attaccante', 185, 7);
INSERT INTO giocatore (id, nome, cognome, data_di_nascita, ruolo, altezza, squadra_id) VALUES (41, 'Preben', 'Il Sindaco', '1957-09-11', 'Attaccante', 183, 7);

-- ==========================================
-- POPOLAMENTO TABELLA COMMENTI
-- ==========================================
INSERT INTO commento (id, testo, data_ora, utente_id, partita_id) VALUES (101, 'Mamma mia il Capitano, che gol assurdo al torneo del quartiere!', '2026-06-01 22:45:00', 2, 1);
INSERT INTO commento (id, testo, data_ora, utente_id, partita_id) VALUES (102, 'I Lupi non perdono mai, nemmeno a calcetto dopolavoro.', '2026-06-08 20:10:00', 2, 2);
INSERT INTO commento (id, testo, data_ora, utente_id, partita_id) VALUES (103, 'Bobo e il Rapace non hanno toccato palla oggi. Troppa Roma.', '2026-06-15 22:50:00', 1, 3);
INSERT INTO commento (id, testo, data_ora, utente_id, partita_id) VALUES (104, 'Bella partita a San Siro Amatoriale, ho lasciato i polmoni in campo.', '2026-06-05 22:55:00', 2, 4);
