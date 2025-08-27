package fiap.adj.fase3.tech_challenge_hospital.infrastructure.presenters;

import fiap.adj.fase3.tech_challenge_hospital.application.dtos.internal.PacienteDto;
import fiap.adj.fase3.tech_challenge_hospital.application.dtos.response.PacienteResponseDto;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.daos.PacienteDao;

public class PacientePresenter {

    public static PacienteResponseDto converterDaoParaResponse(PacienteDao dao) {
        return new PacienteResponseDto(dao.getId(), dao.getNome());
    }

    public static PacienteResponseDto converterDtoParaResponse(PacienteDto dto) {
        return new PacienteResponseDto(dto.id(), dto.nome());
    }

    public static PacienteDao converterDtoParaDao(PacienteDto dto) {
        return new PacienteDao(dto.id(), dto.nome());
    }

    public static PacienteDto converterDaoParaDto(PacienteDao dao) {
        return new PacienteDto(dao.getId(), dao.getNome());
    }
}
