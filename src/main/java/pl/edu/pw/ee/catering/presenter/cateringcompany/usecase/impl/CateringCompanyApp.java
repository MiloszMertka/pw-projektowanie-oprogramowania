package pl.edu.pw.ee.catering.presenter.cateringcompany.usecase.impl;

import com.vaadin.flow.component.UI;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;
import pl.edu.pw.ee.catering.model.cateringcompany.service.ICateringCompany;
import pl.edu.pw.ee.catering.model.meal.dto.MealDetails;
import pl.edu.pw.ee.catering.model.order.dto.OrderList;
import pl.edu.pw.ee.catering.presenter.cateringcompany.usecase.ICateringCompanyRouter;
import pl.edu.pw.ee.catering.presenter.cateringcompany.usecase.ICreateMealUC;
import pl.edu.pw.ee.catering.view.meal.ui.impl.CreateMealFormComponent;
import pl.edu.pw.ee.catering.view.order.ui.impl.HistoricalOrderListComponent;

@Component
@RequiredArgsConstructor
public class CateringCompanyApp implements ICateringCompanyRouter, ICreateMealUC {

    private final ICateringCompany cateringCompany;
    private final ObjectProvider<HistoricalOrderListComponent> historicalOrderListProvider;
    private final ObjectProvider<CreateMealFormComponent> createMealFormComponents;

    @Override
    public void navigateToHistoricalOrderList() {
        UI.getCurrent().navigate(HistoricalOrderListComponent.class);

        HistoricalOrderListComponent historicalOrderList = historicalOrderListProvider.getIfAvailable();
        if (historicalOrderList == null) {
            throw new IllegalStateException("HistoricalOrderListComponent not found");
        }

        OrderList orders = cateringCompany.showHistoricalOrderList(1L);
        historicalOrderList.showHistoricalOrderList(orders);
    }

    @Override
    public void navigateToCreateMealForm() {
        UI.getCurrent().navigate(CreateMealFormComponent.class);

        CreateMealFormComponent createMealForm = createMealFormComponents.getIfAvailable();
        if(createMealForm == null){
            throw new IllegalStateException("CreateMealFormComponent not found");
        }

        createMealForm.showCreateMealForm();


    }

    @Override
    public void createMeal(MealDetails mealDetails) {
        cateringCompany.createMeal(mealDetails);
    }
}
