package com.sicredi.evaluation.common.model;

import com.sicredi.evaluation.common.AccountErrorEntityStub;
import com.sicredi.evaluation.input.model.AccountInput;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountErrorEntityTest {
    private static final String BRANCH_NUMBER = "0000";
    private static final String ACCOUNT_NUMBER = "111111";
    private static final Double BALANCE = 10.00;
    private static final String STATUS = "A";

    @Test
    public void mapToInputModel_withAttributes_shouldReturnInputModel() {
        var accountErrorEntity = AccountErrorEntityStub.buildAccountErrorEntity();

        var accountInput = accountErrorEntity.mapToInputModel();
        assertEquals(BRANCH_NUMBER, accountInput.getBranchNumber());
        assertEquals(ACCOUNT_NUMBER, accountInput.getAccountNumber());
        assertEquals(BALANCE, accountInput.getBalance());
        assertEquals(STATUS, accountInput.getStatus());
    }

}