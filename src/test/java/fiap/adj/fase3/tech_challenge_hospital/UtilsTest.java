package fiap.adj.fase3.tech_challenge_hospital;

import fiap.adj.fase3.tech_challenge_hospital.application.dtos.request.MedicoRequestDto;
import fiap.adj.fase3.tech_challenge_hospital.application.dtos.request.UserRequestDto;
import fiap.adj.fase3.tech_challenge_hospital.domain.entities.enums.RoleEnum;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.daos.MedicoDao;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.daos.RoleDao;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.daos.UserDao;

import java.util.Collections;
import java.util.HashSet;

public class UtilsTest {

    public static MedicoRequestDto montarMedicoRequestDto(String nome, String username, String password) {
        var userRequestDto = montarUserRequestDto(username, password);
        return new MedicoRequestDto(nome, userRequestDto);
    }

    public static UserRequestDto montarUserRequestDto(String username, String password) {
        return new UserRequestDto(username, password);
    }

    public static MedicoDao montarMedicoDao(String nome, String username, String password) {
        var userDao = montarUserDao(username, password, 3L, RoleEnum.ROLE_MEDICO.getValue());
        var medicoDao = new MedicoDao();
        medicoDao.setNome(nome);
        medicoDao.setUser(userDao);
        return medicoDao;
    }

    public static UserDao montarUserDao(String username, String password, Long idRole, String role) {
        var userDao = new UserDao();
        userDao.setUsername(username);
        userDao.setPassword(password);
        userDao.setEnabled(true);

        var roleDao = montarRoleDao(idRole, role);
        userDao.setRoles(new HashSet<>(Collections.singleton(roleDao)));

        return userDao;
    }

    public static RoleDao montarRoleDao(Long id, String role) {
        var roleDao = new RoleDao();
        roleDao.setId(id);
        roleDao.setName(role);
        return roleDao;
    }
}
