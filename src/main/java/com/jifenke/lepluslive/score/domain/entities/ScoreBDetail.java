package com.jifenke.lepluslive.score.domain.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by wcg on 16/3/18.
 */
@Entity
@Table(name = "SCOREB_DETAIL")
public class ScoreBDetail {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;


  private Long number;
  private String operate;
  private Date dateCreated = new Date();

  @ManyToOne
  private ScoreB scoreB;

  private Integer origin;  //1=线上返还  2=线上消费  3=线下消费  4=线下返还   5=活动返还   6=运动   7=摇一摇  8=APP分享
  // 9=线下支付完成页注册会员 10=合伙人发福利  11=临时活动 13=充话费发送包  15001=领取优惠券消耗  15002=富友订单退款

  private String orderSid;  //对应的订单号

  public Integer getOrigin() {
    return origin;
  }

  public void setOrigin(Integer origin) {
    this.origin = origin;
  }

  public String getOrderSid() {
    return orderSid;
  }

  public void setOrderSid(String orderSid) {
    this.orderSid = orderSid;
  }

  public ScoreB getScoreB() {
    return scoreB;
  }

  public void setScoreB(ScoreB scoreB) {
    this.scoreB = scoreB;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getNumber() {
    return number;
  }

  public void setNumber(Long number) {
    this.number = number;
  }

  public String getOperate() {
    return operate;
  }

  public void setOperate(String operate) {
    this.operate = operate;
  }

  public Date getDateCreated() {
    return dateCreated;
  }

  public void setDateCreated(Date dateCreated) {
    this.dateCreated = dateCreated;
  }
}
