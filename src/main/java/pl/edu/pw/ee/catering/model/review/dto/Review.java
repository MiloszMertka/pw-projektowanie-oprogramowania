package pl.edu.pw.ee.catering.model.review.dto;

import lombok.*;
import pl.edu.pw.ee.catering.model.personaldata.dto.PersonalData;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Review {
    private long id;
    private String description;
    private Integer rating;
    private PersonalData personalData;
}
