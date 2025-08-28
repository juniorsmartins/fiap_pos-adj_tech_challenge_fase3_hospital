package fiap.adj.fase3.tech_challenge_hospital.infrastructure.ports.output;

import fiap.adj.fase3.tech_challenge_hospital.application.dtos.internal.PacienteDto;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.daos.PacienteDao;

import java.util.Optional;

public interface PacienteOutputPort {

    PacienteDto criar(PacienteDto dto);

    Optional<PacienteDao> consultarPorId(Long id);

    void apagarPorId(Long id);
}
