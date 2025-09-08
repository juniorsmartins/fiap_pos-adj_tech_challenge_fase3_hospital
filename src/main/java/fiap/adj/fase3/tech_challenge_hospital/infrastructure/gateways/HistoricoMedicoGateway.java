package fiap.adj.fase3.tech_challenge_hospital.infrastructure.gateways;

import fiap.adj.fase3.tech_challenge_hospital.application.dtos.internal.HistoricoMedicoDto;
import fiap.adj.fase3.tech_challenge_hospital.application.usecases.HistoricoMedicoPresenter;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.ports.output.HistoricoMedicoOutputPort;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.repositories.ConsultaRepository;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.repositories.HistoricoMedicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class HistoricoMedicoGateway implements HistoricoMedicoOutputPort {

    private final HistoricoMedicoRepository historicoMedicoRepository;

    private final ConsultaRepository consultaRepository;

    @Transactional
    @Override
    public HistoricoMedicoDto salvar(HistoricoMedicoDto dto) {

        return Optional.ofNullable(dto)
                .map(HistoricoMedicoPresenter::converterDtoParaDao)
                .map(historicoMedicoRepository::save)
                .map(HistoricoMedicoPresenter::converterDaoParaDto)
                .orElseThrow();
//        var consulta = consultaRepository.findByIdAndStatusNot(dto.getConsulta().getId(), ConsultaStatusEnum.CANCELADO.getValue())
//                .map(ConsultaPresenter::converterDaoParaDto)
//                .orElseThrow();
//        dto.setConsulta(consulta);
//        var historicoMedicoDao = HistoricoMedicoPresenter.converterDtoParaDao(dto);
//        historicoMedicoRepository.save(historicoMedicoDao);
//        return HistoricoMedicoPresenter.converterDaoParaDto(historicoMedicoDao);
    }

    @Override
    public Set<HistoricoMedicoDto> listarHistoricoMedicoPorIdPaciente(Long id) {
        return historicoMedicoRepository.listarHistoricoMedicoPorIdPaciente(id)
                .stream()
                .map(HistoricoMedicoPresenter::converterDaoParaDto)
                .collect(Collectors.toSet());
    }
}
