package sample.controller;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.stage.Stage;
import sample.patterns_lab5.PizzaChefAdapter;
import sample.service.AboutWorkService;
import sample.service.BufferService;
import sample.entity.PizzaChef;

public class ControllerWindowPizzaChef implements EditController<PizzaChef>{
    @FXML
    private TextField inputFirstname;
    @FXML
    private TextField inputLastname;
    @FXML
    private TextField inputLvlOfCooking;
    @FXML
    private ChoiceBox<Boolean> choiceBox;
    @FXML
    private TextArea inputDecryption;
    @FXML
    private Button btnAdd;
    @FXML
    private Button btnEdit;

    private final BufferService buffer = BufferService.getInstance();
    private final AboutWorkService workService = AboutWorkService.getInstance();


    @FXML
    void initialize() {
        choiceBox.getItems().add(Boolean.TRUE);
        choiceBox.getItems().add(Boolean.FALSE);
        choiceBox.setValue(Boolean.TRUE);

        if (buffer.getCurrentAction().equals("edit")) {
            PizzaChef pizzaChef = (PizzaChef) buffer.getWorker();
            inputFirstname.setText(pizzaChef.getFirstname());
            inputLastname.setText(pizzaChef.getLastname());
            inputDecryption.setText(pizzaChef.getDecryption());
            inputLvlOfCooking.setText(String.valueOf(pizzaChef.getLevelOfCooking()));
            choiceBox.setValue(pizzaChef.getKnowledgeOfItalicHistory());
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
    public PizzaChef getEntity() {
        PizzaChef pizzaChef = new PizzaChef();
        pizzaChef.setFirstname(inputFirstname.getText());
        pizzaChef.setLastname(inputLastname.getText());
        pizzaChef.setLevelOfCooking(Integer.parseInt(inputLvlOfCooking.getText()));
        pizzaChef.setKnowledgeOfItalicHistory(choiceBox.getValue());
        pizzaChef.setDecryption(inputDecryption.getText());
        pizzaChef.setAboutWork(workService.printAboutWork(new PizzaChefAdapter(pizzaChef)));
        return pizzaChef;
    }



}
