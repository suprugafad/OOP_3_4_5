package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;
import sample.patterns_lab5.DeveloperAdapter;
import sample.service.AboutWorkService;
import sample.service.BufferService;
import sample.entity.Developer;

public class ControllerWindowDeveloper implements EditController<Developer> {

    @FXML
    private ChoiceBox<Developer.ProgramLanguage> choiceBox;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnEdit;
    @FXML
    private TextArea inputDecryption;
    @FXML
    private TextField inputLastname;
    @FXML
    private TextField inputCurrentProject;
    @FXML
    private TextField inputFirstname;
    private final BufferService buffer = BufferService.getInstance();
    private final AboutWorkService workService = AboutWorkService.getInstance();

    @FXML
    void initialize() {
        choiceBox.getItems().add(Developer.ProgramLanguage.CPP);
        choiceBox.getItems().add(Developer.ProgramLanguage.JAVA);
        choiceBox.getItems().add(Developer.ProgramLanguage.PHP);
        choiceBox.getItems().add(Developer.ProgramLanguage.PYTHON);

        if (buffer.getCurrentAction().equals("edit")) {
            Developer developer = (Developer) buffer.getWorker();
            inputFirstname.setText(developer.getFirstname());
            inputLastname.setText(developer.getLastname());
            inputCurrentProject.setText(developer.getCurrentProject());
            inputDecryption.setText(developer.getDecryption());
            choiceBox.setValue(developer.getProgramLanguage());
            btnAdd.setVisible(false);
        } else {
            btnEdit.setVisible(true);
        }
    }


    @Override
    public void add(InputEvent event) {
        buffer.getObservableList().add(getEntity());
        buffer.getTableView().setItems(buffer.getObservableList());
        ((Stage) (((Node) event.getSource()).getScene().getWindow())).close();
    }

    @Override
    public void edit(InputEvent event) {
        buffer.getObservableList().set(buffer.getTableView().getSelectionModel().getSelectedIndex(), getEntity());
        ((Stage) (((Node) event.getSource()).getScene().getWindow())).close();
    }

    @Override
    public Developer getEntity() {
        Developer developer = new Developer();
        developer.setFirstname(inputFirstname.getText());
        developer.setLastname(inputLastname.getText());
        developer.setCurrentProject(inputCurrentProject.getText());
        developer.setProgramLanguage(choiceBox.getValue());
        developer.setDecryption(inputDecryption.getText());
        developer.setAboutWork(workService.printAboutWork(new DeveloperAdapter(developer)));
        return developer;
    }


}
