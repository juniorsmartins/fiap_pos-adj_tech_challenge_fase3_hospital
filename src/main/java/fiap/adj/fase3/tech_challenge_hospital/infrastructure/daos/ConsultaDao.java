package fiap.adj.fase3.tech_challenge_hospital.infrastructure.daos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "consultas")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public final class ConsultaDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataHora;

    private String status;

    @ManyToOne
    @JoinColumn(name = "medico", nullable = false)
    private MedicoDao medico;

    @ManyToOne
    @JoinColumn(name = "paciente", nullable = false)
    private PacienteDao paciente;
}
