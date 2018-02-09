module ph.kana.clock {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;

	opens  ph.kana.clock.ui to javafx.graphics;
	opens  ph.kana.clock.ui.fxml to javafx.fxml;
}