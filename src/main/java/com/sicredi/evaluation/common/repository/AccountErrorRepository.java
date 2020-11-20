package com.sicredi.evaluation.common.repository;

import com.sicredi.evaluation.common.model.AccountErrorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountErrorRepository extends JpaRepository<AccountErrorEntity, Long> {
}
