package com.sicredi.evaluation.common.model;

import com.sicredi.evaluation.input.model.AccountInput;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "account_error")
public class AccountErrorEntity {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "branch_number")
    private String branchNumber;

    @Column(name = "account_number")
    private String accountNumber;

    private Double balance;
    private String status;
    private String reason;

    /**
     * Method to map AccountErrorEntity into AccountInput
     *
     * @return the mapped AccountInput
     */
    public AccountInput mapToInputModel() {
        return AccountInput.builder()
                .branchNumber(this.branchNumber)
                .accountNumber(this.accountNumber)
                .balance(this.balance)
                .status(this.status)
                .build();
    }
}
