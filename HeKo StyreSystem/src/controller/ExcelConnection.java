package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.ExcelStyleDateFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javafx.collections.ObservableList;
import model.Beboer;
import model.Deadline;
import model.Studiekontrol;
import model.Studiekontrolstatus;
import model.Uddannelse;
import model.Værelsesudlejning;

/**
 * Klassen binder modellageret sammen med brugerinteraktionen i viewet
 * 
 * @author Janus
 *
 */

public class ExcelConnection {
	// Controller oprettes selv som et objekt i viewet
	private ObservableList<Beboer> beboere;
	private ObservableList<Deadline> deadlines;
	private ObservableList<Beboer> fremlejere;
	private ArrayList<Studiekontrol> studiekontroller;// Bør ikke være der? - evt. giv beboer en variabel som tilkendegiver status?
	private ObservableList<Værelsesudlejning> værelsesudlejning;//Evt. to obsLister? - En til udlejede og en til ikke udlejede
	
	Workbook wb = new XSSFWorkbook();
	Sheet sheet;
	Cell cell;

	public ExcelConnection() {
		opretBeboereFraExcel(); // beboere oprettes
		opretDeadlinesFraExcel();
		opretFremlejerFraExcel();
		opretStudiekontrollerFraExcel();
		opretVærelsesudlejningFraExcel();
	}
private void opretStudiekontrollerFraExcel() {
		
		//loop der kører både beboere og fremlejer igennem
	// tjek studiekontrolstatus
	//Hvis studiekontrolstatus er andet end IKKEIGANG
	//Så tilføj dem til en liste/set
	//tredje ydre loop opretter studiekontrolobjektet der skal fyldes og lægges i 
	//Søg listen igennem og inddel dem i studiekontroller med udgangspunkt i lejeaftalensudløb - ydre loop styrer måneden - indre loop kører listen igennem
	//
	//
		
	}
/**
 * Metoden henter både udlejede og ikke udlejede værelser og gemmer dem i en observableList ved navn værelsesudlejning
 */
	private void opretVærelsesudlejningFraExcel() {
		try (Workbook wb = WorkbookFactory.create(new File("IndstillingsInfo.xlsx"))) {

			Sheet sheet = wb.getSheet("Værelsesudlejning");
			int startRække = sheet.getFirstRowNum() + 1;// +1 for ikk at tageoverskriften med

			int slutRække = sheet.getLastRowNum();

			for (int i = startRække; i < slutRække; i++) {
				Row row = sheet.getRow(i);

				int kollonnenummer = 0;
				Cell cell;
				cell = row.getCell(kollonnenummer);
				LocalDate indflytningsdato = konverterDateTilLocalDate(cell);
				cell = row.getCell(kollonnenummer++);
				String værelse = cell.getStringCellValue();
				cell = row.getCell(kollonnenummer++);
				String navn = cell.getStringCellValue();
				cell = row.getCell(kollonnenummer++);
				LocalDate behandlingsdato = konverterDateTilLocalDate(cell);
				cell = row.getCell(kollonnenummer++);
				String behandlerinitialer = cell.getStringCellValue();
				

				Værelsesudlejning v = new Værelsesudlejning(indflytningsdato, værelse, navn, behandlingsdato, behandlerinitialer);
				værelsesudlejning.add(v);

			}
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			System.out.println("Filen kan ikke findes");
			e.printStackTrace();
		}		
	}


	/**
	 * Metoden konverterer String til Enum
	 * @param s String i forbindelse med studiekontrolstatus for beboer som skal konverteres til ENUM
	 * @return Enum tilsvarende string der gemmes i Exceldokumentet
	 */
	private Enum<Studiekontrolstatus> konverterStringTilEnum(String s){
		Enum<Studiekontrolstatus> status;
		switch(s) {
		case "Ikke i gang" :
			status =  Studiekontrolstatus.IKKEIGANG;
			return status;
		case "Modtaget, ikke godkendt" :
			status = Studiekontrolstatus.MODTAGETIKKEGODKENDT;
			return status;
		case "Ikke Modtaget":
			status = Studiekontrolstatus.IKKEAFLEVERET;
			return status;
		case "Sendt til boligselskab":
			status = Studiekontrolstatus.SENDTTILBOLIGSELSKAB;
			return status;
		default :
			return null;
		}
		
	}
	/**
	 * Metoden konverterer Enum til String
	 * @param studiekontrolstatus Den Enum der skal konverteres til en string der kan gemmes i Exceldokumentet
	 * @return String på studiekontrolstatus
	 */
	private String konverterEnumTilString(Studiekontrolstatus studiekontrolstatus){
		String s;
		switch(studiekontrolstatus) {
		case  IKKEIGANG:
			s = "Ikke i gang" ;
			return s;
		case  MODTAGETIKKEGODKENDT:
			s = "Modtaget, ikke godkendt" ;
			return s;
		case IKKEAFLEVERET:
			s = "Ikke Modtaget";
			return s;
		case SENDTTILBOLIGSELSKAB:
			s = "Sendt til boligselskab";
			return s;
		default :
			return null;
		}
		
	}

	private void opretFremlejerFraExcel() {
		try (Workbook wb = WorkbookFactory.create(new File("IndstillingsInfo.xlsx"))) {

			Sheet sheet = wb.getSheet("Fremlejer");
			int startRække = sheet.getFirstRowNum() + 1;// +1 for ikk at tageoverskriften med

			int slutRække = sheet.getLastRowNum();

			for (int i = startRække; i < slutRække; i++) {
				Row row = sheet.getRow(i);
				// Load de forskellige ting til "beboere her"
				int kollonnenummer = 0;
				Cell cell;
				cell = row.getCell(kollonnenummer);
				String værelse = cell.getStringCellValue();
				cell = row.getCell(kollonnenummer++);
				String navn = cell.getStringCellValue();
				cell = row.getCell(kollonnenummer++);
				LocalDate indflytning = konverterDateTilLocalDate(cell);
				cell = row.getCell(kollonnenummer++);
				String uddannelsessted = cell.getStringCellValue();
				cell = row.getCell(kollonnenummer++);
				String uddannelsesretning = cell.getStringCellValue();
				cell = row.getCell(kollonnenummer++);
				LocalDate uddStart = konverterDateTilLocalDate(cell);
				cell = row.getCell(kollonnenummer++);
				LocalDate uddSlut = konverterDateTilLocalDate(cell);
				cell = row.getCell(kollonnenummer++);
				LocalDate lejeaftalensUdløb = konverterDateTilLocalDate(cell);
				cell = row.getCell(kollonnenummer++);
				String telefonnummer = cell.getStringCellValue();
				cell = row.getCell(kollonnenummer++);
				
				Enum<Studiekontrolstatus> studiekontrolstatus = konverterStringTilEnum(cell.getStringCellValue());
				Uddannelse uddannelse = new Uddannelse(uddannelsessted, uddannelsesretning, uddStart, uddSlut);
				Beboer beboer = new Beboer(værelse, navn, uddannelse, indflytning, lejeaftalensUdløb, telefonnummer, studiekontrolstatus);
				fremlejere.add(beboer);

			}
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			System.out.println("Filen kan ikke findes");
			e.printStackTrace();
		}
		
	}

	private void opretDeadlinesFraExcel() {
		try (Workbook wb = WorkbookFactory.create(new File("IndstillingsInfo.xlsx"))) {

			Sheet sheet = wb.getSheet("Deadlines");
			int startRække = sheet.getFirstRowNum() + 1;// +1 for ikk at tageoverskriften med

			int slutRække = sheet.getLastRowNum();

			for (int i = startRække; i < slutRække; i++) {
				Row row = sheet.getRow(i);
				// Load de forskellige ting til "beboere her"
				int kollonnenummer = 0;
				Cell cell;
				cell = row.getCell(kollonnenummer);
				String hvem = cell.getStringCellValue();
				cell = row.getCell(kollonnenummer++);
				String hvad = cell.getStringCellValue();
				cell = row.getCell(kollonnenummer++);
				LocalDate hvornår = konverterDateTilLocalDate(cell);

				Deadline deadline = new Deadline(hvem, hvad, hvornår);
				deadlines.add(deadline);

			}
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			System.out.println("Filen kan ikke findes");
			e.printStackTrace();
		}

	}

	/**
	 * Metoden opretter fanerne og overskrifter i en excelfil. Skal anvendes hvis
	 * filen ikke kan findes.
	 */
	public void createExcelFile() {

		Sheet sheet1 = wb.createSheet("Beboerliste");
		Sheet sheet2 = wb.createSheet("Dispensationer");
		Sheet sheet3 = wb.createSheet("Fremlejer");
		Sheet sheet4 = wb.createSheet("Deadlines");
		// Evt. flere sheets

		// Sætter titler på Sheets
		Row row = sheet1.createRow(0);

		int start = 0;

		row.createCell(start).setCellValue("Værelse");
		row.createCell(start++).setCellValue("Navn");
		row.createCell(start++).setCellValue("Indflytningsdato");
		row.createCell(start++).setCellValue("Uddannelsessted");
		row.createCell(start++).setCellValue("Uddannelsesretning");
		row.createCell(start++).setCellValue("Uddannelse påbegyndt:");
		row.createCell(start++).setCellValue("Uddannelse forventes afsluttet");
		row.createCell(start++).setCellValue("Udløbsdato på lejeaftale");
		row.createCell(start++).setCellValue("Telefonnummer");

		Cell c = row.createCell(0);// Anden måde at kreere celle på
		c.getStringCellValue();// forskellige metoder til at hente bestemte typer data

		try {
			FileOutputStream stream = new FileOutputStream("IndstillingsInfo.xlsx");
			wb.write(stream);
			stream.close();
		}

		catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				wb.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void skrivAlleBeboereTilExcel(Workbook wb, Sheet sheet) {

		// Sætter titler på Sheets
		Row row = sheet.createRow(0);

		int start = 0;

		row.createCell(start).setCellValue("Værelse");
		row.createCell(start++).setCellValue("Navn");
		row.createCell(start++).setCellValue("Indflytningsdato");
		row.createCell(start++).setCellValue("Uddannelsessted");
		row.createCell(start++).setCellValue("Uddannelsesretning");
		row.createCell(start++).setCellValue("Uddannelse påbegyndt:");
		row.createCell(start++).setCellValue("Uddannelse forventes afsluttet");
		row.createCell(start++).setCellValue("Udløbsdato på lejeaftale");
		row.createCell(start++).setCellValue("Telefonnummer");
		// Fyld sheet1 med beboerinformationer
		for (int i = 1; i < beboere.size(); i++) {
			row = sheet.createRow(i);
			start = 0;

			Cell c = row.createCell(0);

			Date d = new Date(2000 - 1900, 10 - 1, 29, 21, 03); // Find konvertering mellem LocalDate og
																// Date
			row.createCell(start).setCellValue(beboere.get(i).getVærelse());// Værelsesnr
			row.createCell(start++).setCellValue(beboere.get(i).getNavn());// Navn
			c = createExcelDateFormat(row, wb, start++, d);// indflytningsdato
			row.createCell(start++).setCellValue(beboere.get(i).getUddannelse().getUddannelsessted()); // uddannelsessted
			row.createCell(start++).setCellValue(beboere.get(i).getUddannelse().getUddannelsesretning()); // uddannelsesretning
			c = createExcelDateFormat(row, wb, start++, d);// uddannelse påbegyndt //d skal konverteres fra en
															// localdate.
			// LocalDate skal konverteres til Date()
			c = createExcelDateFormat(row, wb, start++, d);// uddannelse forventes afsluttet //Samme som ovenover mht. d
			c = createExcelDateFormat(row, wb, start++, d);// udløb på lejeaftale //Samme som ovenover mht. d
			row.createCell(start++).setCellValue(beboere.get(i).getTelefonnummer());

		}
	}

	public ObservableList<Beboer> getBeboere() {

		return beboere;
	}

	public Cell createExcelDateFormat(Row row, Workbook wb, int rækkeplads, Date d) {
		Cell c = row.createCell(rækkeplads);
		DataFormat format = wb.createDataFormat();
		CellStyle datestyle = wb.createCellStyle();
		datestyle.setDataFormat(format.getFormat("dd.MM.yyyy"));
		c.setCellStyle(datestyle);
		// Problemet er at der kun tilskrives dags dato
		// Find metode til at sætte en hvilken som helst dato ind
		c.setCellValue(d); // new Date()
		return c;
	}

	public void opretBeboereFraExcel() {
		// WorkbookFactory oprettes ud fra den givne fil
		try (Workbook wb = WorkbookFactory.create(new File("IndstillingsInfo.xlsx"))) {

			Sheet sheet = wb.getSheet("Beboerliste");
			int startRække = sheet.getFirstRowNum() + 1;// +1 for ikk at tageoverskriften med

			int slutRække = sheet.getLastRowNum() + 1;

			for (int i = startRække; i < slutRække; i++) {
				Row row = sheet.getRow(i);
				// Load de forskellige ting til "beboere her"
				int kollonnenummer = 0;
				Cell cell;
				cell = row.getCell(kollonnenummer);
				String værelse = cell.getStringCellValue();
				cell = row.getCell(kollonnenummer++);
				String navn = cell.getStringCellValue();
				cell = row.getCell(kollonnenummer++);
				LocalDate indflytning = konverterDateTilLocalDate(cell);
				cell = row.getCell(kollonnenummer++);
				String uddannelsessted = cell.getStringCellValue();
				cell = row.getCell(kollonnenummer++);
				String uddannelsesretning = cell.getStringCellValue();
				cell = row.getCell(kollonnenummer++);
				LocalDate uddStart = konverterDateTilLocalDate(cell);
				cell = row.getCell(kollonnenummer++);
				LocalDate uddSlut = konverterDateTilLocalDate(cell);
				cell = row.getCell(kollonnenummer++);
				LocalDate lejeaftalensUdløb = konverterDateTilLocalDate(cell);
				cell = row.getCell(kollonnenummer++);
				String telefonnummer = cell.getStringCellValue();
				cell = row.getCell(kollonnenummer++);
				
				Enum<Studiekontrolstatus> studiekontrolstatus = konverterStringTilEnum(cell.getStringCellValue());
				Uddannelse uddannelse = new Uddannelse(uddannelsessted, uddannelsesretning, uddStart, uddSlut);
				Beboer beboer = new Beboer(værelse, navn, uddannelse, indflytning, lejeaftalensUdløb, telefonnummer, studiekontrolstatus);
				fremlejere.add(beboer);

			}
		} catch (EncryptedDocumentException | InvalidFormatException | IOException e) {
			System.out.println("Filen kan ikke findes");
			e.printStackTrace();
		}

	}

	/**
	 * Metoden tager en Excelcelle indeholdende en Date() og konverterer
	 * Date-objektet til et LocalDate Objekt.
	 * 
	 * @param cell
	 *            er cellen indeholdende Datoen.
	 * @return LocalDate objektet.
	 */
	public LocalDate konverterDateTilLocalDate(Cell cell) {
		// herunder konverteres date til LocalDate
		Date input = cell.getDateCellValue();
		Instant instant = input.toInstant();
		ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
		LocalDate localDate = zdt.toLocalDate();
		return localDate;
	}

	/**
	 * Metoden konverterer et localdate objekt til et Date() objekt.
	 * 
	 * @param dato
	 *            : datoen der skal konverteres
	 * @return Date : et DateObjekt()
	 */
	public Date konverterLocalDateTilDate(LocalDate dato) {
		Date date = Date.from(dato.atStartOfDay(ZoneId.systemDefault()).toInstant());
		return date;
	}
}
