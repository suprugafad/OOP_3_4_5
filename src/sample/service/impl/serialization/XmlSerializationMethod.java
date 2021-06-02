package sample.service.impl.serialization;

import javafx.collections.ObservableList;
import sample.entity.Worker;
import sample.service.SerializationService;

import java.beans.XMLEncoder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class XmlSerializationMethod implements SerializationService {
    @Override
    public void save(ObservableList<? extends Worker> observableList, File file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        XMLEncoder encoder = new XMLEncoder(fileOutputStream);
        encoder.setExceptionListener(e -> System.out.println("Exception! :" + e.toString()));
        encoder.writeObject(new ArrayList<>(observableList));
        encoder.close();
        fileOutputStream.close();
    }
}
