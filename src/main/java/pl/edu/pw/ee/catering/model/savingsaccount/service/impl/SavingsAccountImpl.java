package pl.edu.pw.ee.catering.model.savingsaccount.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import pl.edu.pw.ee.catering.model.order.repository.OrderRepository;
import pl.edu.pw.ee.catering.model.savingsaccount.entity.SavingsAccount;
import pl.edu.pw.ee.catering.model.savingsaccount.repository.SavingsAccountRepository;
import pl.edu.pw.ee.catering.model.savingsaccount.service.ISavingsAccount;


import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SavingsAccountImpl implements ISavingsAccount {
    private final SavingsAccountRepository svRepository;

    @Override
    public boolean checkIsAmountEnough(Long savingsAccountId, int orderPrice) {
       Optional<SavingsAccount> sa = svRepository.findById(savingsAccountId);
       int savings = sa.map(SavingsAccount::getAmount).orElse(0);
       return savings >= orderPrice;
    }

    @Override
    public void updateSavingsAccount(Long savingsAccountId, int orderPrice) {
        Optional<SavingsAccount> sao = svRepository.findById(savingsAccountId);
        if (sao.isPresent()) {
            SavingsAccount sa = sao.get();
            int currentAmount = sa.getAmount();
            sa.setAmount(currentAmount - orderPrice);
        }
    }
}
