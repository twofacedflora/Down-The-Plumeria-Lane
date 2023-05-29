package downtheplumerialane;

import java.awt.event.*;
import javax.swing.*;

public class CharacterNamingInterfaceController {

	private Window window;
	private ControlMap controlMap;
	private CharacterNamingInterface characterNamingInterface;
	private Keyboard keyboard;
	private TextEntryBox nameBox;

	public CharacterNamingInterfaceController(
		Window w,
		CharacterNamingInterface itf
	) {
		window = w;
		characterNamingInterface = itf;
		keyboard = itf.getKeyboard();
		nameBox = itf.getNameBox();
		controlMap = new ControlMap(itf);
		controlMap.mapAction(ControlMap.Command.UP, upAction);
		controlMap.mapAction(ControlMap.Command.DOWN, downAction);
		controlMap.mapAction(ControlMap.Command.LEFT, leftAction);
		controlMap.mapAction(ControlMap.Command.RIGHT, rightAction);
		controlMap.mapAction(ControlMap.Command.CONFIRM, confirmAction);
		controlMap.mapAction(ControlMap.Command.BACK, backAction);
	}

	Action upAction = new AbstractAction() {
		public void actionPerformed(ActionEvent evt) {
			keyboard.moveSelectorUp();
		}
	};

	Action downAction = new AbstractAction() {
		public void actionPerformed(ActionEvent evt) {
			keyboard.moveSelectorDown();
		}
	};

	Action leftAction = new AbstractAction() {
		public void actionPerformed(ActionEvent evt) {
			keyboard.moveSelectorLeft();
		}
	};

	Action rightAction = new AbstractAction() {
		public void actionPerformed(ActionEvent evt) {
			keyboard.moveSelectorRight();
		}
	};

	// BUG: trigger exception when namebox is empty
	Action confirmAction = new AbstractAction() {
		public void actionPerformed(ActionEvent evt) {
			int pos = keyboard.getSelectorPos();

			if (pos <= 25) {
				nameBox.enterCharacter(Key.getKeys().get(pos).getText().charAt(0));
			} else {
				switch (pos) {
					case 26:
						keyboard.toggleCaps();
						break;
					case 27:
						Player.getActivePlayer().setName(nameBox.getText());
						window.switchInterface("ingame");
						break;
					case 28:
						nameBox.deleteCharacter();
				}
			}
		}
	};

	Action backAction = new AbstractAction() {
		public void actionPerformed(ActionEvent evt) {
			window.switchInterface("title");
		}
	};
}
