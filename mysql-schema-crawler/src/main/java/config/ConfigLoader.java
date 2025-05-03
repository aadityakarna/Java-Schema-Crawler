package config;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.DatabaseConfig;

import java.io.File;
import java.io.IOException;

public class ConfigLoader {
    public static DatabaseConfig loadConfig(String filePath) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(filePath), DatabaseConfig.class);
    }
}