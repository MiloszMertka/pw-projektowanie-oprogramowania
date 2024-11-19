package pl.edu.pw.ee.catering.presenter.client.usecase;

public interface IPlaceOrderUC {

    void placeOrder();

    void payWithPaySystem(Long orderId);
}
