package pl.edu.pw.ee.catering.model.meal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Currency;

@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Price {

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private Currency currency;
}
