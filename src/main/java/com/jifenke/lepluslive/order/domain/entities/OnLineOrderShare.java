package com.jifenke.lepluslive.order.domain.entities;

import com.jifenke.lepluslive.merchant.domain.entities.Merchant;
import com.jifenke.lepluslive.partner.domain.entities.Partner;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * 线上订单分润 Created by zhangwen on 16/11/05.
 */
@Entity
@Table(name = "ON_LINE_ORDER_SHARE")
public class OnLineOrderShare {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private Long shareMoney = 0L;  //=toLockMerchant + toLockPartner

  private Long toLockMerchant = 0L;

  private Long toLockPartner = 0L;

  private Date createDate = new Date();

  @ManyToOne
  private Merchant lockMerchant;

  @ManyToOne
  private Partner lockPartner;

  public Long getShareMoney() {
    return shareMoney;
  }

  public void setShareMoney(Long shareMoney) {
    this.shareMoney = shareMoney;
  }

  public Merchant getLockMerchant() {
    return lockMerchant;
  }

  public void setLockMerchant(Merchant lockMerchant) {
    this.lockMerchant = lockMerchant;
  }

  public Partner getLockPartner() {
    return lockPartner;
  }

  public void setLockPartner(Partner lockPartner) {
    this.lockPartner = lockPartner;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getToLockMerchant() {
    return toLockMerchant;
  }

  public void setToLockMerchant(Long toLockMerchant) {
    this.toLockMerchant = toLockMerchant;
  }

  public Long getToLockPartner() {
    return toLockPartner;
  }

  public void setToLockPartner(Long toLockPartner) {
    this.toLockPartner = toLockPartner;
  }
}
