package fiap.adj.fase3.tech_challenge_hospital.infrastructure.gateways;

import fiap.adj.fase3.tech_challenge_hospital.application.dtos.internal.PacienteDto;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.daos.PacienteDao;
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
    public PacienteDto criar(PacienteDto dto) {
        return Optional.ofNullable(dto)
                .map(PacientePresenter::converterDtoParaDao)
                .map(pacienteRepository::save)
                .map(PacientePresenter::converterDaoParaDto)
                .orElseThrow();
    }

    @Override
    public Optional<PacienteDao> consultarPorId(Long id) {
        return pacienteRepository.findById(id);
    }
}
