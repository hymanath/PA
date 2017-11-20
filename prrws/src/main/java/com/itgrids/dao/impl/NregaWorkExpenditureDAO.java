package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.itgrids.model.NregaWorkExpenditure;

import com.itgrids.dao.INregaWorkExpenditureDAO;

@Repository
public class NregaWorkExpenditureDAO extends GenericDaoHibernate<NregaWorkExpenditure, Long> implements INregaWorkExpenditureDAO{

	public NregaWorkExpenditureDAO() {
		super(NregaWorkExpenditure.class);
		
	}

}
