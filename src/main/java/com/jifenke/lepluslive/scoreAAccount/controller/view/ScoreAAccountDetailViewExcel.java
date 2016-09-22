package com.jifenke.lepluslive.scoreAAccount.controller.view;

import com.jifenke.lepluslive.scoreAAccount.domain.entities.ScoreAAccountDetail;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by lss on 2016/9/18.
 */
@Configuration
public class ScoreAAccountDetailViewExcel extends AbstractExcelView {
  @Override
  protected void buildExcelDocument(Map<String, Object> map, HSSFWorkbook hssfWorkbook,
                                    HttpServletRequest request,
                                    HttpServletResponse response) throws Exception {
    HSSFSheet sheet = hssfWorkbook.createSheet("list");
    setExcelHeader(sheet);
    List<ScoreAAccountDetail> scoreAAccountList = (List<ScoreAAccountDetail>) map.get("scoreAAccountDetailList");
    setExcelRows(sheet, scoreAAccountList);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String filename = sdf.format(new Date()) + ".xls";//设置下载时客户端Excel的名称
    response.setContentType("application/vnd.ms-excel");
    response.setHeader("Content-disposition", "attachment;filename=" + filename);
    OutputStream ouputStream = response.getOutputStream();
    hssfWorkbook.write(ouputStream);
    ouputStream.flush();
    ouputStream.close();
  }
  public void setExcelHeader(HSSFSheet excelSheet) {
    HSSFRow excelHeader = excelSheet.createRow(0);
    excelHeader.createCell(0).setCellValue("红包变更时间");
    excelHeader.createCell(1).setCellValue("变更项目(订单 会员 编号)");
    excelHeader.createCell(2).setCellValue("消费者使用红包");
    excelHeader.createCell(3).setCellValue("积分客发放红包");
    excelHeader.createCell(4).setCellValue("佣金收入");
    excelHeader.createCell(5).setCellValue("分润后积分客收入");
  }
  public void setExcelRows(HSSFSheet excelSheet, List<ScoreAAccountDetail> scoreAAccountDetailList) {
    int record = 1;
    for (ScoreAAccountDetail scoreAAccountDetail : scoreAAccountDetailList) {
      HSSFRow excelRow = excelSheet.createRow(record++);
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
      excelRow.createCell(0).setCellValue(sdf.format(scoreAAccountDetail.getChangeDate()));
      if(scoreAAccountDetail.getChangeProject()!=null){
        if (scoreAAccountDetail.getChangeProject()==0){
          excelRow.createCell(1).setCellValue( "关注送红包" + "(" + scoreAAccountDetail.getSerialNumber()  + ")");
        }
        if (scoreAAccountDetail.getChangeProject()==1){
          excelRow.createCell(1).setCellValue( "线上返还" + "(" + scoreAAccountDetail.getSerialNumber()  + ")");
        }
        if (scoreAAccountDetail.getChangeProject()==2){
          excelRow.createCell(1).setCellValue( "线上消费" + "(" + scoreAAccountDetail.getSerialNumber()  + ")");
        }
        if (scoreAAccountDetail.getChangeProject()==3){
          excelRow.createCell(1).setCellValue( "线下消费" + "(" + scoreAAccountDetail.getSerialNumber()  + ")");
        }
        if (scoreAAccountDetail.getChangeProject()==4){
          excelRow.createCell(1).setCellValue( "线下返还" + "(" + scoreAAccountDetail.getSerialNumber()  + ")");
        }
        if (scoreAAccountDetail.getChangeProject()==5){
          excelRow.createCell(1).setCellValue( "活动返还" + "(" + scoreAAccountDetail.getSerialNumber()  + ")");
        }
        if (scoreAAccountDetail.getChangeProject()==6){
          excelRow.createCell(1).setCellValue( "运动" + "(" + scoreAAccountDetail.getSerialNumber()  + ")");
        }
        if (scoreAAccountDetail.getChangeProject()==7){
          excelRow.createCell(1).setCellValue( "摇一摇" + "(" + scoreAAccountDetail.getSerialNumber()  + ")");
        }
        if (scoreAAccountDetail.getChangeProject()==8){
          excelRow.createCell(1).setCellValue( "APP分享" + "(" + scoreAAccountDetail.getSerialNumber()  + ")");
        }
        if (scoreAAccountDetail.getChangeProject()==9){
          excelRow.createCell(1).setCellValue( "线下支付完成页注册会员" + "(" + scoreAAccountDetail.getSerialNumber()  + ")");
        }
      }else{
        excelRow.createCell(1).setCellValue( scoreAAccountDetail.getOperate() + "(" + scoreAAccountDetail.getSerialNumber()  + ")");
      }
      if(scoreAAccountDetail.getUseScoreA()!=null){
        excelRow.createCell(2).setCellValue("￥" + scoreAAccountDetail.getUseScoreA() / 100);
      }else{
        excelRow.createCell(2).setCellValue("￥" + 0);
      }
      if(scoreAAccountDetail.getIssuedScoreA()!=null){
        excelRow.createCell(3).setCellValue("￥" + scoreAAccountDetail.getIssuedScoreA() / 100);
      }else{
        excelRow.createCell(3).setCellValue("￥" + 0);
      }
      if(scoreAAccountDetail.getCommissionIncome()!=null){
        excelRow.createCell(4).setCellValue("￥" + scoreAAccountDetail.getCommissionIncome() / 100);
      }else{
        excelRow.createCell(4).setCellValue("￥" + 0);
      }
      if(scoreAAccountDetail.getJfkShare()!=null){
        excelRow.createCell(5).setCellValue("￥" + scoreAAccountDetail.getJfkShare() / 100);
      }else{
        excelRow.createCell(5).setCellValue("￥" + 0);
      }
    }
  }

}
