package model;

import java.time.LocalDate;
import java.util.List;

/**
 * 
 * @author Janus Klassen anvendes til at oprette deadlines/påmindelser i
 *         "hovedmenuen".
 */
public class Deadline {
	private String hvem;
	private String hvad;
	private LocalDate hvornår;
	private boolean klaret;
	private String ID;// Et ID kan sammensættes ud fra værelsesnummer og beboer.getNavn

	/**
	 * @param hvem
	 *            : Hvem der er deadline for (værelse)((indstilling)
	 * @param hvad
	 *            : hvad deadlinen er.
	 * @param hvornår2
	 *            : Hvornår skal deadlinen klares.
	 * @param ID
	 *            : Hvis ikke der findes et id sættes denne null. (Hvis id indlæses
	 *            fra Excel skal denne ikke være null). ID sammensættes af værelsesnummer + beboernavn + plads i listen
	 * @param en
	 *            beboerliste vedhæftes så id kan oprettes ved at finde navn til det tilsvarende værelsesnummer
	 */
	public Deadline(String hvem, String hvad, LocalDate hvornår, String ID, List<Beboer> list) {

		this.hvem = hvem;
		this.hvad = hvad;
		this.hvornår = hvornår;
		klaret = false;
		// Opretter ID ud fra værelse og navn
		if (ID == null) {
			for (int i = 0; i < list.size(); i++) {
				if (list.get(i).getVærelse().equals(hvem))
				setID(hvem + list.get(i).getNavn()); //evt. også + arrayplads
			}
		
		}
		else
			this.ID = ID;

	}

	public String getHvem() {
		return hvem;
	}

	public void setHvem(String hvem) {
		this.hvem = hvem;
	}

	public String getHvad() {
		return hvad;
	}

	public void setHvad(String hvad) {
		this.hvad = hvad;
	}

	public LocalDate getHvornår() {
		return hvornår;
	}

	public void setHvornår(LocalDate hvornår) {
		this.hvornår = hvornår;
	}

	public boolean isKlaret() {
		return klaret;
	}

	public void setKlaret(boolean klaret) {
		this.klaret = klaret;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

}
