package fiap.adj.fase3.tech_challenge_hospital.infrastructure.repositories;

import fiap.adj.fase3.tech_challenge_hospital.infrastructure.daos.HistoricoMedicoDao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface HistoricoMedicoRepository extends JpaRepository<HistoricoMedicoDao, Long> {

    @Query(
            value = "SELECT his.id, his.diagnostico, his.prescricao, his.exames, his.consulta " +
                    "FROM historicos_medicos AS his " +
                    "INNER JOIN consultas AS con " +
                    "   ON his.consulta = con.id " +
                    "INNER JOIN pacientes AS pac " +
                    "   ON con.paciente = pac.id " +
                    "WHERE 1 = 1 " +
                    "   AND pac.id = :idPaciente",
            nativeQuery = true
    )
    Set<HistoricoMedicoDao> listarHistoricoMedicoPorIdPaciente(@Param("idPaciente") Long idPaciente);
}
