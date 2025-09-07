package fiap.adj.fase3.tech_challenge_hospital.infrastructure.controllers;

import fiap.adj.fase3.tech_challenge_hospital.application.dtos.request.HistoricoMedicoRequestDto;
import fiap.adj.fase3.tech_challenge_hospital.application.dtos.response.HistoricoMedicoResponseDto;
import fiap.adj.fase3.tech_challenge_hospital.application.usecases.HistoricoMedicoPresenter;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.ports.input.HistoricoMedicoInputPort;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.ports.output.ConsultaOutputPort;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.ports.output.HistoricoMedicoOutputPort;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.ports.output.MedicoOutputPort;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.ports.output.PacienteOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class HistoricoMedicoController {

    private final HistoricoMedicoInputPort historicoMedicoInputPort;

    private final HistoricoMedicoOutputPort historicoMedicoOutputPort;

    private final ConsultaOutputPort consultaOutputPort;

    private final MedicoOutputPort medicoOutputPort;

    private final PacienteOutputPort pacienteOutputPort;

    @MutationMapping
    public HistoricoMedicoResponseDto criarHistoricoMedico(@Argument HistoricoMedicoRequestDto request) {
        return Optional.ofNullable(request)
                .map(dto -> historicoMedicoInputPort.criar(dto, consultaOutputPort, historicoMedicoOutputPort))
                .map(HistoricoMedicoPresenter::converterDtoParaResponse)
                .orElseThrow();
    }
}
