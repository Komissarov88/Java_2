package hw4;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class Controller {
    @FXML
    public TextArea chat;
    @FXML
    public TextField messageField;

    @FXML
    public void sendMessage() {
        if (messageField.getLength() == 0) {
            messageField.requestFocus();
            return;
        }
        chat.appendText(messageField.getText() + "\n");
        messageField.clear();
        messageField.requestFocus();
    }

    @FXML
    public void onKeyPressed(KeyEvent keyEvent) {
        if (keyEvent.getCode().getCode() == 10) {
            sendMessage();
        }
    }
}
