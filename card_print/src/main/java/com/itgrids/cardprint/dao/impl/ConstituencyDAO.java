package com.itgrids.cardprint.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.cardprint.dao.IConstituencyDAO;
import com.itgrids.cardprint.model.Constituency;

public class ConstituencyDAO extends GenericDaoHibernate<Constituency, Long> implements IConstituencyDAO {

	public ConstituencyDAO(){
		super(Constituency.class);
	}
	
	public List<Object[]> getAllAssemblyConstituencies() {
		
		Query query = getSession().createQuery("" +
		" select model.constituencyId , model.name from Constituency model " +
		" where  model.electionScope.electionType.electionTypeId = 2 and model.deformDate is null " +
		" order by model.name");
		
		return query.list();
	}
}
