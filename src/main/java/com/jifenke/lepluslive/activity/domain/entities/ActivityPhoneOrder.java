package com.jifenke.lepluslive.activity.domain.entities;

import com.jifenke.lepluslive.global.util.MvUtil;
import com.jifenke.lepluslive.order.domain.entities.PayOrigin;
import com.jifenke.lepluslive.user.domain.entities.LeJiaUser;

import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 话费订单 Created by zhangwen on 2016/10/26.
 */
@Entity
@Table(name = "ACTIVITY_PHONE_ORDER")
public class ActivityPhoneOrder {

  @Id
  @GeneratedValue(generator = "system-uuid")
  @GenericGenerator(name = "system-uuid", strategy = "uuid")
  private String id;

  private String orderSid = MvUtil.getOrderNumber(); //自己的订单编号

  private String orderId;   //第三方充值平台的订单编号

  private Integer type = 1;  //订单类型  1=积分订单|2=金币订单

  private Integer cheap = 0;  //该订单是否是特惠订单

  private Integer state = 0;  //订单状态  0=待支付|1=已支付待充值|2=已充值|3=已支付充值失败

  private Integer payState = 0;  //订单是否已支付

  @ManyToOne
  private LeJiaUser leJiaUser;

  @ManyToOne
  private ActivityPhoneRule phoneRule;

  @ManyToOne
  private PayOrigin payOrigin;    //支付方式及订单来源

  private String phone;  //实际充值手机号

  private Integer worth;  //充值面额 元为单位

  private Integer truePrice = 0;  //用户实际支付金额

  private Integer trueScoreB = 0;  //用户实际使用积分或金币

  private Integer usePrice = 0;   //账户实际消耗金额

  private Integer payBackScore = 0;  //订单返红包金额

  private Date createDate = new Date();   //创建时间

  private Date payDate; //微信支付时间或使用积分付款时间

  private Date completeDate;  //话费充值成功时间

  private Date errorDate;  //第三方回调充值失败时间

  private String message;   //第三方回调充值失败原因

  private Integer platform = 1;  //1=正常充值|2=充值失败后补交充值|3=其他平台手动充值

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getOrderSid() {
    return orderSid;
  }

  public void setOrderSid(String orderSid) {
    this.orderSid = orderSid;
  }

  public String getOrderId() {
    return orderId;
  }

  public void setOrderId(String orderId) {
    this.orderId = orderId;
  }

  public Integer getState() {
    return state;
  }

  public void setState(Integer state) {
    this.state = state;
  }

  public LeJiaUser getLeJiaUser() {
    return leJiaUser;
  }

  public void setLeJiaUser(LeJiaUser leJiaUser) {
    this.leJiaUser = leJiaUser;
  }

  public ActivityPhoneRule getPhoneRule() {
    return phoneRule;
  }

  public void setPhoneRule(ActivityPhoneRule phoneRule) {
    this.phoneRule = phoneRule;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public Integer getWorth() {
    return worth;
  }

  public void setWorth(Integer worth) {
    this.worth = worth;
  }

  public Integer getTruePrice() {
    return truePrice;
  }

  public void setTruePrice(Integer truePrice) {
    this.truePrice = truePrice;
  }

  public Integer getTrueScoreB() {
    return trueScoreB;
  }

  public Integer getPlatform() {
    return platform;
  }

  public void setPlatform(Integer platform) {
    this.platform = platform;
  }

  public PayOrigin getPayOrigin() {
    return payOrigin;
  }

  public void setPayOrigin(PayOrigin payOrigin) {
    this.payOrigin = payOrigin;
  }

  public void setTrueScoreB(Integer trueScoreB) {
    this.trueScoreB = trueScoreB;
  }

  public Integer getUsePrice() {
    return usePrice;
  }

  public void setUsePrice(Integer usePrice) {
    this.usePrice = usePrice;
  }

  public Integer getPayBackScore() {
    return payBackScore;
  }

  public void setPayBackScore(Integer payBackScore) {
    this.payBackScore = payBackScore;
  }

  public Date getCreateDate() {
    return createDate;
  }

  public void setCreateDate(Date createDate) {
    this.createDate = createDate;
  }

  public Integer getCheap() {
    return cheap;
  }

  public void setCheap(Integer cheap) {
    this.cheap = cheap;
  }

  public Date getPayDate() {
    return payDate;
  }

  public void setPayDate(Date payDate) {
    this.payDate = payDate;
  }

  public Integer getPayState() {
    return payState;
  }

  public void setPayState(Integer payState) {
    this.payState = payState;
  }

  public Date getCompleteDate() {
    return completeDate;
  }

  public void setCompleteDate(Date completeDate) {
    this.completeDate = completeDate;
  }

  public Date getErrorDate() {
    return errorDate;
  }

  public void setErrorDate(Date errorDate) {
    this.errorDate = errorDate;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Integer getType() {
    return type;
  }

  public void setType(Integer type) {
    this.type = type;
  }
}
