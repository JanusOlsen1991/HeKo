package view;

import java.text.SimpleDateFormat;

import controller.Controller;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Beboer;
import model.Deadline;
import model.Dispensation;
import model.Værelsesudlejning;

public class GUI{// extends Application {
	private Scene scene;
	Controller controller = new Controller();
	GUI_PopUps popUp = new GUI_PopUps();

//Skal indeholde både modeller og Controller objekter

	/**
	 * Metoden anvendes til at oprette "hovedmenuen" for programmet.
	 * 
	 * @param primaryStage
	 *            : is the stage given to set up the GUI
	 */
//	@Override

	public void hovedMenu(Stage primaryStage) throws Exception {
		BorderPane borderP = new BorderPane();
		primaryStage.setTitle("Herlevkollegiets Indstillingsudvalg");
		VBox venstreLayout = new VBox();
		GridPane højreLayout = new GridPane();
		borderP.setLeft(venstreLayout);
		borderP.setCenter(højreLayout);

		// Buttons til venstre side af menuen
		Button beboerlisteButton = new Button("Beboerliste");
		beboerlisteButton.setOnAction(e -> beboerlisteMenu(primaryStage));
		Button studieKontrolButton = new Button("Studiekontrol");
		studieKontrolButton.setOnAction(e -> studieKontrolMenu(primaryStage));
		Button dispensationsButton = new Button("Dispensation");
		dispensationsButton.setOnAction(e -> dispensationsMenu(primaryStage));
		Button fremlejeButton = new Button("Fremleje");
		fremlejeButton.setOnAction(e -> fremlejeMenu(primaryStage));
		Button værelsesudlejningsButton = new Button("Værelsesudlejning");
		værelsesudlejningsButton.setOnAction(e -> værelsesUdlejning(primaryStage));

		// Tilføjer buttons til venstre side.
		venstreLayout.getChildren().addAll(beboerlisteButton, studieKontrolButton, dispensationsButton, fremlejeButton,
				værelsesudlejningsButton);

		// Buttons og "Påmindelser/deadlines" til højre side af menuen
		TableView<Deadline> tView = new TableView<Deadline>();
		TableColumn hvornårColumn = new TableColumn("Dato");
		TableColumn hvadColumn = new TableColumn("Hvad:");
		TableColumn hvemColumn = new TableColumn("Hvem:");
		tView.getColumns().add(hvornårColumn);
		tView.getColumns().add(hvadColumn);
		tView.getColumns().add(hvemColumn);

		Button tilføjButton = new Button("Tilføj påmindelse");
		tilføjButton.setOnAction( e-> popUp.createDeadline());
		
		Button fjernButton = new Button("Fjern påmindelse");
		//TESTTTTTT
		Deadline deadline = new Deadline(null,null,null);
		fjernButton.setOnAction(e-> popUp.startStudiekontrol());
		
		
		// Tilføjer til højre side af menuen
		højreLayout.add(tView, 2, 3, 3, 6);
		højreLayout.add(tilføjButton, 2, 10);
		højreLayout.add(fjernButton, 4, 10);

		scene = new Scene(borderP, 900, 700);
		primaryStage.setScene(scene);
		primaryStage.show();

	}
/**
 * 
 * @param primaryStage : main stage
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

		TableView<Værelsesudlejning> tW1 = new TableView<Værelsesudlejning>();
		TableView<Værelsesudlejning> tW2 = new TableView<Værelsesudlejning>();


		TableColumn værelseColumn = new TableColumn("Værelse");
		TableColumn navnColumn = new TableColumn("navn");
		TableColumn indflytningColumn = new TableColumn("Overtagelsesdato");
		TableColumn behandletDatoColumn = new TableColumn("Behandlingsdato");
		TableColumn behandlerInitialerColumn = new TableColumn("Behandler Initialer");

		tW1.getColumns().addAll(indflytningColumn,værelseColumn, navnColumn, behandletDatoColumn, behandlerInitialerColumn);
		tW2.getColumns().addAll(indflytningColumn,værelseColumn, navnColumn, behandletDatoColumn, behandlerInitialerColumn);
		
		tab1.setContent(tW1);
		tab2.setContent(tW2);
		
		scene = new Scene(borderP, 900, 700);
		primaryStage.setScene(scene);
	}

	private void dispensationsMenu(Stage primaryStage) {
		
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
		
		TableView<Dispensation> tW = new TableView<Dispensation>();
		TableColumn værelseColumn = new TableColumn("Værelse");
		TableColumn navnColumn = new TableColumn("Navn");
		TableColumn hovedMenudatoColumn = new TableColumn("hovedMenu dato");
		TableColumn slutDatoColumn = new TableColumn("Slut dato");
		TableColumn antalBetingelserColumn = new TableColumn("Antal betingelser");
		
		tW.getColumns().addAll(værelseColumn,navnColumn,hovedMenudatoColumn,slutDatoColumn,antalBetingelserColumn);
		
		Tab tab1 = new Tab("Aktive dispensationer");
		tab1.setClosable(false);
		tab1.setContent(tW);
		tP.getTabs().add(tab1);
		

		
		scene = new Scene(borderP, 900,700);
		primaryStage.setScene(scene);
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
		
		//Tabs herunder skal oprettes KUN "når de er i gang".

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
		//...

		TableView<Beboer> tW1 = new TableView<Beboer>();
		TableView<Beboer> tW2 = new TableView<Beboer>();
		TableView<Beboer> tW3 = new TableView<Beboer>();
		TableView<Beboer> tW4 = new TableView<Beboer>();
		TableView<Beboer> tW5 = new TableView<Beboer>();
		TableView<Beboer> tW6 = new TableView<Beboer>();

		// kolloner til Tableviews
		TableColumn værelseColumn = new TableColumn("Værelse");
		TableColumn navnColumn = new TableColumn("Navn");
//		TableColumn indflytningColumn = new TableColumn("indflytningsdato");
		TableColumn uddannelseColumn = new TableColumn("Uddannelse");
		TableColumn uddStedColumn = new TableColumn("Uddannelsessted");
		TableColumn påbegyndtUddColumn = new TableColumn("Uddannelse påbegyndt");
		TableColumn afslutningUddColumn = new TableColumn("Uddannelse forventes afsluttet");
		TableColumn lejeaftalensUdløbColumn = new TableColumn("Lejeaftalens udløb"); // SLET? - Måske for forvirrende,
																						// var tænkt som at de kunne se
																						// hvornår de skulle have
																						// studiekontrol igen næste
																						// gang.

		// TableViews oprettes med kollonnerne
		tW1.getColumns().addAll(værelseColumn, navnColumn, uddannelseColumn, påbegyndtUddColumn,
				afslutningUddColumn, lejeaftalensUdløbColumn);
		tW2.getColumns().addAll(værelseColumn, navnColumn, uddannelseColumn, påbegyndtUddColumn,
				afslutningUddColumn, lejeaftalensUdløbColumn);
		tW3.getColumns().addAll(værelseColumn, navnColumn, uddannelseColumn, påbegyndtUddColumn,
				afslutningUddColumn, lejeaftalensUdløbColumn);
		tW4.getColumns().addAll(værelseColumn, navnColumn, uddannelseColumn, påbegyndtUddColumn,
				afslutningUddColumn, lejeaftalensUdløbColumn);
		tW5.getColumns().addAll(værelseColumn, navnColumn, uddannelseColumn, påbegyndtUddColumn,
				afslutningUddColumn, lejeaftalensUdløbColumn);
		tW6.getColumns().addAll(værelseColumn, navnColumn, uddannelseColumn, påbegyndtUddColumn,
				afslutningUddColumn, lejeaftalensUdløbColumn);

		// Første tab: Alle beboere

		tab1.setContent(tW1);
		tab2.setContent(tW2);
		tab3.setContent(tW3);
		tab4.setContent(tW4);
		tab5.setContent(tW5);
		tab6.setContent(tW6);

		tP.getTabs().addAll(tab1, tab2, tab3, tab4, tab5, tab6);
		scene = new Scene(borderP, 900, 700);
		primaryStage.setScene(scene);
		
	}

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

		TableView<Beboer> tW1 = new TableView<Beboer>();
		TableView<Beboer> tW2 = new TableView<Beboer>();
		TableView<Beboer> tW3 = new TableView<Beboer>();
		TableView<Beboer> tW4 = new TableView<Beboer>();
		TableView<Beboer> tW5 = new TableView<Beboer>();
		TableView<Beboer> tW6 = new TableView<Beboer>();

		// kolloner til Tableviews
		TableColumn værelseColumn = new TableColumn("Værelse");
		TableColumn navnColumn = new TableColumn("Navn");
		TableColumn indflytningColumn = new TableColumn("indflytningsdato");
		TableColumn uddannelseColumn = new TableColumn("Uddannelse");
		TableColumn uddStedColumn = new TableColumn("Uddannelsessted");
		TableColumn påbegyndtUddColumn = new TableColumn("Uddannelse påbegyndt");
		TableColumn afslutningUddColumn = new TableColumn("Uddannelse forventes afsluttet");
		TableColumn lejeaftalensUdløbColumn = new TableColumn("Lejeaftalens udløb"); // SLET? - Måske for forvirrende,
																						// var tænkt som at de kunne se
																						// hvornår de skulle have
																						// studiekontrol igen næste
																						// gang.

		// TableViews oprettes med kollonnerne
		tW1.getColumns().addAll(værelseColumn, navnColumn, indflytningColumn, uddannelseColumn, påbegyndtUddColumn,
				afslutningUddColumn, lejeaftalensUdløbColumn);
		tW2.getColumns().addAll(værelseColumn, navnColumn, indflytningColumn, uddannelseColumn, påbegyndtUddColumn,
				afslutningUddColumn, lejeaftalensUdløbColumn);
		tW3.getColumns().addAll(værelseColumn, navnColumn, indflytningColumn, uddannelseColumn, påbegyndtUddColumn,
				afslutningUddColumn, lejeaftalensUdløbColumn);
		tW4.getColumns().addAll(værelseColumn, navnColumn, indflytningColumn, uddannelseColumn, påbegyndtUddColumn,
				afslutningUddColumn, lejeaftalensUdløbColumn);
		tW5.getColumns().addAll(værelseColumn, navnColumn, indflytningColumn, uddannelseColumn, påbegyndtUddColumn,
				afslutningUddColumn, lejeaftalensUdløbColumn);
		tW6.getColumns().addAll(værelseColumn, navnColumn, indflytningColumn, uddannelseColumn, påbegyndtUddColumn,
				afslutningUddColumn, lejeaftalensUdløbColumn);

		// Første tab: Alle beboere

		tab1.setContent(tW1);
		tab2.setContent(tW2);
		tab3.setContent(tW3);
		tab4.setContent(tW4);
		tab5.setContent(tW5);
		tab6.setContent(tW6);

		tP.getTabs().addAll(tab1, tab2, tab3, tab4, tab5, tab6);
		scene = new Scene(borderP, 900, 700);
		primaryStage.setScene(scene);
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
//		TableColumn påbegyndtUddColumn = new TableColumn("Uddannelse påbegyndt");
//		TableColumn afslutningUddColumn = new TableColumn("Uddannelse forventes afsluttet");
//		TableColumn lejeaftalensUdløbColumn = new TableColumn("Lejeaftalens udløb");
		// Ovenstående er informationer der skal tastes ind, men ikke noget der skal fremgå på siden.
		
		tW.getColumns().addAll(værelseColumn,fremlejerColumn,fremlejetagerNavnColumn,hovedMenuDatoColumn,slutDatoColumn);
		
		borderP.setCenter(tW);
		scene = new Scene(borderP, 900, 700);
		primaryStage.setScene(scene);
	}
}
