package com.sicredi.evaluation.common.service;

import com.sicredi.evaluation.common.exception.DatabaseExeption;
import com.sicredi.evaluation.common.model.AccountErrorEntity;
import com.sicredi.evaluation.common.repository.AccountErrorRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class AccountErrorService {
    private final AccountErrorRepository accountErrorRepository;

    /**
     * Method to save not processed accounts
     *
     * @param accountErrorEntity the account containing the error reason
     * @Throws  when gets querying error
     */
    public void saveError(AccountErrorEntity accountErrorEntity) {
        try {
            accountErrorRepository.save(accountErrorEntity);
        } catch (DataAccessException error) {
            log.error("Error saving accountError: {}", accountErrorEntity);
            throw new DatabaseExeption(error.getMostSpecificCause().getMessage());
        }
    }

    /**
     * Method to find all the unprocessed accounts
     *
     * @return List of accountErrorEntity
     * @Throws  when gets querying error
     */
    public List<AccountErrorEntity> findUnprocessedAccounts() {
        try {
            return accountErrorRepository.findAll();
        } catch (DataAccessException error) {
            log.error("Error finding unprocessed accounts");
            throw new DatabaseExeption(error.getMostSpecificCause().getMessage());
        }
    }
}
