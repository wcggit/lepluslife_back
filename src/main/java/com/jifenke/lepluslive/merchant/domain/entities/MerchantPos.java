package com.jifenke.lepluslive.merchant.domain.entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by wcg on 16/8/2.
 */
@Entity
@Table(name = "MERCHANT_POS")
public class MerchantPos {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne
  private Merchant merchant;

  private String posId;

  private String sshKey;

  private String psamCard;

  private BigDecimal debitCardCommission; //借记卡佣金比

  private BigDecimal ljCommission; //会员刷卡消费佣金

  private BigDecimal wxCommission; // 微信佣金

  private BigDecimal aliCommission; //阿里佣金

  private BigDecimal creditCardCommission; //贷记卡佣金比

  private Long ceil; //封顶手续费

  public BigDecimal getLjCommission() {
    return ljCommission;
  }

  public void setLjCommission(BigDecimal ljCommission) {
    this.ljCommission = ljCommission;
  }

  public String getPsamCard() {
    return psamCard;
  }

  public void setPsamCard(String psamCard) {
    this.psamCard = psamCard;
  }


  public BigDecimal getWxCommission() {
    return wxCommission;
  }

  public void setWxCommission(BigDecimal wxCommission) {
    this.wxCommission = wxCommission;
  }

  public BigDecimal getAliCommission() {
    return aliCommission;
  }

  public void setAliCommission(BigDecimal aliCommission) {
    this.aliCommission = aliCommission;
  }

  public Long getCeil() {
    return ceil;
  }

  public void setCeil(Long ceil) {
    this.ceil = ceil;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Merchant getMerchant() {
    return merchant;
  }

  public void setMerchant(Merchant merchant) {
    this.merchant = merchant;
  }

  public String getPosId() {
    return posId;
  }

  public void setPosId(String posId) {
    this.posId = posId;
  }

  public String getSshKey() {
    return sshKey;
  }

  public void setSshKey(String sshKey) {
    this.sshKey = sshKey;
  }


  public BigDecimal getDebitCardCommission() {
    return debitCardCommission;
  }

  public void setDebitCardCommission(BigDecimal debitCardCommission) {
    this.debitCardCommission = debitCardCommission;
  }

  public BigDecimal getCreditCardCommission() {
    return creditCardCommission;
  }

  public void setCreditCardCommission(BigDecimal creditCardCommission) {
    this.creditCardCommission = creditCardCommission;
  }

  private Date createdDate;                  // 创建时间

  private BigDecimal wxProcedureFee;         // 微信手续费
  private BigDecimal aliProcedureFee;        // 阿里手续费
  private BigDecimal streamScoreA;           // 导流订单参数：   红包比
  private BigDecimal streamScoreB;           // 导流订单参数：   积分比
  private BigDecimal lejiaScoreA;            // 会员订单参数：   红包比
  private BigDecimal lejiaScoreB;            // 会员订单参数：   积分比

  public BigDecimal getStreamScoreA() {
    return streamScoreA;
  }

  public void setStreamScoreA(BigDecimal streamScoreA) {
    this.streamScoreA = streamScoreA;
  }

  public BigDecimal getStreamScoreB() {
    return streamScoreB;
  }

  public void setStreamScoreB(BigDecimal streamScoreB) {
    this.streamScoreB = streamScoreB;
  }

  public BigDecimal getWxProcedureFee() {
    return wxProcedureFee;
  }

  public void setWxProcedureFee(BigDecimal wxProcedureFee) {
    this.wxProcedureFee = wxProcedureFee;
  }

  public BigDecimal getAliProcedureFee() {
    return aliProcedureFee;
  }

  public void setAliProcedureFee(BigDecimal aliProcedureFee) {
    this.aliProcedureFee = aliProcedureFee;
  }

  public Date getCreatedDate() {
    return createdDate;
  }

  public void setCreatedDate(Date createDate) {
    this.createdDate = createDate;
  }

  public BigDecimal getLejiaScoreA() {
    return lejiaScoreA;
  }

  public void setLejiaScoreA(BigDecimal lejiaScoreA) {
    this.lejiaScoreA = lejiaScoreA;
  }

  public BigDecimal getLejiaScoreB() {
    return lejiaScoreB;
  }

  public void setLejiaScoreB(BigDecimal lejiaScoreB) {
    this.lejiaScoreB = lejiaScoreB;
  }
}
