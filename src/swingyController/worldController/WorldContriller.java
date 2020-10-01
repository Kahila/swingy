package swingyController.worldController;

import swingyView.swingyViewGui.*;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.stage.Stage;
import swingyController.heroController.HeroController;
import swingyModel.swingyDatabase.DatabaseSetup;

public class WorldContriller extends Application{
	private static HeroController heroController = null;
	private static int level = 9;
	private static List<String> collection = null;
	private static String[] HERO = null; //hero content will be stored and persisted here
	
	public static void main(String[] args) {
		heroController = new HeroController();
		heroController.getHero();
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		new GuiView(primaryStage);
		primaryStage.show();
	}
	
	public static int getWorldSize() {
		return (level);//temp value
	}
	
	public static void setWorld() {
		level += 10;
	}
	
	public static int getLevel() {
		return (1);
	}

	//creating new hero
	public static void newHero(String info) {
		DatabaseSetup.writter(info);
		System.out.println(info);
	}
	
	//collecting all the previously created heroes from textFile
	public static List<String> collect()
	{
		collection = new ArrayList<>();
		collection = DatabaseSetup.collect();
		return (collection);
	}
}
