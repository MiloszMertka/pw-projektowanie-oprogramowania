package pl.edu.pw.ee.catering.model.personaldata.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class PersonalData {
    private String email;
    private String firstName;
    private String lastName;
}
