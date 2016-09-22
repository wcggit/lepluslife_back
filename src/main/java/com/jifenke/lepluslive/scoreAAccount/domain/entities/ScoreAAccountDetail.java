package com.jifenke.lepluslive.scoreAAccount.domain.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by lss on 2016/9/9.
 */
@Entity
@Table(name = "SCOREA_ACCOUNT_DETAIL")
public class ScoreAAccountDetail {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private  String serialNumber;//订单 会员 编号

  private Date changeDate; //红包变更时间

  private Integer changeProject;//变更项目

  private String operate;//变更项目注释

  private Long useScoreA;//消费者使用红包

  private Long IssuedScoreA;//发放红包

  private Long  commissionIncome;//佣金收入

  private Long jfkShare;//分润后积分客收入

  public Long getUseScoreA() {
    return useScoreA;
  }

  public void setUseScoreA(Long useScoreA) {
    this.useScoreA = useScoreA;
  }

  public Long getJfkShare() {

    return jfkShare;
  }

  public String getOperate() {
    return operate;
  }

  public void setOperate(String operate) {
    this.operate = operate;
  }

  public void setJfkShare(Long jfkShare) {
    this.jfkShare = jfkShare;
  }

  public Long getIssuedScoreA() {
    return IssuedScoreA;
  }

  public void setIssuedScoreA(Long issuedScoreA) {
    IssuedScoreA = issuedScoreA;
  }

  public Long getId() {

    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getCommissionIncome() {

    return commissionIncome;
  }

  public void setCommissionIncome(Long commissionIncome) {
    this.commissionIncome = commissionIncome;
  }

  public Integer getChangeProject() {
    return changeProject;
  }

  public void setChangeProject(Integer changeProject) {
    this.changeProject = changeProject;
  }

  public Date getChangeDate() {

    return changeDate;
  }

  public void setChangeDate(Date changeDate) {
    this.changeDate = changeDate;
  }

  public String getSerialNumber() {
    return serialNumber;
  }

  public void setSerialNumber(String serialNumber) {
    this.serialNumber = serialNumber;
  }
}
