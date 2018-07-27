package model;

import java.time.LocalDate;
import java.util.ArrayList;
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
	 * VIGTIGT - HUSK AT BRUG ID PARAMETERen RIGTIGT - ELLERS VIL DER HELE TIDEN BLIVE OPRETTET EN NY DEADLINE
	 * @param hvem
	 *            : Hvem der er deadline for (værelse)((indstilling)
	 * @param hvad
	 *            : hvad deadlinen er.
	 * @param hvornår
	 *            : Hvornår skal deadlinen klares.
	 * @param ID
	 *            : Hvis ikke der findes et id sættes denne null og et ID oprettes med udgangspunkt i placering i listen. ID skal som udgangspunkt kun sættes 
	 * @param en
	 *            beboerliste vedhæftes så id kan oprettes ved at finde navn til det
	 *            tilsvarende værelsesnummer
	 */
	public Deadline(String hvem, String hvad, LocalDate hvornår, String ID, List<Deadline> list) {

		this.hvem = hvem;
		this.hvad = hvad;
		this.hvornår = hvornår;
		klaret = false;
		// Opretter ID ud fra placering i deadlines listen
		if (ID == null) {
			this.setID(Integer.toString(list.size())); // skal der være +1?

		} else
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

	public void setID(String ID) {
		this.ID = ID;
	}

}
