package controller;

import java.io.FileOutputStream;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javafx.collections.ObservableList;
import model.Beboer;
import model.Deadline;


/**
 * Klassen binder modellageret sammen med brugerinteraktionen i viewet
 * @author Janus
 *
 */

public class Controller {
	//Controller oprettes selv som et objekt i viewet
	private ObservableList<Beboer> beboere;
	private ObservableList<Deadline> deadlines;
	private ObservableList<Beboer> fremlejere;
	private ObservableList<Studiekontrol> studiekontroller;
	private ArrayList værelsesudlejning;
	
	
	//Skal indeholde alle modellerne
	public static void main(String[]args) {
		//Til testing
	}
	public Controller() {
		//Skal ikke tage parametre, men indlæse fra Excel-fil og oprette de enkelte ObservableLists/ArrayLists.
		
	}
	
	public void createExcelFile() { 
	Workbook wb = new XSSFWorkbook();
	
	Sheet sheet1 = wb.createSheet("BeboerListe");
	
	try {
		FileOutputStream stream = new FileOutputStream("test1.xlsx");
		wb.write(stream);
		stream.close();
	}
	
	catch(Exception e){
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
	/**
	 * 
	 * @param antalKollonner antal kollonner i en observableList der skal fyldes
	 * @return
	 */
	public ObservableList<?> getObservableList(int antalKollonner ) {
		
		return null;
	}


}
