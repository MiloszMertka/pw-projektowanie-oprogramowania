package pl.edu.pw.ee.catering.presenter.meal.usecase.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pw.ee.catering.model.cateringcompany.service.ICateringCompany;
import pl.edu.pw.ee.catering.model.meal.dto.MealDetails;
import pl.edu.pw.ee.catering.presenter.meal.usecase.ICreateMealUC;

@Service
@RequiredArgsConstructor
public class CreateMealUCImpl implements ICreateMealUC {
        private final ICateringCompany cateringCompany;

        @Override
        public void createMeal(MealDetails mealDetails) {
            cateringCompany.createMeal(mealDetails);
        }
}
