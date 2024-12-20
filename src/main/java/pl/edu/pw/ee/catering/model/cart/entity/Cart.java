package pl.edu.pw.ee.catering.model.cart.entity;

import jakarta.persistence.*;
import lombok.*;
import pl.edu.pw.ee.catering.model.meal.entity.Meal;

import java.util.List;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Cart { // Consulted with architect - diagram 'Architektura logiczna - struktura' has to be updated

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /// Since there will be only 1 user (due to no authentication) it should be set to always one number
    @Column(nullable = false)
    private Long clientId;

    @ManyToMany(fetch = FetchType.EAGER)
    private List<Meal> meals;

}
