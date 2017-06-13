package com.itgrids.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IStateDAO;
import com.itgrids.model.State;

@Repository
public class StateDAO extends GenericDaoHibernate<State, Long> implements IStateDAO {

	@Autowired
	SessionFactory sessionFactory; 

	public StateDAO() {
		super(State.class);
   }
	
}
