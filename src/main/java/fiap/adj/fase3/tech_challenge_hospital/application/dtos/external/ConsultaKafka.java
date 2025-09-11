package fiap.adj.fase3.tech_challenge_hospital.application.dtos.external;

import java.time.LocalDateTime;

public record ConsultaKafka(Long id, LocalDateTime dataHora, String status, String nomeMedico, String nomePaciente, String emailPaciente) {
}
