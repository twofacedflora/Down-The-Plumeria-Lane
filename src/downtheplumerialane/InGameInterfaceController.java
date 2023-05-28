package downtheplumerialane;

import java.awt.event.*;
import javax.swing.*;

public class InGameInterfaceController {

	private Window window;
	private ControlMap keyMap;
	private InGameInterface inGameInterface;
	private MapContainer mapContainer;

	public InGameInterfaceController(Window w, InGameInterface itf) {
		window = w;
		inGameInterface = itf;
		mapContainer = itf.getActiveMap();
		keyMap = new ControlMap(itf);
		keyMap.mapAction(ControlMap.Command.UP, upAction);
		keyMap.mapAction(ControlMap.Command.DOWN, downAction);
		keyMap.mapAction(ControlMap.Command.LEFT, leftAction);
		keyMap.mapAction(ControlMap.Command.RIGHT, rightAction);
		keyMap.mapAction(ControlMap.Command.CONFIRM, confirmAction);
		keyMap.mapAction(ControlMap.Command.BACK, backAction);
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
