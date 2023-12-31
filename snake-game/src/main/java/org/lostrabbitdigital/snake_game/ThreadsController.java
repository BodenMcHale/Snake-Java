package org.lostrabbitdigital.snake_game;

import java.util.ArrayList;

//Controls all the game logic .. most important class in this project.
public class ThreadsController extends Thread {
	ArrayList<ArrayList<TileData>> Tiles = new ArrayList<ArrayList<TileData>>();
	Tuple headSnakePos;
	int sizeSnake = 2;
	long speed = 75;
	public static int directionSnake;

	ArrayList<Tuple> positions = new ArrayList<Tuple>();
	Tuple foodPosition;

	// Constructor of ControllerThread
	ThreadsController(Tuple positionDepart) {
		// Get all the threads
		Tiles = Window.Grid;

		headSnakePos = new Tuple(positionDepart.x, positionDepart.y);
		directionSnake = 1;

		// TODO: What even is this, man?
		// !!! Pointer !!!!
		Tuple headPos = new Tuple(headSnakePos.getX(), headSnakePos.getY());
		positions.add(headPos);

		foodPosition = new Tuple(Window.height / 2, Window.width / 2);
		spawnFood(foodPosition);

	}

	// Run the game
	public void run() {
		while (true) {
			renderSnake();
			moveSnake(directionSnake);
			checkCollision();
			deleteTail();
			delay();
		}
	}

	// Delay between each move of the snake
	private void delay() {
		try {
			sleep(speed);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// Checking if the snake bites itself or is eating
	private void checkCollision() {
		Tuple posCritique = positions.get(positions.size() - 1);
		for (int i = 0; i <= positions.size() - 2; i++) {
			boolean biteItself = posCritique.getX() == positions.get(i).getX()
					&& posCritique.getY() == positions.get(i).getY();
			if (biteItself) {
				stopTheGame();
			}
		}

		boolean eatingFood = posCritique.getX() == foodPosition.y && posCritique.getY() == foodPosition.x;
		if (eatingFood) {
			System.out.println("Yummy!");
			sizeSnake = sizeSnake + 1;
			foodPosition = getFreeTile();

			spawnFood(foodPosition);
		}
	}

	// Stops The Game
	private void stopTheGame() {
		System.out.println("You have died, \n Score: #");
		while (true) {
			delay();
		}
	}

	// Put food in a position and displays it
	private void spawnFood(Tuple foodPositionIn) {
		Tiles.get(foodPositionIn.x).get(foodPositionIn.y).lightMeUp(1);
	}

	// return a position not occupied by the snake
	private Tuple getFreeTile() {
		Tuple p;
		int ranX = 0 + (int) (Math.random() * 19);
		int ranY = 0 + (int) (Math.random() * 19);
		p = new Tuple(ranX, ranY);
		for (int i = 0; i <= positions.size() - 1; i++) {
			if (p.getY() == positions.get(i).getX() && p.getX() == positions.get(i).getY()) {
				ranX = 0 + (int) (Math.random() * 19);
				ranY = 0 + (int) (Math.random() * 19);
				p = new Tuple(ranX, ranY);
				i = 0;
			}
		}
		return p;
	}
	
	// TODO: Rename this function
	// Moves the head of the snake and refreshes the positions in the arraylist
	// 1:right 2:left 3:top 4:bottom 0:nothing
	private void moveSnake(int dir) {
		switch (dir) {
			case 4:
				headSnakePos.ChangeData(headSnakePos.x, (headSnakePos.y + 1) % Window.width);
				positions.add(new Tuple(headSnakePos.x, headSnakePos.y));
				break;
			case 3:
				if (headSnakePos.y - 1 < 0) {
					headSnakePos.ChangeData(headSnakePos.x, Window.width - 1);
				} else {
					headSnakePos.ChangeData(headSnakePos.x, Math.abs(headSnakePos.y - 1) % Window.width);
				}
				positions.add(new Tuple(headSnakePos.x, headSnakePos.y));
				break;
			case 2:
				if (headSnakePos.x - 1 < 0) {
					headSnakePos.ChangeData(Window.height - 1, headSnakePos.y);
				} else {
					headSnakePos.ChangeData(Math.abs(headSnakePos.x - 1) % Window.height, headSnakePos.y);
				}
				positions.add(new Tuple(headSnakePos.x, headSnakePos.y));

				break;
			case 1:
				headSnakePos.ChangeData(Math.abs(headSnakePos.x + 1) % Window.height, headSnakePos.y);
				positions.add(new Tuple(headSnakePos.x, headSnakePos.y));
				break;
		}
	}

	// TODO: Rename this and explain
	// Refresh the tiles that needs to be
	private void renderSnake() {
		for (Tuple t : positions) {
			int y = t.getX();
			int x = t.getY();
			Tiles.get(x).get(y).lightMeUp(0);

		}
	}

	// Refreshes the tail of the snake, by removing the superfluous data in
	// positions arraylist
	// and refreshing the display of the things that is removed
	private void deleteTail() {
		// TODO: Rename currentPosition to something more accurate
		int currentPosition = sizeSnake;
		for (int i = positions.size() - 1; i >= 0; i--) {
			if (currentPosition == 0) {
				Tuple t = positions.get(i);
				Tiles.get(t.y).get(t.x).lightMeUp(2);
			} else {
				currentPosition--;
			}
		}
		currentPosition = sizeSnake;
		for (int i = positions.size() - 1; i >= 0; i--) {
			if (currentPosition == 0) {
				positions.remove(i);
			} else {
				currentPosition--;
			}
		}
	}
}
