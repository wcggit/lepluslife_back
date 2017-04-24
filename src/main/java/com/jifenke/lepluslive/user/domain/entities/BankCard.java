package com.jifenke.lepluslive.user.domain.entities;

import javax.persistence.*;
import java.util.Date;

/**
 * 用户银行卡号相关接口 Created by zhangwen on 2016/11/28.
 */
@Entity
@Table(name = "BANK_CARD")
public class BankCard {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne
  private LeJiaUser leJiaUser;

  private Date bindDate = new Date();

  private Integer state = 1;  //状态  0=已删除不显示|1=生效中


  private String number; //银行卡号

  private String cardType;  //银行卡的类型

  private Integer cardLength = 0;  //银行卡长度

  private String prefixNum;   //银行卡前缀

  private String cardName;  //银行卡名称

  private String bankName;  //归属银行

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public LeJiaUser getLeJiaUser() {
    return leJiaUser;
  }

  public void setLeJiaUser(LeJiaUser leJiaUser) {
    this.leJiaUser = leJiaUser;
  }

  public Integer getState() {
    return state;
  }

  public void setState(Integer state) {
    this.state = state;
  }

  public Date getBindDate() {
    return bindDate;
  }

  public void setBindDate(Date bindDate) {
    this.bindDate = bindDate;
  }

  public String getCardType() {
    return cardType;
  }

  public void setCardType(String cardType) {
    this.cardType = cardType;
  }

  public Integer getCardLength() {
    return cardLength;
  }

  public void setCardLength(Integer cardLength) {
    this.cardLength = cardLength;
  }

  public String getPrefixNum() {
    return prefixNum;
  }

  public void setPrefixNum(String prefixNum) {
    this.prefixNum = prefixNum;
  }

  public String getCardName() {
    return cardName;
  }

  public void setCardName(String cardName) {
    this.cardName = cardName;
  }

  public String getBankName() {
    return bankName;
  }

  public void setBankName(String bankName) {
    this.bankName = bankName;
  }
}
