package com.itgrids.cardprint.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.cardprint.dao.IStateDAO;
import com.itgrids.cardprint.model.State;

public class StateDAO  extends GenericDaoHibernate<State, Long> implements IStateDAO {

	public StateDAO(){
		super(State.class);
	}

}
