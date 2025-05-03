package controller;

import config.ConfigLoader;
import model.*;
import service.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/crawler")
public class CrawlerController {

    @GetMapping("/schema")
    public ResponseEntity<List<TableModel>> getSchema() throws Exception {
        DatabaseConfig config = ConfigLoader.loadConfig("config.json");
        String url = "jdbc:mysql://" + config.getHost() + ":" + config.getPort() + "/" + config.getDatabase();
        Connection conn = DriverManager.getConnection(url, config.getUsername(), config.getPassword());

        DatabaseCrawler crawler = new DatabaseCrawler(conn);
        return ResponseEntity.ok(crawler.extractSchema());
    }

    @GetMapping("/models")
    public ResponseEntity<List<String>> getGeneratedModels() throws Exception {
        DatabaseConfig config = ConfigLoader.loadConfig("config.json");
        String url = "jdbc:mysql://" + config.getHost() + ":" + config.getPort() + "/" + config.getDatabase();
        Connection conn = DriverManager.getConnection(url, config.getUsername(), config.getPassword());

        DatabaseCrawler crawler = new DatabaseCrawler(conn);
        List<TableModel> tables = crawler.extractSchema();

        List<String> models = new ArrayList<>();
        for (TableModel table : tables) {
            models.add(ModelGenerator.generateModelCode(table));
        }
        return ResponseEntity.ok(models);
    }
}