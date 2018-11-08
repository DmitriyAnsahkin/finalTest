package other;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SingletonForProperties {
    private final Properties properties = new Properties();
    private static SingletonForProperties INSTANCE = null;

    private SingletonForProperties(){
        try{
            properties.load(new FileInputStream(new File("src/test/resources/config/default.properties")));
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    public static SingletonForProperties getInstance() {
        if (INSTANCE == null){
            INSTANCE = new SingletonForProperties();
        }
        return INSTANCE;
    }

    public Properties getProperties() {
        return properties;
    }
}
