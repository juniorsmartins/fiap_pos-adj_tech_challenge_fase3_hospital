package fiap.adj.fase3.tech_challenge_hospital.application.mappers;

import fiap.adj.fase3.tech_challenge_hospital.application.dtos.request.PacienteRequestDto;
import fiap.adj.fase3.tech_challenge_hospital.application.dtos.response.PacienteResponseDto;
import fiap.adj.fase3.tech_challenge_hospital.domain.entities.Paciente;

public class PacienteMapper {

    public static Paciente converterParaPaciente(PacienteRequestDto dto) {
        return new Paciente(dto.getNome());
    }

    public static PacienteResponseDto converterParaDto(Paciente paciente) {
        return new PacienteResponseDto(paciente.getId(), paciente.getNome());
    }
}
