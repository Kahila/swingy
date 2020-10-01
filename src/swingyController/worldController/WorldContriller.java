package swingyController.worldController;

import swingyView.swingyViewGui.*;

import javafx.application.Application;
import javafx.stage.Stage;
import swingyController.heroController.HeroController;

public class WorldContriller extends Application{
	private static HeroController heroController = null;
	
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
		return (9);//temp value
	}
	
	public static int getLevel() {
		return (1);
	}
}
