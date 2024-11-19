package pl.edu.pw.ee.catering.model.savingsaccount.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pw.ee.catering.model.savingsaccount.service.ISavingsAccount;

@Service
@RequiredArgsConstructor
public class SavingsAccountImpl implements ISavingsAccount {
    @Override
    public boolean checkIsAmountEnough(int orderPrice) {
        return true;
    }

    @Override
    public void updateSavingsAccount(int orderPrice) {
        return;
    }
}
