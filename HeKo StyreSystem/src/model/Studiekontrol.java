package model;

import java.time.LocalDate;


import javafx.collections.ObservableList;

public class Studiekontrol {
	private ObservableList<Beboer> beboere;
	 private LocalDate afleveringsfrist;
	 private LocalDate påmindelse;
	 private LocalDate påbegyndelsesdato;
	 private int månedsnummer;
	 private boolean afsluttet;
	
 /**
	 * @param beboere
	 * @param afleveringsfrist
	 * @param påmindelse
	 * @param månedsnummer
	 * @param afsluttet: tilkendegiver om studiekontrollen er afsluttet
	 */
	public Studiekontrol(ObservableList<Beboer> beboere, LocalDate afleveringsfrist, LocalDate påmindelse, LocalDate påbegyndelsesdato, int månedsnummer, boolean afsluttet) {
		this.beboere = beboere;
		this.afleveringsfrist = afleveringsfrist;
		this.påmindelse = påmindelse;
		this.månedsnummer = månedsnummer;
		this.afsluttet = afsluttet;
		this.påbegyndelsesdato = påbegyndelsesdato;
	}
public ObservableList<Beboer> getBeboere() {
		return beboere;
	}
	public void setBeboere(ObservableList<Beboer> beboere) {
		this.beboere = beboere;
	}
	public LocalDate getAfleveringsfrist() {
		return afleveringsfrist;
	}
	public void setAfleveringsfrist(LocalDate afleveringsfrist) {
		this.afleveringsfrist = afleveringsfrist;
	}
	public LocalDate getPåmindelse() {
		return påmindelse;
	}
	public void setPåmindelse(LocalDate påmindelse) {
		this.påmindelse = påmindelse;
	}
	public int getMånedsnummer() {
		return månedsnummer;
	}
	public void setMånedsnummer(int månedsnummer) {
		this.månedsnummer = månedsnummer;
	}
	public boolean isAfsluttet() {
		return afsluttet;
	}
	public void setAfsluttet(boolean afsluttet) {
		this.afsluttet = afsluttet;
	}
	public LocalDate getPåbegyndelsesdato() {
		return påbegyndelsesdato;
	}
	public void setPåbegyndelsesdato(LocalDate påbegyndelsesdato) {
		this.påbegyndelsesdato = påbegyndelsesdato;
	}

 
}
