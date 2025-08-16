package fiap.adj.fase3.tech_challenge_hospital.infrastructure.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityEndpoint {

    @GetMapping("/public")
    public String publicRoute() {
        return "Rota Pública";
    }

    @GetMapping("/private")
    public String privateRoute() {
        return "Rota Privada";
    }
}
