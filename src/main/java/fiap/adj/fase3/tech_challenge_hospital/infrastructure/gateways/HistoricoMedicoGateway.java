package fiap.adj.fase3.tech_challenge_hospital.infrastructure.gateways;

import fiap.adj.fase3.tech_challenge_hospital.application.dtos.internal.HistoricoMedicoDto;
import fiap.adj.fase3.tech_challenge_hospital.application.usecases.HistoricoMedicoPresenter;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.ports.output.HistoricoMedicoOutputPort;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.repositories.HistoricoMedicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class HistoricoMedicoGateway implements HistoricoMedicoOutputPort {

    private final HistoricoMedicoRepository historicoMedicoRepository;

    @Transactional
    @Override
    public HistoricoMedicoDto salvar(HistoricoMedicoDto dto) {
        return Optional.ofNullable(dto)
                .map(HistoricoMedicoPresenter::converterDtoParaDao)
                .map(historicoMedicoRepository::save)
                .map(HistoricoMedicoPresenter::converterDaoParaDto)
                .orElseThrow();
    }
}
