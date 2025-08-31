package fiap.adj.fase3.tech_challenge_hospital.domain.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public final class Usuario {

    private Long id;

    private String username;

    private String password;

    private boolean enabled;

    private Set<Role> roles;

    public Usuario(Long id, String username, String password, boolean enabled, Set<Role> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
    }

    public Usuario(String username, String password, boolean enabled, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.roles = roles;
    }

    public Usuario(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
