package pl.edu.pw.ee.catering.view.cateringcompany.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import pl.edu.pw.ee.catering.presenter.cateringcompany.usecase.ICateringCompanyRouter;

@UIScope
@SpringComponent
@Route("")
public class CateringCompanyUI extends VerticalLayout {

    private final ICateringCompanyRouter router;

    public CateringCompanyUI(ICateringCompanyRouter router) {
        this.router = router;
        initLayout();
    }

    private void initLayout() {
        Button historicalOrderListButton = new Button("Wyświetl historyczne zamówienia",
                event -> router.navigateToHistoricalOrderList());
        add(historicalOrderListButton);

        Button orderListButton = new Button("Wyświetl zamówienia",
                event -> router.navigateToOrderList());
        add(orderListButton);

//        Button orderDetailsButton = new Button("Przykładowe zamówienie",
//                event -> router.navigateToOrderDetails(1L));
//        add(orderDetailsButton);

        Button createMealFormButton = new Button("Stwórz nowy posiłek",
                event -> router.navigateToCreateMealForm());
        add(createMealFormButton);

        Button mealListButton = new Button("Wyświetl listę własnych ofert kateringowych",
                event -> router.navigateToMealList());
        add(mealListButton);
    }
}
