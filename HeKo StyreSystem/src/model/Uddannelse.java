package model;

import java.text.SimpleDateFormat;

/**
 * Uddannelsesklassen anvendes til at tilkendegive den studerendes uddannelsesforhold.
 * @author Janus
 *
 */
public class Uddannelse {
	
	private String uddannelsesretning;
	private String uddannelsessted;
	private SimpleDateFormat påbegyndtDato;
	private SimpleDateFormat forventetAfsluttetDato;
	
	/**
	 * 
	 * @param uddannelsessted : en der udbyder uddannelsesretningen
	 * @param uddannelsesretning : Den bestemte uddannelsesretning den studerende følger.
	 */
	public Uddannelse(String uddannelsessted, String uddannelsesretning, SimpleDateFormat påbegyndtDato, SimpleDateFormat forventetAfsluttetDato) {
		this.uddannelsessted = uddannelsessted;
		this.uddannelsesretning = uddannelsesretning;
		this.forventetAfsluttetDato = forventetAfsluttetDato;
		this.påbegyndtDato = påbegyndtDato;
		
	}
	public String getUddannelsesretning() {
		return uddannelsesretning;
	}
	public void setUddannelsesretning(String uddannelsesretning) {
		this.uddannelsesretning = uddannelsesretning;
	}
	public String getUddannelses() {
		return uddannelsessted;
	}
	public void setUddannelses(String uddannelsessted) {
		this.uddannelsessted = uddannelsessted;
	}
	public SimpleDateFormat getForventetAfsluttetDato() {
		return forventetAfsluttetDato;
	}
	public void setForventetAfsluttetDato(SimpleDateFormat forventetAfsluttetDato) {
		this.forventetAfsluttetDato = forventetAfsluttetDato;
	}
	public SimpleDateFormat getPåbegyndtDato() {
		return påbegyndtDato;
	}
	public void setPåbegyndtDato(SimpleDateFormat påbegyndtDato) {
		this.påbegyndtDato = påbegyndtDato;
	}
	
	

}
