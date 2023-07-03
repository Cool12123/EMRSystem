package application;

import javafx.scene.control.Label;

public class WarningSystem {
	public Label warningMessage;
	WarningSystem (String messageContent) {
		this.warningMessage = new Label(messageContent);
		warningMessage.setStyle("-fx-font-family: Times New Roman; -fx-font-size: 30;");
	}
}