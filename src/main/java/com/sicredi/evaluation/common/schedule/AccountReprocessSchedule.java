package com.sicredi.evaluation.common.schedule;

import com.sicredi.evaluation.common.AccountUpdateFacade;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@EnableScheduling
@Component
@AllArgsConstructor
public class AccountReprocessSchedule {
    private final AccountUpdateFacade accountUpdateFacade;

    /**
     * Schedule to find and reprocess the accounts that weren't
     * processed due to Receita's side error
     *
     */
    @Scheduled(fixedDelay = 10000)
    public void reprocessAccountsInError() {
        accountUpdateFacade.reprocessUnprocessedAccounts();
    }
}
