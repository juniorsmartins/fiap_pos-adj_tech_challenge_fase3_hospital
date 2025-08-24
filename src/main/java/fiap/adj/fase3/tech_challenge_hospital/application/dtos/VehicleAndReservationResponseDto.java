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
@SchemaMapping("VehicleAndReservationResponse")
public class VehicleAndReservationResponseDto {

    private VehicleDto vehicle;

    private ReservationDto reservation;
}
