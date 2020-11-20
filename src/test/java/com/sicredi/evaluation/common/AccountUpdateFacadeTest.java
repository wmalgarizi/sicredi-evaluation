package com.sicredi.evaluation.common;

import com.sicredi.evaluation.common.exception.ConnectionTimeoutException;
import com.sicredi.evaluation.common.exception.ServiceUnavailableException;
import com.sicredi.evaluation.common.model.AccountErrorEntity;
import com.sicredi.evaluation.common.service.AccountErrorService;
import com.sicredi.evaluation.common.service.ReceitaService;
import com.sicredi.evaluation.input.model.AccountInput;
import com.sicredi.evaluation.output.model.AccountOutput;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atMostOnce;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountUpdateFacadeTest {
    private static final String BRANCH_NUMBER = "0000";
    private static final String ACCOUNT_NUMBER = "111111";
    private static final Double BALANCE = 10.00;
    private static final String STATUS = "A";

    @InjectMocks
    private AccountUpdateFacade accountUpdateFacade;

    @Mock
    private ReceitaService receitaService;

    @Mock
    private AccountErrorService accountErrorService;

    @Test
    public void updateAccount_withValidInput_whenUpdateSuccessfully_shouldReturnOutputWithUpdatedTrue() throws InterruptedException {
        //Given
        var accountInput = buildValidAccountInput();

        //When
        when(receitaService.atualizarConta(BRANCH_NUMBER, ACCOUNT_NUMBER, BALANCE, STATUS))
                .thenReturn(true);

        //Then
        AccountOutput accountOutput = accountUpdateFacade.updateAccount(accountInput);

        assertEquals(BRANCH_NUMBER, accountOutput.getBranchNumber());
        assertEquals(ACCOUNT_NUMBER, accountOutput.getAccountNumber());
        assertEquals(BALANCE, accountOutput.getBalance());
        assertEquals(STATUS, accountOutput.getStatus());
        assertNotNull(accountOutput.getUpdated());
        assertTrue(accountOutput.getUpdated());

        verify(accountErrorService, never()).saveError(any(AccountErrorEntity.class));
    }

    @Test
    public void updateAccount_withValidInput_whenDoNotUpdateSuccessfully_shouldReturnOutputWithUpdatedFalse() throws InterruptedException {
        //Given
        var accountInput = buildValidAccountInput();

        //When
        when(receitaService.atualizarConta(BRANCH_NUMBER, ACCOUNT_NUMBER, BALANCE, STATUS))
                .thenReturn(false);

        //Then
        AccountOutput accountOutput = accountUpdateFacade.updateAccount(accountInput);

        assertEquals(BRANCH_NUMBER, accountOutput.getBranchNumber());
        assertEquals(ACCOUNT_NUMBER, accountOutput.getAccountNumber());
        assertEquals(BALANCE, accountOutput.getBalance());
        assertEquals(STATUS, accountOutput.getStatus());
        assertNotNull(accountOutput.getUpdated());
        assertFalse(accountOutput.getUpdated());

        verify(accountErrorService, never()).saveError(any(AccountErrorEntity.class));
    }

    @Test(expected = ServiceUnavailableException.class)
    public void updateAccount_withValidInput_whenCouldNotConnectToReceita_shouldSaveErrorAndThrowServiceUnavailableException() throws InterruptedException {
        //Given
        var accountInput = buildValidAccountInput();

        //When
        when(receitaService.atualizarConta(BRANCH_NUMBER, ACCOUNT_NUMBER, BALANCE, STATUS))
                .thenThrow(new RuntimeException("Error"));

        //Then
        accountUpdateFacade.updateAccount(accountInput);

        verify(accountErrorService, atMostOnce()).saveError(any(AccountErrorEntity.class));
    }

    @Test(expected = ConnectionTimeoutException.class)
    public void updateAccount_withValidInput_whenCouldReceitaReturnTimeout_shouldSaveErrorAndThrowConnectionTimeoutException() throws InterruptedException {
        //Given
        var accountInput = buildValidAccountInput();

        //When
        when(receitaService.atualizarConta(BRANCH_NUMBER, ACCOUNT_NUMBER, BALANCE, STATUS))
                .thenThrow(new InterruptedException("Error"));

        //Then
        accountUpdateFacade.updateAccount(accountInput);

        verify(accountErrorService, atMostOnce()).saveError(any(AccountErrorEntity.class));
    }

    @Test
    public void reprocessUnprocessedAccounts_whenFindUnprocessedAccount_shouldReprocess() throws InterruptedException {
        //Given
        var accountErrorEntity = buildValidAccountInput().mapToAccountErrorEntity("Error");

        //When
        when(accountErrorService.findUnprocessedAccounts())
                .thenReturn(List.of(accountErrorEntity));
        when(receitaService.atualizarConta(BRANCH_NUMBER, ACCOUNT_NUMBER, BALANCE, STATUS))
                .thenReturn(true);

        //Then
        accountUpdateFacade.reprocessUnprocessedAccounts();

        verify(accountErrorService, atMostOnce()).findUnprocessedAccounts();
        verify(receitaService, atMostOnce()).atualizarConta(BRANCH_NUMBER, ACCOUNT_NUMBER, BALANCE, STATUS);
        verify(accountErrorService, never()).saveError(any(AccountErrorEntity.class));
    }

    @Test
    public void reprocessUnprocessedAccounts_whenDoNotFindUnprocessedAccount_shouldNotReprocess() throws InterruptedException {
        //Given
        //When
        when(accountErrorService.findUnprocessedAccounts())
                .thenReturn(Collections.emptyList());

        //Then
        accountUpdateFacade.reprocessUnprocessedAccounts();

        verify(accountErrorService, atMostOnce()).findUnprocessedAccounts();
        verify(receitaService, never()).atualizarConta(BRANCH_NUMBER, ACCOUNT_NUMBER, BALANCE, STATUS);
        verify(accountErrorService, never()).saveError(any(AccountErrorEntity.class));
    }

    private AccountInput buildValidAccountInput() {
        return AccountInput.builder()
                .branchNumber(BRANCH_NUMBER)
                .accountNumber(ACCOUNT_NUMBER)
                .balance(BALANCE)
                .status(STATUS)
                .build();
    }

}