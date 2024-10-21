package pl.edu.pw.ee.catering.model.order.dto;

import lombok.Getter;

@Getter
public enum OrderStatus {
    PLACED("Złożone"),
    REJECTED("Odrzucone"),
    CANCELED("Anulowane"),
    UNPAID("Nieopłacone"),
    MISSED("Przeoczone"),
    FINISHED("Zrealizowane"),
    IN_DELIVERY("W dostawie"),
    IN_PREPARATION("W przygotowaniu"),
    RECEIVED("Otrzymane"),
    PAID("Opłacone");

    private final String displayName;
    OrderStatus(String displayName) {
        this.displayName = displayName;
    }
}
