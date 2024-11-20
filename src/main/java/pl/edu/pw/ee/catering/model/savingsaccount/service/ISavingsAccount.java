package pl.edu.pw.ee.catering.model.savingsaccount.service;

public interface ISavingsAccount {
    boolean checkIsAmountEnough(Long savingsAccountId, int orderPrice);

    void updateSavingsAccount(Long savingsAccountId, int orderPrice);
}
