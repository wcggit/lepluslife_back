package com.jifenke.lepluslive.merchant.controller.dto;

import com.jifenke.lepluslive.merchant.domain.entities.Merchant;
import com.jifenke.lepluslive.merchant.domain.entities.MerchantRebatePolicy;
import com.jifenke.lepluslive.merchant.domain.entities.MerchantScanPayWay;
import com.jifenke.lepluslive.merchant.domain.entities.MerchantSettlementStore;

/**
 * Created by xf on 16-11-9.
 */
public class MerchantDto {
    private Merchant merchant;
    private MerchantRebatePolicy merchantRebatePolicy;

  private MerchantScanPayWay merchantScanPayWay;

  private MerchantSettlementStore merchantSettlementStore;

    public Merchant getMerchant() {
        return merchant;
    }

    public void setMerchant(Merchant merchant) {
        this.merchant = merchant;
    }

    public MerchantRebatePolicy getMerchantRebatePolicy() {
        return merchantRebatePolicy;
    }

    public void setMerchantRebatePolicy(MerchantRebatePolicy merchantRebatePolicy) {
        this.merchantRebatePolicy = merchantRebatePolicy;
    }

  public MerchantScanPayWay getMerchantScanPayWay() {
    return merchantScanPayWay;
  }

  public void setMerchantScanPayWay(MerchantScanPayWay merchantScanPayWay) {
    this.merchantScanPayWay = merchantScanPayWay;
  }

  public MerchantSettlementStore getMerchantSettlementStore() {
    return merchantSettlementStore;
  }

  public void setMerchantSettlementStore(MerchantSettlementStore merchantSettlementStore) {
    this.merchantSettlementStore = merchantSettlementStore;
  }
}
