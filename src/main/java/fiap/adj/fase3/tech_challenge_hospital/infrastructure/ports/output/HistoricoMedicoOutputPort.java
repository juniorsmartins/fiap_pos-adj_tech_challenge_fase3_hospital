package fiap.adj.fase3.tech_challenge_hospital.infrastructure.ports.output;

import fiap.adj.fase3.tech_challenge_hospital.application.dtos.internal.HistoricoMedicoDto;

import java.util.Set;

public interface HistoricoMedicoOutputPort {

    HistoricoMedicoDto salvar(HistoricoMedicoDto dto);

    Set<HistoricoMedicoDto> listarHistoricoMedicoPorIdPaciente(Long id);

    HistoricoMedicoDto consultarHistoricoMedicoPorIdConsulta(Long id);
}
