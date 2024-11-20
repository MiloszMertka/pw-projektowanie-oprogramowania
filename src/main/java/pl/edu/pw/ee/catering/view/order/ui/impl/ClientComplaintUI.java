package pl.edu.pw.ee.catering.view.order.ui.impl;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.UploadI18N;
import com.vaadin.flow.component.upload.receivers.FileBuffer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import pl.edu.pw.ee.catering.model.meal.dto.MealDetails;
import pl.edu.pw.ee.catering.model.meal.entity.Image;
import pl.edu.pw.ee.catering.model.meal.entity.Ingredient;
import pl.edu.pw.ee.catering.model.meal.entity.Price;
import pl.edu.pw.ee.catering.model.order.dto.OrderStatus;
import pl.edu.pw.ee.catering.model.order.dto.OrderWithDetails;
import pl.edu.pw.ee.catering.presenter.cateringcompany.usecase.ICateringCompanyRouter;
import pl.edu.pw.ee.catering.presenter.order.usecase.IChangeOrderStatusUc;
import pl.edu.pw.ee.catering.view.order.ui.IChangeOrderStatus;
import pl.edu.pw.ee.catering.view.order.ui.IPlaceComplaintForm;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Objects;

@UIScope
@SpringComponent
@Route("client-complaint")
@CssImport("./styles/order-form/summary-form.css")
public class ClientComplaintUI extends VerticalLayout implements IPlaceComplaintForm {
    private final ICateringCompanyRouter router;
    private final IChangeOrderStatusUc complainP;
    ClientComplaintUI(ICateringCompanyRouter router, IChangeOrderStatusUc cp){
        this.router = router;
        this.complainP=cp;
        //showPlaceComplaintForm(new OrderWithDetails(0,"da","21", OrderStatus.FINISHED));
    }

    @Override
    public void showPlaceComplaintForm(Long Id) {
        removeAll();
        this.setAlignItems(Alignment.CENTER);
        this.setJustifyContentMode(JustifyContentMode.CENTER);

        TextArea complaintText = new TextArea("Treść reklamacji");
        SetTextField(complaintText);
        Button addButton = new Button("Złóż reklamacje");
        Button stopButton = new Button("Anuluj");

        addButton.addClassName("proceed-button");

        FormLayout formLayout = new FormLayout();

        addButton.addClickListener( buttonClickEvent -> {
            if(complaintText.isInvalid()){
                Notification notification = Notification.show("Pola są niepoprawnie wypełnione.");
                notification.setDuration(3000);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                notification.setPosition(Notification.Position.TOP_CENTER);
            }else{

                complaintText.getValue();
                long orderComplaintId = Id;
                //No
                complainP.makeComplain(orderComplaintId);
                router.navigateToClientHistoricalOrderList();
                Notification notification = Notification.show("Zgłoszono reklamacje");
                notification.setDuration(6000);
                notification.addThemeVariants(NotificationVariant.LUMO_SUCCESS) ;
                notification.setPosition(Notification.Position.TOP_CENTER);
            }

        });

        stopButton.addClickListener(e->{
            router.navigateToClientHistoricalOrderList();
        });

        H1 title = new H1("Reklamacja");
        title.addClassName("form-title");



        //form
        formLayout.add( complaintText,addButton,stopButton);
        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 1));

        VerticalLayout verticalLayout =new VerticalLayout();
        verticalLayout.setAlignSelf(Alignment.CENTER);
        verticalLayout.setAlignItems(Alignment.CENTER);
        verticalLayout.setWidth("800px");
        verticalLayout.add(title,formLayout);
        add(verticalLayout);
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
