package sample.service;

import sample.context.Plugins;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ConvertToZIPService {

    private static ConvertToZIPService instance;
    private static final String path = "zip/";

    public static ConvertToZIPService getInstance() {
        if (instance == null) {
            instance = new ConvertToZIPService();
        }
        return instance;
    }

    public void convert(File file) {
        if (Plugins.isExistsPlugin(Plugins.ZIP_PLUGIN) && Plugins.ZIP_PLUGIN.getInclude()) {
            String filename = file.getName().substring(0, file.getName().indexOf(".")) + ".zip";
            try (
                    ZipOutputStream zipOutputStream = new ZipOutputStream(new FileOutputStream(path + filename));
                    FileInputStream fileInputStream = new FileInputStream(file);
            ) {
                ZipEntry entry = new ZipEntry(file.getName());
                zipOutputStream.putNextEntry(entry);
                byte[] buffer = new byte[fileInputStream.available()];

                fileInputStream.read(buffer);
                zipOutputStream.write(buffer);
                zipOutputStream.closeEntry();
            } catch (Exception e) {
                System.out.println("error: " + e);
            }
        } else if (Plugins.ZIP_PLUGIN.getInclude()){
            AppService.getInstance().showErrorMessage("File will be saved without TO_ZIP plugin!");
        }
    }
}
