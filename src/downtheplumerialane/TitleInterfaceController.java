package downtheplumerialane;

import java.awt.event.*;
import javax.swing.*;

public class TitleInterfaceController {

	private Window window;
	private KeyMap keyMap;
	private TitleInterface titleInterface;

	public TitleInterfaceController(Window w, TitleInterface itf) {
		window = w;
		titleInterface = itf;
		keyMap = new KeyMap(itf);
		keyMap.mapAction(KeyMap.Command.UP, upAction);
		keyMap.mapAction(KeyMap.Command.DOWN, downAction);
		keyMap.mapAction(KeyMap.Command.CONFIRM, confirmAction);
	}

	Action upAction = new AbstractAction() {
		public void actionPerformed(ActionEvent evt) {
			int pos = titleInterface.getSelectorPosition();
			if (pos == 0) {
				titleInterface.switchOption(2);
			} else {
				titleInterface.switchOption(pos - 1);
			}
		}
	};

	Action downAction = new AbstractAction() {
		public void actionPerformed(ActionEvent evt) {
			int pos = titleInterface.getSelectorPosition();
			if (pos == 2) {
				titleInterface.switchOption(0);
			} else {
				titleInterface.switchOption(pos + 1);
			}
		}
	};

	Action confirmAction = new AbstractAction() {
		public void actionPerformed(ActionEvent evt) {
			switch (titleInterface.getSelectorPosition()) {
				case 0:
					window.switchInterface("characternaming");
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
