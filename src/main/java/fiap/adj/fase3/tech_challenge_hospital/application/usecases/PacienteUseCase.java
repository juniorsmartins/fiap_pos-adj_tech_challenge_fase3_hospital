package fiap.adj.fase3.tech_challenge_hospital.application.usecases;

import fiap.adj.fase3.tech_challenge_hospital.application.dtos.request.PacienteRequestDto;
import fiap.adj.fase3.tech_challenge_hospital.application.dtos.response.PacienteResponseDto;
import fiap.adj.fase3.tech_challenge_hospital.application.mappers.PacienteMapper;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.ports.input.PacienteInputPort;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.ports.output.PacienteOutputPort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PacienteUseCase implements PacienteInputPort {

    @Override
    public PacienteResponseDto criar(PacienteRequestDto requestDto, PacienteOutputPort outputPort) {
        return Optional.ofNullable(requestDto)
                .map(PacienteMapper::converterParaPaciente)
                .map(PacienteMapper::converterParaDto)
                .map(outputPort::criar)
                .orElseThrow();
    }

    @Override
    public PacienteResponseDto consultar(Long pacienteId, PacienteOutputPort outputPort) {
        return outputPort.findById(pacienteId);
    }
}
