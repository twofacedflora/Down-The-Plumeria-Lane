package downtheplumerialane;

import java.awt.event.*;
import javax.swing.*;

public class InGameInterfaceController {

	private Window window;
	private KeyMap keyMap;
	private InGameInterface inGameInterface;
	private MapContainer mapContainer;

	public InGameInterfaceController(Window w, InGameInterface itf) {
		window = w;
		inGameInterface = itf;
		mapContainer = itf.getActiveMap();
		keyMap = new KeyMap(itf);
		keyMap.mapAction(KeyMap.Command.UP, upAction);
		keyMap.mapAction(KeyMap.Command.DOWN, downAction);
		keyMap.mapAction(KeyMap.Command.LEFT, leftAction);
		keyMap.mapAction(KeyMap.Command.RIGHT, rightAction);
		keyMap.mapAction(KeyMap.Command.CONFIRM, confirmAction);
		keyMap.mapAction(KeyMap.Command.BACK, backAction);
	}

	Action upAction = new AbstractAction() {
		public void actionPerformed(ActionEvent evt) {
			mapContainer.moveSourceUp();
			inGameInterface.repaint();
		}
	};

	Action downAction = new AbstractAction() {
		public void actionPerformed(ActionEvent evt) {
			mapContainer.moveSourceDown();
			inGameInterface.repaint();
		}
	};

	Action leftAction = new AbstractAction() {
		public void actionPerformed(ActionEvent evt) {
			mapContainer.moveSourceLeft();
			inGameInterface.repaint();
		}
	};

	Action rightAction = new AbstractAction() {
		public void actionPerformed(ActionEvent evt) {
			mapContainer.moveSourceRight();
			inGameInterface.repaint();
		}
	};

	Action confirmAction = new AbstractAction() {
		public void actionPerformed(ActionEvent evt) {}
	};

	Action backAction = new AbstractAction() {
		public void actionPerformed(ActionEvent evt) {
			window.switchInterface("paused");
		}
	};
}
