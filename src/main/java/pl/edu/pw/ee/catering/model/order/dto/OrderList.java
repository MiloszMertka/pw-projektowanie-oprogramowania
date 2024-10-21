package pl.edu.pw.ee.catering.model.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class OrderList {
    private List<OrderWithDetails> orders;
}
