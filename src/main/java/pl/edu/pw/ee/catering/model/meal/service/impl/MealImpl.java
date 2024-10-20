package pl.edu.pw.ee.catering.model.meal.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pw.ee.catering.model.meal.dto.MealDetails;
import pl.edu.pw.ee.catering.model.meal.dto.MealList;
import pl.edu.pw.ee.catering.model.meal.entity.Meal;
import pl.edu.pw.ee.catering.model.meal.repository.MealRepository;
import pl.edu.pw.ee.catering.model.meal.service.IMeal;

@Service
@RequiredArgsConstructor
class MealImpl implements IMeal {
    private final MealRepository mealRepository;

    @Override
    public void createMeal(MealDetails meal) {
        Meal newMeal = mapMealDetailsToEntity(meal);
        mealRepository.save(newMeal);
    }

    public void deleteMeal(Long id) {
        validateMealExists(id);
        mealRepository.deleteById(id);
    }

    @Override
    public MealList getMealList(Long id) {
        return new MealList(mealRepository.findAllByCompanyId(id));
    }

    private void validateMealExists(Long id) {
        if (!mealRepository.existsById(id)) {
            throw new IllegalArgumentException("Meal with id " + id + " does not exist");
        }
    }

    private Meal mapMealDetailsToEntity(MealDetails mealDetails) {
        return Meal.builder()
                .name(mealDetails.getName())
                .caloricity(mealDetails.getCaloricity())
                .price(mealDetails.getPrice())
                .image(mealDetails.getImage())
                .ingredients(mealDetails.getIngredients())
                .availability(mealDetails.isAvailability())
                .description(mealDetails.getDescription())
                .companyId(mealDetails.getCompanyId())
                .build();
    }
}
