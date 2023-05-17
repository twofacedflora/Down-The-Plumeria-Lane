package downtheplumerialane;

import java.awt.event.*;
import javax.swing.*;

public class PausedInterfaceController {

	private Window window;
	private KeyMap keyMap;
	private PausedInterface pausedInterface;

	public PausedInterfaceController(Window w, PausedInterface itf) {
		window = w;
		pausedInterface = itf;
		keyMap = new KeyMap(itf);
		keyMap.mapAction(KeyMap.Command.UP, upAction);
		keyMap.mapAction(KeyMap.Command.DOWN, downAction);
		keyMap.mapAction(KeyMap.Command.CONFIRM, confirmAction);
		keyMap.mapAction(KeyMap.Command.BACK, backAction);
	}

	Action upAction = new AbstractAction() {
		public void actionPerformed(ActionEvent evt) {
			int pos = pausedInterface.getSelectorPosition();
			if (pos == 0) {
				pausedInterface.switchOption(3);
			} else {
				pausedInterface.switchOption(pos - 1);
			}
		}
	};

	Action downAction = new AbstractAction() {
		public void actionPerformed(ActionEvent evt) {
			int pos = pausedInterface.getSelectorPosition();
			if (pos == 3) {
				pausedInterface.switchOption(0);
			} else {
				pausedInterface.switchOption(pos + 1);
			}
		}
	};

	Action confirmAction = new AbstractAction() {
		public void actionPerformed(ActionEvent evt) {
			switch (pausedInterface.getSelectorPosition()) {
				case 0:
					window.switchInterface("ingame");
					break;
				case 1:
					// TODO: add link to user guide
					break;
				case 2:
					window.switchInterface("settings");
					break;
				case 3:
					window.switchInterface("title");
					break;
				default:
					break;
			}
		}
	};

	Action backAction = new AbstractAction() {
		public void actionPerformed(ActionEvent evt) {
			window.switchInterface("ingame");
		}
	};
}
