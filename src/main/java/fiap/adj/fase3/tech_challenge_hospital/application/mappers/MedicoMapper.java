package fiap.adj.fase3.tech_challenge_hospital.application.mappers;

import fiap.adj.fase3.tech_challenge_hospital.application.dtos.internal.MedicoDto;
import fiap.adj.fase3.tech_challenge_hospital.application.dtos.request.MedicoRequestDto;
import fiap.adj.fase3.tech_challenge_hospital.domain.entities.Medico;

public class MedicoMapper {

    public static Medico converterRequestParaEntity(MedicoRequestDto dto) {
        return new Medico(dto.getNome());
    }

    public static MedicoDto converterEntityParaDto(Medico medico) {
        return new MedicoDto(medico.getId(), medico.getNome());
    }
}
