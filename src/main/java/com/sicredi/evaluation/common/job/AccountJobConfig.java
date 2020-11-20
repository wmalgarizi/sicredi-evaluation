package com.sicredi.evaluation.common.job;

import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class AccountJobConfig {
    private final JobBuilderFactory jobBuilderFactory;

    @Bean
    public Job accountJob(@Qualifier(value = "accountStep") Step accountStep){
        return jobBuilderFactory
                .get("job")
                .start(accountStep)
                .build();
    }
}
