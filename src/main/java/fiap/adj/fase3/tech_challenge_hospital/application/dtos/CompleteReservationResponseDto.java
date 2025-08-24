package fiap.adj.fase3.tech_challenge_hospital.application.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.graphql.data.method.annotation.SchemaMapping;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SchemaMapping("CompleteReservationResponse")
public class CompleteReservationResponseDto {

    private String message;

    private Long totalDays;
}
