package com.sicredi.evaluation.input.reader;

import com.sicredi.evaluation.input.mapper.AccountInputLineMapper;
import com.sicredi.evaluation.input.model.AccountInput;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
public class AccountReader {

    @StepScope
    @Bean(name = "accountItemReader")
    public FlatFileItemReader<AccountInput> fileItemReader(@Value(value = "${account.file.input.path}") Resource resource){
        var flatFileItemReader = new FlatFileItemReader<AccountInput>();

        flatFileItemReader.setName("reader");
        flatFileItemReader.setEncoding("utf-8");
        flatFileItemReader.setLinesToSkip(1);
        flatFileItemReader.setResource(resource);
        flatFileItemReader.setLineMapper(AccountInputLineMapper.lineMapper());

        return flatFileItemReader;
    }
}
