package pl.edu.pw.ee.catering.view.meal.ui.impl;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
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
import com.vaadin.flow.component.upload.receivers.FileBuffer;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import pl.edu.pw.ee.catering.model.cateringcompany.service.ICateringCompany;
import pl.edu.pw.ee.catering.model.meal.dto.MealDetails;
import pl.edu.pw.ee.catering.model.meal.entity.Image;
import pl.edu.pw.ee.catering.model.meal.entity.Ingredient;
import pl.edu.pw.ee.catering.model.meal.entity.Price;
import pl.edu.pw.ee.catering.model.meal.entity.Meal;
import pl.edu.pw.ee.catering.presenter.cateringcompany.usecase.ICateringCompanyRouter;
import pl.edu.pw.ee.catering.presenter.meal.usecase.IEditMealUC;
import pl.edu.pw.ee.catering.view.meal.ui.IEditMealForm;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@UIScope
@SpringComponent
@Route("edit-meal-form")
public class EditCompanyMealUI extends VerticalLayout implements IEditMealForm, HasUrlParameter<Long> {

    private final IEditMealUC editMealUC;
    private final ICateringCompanyRouter router;
    private final ICateringCompany cateringCompany;

    private FileBuffer buffer = new FileBuffer();
    private Meal existingMeal;

    private TextField mealName;
    private NumberField mealCost;
    private IntegerField mealCalories;
    private TextArea mealDescription;
    private Upload imageUpload;
    private Button saveButton;
    private VerticalLayout mealIngredients;
    private List<TextField> ingredientsNames;
    private List<IntegerField> ingredientsGrama;
    private static final int Capacity = 100;

    public EditCompanyMealUI(IEditMealUC editMealUC, ICateringCompanyRouter router, ICateringCompany cateringCompany) {
        this.router = router;
        this.editMealUC = editMealUC;
        this.cateringCompany = cateringCompany;
        initializeComponents();
    }

    private void initializeComponents() {
        ingredientsNames = new ArrayList<>(Capacity);
        ingredientsGrama = new ArrayList<>(Capacity);
        buffer = new FileBuffer();

        mealName = new TextField("Nazwa posiłku");
        mealCost = new NumberField("Cena");
        mealCalories = new IntegerField("Kalorie");
        mealDescription = new TextArea("Opis");
        imageUpload = new Upload(buffer);
        saveButton = new Button("Zapisz");
        mealIngredients = new VerticalLayout();

        SetMealName(mealName);
        SetMealCalories(mealCalories);
        SetMealCost(mealCost);
        SetMealDescription(mealDescription);
        SetMealIngredients(mealIngredients);

        imageUpload.addFileRemovedListener(fileRemovedEvent -> buffer = new FileBuffer());
        saveButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
    }

    @Override
    public void showEditMealForm(Long id) {
        this.existingMeal = cateringCompany.getMeal(id);
        removeAll();
        populateFormFields(this.existingMeal);
        setupSaveButton();

        FormLayout formLayout = new FormLayout();
        formLayout.add(mealName, mealCalories, mealCost, imageUpload, mealDescription, mealIngredients, saveButton);
        formLayout.setResponsiveSteps(new FormLayout.ResponsiveStep("0", 2));
        formLayout.setColspan(saveButton, 2);

        add(formLayout);
    }

    private void populateFormFields(Meal meal) {
        mealName.setValue(meal.getName());
        mealCalories.setValue(meal.getCaloricity());
        mealCost.setValue(meal.getPrice().getAmount());
        mealDescription.setValue(meal.getDescription());

        for (Ingredient ingredient : meal.getIngredients()) {
            TextField ingredientName = new TextField("Nazwa Składnika", ingredient.getName());
            IntegerField ingredientGrama = new IntegerField("Gramatura", String.valueOf(ingredient.getGrammaturgy()));
            ingredientGrama.setValue(ingredient.getGrammaturgy() != null ? ingredient.getGrammaturgy() : 0);
            ingredientsNames.add(ingredientName);
            ingredientsGrama.add(ingredientGrama);
        }
    }

    private void setupSaveButton() {
        saveButton.addClickListener(buttonClickEvent -> {
            boolean ingredientsOk = false;
            for (int i = 0; i < ingredientsNames.size(); i++) {
                ingredientsOk |= ingredientsNames.get(i).isInvalid();
                ingredientsOk |= ingredientsGrama.get(i).isInvalid();
            }

            if (ingredientsOk || mealCost.isInvalid() || mealName.isInvalid() || mealCalories.isInvalid() || mealDescription.isInvalid()) {
                Notification notification = Notification.show("Pola są niepoprawnie wypełnione.");
                notification.setDuration(3000);
                notification.addThemeVariants(NotificationVariant.LUMO_ERROR);
                notification.setPosition(Notification.Position.TOP_CENTER);
                return;
            }

            String filePath = existingMeal.getImage() != null ? existingMeal.getImage().getPath() : "";
            if (!Objects.equals(buffer.getFileName(), "")) {
                filePath = saveImage(buffer);
            }

            MealDetails mealDetails = new MealDetails();
            mealDetails.setId(existingMeal.getId());
            mealDetails.setName(mealName.getValue());
            mealDetails.setCaloricity(mealCalories.getValue());
            mealDetails.setPrice(Price.builder().amount(mealCost.getValue()).currency(Currency.getInstance("PLN")).build());
            mealDetails.setImage(new Image(buffer.getFileName(), filePath));
            mealDetails.setDescription(mealDescription.getValue());

            List<Ingredient> updatedIngredients = new ArrayList<>();
            for(int i = 0 ; i < ingredientsNames.size();i++){
                updatedIngredients.add(new Ingredient().builder()
                        .name(ingredientsNames.get(i).getValue())
                        .grammaturgy(ingredientsGrama.get(i).getValue())
                        .build());
            }
            mealDetails.setIngredients(updatedIngredients);
            editMealUC.editMeal(mealDetails);
            router.navigateToMealList();
        });
    }

    private String saveImage(FileBuffer buffer) {
        String filePath = "";
        String targetDirectory = "src/main/java/pl/edu/pw/ee/catering/uploads/";
        Path targetPath = Paths.get(targetDirectory, buffer.getFileName());
        targetPath = getUniqueFilePath(targetPath);

        try {
            Files.createDirectories(targetPath.getParent());
            Files.copy(buffer.getFileData().getFile().toPath(), targetPath);
            filePath = targetPath.getFileName().toString();
        } catch (IOException e) {
            Notification.show("Nie udało się zapisać obrazu.");
        }

        return filePath;
    }

    private Path getUniqueFilePath(Path originalPath) {
        Path path = originalPath;
        StringBuilder randoms = new StringBuilder();
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

            String newFileName = String.format("%s(%s)%s", nameWithoutExtension, randoms, extension);
            path = originalPath.getParent().resolve(newFileName);
            randoms.append((char) (random.nextInt(26) + 'a'));
        }

        return path;
    }

    private void SetMealName(TextField mealName) {
        mealName.setRequired(true);
        mealName.setInvalid(true);
        mealName.setMinLength(1);
        mealName.setPattern("^.*[a-zA-Z]+.*$");
        mealName.setI18n(new TextField.TextFieldI18n()
                .setRequiredErrorMessage("Pole jest wymagane")
                .setPatternErrorMessage("Musi zawierać litery"));
    }

    private void SetMealCost(NumberField mealCost) {
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

    private void SetMealCalories(IntegerField mealCalories) {
        mealCalories.setMin(0);
        mealCalories.setValue(0);
        mealCalories.setI18n(new IntegerField.IntegerFieldI18n()
                .setMinErrorMessage("Kalorie nie mogą być mniejsze od 0"));
    }

    private void SetMealDescription(TextArea mealDescription) {
        int charLimit = 600;
        mealDescription.setMaxLength(charLimit);
        mealDescription.setValueChangeMode(ValueChangeMode.EAGER);
        mealDescription.addValueChangeListener(e -> {
            e.getSource()
                    .setHelperText("%d/%d".formatted(e.getValue().length(), charLimit));
        });
        mealDescription.setI18n(new TextArea.TextAreaI18n()
                .setMaxLengthErrorMessage("Zbyt długi opis"));
    }

    private void SetMealIngredients(VerticalLayout mealIngredients) {
        mealIngredients.getStyle().set("border", "1px solid blue");

        Span title = new Span("Składniki");
        mealIngredients.setAlignItems(Alignment.CENTER);
        VerticalLayout ingredientsForms = new VerticalLayout();
        ingredientsForms.getStyle().set("border", "1px solid blue");
        Button add = new Button("Dodaj składnik");

        add.addClickListener(buttonClickEvent -> {
            FormLayout igf = new FormLayout();
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

        mealIngredients.add(title, ingredientsForms, add);
    }

    @Override
    public void setParameter(BeforeEvent beforeEvent, Long aLong) {
        showEditMealForm(aLong);
    }
}
