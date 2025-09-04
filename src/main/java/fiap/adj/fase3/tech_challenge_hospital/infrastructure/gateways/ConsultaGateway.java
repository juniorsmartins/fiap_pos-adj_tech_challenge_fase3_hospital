package fiap.adj.fase3.tech_challenge_hospital.infrastructure.gateways;

import fiap.adj.fase3.tech_challenge_hospital.application.dtos.internal.ConsultaDto;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.ports.output.ConsultaOutputPort;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.presenters.ConsultaPresenter;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.repositories.ConsultaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ConsultaGateway implements ConsultaOutputPort {

    private final ConsultaRepository consultaRepository;

    @Transactional
    @Override
    public ConsultaDto salvar(ConsultaDto dto) {
        return Optional.ofNullable(dto)
                .map(ConsultaPresenter::converterDtoParaDao)
                .map(consultaRepository::save)
                .map(ConsultaPresenter::converterDaoParaDto)
                .orElseThrow();
    }

    @Transactional
    @Override
    public Optional<ConsultaDto> consultarPorIdAndStatus(Long id, String status) {
        return consultaRepository.findByIdAndStatus(id, status)
                .map(ConsultaPresenter::converterDaoParaDto);
    }
}
