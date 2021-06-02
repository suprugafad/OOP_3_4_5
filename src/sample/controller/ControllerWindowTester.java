package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;
import sample.patterns_lab5.TesterAdapter;
import sample.service.AboutWorkService;
import sample.service.BufferService;
import sample.entity.Tester;

public class ControllerWindowTester implements EditController<Tester> {
    @FXML
    private ChoiceBox<Tester.TypeTester> choiceBox;
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
        choiceBox.getItems().add(Tester.TypeTester.ANALYTICAL_TESTER);
        choiceBox.getItems().add(Tester.TypeTester.DEVELOPER_TESTER);
        choiceBox.getItems().add(Tester.TypeTester.USER_TESTER);
        if (buffer.getCurrentAction().equals("edit")) {
            Tester tester = (Tester) buffer.getWorker();
            inputFirstname.setText(tester.getFirstname());
            inputLastname.setText(tester.getLastname());
            inputCurrentProject.setText(tester.getCurrentProject());
            inputDecryption.setText(tester.getDecryption());
            choiceBox.setValue(tester.getTypeTester());
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
    public Tester getEntity() {
        Tester tester = new Tester();
        tester.setFirstname(inputFirstname.getText());
        tester.setLastname(inputLastname.getText());
        tester.setCurrentProject(inputCurrentProject.getText());
        tester.setTypeTester(choiceBox.getValue());
        tester.setDecryption(inputDecryption.getText());
        tester.setAboutWork(workService.printAboutWork(new TesterAdapter(tester)));
        return tester;
    }
}
