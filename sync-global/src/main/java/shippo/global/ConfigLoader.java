package shippo.global;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    private Logger LOG = LoggerFactory.getLogger(ConfigLoader.class);
    static ConfigLoader instance = null;
    Properties properties = new Properties();

    public static ConfigLoader getInstance() {
        if (instance == null) {
            instance = new ConfigLoader();
        }
        return instance;
    }

    public ConfigLoader() {
        String currentPath = "";
        String confPath = "";
        try {
            currentPath = new File(".").getCanonicalPath();
            System.out.println("Current path:" + currentPath);
            confPath = currentPath + "/conf/config.properties";
            properties.load(new FileReader(confPath));
            System.out.println(properties);
        } catch (IOException e) {
            LOG.error("Load properties file error ",e);
        }
    }

    public Properties getProperties() {
        return properties;
    }
}
