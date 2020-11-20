package com.sicredi.evaluation.common.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum  AccountFileAttributeLabelEnum {
    BRANCH_NUMBER("agencia"),
    ACCOUNT_NUMBER("conta"),
    BALANCE("saldo"),
    STATUS("status"),
    UPDATED("atualizada");

    private String label;

}
