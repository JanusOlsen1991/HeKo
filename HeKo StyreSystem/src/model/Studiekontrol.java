package model;

import java.time.LocalDate;
import java.util.ArrayList;

import javafx.collections.ObservableList;

public class Studiekontrol {
	private ArrayList<Beboer> beboere;
	private LocalDate afleveringsfrist;
	private LocalDate påmindelse;
	private LocalDate påbegyndelsesdato;
	private int månedsnummer;
	private boolean afsluttet;
	private String studiekontrolID;

	/**
	 * @param beboere
	 * @param afleveringsfrist
	 * @param påmindelse
	 * @param månedsnummer
	 * @param afsluttet:
	 *            tilkendegiver om studiekontrollen er afsluttet
	 * 
	 */
	public Studiekontrol(ArrayList<Beboer> beboere, LocalDate afleveringsfrist, LocalDate påmindelse,
			LocalDate påbegyndelsesdato, int månedsnummer, boolean afsluttet, String studiekontrolID) {
		this.beboere = beboere;
		this.afleveringsfrist = afleveringsfrist;
		this.påmindelse = påmindelse;
		this.månedsnummer = månedsnummer;
		this.afsluttet = afsluttet;
		this.påbegyndelsesdato = påbegyndelsesdato;
		if (studiekontrolID != null)
			this.studiekontrolID = studiekontrolID;
		else
			this.studiekontrolID = opretStudiekontrolID();

	}

	private String opretStudiekontrolID() {
		String s;
		int måned = getMånedsnummer() + 4;
		int år;

		if (måned % 12 > 0)
			år = påbegyndelsesdato.getYear();
		else
			år = påbegyndelsesdato.getYear() + 1;

		switch (månedsnummer) {
		case 1:
			s = "Januar";
		case 2:
			s = "Februar";
		case 3:
			s = "Marts";
		case 4:
			s = "April";
		case 5:
			s = "Maj";
		case 6:
			s = "Juni";
		case 7:
			s = "Juli";
		case 8:
			s = "August";
		case 9:
			s = "September";
		case 10:
			s = "Oktober";
		case 11:
			s = "November";
		case 12:
			s = "December";
		default:
			s = "Ukendt måned";

		}
		return s+=år;
	}

	public ArrayList<Beboer> getBeboere() {
		return beboere;
	}

	public void setBeboere(ArrayList<Beboer> list) {
		this.beboere = list;
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

	public String getStudiekontrolID() {
		return studiekontrolID;
	}

	public void setStudiekontrolID(String studiekontrolID) {
		this.studiekontrolID = studiekontrolID;
	}

}
