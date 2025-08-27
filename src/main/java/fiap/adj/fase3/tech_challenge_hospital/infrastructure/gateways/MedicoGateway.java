package fiap.adj.fase3.tech_challenge_hospital.infrastructure.gateways;

import fiap.adj.fase3.tech_challenge_hospital.application.dtos.internal.MedicoDto;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.daos.MedicoDao;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.ports.output.MedicoOutputPort;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.presenters.MedicoPresenter;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.repositories.MedicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class MedicoGateway implements MedicoOutputPort {

    private final MedicoRepository medicoRepository;

    @Override
    public MedicoDto criar(MedicoDto dto) {
        return Optional.ofNullable(dto)
                .map(MedicoPresenter::converterDtoParaDao)
                .map(medicoRepository::save)
                .map(MedicoPresenter::converterDaoParaDto)
                .orElseThrow();
    }

    @Override
    public Optional<MedicoDao> consultarPorId(Long id) {
        return medicoRepository.findById(id);
    }
}
