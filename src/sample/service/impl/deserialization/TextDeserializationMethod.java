package sample.service.impl.deserialization;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.entity.Worker;
import sample.service.DeserializationService;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.List;

public class TextDeserializationMethod implements DeserializationService {
    @Override
    public ObservableList<Worker> open(File file) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        @SuppressWarnings("unchecked")
        List<Worker> list = (List<Worker>) objectInputStream.readObject();

        ObservableList<Worker> observableList = FXCollections.observableArrayList();
        observableList.addAll(list);
        return observableList;
    }
}
