package org.lostrabbitdigital.snake_game;

public class Tuple {
	public int x;
	public int y;
	public int xf;
	public int yf;

	public Tuple(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public void ChangeData(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	// TODO: What is xF?
	public int getXf() {
		return xf;
	}

	// TODO: What is yF?
	public int getYf() {
		return yf;
	}

}