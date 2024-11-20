package pl.edu.pw.ee.catering.model.review.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.edu.pw.ee.catering.model.review.entity.AppReview;

@Repository
public interface ReviewRepository extends JpaRepository<AppReview, Long> {
}
