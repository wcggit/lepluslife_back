package com.jifenke.lepluslive.scoreAAccount.controller;

import com.jifenke.lepluslive.global.util.LejiaResult;
import com.jifenke.lepluslive.global.util.MvUtil;
import com.jifenke.lepluslive.scoreAAccount.controller.view.ScoreAAccountViewExcel;
import com.jifenke.lepluslive.scoreAAccount.domain.criteria.ScoreAAccountCriteria;
import com.jifenke.lepluslive.scoreAAccount.service.ScoreAAccountService;

import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by lss on 2016/9/12.
 */
@RestController
@RequestMapping("/manage")
public class ScoreAAccountController {
  @Inject
  private ScoreAAccountService scoreAAccountService;

  @Inject
  private ScoreAAccountViewExcel scoreAAccountViewExcel;

  @RequestMapping(value = "/scoreAAccountPage", method = RequestMethod.GET)
  public ModelAndView scoreAAccountPage(Model model) {

    Long presentHoldScorea=scoreAAccountService.findPresentHoldScorea();
    Long issueScorea=scoreAAccountService.findIssueScorea();
    Long useScorea=scoreAAccountService.findUseScorea();
    Long ljCommission=scoreAAccountService.findLjCommission();
    Long shareMoney=scoreAAccountService.findShareMoney();
    model.addAttribute("presentHoldScorea",presentHoldScorea);
    model.addAttribute("issueScorea",issueScorea);
    model.addAttribute("useScorea",useScorea);
    model.addAttribute("ljCommission",ljCommission);
    model.addAttribute("shareMoney",shareMoney);

    return MvUtil.go("/scoreAAccount/scoreAAccount");
  }





  @RequestMapping(value = "/scoreAAccount", method = RequestMethod.POST)
     public
     @ResponseBody
  LejiaResult getscoreAAccount(@RequestBody ScoreAAccountCriteria scoreAAccountCriteria) {
    Page page = scoreAAccountService.findScoreAAccountByPage(scoreAAccountCriteria, 10);
    if (scoreAAccountCriteria.getOffset() == null) {
      scoreAAccountCriteria.setOffset(1);
    }
    return LejiaResult.ok(page);
  }


  @RequestMapping(value = "/scoreAAccount/export", method = RequestMethod.POST)
  public ModelAndView exporeExcel(ScoreAAccountCriteria scoreAAccountCriteria) {
    if (scoreAAccountCriteria.getOffset() == null) {
      scoreAAccountCriteria.setOffset(1);
    }
    Page page = scoreAAccountService.findScoreAAccountByPage(scoreAAccountCriteria, 10000);
    Map map = new HashMap();
    map.put("scoreAAccountList", page.getContent());
    return new ModelAndView(scoreAAccountViewExcel, map);
  }


}
