package fiap.adj.fase3.tech_challenge_hospital.domain.entities;

import fiap.adj.fase3.tech_challenge_hospital.application.dtos.internal.MedicoDto;
import fiap.adj.fase3.tech_challenge_hospital.application.dtos.internal.RoleDto;
import fiap.adj.fase3.tech_challenge_hospital.application.dtos.internal.UserDto;
import fiap.adj.fase3.tech_challenge_hospital.application.dtos.request.MedicoRequestDto;
import fiap.adj.fase3.tech_challenge_hospital.application.dtos.request.UserRequestDto;
import fiap.adj.fase3.tech_challenge_hospital.application.mappers.MedicoMapper;
import fiap.adj.fase3.tech_challenge_hospital.domain.entities.enums.RoleEnum;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.ports.output.RoleOutputPort;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public final class Medico {

    private Long id;

    private String nome;

    private Usuario user;

    public Medico(Long id, String nome, Usuario user) {
        this.id = id;
        this.nome = nome;
        this.user = user;
    }

    public Medico(String nome, Usuario user) {
        this.nome = nome;
        this.user = user;
    }

    public static Medico converterRequestParaEntity(MedicoRequestDto request, RoleOutputPort roleOutputPort) {
        var usuario = criarUsuarioEntity(request.getUser(), roleOutputPort);
        return new Medico(request.getNome(), usuario);
    }

    private static Usuario criarUsuarioEntity(UserRequestDto dto, RoleOutputPort roleOutputPort) {

        var role = consultarRolePorNome(RoleEnum.ROLE_MEDICO.getValue(), roleOutputPort);
        return new Usuario(dto.getUsername(), dto.getPassword(), true, Set.of(role));
    }

    private static Role consultarRolePorNome(String nome, RoleOutputPort roleOutputPort) {
        return roleOutputPort.consultarPorNome(nome)
                .map(Medico::converterDtoParaEntity)
                .orElseThrow();
    }

    private static Role converterDtoParaEntity(RoleDto dto) {
        return new Role(dto.id(), dto.name());
    }

    public static MedicoDto converterEntityParaDto(Medico medico) {
        var userDto = converterEntityParaDto(medico.getUser());
        return new MedicoDto(medico.getId(), medico.getNome(), userDto);
    }

    private static UserDto converterEntityParaDto(Usuario usuario) {
        var roleDto = usuario.getRoles().stream()
                .map(Medico::converterEntityParaDto)
                .collect(Collectors.toSet());

        return new UserDto(usuario.getId(), usuario.getUsername(), usuario.getPassword(), usuario.isEnabled(), roleDto);
    }

    private static RoleDto converterEntityParaDto(Role role) {
        return new RoleDto(role.getId(), role.getName());
    }

    public static Medico regraAtualizar(MedicoDto dto, MedicoRequestDto request) {

        var entity = MedicoMapper.converterDtoParaEntity(request);
        entity.setId(dto.id());
        return entity;
    }
}
