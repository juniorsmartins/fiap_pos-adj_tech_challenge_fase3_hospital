package fiap.adj.fase3.tech_challenge_hospital.application.usecases;

import fiap.adj.fase3.tech_challenge_hospital.application.dtos.internal.PacienteDto;
import fiap.adj.fase3.tech_challenge_hospital.application.dtos.request.PacienteRequestDto;
import fiap.adj.fase3.tech_challenge_hospital.application.mappers.PacienteMapper;
import fiap.adj.fase3.tech_challenge_hospital.domain.entities.Paciente;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.ports.input.PacienteInputPort;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.ports.output.PacienteOutputPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class PacienteUseCase implements PacienteInputPort {

    @Transactional
    @Override
    public PacienteDto criar(PacienteRequestDto requestDto, PacienteOutputPort outputPort) {
        return Optional.ofNullable(requestDto)
                .map(PacienteMapper::converterRequestParaEntity)
                .map(PacienteMapper::converterEntityParaDto)
                .map(outputPort::salvar)
                .orElseThrow();
    }

    @Transactional
    @Override
    public void apagarPorId(Long id, PacienteOutputPort outputPort) {
        outputPort.consultarPorId(id)
                .ifPresentOrElse(dto -> outputPort.apagarPorId(dto.id()), () -> {
                    throw new RuntimeException();
                });
    }

    @Override
    public PacienteDto atualizar(Long id, PacienteRequestDto requestDto, PacienteOutputPort outputPort) {
        return outputPort.consultarPorId(id)
                .map(dto -> Paciente.regraAtualizar(dto, requestDto))
                .map(PacienteMapper::converterEntityParaDto)
                .map(outputPort::salvar)
                .orElseThrow();
    }
}
