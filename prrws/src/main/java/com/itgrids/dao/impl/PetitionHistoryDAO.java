package com.itgrids.dao.impl;

import java.util.List;
import java.util.Map;

import org.appfuse.dao.SearchException;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPetitionHistoryDAO;
import com.itgrids.model.PetitionHistory;

@Repository
public class PetitionHistoryDAO extends GenericDaoHibernate<PetitionHistory, Long> implements IPetitionHistoryDAO {
	@Autowired
	SessionFactory sessionFactory;
	public PetitionHistoryDAO() {
		super(PetitionHistory.class);
	}

	

}
