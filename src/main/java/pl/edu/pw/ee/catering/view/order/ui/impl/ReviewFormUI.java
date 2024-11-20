package pl.edu.pw.ee.catering.view.order.ui.impl;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.pw.ee.catering.model.personaldata.dto.PersonalData;
import pl.edu.pw.ee.catering.model.review.dto.Review;
import pl.edu.pw.ee.catering.model.review.service.impl.ReviewImpl;
import pl.edu.pw.ee.catering.presenter.cateringcompany.usecase.ICateringCompanyRouter;
import pl.edu.pw.ee.catering.view.order.ui.IReviewForm;

@UIScope
@SpringComponent
@Route("client-review")
@CssImport("./styles/order-form/summary-form.css")
public class ReviewFormUI extends VerticalLayout implements IReviewForm {
    private final ICateringCompanyRouter router;

    @Autowired
    private final ReviewImpl reviewService;

    ReviewFormUI(ICateringCompanyRouter router, ReviewImpl reviewService){
        this.router = router;
        this.reviewService = reviewService;
    }
    @Override
    public void showReviewForm(Long Id) {
        removeAll();
        this.setAlignItems(Alignment.CENTER);
        this.setJustifyContentMode(JustifyContentMode.CENTER);

        IntegerField reviewRating = new IntegerField("Ocena");
        SetIntegerField(reviewRating);

        TextArea reviewText = new TextArea("Treść opinii");
        SetTextField(reviewText);

        Button addButton = new Button("Złóż opinię");
        Button stopButton = new Button("Anuluj");

        addButton.addClassName("proceed-button");

        FormLayout formLayout = new FormLayout();

        addButton.addClickListener(buttonClickEvent -> {
            if (reviewText.isInvalid() || reviewRating.isInvalid()) {
                Notification notification = Notification.show("Pola są niepoprawnie wypełnione.");
                notification.setDuration(3000);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                notification.setPosition(Notification.Position.TOP_CENTER);
            } else {
                String reviewContent = reviewText.getValue();
                int rating = reviewRating.getValue();

                PersonalData data = new PersonalData("mail", "imię", "nazwisko");
                Review review = new Review();
                review.setDescription(reviewContent);
                review.setRating(rating);
                review.setPersonalData(data);

                reviewService.createReview(review);

                router.navigateToClientHistoricalOrderList();
                Notification notification = Notification.show("Dodano opinię");
                notification.setDuration(6000);
                notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS);
                notification.setPosition(Notification.Position.TOP_CENTER);
            }
        });

        stopButton.addClickListener(e -> {
            router.navigateToClientHistoricalOrderList();
        });

        H1 title = new H1("Opinia");
        title.addClassName("form-title");

        formLayout.add(reviewRating, reviewText, addButton, stopButton);
        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1));

        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setAlignSelf(Alignment.CENTER);
        verticalLayout.setAlignItems(Alignment.CENTER);
        verticalLayout.setWidth("800px");
        verticalLayout.add(title, formLayout);
        add(verticalLayout);
    }

    private void SetIntegerField(IntegerField field) {
        field.setMin(0);
        field.setMax(5);
        field.setValue(0);
        field.setStep(1);
        field.setHelperText("Wprowadź ocenę od 0 do 5.");
        field.setRequiredIndicatorVisible(true);

        field.addValueChangeListener(e -> {
            if (e.getValue() < field.getMin() || e.getValue() > field.getMax()) {
                field.setInvalid(true);
                field.setErrorMessage("Ocena musi być w zakresie od 0 do 5.");
            } else {
                field.setInvalid(false);
            }
        });
    }



    private void SetTextField(TextArea text){
        int charLimit = 1600;
        text.setMaxLength(charLimit);
        text.setValueChangeMode(ValueChangeMode.EAGER);
        text.setRequired(true);
        text.addValueChangeListener(e -> {
            e.getSource()
                    .setHelperText("%d/%d".formatted(e.getValue().length(), charLimit));
        });
        text.setI18n(new TextArea.TextAreaI18n()
                .setMaxLengthErrorMessage("Zbyt długi opis")
                .setRequiredErrorMessage("Pole jest wymagane"));


        text.setInvalid(true);
    }
}

