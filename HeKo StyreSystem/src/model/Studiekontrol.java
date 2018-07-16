package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Studiekontrol {
	private ArrayList<Beboer> beboere;
	 private SimpleDateFormat afleveringsfrist;
	 private SimpleDateFormat påmindelse;
	
 public ArrayList getBeboere() {
		return beboere;
	}
	public void setBeboere(ArrayList<Beboer> beboere) {
		this.beboere = beboere;
	}
	public SimpleDateFormat getAfleveringsfrist() {
		return afleveringsfrist;
	}
	public void setAfleveringsfrist(SimpleDateFormat afleveringsfrist) {
		this.afleveringsfrist = afleveringsfrist;
	}
	public SimpleDateFormat getPåmindelse() {
		return påmindelse;
	}
	public void setPåmindelse(SimpleDateFormat påmindelse) {
		this.påmindelse = påmindelse;
	}

 
}
