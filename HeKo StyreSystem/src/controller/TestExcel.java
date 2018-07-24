package controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.Beboer;
import model.Deadline;
import model.Studiekontrolstatus;
import model.Uddannelse;

public class TestExcel {
//This works i principle!
	public static void main(String[] args) throws InvalidFormatException, EncryptedDocumentException, IOException {
ExcelConnection ec = new ExcelConnection();
LocalDate d = LocalDate.now();
Uddannelse uddannelse = new Uddannelse("DTU", "Ingeniør", d, d);
Beboer janus = new Beboer("Janus", "422", uddannelse, d, d, "28459844", Studiekontrolstatus.GODKENDT);
ec.opretBeboerIExcel(janus);
Uddannelse u = new Uddannelse("DTU", "Ingeniør", d, d);
Beboer janus2 = new Beboer("Janus Olsen", "201", uddannelse, d, d, "28459844", Studiekontrolstatus.GODKENDT);
ec.opretBeboerIExcel(janus2);
Uddannelse u1 = new Uddannelse("DTU", "Ingeniør", d, d);
Beboer janus21 = new Beboer("Lort lugter", "422", uddannelse, d, d, "28459844", Studiekontrolstatus.GODKENDT);
ec.opretBeboerIExcel(janus21);
Uddannelse u11 = new Uddannelse("DTU", "Ingeniør", d, d);
Beboer janus211 = new Beboer("Pis", "417", uddannelse, d, d, "28459844", Studiekontrolstatus.GODKENDT);
ec.opretBeboerIExcel(janus211);
for (int i = 0; i< ec.getBeboere().size(); i++) {
	System.out.println(ec.getBeboere().get(i).getVærelse());
	System.out.println(ec.getBeboere().get(i).getIndflytningsdato().toString());

}




////		//Metode herunder skal anvendes til at opdatere og skrive til excel
//		FileInputStream fis = new FileInputStream("IndstillingsInfo.xlsx");
//		Workbook workbook = WorkbookFactory.create(fis);
//
//		//Modify the workbook as you wish
//		//As an example, we override the first cell of the first row in the first sheet (0-based indices)
//		LocalDate dPrint = ec.konverterDateTilLocalDate(workbook.getSheetAt(0).getRow(1).getCell(7).getDateCellValue());
//		System.out.println(dPrint.toString());
////		workbook.getSheetAt(2).getRow(0).getCell(0).setCellValue("Marc er en kæmpe hat");
////		//Disse metoder virker til at lave ny 
////		workbook.getSheetAt(2).createRow(2).createCell(0).setCellValue("Marc er en kæmpe hat");
////		//Sådan kan du overskrive en række.
////		workbook.getSheetAt(2).createRow(0).createCell(0).setCellValue("janus er mega awesome");
//
//
//		//you have to close the input stream FIRST before writing to the same file.
//		fis.close() ;
//
//		//save your changes to the same file.
//		workbook.write(new FileOutputStream("IndstillingsInfo.xlsx")); 
//		workbook.close();
	}

}
