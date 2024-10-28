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
        Button mealListButton = new Button("Wyświetl listę własnych ofert kateringowych",
            event -> router.navigateToMealList());

        add(mealListButton);
    }
}
