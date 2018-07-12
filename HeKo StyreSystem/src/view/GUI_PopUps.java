package view;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Deadline;

public class GUI_PopUps {
	private Stage stage = new Stage();

	public void createDeadline() {

		// stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Opret Deadline");

		Label l1 = new Label("Hvem?");
		Label l2 = new Label("Hvad?");
		Label l3 = new Label("Hvornår?");
		DatePicker hvornår = new DatePicker();
		TextField hvad = new TextField();
		TextField hvem = new TextField();

		Button opretButton = new Button("Tilføj påmindelse");
		Button annullerButton = new Button("Annuller");

		GridPane layout = new GridPane();
		layout.add(l1, 3, 3);
		layout.add(hvem, 3, 5);
		layout.add(l2, 5, 3);
		layout.add(hvad, 5, 5);
		layout.add(l3, 7, 3);
		layout.add(hvornår, 7, 5);
		layout.add(opretButton, 5, 7);
		layout.add(annullerButton, 7, 7);
		Scene scene = new Scene(layout);
		stage.setScene(scene);
		stage.show();
	}

	public void changeDeadline(Deadline deadline) {

		stage.setTitle("Rediger deadline");

		GridPane layout = new GridPane();
		// getDeadline() - should add to the when, where who;

		Button ændrButton = new Button("Gem ændringer");
		Button annullerButton = new Button("Anuller");

		Label l1 = new Label("Hvem?");
		Label l2 = new Label("Hvad?");
		Label l3 = new Label("Hvornår?");
		DatePicker hvornår = new DatePicker();
		TextField hvad = new TextField();
		TextField hvem = new TextField();
		hvem.setText("Roomnumber");

		layout.add(l1, 3, 3);
		layout.add(hvem, 3, 5);
		layout.add(l2, 5, 3);
		layout.add(hvad, 5, 5);
		layout.add(l3, 7, 3);
		layout.add(hvornår, 7, 5);
		layout.add(ændrButton, 7, 7);
		layout.add(annullerButton, 5, 7);

		Scene scene = new Scene(layout);
		stage.setScene(scene);
		stage.show();
	}

	public void redigerBeboeroplysninger() {
		stage.setTitle("Rediger beboeroplysninger");

		GridPane layout = new GridPane();

		Label l1 = new Label("Værelse:");
		Label l2 = new Label("Navn:");
		Label l3 = new Label("Uddannelsessted");
		Label l4 = new Label("Uddannelsesretning");
		Label l5 = new Label("Uddannelse påbegyndt");
		Label l6 = new Label("Forventet uddannelsesafslutning");

		TextField værelse = new TextField();
		TextField navn = new TextField();
		TextField uddannelsessted = new TextField();
		TextField uddannelsesretning = new TextField();
		DatePicker uddStart = new DatePicker();
		DatePicker uddSlut = new DatePicker();

		Button gemButton = new Button("Gem ændringer");
		Button annullerButton = new Button("Annuller");
		// Sætter labels på layout
		layout.add(l1, 3, 3);
		layout.add(l2, 3, 6);
		layout.add(l3, 3, 9);
		layout.add(l4, 3, 12);
		layout.add(l5, 3, 15);
		layout.add(l6, 3, 18);
		// Sætter tekstfelter og datepicker på layout
		layout.add(værelse, 5, 3);
		layout.add(navn, 5, 6);
		layout.add(uddannelsessted, 5, 9);
		layout.add(uddannelsesretning, 5, 12);
		layout.add(uddStart, 5, 15);
		layout.add(uddSlut, 5, 18);
		// Sætter buttons på layout
		layout.add(gemButton, 3, 21);
		layout.add(annullerButton, 5, 21);
		layout.setPrefSize(500, 400);

		Scene scene = new Scene(layout);
		stage.setScene(scene);
		stage.show();
	}
	public void startStudiekontrol() {
		stage.setTitle("Påbegynd studiekontrol");
		
		GridPane layout = new GridPane();
		layout.setPrefSize(600, 300);
		
		//Valgmuligheder og labels for at påbegynde studiekontrol oprettes
		
		Label l1 = new Label("Vælg måned der påbegyndes\nstudiekontrol for:");
		Label l2 = new Label("Påmindelsesdato");
		Label l3 = new Label("Afleveringsfrist/Afslutningsdato");

		DatePicker påmindelsesdato = new DatePicker();
		DatePicker afleveringsfrist = new DatePicker();
		ComboBox<String> udløbsmåned = new ComboBox<String>();
		udløbsmåned.getItems().addAll("Januar", "Februar", "Marts", "April", "Maj","Juni","Juli","August","September","Oktober", "November","December");
		udløbsmåned.setPromptText("Vælg Måned");
		
		Button påbegyndButton = new Button("Påbegynd studiekontrol");
		påbegyndButton.setOnAction(e-> System.out.println("Jeg er ikke implementeret endnu"));
		Button annullerButton = new Button("Annuller");
		annullerButton.setOnAction(e-> stage.close());
		layout.add(l1, 3, 3);
		layout.add(l2, 6, 3);
		layout.add(l3, 9, 3);
		layout.add(udløbsmåned, 3, 5);
		layout.add(påmindelsesdato, 6, 5);
		layout.add(afleveringsfrist, 9, 5);
		
		layout.add(påbegyndButton, 6, 7);
		layout.add(annullerButton, 9, 7);


		
		Scene scene = new Scene(layout);
		stage.setScene(scene);
		stage.show();
		
	}
	public void modtagetStudiekontrol() {
	stage.setTitle("Studiekontrol for beboer:");
	
	GridPane layout = new GridPane();
	
	Label l1 = new Label("Værelse");
	Label l2 = new Label("Navn");
	Label l3 = new Label("Uddannelsessted");
	Label l4 = new Label("Uddannelsesretning");
	Label l5 = new Label("");
	Label l6 = new Label();
	Label l7 = new Label();

	
	
	
	Scene scene = new Scene(layout);
	stage.setScene(scene);
	stage.show();
	}
//	stage.setTitle("Påbegynd studiekontrol");
//	GridPane layout = new GridPane();
//	Scene scene = new Scene(layout);
//	stage.setScene(scene);
//	stage.show();
}
