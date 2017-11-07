package com.feng.competition.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.feng.competition.bean.AjaxJson;
import com.feng.competition.bean.Competition;
import com.feng.competition.bean.Page;
import com.feng.competition.bean.Shou;
import com.feng.competition.service.CompetitionService;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/competition")
public class CompetitionController {
  @Autowired
  private CompetitionService competitionService;

  @RequestMapping(params = "gets", method = RequestMethod.GET)
  @ResponseBody
  public String getCompetition(HttpServletRequest request, Page page) {
    AjaxJson j = new AjaxJson();
    j.setObj(competitionService.getAllCompetitions(page));
    List<Competition> list = (List<Competition>) j.getObj();

    String paramStr = JSONObject.fromObject(j).toString();
    return paramStr;
  }

  @RequestMapping(params = "image", method = RequestMethod.GET)
  @ResponseBody
  public void getImage(HttpServletRequest request, HttpServletResponse response, String name)
      throws IOException {
    String url = "c:/code/fengge/img/" + name + ".jpg";
    FileInputStream inputStream = new FileInputStream(url);
    int i = inputStream.available();
    // byte数组用于存放图片字节数据
    byte[] buff = new byte[i];
    inputStream.read(buff);
    // 记得关闭输入流
    inputStream.close();
    // 设置发送到客户端的响应内容类型
    response.setContentType("image/*");
    OutputStream out = response.getOutputStream();
    out.write(buff);
    // 关闭响应输出流
    out.close();
  }

  @RequestMapping(params = "shou", method = RequestMethod.GET)
  @ResponseBody
  public void shou(HttpServletRequest request, HttpServletResponse response, Shou shou)
      throws IOException {
    OutputStream out = null;
    try {
      out = response.getOutputStream();
      competitionService.saveShou(shou);
      out.write(0 + '1');
    } catch (Exception e) {
      out.write(0 + '0');
    }
    out.close();
  }

  @RequestMapping(params = "getshou", method = RequestMethod.GET)
  @ResponseBody
  public String getShou(HttpServletRequest request, HttpServletResponse response)
      throws IOException {
    AjaxJson j = new AjaxJson();
    j.setObj(competitionService.getAllShous());
    List<Shou> list = (List<Shou>) j.getObj();
    String str = "(";
    for (Shou s : list) {
      str += s.getCid() + ",";
    }
    if (list.size() == 0) {
      str = "";
      return null;
    } else {
      str = str.substring(0, str.length() - 1) + ")";
    }

    String paramStr = JSONObject.fromObject(j).toString();
    return paramStr;
  }
}
