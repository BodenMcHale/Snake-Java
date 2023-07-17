import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {

		// Initialise the window
		Window f1 = new Window();

		// Set window properties
		f1.setTitle("Lost Rabbit Digital - Snake");
		f1.setSize(600, 600);
		f1.setVisible(true);
		f1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
