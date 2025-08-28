package fiap.adj.fase3.tech_challenge_hospital.application.usecases;

import fiap.adj.fase3.tech_challenge_hospital.application.dtos.internal.MedicoDto;
import fiap.adj.fase3.tech_challenge_hospital.application.dtos.request.MedicoRequestDto;
import fiap.adj.fase3.tech_challenge_hospital.application.mappers.MedicoMapper;
import fiap.adj.fase3.tech_challenge_hospital.domain.entities.Medico;
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
            .map(outputPort::salvar)
            .orElseThrow();
    }

    @Override
    public void apagarPorId(Long id, MedicoOutputPort outputPort) {
        outputPort.consultarPorId(id)
            .ifPresentOrElse(dto -> outputPort.apagarPorId(dto.id()), () -> {
                throw new RuntimeException();
            });
    }

    @Override
    public MedicoDto atualizar(Long id, MedicoRequestDto requestDto, MedicoOutputPort outputPort) {
        return outputPort.consultarPorId(id)
            .map(dto -> regraAtualizar(dto, requestDto))
            .map(MedicoMapper::converterEntityParaDto)
            .map(outputPort::salvar)
            .orElseThrow();
    }

    private Medico regraAtualizar(MedicoDto dto, MedicoRequestDto requestDto) {
        return Optional.ofNullable(requestDto)
            .map(MedicoMapper::converterRequestParaEntity)
            .map(entity -> {
                entity.setId(dto.id());
                return entity;
            })
            .orElseThrow();
    }
}
