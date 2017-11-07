package com.feng.competition.service.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.feng.competition.bean.Competition;
import com.feng.competition.bean.Page;
import com.feng.competition.bean.Shou;
import com.feng.competition.service.CompetitionService;
import com.feng.util.HibernateUtil;

@Service("wxAccountMgrService")
@Transactional
public class CompetitionServiceImpl implements CompetitionService {
  @Override
  public List<Competition> getAllCompetitions(Page page) {

    Session session = HibernateUtil.currentSession();

    String hql = " from Competition where 1=1";
    if (page.getCity() != null && !page.getCity().equals("全国")) {
      hql += " and city='" + page.getCity() + "'";
    }
    if (page.getKeyWord() != null) {
      hql += " and name like '%" + page.getKeyWord() + "%'";
    }
    if (page.getIndustry() != null && !page.getIndustry().equals("全部")) {
      hql += " and industry='" + page.getIndustry() + "'";
    }
    System.out.println(hql);
    Query query = session.createQuery(hql);
    if (page.getSize() <= 0) {
      page.setSize(5);
    }
    if (page.getPage() < 0) {
      page.setPage(0);
    }
    query.setFirstResult(page.getPage() * page.getSize());
    query.setMaxResults(page.getSize());
    List<Competition> list = query.list();

    HibernateUtil.closeSession();

    return list;
  }

  @Override
  public void saveShou(Shou shou) {
    Session s = null;
    Transaction tx = null;

    try {
      s = HibernateUtil.currentSession();
      tx = s.beginTransaction();
      s.save(shou);
      tx.commit();
    } finally {
      if (s != null) {
        s.close();
      }
    }

  }

  @Override
  public List<Shou> getAllShous() {
    Session session = HibernateUtil.currentSession();

    String hql = " from Shou";
    Query query = session.createQuery(hql);
    List<Shou> list = query.list();

    HibernateUtil.closeSession();
    return list;
  }
}
