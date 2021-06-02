package sample.context;

import java.io.File;

public enum Plugins {

    XML_TO_JSON_PLUGIN("convertXmlToJson.txt", false, false),
    ZIP_PLUGIN("zip.txt", false, false);

    private static final String path = "plugins/";

    private final String nameFile;
    private Boolean isLoad;
    private Boolean isInclude;

    Plugins(String nameFile, Boolean isLoad, Boolean isInclude) {
        this.nameFile = nameFile;
        this.isLoad = isLoad;
        this.isInclude = isInclude;
    }

    public String getNameFile() {
        return nameFile;
    }

    public Boolean getLoad() {
        return isLoad;
    }

    public void setLoad(Boolean load) {
        isLoad = load;
    }

    public Boolean getInclude() {
        return isInclude;
    }

    public void setInclude(Boolean include) {
        isInclude = include;
    }

    public static Boolean isExistsPlugin(Plugins plugin) {
        if (new File(path + plugin.getNameFile()).exists()) {
            plugin.setLoad(true);
            return true;
        } else {
            plugin.setLoad(false);
        }
        return false;
    }

}
