package fiap.adj.fase3.tech_challenge_hospital.infrastructure.ports.input;

import fiap.adj.fase3.tech_challenge_hospital.application.dtos.internal.MedicoDto;
import fiap.adj.fase3.tech_challenge_hospital.application.dtos.request.MedicoRequestDto;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.ports.output.MedicoOutputPort;

public interface MedicoInputPort {

    MedicoDto criar(MedicoRequestDto requestDto, MedicoOutputPort outputPort);

    void apagarPorId(Long id, MedicoOutputPort outputPort);

    MedicoDto atualizar(Long id, MedicoRequestDto requestDto, MedicoOutputPort outputPort);
}
