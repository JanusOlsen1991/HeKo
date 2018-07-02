package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
/**
 * 
 * @author Janus
 *Klassen anvendes ved oprettelse af dispensationer til en beboer
 *
 */

public class Dispensation {
private Beboer beboer;
private SimpleDateFormat startDato;
private SimpleDateFormat slutDato;
private ArrayList<Deadline> deadlines;
/**
 * @param beboer : beboeren der får dispensation
 * @param startDato : startdato for dispensation 
 * @param slutDato : slutdato for dispensation
 * @param deadlines : eventuelle deadlines 
 */
public Dispensation(Beboer beboer, SimpleDateFormat startDato, SimpleDateFormat slutDato,
		ArrayList<Deadline> deadlines) {
	this.beboer = beboer;
	this.startDato = startDato;
	this.slutDato = slutDato;
	this.deadlines = deadlines;
}
public Beboer getBeboer() {
	return beboer;
}
public void setBeboer(Beboer beboer) {
	this.beboer = beboer;
}
public SimpleDateFormat getStartDato() {
	return startDato;
}
public void setStartDato(SimpleDateFormat startDato) {
	this.startDato = startDato;
}
public SimpleDateFormat getSlutDato() {
	return slutDato;
}
public void setSlutDato(SimpleDateFormat slutDato) {
	this.slutDato = slutDato;
}
public ArrayList<Deadline> getDeadlines() {
	return deadlines;
}
public void setDeadlines(ArrayList<Deadline> deadlines) {
	this.deadlines = deadlines;
}


}
