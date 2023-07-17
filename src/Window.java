import java.awt.GridLayout;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JFrame;

class Window extends JFrame {
	private static final long serialVersionUID = -2542001418764869760L;
	public static ArrayList<ArrayList<DataOfSquare>> Grid;
	public static int width = 50;
	public static int height = 50;

	public Window() {

		// Creates the arraylist that'll contain the threads
		Grid = new ArrayList<ArrayList<DataOfSquare>>();
		ArrayList<DataOfSquare> data;

		// Creates Threads and its data and adds it to the arrayList
		for (int i = 0; i < width; i++) {
			data = new ArrayList<DataOfSquare>();
			for (int j = 0; j < height; j++) {
				DataOfSquare c = new DataOfSquare(2);
				data.add(c);
			}
			Grid.add(data);
		}

		// Setup the panel layout
		getContentPane().setLayout(new GridLayout(width, height, 0, 0));

		// Start & pauses all threads, then adds every square of each thread to the
		// panel
		for (int i = 0; i < width; i++) {
			for (int j = 0; j < height; j++) {
				getContentPane().add(Grid.get(i).get(j).square);
			}
		}

		// Initial position of the snake
		Tuple position = new Tuple(10, 10);
		// passing this value to the controller
		ThreadsController controller = new ThreadsController(position);
		
		// Start the game
		controller.start();

		// Link the window to Keyboard class
		this.addKeyListener((KeyListener) new Keyboard());
	}
}
