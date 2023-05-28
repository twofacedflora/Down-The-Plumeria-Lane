package downtheplumerialane;

import java.awt.*;
import javax.swing.*;

public class SettingsMenu extends JPanel {

	protected int selectorPosition = 0;
	private boolean locked = false;

	protected SettingContainer selectedSetting;
	private SettingGroup group;

	public SettingsMenu(SettingGroup g) {
		group = g;
		setLayout(new VerticalGridLayout(4, 2));
		setPreferredSize(new Dimension(512, 288));
		setAlignmentX(CENTER_ALIGNMENT);
		setOpaque(false);
	}

	public void verifyLock() {
		if (selectedSetting.getActive()) {
			locked = true;
		} else {
			locked = false;
		}
	}

	public void reset() {
		removeSelectedState();
		selectorPosition = 0;
		selectedSetting = group.getSettings().get(selectorPosition).getContainer();
		setSelectedState();
	}

	public void moveSelectorUp() {
		if (!locked && !(selectorPosition % 4 == 0)) {
			removeSelectedState();
			selectorPosition--;
			selectedSetting =
				group.getSettings().get(selectorPosition).getContainer();
			setSelectedState();
		}
	}

	public void moveSelectorDown() {
		if (
			!locked &&
			!(selectorPosition % 4 == 3) &&
			(group.getSettings().size() > selectorPosition + 1)
		) {
			removeSelectedState();
			selectorPosition++;
			selectedSetting =
				group.getSettings().get(selectorPosition).getContainer();
			setSelectedState();
		}
	}

	public void moveSelectorLeft() {
		if (!locked) {
			if (selectorPosition > 3) {
				removeSelectedState();
				selectorPosition -= 4;
				selectedSetting =
					group.getSettings().get(selectorPosition).getContainer();
				setSelectedState();
			}
		} else {
			if (selectedSetting.getSetting() instanceof Setting.Scale) {
				((Setting.Scale) selectedSetting.getSetting()).decrementValue();
				((Setting.Scale) selectedSetting.getSetting()).updateJson();
				(
					(SettingContainer.ArrowControls) (selectedSetting.controlField)
				).value.setText(
						Integer.toString(
							((Setting.Scale) selectedSetting.getSetting()).getValue()
						)
					);
			}
		}
	}

	public void moveSelectorRight() {
		if (!locked) {
			if (
				selectorPosition < 4 &&
				(group.getSettings().size() >= selectorPosition + 5)
			) {
				removeSelectedState();
				selectorPosition += 4;
				selectedSetting =
					group.getSettings().get(selectorPosition).getContainer();
				setSelectedState();
			}
		} else {
			if (selectedSetting.getSetting() instanceof Setting.Scale) {
				((Setting.Scale) selectedSetting.getSetting()).incrementValue();
				((Setting.Scale) selectedSetting.getSetting()).updateJson();
				(
					(SettingContainer.ArrowControls) (selectedSetting.controlField)
				).value.setText(
						Integer.toString(
							((Setting.Scale) selectedSetting.getSetting()).getValue()
						)
					);
			}
		}
	}

	public void setSelectedState() {
		selectedSetting.showSelector();
		selectedSetting.invertColor();
	}

	public void removeSelectedState() {
		selectedSetting.hideSelector();
		selectedSetting.resetColor();
	}
}
