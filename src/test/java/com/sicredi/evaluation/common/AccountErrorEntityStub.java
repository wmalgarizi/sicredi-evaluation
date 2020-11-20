package com.sicredi.evaluation.common;

import com.sicredi.evaluation.common.model.AccountErrorEntity;

public class AccountErrorEntityStub {
    private static final String BRANCH_NUMBER = "0000";
    private static final String ACCOUNT_NUMBER = "111111";
    private static final Double BALANCE = 10.00;
    private static final String STATUS = "A";
    private static final String REASON = "Error";

    public static AccountErrorEntity buildAccountErrorEntity() {
        return AccountErrorEntity.builder()
                .branchNumber(BRANCH_NUMBER)
                .accountNumber(ACCOUNT_NUMBER)
                .balance(BALANCE)
                .status(STATUS)
                .reason(REASON)
                .build();
    }
}
