package fiap.adj.fase3.tech_challenge_hospital.infrastructure.presenters;

import fiap.adj.fase3.tech_challenge_hospital.application.dtos.internal.ConsultaDto;
import fiap.adj.fase3.tech_challenge_hospital.application.dtos.response.ConsultaResponseDto;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.daos.ConsultaDao;

public final class ConsultaPresenter {

    public static ConsultaResponseDto converterDtoParaResponse(ConsultaDto dto) {
        var medicoResponse = MedicoPresenter.converterDtoParaResponse(dto.medico());
        var pacienteResponse = PacientePresenter.converterDtoParaResponse(dto.paciente());
        return new ConsultaResponseDto(dto.id(), dto.dataHora(), dto.status(), medicoResponse, pacienteResponse);
    }

    public static ConsultaDao converterDtoParaDao(ConsultaDto dto) {
        var medicoDao = MedicoPresenter.converterDtoParaDao(dto.medico());
        var pacienteDao = PacientePresenter.converterDtoParaDao(dto.paciente());
        return new ConsultaDao(dto.id(), dto.dataHora(), dto.status(), medicoDao, pacienteDao);
    }

    public static ConsultaDto converterDaoParaDto(ConsultaDao dao) {
        var medicoDto = MedicoPresenter.converterDaoParaDto(dao.getMedico());
        var pacienteDto = PacientePresenter.converterDaoParaDto(dao.getPaciente());
        return new ConsultaDto(dao.getId(), dao.getDataHora(), dao.getStatus(), medicoDto, pacienteDto);
    }
}
