package com.sicredi.evaluation.input.mapper;

import com.sicredi.evaluation.common.enumeration.AccountFileAttributeLabelEnum;
import com.sicredi.evaluation.input.model.AccountInput;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;

public class AccountInputLineMapper {
    private static final String FILE_ATTRIBUTE_DELIMITER = ";";

    private AccountInputLineMapper() {
    }

    public static LineMapper<AccountInput> lineMapper() {
        var accountLineMapper = new DefaultLineMapper<AccountInput>();

        var accountLineTokenizer = createAccountLineTokenizer();
        accountLineMapper.setLineTokenizer(accountLineTokenizer);

        var accountInformationMapper = createAccountInformationMapper();
        accountLineMapper.setFieldSetMapper(accountInformationMapper);

        return accountLineMapper;
    }

    private static LineTokenizer createAccountLineTokenizer() {
        DelimitedLineTokenizer studentLineTokenizer = new DelimitedLineTokenizer();
        studentLineTokenizer.setDelimiter(FILE_ATTRIBUTE_DELIMITER);
        studentLineTokenizer.setNames(
                AccountFileAttributeLabelEnum.BRANCH_NUMBER.getLabel(),
                AccountFileAttributeLabelEnum.ACCOUNT_NUMBER.getLabel(),
                AccountFileAttributeLabelEnum.BALANCE.getLabel(),
                AccountFileAttributeLabelEnum.STATUS.getLabel()
        );
        return studentLineTokenizer;
    }

    private static FieldSetMapper<AccountInput> createAccountInformationMapper() {
        return fieldSet -> AccountInput.builder()
                .branchNumber(fieldSet.readString(AccountFileAttributeLabelEnum.BRANCH_NUMBER.getLabel()))
                .accountNumber(fieldSet.readString(AccountFileAttributeLabelEnum.ACCOUNT_NUMBER.getLabel()).replace("-", ""))
                .balance(Double.valueOf(fieldSet.readString(AccountFileAttributeLabelEnum.BALANCE.getLabel()).replaceAll(" ", "").replace(",", ".")))
                .status(fieldSet.readString(AccountFileAttributeLabelEnum.STATUS.getLabel()))
                .build();
    }

}
