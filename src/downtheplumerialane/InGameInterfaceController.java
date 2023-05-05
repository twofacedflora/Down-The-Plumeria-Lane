package downtheplumerialane;

import java.awt.event.*;
import javax.swing.*;

public class InGameInterfaceController {

	private Window window;
	private InGameInterface inGameInterface;
	private MapContainer mapContainer;

	public InGameInterfaceController(Window w, InGameInterface i) {
		window = w;
		inGameInterface = i;
		mapContainer = i.getActiveMap();
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
			window.switchInterface("title");
		}
	};

	public void setKeybinds() {
		inGameInterface
			.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
			.put(KeyStroke.getKeyStroke("UP"), "up");
		inGameInterface
			.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
			.put(KeyStroke.getKeyStroke("DOWN"), "down");
		inGameInterface
			.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
			.put(KeyStroke.getKeyStroke("LEFT"), "left");
		inGameInterface
			.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
			.put(KeyStroke.getKeyStroke("RIGHT"), "right");
		inGameInterface
			.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
			.put(KeyStroke.getKeyStroke("C"), "confirm");
		inGameInterface
			.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW)
			.put(KeyStroke.getKeyStroke("X"), "back");

		inGameInterface.getActionMap().put("up", upAction);
		inGameInterface.getActionMap().put("down", downAction);
		inGameInterface.getActionMap().put("left", leftAction);
		inGameInterface.getActionMap().put("right", rightAction);
		inGameInterface.getActionMap().put("confirm", confirmAction);
		inGameInterface.getActionMap().put("back", backAction);
	}
}
