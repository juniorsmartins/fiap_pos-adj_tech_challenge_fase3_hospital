package fiap.adj.fase3.tech_challenge_hospital.infrastructure.presenters;

import fiap.adj.fase3.tech_challenge_hospital.application.dtos.internal.MedicoDto;
import fiap.adj.fase3.tech_challenge_hospital.application.dtos.response.MedicoResponseDto;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.daos.MedicoDao;

public class MedicoPresenter {

    public static MedicoResponseDto converterDtoParaResponse(MedicoDto dto) {
        return new MedicoResponseDto(dto.id(), dto.nome());
    }

    public static MedicoResponseDto converterDaoParaResponse(MedicoDao dao) {
        return new MedicoResponseDto(dao.getId(), dao.getNome());
    }

    public static MedicoDao converterDtoParaDao(MedicoDto dto) {
        return new MedicoDao(dto.id(), dto.nome());
    }

    public static MedicoDto converterDaoParaDto(MedicoDao dao) {
        return new MedicoDto(dao.getId(), dao.getNome());
    }
}
