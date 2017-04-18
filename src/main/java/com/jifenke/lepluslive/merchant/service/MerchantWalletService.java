package com.jifenke.lepluslive.merchant.service;

import com.jifenke.lepluslive.merchant.domain.entities.MerchantWallet;
import com.jifenke.lepluslive.merchant.repository.MerchantWalletRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * 门店钱包 Created by zhangwen on 2017/1/3.
 */
@Service
@Transactional(readOnly = true)
public class MerchantWalletService {

  @Inject
  private MerchantWalletRepository repository;

  /**
   * 给某个门店钱包加结算单转账金额  2017/01/03
   *
   * @param merchantId    门店ID
   * @param transferPrice 结算单总转账金额
   */
  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public void changeMerchantWalletTotalTransferMoney(Long merchantId, Long transferPrice) {
    MerchantWallet merchantWallet = repository.findByMerchantId(merchantId);
    if (merchantWallet != null) {
      merchantWallet.setTotalTransferMoney(merchantWallet.getTotalTransferMoney() + transferPrice);
      repository.save(merchantWallet);
    }
  }

}
