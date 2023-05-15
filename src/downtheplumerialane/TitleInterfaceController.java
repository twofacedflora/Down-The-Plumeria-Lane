package downtheplumerialane;

import java.awt.event.*;
import java.util.*;
import javax.swing.*;

public class TitleInterfaceController {

	private static int selectedOption = 0;

	private Window window;
	private KeyMap keyMap;
	private TitleInterface titleScreen;

	private ArrayList<JLabel> options;

	public TitleInterfaceController(
		Window w,
		TitleInterface itf,
		ArrayList<JLabel> o
	) {
		window = w;
		titleScreen = itf;
		options = o;
		keyMap = new KeyMap(itf);
		keyMap.mapAction(KeyMap.Command.UP, upAction);
		keyMap.mapAction(KeyMap.Command.DOWN, downAction);
		keyMap.mapAction(KeyMap.Command.CONFIRM, confirmAction);
	}

	public void setSelector(int i) {
		try {
			options
				.get(i)
				.setIcon(
					Window.createScaledImageIcon("interface/ticked_checkbox.png", 40)
				);
		} catch (NullPointerException | IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	public void resetOptionState(int i) {
		try {
			options
				.get(i)
				.setIcon(Window.createScaledImageIcon("interface/checkbox.png", 40));
		} catch (NullPointerException | IndexOutOfBoundsException e) {
			e.printStackTrace();
		}
	}

	Action upAction = new AbstractAction() {
		public void actionPerformed(ActionEvent evt) {
			resetOptionState(selectedOption);
			if (selectedOption == 0) selectedOption = 2; else selectedOption--;
			setSelector(selectedOption);
		}
	};

	Action downAction = new AbstractAction() {
		public void actionPerformed(ActionEvent evt) {
			resetOptionState(selectedOption);
			if (selectedOption == 2) selectedOption = 0; else selectedOption++;
			setSelector(selectedOption);
		}
	};

	Action confirmAction = new AbstractAction() {
		public void actionPerformed(ActionEvent evt) {
			switch (selectedOption) {
				case 0:
					window.switchInterface("namechar");
					break;
				case 1:
					window.switchInterface("settings");
					break;
				case 2:
					System.exit(0);
					break;
				default:
					break;
			}
		}
	};
}
