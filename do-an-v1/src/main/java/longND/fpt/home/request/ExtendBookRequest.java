package longND.fpt.home.request;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExtendBookRequest {
    private Long orderId;
    private LocalDateTime returnDate;
}
