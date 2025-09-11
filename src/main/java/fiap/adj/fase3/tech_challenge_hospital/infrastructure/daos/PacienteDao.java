package fiap.adj.fase3.tech_challenge_hospital.infrastructure.daos;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "pacientes")
@AllArgsConstructor
@Getter
@Setter
public final class PacienteDao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY, targetEntity = UserDao.class, optional = false)
    private UserDao user;

    public PacienteDao(String nome, String email, UserDao user) {
        this.nome = nome;
        this.email = email;
        this.user = user;
    }
}
