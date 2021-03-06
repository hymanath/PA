package com.itgrids.dao.impl;

import java.util.List;
import java.util.Map;

import org.appfuse.dao.SearchException;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmStatusDAO;
import com.itgrids.model.PmStatus;

@Repository
public class PmStatusDAO extends GenericDaoHibernate<PmStatus, Long> implements IPmStatusDAO {
	@Autowired
	SessionFactory sessionFactory;
	public PmStatusDAO() {
		super(PmStatus.class);
		// TODO Auto-generated constructor stub
	}

	public List<Object[]> getPmStatusList(List<Long> statId){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct model.pmStatusId,model.status from PmStatus model where model.isDeleted = 'N' ");
		if(statId != null && statId.size()>0){
			sb.append(" and  model.pmStatusId in (:statId) ");
		}
		sb.append(" order by model.orderNo ");
		Query qry = getSession().createQuery(sb.toString());
		if(statId != null && statId.size() >0){
			qry.setParameterList("statId", statId);
		}
		return qry.list();
	}

}
