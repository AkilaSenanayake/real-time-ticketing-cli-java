package iit.oop.cw.configurations;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class  ConfigProcess {

    public static void saveConfigToFile(Configuration config, String filepath) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(filepath)) { // Removed extra parentheses
            gson.toJson(config, writer);
            System.out.println("Saved configuration to " + filepath);
        } catch (IOException e) {
            System.err.println("Failed to save configuration: " + e.getMessage());
        }
    }

    public static Configuration loadConfigFromFile(String filepath) {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filepath)) { // Removed extra parentheses
            return gson.fromJson(reader, Configuration.class); // Fixed method name
        } catch (IOException e) {
            System.err.println("Failed to load configuration: " + e.getMessage());
            return null;
        }
    }
}
