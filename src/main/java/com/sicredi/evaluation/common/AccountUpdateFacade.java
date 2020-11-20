package com.sicredi.evaluation.common;

import com.sicredi.evaluation.common.exception.ConnectionTimeoutException;
import com.sicredi.evaluation.common.exception.ServiceUnavailableException;
import com.sicredi.evaluation.common.service.AccountErrorService;
import com.sicredi.evaluation.common.service.ReceitaService;
import com.sicredi.evaluation.input.model.AccountInput;
import com.sicredi.evaluation.output.model.AccountOutput;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class AccountUpdateFacade {
    private final ReceitaService receitaService;
    private final AccountErrorService accountErrorService;

    /**
     * Method to update the account in Receita
     *
     * @param accountInput the account to be updated
     * @return The accountOutput containing the status of Receita update
     * @Throws ServiceUnavailableException when Receita Service is not available
     * @Throws InterruptedException when Receita Service request timed out
     */
    public AccountOutput updateAccount(AccountInput accountInput) {
        try {
            log.info("Starting to update account: {}", accountInput);
            var updated = receitaService.atualizarConta(
                    accountInput.getBranchNumber(),
                    accountInput.getAccountNumber(),
                    accountInput.getBalance(),
                    accountInput.getStatus()
            );
            log.info("Account updated successfully");
            return accountInput.mapToOutput(updated);
        } catch (RuntimeException error) {
            log.error("ReceitaService unavailable");
            accountErrorService.saveError(accountInput.mapToAccountErrorEntity(error.getMessage()));
            throw new ServiceUnavailableException(error.getMessage());
        } catch (InterruptedException error) {
            log.error("Connection timeout");
            accountErrorService.saveError(accountInput.mapToAccountErrorEntity(error.getMessage()));
            throw new ConnectionTimeoutException();
        }
    }

    /**
     * Method to reprocess the Accounts in error
     *
     */
    public void reprocessUnprocessedAccounts() {
        accountErrorService.findUnprocessedAccounts()
                .forEach(accountError -> this.updateAccount(accountError.mapToInputModel()));
    }

}
