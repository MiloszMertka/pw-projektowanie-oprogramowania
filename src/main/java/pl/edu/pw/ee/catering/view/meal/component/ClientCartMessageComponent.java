package pl.edu.pw.ee.catering.view.meal.component;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import pl.edu.pw.ee.catering.presenter.client.usecase.IClientRouter;

@CssImport("./styles/meal-list/meal-list-component.css")
public class ClientCartMessageComponent extends VerticalLayout {

    private final IClientRouter router;

    public ClientCartMessageComponent(IClientRouter router) {
        this.router = router;
        initLayout();
    }

    private void initLayout() {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        H1 title = new H1("Dodano posiłek do koszyka!");
        add(title);

        Button closeButton = new Button("Powrót", event -> router.navigateToClientMealList());

        add(closeButton);
    }
}
