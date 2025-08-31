package fiap.adj.fase3.tech_challenge_hospital.domain.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public final class Role {

    private Long id;

    private String name;

    public Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Role(String name) {
        this.name = name;
    }
}
