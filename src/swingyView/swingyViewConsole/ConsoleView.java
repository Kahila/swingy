package swingyView.swingyViewConsole;

import java.util.ArrayList;
import java.util.List;

import swingyController.heroController.HeroController;
import swingyController.worldController.WorldContriller;

public class ConsoleView {
	private int[][] hero = null;
	private HeroController heroController = null;
	private String award = "";
	private WorldContriller wc = new WorldContriller();
	
	public void worldView() {
		String input = "";
		input = first();//select or create player
		if (input.trim().equals("2"))
			CreateHero();
		else if (input.trim().equals("1"))
			SelectHero();
		System.out.println("****************************\nHERO Stats :\n****************************\n" + WorldContriller.getStats());			
		gameplay();
	}
	
	//first prompt
	private String first() {
		System.out.printf("Select or Create Hero\n\n"
				+ "1 - Select Hero\n"
				+ "2 - Create Hero\n"
				+ "3 - Exit\n");

		String str = "";
		while (true) {
			System.out.printf("\nAnswer: ");
			str = wc.userInput();
			if (str.trim().equals("1")) {
				return ("1");
			}else if (str.trim().equals("2")) {
				return("2");
			}else if (str.trim().equals("3")) {
				System.out.printf("\nGOOD BYE\n");
				System.exit(0);
			}
			else System.out.printf("\ninvalid input!!!!!\n");
		}
			
	}
	
	//select Hero
	private void SelectHero() {
		List<String> tempHero = new ArrayList<>();
		tempHero = WorldContriller.collect();
		int i = 0;
		int index = 0;
		String str = "";
		
		System.out.printf("\n***********************\nSELECT FROM BELOW\n***********************\n");
		for (String h : tempHero) {
			System.out.println(h);
			i++;
		}
		
		while (true) {
			try {
				System.out.printf("\nAnswer: ");
				str = wc.userInput();
				if ((Integer.parseInt(str) <= i) && (Integer.parseInt(str) > 0))
				{
					index = Integer.parseInt(str);
					System.out.println("chosen ->" + tempHero.get(index - 1));
					WorldContriller.currentPlayer(tempHero.get(index - 1));
					break;
				}else
					System.out.println("Invalid Pick");
			}catch(NumberFormatException e) {
				System.out.println("Invalid Input");
			}
		}
	}
	
	//create Hero
	private void CreateHero() {
		String tempHero[] = {"Avenger", "Paladin", "X-Men", "ThunderCat"};
		String avengers[] = {"Thor", "Wasp", "Hulk", "Ant-Man", "Iron-Man", "Vision", "Tigro"};
		String paladins[] = {"Keith", "Lance", "Hunk", "Pidge", "Shiro", "Allura"};
		String xMen[] = {"Cyclops", "Beast", "Havok", "X-23", "Storm", "Colossus", "Bloodstorm", "Ink"};
		String thundercats[] = {"Jaga", "WilyKit", "WilyKat", "Tygra", "Cheetara", "Lion-O", "Panthro"};
		
		System.out.printf("\n***********************\nCREATING NEW HERO\n***********************\n");
		int i = 1;
		System.out.println(0 + " exit");
		for (String h: tempHero) {
			System.out.println(i + " " + h);
			i++;
		}
		
		String str = "";
		String answer = "";
		
		//selecting the hero class
		while (true) {
			System.out.printf("\nAnswer: ");
			str = wc.userInput();
			if (str.trim().equals("0")) {
				System.out.printf("\nGOOD BYE\n");
				System.exit(0);
			}else if (str.trim().equals("1")) {
				System.out.println(tempHero[0]);
				listHero(avengers);
				answer += tempHero[0] + "\t" + userOption(avengers);;
				break;
			}else if (str.trim().equals("2")) {
				System.out.println(tempHero[1]);
				listHero(paladins);
				answer += tempHero[1] + "\t" + userOption(paladins);;
				break;
			}else if (str.trim().equals("3")) {
				System.out.println(tempHero[2]);
				listHero(xMen);
				answer += tempHero[2] + "\t" + userOption(xMen);;
				break;
			}else if (str.trim().equals("4")) {
				System.out.println(tempHero[3]);
				listHero(thundercats);		
				answer += tempHero[3] + "\t" + userOption(thundercats);;
				break;
			}else System.out.printf("\ninvalid input!!!!!\n");
		}
		WorldContriller.newHero(answer);
		WorldContriller.currentPlayer("-6");
	}
	
	//
	private String userOption(String[] heroClass) {
		String str = "";
		
		int i = 0;
		int index = 0;
		for (@SuppressWarnings("unused") String h: heroClass) {
			i++;
		}
		
		while (true) {
			try {
				System.out.printf("\nAnswer: ");
				str = wc.userInput();
				if ((Integer.parseInt(str) <= i) && (Integer.parseInt(str) > 0))
				{
					index = Integer.parseInt(str);
//					System.out.println("chosen ->" + heroClass[index - 1]);
					return heroClass[index - 1];
				}else
					System.out.println("Invalid Pick");
			}catch(NumberFormatException e) {
				System.out.println("Invalid Input");
			}
		}		
	}
	
	private void initHero() {
		//initializing hero
		heroController = new HeroController();
		hero = heroController.getHero();
	}
	
	//gamePlay in progress
	private void gameplay() {
		String str = "";
		initHero();
		
		System.out.printf("\n***********************\nGame In Progress\n***********************\n0 - exit\n");
		while (true) {
			System.out.printf("w - Up\n"
					+ "s - Down\n"
					+ "a - Left\n"
					+ "d - Right\n");
			
			showHero();
			System.out.printf("\ninput: ");
			str = wc.userInput();
			if (str.trim().equals("0")) {
				System.out.printf("GOOD BYE\n");
				System.exit(0);
			}else if (str.trim().equals("a")) {//down
				hero = heroController.moveHero("UP");//up
				encounter();
//				if (heroController.getComplet()) {
//					System.out.printf("game won");
//				}
			}else if (str.trim().equals("d")) {//up
				hero = heroController.moveHero("DOWN");//down
				encounter();
//				if (heroController.getComplet()) {
//					System.out.printf("game won");
//				};
			}else if (str.trim().equals("w")) {//left
				hero = heroController.moveHero("RIGHT");//right
				encounter();
//				if (heroController.getComplet()) {
//					System.out.printf("game won");
//				}
			}else if (str.trim().equals("s")) {//right
				hero = heroController.moveHero("LEFT");//left
				encounter();
//				if (heroController.getComplet()) {
//					System.out.printf("game won");
//				}
			}else System.out.printf("\ninvalid input!!!!!\n");
			levelComplete();
		}
	}
	
	//show hero
	private void showHero() {
		for (int i = 0; i < WorldContriller.getWorldSize(); i++) {
			for (int j = 0; j < WorldContriller.getWorldSize(); j++) {
				if (hero[i][j] == 1)
					System.out.printf("@ ");
				else if (hero[i][j] == -2)
					System.out.printf("$ ");
				else if (hero[i][j] > 50)
					System.out.printf("# ");
				else
					System.out.printf("  ");
			}
			System.out.printf("\n");
		}
	}
	
	
	//villain encounter
	private void encounter() {
		
		if (heroController.getFight()) {
			System.out.println("\nVILLAIN HAS BEEN ENCOUNTERED!!\n\n1\tFight\n2\tRun");
			String str = "";
			
			while (true) {
				try {
					System.out.printf("\nAnswer: ");
					str = wc.userInput();
					if (str.trim().equals("1"))
					{
						//Fighting
						if (heroController.getWin() == false) {
							System.out.printf("You Have lost the Fight\n");
							System.exit(0);
						}else {//won fight
							System.out.println("\nVICTORY\n\n1\tTakeAward\n2\tLeave Award\naward: " + (award = heroController.getAward()));
							if (award.trim().equals("none") == false)
								award();
						}
						break;
					}else if(str.trim().equals("2")) {
						//running
						hero = heroController.runner();
						if (!heroController.getRun()) {
							if (heroController.getWin() == false) {
								System.out.printf("You cannot run\nforced to fight\nYou Have lost the Fight\n");
								System.exit(0);
							}else {
								System.out.printf("You cannot run\nforced to fight\nYou Have won the Fight\naward: No award for escape attemp");
								break;
							}
						}else {
							showHero();
							break;				
						}
					}else 
						System.out.println("Invalid Pick");
				}catch(NumberFormatException e) {
					System.out.println("Invalid Input");
				}
			}
		}
	}
	
	//take award
	private void award() {
			String str = "";
			
			while (true) {
				try {
					System.out.printf("\nAnswer: ");
					str = wc.userInput();
					if (str.trim().equals("1"))
					{
						//taking award
						WorldContriller.getAward(award);
						break;
					}else if(str.trim().equals("2")) {
						//leaving award
						break;
					}else 
						System.out.println("Invalid Pick");
				}catch(NumberFormatException e) {
					System.out.println("Invalid Input");
				}
			}
			
	}
	
	//checking if villain has been encountered 
	private void levelComplete() {
		if (heroController.getComplet()) {
			String str = "";
			
			System.out.println("\nLEVEl COMPLETE\n0\tExit\n1\tNext");
			while (true) {
				try {
					System.out.printf("\nAnswer: ");
					str = wc.userInput();
					if (str.trim().equals("1"))
					{
						//accept next level
						WorldContriller.setWorld();
						initHero();
						break;
					}else 
						System.out.println("Invalid Pick");
				}catch(NumberFormatException e) {
					System.out.println("Invalid Input");
				}
			}
		}
	}
	
	//get heros
	private void listHero(String[] hero) {
		int i = 1;
		for (String h: hero) {
			System.out.println("\t" + i + " " + h);
			i++;
		}
	}
}
