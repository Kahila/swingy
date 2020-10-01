package swingyController.heroController;

import java.util.Random;

import swingyController.worldController.WorldContriller;

public class HeroController {
	private int[][] hero = null;
	private boolean fight = false;
	private boolean win = false;
	private boolean run = true;
	
	//initializing the hero
	private void initHero(){
		int size = WorldContriller.getWorldSize();
		hero = new int[size][size];
		for (int r = 0; r < size; r++) {
			for (int c = 0; c < size; c++) {
				int random = rand(100);
				if ((c == 0 && r != 0) || (c == (size -1) && r != (size -1))){
					hero[r][c] = -2;
				}else if (r == 0 || (r == (size -1))) {
					hero[r][c] = -2;
				}else if (random > 50) {
					hero[r][c] = random; //upper bound of level one
				}else {
					hero[r][c] = 0;			
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
					if (direction.trim().equals("UP")) {
						checkCondition(r, c - 1);
//						runner(r, c  - 1);
//						if (run) {
							hero[r][c - 1] = 1;
							hero[r][c] = 0;							
//						}
						return hero;
					}else if (direction.trim().equals("DOWN")) {
						checkCondition(r, c + 1);
//						runner(r, c + 1);
//						if (run) {
							hero[r][c + 1] = 1;
							hero[r][c] = 0;
//						}
						return hero;
					}else if (direction.trim().equals("LEFT")) {
						checkCondition(r + 1, c);
//						runner(r + 1, c);
//						if (run) {
							hero[r + 1][c] = 1;
							hero[r][c] = 0;							
//						}
						return hero;
					}else if (direction.trim().equals("RIGHT")) {
						checkCondition(r - 1, c);
//						runner(r - 1, c);
//						if (run) {
							hero[r - 1][c] = 1;
							hero[r][c] = 0;							
//						}
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
			if (rand >= 70) {
				win = true;
//				fight = false;
			}else {
				win = false;
			}
		}
	}
	
	private void runner(int r, int c) {
		if (hero[r][c] > 50) {
			//this.fight = true;
			int rand = rand(100);
			if (rand >= 50) {
				run = false;
			}else {
				run = true;
			}
		}
	}
	
	//getters
	public boolean getFight() {
		return (fight);
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
