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
	private static int level = 0;
	private static String Stats = "";
	private static List<String> collection = null;
	private static String[] HERO = new String[8]; //hero content will be stored and persisted here
	
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
	
	//getting hero stats
	private static void setStats() {
		Stats = "Name:\t\t"+HERO[2];
		Stats += "\nClass:\t\t" + HERO[1];
		Stats += "\nLevel:\t\t" + HERO[3];
		Stats += "\nExperience:\t"+ HERO[4];
		Stats += "\nAttack:\t\t"+ HERO[5];
		Stats += "\nDefence:\t\t"+ HERO[6];
		Stats += "\nHitPoints:\t\t"+ HERO[7];
	}
	
	public static int getWorldSize() {
		int size = 0;
		try {
			int level = Integer.parseInt(HERO[3]);
			size =  (level - 1)*5+10-(level%2);			
		}catch(NumberFormatException e) {
			return (10);
		}

		return (size);//temp value
	}
	
	//hero 
	public static void setWorld() {
		try {
			int level = Integer.parseInt(HERO[3])+1;
			int xp = level*1000 + (level-1)*(level-1)*450;
			if (level > 5) {
				System.exit(-1);
			}
			HERO[3] = level + "";
			HERO[4] = xp + "";
			update();
		}catch(NumberFormatException e) {
			System.out.println("");
		}
	}
	
	public static int getLevel() {
		return (1);
	}
	
	//creating new hero
	public static void newHero(String info) {
		DatabaseSetup.writter(info);
		//System.out.println(info);
	}
	
	//collecting all the previously created heroes from textFile
	public static List<String> collect()
	{
		collection = new ArrayList<>();
		collection = DatabaseSetup.collect();
		return (collection);
	}
	
	//method to populated selected Hero
	public static void currentPlayer(String id) {
		if (id.trim().equals("-6"))
			id = DatabaseSetup.getCount();
		String[] getId = id.trim().split("\t");
		getId = DatabaseSetup.ReadLine(getId[0]).trim().split("\t");
		//populating created Hero
		for (int r = 0; r < 8; r++) {
			HERO[r] = getId[r];
		}
	}
	
	public static void getAward(String award) {
		//"PHOENIX-BUSTER", "HULKBUSTER", "ULTRON", "InfinityGauntlet"
		try {
			if (award.trim().equals("PHOENIX-BUSTER")) {
				HERO[5]	= Integer.parseInt(HERO[5]) + 10 + "";
			}else if (award.trim().equals("HULKBUSTER")) {
				HERO[6]	= Integer.parseInt(HERO[6]) + 10 + "";
			}else if (award.trim().equals("ULTRON")) {
				HERO[7]	= Integer.parseInt(HERO[7]) + 10 + "";
			}else if (award.trim().equals("Infinity Gauntlet")) {
				HERO[6]	= Integer.parseInt(HERO[6]) + 50 + "";
				HERO[5]	= Integer.parseInt(HERO[5]) + 50 + "";
				HERO[7]	= Integer.parseInt(HERO[7]) + 50 + "";
			}
			update();
		}catch(NumberFormatException e) {
			System.out.println("");
		}
	}
	
	//getter for Stats
	public static String getStats() {
		setStats();
		return (Stats);
	}
	
	//update content for hero during gamePlay
	private static void update() {
		String content = HERO[0] + "\t" + HERO[1] + "\t" + HERO[2] + "\t"+HERO[3]+"\t" + HERO[4] +"\t"+ HERO[5] +"\t"+ HERO[6] +"\t"+ HERO[7] +"";
		//System.out.println(content);
		DatabaseSetup.update(content, HERO[0]);
	}
}
