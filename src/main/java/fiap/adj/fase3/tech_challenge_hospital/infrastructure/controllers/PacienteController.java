package fiap.adj.fase3.tech_challenge_hospital.infrastructure.controllers;

import fiap.adj.fase3.tech_challenge_hospital.application.dtos.request.PacienteRequestDto;
import fiap.adj.fase3.tech_challenge_hospital.application.dtos.response.PacienteResponseDto;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.ports.input.PacienteInputPort;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.ports.output.PacienteOutputPort;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class PacienteController {

    private final PacienteInputPort pacienteInputPort;

    private final PacienteOutputPort pacienteOutputPort;

    @MutationMapping(value = "criarPaciente")
    public PacienteResponseDto criarPaciente(@Argument PacienteRequestDto request) {
        return pacienteInputPort.criar(request, pacienteOutputPort);
    }

    @QueryMapping(value = "consultarPacientePorId")
    public PacienteResponseDto consultarPacientePorId(@Argument Long id) {
        return pacienteInputPort.consultar(id, pacienteOutputPort);
    }
}
