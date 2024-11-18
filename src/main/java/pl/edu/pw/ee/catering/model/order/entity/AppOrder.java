package pl.edu.pw.ee.catering.model.order.entity;

import jakarta.persistence.*;
import lombok.*;
import pl.edu.pw.ee.catering.model.order.dto.OrderStatus;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AppOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String date;

    @Column(nullable = false)
    private OrderStatus status;

    @Column(nullable = false) // Consulted with architect - diagram 'Architektura logiczna - struktura' has to be updated
    private Long companyId = 1L;

    @Column(nullable = false)
    private Long clientId = 1L;
}
