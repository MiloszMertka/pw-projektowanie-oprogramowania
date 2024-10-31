package pl.edu.pw.ee.catering.presenter.cateringcompany.usecase;

public interface ICateringCompanyRouter {

    void navigateToHistoricalOrderList();
    void navigateToOrderDetails(Long id);
    void navigateToOrderList();
    void navigateToCreateMealForm();
    void navigateToMealList();
    void navigateToDeleteMeal(Long id);
    void navigateToEditMealForm(Long id);
}
