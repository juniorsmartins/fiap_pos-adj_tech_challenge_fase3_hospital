package fiap.adj.fase3.tech_challenge_hospital.infrastructure.repositories;

import fiap.adj.fase3.tech_challenge_hospital.infrastructure.daos.HistoricoMedicoDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HistoricoMedicoRepository extends JpaRepository<HistoricoMedicoDao, Long> {
}
