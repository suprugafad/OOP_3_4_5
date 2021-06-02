package sample.service;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.entity.Worker;

import java.io.File;
import java.io.IOException;

public class AppService {

    private static AppService instance;

    public static AppService getInstance() {
        if (instance == null) {
            instance = new AppService();
        }
        return instance;
    }

    // Всплывающие окно, говорящее об ошибке
    public void showErrorMessage(String errorText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error message!");
        alert.setContentText(errorText);
        alert.showAndWait();
    }

    public File saveFile() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Текстовый файл", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        return fileChooser.showSaveDialog(null);
    }

    public File openFile() {
        FileChooser fileChooser = new FileChooser();
        try {
            return fileChooser.showOpenDialog(null);
        } catch (NullPointerException e) {
            System.out.println("error: " + e);
        }
        return null;
    }

    /**
     * Confirm delete {@link Worker}.
     *
     * @return {@link Boolean}
     */
    public boolean enterSelectDelete() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Do you really want to delete worker?");
        alert.setContentText("Confirm your selection");
        alert.showAndWait();
        return alert.getResult() == ButtonType.OK;
    }


    public void createWindow(FXMLLoader fxmlLoader) throws IOException {
        Scene scene = new Scene(fxmlLoader.load(), 214, 466);
        Stage stage = new Stage();
        stage.setTitle("Worker");
        stage.setScene(scene);
        stage.show();
    }

}
