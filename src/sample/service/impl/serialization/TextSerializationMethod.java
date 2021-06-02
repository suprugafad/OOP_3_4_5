package sample.service.impl.serialization;

import javafx.collections.ObservableList;
import sample.entity.Worker;
import sample.service.SerializationService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class TextSerializationMethod implements SerializationService {
    @Override
    public void save(ObservableList<? extends Worker> observableList, File file) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(new ArrayList<>(observableList));
        objectOutputStream.close();
    }

}
