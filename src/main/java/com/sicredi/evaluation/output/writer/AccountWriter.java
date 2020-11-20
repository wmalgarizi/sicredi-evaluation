package com.sicredi.evaluation.output.writer;

import com.sicredi.evaluation.output.aggregator.AccountOutputLineBuilder;
import com.sicredi.evaluation.output.model.AccountOutput;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.ResourcePatternResolver;

@Slf4j
@Configuration
@AllArgsConstructor
public class AccountWriter {
    private final ResourcePatternResolver resourcePatternResolver;

    @StepScope
    @Bean(name = "accountItemWriter")
    public FlatFileItemWriter<AccountOutput> flatFileItemWriter(@Value("${account.file.output.path}") String outputFile) {
        var flatFileItemWriter = new FlatFileItemWriter<AccountOutput>();
        flatFileItemWriter.setEncoding("utf-8");
        flatFileItemWriter.setName("writer");
        flatFileItemWriter.setResource(resourcePatternResolver.getResource(outputFile));
        flatFileItemWriter.setHeaderCallback(AccountOutputLineBuilder.createAccountHeader());
        flatFileItemWriter.setLineAggregator(AccountOutputLineBuilder.createAccountLineAggregator());

        return flatFileItemWriter;
    }
}
