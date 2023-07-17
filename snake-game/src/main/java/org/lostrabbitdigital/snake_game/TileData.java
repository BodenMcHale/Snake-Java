package org.lostrabbitdigital.snake_game;

import java.util.ArrayList;
import java.awt.Color;

public class TileData {
	// Array which contains the colors
	ArrayList<Color> gameColors = new ArrayList<Color>();
	// 0 - Background
	// 1 - Food
	// 2 - Snake
	int color;
	TilePanel tile;

	// TODO: Change to strings instead of int for ease of understanding
	public TileData(int col) {
		// Add the colors to the array
		// Lets add the color to the arrayList
		gameColors.add(Color.GREEN);// Snake
		gameColors.add(Color.YELLOW); // Food
		gameColors.add(Color.DARK_GRAY); // Background
		color = col;

		// TODO: Explain this
		tile = new TilePanel(gameColors.get(color));
	}

	public void lightMeUp(int c) {
		tile.ChangeColor(gameColors.get(c));
	}
}
