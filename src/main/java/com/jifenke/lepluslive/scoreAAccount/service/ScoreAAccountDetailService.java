package com.jifenke.lepluslive.scoreAAccount.service;

import com.jifenke.lepluslive.order.domain.entities.OffLineOrder;
import com.jifenke.lepluslive.order.domain.entities.OffLineOrderShare;
import com.jifenke.lepluslive.order.repository.OffLineOrderRepository;
import com.jifenke.lepluslive.order.repository.OffLineOrderShareRepository;
import com.jifenke.lepluslive.score.domain.entities.ScoreA;
import com.jifenke.lepluslive.score.domain.entities.ScoreADetail;
import com.jifenke.lepluslive.score.repository.ScoreADetailRepository;
import com.jifenke.lepluslive.scoreAAccount.domain.criteria.ScoreAAccountDetailCriteria;
import com.jifenke.lepluslive.scoreAAccount.domain.entities.ScoreAAccountDetail;
import com.jifenke.lepluslive.scoreAAccount.repository.ScoreAAccountDetailRepository;
import com.jifenke.lepluslive.user.domain.entities.LeJiaUser;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by lss on 2016/9/9.
 */
@Service
@Transactional(readOnly = true)
public class ScoreAAccountDetailService {

  @Inject
  private ScoreADetailRepository scoreADetailRepository;

  @Inject
  private OffLineOrderShareRepository offLineOrderShareRepository;

  @Inject
  private ScoreAAccountDetailRepository scoreAAccountDetailRepository;

  @Inject
  private OffLineOrderRepository offLineOrderRepository;

  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public void addAllScoreAAccountDetail() {
    List<ScoreADetail> scoreADetailList = scoreADetailRepository.findAll();
    for (ScoreADetail scoreADetail : scoreADetailList) {
      ScoreAAccountDetail scoreAAccountDetail = new ScoreAAccountDetail();
      Date changeDate = scoreADetail.getDateCreated();
      String orderSid = null;
      Integer origin = scoreADetail.getOrigin();
      String  operate=scoreADetail.getOperate();
      ScoreA scoreA = scoreADetail.getScoreA();
      scoreAAccountDetail.setChangeDate(changeDate);
      scoreAAccountDetail.setChangeProject(origin);
      scoreAAccountDetail.setOperate(operate);
      if (scoreADetail.getNumber() > 0L) {
        scoreAAccountDetail.setIssuedScoreA(scoreADetail.getNumber());
      } else {
        scoreAAccountDetail.setUseScoreA(Math.abs(
            scoreADetail.getNumber()));
      }
      if (origin != null) {
        if (origin == 1 || origin == 2 || origin == 3 || origin == 4) {
          scoreAAccountDetail.setSerialNumber(scoreADetail.getOrderSid());
          orderSid = scoreADetail.getOrderSid();
          if (orderSid != null) {
            OffLineOrder offlineOrder = offLineOrderRepository.findOneByOrderSid(orderSid);
            if (offlineOrder != null) {
              OffLineOrderShare
                  offLineOrderShar =
                  offLineOrderShareRepository.findOneByOrderId(offlineOrder.getId());
              if (offLineOrderShar != null) {
                scoreAAccountDetail.setJfkShare(offLineOrderShar.getShareMoney());
              }
            }
          }
          if (orderSid != null) {
            OffLineOrder offlineOrder = offLineOrderRepository.findOneByOrderSid(orderSid);
            if (offlineOrder != null) {
              scoreAAccountDetail.setCommissionIncome(offlineOrder.getLjCommission());
            }
          }
        }else{
          scoreAAccountDetail.setSerialNumber(scoreADetail.getOrderSid());
          orderSid = scoreADetail.getOrderSid();
          if (scoreA != null) {
            LeJiaUser leJiaUser = scoreA.getLeJiaUser();
            if (leJiaUser != null) {
              scoreAAccountDetail.setSerialNumber(leJiaUser.getUserSid());
            }
          }
        }
        scoreAAccountDetailRepository.save(scoreAAccountDetail);
      } else {
        if (scoreA != null) {
          LeJiaUser leJiaUser = scoreA.getLeJiaUser();
          if (leJiaUser != null) {
            scoreAAccountDetail.setSerialNumber(leJiaUser.getUserSid());
          }
        }
        scoreAAccountDetailRepository.save(scoreAAccountDetail);
      }
    }
  }


  public static Specification<ScoreAAccountDetail> getWhereClause(ScoreAAccountDetailCriteria scoreAAccountDetailCriteria) {
    return new Specification<ScoreAAccountDetail>() {
      @Override
      public Predicate toPredicate(Root<ScoreAAccountDetail> r, CriteriaQuery<?> q,
                                   CriteriaBuilder cb) {
        Predicate predicate = cb.conjunction();
        if (scoreAAccountDetailCriteria.getChangeDate() != null
            && scoreAAccountDetailCriteria.getChangeDate() != "") {
          predicate.getExpressions().add(
              cb.between(r.get("changeDate"), new Date(scoreAAccountDetailCriteria.getChangeDate()+ " " + "00:00:00"),
                         new Date(scoreAAccountDetailCriteria.getChangeDate()+ " " + "23:59:59")));
        }
        if (scoreAAccountDetailCriteria.getChangeProject() != null) {
          predicate.getExpressions().add(
              cb.equal(r.get("changeProject"),
                       scoreAAccountDetailCriteria.getChangeProject()));
        }
        return predicate;
      }
    };
  }



  public Page findScoreAAccountDetailByPage(ScoreAAccountDetailCriteria scoreAAccountDetailCriteria, Integer limit) {
    Sort sort = new Sort(Sort.Direction.DESC, "changeDate");
    return scoreAAccountDetailRepository
        .findAll(getWhereClause(scoreAAccountDetailCriteria),
                 new PageRequest(scoreAAccountDetailCriteria.getOffset() - 1, limit, sort));
  }




}
