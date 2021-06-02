package sample.service.impl.deserialization;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.entity.Worker;
import sample.service.DeserializationService;

import java.beans.XMLDecoder;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

public class XmlDeserializationMethod implements DeserializationService {
    @Override
    public ObservableList<Worker> open(File file) throws IOException {
        FileInputStream fis = new FileInputStream(file);
        XMLDecoder decoder = new XMLDecoder(fis);
        @SuppressWarnings("unchecked")
        List<Worker> list = (List<Worker>) decoder.readObject();
        decoder.close();
        fis.close();

        ObservableList<Worker> observableList = FXCollections.observableArrayList();
        observableList.addAll(list);
        return observableList;
    }
}
