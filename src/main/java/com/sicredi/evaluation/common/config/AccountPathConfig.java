package com.sicredi.evaluation.common.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Getter
@Configuration
public class AccountPathConfig {
    @Value(value = "${account.file.input.path}")
    private String accountFileInputPath;

    @Value(value = "${account.file.output.path}")
    private String accountFileOutputPath;

}
