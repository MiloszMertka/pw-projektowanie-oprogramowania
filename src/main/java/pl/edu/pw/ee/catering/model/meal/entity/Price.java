package pl.edu.pw.ee.catering.model.meal.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Currency;

@Embeddable
public class Price {

    @Column(nullable = false)
    private Double amount;

    @Column(nullable = false)
    private Currency currency;
}
