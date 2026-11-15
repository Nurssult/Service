package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        String port = System.getenv().getOrDefault("PORT", "8080");
        System.setProperty("server.port", port);
        SpringApplication.run(App.class, args);
    }
}
