package appline;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropsSettings {
    private Properties properties = new Properties();
    private static PropsSettings INSTANCE = null;

    private PropsSettings() {
        try {
            properties.load(new FileInputStream("src//test//resources//.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static PropsSettings getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PropsSettings();
        }
        return INSTANCE;
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public Properties getProperties() {
        return properties;
    }
}

