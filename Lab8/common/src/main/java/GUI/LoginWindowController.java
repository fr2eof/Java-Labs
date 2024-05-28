package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class LoginWindowController {

    @FXML
    private VBox authVBox;

    @FXML
    private VBox regVBox;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button authButton;

    @FXML
    private Label registerLabel;

    @FXML
    private TextField registerUsernameField;

    @FXML
    private PasswordField registerPasswordField1;

    @FXML
    private PasswordField registerPasswordField2;

    @FXML
    private Button registerButton;

    @FXML
    private Label errorLabel;

    @FXML
    private Label authLabel;

    @FXML
    private ChoiceBox<String> langChoiceBox;

    @FXML
    public void initialize() {
        // Инициализация элементов, если необходимо
        langChoiceBox.getItems().addAll("English", "Русский", "Español");
        langChoiceBox.setValue("English"); // Устанавливаем значение по умолчанию
        showAuthMenu(); // Показываем меню авторизации по умолчанию
    }

    @FXML
    private void auth() {
        // Логика авторизации
        String username = usernameField.getText();
        String password = passwordField.getText();
        if (username.isEmpty() || password.isEmpty()) {
            errorLabel.setText("Please fill in all fields");
            errorLabel.setVisible(true);
        } else {
            // Реализация авторизации
            errorLabel.setText("");
            errorLabel.setVisible(false);
            System.out.println("Username: " + username + ", Password: " + password);
            // Добавьте здесь свою логику авторизации
        }
    }

    @FXML
    private void register() {
        // Логика регистрации
        String username = registerUsernameField.getText();
        String password1 = registerPasswordField1.getText();
        String password2 = registerPasswordField2.getText();
        if (username.isEmpty() || password1.isEmpty() || password2.isEmpty()) {
            errorLabel.setText("Please fill in all fields");
            errorLabel.setVisible(true);
        } else if (!password1.equals(password2)) {
            errorLabel.setText("Passwords do not match");
            errorLabel.setVisible(true);
        } else {
            // Реализация регистрации
            errorLabel.setText("");
            errorLabel.setVisible(false);
            System.out.println("Registered Username: " + username + ", Password: " + password1);
            // Добавьте здесь свою логику регистрации
        }
    }

    @FXML
    private void showAuthMenu() {
        authVBox.setVisible(true);
        regVBox.setVisible(false);
        errorLabel.setVisible(false);
    }

    @FXML
    private void showRegisterMenu() {
        authVBox.setVisible(false);
        regVBox.setVisible(true);
        errorLabel.setVisible(false);
    }

    @FXML
    private void underlineRegisterLabel() {
        registerLabel.setUnderline(true);
    }

    @FXML
    private void undoUnderlineRegisterLabel() {
        registerLabel.setUnderline(false);
    }

    @FXML
    private void underlineAuthLabel() {
        authLabel.setUnderline(true);
    }

    @FXML
    private void undoUnderlineAuthLabel() {
        authLabel.setUnderline(false);
    }
}
