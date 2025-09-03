package fiap.adj.fase3.tech_challenge_hospital.application.dtos.internal;

import java.time.LocalDateTime;

public record ConsultaDto(Long id, LocalDateTime dataHora, String status, MedicoDto medico, PacienteDto paciente) {
}
