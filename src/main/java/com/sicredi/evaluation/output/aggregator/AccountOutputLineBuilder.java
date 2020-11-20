package com.sicredi.evaluation.output.aggregator;

import com.sicredi.evaluation.common.enumeration.AccountFileAttributeLabelEnum;
import com.sicredi.evaluation.output.model.AccountOutput;
import org.springframework.batch.item.file.FlatFileHeaderCallback;
import org.springframework.batch.item.file.transform.LineAggregator;

import java.util.Optional;

public class AccountOutputLineBuilder {
    private static final String FILE_ATTRIBUTE_DELIMITER = ";";

    private AccountOutputLineBuilder() {
    }

    public static FlatFileHeaderCallback createAccountHeader() {
        return headerWriter -> headerWriter
                .write(String.join(FILE_ATTRIBUTE_DELIMITER,
                        AccountFileAttributeLabelEnum.BRANCH_NUMBER.getLabel(),
                            AccountFileAttributeLabelEnum.ACCOUNT_NUMBER.getLabel(),
                            AccountFileAttributeLabelEnum.BALANCE.getLabel(),
                            AccountFileAttributeLabelEnum.STATUS.getLabel(),
                            AccountFileAttributeLabelEnum.UPDATED.getLabel()
                ));
    }

    public static LineAggregator<AccountOutput> createAccountLineAggregator() {
        return (AccountOutput item) -> String.join(FILE_ATTRIBUTE_DELIMITER,
                item.getBranchNumber(),
                formatAccountNumber(item.getAccountNumber()),
                Optional.ofNullable(item.getBalance()).map(String::valueOf).orElse("0.00"),
                item.getStatus(),
                item.getUpdated().toString());
    }

    private static String formatAccountNumber(String accountNumber) {
        var length = accountNumber.length();
        var accountNumberWithoutLastDigit = accountNumber.substring(0, length -1);
        var lastDigit = accountNumber.substring(length - 1);
        return accountNumberWithoutLastDigit.concat("-").concat(lastDigit);
    }
}
