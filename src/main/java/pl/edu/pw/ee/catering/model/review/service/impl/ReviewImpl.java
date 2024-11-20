package pl.edu.pw.ee.catering.model.review.service.impl;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import pl.edu.pw.ee.catering.model.review.dto.Review;
import pl.edu.pw.ee.catering.model.review.entity.AppReview;
import pl.edu.pw.ee.catering.model.review.repository.ReviewRepository;
import pl.edu.pw.ee.catering.model.review.service.IReview;
import pl.edu.pw.ee.catering.model.personaldata.dto.PersonalData;
import pl.edu.pw.ee.catering.model.personaldata.entity.AppPersonalData;

@Service
@RequiredArgsConstructor
public class ReviewImpl implements IReview {
    private final ReviewRepository reviewRepository;
    
    @Override
    public void createReview(Review review) {
        reviewRepository.save(mapReviewToAppReview(review));
    }
    
    private AppReview mapReviewToAppReview(Review review) {
        return AppReview.builder()
            .description(review.getDescription())
            .rating(review.getRating())
            .personalData(mapPersonalDataToAppPersonalData(review.getPersonalData()))
            .build();
    }
    
    private AppPersonalData mapPersonalDataToAppPersonalData(PersonalData personalData) {
        return AppPersonalData.builder()
            .firstName(personalData.getFirstName())
            .lastName(personalData.getLastName())
            .email(personalData.getEmail())
            .build();
    }
}