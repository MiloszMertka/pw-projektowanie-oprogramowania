package pl.edu.pw.ee.catering.view.cateringcompany.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import pl.edu.pw.ee.catering.presenter.cateringcompany.usecase.ICateringCompanyRouter;


@Route("test")
public class CateringCompanyUI extends VerticalLayout {

    private final ICateringCompanyRouter router;

    public CateringCompanyUI(ICateringCompanyRouter router) {
        this.router = router;
        initLayout();
    }

    private void initLayout() {
        Button createMealFormButton = new Button("Stwórz nowy posiłek",
                event -> router.navigateToCreateMealForm());

        add(createMealFormButton);
    }
}
