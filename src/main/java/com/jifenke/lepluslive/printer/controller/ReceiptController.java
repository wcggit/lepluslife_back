package com.jifenke.lepluslive.printer.controller;

import com.jifenke.lepluslive.global.util.LejiaResult;
import com.jifenke.lepluslive.printer.domain.MD5;
import com.jifenke.lepluslive.printer.domain.criteria.ReceiptCriteria;
import com.jifenke.lepluslive.printer.domain.entities.Receipt;
import com.jifenke.lepluslive.printer.service.ReceiptService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lss on 2016/12/27.
 */
@RestController
@RequestMapping("/manage")
public class ReceiptController {

  @Inject
  private ReceiptService receiptService;

  @Value("${printer.apiKey}")
  private String apiKey;

  @RequestMapping(value = "/receiptList", method = RequestMethod.POST)
  public
  @ResponseBody
  LejiaResult receiptList(@RequestBody ReceiptCriteria receiptCriteria) {
    if (receiptCriteria.getOffset() == null) {
      receiptCriteria.setOffset(1);
    }
    Page page = receiptService.findReceiptByPage(receiptCriteria, 10);
    return LejiaResult.ok(page);
  }


  @RequestMapping(value = "/addReceipt", method = RequestMethod.POST)
  public String printer(@RequestBody String paramms) {
    Map<String, String> params = new HashMap<>();
    String a[] = paramms.split("&");
    String orderSid = a[0].split("=")[1];
    String sign = a[1].split("=")[1];
    params.put("orderSid", orderSid);
    params.put("apiKey", apiKey);
    String str = MD5.MD5Encode(apiKey + orderSid).toUpperCase();
    if (str.equals(sign)) {
      receiptService.addReceipt(paramms);
    }
    return "1";
  }


  @RequestMapping(value = "/receiptReturnValues", method = RequestMethod.POST)
  public void receiptReturnValues(@RequestParam String dataid, //单号（与打印接口中返回的id一一对应）
                                  @RequestParam String time, //用于签名
                                  @RequestParam String state, //状态（1为打印完成）
                                  @RequestParam String sign,
                                  @RequestParam String machine_code   //打印机终端号
  ) {
    String str = MD5.MD5Encode(apiKey + time).toUpperCase();
    if (str.equals(sign)) {
      if ("1".equals(state)) {
        System.out.println("打印完成");
        Receipt receipt = receiptService.findBySid(dataid);
        if (receipt.getState() == 0) {
          receipt.setState(1);
        }
        if (receipt.getState() == 4) {
          receipt.setState(3);
        }
        receiptService.saveOne(receipt);
      } else {
        System.out.println("重新打印");
        receiptService.printUnsuccessfulOrder(dataid);
      }
    }
  }

  /**
   * 接口可用性测试  2017/6/12
   */
  @RequestMapping(value = "/receiptReturnValues", method = RequestMethod.GET)
  public String receiptReturnValues() {
    return "{\"data\":\"OK\"}";
  }
}
