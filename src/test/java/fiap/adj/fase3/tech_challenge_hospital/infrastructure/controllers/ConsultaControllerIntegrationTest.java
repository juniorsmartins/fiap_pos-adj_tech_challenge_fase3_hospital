package fiap.adj.fase3.tech_challenge_hospital.infrastructure.controllers;

import fiap.adj.fase3.tech_challenge_hospital.UtilConsultaTest;
import fiap.adj.fase3.tech_challenge_hospital.UtilMedicoTest;
import fiap.adj.fase3.tech_challenge_hospital.UtilPacienteTest;
import fiap.adj.fase3.tech_challenge_hospital.domain.entities.enums.ConsultaStatusEnum;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.daos.ConsultaDao;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.daos.MedicoDao;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.daos.PacienteDao;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.repositories.ConsultaRepository;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.repositories.MedicoRepository;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.repositories.PacienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ConsultaControllerIntegrationTest {

    public static final String DATA_HORA_INICIAL = "2025-07-12T10:10:22";

    @Autowired
    private ConsultaController controller;

    @Autowired
    private ConsultaRepository repository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    private ConsultaDao dao;

    private MedicoDao medicoDao1;

    private MedicoDao medicoDao2;

    private PacienteDao pacienteDao1;

    private PacienteDao pacienteDao2;

    @BeforeEach
    void setUp() {
        medicoDao1 = UtilMedicoTest.montarMedicoDao("MedicoConsulta 1", "username111", "password111");
        medicoRepository.save(medicoDao1);

        medicoDao2 = UtilMedicoTest.montarMedicoDao("MedicoConsulta 2", "username222", "password222");
        medicoRepository.save(medicoDao2);

        pacienteDao1 = UtilPacienteTest.montarPacienteDao("PacienteConsulta 1", "username333", "password333");
        pacienteRepository.save(pacienteDao1);

        pacienteDao2 = UtilPacienteTest.montarPacienteDao("PacienteConsulta 2", "username444", "password444");
        pacienteRepository.save(pacienteDao2);

        var dataHora1 = LocalDateTime.of(LocalDate.of(2025, 8, 10), LocalTime.of(14, 10));
        dao = UtilConsultaTest.montarConsultaDao(dataHora1, ConsultaStatusEnum.AGENDADO.getValue(), medicoDao1, pacienteDao1);
        repository.save(dao);

        var dataHora2 = LocalDateTime.of(LocalDate.of(2025, 9, 5), LocalTime.of(16, 12));
        var dao2 = UtilConsultaTest.montarConsultaDao(dataHora2, ConsultaStatusEnum.AGENDADO.getValue(), medicoDao2, pacienteDao1);
        repository.save(dao2);
    }

    @Nested
    @DisplayName("Criar")
    class Criar {

        @Test
        void dadaRequisicaoValida_quandoCriar_entaoRetornarResponseComDadosValidos() {
            var request = UtilConsultaTest.montarConsultaRequestDto(DATA_HORA_INICIAL, medicoDao1.getId(), pacienteDao1.getId());
            var response = controller.criarConsulta(request);
            assertNotNull(response.id());
            assertEquals(request.getDataHora(), response.dataHora().toString());
            assertEquals(response.status(), ConsultaStatusEnum.AGENDADO.getValue());
            assertEquals(medicoDao1.getId(), response.medico().id());
            assertEquals(pacienteDao1.getId(), response.paciente().id());
        }

        @Test
        void dadaRequisicaoValida_quandoCriar_entaoSalvarDadosValidosNoBanco() {
            var request = UtilConsultaTest.montarConsultaRequestDto(DATA_HORA_INICIAL, medicoDao1.getId(), pacienteDao1.getId());
            var response = controller.criarConsulta(request);
            var dadoSalvo = repository.findById(response.id()).orElseThrow();
            assertEquals(request.getDataHora(), dadoSalvo.getDataHora().toString());
            assertEquals(ConsultaStatusEnum.AGENDADO.getValue(), dadoSalvo.getStatus());
            assertEquals(request.getMedicoId(), dadoSalvo.getMedico().getId());
            assertEquals(request.getPacienteId(), dadoSalvo.getPaciente().getId());
        }
    }

    @Nested
    @DisplayName("Atualizar")
    class Atualizar {

        @Test
        void dadaRequisicaoValida_quandoAtualizar_entaoRetornarResponseValido() {
            var id = dao.getId();
            var desatualizado = repository.findById(id).orElseThrow();
            assertNotEquals(DATA_HORA_INICIAL, desatualizado.getDataHora().toString());
            assertEquals(medicoDao1.getId(), desatualizado.getMedico().getId());
            assertEquals(pacienteDao1.getId(), desatualizado.getPaciente().getId());

            var atualizado = UtilConsultaTest
                    .montarConsultaRequestDto(DATA_HORA_INICIAL, medicoDao2.getId(), pacienteDao2.getId());
            var response = controller.atualizarConsulta(id, atualizado);

            assertEquals(DATA_HORA_INICIAL, response.dataHora().toString());
            assertEquals(medicoDao2.getId(), response.medico().id());
            assertEquals(pacienteDao2.getId(), response.paciente().id());
        }

        @Test
        void dadaRequisicaoValida_quandoAtualizar_entaoAtualizarNoBanco() {
            var id = dao.getId();
            var atualizado = UtilConsultaTest
                    .montarConsultaRequestDto(DATA_HORA_INICIAL, medicoDao2.getId(), pacienteDao2.getId());
            var response = controller.atualizarConsulta(id, atualizado);

            var doBanco = repository.findById(id).orElseThrow();

            assertEquals(doBanco.getDataHora(), response.dataHora());
            assertEquals(doBanco.getStatus(), response.status());
            assertEquals(doBanco.getMedico().getId(), response.medico().id());
            assertEquals(doBanco.getPaciente().getId(), response.paciente().id());
        }
    }

    @Nested
    @DisplayName("Consultar")
    class Consultar {

        @Test
        void dadoIdValido_quandoConsultarPorId_entaoRetornarResponseValido() {
            var response = controller.consultarConsultaPorId(dao.getId());
            assertEquals(dao.getId(), response.id());
            assertEquals(dao.getDataHora(), response.dataHora());
            assertEquals(dao.getMedico().getId(), response.medico().id());
            assertEquals(dao.getPaciente().getId(), response.paciente().id());
        }
    }

    @Nested
    @DisplayName("Concluir")
    class Concluir {

        @Test
        void dadoIdValido_quandoConcluirConsulta_entaoRetornarTrue() {
            var response = controller.concluirConsulta(dao.getId());
            assertTrue(response);
        }

        @Test
        void dadoIdValido_quandoConcluirConsulta_entaoSalvarConsultaComStatusConcluido() {
            var dadoAnterior = repository.findById(dao.getId()).orElseThrow();
            assertEquals(ConsultaStatusEnum.AGENDADO.getValue(), dadoAnterior.getStatus());

            var response = controller.concluirConsulta(dao.getId());
            assertTrue(response);

            var dadoPosterior = repository.findById(dao.getId()).orElseThrow();
            assertEquals(ConsultaStatusEnum.CONCLUIDO.getValue(), dadoPosterior.getStatus());
        }
    }

    @Nested
    @DisplayName("Cancelar")
    class Cancelar {

        @Test
        void dadoIdValido_quandoCancelarConsulta_entaoRetornarTrue() {
            var response = controller.cancelarConsulta(dao.getId());
            assertTrue(response);
        }

        @Test
        void dadoIdValido_quandoCancelarConsulta_entaoSalvarConsultaComStatusCancelado() {
            var dadoAnterior = repository.findById(dao.getId()).orElseThrow();
            assertEquals(ConsultaStatusEnum.AGENDADO.getValue(), dadoAnterior.getStatus());

            var response = controller.cancelarConsulta(dao.getId());
            assertTrue(response);

            var dadoPosterior = repository.findById(dao.getId()).orElseThrow();
            assertEquals(ConsultaStatusEnum.CANCELADO.getValue(), dadoPosterior.getStatus());
        }
    }

    @Nested
    @DisplayName("listar")
    class Listar {

        @Test
        void dadoIdValido_quandoListarHistoricoDeConsultasPorIdPaciente_entaoRetornarListaValida() {
            var colecao = controller.listarHistoricoDeConsultasPorIdPaciente(pacienteDao1.getId());
            assertEquals(2, colecao.size());
        }
    }
}