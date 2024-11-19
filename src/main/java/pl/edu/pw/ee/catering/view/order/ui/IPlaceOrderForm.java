package pl.edu.pw.ee.catering.view.order.ui;

public interface IPlaceOrderForm {

    void showOrderForm();

    void showSuccessForm();

    void showRedirectionForm(Long orderId);
}
