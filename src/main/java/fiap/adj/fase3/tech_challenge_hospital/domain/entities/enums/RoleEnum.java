package fiap.adj.fase3.tech_challenge_hospital.domain.entities.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RoleEnum {

    ROLE_MEDICO("ROLE_MEDICO"),
    ROLE_PACIENTE("ROLE_PACIENTE");

    private final String value;
}
