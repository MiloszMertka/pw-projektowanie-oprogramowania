package pl.edu.pw.ee.catering.model.savingsaccount.service;

public interface ISavingsAccount {
    boolean checkIsAmountEnough(int orderPrice);

    void updateSavingsAccount(int orderPrice);
}
