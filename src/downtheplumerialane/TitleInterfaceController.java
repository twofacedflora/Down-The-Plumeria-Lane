package downtheplumerialane;

import java.awt.event.*;
import javax.swing.*;

public class TitleInterfaceController {

	private Window window;
	private ControlMap keyMap;
	private TitleInterface titleInterface;

	public TitleInterfaceController(Window w, TitleInterface itf) {
		window = w;
		titleInterface = itf;
		keyMap = new ControlMap(itf);
		keyMap.mapAction(ControlMap.Command.UP, upAction);
		keyMap.mapAction(ControlMap.Command.DOWN, downAction);
		keyMap.mapAction(ControlMap.Command.CONFIRM, confirmAction);
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
