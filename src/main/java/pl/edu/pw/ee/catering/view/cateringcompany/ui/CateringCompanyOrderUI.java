package pl.edu.pw.ee.catering.view.cateringcompany.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import pl.edu.pw.ee.catering.presenter.cateringcompany.usecase.ICateringCompanyRouter;

@Route("orders")
public class CateringCompanyOrderUI extends VerticalLayout {
    private final ICateringCompanyRouter router;

    public CateringCompanyOrderUI(ICateringCompanyRouter router) {
        this.router = router;
        initLayout();
    }

    private void initLayout() {
        Button historicalOrdersButton = new Button("Wyświetl historyczne zamówienia",
                event -> router.navigateToHistoricalOrderList());

        add(historicalOrdersButton);
    }
}
