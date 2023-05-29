package downtheplumerialane;

import java.awt.event.*;
import javax.swing.*;

public class InGameInterfaceController {

	private Window window;
	private ControlMap keyMap;
	private InGameInterface inGameInterface;
	private MapContainer mapContainer;
	private PlayerComponent playerComponent;

	public InGameInterfaceController(Window w, InGameInterface itf, Player p) {
		window = w;
		inGameInterface = itf;
		playerComponent = p.getComponent();
		mapContainer = itf.getActiveMap();
		keyMap = new ControlMap(itf);
		keyMap.mapAction(ControlMap.Command.UP, upAction);
		keyMap.mapAction(ControlMap.Command.DOWN, downAction);
		keyMap.mapAction(ControlMap.Command.LEFT, leftAction);
		keyMap.mapAction(ControlMap.Command.RIGHT, rightAction);
		keyMap.mapAction(ControlMap.Command.CONFIRM, confirmAction);
		keyMap.mapAction(ControlMap.Command.BACK, backAction);
	}

	// TODO: make player animation timer-based instead of input-based

	Action upAction = new AbstractAction() {
		public void actionPerformed(ActionEvent evt) {
			playerComponent.updateCurrentFrame("UP");
			mapContainer.moveSourceUp();
			inGameInterface.repaint();
		}
	};

	Action downAction = new AbstractAction() {
		public void actionPerformed(ActionEvent evt) {
			playerComponent.updateCurrentFrame("DOWN");
			mapContainer.moveSourceDown();
			inGameInterface.repaint();
		}
	};

	Action leftAction = new AbstractAction() {
		public void actionPerformed(ActionEvent evt) {
			playerComponent.updateCurrentFrame("LEFT");
			mapContainer.moveSourceLeft();
			inGameInterface.repaint();
		}
	};

	Action rightAction = new AbstractAction() {
		public void actionPerformed(ActionEvent evt) {
			playerComponent.updateCurrentFrame("RIGHT");
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
