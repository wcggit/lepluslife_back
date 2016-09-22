package com.jifenke.lepluslive.scoreAAccount.controller;

import com.jifenke.lepluslive.global.util.LejiaResult;
import com.jifenke.lepluslive.global.util.MvUtil;
import com.jifenke.lepluslive.scoreAAccount.controller.view.ScoreAAccountDetailViewExcel;
import com.jifenke.lepluslive.scoreAAccount.domain.criteria.ScoreAAccountDetailCriteria;
import com.jifenke.lepluslive.scoreAAccount.service.ScoreAAccountDetailService;

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
 * Created by lss on 2016/9/9.
 */
@RestController
@RequestMapping("/manage")
public class ScoreAAccountDetailController {


  @Inject
  private ScoreAAccountDetailService scoreAAccountDetailService;

  @Inject
  private ScoreAAccountDetailViewExcel scoreAAccountDetailViewExcel;


  @RequestMapping(value = "/serchScoreAAccountDetailPage", method = RequestMethod.GET)
  public ModelAndView serchScoreAAccountDetailPage( Model model,String time) {

    model.addAttribute("time",time);
    return MvUtil.go("/scoreAAccount/scoreAAccountDetail");
  }

  @RequestMapping(value = "/scoreAAccountDetail", method = RequestMethod.POST)
  public
  @ResponseBody
  LejiaResult getscoreAAccountDetail(@RequestBody ScoreAAccountDetailCriteria scoreAAccountDetailCriteria) {
    if (scoreAAccountDetailCriteria.getOffset() == null) {
      scoreAAccountDetailCriteria.setOffset(1);
    }
    Page page = scoreAAccountDetailService.findScoreAAccountDetailByPage(scoreAAccountDetailCriteria, 10);
    return LejiaResult.ok(page);
  }

  @RequestMapping(value = "/scoreAAccountDetail/export", method = RequestMethod.POST)
  public ModelAndView exporeExcel(ScoreAAccountDetailCriteria scoreAAccountDetailCriteria) {
    if (scoreAAccountDetailCriteria.getOffset() == null) {
      scoreAAccountDetailCriteria.setOffset(1);
    }
    Page page = scoreAAccountDetailService.findScoreAAccountDetailByPage(
        scoreAAccountDetailCriteria, 10000);
    Map map = new HashMap();
    map.put("scoreAAccountDetailList", page.getContent());
    return new ModelAndView(scoreAAccountDetailViewExcel, map);
  }
}
