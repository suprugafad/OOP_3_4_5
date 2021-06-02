package sample.service;

import javafx.collections.ObservableList;
import org.json.JSONException;
import sample.entity.Worker;

import java.io.File;
import java.io.IOException;

public interface SerializationService {
    void save(ObservableList<? extends Worker> observableList, File file) throws IOException, JSONException;
}
