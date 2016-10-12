package com.jifenke.lepluslive.web.rest;

import com.jifenke.lepluslive.Application;
import com.jifenke.lepluslive.barcode.BarcodeConfig;
import com.jifenke.lepluslive.barcode.service.BarcodeService;
import com.jifenke.lepluslive.filemanage.service.FileImageService;
import com.jifenke.lepluslive.global.config.Constants;
import com.jifenke.lepluslive.global.util.MD5Util;
import com.jifenke.lepluslive.global.util.MvUtil;
import com.jifenke.lepluslive.merchant.domain.entities.Merchant;
import com.jifenke.lepluslive.merchant.domain.entities.MerchantUser;
import com.jifenke.lepluslive.merchant.repository.MerchantRepository;
import com.jifenke.lepluslive.merchant.service.MerchantService;
import com.jifenke.lepluslive.order.service.FinanicalStatisticService;
import com.jifenke.lepluslive.order.service.OffLineOrderService;
import com.jifenke.lepluslive.partner.domain.entities.Partner;
import com.jifenke.lepluslive.score.repository.ScoreARepository;
import com.jifenke.lepluslive.score.repository.ScoreBRepository;
import com.jifenke.lepluslive.scoreAAccount.repository.ScoreAAccountRepository;
import com.jifenke.lepluslive.scoreAAccount.service.ScoreAAccountService;
import com.jifenke.lepluslive.user.repository.LeJiaUserRepository;
import com.jifenke.lepluslive.user.repository.WeiXinUserRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

/**
* Created by wcg on 16/4/15.
*/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest
@ActiveProfiles({Constants.SPRING_PROFILE_DEVELOPMENT})
public class ttt {


  @Inject
  private WeiXinUserRepository weiXinUserRepository;

  @Inject
  private LeJiaUserRepository leJiaUserRepository;

  @Inject
  private ScoreARepository scoreARepository;

  @Inject
  private MerchantRepository merchantRepository;

  @Inject
  private MerchantService merchantService;

  @Inject
  private BarcodeService barcodeService;

  private String barCodeRootUrl = "http://lepluslive-barcode.oss-cn-beijing.aliyuncs.com";

  @Inject
  private FileImageService fileImageService;

  @Inject
  private OffLineOrderService offLineOrderService;


  @Inject
  private FinanicalStatisticService finanicalStatisticService;


  @Inject
  private ScoreAAccountService scoreAAccountService;

  @Inject
  private ScoreAAccountRepository scoreAAccountRepository;
  //添加所有红包账户
  @Test
  public void qqqq() {
    scoreAAccountService.addAllScoreAAccount();
  }


 

  @Test
  public void tttt() {
   Date start = new Date();
    for(int i =0;i<=10000;i++){
      merchantService.findMerchantByMerchantSid("9103713");
    }
    Date end =  new Date();
    System.out.println(end.getTime()-start.getTime());

  }
  @Test
  public  void lll() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
    try {
      URL url = new URL("http://60.205.14.180:9001/HttpSmsMt");

      // 将url 以 open方法返回的urlConnection  连接强转为HttpURLConnection连接  (标识一个url所引用的远程对象连接)
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();// 此时cnnection只是为一个连接对象,待连接中

      // 设置连接输出流为true,默认false (post 请求是以流的方式隐式的传递参数)
      connection.setDoOutput(true);

      // 设置连接输入流为true
      connection.setDoInput(true);

      // 设置请求方式为post
      connection.setRequestMethod("POST");

      // post请求缓存设为false
      connection.setUseCaches(false);

      // 设置该HttpURLConnection实例是否自动执行重定向
      connection.setInstanceFollowRedirects(true);

      // 设置请求头里面的各个属性 (以下为设置内容的类型,设置为经过urlEncoded编码过的from参数)
      // application/x-javascript text/xml->xml数据 application/x-javascript->json对象 application/x-www-form-urlencoded->表单数据
      // ;charset=utf-8 必须要，不然妙兜那边会出现乱码【★★★★★】
      connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

      // 建立连接 (请求未开始,直到connection.getInputStream()方法调用时才发起,以上各个参数设置需在此方法之前进行)
      connection.connect();

      // 创建输入输出流,用于往连接里面输出携带的参数,(输出内容为?后面的内容)
      DataOutputStream dataout = new DataOutputStream(connection.getOutputStream());
      String mttime00=sdf.format(new Date());
      String name = "name="+ URLEncoder.encode("jfk-yx", "utf-8");// 已修改【改为错误数据，以免信息泄露】
      String password = "&pwd="+ URLEncoder.encode(
          MD5Util.MD5Encode("d41d8cd98f00b204e9800998ecf8427e" + mttime00, "UTF-8"), "utf-8");              // 已修改【改为错误数据，以免信息泄露】
      String phone = "&phone="+ URLEncoder.encode("18944639467", "utf-8");
      String content = "&content="+ URLEncoder.encode("第一次调接口好紧张", "utf-8");
      String mttime = "&mttime="+ URLEncoder.encode(mttime00, "utf-8");
      String rpttype = "&rpttype="+ URLEncoder.encode("1", "utf-8");

      // 格式 parm = aaa=111&bbb=222&ccc=333&ddd=444
      String parm = name+ password+ phone+ content+ mttime+ rpttype;

      // 将参数输出到连接
      dataout.writeBytes(parm);

      // 输出完成后刷新并关闭流
      dataout.flush();
      dataout.close(); // 重要且易忽略步骤 (关闭流,切记!)

//            System.out.println(connection.getResponseCode());

      // 连接发起请求,处理服务器响应  (从连接获取到输入流并包装为bufferedReader)
      BufferedReader
          bf = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
      String line;
      StringBuilder sb = new StringBuilder(); // 用来存储响应数据

      // 循环读取流,若不到结尾处
      while ((line = bf.readLine()) != null) {
//                sb.append(bf.readLine());
        sb.append(line).append(System.getProperty("line.separator"));
      }
      bf.close();    // 重要且易忽略步骤 (关闭流,切记!)
      connection.disconnect(); // 销毁连接
      System.out.println(sb.toString());

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

////  public static void main(String[] args) {
////    int x[][] = new int[9][9];
////    for(int i=0;i<9;i++){
////      for(int y=0;y<9;y++){
////        x[i][y]=new Random().nextInt(2);
////      }
////    }
////    Scanner input = new Scanner(System.in);
////    int a = input.nextInt();
////    int b = input.nextInt();
////    int n = input.nextInt();
////
////    for(int z=1;z<n;z++){
////      int m = x[a][b];
////      int a1 = x[a-1][b];
////      int a2 = x[a+1][b];
////      int a3 = x[a][b+1];
////      int a4 = x[a][b-1];
////
////
////
////    }
//
//
//
//  }


}
