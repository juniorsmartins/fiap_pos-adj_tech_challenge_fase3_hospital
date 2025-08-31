package fiap.adj.fase3.tech_challenge_hospital.application.mappers;

import fiap.adj.fase3.tech_challenge_hospital.application.dtos.internal.MedicoDto;
import fiap.adj.fase3.tech_challenge_hospital.application.dtos.internal.RoleDto;
import fiap.adj.fase3.tech_challenge_hospital.application.dtos.internal.UserDto;
import fiap.adj.fase3.tech_challenge_hospital.application.dtos.request.MedicoRequestDto;
import fiap.adj.fase3.tech_challenge_hospital.application.dtos.request.UserRequestDto;
import fiap.adj.fase3.tech_challenge_hospital.domain.entities.Medico;
import fiap.adj.fase3.tech_challenge_hospital.domain.entities.Role;
import fiap.adj.fase3.tech_challenge_hospital.domain.entities.Usuario;

import java.util.Set;
import java.util.stream.Collectors;

public final class MedicoMapper {

    public static Medico converterDtoParaEntity(MedicoRequestDto dto) {
        var usuario = criarUsuarioEntity(dto.getUser());
        return new Medico(dto.getNome(), usuario);
    }

    private static Usuario criarUsuarioEntity(UserRequestDto dto) {
        var role = new Role("ROLE_MEDICO");
        return new Usuario(dto.getUsername(), dto.getPassword(), true, Set.of(role));
    }

    public static MedicoDto converterEntityParaDto(Medico medico) {
        var userDto = converterEntityParaDto(medico.getUser());
        return new MedicoDto(medico.getId(), medico.getNome(), userDto);
    }

    private static UserDto converterEntityParaDto(Usuario usuario) {
        var roleDto = usuario.getRoles().stream()
                .map(MedicoMapper::converterEntityParaDto)
                .collect(Collectors.toSet());

        return new UserDto(usuario.getId(), usuario.getUsername(), usuario.getPassword(), usuario.isEnabled(), roleDto);
    }

    private static RoleDto converterEntityParaDto(Role role) {
        return new RoleDto(role.getId(), role.getName());
    }
}
