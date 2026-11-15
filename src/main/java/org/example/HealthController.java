package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class HealthController {

    @Value("${GREETING:M1 HTTP Service}")
    private String greeting;

    @Value("${API_KEY:}")
    private String apiKey;

    @GetMapping("/")
    public String home() {
        return greeting;
    }

    @GetMapping("/healthz")
    public String health() {
        return "OK";
    }

    @GetMapping("/private")
    public ResponseEntity<String> privateEndpoint(
            @RequestHeader(value = "X-API-Key", required = false) String providedKey) {

        if (apiKey.isBlank() || !Objects.equals(apiKey, providedKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Unauthorized");
        }

        return ResponseEntity.ok("Authorized");
    }
}
