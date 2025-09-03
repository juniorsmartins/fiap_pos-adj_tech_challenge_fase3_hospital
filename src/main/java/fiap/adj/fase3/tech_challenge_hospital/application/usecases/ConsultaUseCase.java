package fiap.adj.fase3.tech_challenge_hospital.application.usecases;

import fiap.adj.fase3.tech_challenge_hospital.application.dtos.internal.ConsultaDto;
import fiap.adj.fase3.tech_challenge_hospital.application.dtos.request.ConsultaRequestDto;
import fiap.adj.fase3.tech_challenge_hospital.domain.entities.Consulta;
import fiap.adj.fase3.tech_challenge_hospital.domain.entities.enums.ConsultaStatusEnum;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.ports.input.ConsultaInputPort;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.ports.output.ConsultaOutputPort;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.ports.output.MedicoOutputPort;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.ports.output.PacienteOutputPort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ConsultaUseCase implements ConsultaInputPort {

    @Transactional
    @Override
    public ConsultaDto agendar(ConsultaRequestDto request, MedicoOutputPort medicoOutputPort, PacienteOutputPort pacienteOutputPort, ConsultaOutputPort consultaOutputPort) {
        return Optional.ofNullable(request)
                .map(dto -> Consulta.converterRequestParaEntity(dto, ConsultaStatusEnum.AGENDADO, medicoOutputPort, pacienteOutputPort))
                .map(Consulta::converterEntityParaDto)
                .map(consultaOutputPort::salvar)
                .orElseThrow();
    }
}
