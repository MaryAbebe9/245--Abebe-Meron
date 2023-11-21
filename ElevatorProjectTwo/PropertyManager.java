import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyManager {
    private Properties properties;

    public PropertyManager() {
        properties = new Properties();
        loadDefaultProperties();loadDefaultProperties();
    }

    public void readProperties(String filePath) {
        try {
            properties.load(new FileInputStream(filePath));
        } catch (IOException e) {
            System.err.println("Property file not found. Using default properties.");
        }
    }

    private void loadDefaultProperties() {
        properties.setProperty("structures", "linked");
        properties.setProperty("floors", "32");
        properties.setProperty("passengers", "0.03");
        properties.setProperty("elevators", "1");
        properties.setProperty("elevatorCapacity", "10");
        properties.setProperty("duration", "500");
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
    
    
    public static void main(String[] args){
         Simulation sim = new Simulation(new PropertyManager());
         sim.simulate(0);
    }
}


 





