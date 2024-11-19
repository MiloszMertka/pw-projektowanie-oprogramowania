package pl.edu.pw.ee.catering.view.meal.component.util.buttons;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;

@CssImport("./styles/meal-list/buttons.css")
public class AddToCartButton extends Button {

    public AddToCartButton() {
        super("Dodaj do koszyka");
        addClassName("add-to-cart-button");
    }
}