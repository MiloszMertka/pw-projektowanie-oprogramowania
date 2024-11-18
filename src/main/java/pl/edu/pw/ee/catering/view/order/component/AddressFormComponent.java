package pl.edu.pw.ee.catering.view.order.component;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.server.VaadinSession;
import lombok.RequiredArgsConstructor;
import pl.edu.pw.ee.catering.presenter.client.usecase.IPlaceOrderUC;

@CssImport("./styles/order-form/address-form.css")
@RequiredArgsConstructor
public class AddressFormComponent extends VerticalLayout {

    private final IPlaceOrderUC placeOrderUC;

    private final TextField firstName;
    private final TextField lastName;
    private final TextField phone;
    private final EmailField email;
    private final TextField postalCode;
    private final TextField city;
    private final TextField street;
    private final TextArea courierNotes;

    public AddressFormComponent(IPlaceOrderUC placeOrderUC) {
        this.placeOrderUC = placeOrderUC;
        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        firstName = new TextField("Imię");
        lastName = new TextField("Nazwisko");
        phone = new TextField("Telefon");
        email = new EmailField("E-mail");
        postalCode = new TextField("Kod pocztowy");
        city = new TextField("Miasto");
        street = new TextField("Ulica, Nr. budynku/mieszkania");
        courierNotes = new TextArea("Uwagi dla kuriera");

        initLayout();
        addValidation();
    }

    private void initLayout() {
        H1 title = new H1("Dane adresowe");
        title.addClassName("form-title");

        HorizontalLayout nameLayout = new HorizontalLayout(firstName, lastName);
        HorizontalLayout contactLayout = new HorizontalLayout(phone, email);
        HorizontalLayout addressLayout = new HorizontalLayout(postalCode, city);

        Button proceedButton = new Button("Przejdź do płatności za zamówienie",
            buttonClickEvent -> submitForm());
        proceedButton.addClassName("proceed-button");

        Button backButton = new Button("Powrót", buttonClickEvent -> goBack());
        backButton.addClassName("back-button");

        HorizontalLayout buttonLayout = new HorizontalLayout(proceedButton, backButton);

        VerticalLayout formLayout = new VerticalLayout(title, nameLayout, contactLayout,
            addressLayout, street, courierNotes, buttonLayout);
        formLayout.setAlignItems(Alignment.STRETCH);
        formLayout.setWidth("400px");

        add(formLayout);
    }

    private void addValidation() {
        firstName.setRequiredIndicatorVisible(true);
        lastName.setRequiredIndicatorVisible(true);
        phone.setRequiredIndicatorVisible(true);
        email.setRequiredIndicatorVisible(true);
        postalCode.setRequiredIndicatorVisible(true);
        city.setRequiredIndicatorVisible(true);
        street.setRequiredIndicatorVisible(true);

        firstName.setErrorMessage("To pole nie może być puste");
        lastName.setErrorMessage("To pole nie może być puste");
        phone.setErrorMessage("Wprowadź poprawny numer telefonu (format: 9 cyfr)");
        email.setErrorMessage("Wprowadź poprawny adres e-mail");
        postalCode.setErrorMessage("Wprowadź poprawny kod pocztowy (format: xx-xxx)");
        city.setErrorMessage("To pole nie może być puste");
        street.setErrorMessage("To pole nie może być puste");

        firstName.setValueChangeMode(ValueChangeMode.EAGER);
        lastName.setValueChangeMode(ValueChangeMode.EAGER);
        phone.setValueChangeMode(ValueChangeMode.EAGER);
        email.setValueChangeMode(ValueChangeMode.EAGER);
        postalCode.setValueChangeMode(ValueChangeMode.EAGER);
        city.setValueChangeMode(ValueChangeMode.EAGER);
        street.setValueChangeMode(ValueChangeMode.EAGER);

        firstName.addValueChangeListener(event -> validateRequiredField(firstName));
        lastName.addValueChangeListener(event -> validateRequiredField(lastName));
        phone.addValueChangeListener(event -> validateRequiredField(phone));
        email.addValueChangeListener(event -> validateRequiredField(email));
        postalCode.addValueChangeListener(event -> validateRequiredField(postalCode));
        city.addValueChangeListener(event -> validateRequiredField(city));
        street.addValueChangeListener(event -> validateRequiredField(street));
    }

    private void validateRequiredField(TextField field) {
        if (field.isEmpty()) {
            field.setInvalid(true);
            field.setErrorMessage("To pole jest wymagane");
        } else {
            field.setInvalid(false);
        }
    }

    private void validateRequiredField(EmailField field) {
        if (field.isEmpty()) {
            field.setInvalid(true);
            field.setErrorMessage("To pole jest wymagane");
        } else {
            field.setInvalid(false);
        }
    }

    private void submitForm() {
        if (validateForm()) {
            VaadinSession.getCurrent().setAttribute("firstName", firstName.getValue());
            VaadinSession.getCurrent().setAttribute("lastName", lastName.getValue());
            VaadinSession.getCurrent().setAttribute("phone", phone.getValue());
            VaadinSession.getCurrent().setAttribute("email", email.getValue());
            VaadinSession.getCurrent().setAttribute("address",
                street.getValue() + ", " + postalCode.getValue() + " " + city.getValue());
            VaadinSession.getCurrent().setAttribute("courierNotes", courierNotes.getValue());

            removeAll();
            OrderSummaryComponent orderSummaryComponent = new OrderSummaryComponent(placeOrderUC);
            add(orderSummaryComponent);
        }
    }

    private void goBack() {
        getUI().ifPresent(ui -> ui.navigate("/client"));
    }

    private boolean validateForm() {
        boolean isValid = true;

        if (firstName.isEmpty()) {
            firstName.setInvalid(true);
            isValid = false;
        }
        if (lastName.isEmpty()) {
            lastName.setInvalid(true);
            isValid = false;
        }
        if (phone.isEmpty() || !phone.getValue().matches("\\d{9}")) {
            phone.setInvalid(true);
            isValid = false;
        }
        if (email.isEmpty()) {
            email.setInvalid(true);
            isValid = false;
        }
        if (postalCode.isEmpty() || !postalCode.getValue().matches("\\d{2}-\\d{3}")) {
            postalCode.setInvalid(true);
            isValid = false;
        }
        if (city.isEmpty()) {
            city.setInvalid(true);
            isValid = false;
        }
        if (street.isEmpty()) {
            street.setInvalid(true);
            isValid = false;
        }

        return isValid;
    }
}