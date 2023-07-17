package org.lostrabbitdigital.snake_game;

import java.awt.Color;
import javax.swing.JPanel;

// TODO: Rename to more accurate name
public class TilePanel extends JPanel {

	private static final long serialVersionUID = 1L;

	public TilePanel(Color c) {
		this.setBackground(c);
	}

	public void ChangeColor(Color c) {
		this.setBackground(c);
		this.repaint();
	}

}
