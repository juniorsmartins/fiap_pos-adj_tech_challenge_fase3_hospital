package fiap.adj.fase3.tech_challenge_hospital.application.configs.kafka;

import fiap.adj.fase3.tech_challenge_hospital.application.dtos.external.MensagemKafka;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.internals.Acknowledgements;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public final class KafkaConsumer {

    @KafkaListener(topics = "${spring.kafka.topic.evento-informar-paciente-consulta}", groupId = "grupo-mensagem-kafka", containerFactory = "kafkaListenerContainerFactory")
    public void consumirEventoConsulta(MensagemKafka mensagem, Acknowledgements ack) {

        try {
            log.info("Mensagem recebida no tópico de eventos de consulta: {}", mensagem);
            ack.isCompleted(); // Confirmar o processamento da mensagem
        } catch (Exception e) {
            log.error("Erro ao processar a mensagem: {}", e.getMessage());
        }
    }
}
