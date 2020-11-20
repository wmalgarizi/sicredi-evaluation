package com.sicredi.evaluation.input.model;

import com.sicredi.evaluation.common.model.AccountErrorEntity;
import com.sicredi.evaluation.common.model.BaseModel;
import com.sicredi.evaluation.output.model.AccountOutput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountInput extends BaseModel {
    private String branchNumber;
    private String accountNumber;
    private Double balance;
    private String status;

    public AccountOutput mapToOutput(Boolean updated) {
        return AccountOutput.builder()
                .branchNumber(this.branchNumber)
                .accountNumber(this.accountNumber)
                .balance(this.balance)
                .status(this.status)
                .updated(updated)
                .build();
    }

    /**
     * Method to map AccountInput into AccountErrorEntity
     *
     * @return the mapped AccountErrorEntity
     */
    public AccountErrorEntity mapToAccountErrorEntity(String reason) {
        return AccountErrorEntity.builder()
                .branchNumber(this.branchNumber)
                .accountNumber(this.accountNumber)
                .balance(this.balance)
                .status(this.status)
                .reason(reason)
                .build();
    }

}
