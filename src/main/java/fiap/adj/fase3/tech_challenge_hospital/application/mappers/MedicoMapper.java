package fiap.adj.fase3.tech_challenge_hospital.application.mappers;

import fiap.adj.fase3.tech_challenge_hospital.application.dtos.request.MedicoRequestDto;
import fiap.adj.fase3.tech_challenge_hospital.application.dtos.request.UserRequestDto;
import fiap.adj.fase3.tech_challenge_hospital.domain.entities.Medico;
import fiap.adj.fase3.tech_challenge_hospital.domain.entities.Role;
import fiap.adj.fase3.tech_challenge_hospital.domain.entities.Usuario;

import java.util.Set;

public final class MedicoMapper {

    public static Medico converterDtoParaEntity(MedicoRequestDto dto) {
        var usuario = criarUsuarioEntity(dto.getUser());
        return new Medico(dto.getNome(), usuario);
    }

    private static Usuario criarUsuarioEntity(UserRequestDto dto) {
        var role = new Role("ROLE_MEDICO");
        return new Usuario(dto.getUsername(), dto.getPassword(), true, Set.of(role));
    }
}
