package org.example;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthController {

    @GetMapping("/")
    public String home() {
        return "M1 HTTP Service";
    }

    @GetMapping("/healthz")
    public String health() {
        return "OK";
    }
}