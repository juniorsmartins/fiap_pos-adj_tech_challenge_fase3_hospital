package fiap.adj.fase3.tech_challenge_hospital.application.mappers;

import fiap.adj.fase3.tech_challenge_hospital.application.dtos.internal.PacienteDto;
import fiap.adj.fase3.tech_challenge_hospital.application.dtos.request.PacienteRequestDto;
import fiap.adj.fase3.tech_challenge_hospital.domain.entities.Paciente;

public class PacienteMapper {

    public static Paciente converterRequestParaEntity(PacienteRequestDto dto) {
        return new Paciente(dto.getNome());
    }

    public static PacienteDto converterEntityParaDto(Paciente paciente) {
        return new PacienteDto(paciente.getId(), paciente.getNome());
    }
}
