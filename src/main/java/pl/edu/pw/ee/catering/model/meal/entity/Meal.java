package pl.edu.pw.ee.catering.model.meal.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Objects;

@Entity
public class Meal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer caloricity;

    @Embedded
    @NotNull
    private Price price;

    @Embedded
    private Image image;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Ingredient> ingredients;

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Meal meal)) return false;
        return Objects.equals(id, meal.id);
    }
}
