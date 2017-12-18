package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.INregaComponentCommentsHistoryDAO;
import com.itgrids.model.NregaComponentCommentsHistory;

@Repository
public class NregaComponentCommentsHistoryDAO extends GenericDaoHibernate<NregaComponentCommentsHistory, Long> implements INregaComponentCommentsHistoryDAO{

	public NregaComponentCommentsHistoryDAO() {
		super(NregaComponentCommentsHistory.class);
		
	}

}
