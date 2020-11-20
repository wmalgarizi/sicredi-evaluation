package com.sicredi.evaluation.common.processor;

import com.sicredi.evaluation.common.facade.AccountUpdateFacade;
import com.sicredi.evaluation.input.model.AccountInput;
import com.sicredi.evaluation.output.model.AccountOutput;
import lombok.AllArgsConstructor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class AccountProcessor {
    private final AccountUpdateFacade accountUpdateFacade;

    @Bean
    public ItemProcessor<AccountInput, AccountOutput> processor() {
        return accountUpdateFacade::updateAccount;
    }
}
