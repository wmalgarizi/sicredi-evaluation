package com.sicredi.evaluation.common.step;

import com.sicredi.evaluation.input.model.AccountInput;
import com.sicredi.evaluation.output.model.AccountOutput;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class AccountStepConfig {
    private final StepBuilderFactory stepBuilderFactory;
    private final ItemReader<AccountInput> accountItemReader;
    private final ItemProcessor<AccountInput, AccountOutput> accountProcessor;
    private final ItemWriter<AccountOutput> accountItemWriter;

    @Bean
    public Step accountStep(){
        return stepBuilderFactory
                .get("step")
                .<AccountInput, AccountOutput>chunk(10)
                .reader(accountItemReader)
                .processor(accountProcessor)
                .writer(accountItemWriter)
                .faultTolerant()
                .skip(Exception.class)
                .skipLimit(Integer.MAX_VALUE)
                .build();
    }
}
