package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;
import sample.patterns_lab5.SushiChefAdapter;
import sample.service.AboutWorkService;
import sample.service.BufferService;
import sample.entity.SushiChef;

public class ControllerWindowSushiChef implements EditController<SushiChef> {
    public TextField inputFirstname;
    public TextField inputLastname;
    public TextArea inputDecryption;
    public TextField inputLvlOfCooking;
    public TextField inputLvlUsingChineseChopsticks;
    public TextField inputLvlOfSpeedCooking;
    public Button btnAdd;
    public Button btnEdit;

    private final BufferService buffer = BufferService.getInstance();
    private final AboutWorkService workService = AboutWorkService.getInstance();

    @FXML
    void initialize() {
        if (buffer.getCurrentAction().equals("edit")) {
            SushiChef accountant = (SushiChef) buffer.getWorker();
            inputFirstname.setText(accountant.getFirstname());
            inputLastname.setText(accountant.getLastname());
            inputDecryption.setText(accountant.getDecryption());
            inputLvlOfCooking.setText(String.valueOf(accountant.getLevelOfCooking()));
            inputLvlUsingChineseChopsticks.setText(String.valueOf(accountant.getLevelUsingChineseChopsticks()));
            inputLvlOfSpeedCooking.setText(String.valueOf(accountant.getLevelOfSpeedCooking()));
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
    public SushiChef getEntity() {
        SushiChef sushiChef = new SushiChef();
        sushiChef.setFirstname(inputFirstname.getText());
        sushiChef.setLastname(inputLastname.getText());
        sushiChef.setLevelOfCooking(Integer.parseInt(inputLvlOfCooking.getText()));
        sushiChef.setLevelUsingChineseChopsticks(Integer.parseInt(inputLvlUsingChineseChopsticks.getText()));
        sushiChef.setLevelOfSpeedCooking(Integer.parseInt(inputLvlOfSpeedCooking.getText()));
        sushiChef.setDecryption(inputDecryption.getText());
        sushiChef.setAboutWork(workService.printAboutWork(new SushiChefAdapter(sushiChef)));
        return sushiChef;
    }
}
