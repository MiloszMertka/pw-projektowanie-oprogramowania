package pl.edu.pw.ee.catering.presenter.meal.usecase.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pw.ee.catering.model.cateringcompany.service.ICateringCompany;
import pl.edu.pw.ee.catering.presenter.meal.usecase.IDeleteMealUC;

@Service
@RequiredArgsConstructor
class DeleteMealUCImpl implements IDeleteMealUC {
    private final ICateringCompany cateringCompany;

    @Override
    public void deleteMeal(Long id) {
        cateringCompany.deleteMeal(id);
    }
}
