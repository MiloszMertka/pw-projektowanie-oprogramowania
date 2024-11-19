package pl.edu.pw.ee.catering.view.meal.component;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

@CssImport("./styles/meal-list/meal-list-component.css")
public class ClientMealListMessageComponent extends VerticalLayout {

    public ClientMealListMessageComponent(String message) {
        initLayout(message);
    }

    private void initLayout(String message) {
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        H1 title = new H1(message);
        add(title);

        Button closeButton = new Button("Powrót", event -> {
            getUI().ifPresent(ui -> ui.navigate("client"));
        });

        add(closeButton);
    }
}
