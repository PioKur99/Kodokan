package pl.kodokan.fcp.server;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IsAliveController {

    @GetMapping("/isAlive")
    ResponseEntity<String> isAlive() {
        return ResponseEntity.ok("Alive and kickin'");
    }
}
