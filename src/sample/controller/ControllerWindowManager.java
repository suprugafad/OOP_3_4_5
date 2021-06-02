package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;
import sample.patterns_lab5.ManagerAdapter;
import sample.entity.Manager;
import sample.service.AboutWorkService;
import sample.service.BufferService;

public class ControllerWindowManager implements EditController<Manager> {

    public TextField inputFirstname;
    public TextField inputLastname;
    public TextArea inputDecryption;
    public TextField inputNameProject;
    public TextField inputCountPersonsInProject;
    public Button btnEdit;
    public Button btnAdd;

    private final BufferService buffer = BufferService.getInstance();
    private final AboutWorkService workService = AboutWorkService.getInstance();


    @FXML
    void initialize() {
        if (buffer.getCurrentAction().equals("edit")) {
            Manager manager = (Manager) buffer.getWorker();
            inputFirstname.setText(manager.getFirstname());
            inputLastname.setText(manager.getLastname());
            inputNameProject.setText(manager.getNameProject());
            inputCountPersonsInProject.setText(String.valueOf(manager.getCountPersonInProject()));
            inputDecryption.setText(manager.getDecryption());
            btnAdd.setVisible(false);
        } else {
            btnEdit.setVisible(false);
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
    public Manager getEntity() {
        Manager manager = new Manager();
        manager.setFirstname(inputFirstname.getText());
        manager.setLastname(inputLastname.getText());
        manager.setNameProject(inputNameProject.getText());
        manager.setCountPersonInProject(Integer.parseInt(inputCountPersonsInProject.getText()));
        manager.setDecryption(inputDecryption.getText());
        manager.setAboutWork(workService.printAboutWork(new ManagerAdapter(manager)));
        return manager;
    }


}
