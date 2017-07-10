package com.rwss.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rwss.dao.IRwsPhysicalchemtestDAO;
import com.rwss.dto.InputVO;
import com.rwss.model.RwsPhysicalchemtest;

@Repository
public class RwsPhysicalchemtestDAO extends GenericDaoHibernate<RwsPhysicalchemtest,Long> implements IRwsPhysicalchemtestDAO {


	@Autowired
	SessionFactory sessionFactory;
	
	public RwsPhysicalchemtestDAO() {
		super(RwsPhysicalchemtest.class);

	}
	public Long getPhysicalchemtestCountYearWise(InputVO inputVO) {
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select sum(model.phyChemTest) from RwsPhysicalchemtest model ");
		if (inputVO.getYear() != null && inputVO.getYear().trim().length() > 0) {
			queryStr.append(" where model.year=:year");
		}
		Query query = getSession().createQuery(queryStr.toString());
		if (inputVO.getYear() != null && inputVO.getYear().trim().length() > 0) {
			query.setParameter("year", Long.valueOf(inputVO.getYear()));
		}
		return (Long) query.uniqueResult();
	}
}
