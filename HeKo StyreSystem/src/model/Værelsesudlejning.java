package model;

import java.time.LocalDate;

public class Værelsesudlejning {
	/**
	 * @param indflytningsdato : den dato hvor en ny beboer skal overtage et værelse
	 * @param værelse : Værelsesnummer
	 * @param navn	: indflytterens navn
	 * @param behandlingsdato: datoen hvor sagen er blevet behandlet
	 * @param behandlerInitialer : initialerne på indstillingsrepræsentanten
	 */
	public Værelsesudlejning(LocalDate indflytningsdato, String værelse, String navn, LocalDate behandlingsdato,
			String behandlerInitialer) {
		this.indflytningsdato = indflytningsdato;
		this.værelse = værelse;
		this.navn = navn;
		this.behandlingsdato = behandlingsdato;
		this.behandlerInitialer = behandlerInitialer;
	}
	private LocalDate indflytningsdato;
	private String værelse;
	private String navn;
	private LocalDate behandlingsdato;
	private String behandlerInitialer;
	public LocalDate getindflytningsdato() {
		return indflytningsdato;
	}
	public void setindflytningsdato(LocalDate indflytningsdato) {
		this.indflytningsdato = indflytningsdato;
	}
	public String getVærelse() {
		return værelse;
	}
	public void setVærelse(String værelse) {
		this.værelse = værelse;
	}
	public String getNavn() {
		return navn;
	}
	public void setNavn(String navn) {
		this.navn = navn;
	}
	public LocalDate getBehandlingsdato() {
		return behandlingsdato;
	}
	public void setBehandlingsdato(LocalDate behandlingsdato) {
		this.behandlingsdato = behandlingsdato;
	}
	public String getBehandlerInitialer() {
		return behandlerInitialer;
	}
	public void setBehandlerInitialer(String behandlerInitialer) {
		this.behandlerInitialer = behandlerInitialer;
	}

}
