package sample.patterns_lab5;

import javafx.collections.ObservableList;
import org.json.JSONException;
import sample.entity.Worker;
import sample.service.ConvertToZIPService;
import sample.service.ConvertXMLToJSONService;
import sample.service.SerializationService;
import sample.service.impl.serialization.XmlSerializationMethod;

import java.io.File;
import java.io.IOException;

public class XmlSerializationMethodProxy implements SerializationService {

    private final SerializationService serializationService = new XmlSerializationMethod();

    @Override
    public void save(ObservableList<? extends Worker> observableList, File file) throws IOException, JSONException {

        serializationService.save(observableList, file);

        ConvertToZIPService.getInstance().convert(file);

        ConvertXMLToJSONService.getInstance().convert(file);
    }
}
