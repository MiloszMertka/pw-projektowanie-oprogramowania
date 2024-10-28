package pl.edu.pw.ee.catering.view.meal.ui.impl;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
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
import com.vaadin.flow.component.upload.receivers.MemoryBuffer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import pl.edu.pw.ee.catering.model.meal.dto.MealDetails;
import pl.edu.pw.ee.catering.model.meal.entity.Image;
import pl.edu.pw.ee.catering.model.meal.entity.Ingredient;
import pl.edu.pw.ee.catering.model.meal.entity.Price;
import pl.edu.pw.ee.catering.presenter.cateringcompany.usecase.ICateringCompanyRouter;
import pl.edu.pw.ee.catering.presenter.cateringcompany.usecase.ICreateMealUC;
import pl.edu.pw.ee.catering.view.meal.ui.ICreateMealForm;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@UIScope
@SpringComponent
@Route("create-new-meal-form")
public class CreateCompanyMealUI extends VerticalLayout implements ICreateMealForm {

    private final ICreateMealUC createMealUC;
    private final ICateringCompanyRouter router;

    private FileBuffer buffer = new FileBuffer ();

    public CreateCompanyMealUI(ICreateMealUC createMealUC, ICateringCompanyRouter router){
        this.router = router;
        this.createMealUC = createMealUC;
        showCreateMealForm();
    }



    public final  void showCreateMealForm(){
        removeAll();
        ingredientsNames = new ArrayList<>(Capacity);
        ingredientsGrama = new ArrayList<>(Capacity);
        buffer = new FileBuffer ();


        TextField mealName = new TextField("Nazwa posiłku");
        NumberField mealCost = new NumberField("Cena");
        IntegerField mealCalories = new IntegerField("Kalorie");
        TextArea mealDescription = new TextArea("Opis");
        Upload imageUpload = new Upload(buffer);
        Button addButton = new Button("Dodaj");
        VerticalLayout mealIngredients = new VerticalLayout();
        FormLayout formLayout = new FormLayout();
        ArrayList<Component> components = new ArrayList<>();
        //components.add(mealName);
        SetMealName(mealName);
        SetMealCalories(mealCalories);
        SetMealCost(mealCost);
        SetMealDescription(mealDescription);
        SetMealIngredients(mealIngredients);
        imageUpload.addFileRemovedListener(fileRemovedEvent -> {
            buffer = new FileBuffer ();
        });
        //submit button
        addButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        addButton.addClickListener( buttonClickEvent -> {

            boolean ingredientsOk = false;
            for(int i = 0 ; i < ingredientsNames.size();i++){
                ingredientsOk |= ingredientsNames.get(i).isInvalid();
                ingredientsOk |= ingredientsGrama.get(i).isInvalid();

            }

            if( ingredientsOk || mealCost.isInvalid()|| mealName.isInvalid()||
                    mealCalories.isInvalid()){
                Notification notification = Notification.show("Pola są niepoprawnie wypełnione.");
                notification.setDuration(3000);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                notification.setPosition(Notification.Position.TOP_CENTER);
                return;
            }

            String filePath = "";

            if(!Objects.equals(buffer.getFileName(), "")){
                String targetDirectory = "src/main/java/pl/edu/pw/ee/catering/uploads/";

                Path targetPath = Paths.get(targetDirectory, buffer.getFileName());
                targetPath = getUniqueFilePath(targetPath);
                try {


                    Files.createDirectories(targetPath.getParent());
                    Files.copy(buffer.getFileData().getFile().toPath(), targetPath);
                    filePath = targetPath.getFileName().toString();

                } catch (IOException e) {

                }

            }


            MealDetails mealDetails = new MealDetails();
            mealDetails.setName(mealName.getValue());
            mealDetails.setCaloricity(mealCalories.getValue());
            mealDetails.setPrice( Price.builder().amount(mealCost.getValue()).currency(Currency.getInstance("PLN")).build() );
            mealDetails.setImage( Image.builder().name(buffer.getFileName()).path(filePath).build() );
            mealDetails.setAvailability(true);
            mealDetails.setDescription(mealDescription.getValue());
            ArrayList<Ingredient> ingredientList = new ArrayList<Ingredient>();

            for(int i = 0 ; i < ingredientsNames.size();i++){
                ingredientList.add(new Ingredient().builder()
                                .name(ingredientsNames.get(i).getValue())
                                .grammaturgy(ingredientsGrama.get(i).getValue())
                        .build());
            }
            mealDetails.setIngredients(ingredientList);


            createMealUC.createMeal(mealDetails);


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
        formLayout.add(mealName,mealCalories,mealCost,imageUpload,mealDescription,mealIngredients, addButton);
        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 2));
        formLayout.setColspan(addButton,2);

        add(formLayout);
    }

    private void SetMealName(TextField mealName){

        mealName.setRequired(true);
        mealName.setInvalid(true);
        mealName.setMinLength(1);
        mealName.setPattern("^.*[a-zA-Z]+.*$");
        mealName.setI18n(new TextField.TextFieldI18n()
                .setRequiredErrorMessage("Pole jest wymagane")
                .setPatternErrorMessage("Musi zawierać litery"));

    }

    private void SetMealCost(NumberField mealCost){
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
    }

    private void SetMealCalories(IntegerField mealCalories){
        mealCalories.setMin(0);
        mealCalories.setValue(0);
        mealCalories.setI18n(new IntegerField.IntegerFieldI18n()
                .setMinErrorMessage("Kalorie nie mogą być mniejsze od 0"));
    }

    private void SetMealDescription(TextArea mealDescription){
        int charLimit = 600;
        mealDescription.setMaxLength(charLimit);
        mealDescription.setValueChangeMode(ValueChangeMode.EAGER);
        mealDescription.addValueChangeListener(e -> {
            e.getSource()
                    .setHelperText("%d/%d".formatted(e.getValue().length(), charLimit));
        });
    }
    private static final int Capacity = 100;
    private List<TextField> ingredientsNames = new ArrayList<>(Capacity);
    private List<IntegerField> ingredientsGrama = new ArrayList<>(Capacity);


    private void SetMealIngredients(VerticalLayout mealIngredients){

        mealIngredients.getStyle().set( "border" , "1px solid blue" ) ;

        Span title = new Span("Skaładniki");
        mealIngredients.setAlignItems(Alignment.CENTER);
        VerticalLayout ingredientsForms = new VerticalLayout();
        ingredientsForms.getStyle().set( "border" , "1px solid blue" ) ;
        Button add =new Button("Dodaj skaładnik");
        //ingredientsForms.set
        add.addClickListener(buttonClickEvent -> {
           FormLayout igf =  new FormLayout();
           Button igb = new Button("Usuń");
           IntegerField igif = new IntegerField("Gramatura");
           TextField igt = new TextField("Nazwa Składnika");
           SetMealName(igt);
           SetMealCalories(igif);
           igf.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 3));
            igb.addThemeVariants(ButtonVariant.LUMO_ERROR);
           igf.add(igt, igif, igb);
           ingredientsNames.add(igt);
           ingredientsGrama.add(igif);

           igb.addClickListener(buttonClickEvent1 -> {
               ingredientsNames.remove(igt);
               ingredientsGrama.remove(igif);
               ingredientsForms.remove(igf);
           });
           ingredientsForms.add(igf);

        });

        mealIngredients.add(title, ingredientsForms,  add );


    }

    private Path getUniqueFilePath(Path originalPath) {
        Path path = originalPath;
        StringBuilder  randoms = new StringBuilder();
        Random random = new Random();
        randoms.append((char) (random.nextInt(26) + 'a'));

        while (Files.exists(path)) {
            String fileName = originalPath.getFileName().toString();
            String nameWithoutExtension = fileName.contains(".")
                    ? fileName.substring(0, fileName.lastIndexOf('.'))
                    : fileName;
            String extension = fileName.contains(".")
                    ? fileName.substring(fileName.lastIndexOf('.'))
                    : "";


            String newFileName = String.format("%s(%s)%s", nameWithoutExtension, randoms.toString(), extension);
            path = originalPath.getParent().resolve(newFileName);
            randoms.append( (char) (random.nextInt(26) + 'a'));
        }

        return path;
    }
}
