package com.rwss.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rwss.dao.IRwsBacterialTestDAO;
import com.rwss.dto.InputVO;
import com.rwss.model.RwsBacterialTest;

@Repository
public class RwsBacterialTestDAO  extends GenericDaoHibernate<RwsBacterialTest, Long> implements IRwsBacterialTestDAO {

	
	@Autowired
	SessionFactory sessionFactory;
	
	public RwsBacterialTestDAO() {
		super(RwsBacterialTest.class);
	}

	public Long getBacterialTestCountYearWise(InputVO inputVO) {
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select sum(model.bacterial) from RwsBacterialTest model ");
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
