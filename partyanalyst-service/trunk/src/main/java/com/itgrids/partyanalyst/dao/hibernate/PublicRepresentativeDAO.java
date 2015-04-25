package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPublicRepresentativeDAO;
import com.itgrids.partyanalyst.model.PublicRepresentative;

public class PublicRepresentativeDAO extends GenericDaoHibernate<PublicRepresentative, Long> implements IPublicRepresentativeDAO{

	public PublicRepresentativeDAO() {
		super(PublicRepresentative.class);
	}

	public List<Long> getRepresentativesByPositions(Long representativeLevelId,List<Long> locationValuesList,Long positionId)
	{
		StringBuilder queryStr = new StringBuilder();
		
		if(locationValuesList != null && locationValuesList.size()>0)
		{
			queryStr.append(" select distinct model.candidateId from PublicRepresentative model where model.levelValue in (:locationValuesList) and model.publicRepresentativeTypeId =:positionId ");
			if(representativeLevelId != null && representativeLevelId.longValue() != 0L)
			{
				queryStr.append(" and model.levelId = :representativeLevelId order by model.candidateId ");
			}
			Query query = getSession().createQuery(queryStr.toString());
			query.setParameter("positionId", positionId);
			query.setParameterList("locationValuesList", locationValuesList);
			if(representativeLevelId != null && representativeLevelId.longValue() != 0L)
			{
				query.setParameter("representativeLevelId", representativeLevelId);
			}
			return query.list();
		}
		else
			return null;
	}
	
	public List<Object[]> getCandidateInfoByCandidateIds(List<Long> candidateIdsList)
	{
		StringBuilder queryStr = new StringBuilder();
		//queryStr.append(" select distinct model.candidate, model.publicRepresentativeType.type, model.levelValue ");
		//queryStr.append("  from PublicRepresentative model where model.candidateId in (:candidateIdsList) order by model.candidate.lastname " );

		queryStr.append(" select distinct model.candidate.candidateId, model.candidate.lastname, model.candidate.mobile, model.candidate.gender," +
				"   model.publicRepresentativeType.type,model.levelValue ");
		queryStr.append("  from PublicRepresentative model where model.candidateId in (:candidateIdsList) order by model.candidate.lastname " );
		
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("candidateIdsList", candidateIdsList);
		
		return query.list();
	}
}
