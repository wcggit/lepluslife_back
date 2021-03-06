package com.jifenke.lepluslive.groupon.domain.entities;

import com.jifenke.lepluslive.global.util.MvUtil;
import com.jifenke.lepluslive.merchant.domain.entities.Merchant;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Created by wcg on 2017/6/27.
 */
@Entity
@Table(name = "GROUPON_STATISTIC")
public class GrouponStatistic {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @ManyToOne
  private Merchant merchant;

  private Long check; //核销几笔

  private Long totalMoney;//算上佣金总价

  private Long commission; //佣金

  private Long transferMoney; //转账金额

  private Date balanceDate = new Date(); //结算日期

  private Date completeDate;

  @Column(unique = true)
  private String sid = MvUtil.getRandomNumber(12);

  @Version
  private Long version = 0L;

  private Integer state = 0;

  public Long getTotalMoney() {
    return totalMoney;
  }

  public void setTotalMoney(Long totalMoney) {
    this.totalMoney = totalMoney;
  }

  public Long getCommission() {
    return commission;
  }

  public void setCommission(Long commission) {
    this.commission = commission;
  }

  public String getSid() {
    return sid;
  }

  public void setSid(String sid) {
    this.sid = sid;
  }

  public Integer getState() {
    return state;
  }

  public void setState(Integer state) {
    this.state = state;
  }

  public Long getVersion() {
    return version;
  }

  public void setVersion(Long version) {
    this.version = version;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Merchant getMerchant() {
    return merchant;
  }

  public void setMerchant(Merchant merchant) {
    this.merchant = merchant;
  }

  public Long getCheck() {
    return check;
  }

  public void setCheck(Long check) {
    this.check = check;
  }

  public Long getTransferMoney() {
    return transferMoney;
  }

  public void setTransferMoney(Long transferMoney) {
    this.transferMoney = transferMoney;
  }

  public Date getBalanceDate() {
    return balanceDate;
  }

  public void setBalanceDate(Date balanceDate) {
    this.balanceDate = balanceDate;
  }

  public Date getCompleteDate() {
    return completeDate;
  }

  public void setCompleteDate(Date completeDate) {
    this.completeDate = completeDate;
  }
}
