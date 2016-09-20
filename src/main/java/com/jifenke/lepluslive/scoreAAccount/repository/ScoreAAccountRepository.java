package com.jifenke.lepluslive.scoreAAccount.repository;

import com.jifenke.lepluslive.scoreAAccount.domain.entities.ScoreAAccount;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Created by Administrator on 2016/9/12.
 */
public interface ScoreAAccountRepository extends JpaRepository<ScoreAAccount,Long> {

  Page findAll(Specification<ScoreAAccount> whereClause, Pageable pageRequest);

  @Query(value = "SELECT  DATE_FORMAT(change_date,'%Y%m%d') days,SUM(use_scorea)use_scorea ,SUM(commission_income) commission_income,SUM(issued_scorea) issued_scorea,SUM(jfk_share) jfk_share FROM `scorea_account_detail` GROUP BY days", nativeQuery = true)
  List<Object[]> findAllFromDetail();

  @Query(value = "SELECT * FROM (SELECT  DATE_FORMAT(change_date,'%Y%m%d') days,SUM(use_scorea)use_scorea ,SUM(commission_income) commission_income,SUM(issued_scorea) issued_scorea,SUM(jfk_share) jfk_share FROM `scorea_account_detail`  GROUP BY days)aa  WHERE days=?1", nativeQuery = true)
  List<Object[]> findOneDayFromDetail(String dateStr);


  @Query(value = "SELECT * FROM (SELECT  DATE_FORMAT(balance_date,'%Y%m%d') days,SUM(transfer_price),SUM(transfer_from_true_pay) FROM `financial_statistic` GROUP BY days) aa WHERE days=?1 ", nativeQuery = true)
  List<Object[]>findSettlementAmount(String o);



  @Query(value = "SELECT SUM(score) FROM scorea", nativeQuery = true)
  Long findPresentHoldScorea();

  @Query(value = "SELECT SUM(number) FROM scorea_detail WHERE number>0", nativeQuery = true)
  Long findIssueScorea();

  @Query(value = "SELECT SUM(number) FROM scorea_detail WHERE number<0", nativeQuery = true)
  Long findUseScorea();

  @Query(value = "SELECT  SUM(lj_commission) FROM off_line_order WHERE  rebate_way=1", nativeQuery = true)
  Long findLjCommission();

  @Query(value = "SELECT SUM(share_money) FROM off_line_order_share", nativeQuery = true)
  Long findShareMoney();
}
