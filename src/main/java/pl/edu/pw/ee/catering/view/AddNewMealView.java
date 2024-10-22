package pl.edu.pw.ee.catering.view;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.upload.Receiver;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.component.upload.UploadI18N;
import com.vaadin.flow.component.upload.receivers.FileBuffer;
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;

@Route("/AddNewMeal")
public class AddNewMealView extends VerticalLayout {

    private final VerticalLayout addForm;
    private boolean formsActive = false;
    private final MemoryBuffer buffer = new MemoryBuffer();

    public AddNewMealView(){
        addForm = new VerticalLayout();
        Button addButton = new Button("Dodaj Nowy posiłem");
        addButton.addClickListener(buttonClickEvent -> {
           AddFrom();
        });
        add(addButton,addForm);
    }

    private void AddFrom(){
        if(!formsActive){
            formsActive = true;
            TextField mealName = new TextField("Nazwa posiłku");
            NumberField mealCost = new NumberField("Cena");
            TextArea mealDescription = new TextArea("Opis");
            Upload imageUpload = new Upload(buffer);
            Button addButton = new Button("Dodaj");
            FormLayout formLayout = new FormLayout();

            

            //meal cost
            Div zlSuffix = new Div("zł");
            mealCost.setSuffixComponent(zlSuffix);
            mealCost.setMin(0.01);
            mealCost.setStep(0.01);
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
                addForm.remove(formLayout);
                formsActive = false;
                buffer.getFileName();
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
            formLayout.add(mealName,mealCost,imageUpload,mealDescription,addButton);
            formLayout.setResponsiveSteps();
            formLayout.setColspan(addButton,2);


            addForm.add(formLayout);
        }
    }


}
