package fiap.adj.fase3.tech_challenge_hospital.infrastructure.gateways;

import fiap.adj.fase3.tech_challenge_hospital.application.dtos.response.PacienteResponseDto;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.ports.output.PacienteOutputPort;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.presenters.PacientePresenter;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.repositories.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PacienteGateway implements PacienteOutputPort {

    private final PacienteRepository pacienteRepository;

    @Override
    public PacienteResponseDto criar(PacienteResponseDto dto) {
        return Optional.ofNullable(dto)
                .map(PacientePresenter::converterParaDao)
                .map(pacienteRepository::save)
                .map(PacientePresenter::converterParaResponse)
                .orElseThrow();
    }

    @Override
    public PacienteResponseDto findById(Long id) {
        return pacienteRepository.findById(id)
                .map(PacientePresenter::converterParaResponse)
                .orElseThrow();
    }
}
