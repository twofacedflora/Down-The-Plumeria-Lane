package downtheplumerialane;

import javax.swing.*;

public class Main {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(
			new Runnable() {
				public void run() {
					new Window();
				}
			}
		);
	}
}
// TODO: i will implement savedata someday. yes
