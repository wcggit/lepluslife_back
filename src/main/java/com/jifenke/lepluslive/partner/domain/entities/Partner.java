package com.jifenke.lepluslive.partner.domain.entities;

import com.jifenke.lepluslive.global.util.MvUtil;
import com.jifenke.lepluslive.user.domain.entities.WeiXinUser;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by wcg on 16/6/3. 合伙人表
 */
@Entity
@Table(name = "PARTNER")
public class Partner {


  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String partnerSid = MvUtil.getPartnerSid();

  private String name; //账户名

  private Long userLimit = 0L;

  private Long merchantLimit = 0L;

  private String partnerName; //合伙人姓名

  private String phoneNumber;

  private String password;

  private String bankName;

  private String bankNumber;

  private String payee;

  private Integer benefitTime; //发放福利次数

  @ManyToOne
  private PartnerManager partnerManager;

  @OneToOne
  private WeiXinUser weiXinUser;

  private Integer origin = 0; //0 后台添加  1 公众号注册

  private Date registerDate;

  public Integer getBenefitTime() {
    return benefitTime;
  }

  public void setBenefitTime(Integer benefitTime) {
    this.benefitTime = benefitTime;
  }

  public String getPayee() {
    return payee;
  }

  public void setPayee(String payee) {
    this.payee = payee;
  }

  public String getBankName() {
    return bankName;
  }

  public void setBankName(String bankName) {
    this.bankName = bankName;
  }

  public String getBankNumber() {
    return bankNumber;
  }

  public void setBankNumber(String bankNumber) {
    this.bankNumber = bankNumber;
  }

  public PartnerManager getPartnerManager() {
    return partnerManager;
  }

  public void setPartnerManager(PartnerManager partnerManager) {
    this.partnerManager = partnerManager;
  }

  public Long getMerchantLimit() {
    return merchantLimit;
  }

  public void setMerchantLimit(Long merchantLimit) {
    this.merchantLimit = merchantLimit;
  }

  public String getPartnerName() {
    return partnerName;
  }

  public void setPartnerName(String partnerName) {
    this.partnerName = partnerName;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Long getUserLimit() {
    return userLimit;
  }

  public void setUserLimit(Long userLimit) {
    this.userLimit = userLimit;
  }

  public Partner(Long id) {
    this.id = id;
  }

  public Partner() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPartnerSid() {
    return partnerSid;
  }

  public void setPartnerSid(String partnerSid) {
    this.partnerSid = partnerSid;
  }

  public WeiXinUser getWeiXinUser() {
    return weiXinUser;
  }

  public void setWeiXinUser(WeiXinUser weiXinUser) {
    this.weiXinUser = weiXinUser;
  }

  public Integer getOrigin() {
    return origin;
  }

  public void setOrigin(Integer origin) {
    this.origin = origin;
  }

  public Date getRegisterDate() {
    return registerDate;
  }

  public void setRegisterDate(Date registerDate) {
    this.registerDate = registerDate;
  }
}
