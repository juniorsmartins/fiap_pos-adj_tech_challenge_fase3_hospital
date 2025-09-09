package fiap.adj.fase3.tech_challenge_hospital.infrastructure.controllers;

import fiap.adj.fase3.tech_challenge_hospital.UtilsTest;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.daos.MedicoDao;
import fiap.adj.fase3.tech_challenge_hospital.infrastructure.repositories.MedicoRepository;
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
class MedicoControllerIntegrationTest {

    private static final String NOME_MEDICO1 = "Médico 1";

    private static final String USERNAME = "username123";

    private static final String PASSWORD = "password123";

    @Autowired
    private MedicoController medicoController;

    @Autowired
    private MedicoRepository medicoRepository;

    private MedicoDao medicoDao;

    @BeforeEach
    void setUp() {
        medicoDao = UtilsTest.montarMedicoDao(NOME_MEDICO1, USERNAME, PASSWORD);
        medicoRepository.save(medicoDao);
    }

    @Nested
    @DisplayName("Criar")
    class criar {

        @Test
        void dadoRequisicaoValida_quandoCriar_entaoRetornarResponseComDadosValidos() {
            // Arrange
            var medicoRequestDto = UtilsTest
                    .montarMedicoRequestDto(NOME_MEDICO1, USERNAME, PASSWORD);
            // Act
            var response = medicoController.criarMedico(medicoRequestDto);
            // Assert
            assertNotNull(response.id());
            assertEquals(medicoRequestDto.getNome(), response.nome());
            assertEquals(medicoRequestDto.getUser().getUsername(), response.user().username());
        }

        @Test
        void dadoRequisicaoValida_quandoCriar_entaoSalvarDadosValidosNoBanco() {
            var medicoRequestDto = UtilsTest
                    .montarMedicoRequestDto(NOME_MEDICO1, USERNAME, PASSWORD);
            var response = medicoController.criarMedico(medicoRequestDto);
            var dadoSalvo = medicoRepository.findById(response.id()).orElseThrow();
            assertEquals(medicoRequestDto.getNome(), dadoSalvo.getNome());
            assertEquals(medicoRequestDto.getUser().getUsername(), dadoSalvo.getUser().getUsername());
        }
    }

    @Nested
    @DisplayName("Consultar")
    class Consultar {

        @Test
        void dadoIdValido_quandoConsultarPorId_entaoRetornarResponseValido() {
            var response = medicoController.consultarMedicoPorId(medicoDao.getId());
            assertEquals(medicoDao.getId(), response.id());
            assertEquals(medicoDao.getNome(), response.nome());
            assertEquals(medicoDao.getUser().getUsername(), response.user().username());
        }
    }

    @Nested
    @DisplayName("Apagar")
    class Apagar {

        @Test
        void dadoIdValido_quandoApagarPorId_entaoRetornarTrue() {
            var response = medicoController.apagarMedico(medicoDao.getId());
            assertTrue(response);
        }

        @Test
        void dadoIdValido_quandoApagarPorId_entaoDeletarDoBanco() {
            var dao = medicoRepository.findById(medicoDao.getId());
            assertFalse(dao.isEmpty());

            var response = medicoController.apagarMedico(medicoDao.getId());
            assertTrue(response);

            var daoApagado = medicoRepository.findById(medicoDao.getId());
            assertTrue(daoApagado.isEmpty());
        }
    }

    @Nested
    @DisplayName("Atualizar")
    class Atualizar {

        @Test
        void dadoRequisicaoValida_quandoAtualizar_entaoRetornarResponseValido() {
            var medicoDesatualizado = medicoRepository.findById(medicoDao.getId());
            assertFalse(medicoDesatualizado.isEmpty());
            assertEquals(NOME_MEDICO1, medicoDesatualizado.get().getNome());
            assertEquals(USERNAME, medicoDesatualizado.get().getUser().getUsername());
            assertEquals(PASSWORD, medicoDesatualizado.get().getUser().getPassword());

            var medicoAtualizado = UtilsTest
                    .montarMedicoRequestDto("MédicoAtual", "username999", "password999");
            var response = medicoController.atualizarMedico(medicoDao.getId(), medicoAtualizado);

            assertEquals(medicoAtualizado.getNome(), response.nome());
            assertEquals(medicoAtualizado.getUser().getUsername(), response.user().username());
            assertEquals(medicoAtualizado.getUser().getPassword(), response.user().password());
            assertNotEquals(NOME_MEDICO1, response.nome());
            assertNotEquals(USERNAME, response.user().username());
        }
    }
}