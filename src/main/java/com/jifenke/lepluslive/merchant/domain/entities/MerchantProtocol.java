package com.jifenke.lepluslive.merchant.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Created by wcg on 16/6/6.
 */
@Entity
@Table(name = "MERCHANT_PROTOCOL")
public class MerchantProtocol {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;


  private String picture;                     // 图片 URL

  @ManyToOne
  private Merchant merchant;                  // 商户id

  @ManyToOne
  private MerchantProtocolType
      protocolType;
      // 图片类型: 1-平台受理服务协议书  2-商户基础资料表 3-中汇支付收单特约商户信息调查表  4-结算授权书

  @Column(name = "pic_index", length = 20, columnDefinition = "BIGINT")
  private Long picIndex;                         // 位置索引: 从0开始
  @Column(name = "pic_state", length = 20, columnDefinition = "BIGINT")
  private Long picState;                         // 图片状态: 0-正常 1-失效

  public Long getPicIndex() {
    return picIndex;
  }

  public void setPicIndex(Long picIndex) {
    this.picIndex = picIndex;
  }

  public Long getPicState() {
    return picState;
  }

  public void setPicState(Long picState) {
    this.picState = picState;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getPicture() {
    return picture;
  }

  public void setPicture(String picture) {
    this.picture = picture;
  }

  public Merchant getMerchant() {
    return merchant;
  }

  public void setMerchant(Merchant merchant) {
    this.merchant = merchant;
  }

  public MerchantProtocolType getProtocolType() {
    return protocolType;
  }

  public void setProtocolType(MerchantProtocolType protocolType) {
    this.protocolType = protocolType;
  }
}
