package fiap.adj.fase3.tech_challenge_hospital.infrastructure.ports.output;

import fiap.adj.fase3.tech_challenge_hospital.application.dtos.internal.MedicoDto;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.daos.MedicoDao;

import java.util.Optional;

public interface MedicoOutputPort {

    MedicoDto criar(MedicoDto dto);

    Optional<MedicoDao> consultarPorId(Long id);

    void apagarPorId(Long id);
}
