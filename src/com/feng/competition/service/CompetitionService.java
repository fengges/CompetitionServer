package com.feng.competition.service;

import java.util.List;

import com.feng.competition.bean.Competition;
import com.feng.competition.bean.Page;
import com.feng.competition.bean.Shou;

public interface CompetitionService {

  public List<Competition> getAllCompetitions(Page page);

  public void saveShou(Shou shou);

  public List<Shou> getAllShous();

  public List<Competition> getCompetitionsById(String id);
}
