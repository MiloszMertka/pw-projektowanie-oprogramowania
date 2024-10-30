package pl.edu.pw.ee.catering.view.meal.component.util;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import java.util.stream.Collectors;
import pl.edu.pw.ee.catering.model.meal.entity.Ingredient;
import pl.edu.pw.ee.catering.model.meal.entity.Meal;
import pl.edu.pw.ee.catering.view.meal.component.util.buttons.EditMealButton;

@CssImport("./styles/meal-list/meal-card.css")
public class MealCard extends Div {

    public MealCard(Meal meal) {
        addClassName("meal-card");

        Image image = new Image("https://placehold.co/132x132", "Meal Image");
        image.addClassName("image");

        NativeLabel name = new NativeLabel(meal.getName());
        name.addClassName("meal-card-name");

        NativeLabel ingredients = new NativeLabel("Skład: " + meal.getIngredients().stream()
            .map(Ingredient::getName).collect(Collectors.joining(", ")));
        ingredients.addClassName("meal-card-ingredients");

        HorizontalLayout priceLayout = createLabeledValue("Cena: ",
            meal.getPrice().getAmount() + " " + meal.getPrice().getCurrency());
        HorizontalLayout caloriesLayout = createLabeledValue("Kaloryczność: ",
            meal.getCaloricity() + " kcal");

        HorizontalLayout priceCaloriesLayout = new HorizontalLayout(priceLayout, caloriesLayout);
        priceCaloriesLayout.addClassName("price-calories-layout");

        VerticalLayout infoLayout = new VerticalLayout(name, ingredients, priceCaloriesLayout);
        infoLayout.addClassName("info-layout");

        HorizontalLayout imageInfoLayout = new HorizontalLayout(image, infoLayout);
        imageInfoLayout.addClassName("image-info-layout");

        EditMealButton editButton = new EditMealButton();
        editButton.addClassName("edit-button");

        HorizontalLayout cardLayout = new HorizontalLayout(imageInfoLayout, editButton);
        cardLayout.addClassName("card-layout");

        add(cardLayout);
    }

    private HorizontalLayout createLabeledValue(String label, String value) {
        NativeLabel labelComponent = new NativeLabel(label);
        labelComponent.addClassName("label-component");

        NativeLabel valueComponent = new NativeLabel(value);
        valueComponent.addClassName("value-component");

        HorizontalLayout layout = new HorizontalLayout(labelComponent, valueComponent);
        layout.setSpacing(false);

        return layout;
    }
}
