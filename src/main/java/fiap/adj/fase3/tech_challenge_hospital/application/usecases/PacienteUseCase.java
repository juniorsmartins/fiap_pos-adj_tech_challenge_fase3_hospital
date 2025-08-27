package fiap.adj.fase3.tech_challenge_hospital.application.usecases;

import fiap.adj.fase3.tech_challenge_hospital.application.dtos.internal.PacienteDto;
import fiap.adj.fase3.tech_challenge_hospital.application.dtos.request.PacienteRequestDto;
import fiap.adj.fase3.tech_challenge_hospital.application.mappers.PacienteMapper;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.ports.input.PacienteInputPort;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.ports.output.PacienteOutputPort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PacienteUseCase implements PacienteInputPort {

    @Override
    public PacienteDto criar(PacienteRequestDto requestDto, PacienteOutputPort outputPort) {
        return Optional.ofNullable(requestDto)
                .map(PacienteMapper::converterRequestParaEntity)
                .map(PacienteMapper::converterEntityParaDto)
                .map(outputPort::criar)
                .orElseThrow();
    }
}
