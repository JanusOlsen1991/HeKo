package controller;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestExcel {
//This works i principle!
	public static void main(String[] args) {
ExcelConnection ec = new ExcelConnection();
Workbook wb = new XSSFWorkbook();
Sheet sheet = wb.createSheet();
Row row = sheet.createRow(0);
//Date date = new Date(2000-1900, 10-1, 32);
//Calendar c = Calendar.getInstance();

Cell cell = row.createCell(0);
cell = ec.createExcelDateFormat(row, wb, 0, null);

if(cell.getDateCellValue() == null)
	System.out.println("Cellen er tom");
//LocalDate ld = ec.konverterDateTilLocalDate(cell);
//System.out.println(ld.toString());
//
//date = ec.konverterLocalDateTilDate(ld);
//System.out.println(date.toString());

	}

}
