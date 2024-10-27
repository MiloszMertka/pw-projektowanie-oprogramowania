package pl.edu.pw.ee.catering.view.meal.ui.impl;

import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.UploadI18N;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import pl.edu.pw.ee.catering.model.meal.dto.MealDetails;
import pl.edu.pw.ee.catering.model.meal.entity.Image;
import pl.edu.pw.ee.catering.model.meal.entity.Price;
import pl.edu.pw.ee.catering.presenter.cateringcompany.usecase.ICateringCompanyRouter;
import pl.edu.pw.ee.catering.presenter.cateringcompany.usecase.ICreateMealUC;

import java.util.Currency;

@UIScope
@SpringComponent
@Route("create-new-meal-form")
public class CreateCompanyMealUI extends VerticalLayout {
    private final VerticalLayout addForm;
    private final ICreateMealUC createMealUC;
    private final ICateringCompanyRouter router;
    private boolean formsActive = false;
    private final MemoryBuffer buffer = new MemoryBuffer();

    public CreateCompanyMealUI(ICreateMealUC createMealUC, ICateringCompanyRouter router){
        this.router = router;
        this.createMealUC = createMealUC;
        addForm = new VerticalLayout();

        add(addForm);
    }

    public void showCreateMealForm(){
        if(!formsActive){
            formsActive = true;
            TextField mealName = new TextField("Nazwa posiłku");
            NumberField mealCost = new NumberField("Cena");
            IntegerField mealCalories = new IntegerField("Kalorie");
            TextArea mealDescription = new TextArea("Opis");
            Upload imageUpload = new Upload(buffer);
            Button addButton = new Button("Dodaj");
            FormLayout formLayout = new FormLayout();

            //meal Name
            mealName.setRequired(true);
            mealName.setInvalid(true);
            mealName.setMinLength(1);
            mealName.setPattern("^[a-zA-Z]+$");
            mealName.setI18n(new TextField.TextFieldI18n()
                    .setRequiredErrorMessage("Pole jest wymagane")
                    .setPatternErrorMessage("Musi zawierać litery"));

            //meal Calories
            mealCalories.setMin(0);
            mealCalories.setI18n(new IntegerField.IntegerFieldI18n()
                    .setMinErrorMessage("Kalorie nie mogą być mniejsze od 0"));

            //meal cost
            Div zlSuffix = new Div("zł");
            mealCost.setSuffixComponent(zlSuffix);
            mealCost.setRequired(true);
            mealCost.setInvalid(true);
            mealCost.setMin(0.01);
            mealCost.setStep(0.01);
            mealCost.setRequired(true);
            mealCost.setI18n(new NumberField.NumberFieldI18n()
                    .setMinErrorMessage("Koszt musi być większy od zera")
                    .setStepErrorMessage("Dokładność jest co do grosza"));

            //description
            int charLimit = 600;
            mealDescription.setMaxLength(charLimit);
            mealDescription.setValueChangeMode(ValueChangeMode.EAGER);
            mealDescription.addValueChangeListener(e -> {
                e.getSource()
                        .setHelperText("%d/%d".formatted(e.getValue().length(), charLimit));
            });


            //submit button
            addButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

            addButton.addClickListener( buttonClickEvent -> {



                if( mealCost.isInvalid()|| mealName.isInvalid()){
                    Notification notification = Notification.show("Pola są niepoprawnie wypełnione.");
                    notification.setDuration(3000);
                    notification.addThemeVariants(NotificationVariant.LUMO_ERROR); // Ustawienie stylu jako "błąd"

                    // Ustawienie pozycji poniżej przycisku
                    notification.setPosition(Notification.Position.TOP_CENTER);
                    return;
                }
                MealDetails mealDetails = new MealDetails();
                mealDetails.setName(mealName.getValue());
                mealDetails.setCaloricity(mealCalories.getValue());
                mealDetails.setPrice( Price.builder().amount(mealCost.getValue()).currency(Currency.getInstance("PLN")).build() );
                mealDetails.setImage( Image.builder().name(buffer.getFileName()).path("").build() );
                mealDetails.setAvailability(true);
                mealDetails.setDescription(mealDescription.getValue());

                //createMealUC.createMeal(mealDetails);
                addForm.remove(formLayout);
                formsActive = false;
                buffer.getFileName();
                router.navigateToMealList();

            });

            //imageUploader
            imageUpload.setDropAllowed(true);
            imageUpload.setMaxFiles(1);
            imageUpload.setMaxFileSize(10 * 1024 * 1024);
            imageUpload.setAcceptedFileTypes("image/jpeg", "image/png");//, "image/gif");
            imageUpload.setI18n(new UploadI18N()
                    .setDropFiles( new UploadI18N.DropFiles().setOne("Przeciągnij obraz") )
                    .setAddFiles(new UploadI18N.AddFiles().setOne("Dodaj obraz")));
            imageUpload.addFailedListener(event -> {


            });


            //form
            formLayout.add(mealName,mealCalories,mealCost,imageUpload,mealDescription,addButton);
            formLayout.setResponsiveSteps();
            formLayout.setColspan(addButton,2);


            addForm.add(formLayout);
        }
    }
}
