package com.sicredi.evaluation.input.model;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class AccountInputTest {
    private static final String BRANCH_NUMBER = "0000";
    private static final String ACCOUNT_NUMBER = "111111";
    private static final Double BALANCE = 10.00;
    private static final String STATUS = "A";
    private static final Boolean UPDATED = true;
    private static final String REASON = "Error";

    @Test
    public void mapToOutPut_withAttributes_shouldReturnOutputModel() {
        var accountInput  = buildValidAccountInput();

        var accountOutput = accountInput.mapToOutput(UPDATED);
        assertEquals(BRANCH_NUMBER, accountOutput.getBranchNumber());
        assertEquals(ACCOUNT_NUMBER, accountOutput.getAccountNumber());
        assertEquals(BALANCE, accountOutput.getBalance());
        assertEquals(STATUS, accountOutput.getStatus());
        assertNotNull(accountOutput.getUpdated());
        assertEquals(UPDATED, accountOutput.getUpdated());
    }

    @Test
    public void mapToAccountErrorEntity_withAttributes_shouldReturnAccountErrorEntity() {
        var accountInput  = buildValidAccountInput();

        var accountErrorEntity = accountInput.mapToAccountErrorEntity(REASON);
        assertEquals(BRANCH_NUMBER, accountErrorEntity.getBranchNumber());
        assertEquals(ACCOUNT_NUMBER, accountErrorEntity.getAccountNumber());
        assertEquals(BALANCE, accountErrorEntity.getBalance());
        assertEquals(STATUS, accountErrorEntity.getStatus());
        assertNotNull(accountErrorEntity.getReason());
        assertEquals(REASON, accountErrorEntity.getReason());
    }

    private AccountInput buildValidAccountInput() {
        return AccountInput.builder()
                .branchNumber(BRANCH_NUMBER)
                .accountNumber(ACCOUNT_NUMBER)
                .balance(BALANCE)
                .status(STATUS)
                .build();
    }

}