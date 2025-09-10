package fiap.adj.fase3.tech_challenge_hospital.infrastructure.controllers;

import fiap.adj.fase3.tech_challenge_hospital.UtilConsultaTest;
import fiap.adj.fase3.tech_challenge_hospital.UtilHistoricoMedicoTest;
import fiap.adj.fase3.tech_challenge_hospital.UtilMedicoTest;
import fiap.adj.fase3.tech_challenge_hospital.UtilPacienteTest;
import fiap.adj.fase3.tech_challenge_hospital.domain.entities.enums.ConsultaStatusEnum;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.daos.ConsultaDao;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.daos.MedicoDao;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.daos.PacienteDao;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.repositories.ConsultaRepository;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.repositories.HistoricoMedicoRepository;
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

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@Transactional
class HistoricoMedicoControllerIntegrationTest {

    private static final String DIAGNOSTICO = "Diagnostico 1";

    private static final String PRESCRICAO = "Prescrição 1";

    private static final String EXAMES = "Exames 1";

    @Autowired
    private HistoricoMedicoController controller;

    @Autowired
    private HistoricoMedicoRepository repository;

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    private ConsultaDao consultaDao1;

    private ConsultaDao consultaDao2;

    private MedicoDao medicoDao1;

    private PacienteDao pacienteDao1;

    @BeforeEach
    void setUp() {
        medicoDao1 = UtilMedicoTest.montarMedicoDao("MedicoConsulta 1", "username111", "password111");
        medicoRepository.save(medicoDao1);

        pacienteDao1 = UtilPacienteTest.montarPacienteDao("PacienteConsulta 1", "username333", "password333");
        pacienteRepository.save(pacienteDao1);

        var dataHora1 = LocalDateTime.of(LocalDate.of(2025, 8, 10), LocalTime.of(14, 10));
        consultaDao1 = UtilConsultaTest.montarConsultaDao(dataHora1, ConsultaStatusEnum.AGENDADO.getValue(), medicoDao1, pacienteDao1);
        consultaRepository.save(consultaDao1);

        var dataHora2 = LocalDateTime.of(LocalDate.of(2025, 8, 10), LocalTime.of(14, 10));
        consultaDao2 = UtilConsultaTest.montarConsultaDao(dataHora2, ConsultaStatusEnum.AGENDADO.getValue(), medicoDao1, pacienteDao1);
        consultaRepository.save(consultaDao2);
    }

    @Nested
    @DisplayName("Criar")
    class Criar {

        @Test
        void dadaRequisicaoValida_quandoCriarHistoricoMedico_entaoRetornarResponseValido() {
            var request = UtilHistoricoMedicoTest.montarHistoricoMedicoRequestDto(DIAGNOSTICO, PRESCRICAO, EXAMES, consultaDao1.getId());
            var response = controller.criarHistoricoMedico(request);
            assertEquals(DIAGNOSTICO, response.diagnostico());
            assertEquals(PRESCRICAO, response.prescricao());
            assertEquals(EXAMES, response.exames());
            assertEquals(consultaDao1.getId(), response.consulta().id());
        }

        @Test
        void dadaRequisicaoValida_quandoCriarHistoricoMedico_entaoSalvarNoBanco() {
            var request = UtilHistoricoMedicoTest.montarHistoricoMedicoRequestDto(DIAGNOSTICO, PRESCRICAO, EXAMES, consultaDao1.getId());
            var response = controller.criarHistoricoMedico(request);
            var doBanco = repository.findById(response.id()).orElseThrow();
            assertEquals(doBanco.getDiagnostico(), response.diagnostico());
            assertEquals(doBanco.getPrescricao(), response.prescricao());
            assertEquals(doBanco.getExames(), response.exames());
            assertEquals(doBanco.getConsulta().getId(), response.consulta().id());
        }
    }

    @Nested
    @DisplayName("Listar")
    class Listar {

        @Test
        void dadoIdValido_quandoListarHistoricoPorIdPaciente_entaoRetornarDoisItens() {
            var request1 = UtilHistoricoMedicoTest.montarHistoricoMedicoRequestDto(DIAGNOSTICO, PRESCRICAO, EXAMES, consultaDao1.getId());
            controller.criarHistoricoMedico(request1);

            var request2 = UtilHistoricoMedicoTest.montarHistoricoMedicoRequestDto(DIAGNOSTICO, PRESCRICAO, EXAMES, consultaDao2.getId());
            controller.criarHistoricoMedico(request2);

            var response = controller.listarHistoricoMedicoPorIdPaciente(pacienteDao1.getId());
            assertEquals(2, response.size());
        }
    }
}