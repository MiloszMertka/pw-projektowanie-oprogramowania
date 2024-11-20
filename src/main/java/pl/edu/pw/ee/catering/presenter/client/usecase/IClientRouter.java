package pl.edu.pw.ee.catering.presenter.client.usecase;

public interface IClientRouter {
    void navigateToPlaceOrderForm();
    void navigateToPlaceComplaintForm(Long Id);
    void navigateToClientMealList();
    void navigateToOrderDetails(Long id);
    void navigateToUpdateOrderView(Long id);
}
