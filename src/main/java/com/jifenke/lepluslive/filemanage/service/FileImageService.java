package com.jifenke.lepluslive.filemanage.service;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;
import com.jifenke.lepluslive.global.config.Constants;
import com.jifenke.lepluslive.order.domain.entities.PosDailyBill;
import com.jifenke.lepluslive.order.service.PosOrderService;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.inject.Inject;

/**
 * Created by wcg on 16/3/10.
 */
@Service
public class FileImageService {

  @Value("${bucket.image}")
  private String imageBucket;

  @Value("${bucket.barcode}")
  private String barCodeBucket;

  private OSSClient ossClient;

  @Value("${bucket.endpoint}")
  private String entryPoint;

  @Value("${bucket.ossAccessId}")
  private String ossAccessId;

  @Value("${bucket.ossAccessSecret}")
  private String ossAccessSecret;

  @Inject
  private PosOrderService posOrderService;

  private synchronized OSSClient getOssClient() {
    if (ossClient != null) {
      return ossClient;
    }
    return new OSSClient(entryPoint, ossAccessId, ossAccessSecret);
  }

  public void saveImage(MultipartFile filedata, String filePath) throws IOException {
    ossClient = getOssClient();
    InputStream is = filedata.getInputStream();

    // 创建上传Object的Metadata
    ObjectMetadata meta = new ObjectMetadata();

    // 必须设置ContentLength
    meta.setContentLength(filedata.getSize());
    PutObjectResult putObjectResult = ossClient.putObject(imageBucket, filePath, is, meta);
  }

  public void SaveBarCode(byte[] bytes, String filePath) {
    InputStream is = new ByteArrayInputStream(bytes);
    ObjectMetadata meta = new ObjectMetadata();
    ossClient = getOssClient();
    // 必须设置ContentLength
    meta.setContentLength(bytes.length);
    PutObjectResult putObjectResult = ossClient.putObject(barCodeBucket, filePath, is, meta);
  }

  @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
  public void saveExcel(MultipartFile file, String name) throws Exception {
    PosDailyBill posDailyBill = new PosDailyBill();
    posDailyBill.setCreateDate(new Date());
    posDailyBill.setUrl(Constants.POS_BILL_URL + name);
    posDailyBill.setFilename(name);
    saveImage(file, name);
    posOrderService.savePosDailyBill(posDailyBill);
  }
}
