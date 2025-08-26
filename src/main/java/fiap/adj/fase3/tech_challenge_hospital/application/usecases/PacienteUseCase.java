package fiap.adj.fase3.tech_challenge_hospital.application.usecases;

import fiap.adj.fase3.tech_challenge_hospital.application.dtos.request.PacienteRequestDto;
import fiap.adj.fase3.tech_challenge_hospital.application.dtos.response.PacienteResponseDto;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.ports.input.PacienteInputPort;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.ports.output.PacienteOutputPort;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PacienteUseCase implements PacienteInputPort {

    @Override
    public PacienteResponseDto criar(PacienteRequestDto requestDto, PacienteOutputPort outputPort) {
        return new PacienteResponseDto(1L, requestDto.getNome());
    }

    @Override
    public PacienteResponseDto consultar(UUID pacienteId, PacienteOutputPort outputPort) {
        return new PacienteResponseDto(1L, "Mario Car");
    }
}
