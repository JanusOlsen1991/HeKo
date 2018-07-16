package model;

import java.text.SimpleDateFormat;

public class Beboer {
private String navn;
private String værelse;
private Uddannelse uddannelse;
private SimpleDateFormat indflytningsdato;
private SimpleDateFormat lejeaftalensUdløb;
private boolean studieaktiv;
/**
 * @param navn : Beboerens navn
 * @param værelse : Værelsesnummer
 * @param uddannelse : Uddannelsesinformationer
 * @param indflytningsdato : Dato beboeren har lejemålet fra.
 * @param lejeAftalensUdløb : datoen der kan komme til at danne grundlag for hvornår der påbegynes studiekontrol for beboeren
 */
public Beboer(String navn, String værelse, Uddannelse uddannelse, SimpleDateFormat indflytningsdato,
		SimpleDateFormat lejeaftalensUdløb) {
	this.navn = navn;
	this.værelse = værelse;
	this.uddannelse = uddannelse;
	this.indflytningsdato = indflytningsdato;
	this.lejeaftalensUdløb = lejeaftalensUdløb;
}
public String getNavn() {
	return navn;
}
public void setNavn(String navn) {
	this.navn = navn;
}
public String getVærelse() {
	return værelse;
}
public void setVærelse(String værelse) {
	this.værelse = værelse;
}
public Uddannelse getUddannelse() {
	return uddannelse;
}
public void setUddannelse(Uddannelse uddannelse) {
	this.uddannelse = uddannelse;
}
public SimpleDateFormat getIndflytningsdato() {
	return indflytningsdato;
}
public void setIndflytningsdato(SimpleDateFormat indflytningsdato) {
	this.indflytningsdato = indflytningsdato;
}
public SimpleDateFormat getLejeaftalensUdløbl() {
	return lejeaftalensUdløb;
}
public void setLejeaftalensUdløb(SimpleDateFormat sidsteStudiekontrol) {
	this.lejeaftalensUdløb = sidsteStudiekontrol;
}
public boolean isStudieaktiv() {
	return studieaktiv;
}
public void setStudieaktiv(boolean studieaktiv) {
	this.studieaktiv = studieaktiv;
}

}
