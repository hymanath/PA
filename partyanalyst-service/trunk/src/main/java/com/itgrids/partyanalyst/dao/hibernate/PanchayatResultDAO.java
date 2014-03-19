package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPanchayatResultDAO;
import com.itgrids.partyanalyst.model.PanchayatResult;

public class PanchayatResultDAO extends GenericDaoHibernate<PanchayatResult,Long> implements IPanchayatResultDAO{
	
	public PanchayatResultDAO() {
		super(PanchayatResult.class);
	}
	
	public List<Object[]> getPartyWiseWonInPanchayts(List<Long> panchayatIds){
		Query query=getSession().createQuery("select model.party.partyId,model.party.shortName,model.panchayat.panchayatId,model.panchayat.panchayatName from PanchayatResult model " +
				"where model.panchayat.panchayatId in(:panchayatIds)");
		
		query.setParameterList("panchayatIds", panchayatIds);
		return query.list();
	}

}
