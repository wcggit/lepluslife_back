package com.jifenke.lepluslive.yibao.service;


import com.jifenke.lepluslive.yibao.domain.criteria.LedgerSettlementCriteria;
import com.jifenke.lepluslive.yibao.domain.entities.LedgerSettlement;
import com.jifenke.lepluslive.yibao.repository.LedgerSettlementRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Date;


/**
 * Created by zhangwen on 2017/7/12.
 */
@Service
@Transactional(readOnly = true)
public class LedgerSettlementService {

  @Inject
  private LedgerSettlementRepository ledgerSettlementRepository;

  /***
   *  根据条件查询通道结算单
   *  Created by xf on 2017-07-13.
   */
  @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
  public Page<LedgerSettlement> findByCriteria(LedgerSettlementCriteria criteria, Integer limit) {
    Sort sort = new Sort(Sort.Direction.DESC, "createDate");
    return ledgerSettlementRepository.findAll(getWhereClause(criteria), new PageRequest(criteria.getOffset() - 1, limit, sort));
  }

  public static Specification<LedgerSettlement> getWhereClause(LedgerSettlementCriteria criteria) {
    return new Specification<LedgerSettlement>() {
      @Override
      public Predicate toPredicate(Root<LedgerSettlement> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Predicate predicate = cb.conjunction();
        // 转账状态
        if (criteria.getTransferState() != null) {
          predicate.getExpressions().add(
                  cb.equal(root.get("transferState"), criteria.getTransferState()));
        }
        // 结算状态
        if (criteria.getState() != null) {
          predicate.getExpressions().add(
                  cb.equal(root.get("state"), criteria.getState()));
        }
        // 乐加商户编号
        if (criteria.getMerchantUserId() != null) {
          predicate.getExpressions().add(
                  cb.equal(root.get("merchantUserId"), criteria.getMerchantUserId()));
        }
        // 易宝商户号
        if (criteria.getLedgerNo() != null) {
          predicate.getExpressions().add(
                  cb.equal(root.get("ledgerNo"),  criteria.getLedgerNo()));
        }
        // 转账请求号
        if (criteria.getOrderSid() != null) {
          predicate.getExpressions().add(
                  cb.equal(root.get("orderSid"), criteria.getOrderSid()));
        }
        //  通道结算单号
        if (criteria.getOrderSid() != null) {
          predicate.getExpressions().add(
                  cb.equal(root.get("orderSid"), criteria.getOrderSid()));
        }
        //  清算日期
        if (criteria.getStartDate() != null && criteria.getEndDate() != null) {
          Date start = new Date(criteria.getStartDate());
          Date end = new Date(criteria.getEndDate());
          predicate.getExpressions().add(
                  cb.between(root.get("tradeDate"), start, end));
        }
        return predicate;
      }
    };
  }
}
