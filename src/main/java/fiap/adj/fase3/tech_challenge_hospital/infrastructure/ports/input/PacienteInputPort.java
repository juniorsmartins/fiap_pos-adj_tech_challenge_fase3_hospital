package fiap.adj.fase3.tech_challenge_hospital.infrastructure.ports.input;

import fiap.adj.fase3.tech_challenge_hospital.application.dtos.request.PacienteRequestDto;
import fiap.adj.fase3.tech_challenge_hospital.application.dtos.response.PacienteResponseDto;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.ports.output.PacienteOutputPort;

public interface PacienteInputPort {

    PacienteResponseDto criar(PacienteRequestDto requestDto, PacienteOutputPort outputPort);

    PacienteResponseDto consultar(Long pacienteId, PacienteOutputPort outputPort);
}
