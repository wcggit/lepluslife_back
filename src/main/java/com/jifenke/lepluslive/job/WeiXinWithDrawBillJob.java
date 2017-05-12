package com.jifenke.lepluslive.job;

import com.jifenke.lepluslive.order.service.OrderService;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.context.ApplicationContext;

/**
 * Created by wcg on 2017/5/11.
 */
public class WeiXinWithDrawBillJob extends BaseJobBean{

  @Override
  protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

    ApplicationContext applicationContext = super.getApplicationContext(context);
    OrderService orderService = applicationContext.getBean("orderService", OrderService.class);
    JobDataMap dataMap = context.getJobDetail().getJobDataMap();
    Long orderId = dataMap.getLong("orderId");

  }

}
