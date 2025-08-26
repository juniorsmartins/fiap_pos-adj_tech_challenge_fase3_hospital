package fiap.adj.fase3.tech_challenge_hospital.infrastructure.presenters;

import fiap.adj.fase3.tech_challenge_hospital.application.dtos.response.PacienteResponseDto;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.daos.PacienteDao;

public class PacientePresenter {

    public static PacienteResponseDto converterParaResponse(PacienteDao dao) {
        return new PacienteResponseDto(dao.getId(), dao.getNome());
    }

    public static PacienteDao converterParaDao(PacienteResponseDto dto) {
        return new PacienteDao(dto.id(), dto.nome());
    }
}
