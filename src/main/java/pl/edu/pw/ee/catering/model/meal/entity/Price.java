package pl.edu.pw.ee.catering.model.meal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Currency;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Price {

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private Currency currency;

}
