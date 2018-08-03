package view;

import java.time.LocalDate;
import java.util.ArrayList;

import controller.ExcelConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Beboer;
import model.Deadline;
import model.Dispensation;
import model.Uddannelse;
import model.Værelsesudlejning;

public class GUI {
	private Scene scene;
	ExcelConnection ec = new ExcelConnection();
	GUI_PopUps popUp = new GUI_PopUps();
	GUI_PopUps_Deadlines popUpDead = new GUI_PopUps_Deadlines();

	public GUI(ExcelConnection ec) {
		// this.ec = ec;
	}

	/**
	 * Metoden anvendes til at oprette "hovedmenuen" for programmet.
	 * 
	 * @param primaryStage
	 *            : is the stage given to set up the GUI
	 */
	// @Override

	public void hovedMenu(Stage primaryStage) throws Exception {
		BorderPane borderP = new BorderPane();
		primaryStage.setTitle("Herlevkollegiets Indstillingsudvalg");
		VBox venstreLayout = new VBox();
		GridPane højreLayout = new GridPane();
		borderP.setLeft(venstreLayout);
		borderP.setCenter(højreLayout);

		
		TableView<Deadline> tView = new TableView<Deadline>();
		// Buttons til venstre side af menuen
		Button beboerlisteButton = new Button("Beboerliste");
		beboerlisteButton.setOnAction(e -> beboerlisteMenu(primaryStage));
		Button studieKontrolButton = new Button("Studiekontrol");
		studieKontrolButton.setOnAction(e -> studieKontrolMenu(primaryStage));
		Button dispensationsButton = new Button("Dispensation");
		dispensationsButton.setOnAction(e -> dispensationsMenu(primaryStage, tView));
		Button fremlejeButton = new Button("Fremleje");
		fremlejeButton.setOnAction(e -> fremlejeMenu(primaryStage));
		Button værelsesudlejningsButton = new Button("Værelsesudlejning");
		værelsesudlejningsButton.setOnAction(e -> værelsesUdlejning(primaryStage));

		// Tilføjer buttons til venstre side.
		venstreLayout.getChildren().addAll(beboerlisteButton, studieKontrolButton, dispensationsButton, fremlejeButton,
				værelsesudlejningsButton);

		// Buttons og "Påmindelser/deadlines" til højre side af menuen

		TableColumn<Deadline, LocalDate> hvornårColumn = new TableColumn<Deadline, LocalDate>("Dato");
		hvornårColumn.setCellValueFactory(new PropertyValueFactory<>("hvornår")); // ændr i format så dato bliver:
																					// dd/MM/YYYY

		TableColumn<Deadline, String> hvadColumn = new TableColumn<Deadline, String>("Hvad:");
		hvadColumn.setCellValueFactory(new PropertyValueFactory<>("hvad"));
		TableColumn<Deadline, String> hvemColumn = new TableColumn("Hvem:");
		hvemColumn.setCellValueFactory(new PropertyValueFactory<>("hvem"));


		tView.setItems(getDeadlines());

		tView.getColumns().addAll(hvornårColumn, hvadColumn, hvemColumn);

		// Herunder oprettes en metode til at håndtere dobbeltklik og hente objekter
		// (hel række)
		tView.setRowFactory(tv -> {
			TableRow<Deadline> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {

					Deadline clickedRow = row.getItem();
					popUpDead.changeDeadline(clickedRow, ec, tView);
					tView.refresh();
				}
			});
			return row;
		});

		Button tilføjButton = new Button("Tilføj påmindelse");
		tilføjButton.setOnAction(event -> popUpDead.createDeadline(ec, tView));

		Button fjernButton = new Button("Fjern påmindelse");
		fjernButton.setOnAction(event -> {
			ObservableList<Deadline> deadlineValgt, alleDeadlines;
			alleDeadlines = tView.getItems();
			deadlineValgt = tView.getSelectionModel().getSelectedItems();
			Deadline d = tView.getSelectionModel().getSelectedItem();
			d.setKlaret(true);
			ec.opretDeadlineIExcel(d);
			deadlineValgt.forEach(alleDeadlines::remove);

		});

		// Tilføjer til højre side af menuen
		højreLayout.add(tView, 2, 3, 3, 6);
		højreLayout.add(tilføjButton, 2, 10);
		højreLayout.add(fjernButton, 4, 10);

		scene = new Scene(borderP, 900, 700);
		primaryStage.setScene(scene);
		primaryStage.show();

	}

	private ObservableList<Deadline> getDeadlines() {
		// Der skal lægges ind og testes for 'isKlaret'
		ArrayList<Deadline> alleDeadlines = ec.getDeadlines();
		ArrayList<Deadline> temp = new ArrayList<Deadline>();

		for (Deadline d : alleDeadlines) {
			if (d.isKlaret() == false)
				temp.add(d);
		}

		ObservableList<Deadline> deadlines = FXCollections.observableArrayList(temp);
		return deadlines;
	}

	/**
	 * 
	 * @param primaryStage
	 *            : main stage
	 */
	private void værelsesUdlejning(Stage primaryStage) {
		BorderPane borderP = new BorderPane();
		// venstre side
		Button tilbageButton = new Button("Tilbage");
		tilbageButton.setOnAction(e -> {
			try {
				hovedMenu(primaryStage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		VBox vb = new VBox(tilbageButton);
		borderP.setLeft(vb);

		// Midten
		TabPane tP = new TabPane();
		borderP.setCenter(tP);

		Tab tab1 = new Tab("Ledige værelser");
		tab1.setClosable(false);
		Tab tab2 = new Tab("Udlejede værelser");
		tab2.setClosable(false);

		tP.getTabs().addAll(tab1, tab2);

		TableView<Værelsesudlejning> tView1 = new TableView<Værelsesudlejning>();
		TableView<Værelsesudlejning> tView2 = new TableView<Værelsesudlejning>();

		// Herunder oprettes en metode til at håndtere dobbeltklik
		tView1.setRowFactory(tv -> {
			TableRow<Værelsesudlejning> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {

					Værelsesudlejning clickedRow = row.getItem();
					popUp.værelsesUdlejning(clickedRow, ec, tView1);
					tView1.refresh();
				}
			});
			return row;
		});

		TableColumn<Værelsesudlejning, String> værelseColumn = new TableColumn<Værelsesudlejning, String>("Værelse");
		værelseColumn.setCellValueFactory(new PropertyValueFactory<>("værelse"));

		TableColumn<Værelsesudlejning, String> navnColumn = new TableColumn<Værelsesudlejning, String>("Navn");
		navnColumn.setCellValueFactory(new PropertyValueFactory<>("navn"));

		TableColumn<Værelsesudlejning, LocalDate> indflytningColumn = new TableColumn<Værelsesudlejning, LocalDate>(
				"Overtagelsesdato");
		indflytningColumn.setCellValueFactory(new PropertyValueFactory<>("indflytningsdato"));
		TableColumn<Værelsesudlejning, String> behandletDatoColumn = new TableColumn<Værelsesudlejning, String>(
				"Behandlingsdato");
		behandletDatoColumn.setCellValueFactory(new PropertyValueFactory<>("behandlingsdato"));
		TableColumn<Værelsesudlejning, String> behandlerInitialerColumn = new TableColumn<Værelsesudlejning, String>(
				"Behandler Initialer");
		behandlerInitialerColumn.setCellValueFactory(new PropertyValueFactory<>("behandlerInitialer"));
		tView1.setItems(getVærelsesUdlejning());

		tView1.getColumns().addAll(indflytningColumn, værelseColumn, navnColumn, behandletDatoColumn,
				behandlerInitialerColumn);
		tView2.getColumns().addAll(indflytningColumn, værelseColumn, navnColumn, behandletDatoColumn,
				behandlerInitialerColumn);

		tab1.setContent(tView1);
		tab2.setContent(tView2);

		scene = new Scene(borderP, 900, 700);
		primaryStage.setScene(scene);
	}

	private ObservableList<Værelsesudlejning> getVærelsesUdlejning() {
		// TODO Auto-generated method stub
		return null;
	}

	private void dispensationsMenu(Stage primaryStage, TableView<Deadline> tViewHMenu) {

		BorderPane borderP = new BorderPane();

		// Venstre side af menuen
		Button tilbageButton = new Button("Tilbage");
		tilbageButton.setOnAction(e -> {
			try {
				hovedMenu(primaryStage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		VBox vb = new VBox(tilbageButton);
		borderP.setLeft(vb);

		TabPane tP = new TabPane();
		borderP.setCenter(tP);

		TableView<Dispensation> tView = new TableView<Dispensation>();

		TableColumn<Dispensation, String> værelseColumn = new TableColumn<Dispensation, String>("Værelse");
		værelseColumn.setCellValueFactory(new PropertyValueFactory<>("beboerVærelse"));
		TableColumn<Dispensation, String> navnColumn = new TableColumn<Dispensation, String>("Navn");
		navnColumn.setCellValueFactory(new PropertyValueFactory<>("beboerNavn"));
		TableColumn<Dispensation, LocalDate> startdatoColumn = new TableColumn<Dispensation, LocalDate>("Start dato");
		startdatoColumn.setCellValueFactory(new PropertyValueFactory<>("startDato"));
		TableColumn<Dispensation, LocalDate> slutdatoColumn = new TableColumn<Dispensation, LocalDate>("Slut dato");
		slutdatoColumn.setCellValueFactory(new PropertyValueFactory<>("slutDato"));

		tView.getColumns().addAll(værelseColumn, navnColumn, startdatoColumn, slutdatoColumn);
		tView.getItems().addAll(getDispensationer());
		
		tView.setRowFactory(tv -> {
			TableRow<Dispensation> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (! row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
					
					Dispensation clickedRow = row.getItem();
					popUp.redigerDispensation(clickedRow, ec, tView, tViewHMenu);
				}
			});
			return row;
		});
		tView.setRowFactory(tv -> {
			TableRow<Dispensation> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
					
					popUp.opretDispensation(ec, tView, tViewHMenu);//, tView
				}
			});
			return row;
		});

		// Start knappen
		Button startDispButton = new Button("Kom i gang");
		startDispButton.setOnAction(e -> {
			popUp.opretDispensation(ec, tView, tViewHMenu);//, tView
		});

		tView.setPlaceholder(startDispButton);
		
		

		Tab tab1 = new Tab("Aktive dispensationer");
		tab1.setClosable(false);
		tab1.setContent(tView);
		tP.getTabs().add(tab1);

		scene = new Scene(borderP, 900, 700);
		primaryStage.setScene(scene);
	}

	private ObservableList<Dispensation> getDispensationer() {
		// Der skal lægges ind og testes for 'isKlaret'
		ArrayList<Dispensation> alleDisps = ec.getDispensationer();
		ArrayList<Dispensation> temp = new ArrayList<Dispensation>();
		
		for (Dispensation d : alleDisps) {
			if (d.isiGang() == true)
				temp.add(d);
		}

		ObservableList<Dispensation> dispensationer = FXCollections.observableArrayList(temp);
		return dispensationer;

	}

	private void studieKontrolMenu(Stage primaryStage) {
		// overordnet layout genereres
		BorderPane borderP = new BorderPane();
		// venstre side
		Button tilbageButton = new Button("Tilbage");
		tilbageButton.setOnAction(e -> {
			try {
				hovedMenu(primaryStage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		VBox vb = new VBox(tilbageButton);
		borderP.setLeft(vb);

		// Midten
		TabPane tP = new TabPane();
		borderP.setCenter(tP);

		// Tabs herunder skal oprettes KUN "når de er i gang".

		Tab tab1 = new Tab("Alle igangværende studiekontroller");
		tab1.setClosable(false);
		Tab tab2 = new Tab("Januar");
		tab2.setClosable(false);
		Tab tab3 = new Tab("Februar");
		tab3.setClosable(false);
		Tab tab4 = new Tab("Marts");
		tab4.setClosable(false);
		Tab tab5 = new Tab("April");
		tab5.setClosable(false);
		Tab tab6 = new Tab("Maj");
		tab6.setClosable(false);
		// ...

		TableView<Beboer> tView1 = new TableView<Beboer>();
		TableView<Beboer> tView2 = new TableView<Beboer>();
		TableView<Beboer> tView3 = new TableView<Beboer>();
		TableView<Beboer> tView4 = new TableView<Beboer>();
		TableView<Beboer> tView5 = new TableView<Beboer>();
		TableView<Beboer> tView6 = new TableView<Beboer>();

		// kolloner til Tableviews
		TableColumn værelseColumn = new TableColumn("Værelse");
		TableColumn navnColumn = new TableColumn("Navn");
		// TableColumn indflytningColumn = new TableColumn("indflytningsdato");
		TableColumn uddannelseColumn = new TableColumn("Uddannelse");
		TableColumn uddStedColumn = new TableColumn("Uddannelsessted");
		TableColumn påbegyndtUddColumn = new TableColumn("Uddannelse påbegyndt");
		TableColumn afslutningUddColumn = new TableColumn("Uddannelse forventes afsluttet");
		TableColumn lejeaftalensUdløbColumn = new TableColumn("Lejeaftalens udløb");

		// TableViews oprettes med kollonnerne
		tView1.getColumns().addAll(værelseColumn, navnColumn, uddannelseColumn, påbegyndtUddColumn, afslutningUddColumn,
				lejeaftalensUdløbColumn);
		tView2.getColumns().addAll(værelseColumn, navnColumn, uddannelseColumn, påbegyndtUddColumn, afslutningUddColumn,
				lejeaftalensUdløbColumn);
		tView3.getColumns().addAll(værelseColumn, navnColumn, uddannelseColumn, påbegyndtUddColumn, afslutningUddColumn,
				lejeaftalensUdløbColumn);
		tView4.getColumns().addAll(værelseColumn, navnColumn, uddannelseColumn, påbegyndtUddColumn, afslutningUddColumn,
				lejeaftalensUdløbColumn);
		tView5.getColumns().addAll(værelseColumn, navnColumn, uddannelseColumn, påbegyndtUddColumn, afslutningUddColumn,
				lejeaftalensUdløbColumn);
		tView6.getColumns().addAll(værelseColumn, navnColumn, uddannelseColumn, påbegyndtUddColumn, afslutningUddColumn,
				lejeaftalensUdløbColumn);

		// Første tab: Alle beboere

		tab1.setContent(tView1);
		tab2.setContent(tView2);
		tab3.setContent(tView3);
		tab4.setContent(tView4);
		tab5.setContent(tView5);
		tab6.setContent(tView6);

		tP.getTabs().addAll(tab1, tab2, tab3, tab4, tab5, tab6);
		scene = new Scene(borderP, 900, 700);
		primaryStage.setScene(scene);

	}

	@SuppressWarnings("unchecked")
	public void beboerlisteMenu(Stage primaryStage) {

		// overordnet layout genereres
		BorderPane borderP = new BorderPane();
		// venstre side
		Button tilbageButton = new Button("Tilbage");
		tilbageButton.setOnAction(e -> {
			try {
				hovedMenu(primaryStage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		VBox vb = new VBox(tilbageButton);
		borderP.setLeft(vb);

		// Midten
		TabPane tP = new TabPane();
		borderP.setCenter(tP);
		Tab tab1 = new Tab("Alle beboere");
		tab1.setClosable(false);
		Tab tab2 = new Tab("2. sal");
		tab2.setClosable(false);
		Tab tab3 = new Tab("3. sal");
		tab3.setClosable(false);
		Tab tab4 = new Tab("4. sal");
		tab4.setClosable(false);
		Tab tab5 = new Tab("5. sal");
		tab5.setClosable(false);
		Tab tab6 = new Tab("6. sal");
		tab6.setClosable(false);

		TableView<Beboer> tView1 = new TableView<Beboer>();
		TableView<Beboer> tView2 = new TableView<Beboer>();
		TableView<Beboer> tView3 = new TableView<Beboer>();
		TableView<Beboer> tView4 = new TableView<Beboer>();
		TableView<Beboer> tView5 = new TableView<Beboer>();
		TableView<Beboer> tView6 = new TableView<Beboer>();

		// Herunder oprettes en metode til at håndtere dobbeltklik på eksisterende
		// beboere

		tView1.setRowFactory(tv -> {
			TableRow<Beboer> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {

					Beboer clickedRow = row.getItem();
					popUp.redigerBeboeroplysninger(clickedRow, ec, tView1);

				}
			});
			return row;
		});
		tView1.setRowFactory(tv -> {
			TableRow<Beboer> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
					// dobbeltklik på tom række virker ikke?
					Beboer clickedRow = row.getItem();
					popUp.opretNyBeboeroplysninger(ec, tView1, tView2, tView3, tView4, tView5, tView6);
				}
			});
			return row;
		});

		// Start knappen
		Button bb1 = new Button("Kom i gang");
		bb1.setOnAction(e -> {
			popUp.opretNyBeboeroplysninger(ec, tView1, tView2, tView3, tView4, tView5, tView6);
		});

		tView1.setPlaceholder(bb1);

		tView2.setRowFactory(tv -> {
			TableRow<Beboer> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {

					Beboer clickedRow = row.getItem();
					popUp.redigerBeboeroplysninger(clickedRow, ec, tView2);
				}
			});
			return row;
		});
		tView2.setRowFactory(tv -> {
			TableRow<Beboer> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
					// dobbeltklik på tom række virker ikke?
					Beboer clickedRow = row.getItem();
					popUp.opretNyBeboeroplysninger(ec, tView1, tView2, tView3, tView4, tView5, tView6);
				}
			});
			return row;
		});

		tView3.setRowFactory(tv -> {
			TableRow<Beboer> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {

					Beboer clickedRow = row.getItem();
					popUp.redigerBeboeroplysninger(clickedRow, ec, tView3);

				}
			});
			return row;
		});
		tView3.setRowFactory(tv -> {
			TableRow<Beboer> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
					// dobbeltklik på tom række virker ikke?
					Beboer clickedRow = row.getItem();
					popUp.opretNyBeboeroplysninger(ec, tView1, tView2, tView3, tView4, tView5, tView6);
				}
			});
			return row;
		});

		tView4.setRowFactory(tv -> {
			TableRow<Beboer> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {

					Beboer clickedRow = row.getItem();
					popUp.redigerBeboeroplysninger(clickedRow, ec, tView4);
				}
			});
			return row;
		});
		tView4.setRowFactory(tv -> {
			TableRow<Beboer> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
					// dobbeltklik på tom række virker ikke?
					Beboer clickedRow = row.getItem();
					popUp.opretNyBeboeroplysninger(ec, tView1, tView2, tView3, tView4, tView5, tView6);
				}
			});
			return row;
		});

		tView5.setRowFactory(tv -> {
			TableRow<Beboer> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {

					Beboer clickedRow = row.getItem();
					popUp.redigerBeboeroplysninger(clickedRow, ec, tView5);
				}
			});
			return row;
		});
		tView5.setRowFactory(tv -> {
			TableRow<Beboer> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
					// dobbeltklik på tom række virker ikke?
					Beboer clickedRow = row.getItem();
					popUp.opretNyBeboeroplysninger(ec, tView1, tView2, tView3, tView4, tView5, tView6);
				}
			});
			return row;
		});

		tView6.setRowFactory(tv -> {
			TableRow<Beboer> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (!row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {

					Beboer clickedRow = row.getItem();
					popUp.redigerBeboeroplysninger(clickedRow, ec, tView6);
				}
			});
			return row;
		});
		tView6.setRowFactory(tv -> {
			TableRow<Beboer> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (row.isEmpty() && event.getButton() == MouseButton.PRIMARY && event.getClickCount() == 2) {
					// dobbeltklik på tom række virker ikke?
					Beboer clickedRow = row.getItem();
					popUp.opretNyBeboeroplysninger(ec, tView1, tView2, tView3, tView4, tView5, tView6);
				}
			});
			return row;
		});
		// kollonner til Tableviews //ALLE
		TableColumn<Beboer, String> værelseColumn = new TableColumn<Beboer, String>("Værelse");
		værelseColumn.setCellValueFactory(new PropertyValueFactory<>("værelse"));
		TableColumn<Beboer, String> navnColumn = new TableColumn<Beboer, String>("Navn");
		navnColumn.setCellValueFactory(new PropertyValueFactory<>("navn"));
		TableColumn<Beboer, LocalDate> indflytningColumn = new TableColumn<Beboer, LocalDate>("indflytningsdato");
		indflytningColumn.setCellValueFactory(new PropertyValueFactory<>("indflytningsdato"));
		TableColumn<Beboer, String> telefonColumn = new TableColumn<Beboer, String>("Telefonnummer");
		telefonColumn.setCellValueFactory(new PropertyValueFactory<>("telefonnummer"));
		TableColumn<Beboer, String> uddStedColumn = new TableColumn<Beboer, String>("Uddannelsessted");
		uddStedColumn.setCellValueFactory(new PropertyValueFactory<>("uddannelsessted"));
		TableColumn<Beboer, String> uddannelseColumn = new TableColumn<Beboer, String>("Uddannelsesretning");
		uddannelseColumn.setCellValueFactory(new PropertyValueFactory<>("uddannelsesretning"));
		TableColumn<Beboer, LocalDate> påbegyndtUddColumn = new TableColumn<Beboer, LocalDate>("Uddannelse påbegyndt");
		påbegyndtUddColumn.setCellValueFactory(new PropertyValueFactory<>("påbegyndtDato"));
		TableColumn<Beboer, LocalDate> afslutningUddColumn = new TableColumn<Beboer, LocalDate>(
				"Uddannelse forventes afsluttet");
		afslutningUddColumn.setCellValueFactory(new PropertyValueFactory<>("forventetAfsluttetDato"));
		TableColumn<Beboer, LocalDate> lejeaftalensUdløbColumn = new TableColumn<Beboer, LocalDate>(
				"Lejeaftalens udløb"); // SLET? - Måske for forvirrende,
		påbegyndtUddColumn.setCellValueFactory(new PropertyValueFactory<>("lejeaftalensUdløb"));

		// 2. sal
		TableColumn<Beboer, String> værelseColumn2 = new TableColumn<Beboer, String>("Værelse");
		værelseColumn2.setCellValueFactory(new PropertyValueFactory<>("værelse"));
		TableColumn<Beboer, String> navnColumn2 = new TableColumn<Beboer, String>("Navn");
		navnColumn2.setCellValueFactory(new PropertyValueFactory<>("navn"));
		TableColumn<Beboer, LocalDate> indflytningColumn2 = new TableColumn<Beboer, LocalDate>("indflytningsdato");
		indflytningColumn2.setCellValueFactory(new PropertyValueFactory<>("indflytningsdato"));
		TableColumn<Beboer, String> telefonColumn2 = new TableColumn<Beboer, String>("Telefonnummer");
		telefonColumn2.setCellValueFactory(new PropertyValueFactory<>("telefonnummer"));
		TableColumn<Beboer, String> uddStedColumn2 = new TableColumn<Beboer, String>("Uddannelsessted");
		uddStedColumn2.setCellValueFactory(new PropertyValueFactory<>("uddannelsessted"));
		TableColumn<Beboer, String> uddannelseColumn2 = new TableColumn<Beboer, String>("Uddannelsesretning");
		uddannelseColumn2.setCellValueFactory(new PropertyValueFactory<>("uddannelsesretning"));
		TableColumn<Beboer, LocalDate> påbegyndtUddColumn2 = new TableColumn<Beboer, LocalDate>("Uddannelse påbegyndt");
		påbegyndtUddColumn2.setCellValueFactory(new PropertyValueFactory<>("påbegyndtDato"));
		TableColumn<Beboer, LocalDate> afslutningUddColumn2 = new TableColumn<Beboer, LocalDate>(
				"Uddannelse forventes afsluttet");
		afslutningUddColumn2.setCellValueFactory(new PropertyValueFactory<>("forventetAfsluttetDato"));
		TableColumn<Beboer, LocalDate> lejeaftalensUdløbColumn2 = new TableColumn<Beboer, LocalDate>(
				"Lejeaftalens udløb"); // SLET? - Måske for forvirrende,
		påbegyndtUddColumn2.setCellValueFactory(new PropertyValueFactory<>("lejeaftalensUdløb"));

		// 3. sal
		TableColumn<Beboer, String> værelseColumn3 = new TableColumn<Beboer, String>("Værelse");
		værelseColumn3.setCellValueFactory(new PropertyValueFactory<>("værelse"));
		TableColumn<Beboer, String> navnColumn3 = new TableColumn<Beboer, String>("Navn");
		navnColumn3.setCellValueFactory(new PropertyValueFactory<>("navn"));
		TableColumn<Beboer, LocalDate> indflytningColumn3 = new TableColumn<Beboer, LocalDate>("indflytningsdato");
		indflytningColumn3.setCellValueFactory(new PropertyValueFactory<>("indflytningsdato"));
		TableColumn<Beboer, String> telefonColumn3 = new TableColumn<Beboer, String>("Telefonnummer");
		telefonColumn3.setCellValueFactory(new PropertyValueFactory<>("telefonnummer"));
		TableColumn<Beboer, String> uddStedColumn3 = new TableColumn<Beboer, String>("Uddannelsessted");
		uddStedColumn3.setCellValueFactory(new PropertyValueFactory<>("uddannelsessted"));
		TableColumn<Beboer, String> uddannelseColumn3 = new TableColumn<Beboer, String>("Uddannelsesretning");
		uddannelseColumn3.setCellValueFactory(new PropertyValueFactory<>("uddannelsesretning"));
		TableColumn<Beboer, LocalDate> påbegyndtUddColumn3 = new TableColumn<Beboer, LocalDate>("Uddannelse påbegyndt");
		påbegyndtUddColumn3.setCellValueFactory(new PropertyValueFactory<>("påbegyndtDato"));
		TableColumn<Beboer, LocalDate> afslutningUddColumn3 = new TableColumn<Beboer, LocalDate>(
				"Uddannelse forventes afsluttet");
		afslutningUddColumn3.setCellValueFactory(new PropertyValueFactory<>("forventetAfsluttetDato"));
		TableColumn<Beboer, LocalDate> lejeaftalensUdløbColumn3 = new TableColumn<Beboer, LocalDate>(
				"Lejeaftalens udløb"); // SLET? - Måske for forvirrende,
		påbegyndtUddColumn3.setCellValueFactory(new PropertyValueFactory<>("lejeaftalensUdløb"));

		// 4. sal
		TableColumn<Beboer, String> værelseColumn4 = new TableColumn<Beboer, String>("Værelse");
		værelseColumn4.setCellValueFactory(new PropertyValueFactory<>("værelse"));
		TableColumn<Beboer, String> navnColumn4 = new TableColumn<Beboer, String>("Navn");
		navnColumn4.setCellValueFactory(new PropertyValueFactory<>("navn"));
		TableColumn<Beboer, LocalDate> indflytningColumn4 = new TableColumn<Beboer, LocalDate>("indflytningsdato");
		indflytningColumn4.setCellValueFactory(new PropertyValueFactory<>("indflytningsdato"));
		TableColumn<Beboer, String> telefonColumn4 = new TableColumn<Beboer, String>("Telefonnummer");
		telefonColumn4.setCellValueFactory(new PropertyValueFactory<>("telefonnummer"));
		TableColumn<Beboer, String> uddStedColumn4 = new TableColumn<Beboer, String>("Uddannelsessted");
		uddStedColumn4.setCellValueFactory(new PropertyValueFactory<>("uddannelsessted"));
		TableColumn<Beboer, String> uddannelseColumn4 = new TableColumn<Beboer, String>("Uddannelsesretning");
		uddannelseColumn4.setCellValueFactory(new PropertyValueFactory<>("uddannelsesretning"));
		TableColumn<Beboer, LocalDate> påbegyndtUddColumn4 = new TableColumn<Beboer, LocalDate>("Uddannelse påbegyndt");
		påbegyndtUddColumn4.setCellValueFactory(new PropertyValueFactory<>("påbegyndtDato"));
		TableColumn<Beboer, LocalDate> afslutningUddColumn4 = new TableColumn<Beboer, LocalDate>(
				"Uddannelse forventes afsluttet");
		afslutningUddColumn4.setCellValueFactory(new PropertyValueFactory<>("forventetAfsluttetDato"));
		TableColumn<Beboer, LocalDate> lejeaftalensUdløbColumn4 = new TableColumn<Beboer, LocalDate>(
				"Lejeaftalens udløb"); // SLET? - Måske for forvirrende,
		påbegyndtUddColumn4.setCellValueFactory(new PropertyValueFactory<>("lejeaftalensUdløb"));

		// 5. sal
		TableColumn<Beboer, String> værelseColumn5 = new TableColumn<Beboer, String>("Værelse");
		værelseColumn5.setCellValueFactory(new PropertyValueFactory<>("værelse"));
		TableColumn<Beboer, String> navnColumn5 = new TableColumn<Beboer, String>("Navn");
		navnColumn5.setCellValueFactory(new PropertyValueFactory<>("navn"));
		TableColumn<Beboer, LocalDate> indflytningColumn5 = new TableColumn<Beboer, LocalDate>("indflytningsdato");
		indflytningColumn5.setCellValueFactory(new PropertyValueFactory<>("indflytningsdato"));
		TableColumn<Beboer, String> telefonColumn5 = new TableColumn<Beboer, String>("Telefonnummer");
		telefonColumn5.setCellValueFactory(new PropertyValueFactory<>("telefonnummer"));
		TableColumn<Beboer, String> uddStedColumn5 = new TableColumn<Beboer, String>("Uddannelsessted");
		uddStedColumn5.setCellValueFactory(new PropertyValueFactory<>("uddannelsessted"));
		TableColumn<Beboer, String> uddannelseColumn5 = new TableColumn<Beboer, String>("Uddannelsesretning");
		uddannelseColumn5.setCellValueFactory(new PropertyValueFactory<>("uddannelsesretning"));
		TableColumn<Beboer, LocalDate> påbegyndtUddColumn5 = new TableColumn<Beboer, LocalDate>("Uddannelse påbegyndt");
		påbegyndtUddColumn5.setCellValueFactory(new PropertyValueFactory<>("påbegyndtDato"));
		TableColumn<Beboer, LocalDate> afslutningUddColumn5 = new TableColumn<Beboer, LocalDate>(
				"Uddannelse forventes afsluttet");
		afslutningUddColumn5.setCellValueFactory(new PropertyValueFactory<>("forventetAfsluttetDato"));
		TableColumn<Beboer, LocalDate> lejeaftalensUdløbColumn5 = new TableColumn<Beboer, LocalDate>(
				"Lejeaftalens udløb");
		påbegyndtUddColumn5.setCellValueFactory(new PropertyValueFactory<>("lejeaftalensUdløb"));

		TableColumn<Beboer, String> værelseColumn6 = new TableColumn<Beboer, String>("Værelse");
		værelseColumn6.setCellValueFactory(new PropertyValueFactory<>("værelse"));
		TableColumn<Beboer, String> navnColumn6 = new TableColumn<Beboer, String>("Navn");
		navnColumn6.setCellValueFactory(new PropertyValueFactory<>("navn"));
		TableColumn<Beboer, LocalDate> indflytningColumn6 = new TableColumn<Beboer, LocalDate>("indflytningsdato");
		indflytningColumn6.setCellValueFactory(new PropertyValueFactory<>("indflytningsdato"));
		TableColumn<Beboer, String> telefonColumn6 = new TableColumn<Beboer, String>("Telefonnummer");
		telefonColumn6.setCellValueFactory(new PropertyValueFactory<>("telefonnummer"));
		TableColumn<Beboer, String> uddStedColumn6 = new TableColumn<Beboer, String>("Uddannelsessted");
		uddStedColumn6.setCellValueFactory(new PropertyValueFactory<>("uddannelsessted"));
		TableColumn<Beboer, String> uddannelseColumn6 = new TableColumn<Beboer, String>("Uddannelsesretning");
		uddannelseColumn6.setCellValueFactory(new PropertyValueFactory<>("uddannelsesretning"));
		TableColumn<Beboer, LocalDate> påbegyndtUddColumn6 = new TableColumn<Beboer, LocalDate>("Uddannelse påbegyndt");
		påbegyndtUddColumn6.setCellValueFactory(new PropertyValueFactory<>("påbegyndtDato"));
		TableColumn<Beboer, LocalDate> afslutningUddColumn6 = new TableColumn<Beboer, LocalDate>(
				"Uddannelse forventes afsluttet");
		afslutningUddColumn6.setCellValueFactory(new PropertyValueFactory<>("forventetAfsluttetDato"));
		TableColumn<Beboer, LocalDate> lejeaftalensUdløbColumn6 = new TableColumn<Beboer, LocalDate>(
				"Lejeaftalens udløb"); // SLET? - Måske for forvirrende,
		påbegyndtUddColumn6.setCellValueFactory(new PropertyValueFactory<>("lejeaftalensUdløb"));
		// TableViews oprettes med kollonnerne

		tView1.setItems(getBeboere('1'));
		tView2.setItems(getBeboere('2'));
		tView3.setItems(getBeboere('3'));
		tView4.setItems(getBeboere('4'));
		tView5.setItems(getBeboere('5'));
		tView6.setItems(getBeboere('6'));

		tView1.getColumns().addAll(værelseColumn, navnColumn, indflytningColumn, telefonColumn, uddStedColumn,
				uddannelseColumn, påbegyndtUddColumn, afslutningUddColumn, lejeaftalensUdløbColumn);

		tView2.getColumns().addAll(værelseColumn2, navnColumn2, indflytningColumn2, telefonColumn2, uddStedColumn2,
				uddannelseColumn2, påbegyndtUddColumn2, afslutningUddColumn2, lejeaftalensUdløbColumn2);

		tView3.getColumns().addAll(værelseColumn3, navnColumn3, indflytningColumn3, telefonColumn3, uddStedColumn3,
				uddannelseColumn3, påbegyndtUddColumn3, afslutningUddColumn3, lejeaftalensUdløbColumn3);

		tView4.getColumns().addAll(værelseColumn4, navnColumn4, indflytningColumn4, telefonColumn4, uddStedColumn4,
				uddannelseColumn4, påbegyndtUddColumn4, afslutningUddColumn4, lejeaftalensUdløbColumn4);

		tView5.getColumns().addAll(værelseColumn5, navnColumn5, indflytningColumn5, telefonColumn5, uddStedColumn5,
				uddannelseColumn5, påbegyndtUddColumn5, afslutningUddColumn5, lejeaftalensUdløbColumn5);

		tView6.getColumns().addAll(værelseColumn6, navnColumn6, indflytningColumn6, telefonColumn6, uddStedColumn6,
				uddannelseColumn6, påbegyndtUddColumn6, afslutningUddColumn6, lejeaftalensUdløbColumn6);

		// Første tab: Alle beboere

		tab1.setContent(tView1);
		tab2.setContent(tView2);
		tab3.setContent(tView3);
		tab4.setContent(tView4);
		tab5.setContent(tView5);
		tab6.setContent(tView6);

		tP.getTabs().addAll(tab1, tab2, tab3, tab4, tab5, tab6);
		scene = new Scene(borderP, 900, 700);
		primaryStage.setScene(scene);
	}

	/**
	 * 
	 * @param salnummer
	 *            - Character der gives for at finde salsnummer. angives '1' tages
	 *            alle beboere
	 * @return
	 */
	private ObservableList<Beboer> getBeboere(char salnummer) {
		// Der skal lægges ind og testes for 'isKlaret'
		ArrayList<Beboer> alleBeboere = ec.getBeboere();
		ArrayList<Beboer> temp = new ArrayList<Beboer>();
		if (salnummer != '1') {
			for (Beboer b : alleBeboere) {
				char sal = b.getVærelse().charAt(0);
				if (sal == salnummer) {
					temp.add(b);
				}

			}
			ObservableList<Beboer> beboere = FXCollections.observableArrayList(temp);
			return beboere;
		} else {
			ObservableList<Beboer> beboere = FXCollections.observableArrayList(alleBeboere);
			return beboere;
		}
	}

	public void fremlejeMenu(Stage primaryStage) {
		// Tabs og overordnet layout genereres
		BorderPane borderP = new BorderPane();
		// venstre side
		Button tilbageButton = new Button("Tilbage");
		tilbageButton.setOnAction(e -> {
			try {
				hovedMenu(primaryStage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		VBox vb = new VBox(tilbageButton);
		borderP.setLeft(vb);

		TableView<Beboer> tW = new TableView<Beboer>();

		// kolloner til Tableviews
		TableColumn værelseColumn = new TableColumn("Værelse");
		TableColumn fremlejerColumn = new TableColumn("Fremlejers Navn");
		TableColumn fremlejetagerNavnColumn = new TableColumn("Fremlejetagers navn");
		TableColumn hovedMenuDatoColumn = new TableColumn("hovedMenu dato");
		TableColumn slutDatoColumn = new TableColumn("Slut Dato");
		// TableColumn påbegyndtUddColumn = new TableColumn("Uddannelse påbegyndt");
		// TableColumn afslutningUddColumn = new TableColumn("Uddannelse forventes
		// afsluttet");
		// TableColumn lejeaftalensUdløbColumn = new TableColumn("Lejeaftalens udløb");
		// Ovenstående er informationer der skal tastes ind, men ikke noget der skal
		// fremgå på siden.

		tW.getColumns().addAll(værelseColumn, fremlejerColumn, fremlejetagerNavnColumn, hovedMenuDatoColumn,
				slutDatoColumn);

		borderP.setCenter(tW);
		scene = new Scene(borderP, 900, 700);
		primaryStage.setScene(scene);
	}
}
