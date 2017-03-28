package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICriticalPanchayatsDAO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;
import com.itgrids.partyanalyst.model.CriticalPanchayats;

public class CriticalPanchayatsDAO extends GenericDaoHibernate<CriticalPanchayats, Long> implements ICriticalPanchayatsDAO{

	public CriticalPanchayatsDAO() {
		super(CriticalPanchayats.class);
		// TODO Auto-generated constructor stub
	}

	
	public List<Object[]> getConstituenciesFromCriticalPanchayats()
	{
		Query query = getSession().createQuery("select distinct model.constituency.constituencyId,model.constituency.name from CriticalPanchayats model order by model.constituency.name");
		return query.list();
		
	}
	
	public List<Object[]> getCriticalPanchayatBoothHnos(VoterVO voterVo,Integer startIndex,Integer maxIndex)
	{
		StringBuilder str =new StringBuilder();
		
		str.append("select bpv.booth.boothId,bpv.voter.houseNo,count(bpv.voter.voterId) from BoothPublicationVoter bpv,CriticalPanchayats cp where cp.constituency.constituencyId = bpv.booth.constituency.constituencyId and cp.constituency.constituencyId = :constituencyId and " +
				"bpv.booth.publicationDate.publicationDateId = :publicationId and ");

		str.append("cp.year = :year and cp.type = :type");
		str.append(" group by bpv.booth.boothId,bpv.voter.houseNo ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("constituencyId", voterVo.getConstituencyId());
		query.setParameter("publicationId", voterVo.getPublicationDateId());
		query.setParameter("type", voterVo.getType());
		query.setParameter("year", voterVo.getYear());
		if(startIndex!=null)
		 query.setFirstResult(startIndex);
		if(maxIndex != null)
		 query.setMaxResults(maxIndex);
		return query.list();	
	}
	
}
