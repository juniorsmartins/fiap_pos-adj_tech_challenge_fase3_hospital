package fiap.adj.fase3.tech_challenge_hospital.infrastructure.controllers;

import fiap.adj.fase3.tech_challenge_hospital.UtilPacienteTest;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.daos.PacienteDao;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.repositories.PacienteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class PacienteControllerIntegrationTest {

    private static final String NOME_INICIAL = "Paciente Inicial";

    private static final String USERNAME = "username123";

    private static final String PASSWORD = "password123";

    private static final String NOME_ATUAL = "Paciente Atual";

    private static final String USERNAME_ATUAL = "username999";

    private static final String PASSWORD_ATUAL = "password999";

    @Autowired
    private PacienteController controller;

    @Autowired
    private PacienteRepository repository;

    private PacienteDao pacienteDao;

    @BeforeEach
    void setUp() {
        pacienteDao = UtilPacienteTest.montarPacienteDao(NOME_INICIAL, USERNAME, PASSWORD);
        repository.save(pacienteDao);
    }

    @Nested
    @DisplayName("Criar")
    class Criar {

        @Test
        void dadoRequisicaoValida_quandoCriar_entaoRetornarResponseComDadosValidos() {
            var requestDto = UtilPacienteTest.montarPacienteRequestDto(NOME_INICIAL, USERNAME, PASSWORD);
            var response = controller.criarPaciente(requestDto);
            assertNotNull(response.id());
            assertEquals(requestDto.getNome(), response.nome());
            assertEquals(requestDto.getUser().getUsername(), response.user().username());
        }

        @Test
        void dadoRequisicaoValida_quandoCriar_entaoSalvarDadosValidosNoBanco() {
            var requestDto = UtilPacienteTest.montarPacienteRequestDto(NOME_INICIAL, USERNAME, PASSWORD);
            var response = controller.criarPaciente(requestDto);
            var dadoSalvo = repository.findById(response.id()).orElseThrow();
            assertEquals(requestDto.getNome(), dadoSalvo.getNome());
            assertEquals(requestDto.getUser().getUsername(), dadoSalvo.getUser().getUsername());
        }
    }

    @Nested
    @DisplayName("Consultar")
    class Consultar {

        @Test
        void dadoIdValido_quandoConsultarPorId_entaoRetornarResponseValido() {
            var response = controller.consultarPacientePorId(pacienteDao.getId());
            assertEquals(pacienteDao.getId(), response.id());
            assertEquals(pacienteDao.getNome(), response.nome());
            assertEquals(pacienteDao.getUser().getUsername(), response.user().username());
        }
    }

    @Nested
    @DisplayName("Apagar")
    class Apagar {

        @Test
        void dadoIdValido_quandoApagarPorId_entaoRetornarTrue() {
            var response = controller.apagarPaciente(pacienteDao.getId());
            assertTrue(response);
        }

        @Test
        void dadoIdValido_quandoApagarPorId_entaoDeletarDoBanco() {
            var id = pacienteDao.getId();
            var dao = repository.findById(id);
            assertFalse(dao.isEmpty());

            var response = controller.apagarPaciente(id);
            assertTrue(response);

            var daoApagado = repository.findById(id);
            assertTrue(daoApagado.isEmpty());
        }
    }

    @Nested
    @DisplayName("Atualizar")
    class Atualizar {

        @Test
        void dadoRequisicaoValida_quandoAtualizar_entaoRetornarResponseValido() {
            var desatualizado = repository.findById(pacienteDao.getId());
            assertFalse(desatualizado.isEmpty());
            assertEquals(NOME_INICIAL, desatualizado.get().getNome());
            assertEquals(USERNAME, desatualizado.get().getUser().getUsername());
            assertEquals(PASSWORD, desatualizado.get().getUser().getPassword());

            var atualizado = UtilPacienteTest.montarPacienteRequestDto(NOME_ATUAL, USERNAME_ATUAL, PASSWORD_ATUAL);
            var response = controller.atualizarPaciente(pacienteDao.getId(), atualizado);

            assertEquals(atualizado.getNome(), response.nome());
            assertEquals(atualizado.getUser().getUsername(), response.user().username());
            assertEquals(atualizado.getUser().getPassword(), response.user().password());
            assertNotEquals(NOME_INICIAL, response.nome());
            assertNotEquals(USERNAME, response.user().username());
            assertNotEquals(PASSWORD, response.user().password());
        }

        @Test
        void dadoRequisicaoValida_quandoAtualizar_entaoAtualizarNoBanco() {
            var desatualizado = repository.findById(pacienteDao.getId());
            assertFalse(desatualizado.isEmpty());
            assertEquals(NOME_INICIAL, desatualizado.get().getNome());
            assertEquals(USERNAME, desatualizado.get().getUser().getUsername());
            assertEquals(PASSWORD, desatualizado.get().getUser().getPassword());

            var atualizado = UtilPacienteTest.montarPacienteRequestDto(NOME_ATUAL, USERNAME_ATUAL, PASSWORD_ATUAL);
            var response = controller.atualizarPaciente(pacienteDao.getId(), atualizado);

            var doBanco = repository.findById(response.id()).get();

            assertEquals(doBanco.getNome(), response.nome());
            assertEquals(doBanco.getUser().getUsername(), response.user().username());
            assertEquals(doBanco.getUser().getPassword(), response.user().password());
            assertNotEquals(NOME_INICIAL, doBanco.getNome());
            assertNotEquals(USERNAME, doBanco.getUser().getUsername());
            assertNotEquals(PASSWORD, doBanco.getUser().getPassword());
        }
    }
}