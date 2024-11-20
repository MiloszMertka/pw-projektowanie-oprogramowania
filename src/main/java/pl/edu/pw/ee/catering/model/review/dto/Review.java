package pl.edu.pw.ee.catering.model.review.dto;

import pl.edu.pw.ee.catering.model.personaldata.dto.PersonalData;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class Review {
    private long id;
    private String description;
    private Integer rating;
    private PersonalData personalData;
}
