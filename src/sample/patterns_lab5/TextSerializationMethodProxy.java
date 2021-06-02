package sample.patterns_lab5;

import javafx.collections.ObservableList;
import org.json.JSONException;
import sample.entity.Worker;
import sample.service.ConvertToZIPService;
import sample.service.SerializationService;
import sample.service.impl.serialization.TextSerializationMethod;

import java.io.File;
import java.io.IOException;

public class TextSerializationMethodProxy implements SerializationService {

    private final SerializationService serializationService = new TextSerializationMethod();

    @Override
    public void save(ObservableList<? extends Worker> observableList, File file) throws IOException, JSONException {

        serializationService.save(observableList, file);

        ConvertToZIPService.getInstance().convert(file);
    }
}
