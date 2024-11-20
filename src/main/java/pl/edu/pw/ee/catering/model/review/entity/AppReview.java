package pl.edu.pw.ee.catering.model.review.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import pl.edu.pw.ee.catering.model.personaldata.entity.AppPersonalData;

import java.util.List;
import java.util.Objects;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AppReview {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private String description;

    @Column(nullable = false)
    private Integer rating;

    @Embedded
    @NotNull
    private AppPersonalData personalData;
}
