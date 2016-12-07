package com.itgrids.cardprint.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.cardprint.dao.IConstituencyDAO;
import com.itgrids.cardprint.model.Constituency;

public class ConstituencyDAO extends GenericDaoHibernate<Constituency, Long> implements IConstituencyDAO {

	public ConstituencyDAO(){
		super(Constituency.class);
	}

}
