package fiap.adj.fase3.tech_challenge_hospital.infrastructure.ports.output;

import fiap.adj.fase3.tech_challenge_hospital.application.dtos.internal.HistoricoMedicoDto;

public interface HistoricoMedicoOutputPort {

    HistoricoMedicoDto salvar(HistoricoMedicoDto dto);
}
