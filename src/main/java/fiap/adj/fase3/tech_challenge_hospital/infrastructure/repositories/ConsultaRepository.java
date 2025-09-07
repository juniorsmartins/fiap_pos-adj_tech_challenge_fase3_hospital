package fiap.adj.fase3.tech_challenge_hospital.infrastructure.repositories;

import fiap.adj.fase3.tech_challenge_hospital.infrastructure.daos.ConsultaDao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface ConsultaRepository extends JpaRepository<ConsultaDao, Long> {

    Optional<ConsultaDao> findByIdAndStatus(Long id, String status);

    Set<ConsultaDao> findAllByPacienteId(Long pacienteId);

    Optional<ConsultaDao> findByIdAndStatusNot(Long id, String status);
}
