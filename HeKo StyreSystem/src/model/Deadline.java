package model;

import java.text.SimpleDateFormat;
/**
 * 
 * @author Janus
 * Klassen anvendes til at oprette deadlines/påmindelser i "hovedmenuen".
 */
public class Deadline {
	private String hvem;
	private String hvad;
	private SimpleDateFormat hvornår;
	
	/**
	 * @param hvem : Hvem der er deadline for
	 * @param hvad : hvad deadlinen er.
	 * @param hvornår : Hvornår skal deadlinen klares.
	 */
	public Deadline(String hvem, String hvad, SimpleDateFormat hvornår) {
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
	public SimpleDateFormat getHvornår() {
		return hvornår;
	}
	public void setHvornår(SimpleDateFormat hvornår) {
		this.hvornår = hvornår;
	}
	

}
