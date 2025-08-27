package fiap.adj.fase3.tech_challenge_hospital.application.usecases;

import fiap.adj.fase3.tech_challenge_hospital.application.dtos.internal.MedicoDto;
import fiap.adj.fase3.tech_challenge_hospital.application.dtos.request.MedicoRequestDto;
import fiap.adj.fase3.tech_challenge_hospital.application.mappers.MedicoMapper;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.ports.input.MedicoInputPort;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.ports.output.MedicoOutputPort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MedicoUseCase implements MedicoInputPort {

    @Override
    public MedicoDto criar(MedicoRequestDto requestDto, MedicoOutputPort outputPort) {
        return Optional.ofNullable(requestDto)
                .map(MedicoMapper::converterRequestParaEntity)
                .map(MedicoMapper::converterEntityParaDto)
                .map(outputPort::criar)
                .orElseThrow();
    }
}
