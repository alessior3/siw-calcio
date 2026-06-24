package it.uniroma3.siw.controller;

import it.uniroma3.siw.dto.SquadraDTO;
import it.uniroma3.siw.model.Squadra;
import it.uniroma3.siw.service.SquadraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173", allowCredentials = "true") // Autorizza Vite (React) e i cookie di sessione
public class SquadraRestController {

    @Autowired
    private SquadraService squadraService;

    @GetMapping("/api/squadre")
    public List<SquadraDTO> getTutteLeSquadre() {
        // 1. Recupero le entità originali dal DB
        List<Squadra> squadre = (List<Squadra>) squadraService.findAll();
        
        // 2. Creo una lista vuota di DTO
        List<SquadraDTO> squadreDTO = new ArrayList<>();
        
        // 3. Travaso i dati sicuri dall'Entity al DTO
        for (Squadra s : squadre) {
            SquadraDTO dto = new SquadraDTO();
            dto.setId(s.getId());
            dto.setNome(s.getNome());
            dto.setAnnoDiFondazione(s.getAnnoDiFondazione());
            dto.setCitta(s.getCitta());
            
            squadreDTO.add(dto);
        }
        
        // 4. Spring Boot trasformerà automaticamente questa lista in JSON! [2]
        return squadreDTO; 
    }
}