package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IStrategyAssumptionsDAO;
import com.itgrids.partyanalyst.model.StrategyAssumptions;

public class StrategyAssumptionsDAO  extends GenericDaoHibernate<StrategyAssumptions,Long> implements IStrategyAssumptionsDAO {

	public StrategyAssumptionsDAO(){
		super(StrategyAssumptions.class);
	}
	
	public List<StrategyAssumptions> getDataByConstituencyId(Long constituencyId){
		Query  query = getSession().createQuery("select model from StrategyAssumptions model where model.constituency.constituencyId =:constituencyId ");
		    query.setParameter("constituencyId", constituencyId);
		
		return query.list();
	}
}
