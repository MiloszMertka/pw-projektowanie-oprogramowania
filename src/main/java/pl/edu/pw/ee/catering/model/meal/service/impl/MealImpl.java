package pl.edu.pw.ee.catering.model.meal.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pw.ee.catering.model.meal.repository.MealRepository;
import pl.edu.pw.ee.catering.model.meal.service.IMeal;

@Service
@RequiredArgsConstructor
class MealImpl implements IMeal {
    private final MealRepository mealRepository;

    public void deleteMeal(Long id) {
        validateMealExists(id);
        mealRepository.deleteById(id);
    }

    private void validateMealExists(Long id) {
        if (!mealRepository.existsById(id)) {
            throw new IllegalArgumentException("Meal with id " + id + " does not exist");
        }
    }
}
