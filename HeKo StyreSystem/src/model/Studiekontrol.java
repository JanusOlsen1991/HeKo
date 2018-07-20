package model;

import java.time.LocalDate;


import javafx.collections.ObservableList;

public class Studiekontrol {
	private ObservableList<Beboer> beboere;
	 private LocalDate afleveringsfrist;
	 private LocalDate påmindelse;
	 private int månedsnummer;
	
 /**
	 * @param beboere
	 * @param afleveringsfrist
	 * @param påmindelse
	 * @param månedsnummer
	 */
	public Studiekontrol(ObservableList<Beboer> beboere, LocalDate afleveringsfrist, LocalDate påmindelse, int månedsnummer) {
		this.beboere = beboere;
		this.afleveringsfrist = afleveringsfrist;
		this.påmindelse = påmindelse;
		this.månedsnummer = månedsnummer;
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

 
}
