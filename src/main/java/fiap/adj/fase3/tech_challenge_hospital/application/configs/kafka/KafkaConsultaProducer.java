package fiap.adj.fase3.tech_challenge_hospital.application.configs.kafka;

import fiap.adj.fase3.tech_challenge_hospital.application.dtos.external.ConsultaKafka;
import fiap.adj.fase3.tech_challenge_hospital.application.dtos.internal.ConsultaDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public final class KafkaConsultaProducer {

    private final String topicoConsultaInformarPaciente;

    private final KafkaTemplate<String, ConsultaKafka> kafkaTemplate;

    public KafkaConsultaProducer(@Value("${spring.kafka.topic.consulta-informar-paciente}") String topico,
                                 KafkaTemplate<String, ConsultaKafka> kafkaTemplate) {
        topicoConsultaInformarPaciente = topico;
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(ConsultaDto consultaDto) {
        var mensagem = embalarMensagem(consultaDto);
        kafkaTemplate.send(topicoConsultaInformarPaciente, mensagem.id().toString(), mensagem);
    }

    private ConsultaKafka embalarMensagem(ConsultaDto dto) {
        return new ConsultaKafka(dto.getId(), dto.getDataHora(), dto.getStatus(), dto.getMedico().nome(), dto.getPaciente().nome(), null);
    }
}
