package com.itgrids.cardprint.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.cardprint.dao.IConstituencyPrintStatusDAO;
import com.itgrids.cardprint.model.ConstituencyPrintStatus;

public class ConstituencyPrintStatusDAO extends GenericDaoHibernate<ConstituencyPrintStatus, Long> implements IConstituencyPrintStatusDAO {

	public ConstituencyPrintStatusDAO(){
		super(ConstituencyPrintStatus.class);
	}
    
	public List<Long> getConstituencyPrintStatus(Long constituencyId){
		Query query = getSession().createQuery("" +
		" select model.constituencyPrintStatusId " +
		" from   ConstituencyPrintStatus model " +
		" where  model.constituencyId = :constituencyId ");
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
}
