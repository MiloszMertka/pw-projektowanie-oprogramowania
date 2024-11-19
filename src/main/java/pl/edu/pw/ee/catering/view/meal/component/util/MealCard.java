package pl.edu.pw.ee.catering.view.meal.component.util;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.NativeLabel;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import java.util.stream.Collectors;
import pl.edu.pw.ee.catering.model.meal.entity.Ingredient;
import pl.edu.pw.ee.catering.model.meal.entity.Meal;
import pl.edu.pw.ee.catering.presenter.client.usecase.IAddMealToCartUC;
import pl.edu.pw.ee.catering.view.meal.component.util.buttons.AddToCartButton;
import pl.edu.pw.ee.catering.view.meal.component.util.buttons.DeleteMealButton;
import pl.edu.pw.ee.catering.view.meal.component.util.buttons.EditMealButton;

@CssImport("./styles/meal-list/meal-card.css")
public class MealCard extends Div {

    public MealCard(Meal meal, boolean isMutable, IAddMealToCartUC addMealToCartUC) {
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

        HorizontalLayout cardLayout = getHorizontalLayout(meal, isMutable, imageInfoLayout, addMealToCartUC);

        add(cardLayout);
    }

    private HorizontalLayout getHorizontalLayout(Meal meal, boolean isMutable, HorizontalLayout imageInfoLayout,
                                                 IAddMealToCartUC addMealToCartUC) {
        EditMealButton editButton = null;
        DeleteMealButton deleteMealButton = null;
        AddToCartButton addToCartButton = null;

        if (isMutable) {
            editButton = new EditMealButton();
            editButton.addClassName("edit-button");

            deleteMealButton = new DeleteMealButton();
            deleteMealButton.addClassName("delete-button");

            editButton.addClickListener(event ->
                    UI.getCurrent().navigate("edit-meal-form/" + meal.getId()));

            deleteMealButton.addClickListener(event ->
                    UI.getCurrent().navigate("delete-meal/" + meal.getId()));
        } else {
            addToCartButton = new AddToCartButton();
            addToCartButton.addClickListener(event -> {
                addMealToCartUC.addMealToCart(meal.getId());
            });
        }

        HorizontalLayout cardLayout = isMutable ? new HorizontalLayout(imageInfoLayout, editButton, deleteMealButton)
                : new HorizontalLayout(imageInfoLayout, addToCartButton);
        cardLayout.addClassName("card-layout");
        return cardLayout;
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
