package sample.controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.json.JSONException;
import sample.context.Plugins;
import sample.patterns_lab5.BinarySerializationMethodProxy;
import sample.patterns_lab5.TextSerializationMethodProxy;
import sample.patterns_lab5.XmlSerializationMethodProxy;
import sample.service.BufferService;
import sample.entity.TypeWorker;
import sample.entity.Worker;
import sample.service.AppService;
import sample.service.impl.deserialization.BinaryDeserializationMethod;
import sample.service.impl.serialization.BinarySerializationMethod;
import sample.service.DeserializationService;
import sample.service.SerializationService;
import sample.service.impl.deserialization.TextDeserializationMethod;
import sample.service.impl.deserialization.XmlDeserializationMethod;

import java.io.IOException;
import java.util.NoSuchElementException;

public class Controller {

    public TableColumn<Object, Object> firstnameColumn;
    public TableColumn<Object, Object> lastnameColumn;
    public TableColumn<Object, Object> decryptionColumn;
    public TableColumn<Object, Object> typeColumn;
    public TableColumn<Object, Object> aboutWorkColumn;
    public CheckBox checkBoxXMLtoJSON;
    public CheckBox checkBoxToZip;

    @FXML
    private MenuItem menuItemOpenList;
    @FXML
    private MenuItem menuItemSaveList;
    @FXML
    private RadioButton binaryMethod;
    @FXML
    private RadioButton xmlMethod;
    @FXML
    private RadioButton textMethod;
    @FXML
    private TableView<Worker> tableView;
    @FXML
    private ChoiceBox<String> choiceBox;

    private final BufferService buffer = BufferService.getInstance();

    @FXML
    void initialize() {
        // Start init
        initChoiceBox();
        initColumns();
        initEvents();
        initSerializationMethodMenu();
        initDeserializationMethodMenu();
        // End init

        checkBoxToZip.setOnAction(ActionEvent -> {
            Plugins.ZIP_PLUGIN.setInclude(checkBoxToZip.isSelected());
            System.out.println();
        });

        checkBoxXMLtoJSON.setOnAction(ActionEvent -> {
            Plugins.XML_TO_JSON_PLUGIN.setInclude(checkBoxXMLtoJSON.isSelected());
        });

        buffer.setTableView(tableView);
        buffer.getTableView().setItems(buffer.getObservableList());
        binaryMethod.setSelected(true);
    }

    @FXML
    private void addWorker() {
        try {
            buffer.setCurrentAction("add");
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("Window" + choiceBox.getValue() + ".fxml"));
            AppService.getInstance().createWindow(fxmlLoader);
        } catch (IOException e) {
            System.out.println("error:" + e);
        }
    }

    @FXML
    private void editWorker() {
        if (tableView.getSelectionModel().getSelectedIndex() != -1) {
            try {
                buffer.setCurrentAction("edit");
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(getClass().getResource("Window" + buffer.getWorker().getTypeWorker() + ".fxml"));
                AppService.getInstance().createWindow(fxmlLoader);
            } catch (IOException e) {
                System.out.println("error:" + e);
            }
        } else {
            AppService.getInstance().showErrorMessage("Select any worker!");
        }
    }

    @FXML
    private void deleteWorker() {
        if (AppService.getInstance().enterSelectDelete()) {
            try {
                ObservableList<Worker> studentSelected, allStudents;
                allStudents = tableView.getItems();
                studentSelected = tableView.getSelectionModel().getSelectedItems();
                studentSelected.forEach(allStudents::remove);
            } catch (NoSuchElementException e) {
                System.out.println("error:" + e);
            }
        }
    }

    private void initColumns() {
        tableView.setStyle("-fx-font-size: 16px");
        firstnameColumn.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        lastnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("typeWorker"));
        //decryptionColumn.setCellValueFactory(new PropertyValueFactory<>("decryption"));
        //aboutWorkColumn.setCellValueFactory(new PropertyValueFactory<>("aboutWork"));
    }

    private void initChoiceBox() {
        choiceBox.getItems().add(TypeWorker.PIZZACHEF.getName());
        choiceBox.getItems().add(TypeWorker.DEVELOPER.getName());
        choiceBox.getItems().add(TypeWorker.MANAGER.getName());
        choiceBox.getItems().add(TypeWorker.SUSHICHEF.getName());
        choiceBox.getItems().add(TypeWorker.TESTER.getName());
        choiceBox.setValue(TypeWorker.PIZZACHEF.getName());
    }

    private void initEvents() {
        TableView.TableViewSelectionModel<Worker> selectionModel = tableView.getSelectionModel();
        selectionModel.selectedItemProperty().addListener((observableValue, student1, t1) -> {
            try {
                buffer.setWorker(tableView.getSelectionModel().getSelectedItem());
            } catch (NullPointerException e) {
                System.out.println("error: " + e);
            }
        });
    }

    private void initSerializationMethodMenu() {
        menuItemSaveList.setOnAction(ActionEvent -> {
            SerializationService serializationService = new BinarySerializationMethod();
            if (xmlMethod.isSelected()) {
                serializationService = new XmlSerializationMethodProxy();
            }
            if (textMethod.isSelected()) {
                serializationService = new TextSerializationMethodProxy();
            }
            if (binaryMethod.isSelected()) {
                serializationService = new BinarySerializationMethodProxy();
            }
            try {
                serializationService.save(buffer.getObservableList(), AppService.getInstance().saveFile());
            } catch (IOException | JSONException e) {
                System.out.println("error: " + e);
            }
        });
    }

    private void initDeserializationMethodMenu() {
        menuItemOpenList.setOnAction(ActionEvent -> {
            DeserializationService deserializationService = new TextDeserializationMethod();
            if (xmlMethod.isSelected()) {
                deserializationService = new XmlDeserializationMethod();
            }
            if (textMethod.isSelected()) {
                deserializationService = new TextDeserializationMethod();
            }
            if (binaryMethod.isSelected()) {
                deserializationService = new BinaryDeserializationMethod();
            }
            try {
                buffer.setObservableList(deserializationService.open(AppService.getInstance().openFile()));
            } catch (IOException | ClassNotFoundException | NullPointerException e) {
                System.out.println("error: " + e);
            }
            for (int i = 0; i < buffer.getTableView().getItems().size(); i++) {
                buffer.getTableView().getItems().clear();
            }
            buffer.getTableView().setItems(buffer.getObservableList());
        });
    }


}
