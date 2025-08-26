package fiap.adj.fase3.tech_challenge_hospital.infrastructure.ports.output;

import fiap.adj.fase3.tech_challenge_hospital.application.dtos.response.PacienteResponseDto;

public interface PacienteOutputPort {

    PacienteResponseDto criar(PacienteResponseDto dto);

    PacienteResponseDto findById(Long id);
}
