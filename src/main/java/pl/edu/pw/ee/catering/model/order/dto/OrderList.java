package pl.edu.pw.ee.catering.model.order.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.edu.pw.ee.catering.model.order.entity.AppOrder;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class OrderList {
    private List<AppOrder> orders;
}
