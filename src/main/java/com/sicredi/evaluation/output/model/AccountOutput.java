package com.sicredi.evaluation.output.model;

import com.sicredi.evaluation.common.model.BaseModel;
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
public class AccountOutput extends BaseModel {
    private String branchNumber;
    private String accountNumber;
    private Double balance;
    private String status;
    private Boolean updated;

}
