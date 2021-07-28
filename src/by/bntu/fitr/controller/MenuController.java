package by.bntu.fitr.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import by.bntu.fitr.model.Navigator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MenuController {
    private Navigator navigator = new Navigator();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button mainBtn;

    @FXML
    private Button settingsBtn;

    @FXML
    private Button exitBtn;

    @FXML
    void exitFromProgramAction(MouseEvent event) {
        System.exit(1);
    }

    @FXML
    void navigateToMainAction(MouseEvent event) {
       navigator.navigate(mainBtn,"../view/main.fxml");

    }

    @FXML
    void navigateToSettingsAction(MouseEvent event) {
        navigator.navigate(settingsBtn,"../view/settings.fxml");
    }

    @FXML
    void initialize() {

    }


}
