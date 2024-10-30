package pl.edu.pw.ee.catering.model.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class OrderWithDetails {
    private long id;
    private String name;
    private String date;
    private OrderStatus status;
}
