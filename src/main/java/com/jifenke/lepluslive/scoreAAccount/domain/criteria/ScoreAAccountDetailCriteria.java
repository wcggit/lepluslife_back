package com.jifenke.lepluslive.scoreAAccount.domain.criteria;

/**
 * Created by lss on 2016/9/18.
 */
public class ScoreAAccountDetailCriteria {
  private String changeDate;
  private Integer changeProject;
  private Integer offset;

  public Integer getOffset() {
    return offset;
  }

  public void setOffset(Integer offset) {
    this.offset = offset;
  }

  public String getChangeDate() {
    return changeDate;
  }

  public void setChangeDate(String changeDate) {
    this.changeDate = changeDate;
  }

  public Integer getChangeProject() {
    return changeProject;
  }

  public void setChangeProject(Integer changeProject) {
    this.changeProject = changeProject;
  }
}
