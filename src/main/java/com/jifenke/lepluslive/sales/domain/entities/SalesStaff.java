package com.jifenke.lepluslive.sales.domain.entities;

import com.jifenke.lepluslive.merchant.domain.entities.Merchant;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;

import java.util.List;

/**
 * Created by lss on 2016/8/10.
 */
@Entity
@Table(name = "SALESSTAFF")
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SalesStaff {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String name;

  @OneToMany(mappedBy = "salesStaff", fetch = FetchType.LAZY)
  private List<Merchant> merchant;

  public List<Merchant> getMerchant() {
    return merchant;
  }

  public void setMerchant(List<Merchant> merchant) {
    this.merchant = merchant;
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

  public SalesStaff(String salesStaffId) {
    this.id = Long.parseLong(salesStaffId);
  }

  public SalesStaff() {
  }

  public void addMerchant(Merchant mer) {
    if (mer != null) {
      merchant.add(mer);
    }
  }
}
