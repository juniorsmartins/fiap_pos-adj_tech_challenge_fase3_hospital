package fiap.adj.fase3.tech_challenge_hospital;

import fiap.adj.fase3.tech_challenge_hospital.application.dtos.request.PacienteRequestDto;
import fiap.adj.fase3.tech_challenge_hospital.domain.entities.enums.RoleEnum;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.daos.PacienteDao;

public class UtilPacienteTest {

    public static PacienteRequestDto montarPacienteRequestDto(String nome, String username, String password) {
        var userRequestDto = UtilUserTest.montarUserRequestDto(username, password);
        return new PacienteRequestDto(nome, userRequestDto);
    }

    public static PacienteDao montarPacienteDao(String nome, String username, String password) {
        var userDao = UtilUserTest.montarUserDao(username, password, 4L, RoleEnum.ROLE_PACIENTE.getValue());
        var pacienteDao = new PacienteDao();
        pacienteDao.setNome(nome);
        pacienteDao.setUser(userDao);
        return pacienteDao;
    }
}
