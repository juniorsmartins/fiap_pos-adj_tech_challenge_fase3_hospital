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
@SchemaMapping("Reservation")
public class ReservationDto {

    private Long reservationId;

    private Long vehicleId;

    private Long userId;

    private String startDate;

    private String endDate;

    private String status;
}
