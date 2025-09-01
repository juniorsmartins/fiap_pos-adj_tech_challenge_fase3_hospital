package fiap.adj.fase3.tech_challenge_hospital.infrastructure.daos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pacientes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public final class PacienteDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, targetEntity = UserDao.class, optional = false)
    private UserDao user;
}
