package ph.kana.clock.ui.fxml;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.GridPane;

import java.nio.ByteBuffer;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.BitSet;
import java.util.List;

public class ClockController {

	@FXML
	private GridPane gridPane;

	private RadioButton[] hourBits   = new RadioButton[6];
	private RadioButton[] minuteBits = new RadioButton[7];
	private RadioButton[] secondBits = new RadioButton[7];

	public ClockController() {
		Platform.runLater(() -> {
			initializeBitRadioButtons();
			startClock();
		});
	}

	private void initializeBitRadioButtons() {
		List<Node> children = gridPane.getChildren();
		for (Node node : children) {
			if (node instanceof RadioButton) {
				int row = gridPane.getRowIndex(node);
				int column = gridPane.getColumnIndex(node) - 1;

				if (0 == row) {
					hourBits[column] = (RadioButton) node;
				} else if (1 == row) {
					minuteBits[column] = (RadioButton) node;
				} else if (2 == row) {
					secondBits[column] = (RadioButton) node;
				}
			}
		}
	}

	private void startClock() {
		Task clockTask = new Task<Void>() {
			@Override
			public Void call() throws Exception {
				for (;;) {
					LocalDateTime time = LocalDateTime.now();

					Platform.runLater(() -> {
						showToClock(hourBits, time.getHour());
						showToClock(minuteBits, time.getMinute());
						showToClock(secondBits, time.getSecond());
					});

					Thread.sleep(1000L);
				}
			}
		};
		Thread thread = new Thread(clockTask);
		thread.setDaemon(true);
		thread.start();
	}

	private void showToClock(RadioButton[] bitButtons, int value) {
		String binary = Integer.toBinaryString(value);
		int binaryLength = binary.length();

		for (int i = 0; i < bitButtons.length; i++) {
			RadioButton radioButton = bitButtons[i];
			boolean turnedOn = (i < binaryLength) && ('1' == binary.charAt(i));

			radioButton.setSelected(turnedOn);
		}
	}
}
