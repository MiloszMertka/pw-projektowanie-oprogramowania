package pl.edu.pw.ee.catering.presenter.client.usecase;

public interface IClientRouter {
    void navigateToPlaceOrderForm();
    void navigateToPlaceComplaintForm(Long Id);
    void navigateToClientMealList();
    void navigateToReviewForm(Long Id);
    void navigateToClientOrderList();
    void navigateToOrderDetails(Long id);
    void navigateToUpdateOrderView(Long id);
    void navigateToClientHistoricalOrderList();
}
