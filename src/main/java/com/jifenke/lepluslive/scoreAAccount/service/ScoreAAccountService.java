package com.jifenke.lepluslive.scoreAAccount.service;

import com.jifenke.lepluslive.order.domain.entities.OffLineOrder;
import com.jifenke.lepluslive.order.domain.entities.OffLineOrderShare;
import com.jifenke.lepluslive.order.repository.OffLineOrderRepository;
import com.jifenke.lepluslive.order.repository.OffLineOrderShareRepository;
import com.jifenke.lepluslive.score.domain.entities.ScoreA;
import com.jifenke.lepluslive.score.domain.entities.ScoreADetail;
import com.jifenke.lepluslive.score.repository.ScoreADetailRepository;
import com.jifenke.lepluslive.scoreAAccount.domain.criteria.ScoreAAccountCriteria;
import com.jifenke.lepluslive.scoreAAccount.domain.entities.ScoreAAccount;
import com.jifenke.lepluslive.scoreAAccount.domain.entities.ScoreAAccountDetail;
import com.jifenke.lepluslive.scoreAAccount.repository.ScoreAAccountDetailRepository;
import com.jifenke.lepluslive.scoreAAccount.repository.ScoreAAccountRepository;
import com.jifenke.lepluslive.user.domain.entities.LeJiaUser;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 * Created by Administrator on 2016/9/12.
 */
@Service
@Transactional(readOnly = true)
public class ScoreAAccountService {

  @Inject
  private ScoreAAccountRepository scoreAAccountRepository;

  @Inject
  private ScoreADetailRepository scoreADetailRepository;

  @Inject
  private OffLineOrderShareRepository offLineOrderShareRepository;

  @Inject
  private ScoreAAccountDetailRepository scoreAAccountDetailRepository;

  @Inject
  private OffLineOrderRepository offLineOrderRepository;

  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  //添加一天的红包账户明细和红包账户
  public void addOneDayScoreAAccountData() {
    SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
    Date beginDate = new Date();
    Calendar date = Calendar.getInstance();
    date.setTime(beginDate);
    date.set(Calendar.DATE, date.get(Calendar.DATE) - 1);

    try {
      Date endDate = dft.parse(dft.format(date.getTime()));
      String endDateString=dft.format(endDate);
      String[] stringArray = endDateString.split("-");
      String dateStr=stringArray[0]+stringArray[1]+stringArray[2];
      addOneDayScoreAAccountDetail(dateStr);
      addOneDayScoreAAccount(dateStr);
    } catch (ParseException e) {
      e.printStackTrace();
    }

    //  List<ScoreADetail> scoreADetailList = scoreADetailRepository.findByDate();
  }
  public static Specification<ScoreAAccount> getWhereClause(ScoreAAccountCriteria scoreAAccountCriteria) {
    return new Specification<ScoreAAccount>() {
      @Override
      public Predicate toPredicate(Root<ScoreAAccount> r, CriteriaQuery<?> q,
                                   CriteriaBuilder cb) {
        Predicate predicate = cb.conjunction();
        if (scoreAAccountCriteria.getStartDate() != null && scoreAAccountCriteria.getStartDate() != "") {
          predicate.getExpressions().add(
              cb.between(r.get("changeDate"), new Date(scoreAAccountCriteria.getStartDate()),
                         new Date(scoreAAccountCriteria.getEndDate())));
        }
        return predicate;
      }
    };
  }



  public Page findScoreAAccountByPage(ScoreAAccountCriteria scoreAAccountCriteria, Integer limit) {
    Sort sort = new Sort(Sort.Direction.DESC, "changeDate");
    return scoreAAccountRepository
        .findAll(getWhereClause(scoreAAccountCriteria),
                 new PageRequest(scoreAAccountCriteria.getOffset() - 1, limit, sort));
  }
  //添加一天的红包账户明细
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public void addOneDayScoreAAccountDetail(String dateStr) {
    List<ScoreADetail> scoreADetailList = scoreADetailRepository.findAllByDate(dateStr);
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
  //添加一天的红包账户
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public void addOneDayScoreAAccount(String dateStr1) {
    List<Object[]> list = scoreAAccountRepository.findOneDayFromDetail(dateStr1);
    for (Object[] object : list) {
      ScoreAAccount scoreAAccount = new ScoreAAccount();

      String objectDateStr = object[0].toString();
      char[] stringArr = objectDateStr.toCharArray();
      char[] y = {stringArr[0], stringArr[1], stringArr[2], stringArr[3]};
      char[] m = {stringArr[4], stringArr[5]};
      char[] d = {stringArr[6], stringArr[7]};
      String s1 = new String(y);
      String s2 = new String(m);
      String s3 = new String(d);
      String dateStr = s1 + "-" + s2 + "-" + s3;
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      try {
        Date date = sdf.parse(dateStr);
        scoreAAccount.setChangeDate(date);
      } catch (Exception e) {
        e.printStackTrace();
      }

      Long useScoreA = 0L;
      if (object[1] != null) {
        scoreAAccount.setUseScoreA(Long.parseLong(object[1].toString()));
      } else {
        scoreAAccount.setUseScoreA(useScoreA);
      }

      Long commissionIncome = 0L;
      if (object[2]!= null) {
        scoreAAccount.setCommissionIncome(Long.parseLong(object[2].toString()));
      } else {
        scoreAAccount.setCommissionIncome(commissionIncome);
      }

      Long IssuedScoreA = 0L;
      if (object[3] != null) {
        scoreAAccount.setIssuedScoreA(Long.parseLong(object[3].toString()));
      } else {
        scoreAAccount.setIssuedScoreA(IssuedScoreA);
      }

      Long jfkShare = 0L;
      if (object[4] != null) {
        scoreAAccount.setJfkShare(Long.parseLong(object[4].toString()));
      } else {
        scoreAAccount.setJfkShare(jfkShare);
      }

      Long settlementAmount = 0L;
      List<Object[]> objects = scoreAAccountRepository.findSettlementAmount(object[0].toString());
      if (objects.size() == 1) {
        Long transferPrice = 0L;
        Long transferFromTruePay = 0L;
        if (objects.get(0)[1] != null) {
          transferPrice = Long.parseLong(objects.get(0)[1].toString());
        }
        if (objects.get(0)[2] != null) {
          transferFromTruePay = Long.parseLong(objects.get(0)[2].toString());
        }
        settlementAmount = transferPrice - transferFromTruePay;
        scoreAAccount.setSettlementAmount(settlementAmount);
      } else {
        scoreAAccount.setSettlementAmount(settlementAmount);
      }
      scoreAAccountRepository.save(scoreAAccount);
    }
  }
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public void addAllScoreAAccount() {
    List<Object[]> list = scoreAAccountRepository.findAllFromDetail();
    for (Object[] object : list) {
      ScoreAAccount scoreAAccount = new ScoreAAccount();

      String objectDateStr = object[0].toString();
      char[] stringArr = objectDateStr.toCharArray();
      char[] y = {stringArr[0], stringArr[1], stringArr[2], stringArr[3]};
      char[] m = {stringArr[4], stringArr[5]};
      char[] d = {stringArr[6], stringArr[7]};
      String s1 = new String(y);
      String s2 = new String(m);
      String s3 = new String(d);
      String dateStr = s1 + "-" + s2 + "-" + s3;
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      try {
        Date date = sdf.parse(dateStr);
        scoreAAccount.setChangeDate(date);
      } catch (Exception e) {
        e.printStackTrace();
      }

      Long useScoreA = 0L;
      if (object[1] != null) {
        scoreAAccount.setUseScoreA(Long.parseLong(object[1].toString()));
      } else {
        scoreAAccount.setUseScoreA(useScoreA);
      }

      Long commissionIncome = 0L;
      if (object[2]!= null) {
        scoreAAccount.setCommissionIncome(Long.parseLong(object[2].toString()));
      } else {
        scoreAAccount.setCommissionIncome(commissionIncome);
      }

      Long IssuedScoreA = 0L;
      if (object[3] != null) {
        scoreAAccount.setIssuedScoreA(Long.parseLong(object[3].toString()));
      } else {
        scoreAAccount.setIssuedScoreA(IssuedScoreA);
      }

      Long jfkShare = 0L;
      if (object[4] != null) {
        scoreAAccount.setJfkShare(Long.parseLong(object[4].toString()));
      } else {
        scoreAAccount.setJfkShare(jfkShare);
      }

      Long settlementAmount = 0L;
      List<Object[]> objects = scoreAAccountRepository.findSettlementAmount(object[0].toString());
      if (objects.size() == 1) {
        Long transferPrice = 0L;
        Long transferFromTruePay = 0L;
        if (objects.get(0)[1] != null) {
          transferPrice = Long.parseLong(objects.get(0)[1].toString());
        }
        if (objects.get(0)[2] != null) {
          transferFromTruePay = Long.parseLong(objects.get(0)[2].toString());
        }
        settlementAmount = transferPrice - transferFromTruePay;
        scoreAAccount.setSettlementAmount(settlementAmount);
      } else {
        scoreAAccount.setSettlementAmount(settlementAmount);
      }
      scoreAAccountRepository.save(scoreAAccount);
    }
  }

  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  public Long findPresentHoldScorea() {
    return  scoreAAccountRepository.findPresentHoldScorea();
  }

  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  public Long findIssueScorea() {
    return  scoreAAccountRepository.findIssueScorea();
  }

  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  public Long findUseScorea() {
    return  scoreAAccountRepository.findUseScorea();
  }

  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  public Long findLjCommission() {
    return  scoreAAccountRepository.findLjCommission();
  }

  @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
  public Long findShareMoney() {
    return  scoreAAccountRepository.findShareMoney();
  }
}
