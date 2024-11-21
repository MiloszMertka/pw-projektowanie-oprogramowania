package pl.edu.pw.ee.catering.view.client.ui;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import pl.edu.pw.ee.catering.presenter.client.usecase.IClientRouter;

@UIScope
@SpringComponent
@Route("/client")
public class ClientUI extends VerticalLayout {

    private final IClientRouter router;

    public ClientUI(IClientRouter router) {
        this.router = router;
        initLayout();
    }

    private void initLayout() {
        Button placeOrderButton = new Button("Złóż zamówienie",
            event -> router.navigateToPlaceOrderForm());
        add(placeOrderButton);

        Button showClientMealListButton = new Button("Pokaż listę posiłków",
            event -> router.navigateToClientMealList());
        add(showClientMealListButton);

        Button clientOrderListButton = new Button("Wyświetl zamówienia klienta",
                event -> router.navigateToClientOrderList());
        add(clientOrderListButton);
        Button clientHistoricalListButton = new Button("Wyświetl historyczne zamówienia klienta",
                event -> router.navigateToClientHistoricalOrderList());
        add(clientHistoricalListButton);
        Button modifyClientOrderButton = new Button("Modifikuj zamówienie",
            event -> router.navigateToUpdateOrderView(1L));
        add(modifyClientOrderButton);

        Button showClientOrderDetails = new Button("Szczegóły zamówienia",
            event -> router.navigateToOrderDetails(1L));
        add(showClientOrderDetails);
    }
}
