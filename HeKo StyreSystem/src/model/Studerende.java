package model;

import java.text.SimpleDateFormat;

public class Studerende {
	String navn;
	SimpleDateFormat indflytningsdato;
	Uddannelse uddannelse;
	Dispensation dispensation = null;
	
	public Studerende(String navn, SimpleDateFormat indflytningsdato, String uddannelsesretning, String uddannelsesinstitution, SimpleDateFormat påbegyndtDato, SimpleDateFormat forventetAfsluttetDato) {
		this.navn = navn;
		this.indflytningsdato = indflytningsdato;
		uddannelse = new Uddannelse(uddannelsesinstitution, uddannelsesretning, påbegyndtDato, forventetAfsluttetDato);
		
	}
	public Uddannelse getUddannelse() {
		return uddannelse;
	}
	public void setNavn(String navn) {
	this.navn = navn;
	}
	
	public String getNavn() {
		return navn;
	}
	public void setIndflytningsdato(SimpleDateFormat indflytningsdato) {
		this.indflytningsdato = indflytningsdato;
	}

}
