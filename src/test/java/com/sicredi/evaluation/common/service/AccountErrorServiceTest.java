package com.sicredi.evaluation.common.service;

import com.sicredi.evaluation.common.AccountErrorEntityStub;
import com.sicredi.evaluation.common.exception.DatabaseExeption;
import com.sicredi.evaluation.common.model.AccountErrorEntity;
import com.sicredi.evaluation.common.repository.AccountErrorRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.dao.DataAccessException;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountErrorServiceTest {

    @InjectMocks
    private AccountErrorService accountErrorService;

    @Mock
    private AccountErrorRepository accountErrorRepository;

    @Test
    public void saveError_whenSaveCorrectly_shouldPass() {
        //Given
        var accountErrorEntity = AccountErrorEntityStub.buildAccountErrorEntity();

        //When
        when(accountErrorRepository.save(accountErrorEntity))
                .thenReturn(accountErrorEntity);

        //Then
        accountErrorService.saveError(accountErrorEntity);
    }

    @Test(expected = DatabaseExeption.class)
    public void saveError_whenGetsErrorQueryingDatabase_shouldThrowDatabaseException() {
        //Given
        var accountErrorEntity = AccountErrorEntityStub.buildAccountErrorEntity();

        //When
        when(accountErrorRepository.save(accountErrorEntity))
                .thenThrow(new DataAccessException("Error") {});

        //Then
        accountErrorService.saveError(accountErrorEntity);
    }

    @Test
    public void findUnprocessedAccounts_whenFindSome_shouldReturn() {
        //Given
        var accountErrorEntityList = List.of(AccountErrorEntityStub.buildAccountErrorEntity());

        //When
        when(accountErrorRepository.findAll())
                .thenReturn(accountErrorEntityList);

        //Then
        var findResult = accountErrorService.findUnprocessedAccounts();

        assertFalse(findResult.isEmpty());
    }

    @Test
    public void findUnprocessedAccounts_whenFindNone_shouldReturn() {
        //Given
        //When
        when(accountErrorRepository.findAll())
                .thenReturn(Collections.emptyList());

        //Then
        var findResult = accountErrorService.findUnprocessedAccounts();

        assertTrue(findResult.isEmpty());
    }

    @Test(expected = DatabaseExeption.class)
    public void findUnprocessedAccounts_whenGetsErrorQueryingDatabase_shouldThrowDatabaseException() {
        //Given
        var accountErrorEntity = AccountErrorEntityStub.buildAccountErrorEntity();

        //When
        when(accountErrorRepository.findAll())
                .thenThrow(new DataAccessException("Error") {});

        //Then
        accountErrorService.findUnprocessedAccounts();
    }

}