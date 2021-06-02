package sample.service;

import javafx.collections.ObservableList;
import sample.entity.Worker;

import java.io.File;
import java.io.IOException;

public interface DeserializationService {
    ObservableList<Worker> open(File file) throws IOException, ClassNotFoundException;
}
