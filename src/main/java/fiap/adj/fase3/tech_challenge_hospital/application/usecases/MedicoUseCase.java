package fiap.adj.fase3.tech_challenge_hospital.application.usecases;

import fiap.adj.fase3.tech_challenge_hospital.application.dtos.internal.MedicoDto;
import fiap.adj.fase3.tech_challenge_hospital.application.dtos.request.MedicoRequestDto;
import fiap.adj.fase3.tech_challenge_hospital.domain.entities.Medico;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.ports.input.MedicoInputPort;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.ports.output.MedicoOutputPort;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.ports.output.RoleOutputPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MedicoUseCase implements MedicoInputPort {

    @Transactional
    @Override
    public MedicoDto criar(MedicoRequestDto request, MedicoOutputPort medicoOutputPort, RoleOutputPort roleOutputPort) {
        return Optional.ofNullable(request)
            .map(dto -> Medico.converterRequestParaEntity(dto, roleOutputPort))
            .map(Medico::converterEntityParaDto)
            .map(medicoOutputPort::salvar)
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
            .map(dto -> Medico.regraAtualizar(dto, requestDto))
            .map(Medico::converterEntityParaDto)
            .map(outputPort::salvar)
            .orElseThrow();
    }
}
