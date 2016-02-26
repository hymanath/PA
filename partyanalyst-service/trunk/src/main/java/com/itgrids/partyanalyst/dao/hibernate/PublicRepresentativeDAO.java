package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPublicRepresentativeDAO;
import com.itgrids.partyanalyst.model.PublicRepresentative;
import com.itgrids.partyanalyst.utils.IConstants;

public class PublicRepresentativeDAO extends GenericDaoHibernate<PublicRepresentative, Long> implements IPublicRepresentativeDAO{

	public PublicRepresentativeDAO() {
		super(PublicRepresentative.class);
	}

	public List<Integer> getRepresentativesByPositions(Long representativeLevelId,List<Long> locationValuesList,Long positionId)
	{
		StringBuilder queryStr = new StringBuilder();
		
		if(locationValuesList != null)
		{
			/*queryStr.append(" select distinct model.candidateId from PublicRepresentative model where model.levelValue in (:locationValuesList) and model.publicRepresentativeTypeId =:positionId ");
			if(representativeLevelId != null && representativeLevelId.longValue() != 0L)
			{
				queryStr.append(" and model.levelId = :representativeLevelId order by model.candidateId ");
			}*/
			if(locationValuesList != null && locationValuesList.size()>0)
			{
				queryStr.append(" select distinct candidate_id from public_representative model where model.representative_level_value in (:locationValuesList) and " +
						" model.public_representative_type_id =:positionId  ");
			}
			else
			{
				queryStr.append(" select distinct model.representative_level_value from public_representative model where  " +
						" model.public_representative_type_id in ("+IConstants.MPTC_ELCTION_TYPE_ID+","+IConstants.ZPTC_ELCTION_TYPE_ID+") ");
			}
			
			if(representativeLevelId != null && representativeLevelId.longValue() != 0L)
			{
				queryStr.append(" and model.representative_level_id =:representativeLevelId order by model.candidate_id ");
			}
			
			Query query = getSession().createSQLQuery(queryStr.toString());
			
			if(locationValuesList != null && locationValuesList.size()>0)
			{
				query.setParameter("positionId", positionId);
				query.setParameterList("locationValuesList", locationValuesList);
			}
			if(representativeLevelId != null && representativeLevelId.longValue() != 0L)
			{
				query.setParameter("representativeLevelId", representativeLevelId);
			}
			return query.list();
		}
		else
			return null;
	}
	
	/*public List<Long> getRepresentativesByPositions(Long representativeLevelId,List<Long> locationValuesList,Long positionId)
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
	*/
	public List<Object[]> getCandidateInfoByCandidateIds(List<Long> candidateIdsList)
	{
		StringBuilder queryStr = new StringBuilder();
		//queryStr.append(" select distinct model.candidate, model.publicRepresentativeType.type, model.levelValue ");
		//queryStr.append("  from PublicRepresentative model where model.candidateId in (:candidateIdsList) order by model.candidate.lastname " );

		queryStr.append(" select distinct model.candidate.candidateId, model.candidate.lastname, model.candidate.mobile, model.candidate.gender," +
				"   model.publicRepresentativeType.type,model.levelValue,model.levelId ");
		queryStr.append("  from PublicRepresentative model where model.candidateId in (:candidateIdsList) order by model.candidate.lastname " );
		
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameterList("candidateIdsList", candidateIdsList);
		
		return query.list();
	}
	
	public List<Object[]> getCandidateDetailsByCandidateId(List<Long> candidateIds){
		
		Query query = getSession().createQuery(" select model1.tdpCadreId," +
							" model.publicRepresentativeType.publicRepresentativeTypeId," +
							" model.publicRepresentativeType.type," +
							" model.representativeLevel.representativeLevelId," +
							" model.representativeLevel.representativeLevel," +
							" model.levelValue" +
							" from PublicRepresentative model,TdpCadreCandidate model1" +
							" where model.candidateId = model1.candidateId" +
							" and model1.tdpCadreId in (:candidateIds) )  and  " +
							" model1.tdpCadre.isDeleted='N' and  model1.tdpCadre.enrollmentYear ="+IConstants.CADRE_ENROLLMENT_NUMBER+"");
		query.setParameterList("candidateIds", candidateIds);
		
		return query.list();
	}
}
