package it.uniroma3.siw.dto;

public class SquadraDTO {
    private Long id;
    private String nome;
    private Long annoDiFondazione;
    private String citta;
	
    public SquadraDTO() {}

	public SquadraDTO(Long id, String nome, Long annoDiFondazione, String citta) {
		this.id = id;
		this.nome = nome;
		this.annoDiFondazione = annoDiFondazione;
		this.citta = citta;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Long getAnnoDiFondazione() {
		return annoDiFondazione;
	}

	public void setAnnoDiFondazione(Long annoDiFondazione) {
		this.annoDiFondazione = annoDiFondazione;
	}

	public String getCitta() {
		return citta;
	}

	public void setCitta(String citta) {
		this.citta = citta;
	}
    
    

   
}