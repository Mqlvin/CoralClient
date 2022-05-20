package net.coral.client.file;

import net.coral.client.Coral;

import java.io.File;

public class ResourceHandler {
    public static final File MODULE_CONFIG_DIR = new File("./" + Coral.NAME.toLowerCase() + "/modules/");

    public static void generateDirectoryStructure() {
        MODULE_CONFIG_DIR.mkdirs();
    }

    public static File getModuleConfigLocation(String id) {
        return new File(MODULE_CONFIG_DIR + "/" + id + ".config");
    }
}
