package pl.edu.pw.ee.catering.view.meal.component.util.buttons;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;

@CssImport("./styles/meal-list/buttons.css")
public class EditMealButton extends Button {

    public EditMealButton() {
        super("Edytuj posiłek");

        addClassName("edit-meal-button");
    }
}
