package com.jifenke.lepluslive.yibao.repository;

import com.jifenke.lepluslive.yibao.domain.entities.LedgerQualification;
import com.jifenke.lepluslive.yibao.domain.entities.MerchantUserLedger;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by zhangwen on 2017/7/12.
 */
public interface LedgerQualificationRepository extends JpaRepository<LedgerQualification, Long> {


  LedgerQualification findByMerchantUserLedger(MerchantUserLedger merchantUserLedger);
}
