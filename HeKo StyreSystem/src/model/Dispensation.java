package model;

import java.time.LocalDate;
import java.util.ArrayList;
/**
 * 
 * @author Janus
 *Klassen anvendes ved oprettelse af dispensationer til en beboer
 *
 */

public class Dispensation {
private Beboer beboer;
private LocalDate startDato;
private LocalDate slutDato;
private ArrayList<Deadline> deadlines;
/**
 * @param beboer : beboeren der får dispensation
 * @param startDato : startdato for dispensation 
 * @param slutDato : slutdato for dispensation
 * @param deadlines : eventuelle deadlines 
 */
public Dispensation(Beboer beboer, LocalDate startDato, LocalDate slutDato,
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
public LocalDate getStartDato() {
	return startDato;
}
public void setStartDato(LocalDate startDato) {
	this.startDato = startDato;
}
public LocalDate getSlutDato() {
	return slutDato;
}
public void setSlutDato(LocalDate slutDato) {
	this.slutDato = slutDato;
}
public ArrayList<Deadline> getDeadlines() {
	return deadlines;
}
public void setDeadlines(ArrayList<Deadline> deadlines) {
	this.deadlines = deadlines;
}


}
