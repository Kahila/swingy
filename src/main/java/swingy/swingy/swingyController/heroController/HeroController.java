package swingy.swingy.swingyController.heroController;

import java.util.Random;

import swingy.swingy.swingyController.worldController.WorldContriller;

public class HeroController {
	private int[][] hero = null;
	private int[][] tmpHero = null;
	private int lR = 0, lC = 0;
	private boolean fight = false;
	private boolean win = false;
	private boolean run = true;
	private boolean levelComplete = false;
	private String award[] = {"PHOENIX-BUSTER", "HULKBUSTER", "ULTRON", "Infinity Gauntlet"};
	
	//initializing the hero
	private void initHero(){
		int size = WorldContriller.getWorldSize();
		hero = new int[size][size];
		tmpHero = new int[size][size];
		for (int r = 0; r < size; r++) {
			for (int c = 0; c < size; c++) {
				int random = rand(100);
				if ((c == 0 && r != 0) || (c == (size -1) && r != (size -1))){
					hero[r][c] = -2;
					tmpHero[r][c] = -2;
				}else if (r == 0 || (r == (size -1))) {
					hero[r][c] = -2;
					tmpHero[r][c] = -2;
				}else if (random > 50) {
					hero[r][c] = random; //upper bound of level one
					tmpHero[r][c] = random;
				}else {
					hero[r][c] = 0;
					tmpHero[r][c] = 0;
				}
			}
		}
		hero[size/2][size/2] = 1;
	}
	
	//method for returning random number
	private int rand(int maxBound) {
		Random rand = new Random();
		int random = rand.nextInt(maxBound);
		return (random);
	}
	
	//moving the hero
	public int[][] moveHero(String direction) {
		int size = WorldContriller.getWorldSize();
		
		for(int r = 0; r < size; r++) {
			for (int c = 0; c < size; c++) {
				if (hero[r][c] == 1) {//checking for hero position
					fight = false;
					win = false;
					lR = r;
					lC = c;
					if (direction.trim().equals("UP")) {
						complete(r,c - 1);
						checkCondition(r, c - 1);
							hero[r][c - 1] = 1;
							hero[r][c] = 0;	
						return hero;
					}else if (direction.trim().equals("DOWN")) {
						complete(r,c + 1);
						checkCondition(r, c + 1);
							hero[r][c + 1] = 1;
							hero[r][c] = 0;
						return hero;
					}else if (direction.trim().equals("LEFT")) {
						complete(r + 1,c);
						checkCondition(r + 1, c);
							hero[r + 1][c] = 1;
							hero[r][c] = 0;	
						return hero;
					}else if (direction.trim().equals("RIGHT")) {
						complete(r - 1,c);
						checkCondition(r - 1, c);
						hero[r - 1][c] = 1;
						hero[r][c] = 0;							
						return hero;
					}
				}
			}
		}
		return (hero);
	}

	private void checkCondition(int r, int c) {
		if (hero[r][c] > 50) {
			this.fight = true;
			int rand = rand(200);
			if (rand >= 0) {//////set to 75
				win = true;
			}else {
				win = false;
			}
		}
	}
	
	public int[][] runner() {
			run = false;
			int rand = rand(100);
			if (rand >= 50) {
				run = false;
				return hero;
			}else {
				find();
				return hero;
			}	
	}

	//find and remove old heroes
	private void find() {
		int size = WorldContriller.getWorldSize();
		
		for (int r = 0; r < size; r++) {
			for (int c = 0; c < size; c++) {
				if (hero[r][c] == 1) {
					hero[r][c] = 0;
				}else {
					hero[r][c] = tmpHero[r][c];
				}
			}
		}
		hero[lR][lC] = 1;
		
		for (int r = 0; r < size; r++) {
			for (int c = 0; c < size; c++) {
				System.out.printf("%d\t", hero[c][r]);
			}
			System.out.printf("\n");
		}
		System.out.printf("\n\n");
	}

	public String getAward() {
		int rand = rand(100);
		if (rand > 95)
			return (award[3]);
		else if (rand > 75)
			return (award[2]);
		else if (rand > 60)
			return (award[1]);
		else if (rand > 55)
			return (award[0]);
		return ("none");
	}
	
	//method to check if the level has been completed
	private boolean complete(int r, int c) {

		if (hero[r][c] == -2) {
			levelComplete = true;
			return (true);
		}
		levelComplete = false;
		return (false);
	}

	//getters
	public boolean getFight() {
		return (fight);
	}
	
	public boolean getComplet() {
		return (levelComplete);
	}
	
	public int[][] getHero() {
		initHero();
		return (hero);
	}
	
	public boolean getWin() {
		return (win);
	}
	
	public boolean getRun() {
		return (run);
	}
}
