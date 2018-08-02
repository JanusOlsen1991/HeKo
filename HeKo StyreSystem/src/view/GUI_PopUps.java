package view;

import java.time.LocalDate;
import java.util.ArrayList;

import controller.ExcelConnection;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.Beboer;
import model.Deadline;
import model.Studiekontrolstatus;
import model.Værelsesudlejning;

public class GUI_PopUps {
	// ExcelConnection ec = new ExcelConnection();
	private Stage stage = new Stage();

	public void createDeadline(ExcelConnection ec, TableView<Deadline> tView) {

		// stage.initModality(Modality.APPLICATION_MODAL);
		stage.setTitle("Opret Deadline");

		Label l1 = new Label("Hvem?");
		Label l2 = new Label("Hvad?");
		Label l3 = new Label("Hvornår?");
		DatePicker hvornår = new DatePicker();
		TextField hvad = new TextField();
		TextField hvem = new TextField();

		Button opretButton = new Button("Tilføj påmindelse");
		opretButton.setOnAction(e -> {
			Deadline d = new Deadline(hvem.getText(), hvad.getText(), hvornår.getValue(), null, ec);// ec. get
			ec.opretDeadlineIExcel(d);
			ec.getDeadlines().clear();
			ec.hentDeadlinesFraExcel();
			tView.getItems().add(d);
			stage.close();
		});
		Button annullerButton = new Button("Annuller");
		annullerButton.setOnAction(e -> stage.close());

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
		stage.showAndWait();
	}

	public void changeDeadline(Deadline deadline, ExcelConnection ec, TableView<Deadline> tView) {

		stage.setTitle("Rediger deadline");

		GridPane layout = new GridPane();

		Label l1 = new Label("Hvem?");
		Label l2 = new Label("Hvad?");
		Label l3 = new Label("Hvornår?");

		DatePicker hvornår = new DatePicker();
		hvornår.setValue(deadline.getHvornår());
		TextArea hvad = new TextArea();
		hvad.setText(deadline.getHvad());
		TextField hvem = new TextField();
		hvem.setText(deadline.getHvem());

		Button ændrButton = new Button("Gem ændringer");
		ændrButton.setOnAction(event -> {
			deadline.setHvad(hvad.getText());
			deadline.setHvornår(hvornår.getValue());
			deadline.setHvem(hvem.getText());
			ec.opretDeadlineIExcel(deadline);
			tView.refresh();
			stage.close();

		});
		Button annullerButton = new Button("Anuller");
		annullerButton.setOnAction(e -> {
			stage.close();
		});

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
		stage.showAndWait();
	}

	public void opretNyBeboeroplysninger(ExcelConnection ec, TableView<Beboer> tView1, TableView<Beboer> tView2,
			TableView<Beboer> tView3, TableView<Beboer> tView4, TableView<Beboer> tView5, TableView<Beboer> tView6) {
		stage.setTitle("Rediger beboeroplysninger");
		// stage.initModality(Modality.APPLICATION_MODAL);

		GridPane layout = new GridPane();

		Label l1 = new Label("Værelse:");
		Label l2 = new Label("Navn:");
		Label l3 = new Label("Indflytningsdato:");
		Label l4 = new Label("Telefonnummer:");
		Label l5 = new Label("Uddannelsessted");
		Label l6 = new Label("Uddannelsesretning");
		Label l7 = new Label("Uddannelse påbegyndt");
		Label l8 = new Label("Forventet uddannelsesafslutning");
		Label l9 = new Label("Lejeaftalens udløb:");
		Label l10 = new Label("Studiekontrol status:");

		TextField værelse = new TextField();
		TextField navn = new TextField();
		DatePicker indflytningsdato = new DatePicker();
		TextField telefonnummer = new TextField();
		TextField uddannelsessted = new TextField();
		TextField uddannelsesretning = new TextField();
		DatePicker uddStart = new DatePicker();
		DatePicker uddSlut = new DatePicker();
		DatePicker lejeaftalensUdløb = new DatePicker();
		ComboBox<String> studiekontrolStatus = new ComboBox<String>();
		studiekontrolStatus.getItems().addAll("Ikke i gang", "Modtaget, ikke godkendt", "Ikke Modtaget",
				"Sendt til boligselskab", "Godkendt");

		Button gemButton = new Button("Opret beboer");
		gemButton.setOnAction(e -> {
			Studiekontrolstatus status = (Studiekontrolstatus) ec
					.konverterStringTilEnum(studiekontrolStatus.getValue());
			Beboer b = new Beboer(navn.getText(), værelse.getText(), indflytningsdato.getValue(),
					lejeaftalensUdløb.getValue(), telefonnummer.getText(), status, uddannelsessted.getText(),
					uddannelsesretning.getText(), uddStart.getValue(), uddSlut.getValue());

			ec.opretBeboerIExcel(b);
			ec.getBeboere().clear();
			ec.hentBeboereFraExcel();
			
			tView1.getItems().add(b);
			tView1.refresh();
			
			char c = værelse.getText().charAt(0);
			switch(c) {
			case '2':
				tView2.getItems().add(b);
				tView2.refresh();
				break;
			case '3':
				tView3.getItems().add(b);
				tView3.refresh();
				break;
			case '4':
				tView4.getItems().add(b);
				tView4.refresh();
				break;
			case '5':
				tView5.getItems().add(b);
				tView5.refresh();
				break;
			case '6':
				tView6.getItems().add(b);
				tView6.refresh();
				break;
			default:
				System.out.println("Værelset findes ikke");
			}

			stage.close();
		});
		Button annullerButton = new Button("Annuller");
		annullerButton.setOnAction(e -> {
			stage.close();
		});

		layout.add(l1, 3, 3);
		layout.add(l2, 3, 6);
		layout.add(l3, 3, 9);
		layout.add(l4, 3, 12);
		layout.add(l5, 3, 15);
		layout.add(l6, 3, 18);
		layout.add(l7, 3, 21);
		layout.add(l8, 3, 24);
		layout.add(l9, 3, 27);
		layout.add(l10, 3, 30);

		// Sætter tekstfelter og datepicker på layout
		layout.add(værelse, 5, 3);
		layout.add(navn, 5, 6);
		layout.add(indflytningsdato, 5, 9);
		layout.add(telefonnummer, 5, 12);
		layout.add(uddannelsessted, 5, 15);
		layout.add(uddannelsesretning, 5, 18);
		layout.add(uddStart, 5, 21);
		layout.add(uddSlut, 5, 24);
		layout.add(lejeaftalensUdløb, 5, 27);
		layout.add(studiekontrolStatus, 5, 30);

		// Sætter buttons på layout
		layout.add(gemButton, 3, 33);
		layout.add(annullerButton, 5, 33);
		layout.setPrefSize(500, 700);

		Scene scene = new Scene(layout);
		stage.setScene(scene);
		stage.showAndWait();
	}

	public void redigerBeboeroplysninger(Beboer beboer, ExcelConnection ec, TableView<Beboer> tView) {
		stage.setTitle("Rediger beboeroplysninger");
		// stage.initModality(Modality.APPLICATION_MODAL);

		GridPane layout = new GridPane();

		Label l1 = new Label("Værelse:");
		Label l2 = new Label("Navn:");
		Label l3 = new Label("Indflytningsdato:");
		Label l4 = new Label("Telefonnummer:");
		Label l5 = new Label("Uddannelsessted");
		Label l6 = new Label("Uddannelsesretning");
		Label l7 = new Label("Uddannelse påbegyndt");
		Label l8 = new Label("Forventet uddannelsesafslutning");
		Label l9 = new Label("Lejeaftalens udløb:");

		TextField værelse = new TextField();
		TextField navn = new TextField();
		DatePicker indflytningsdato = new DatePicker();
		TextField telefonnummer = new TextField();
		TextField uddannelsessted = new TextField();
		TextField uddannelsesretning = new TextField();
		DatePicker uddStart = new DatePicker();
		DatePicker uddSlut = new DatePicker();
		DatePicker lejeaftalensUdløb = new DatePicker();
		// ComboBox<String> studiekontrolStatus = new ComboBox<String>();
		// studiekontrolStatus.getItems().addAll("Ikke i gang", "Modtaget, ikke
		// godkendt", "Ikke Modtaget", "Sendt til boligselskab", "Godkendt");

		værelse.setText(beboer.getVærelse());
		navn.setText(beboer.getNavn());
		indflytningsdato.setValue(beboer.getIndflytningsdato());
		telefonnummer.setText(beboer.getTelefonnummer());
		uddannelsessted.setText(beboer.getUddannelsessted());
		uddannelsesretning.setText(beboer.getUddannelsesretning());
		uddStart.setValue(beboer.getPåbegyndtDato());
		uddSlut.setValue(beboer.getForventetAfsluttetDato());
		lejeaftalensUdløb.setValue(beboer.getLejeaftalensUdløb());
		// studiekontrolStatus.setValue(ec.konverterEnumTilString((Studiekontrolstatus)
		// beboer.getStudiekontrolstatus()));

		Button gemButton = new Button("Gem ændringer");
		gemButton.setOnAction(e -> {
			// Studiekontrolstatus status = (Studiekontrolstatus)
			// ec.konverterStringTilEnum(studiekontrolStatus.getValue());
			beboer.setNavn(navn.getText());
			beboer.setVærelse(værelse.getText());
			beboer.setIndflytningsdato(indflytningsdato.getValue());
			beboer.setLejeaftalensUdløb(lejeaftalensUdløb.getValue());
			beboer.setTelefonnummer(telefonnummer.getText());
			// status,
			beboer.setUddannelsessted(uddannelsessted.getText());
			beboer.setUddannelsesretning(uddannelsesretning.getText());
			beboer.setPåbegyndtDato(uddStart.getValue());
			beboer.setForventetAfsluttetDato(uddSlut.getValue());

			ec.opretBeboerIExcel(beboer);
			ec.getBeboere().clear();
			ec.hentBeboereFraExcel();
			tView.refresh();
			stage.close();
		});
		Button annullerButton = new Button("Annuller");
		annullerButton.setOnAction(e -> {
			stage.close();
		});

		layout.add(l1, 3, 3);
		layout.add(l2, 3, 6);
		layout.add(l3, 3, 9);
		layout.add(l4, 3, 12);
		layout.add(l5, 3, 15);
		layout.add(l6, 3, 18);
		layout.add(l7, 3, 21);
		layout.add(l8, 3, 24);
		layout.add(l9, 3, 27);

		// Sætter tekstfelter og datepicker på layout
		layout.add(værelse, 5, 3);
		layout.add(navn, 5, 6);
		layout.add(indflytningsdato, 5, 9);
		layout.add(telefonnummer, 5, 12);
		layout.add(uddannelsessted, 5, 15);
		layout.add(uddannelsesretning, 5, 18);
		layout.add(uddStart, 5, 21);
		layout.add(uddSlut, 5, 24);
		layout.add(lejeaftalensUdløb, 5, 27);

		// Sætter buttons på layout
		layout.add(gemButton, 3, 30);
		layout.add(annullerButton, 5, 30);
		layout.setPrefSize(500, 700);

		Scene scene = new Scene(layout);
		stage.setScene(scene);
		stage.showAndWait();
	}

	public void startStudiekontrol() {
		stage.setTitle("Påbegynd studiekontrol");

		GridPane layout = new GridPane();
		layout.setPrefSize(600, 300);

		// Valgmuligheder og labels for at påbegynde studiekontrol oprettes

		Label l1 = new Label("Vælg måned der påbegyndes\nstudiekontrol for:");
		Label l2 = new Label("Påmindelsesdato");
		Label l3 = new Label("Afleveringsfrist/Afslutningsdato");

		DatePicker påmindelsesdato = new DatePicker();
		DatePicker afleveringsfrist = new DatePicker();
		ComboBox<String> udløbsmåned = new ComboBox<String>();
		udløbsmåned.getItems().addAll("Januar", "Februar", "Marts", "April", "Maj", "Juni", "Juli", "August",
				"September", "Oktober", "November", "December");
		udløbsmåned.setPromptText("Vælg Måned");

		Button påbegyndButton = new Button("Påbegynd studiekontrol");
		påbegyndButton.setOnAction(e -> System.out.println("Jeg er ikke implementeret endnu"));
		Button annullerButton = new Button("Annuller");
		annullerButton.setOnAction(e -> stage.close());
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

	/**
	 * popUp menu til at tjekke studiekontrol af.
	 */
	public void modtagetStudiekontrol() {
		stage.setTitle("Studiekontrol for beboer:");

		GridPane layout = new GridPane();

		Label l1 = new Label("Værelse");
		Label l2 = new Label("Navn");
		Label l3 = new Label("Uddannelsessted");
		Label l4 = new Label("Uddannelsesretning");
		Label l5 = new Label("uddannelse påbegyndt:");
		Label l6 = new Label("Forventet studieafslutning:");
		Label l7 = new Label("Status på studiedokumentation");

		TextField værelse = new TextField();
		TextField navn = new TextField();
		TextField uddannelsessted = new TextField();
		TextField uddannelsesretning = new TextField();
		DatePicker uddStart = new DatePicker();
		DatePicker uddSlut = new DatePicker();
		ComboBox<String> kontrolstatus = new ComboBox<String>();
		kontrolstatus.getItems().addAll("Godkendt", "Modtaget. Ikke godkendt", "Ikke modtaget");
		kontrolstatus.setPromptText("Status...");

		Button gemButton = new Button("Gem ændringer");
		Button annulerButton = new Button("Annuller");

		layout.add(l1, 3, 3);
		layout.add(l2, 3, 5);
		layout.add(l3, 3, 7);
		layout.add(l4, 3, 9);
		layout.add(l5, 3, 11);
		layout.add(l6, 3, 13);
		layout.add(l7, 3, 15);

		layout.add(værelse, 5, 3);
		layout.add(navn, 5, 5);
		layout.add(uddannelsessted, 5, 7);
		layout.add(uddannelsesretning, 5, 9);
		layout.add(uddStart, 5, 11);
		layout.add(uddSlut, 5, 13);
		layout.add(kontrolstatus, 5, 15);

		layout.add(gemButton, 4, 17);
		layout.add(annulerButton, 6, 17);

		Scene scene = new Scene(layout);
		stage.setScene(scene);
		stage.show();
	}

	public void afslutStudiekontrol(String måned, ArrayList arrayList) {
		stage.setTitle("Afslut studiekontrol for " + måned);
		GridPane layout = new GridPane();

		Label l1 = new Label("Er du sikker på du vil afslutte studiekontrollen for " + måned
				+ "\nog sende de herunder listede beboeres oplysninger videre til boligselskabet?");
		Button jaButton = new Button("Ja");
		Button nejButton = new Button("Nej");

		TableColumn værelse = new TableColumn();
		TableColumn navn = new TableColumn();

		TableView<Beboer> tView = new TableView();
		tView.getColumns().addAll(værelse, navn);

		layout.add(l1, 3, 3, 5, 1);

		layout.add(tView, 3, 4, 3, 1);
		layout.add(jaButton, 4, 6);
		layout.add(nejButton, 6, 6);

		Scene scene = new Scene(layout);
		stage.setScene(scene);
		stage.show();
	}

	public void værelsesUdlejning(Værelsesudlejning clickedRow, ExcelConnection ec, TableView<Værelsesudlejning> tW1) {
		// TODO Auto-generated method stub

	}

	// stage.setTitle("Påbegynd studiekontrol");
	// GridPane layout = new GridPane();
	// Scene scene = new Scene(layout);
	// stage.setScene(scene);
	// stage.show();
}
