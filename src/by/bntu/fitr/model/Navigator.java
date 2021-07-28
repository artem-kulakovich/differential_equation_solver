package by.bntu.fitr.model;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


import java.io.IOException;

public class Navigator {

    public void navigate(Button navigateButton, String path) {
        try {
            Stage closeWindow = (Stage) navigateButton.getScene().getWindow();
            Stage stage = new Stage();

            Parent root = FXMLLoader.load(getClass().getResource(path));
            stage.setScene(new Scene(root));

            stage.show();
            closeWindow.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
