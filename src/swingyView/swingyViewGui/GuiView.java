package swingyView.swingyViewGui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import swingyController.worldController.WorldContriller;
import swingyController.heroController.HeroController;

public class GuiView {
	private static String wall = "https://wallpaperaccess.com/full/759485.jpg";
	private static Stage stage = null;
	private TextField[][] world = null;
	private int[][] hero = null;
	private HeroController heroController = null;
	
	private Scene sceneP1 = null; 
	private Scene sceneSelect = null;
	private Scene sceneCreate = null;
	private Scene sceneWorld = null;
	private Scene sceneFight = null;
	
	private GridPane firstPane = null;
	private GridPane selectPane = null;
	private GridPane createPane = null;
	private GridPane worldPane  = null;
	private GridPane fightPane = null;
	
	Button stats = null;
	Button north = null;
	Button south = null;
	Button east = null;
	Button west = null;
	Button fight = null;
	Button run = null;
	
	//Parameterized constructor
	public GuiView(Stage stage1) {
		stage = stage1;
		setUpUI();
	}
	
	//method for setting up UI
	public void setUpUI() {
		stage.setTitle("SWINGY");
		
		PageOne();
		stage.show();
	}
	
	//first page of game GUI
	private void PageOne() {
		firstPane = new GridPane();
		sceneP1 = new Scene(firstPane, 1500, 800);
		
		Button select = new Button("SELECT HERO");
		Button create = new Button("CREATE HERO");
		Button change = new Button("CONSOLE VIEW");
		
		//setting actions for buttons
		select.setOnAction(value ->{
			selectPage();
		});
		
		create.setOnAction(value ->{
			createHero();
		});
		
		select.setPrefWidth(300);
		select.setPrefHeight(50);
		create.setPrefWidth(300);
		create.setPrefHeight(50);
		change.setPrefWidth(300);
		change.setPrefHeight(50);
		select.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: #4cd137;");
		create.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: #4cd137;");
		change.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: #4cd137;");
		
		firstPane.add(select, 0, 0);
		firstPane.add(create, 0, 1);
		firstPane.add(change, 0, 3);
		firstPane.setAlignment(Pos.CENTER);
		firstPane.setStyle("-fx-background-image: url('"+wall+"')");
		
		GridPane.setMargin(select, new Insets(0, 0, 10, 0));
		GridPane.setMargin(create, new Insets(0, 0, 10, 0));
		
		stage.setScene(sceneP1);
	}
	
	//select page GUI
	private void selectPage() {
		Button submit = new Button("SUBMIT");
		Button home = new Button("BACK");
		String tempHero[] = {"Adonis", "Kalombo", "King", "Creed"};
		
		selectPane = new GridPane();
		sceneSelect = new Scene(selectPane, 1500, 800);
		
		ComboBox<String> tfID = new ComboBox<String>(FXCollections.observableArrayList(tempHero)); 
		Label ID = new Label("SELECT HERO");
		
		submit.setPrefWidth(300);
		submit.setPrefHeight(50);
		home.setPrefWidth(300);
		home.setPrefHeight(50);
		tfID.setPrefWidth(300);
		
		selectPane.add(ID, 0, 0);
		selectPane.add(tfID, 0, 1);
		selectPane.add(submit, 0, 2);
		selectPane.add(home, 0, 3);
		
		//setting action for buttons
		home.setOnAction(value ->{stage.setScene(sceneP1);});
		submit.setOnAction(value ->{showWorld();});
		
		submit.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: #4cd137;");
		home.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: #4cd137;");
		tfID.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: #4cd137;");
		selectPane.setStyle("-fx-background-image: url('https://wallpaperaccess.com/full/759485.jpg')");
		ID.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: white;");

		selectPane.setAlignment(Pos.CENTER);
		GridPane.setMargin(tfID, new Insets(0, 0, 10, 0));
		GridPane.setMargin(submit, new Insets(0, 0, 10, 0));
		
		stage.setScene(sceneSelect);
	}
	
	//Create Hero GUI
	private void createHero() {
		Button submit = new Button("CREATE");
		Button home = new Button("BACK");
		String tempHero[] = {"Avenger", "Paladin", "X-Men", "ThunderCat"};
		String avengers[] = {"Thor", "Wasp", "Hulk", "Ant-Man", "Iron-Man", "Vision", "Tigro"};
		String paladins[] = {"Keith", "Lance", "Hunk", "Pidge", "Shiro", "Allura"};
		String xMen[] = {"Cyclops", "Beast", "Havok", "X-23", "Storm", "Colossus", "Bloodstorm", "Ink"};
		String thundercats[] = {"Jaga", "WilyKit", "WilyKat", "Tygra", "Cheetara", "Lion-O", "Panthro"};
		
		ComboBox<String> tfID = new ComboBox<String>(FXCollections.observableArrayList(tempHero));
		ComboBox<String> tfsub = new ComboBox<String>();;

		createPane = new GridPane();
		sceneCreate = new Scene(createPane, 1500, 800);

		EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() { 
          public void handle(ActionEvent e) 
          { 
              if (tfID.getValue().equals(tempHero[0])) {
            	  tfsub.setItems(FXCollections.observableArrayList(avengers));
              }else if (tfID.getValue().equals(tempHero[1])) {
            	  tfsub.setItems(FXCollections.observableArrayList(paladins));
              }else if (tfID.getValue().equals(tempHero[2])) {
            	  tfsub.setItems(FXCollections.observableArrayList(xMen));
              }else if (tfID.getValue().equals(tempHero[3])) {
            	  tfsub.setItems(FXCollections.observableArrayList(thundercats));
              }else {
            	  tfsub.setItems(null);
              }
          } 
		}; 
		
		tfID.setOnAction(event);
		
		submit.setPrefWidth(300);
		submit.setPrefHeight(50);
		tfID.setPrefWidth(300);
		tfsub.setPrefWidth(300);
		home.setPrefWidth(300);
		home.setPrefHeight(50);
		
		createPane.add(tfID, 0, 0);
		createPane.add(tfsub, 0, 1);
		createPane.add(submit, 0, 2);
		createPane.add(home, 0, 3);
		
		createPane.setAlignment(Pos.CENTER);
		GridPane.setMargin(tfID, new Insets(0, 0, 10, 0));
		GridPane.setMargin(tfsub, new Insets(0, 0, 10, 0));
		GridPane.setMargin(submit, new Insets(0, 0, 10, 0));
		
		//setting action for buttons
		home.setOnAction(value ->{stage.setScene(sceneP1);});
		submit.setOnAction(value ->{showWorld();});
		
		createPane.setStyle("-fx-background-image: url('"+wall+"')");
		tfID.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: #4cd137;");
		tfsub.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: #4cd137;");
		submit.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: #4cd137;");
		home.setStyle("-fx-font-weight: bold; -fx-font-size: 20px; -fx-text-fill: #4cd137;");

		stage.setScene(sceneCreate);
	}
	
	//game play GUI
	private void showWorld() {
		worldPane = new GridPane();
		sceneWorld = new Scene(worldPane, 1500, 800);
		Alert a = new Alert(AlertType.NONE); 
		int size = WorldContriller.getWorldSize();
		Button home = new Button("Quit");
		stats = new Button("Stats");
		north = new Button("W");
		south = new Button("S  ");
		east = new Button("A");
		west = new Button("D");
		fight = new Button("F");
		run = new Button("R");
		
		TextField about = new TextField("");
		
		about.setPrefWidth(100);
		about.setPrefHeight(500);
		about.setDisable(true);
		
		fight.setVisible(false);
		run.setVisible(false);
		
		worldPane.setStyle("-fx-background-image: url('"+wall+"')");
		
		//initializing hero
		heroController = new HeroController();
		hero = heroController.getHero();

		world = new TextField[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				world[i][j] = new TextField("");
				world[i][j].setPrefWidth(25);
				world[i][j].setPrefHeight(20);
				world[i][j].setDisable(true);
				
				if (i == 0 || (i == (size -1))) {
					world[i][j].setStyle("-fx-background-color: #4cd137");
					System.out.printf("Adonis\n");
				}else if ((j == 0 && i != 0) || (j == (size -1) && i != (size -1))) {
					world[i][j].setStyle("-fx-background-color: #4cd137");
				}else if (hero[i][j] > 50) {
					world[i][j].setStyle("-fx-background-color: black");
					System.out.printf("%d ", hero[i][j]);
				}
				worldPane.add(world[i][j], i, j);
			}
			System.out.printf("\n");
		}
		
		worldPane.add(north, size/2, size + 1);
		worldPane.add(fight, size/2, size + 1);
		worldPane.add(east, size/2 + 1, size + 2);
		worldPane.add(west, size/2 - 1, size + 2);
		worldPane.add(south, size/2, size + 2);
		worldPane.add(run, size/2, size + 2);
		worldPane.add(home, 0, size + 1);
		worldPane.add(stats, size -1, size + 1);
		
		//showing hero before movements
		showHero();
		
		//setting on action
		north.setOnAction(value -> {
			hero = heroController.moveHero("UP");
			fightMode();
			showHero();
		});
		//down
		south.setOnAction(value -> {
			hero = heroController.moveHero("DOWN");
			fightMode();
			showHero();
		});
		//left
		east.setOnAction(value -> {
			hero = heroController.moveHero("LEFT");
			fightMode();
			showHero();
		});
		//right
		west.setOnAction(value -> {
			hero = heroController.moveHero("RIGHT");
			fightMode();
			showHero();
		});
		//fight
		fight.setOnAction(value ->{
			if (heroController.getWin() == false) {
				gameOver();
			}else {
				gameMode();
				a.setAlertType(AlertType.CONFIRMATION);
				a.show();
				
			}
		});
		//run
		run.setOnAction(value ->{
//			heroController.runner();
			showHero();
			gameMode();
		});
		
		GridPane.setMargin(north, new Insets(10, 0, 0, 0));
		GridPane.setMargin(fight, new Insets(10, 0, 10, 0));
		
		worldPane.setAlignment(Pos.CENTER);
		stage.setScene(sceneWorld);
	}
	
	//method that will determine when to show the fightPane
	private void fightMode() {
		if (heroController.getFight()) {
			north.setVisible(false);
			south.setVisible(false);
			east.setVisible(false);
			west.setVisible(false);
			stats.setVisible(false);
			fight.setVisible(true);
			run.setVisible(true);
		}
	}
	
	private void gameMode() {
			north.setVisible(true);
			south.setVisible(true);
			east.setVisible(true);
			west.setVisible(true);
			stats.setVisible(true);
			fight.setVisible(false);
			run.setVisible(false);
	}
	
	//game over GUI
	private void gameOver() {
		fightPane = new GridPane();
		sceneFight = new Scene(fightPane, 1500, 800);
		Label lbl = new Label("GAME OVER");
		
		fightPane.add(lbl, 0, 0);
		lbl.setStyle("-fx-background-color: #00a8ff");
		fightPane.setStyle("-fx-background-image: url('https://hdwallpaperim.com/wp-content/uploads/2017/08/24/104078-GAME_OVER-video_games-glitch_art-748x468.jpg'); -fx-background-repeat: stretch;-fx-background-size: 1500 800;");
		
		fightPane.setAlignment(Pos.CENTER);
		stage.setScene(sceneFight);
	}
	
	//find hero and display it
	private void showHero() {
		int size = WorldContriller.getWorldSize();
		
		for (int r = 0; r < size; r++) {
			for (int c = 0; c < size; c++) {
				if (hero[r][c] == 1) {
					world[r][c].setStyle("-fx-background-color: #00a8ff");
				}else if (hero[r][c] != -2 && hero[r][c] < 50) {
					world[r][c].setStyle("");
				}
			}
		}
	}	
}