package model;


import java.time.LocalDate;
/**
 * 
 * @author Janus
 * Klassen anvendes til at oprette deadlines/påmindelser i "hovedmenuen".
 */
public class Deadline {
	private String hvem;
	private String hvad;
	private LocalDate hvornår;
	/**
	 * @param hvem : Hvem der er deadline for
	 * @param hvad : hvad deadlinen er.
	 * @param hvornår2 : Hvornår skal deadlinen klares.
	 */
	public Deadline(String hvem, String hvad, LocalDate hvornår) {
		super();
		this.hvem = hvem;
		this.hvad = hvad;
		this.hvornår = hvornår;
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
	

}
