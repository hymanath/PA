package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;


import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAddressDAO;
import com.itgrids.partyanalyst.dao.IPartyTrendsDAO;
import com.itgrids.partyanalyst.model.Address;
import com.itgrids.partyanalyst.model.PartyTrends;

public class PartyTrendsDAO extends GenericDaoHibernate<PartyTrends, Long> implements IPartyTrendsDAO{

	public PartyTrendsDAO() {
		super(PartyTrends.class);
	}
	public List<?> loadConst() {
		
		Query query= getSession().createQuery("select model.constituency.constituencyId,model.constituency.name from   PartyTrends model  group by  model.constituency.constituencyId order by model.constituency.name ");
		return query.list();
	}
	public List<?> loadEntitiesForXl(List<Long> constIds) {
		Query query= getSession().createQuery("select model.constituency.constituencyId,model.constituency.name,model.name,model.pervTrenzWt,model.prpWt,model.youngVotersWt,model.totalWt  from   PartyTrends model  where model.constituency.constituencyId in(:constIds)  group by  model.partyTrendsId  order by model.constituency.name ");
	
		query.setParameterList("constIds", constIds);
		return query.list();
		
	}

	
	
}
