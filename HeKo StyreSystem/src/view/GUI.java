package view;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Beboer;
import model.Deadline;
import model.Værelsesudlejning;

public class GUI extends Application {
	private Scene scene;

	/**
	 * Metoden anvendes til at oprette "hovedmenuen" for programmet.
	 * 
	 * @param primaryStage
	 *            : is the stage given to set up the GUI
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		Stage window = primaryStage;
		window.setTitle("Herlevkollegiets Indstillingsudvalg");
		BorderPane pane = new BorderPane();
		VBox venstreLayout = new VBox();
		GridPane højreLayout = new GridPane();
		pane.setLeft(venstreLayout);
		pane.setRight(højreLayout);

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
		Button fjernButton = new Button("Fjern påmindelse");
		// Tilføjer til højre side af menuen
		højreLayout.add(tView, 2, 3, 3, 6);
		højreLayout.add(tilføjButton, 2, 10);
		højreLayout.add(fjernButton, 4, 10);

		scene = new Scene(pane, 900, 700);
		window.setScene(scene);
		window.show();

	}

	private void værelsesUdlejning(Stage primaryStage) {
		BorderPane borderP = new BorderPane();
		// venstre side
		Button tilbageButton = new Button("Tilbage");
		tilbageButton.setOnAction(e -> {
			try {
				start(primaryStage);
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
		// TODO Auto-generated method stub
	
	}

	private void studieKontrolMenu(Stage primaryStage) {
		// TODO Auto-generated method stub
		
	}

	public void beboerlisteMenu(Stage primaryStage) {

		// overordnet layout genereres
		BorderPane borderP = new BorderPane();
		// venstre side
		Button tilbageButton = new Button("Tilbage");
		tilbageButton.setOnAction(e -> {
			try {
				start(primaryStage);
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
				start(primaryStage);
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
		TableColumn startDatoColumn = new TableColumn("Start dato");
		TableColumn slutDatoColumn = new TableColumn("Slut Dato");
//		TableColumn påbegyndtUddColumn = new TableColumn("Uddannelse påbegyndt");
//		TableColumn afslutningUddColumn = new TableColumn("Uddannelse forventes afsluttet");
//		TableColumn lejeaftalensUdløbColumn = new TableColumn("Lejeaftalens udløb");
		// Ovenstående er informationer der skal tastes ind, men ikke noget der skal fremgå på siden.
		
		tW.getColumns().addAll(værelseColumn,fremlejerColumn,fremlejetagerNavnColumn,startDatoColumn,slutDatoColumn);
		
		borderP.setCenter(tW);
		scene = new Scene(borderP, 900, 700);
		primaryStage.setScene(scene);
	}
}
