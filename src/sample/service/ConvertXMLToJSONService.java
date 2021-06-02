package sample.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;
import sample.context.Plugins;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class ConvertXMLToJSONService {

    private static ConvertXMLToJSONService instance;
    private final static String path = "json/";

    public static ConvertXMLToJSONService getInstance() {
        if (instance == null) {
            instance = new ConvertXMLToJSONService();
        }
        return instance;
    }

    public void convert(File file) throws IOException, JSONException {
        String filename = file.getName().substring(0, file.getName().indexOf(".")) + ".json";
        if (Plugins.isExistsPlugin(Plugins.XML_TO_JSON_PLUGIN) && Plugins.XML_TO_JSON_PLUGIN.getInclude()) {
            byte[] bytes = Files.readAllBytes(file.toPath());
            String xml = new String(bytes);
            JSONObject object = XML.toJSONObject(xml);
            FileWriter fileWriter = new FileWriter(path + filename);

            fileWriter.write(object.toString());
            fileWriter.flush();
            fileWriter.close();

        } else if (!Plugins.XML_TO_JSON_PLUGIN.getInclude()){
            AppService.getInstance().showErrorMessage("File will be saved without XML_TO_JSON plugin!");
        }
    }
}
