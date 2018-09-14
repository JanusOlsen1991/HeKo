package view;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import controller.ExcelConnection;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.Deadline;

public class Main extends Application {
	ExcelConnection ec = new ExcelConnection();
	GUI g = new GUI(ec);

	public static void main(String[] args) {

		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		//TODO hent filplacering fra txt, hvis denne ikke kan findes , da skal filen så oprettes på et sted og filplacering skal gemmes
		
        // The name of the file to open.
        String fileName = "Excelplacering.txt";

        // This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);
            
            line = bufferedReader.readLine();
//            while((line = bufferedReader.readLine()) != null) {
//            
//            }   

            // Always close files.
            bufferedReader.close();
            g.hovedMenu(primaryStage);

        }
        catch(Exception ex) {
        	//TODO lav filen/placeringen
        	FileChooser file = new FileChooser();
    		file.setTitle("Vælg filplacering");
    		File excelplacering = file.showOpenDialog(primaryStage);
    		try {
                // Assume default encoding.
                FileWriter fileWriter =
                    new FileWriter(fileName);

                // Always wrap FileWriter in BufferedWriter.
                BufferedWriter bufferedWriter =
                    new BufferedWriter(fileWriter);

                // Note that write() does not automatically
                // append a newline character.
                System.out.println(excelplacering.getAbsolutePath());
                bufferedWriter.write(excelplacering.getAbsolutePath());
                

                // Always close files.
                bufferedWriter.close();
                g.hovedMenu(primaryStage);
            }
            catch(IOException ex1) {
                System.out.println(
                    "Error writing to file '"
                    + fileName + "'");
                // Or we could just do this:
                // ex.printStackTrace();
            }
        }

    

		
		
		
		boolean fileFound;
		if(fileFound = false) {
		
		
		}

		
	}
}
