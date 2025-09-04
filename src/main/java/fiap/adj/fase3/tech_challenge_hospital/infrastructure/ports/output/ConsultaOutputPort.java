package fiap.adj.fase3.tech_challenge_hospital.infrastructure.ports.output;

import fiap.adj.fase3.tech_challenge_hospital.application.dtos.internal.ConsultaDto;

import java.util.Optional;

public interface ConsultaOutputPort {

    ConsultaDto salvar(ConsultaDto dto);

    Optional<ConsultaDto> consultarPorIdAndStatus(Long id, String status);
}
