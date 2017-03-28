package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPublicRepresentativeDAO;
import com.itgrids.partyanalyst.model.PublicRepresentative;
import com.itgrids.partyanalyst.model.UserAddress;
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
				queryStr.append(" select distinct model.candidate_id from public_representative model , tdp_cadre_candidate  model2, tdp_cadre model3 where " +
						" model.candidate_id = model2.candidate_id and model2.tdp_cadre_id = model3.tdp_cadre_id and model3.is_deleted ='N' and  " +
						"  model.representative_level_value in (:locationValuesList) and " +
						" model.public_representative_type_id =:positionId  ");
			}
			else
			{
				queryStr.append(" select distinct model.representative_level_value from public_representative model, tdp_cadre_candidate  model2, tdp_cadre model3 where " +
						" model.candidate_id = model2.candidate_id and model2.tdp_cadre_id = model3.tdp_cadre_id and model3.is_deleted ='N' and  " +
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
	
	public List<Long> getCandidateCadreDetils(Long candidateId){
		Query query=getSession().createQuery("select model.addressId from PublicRepresentative model where model.candidateId =:candidateId");
		query.setParameter("candidateId", candidateId);
		return query.list(); 
	}
	
	public List<UserAddress> getUserAddressForCadre(Long tdpCadreId)
	{
		Query query = getSession().createQuery("select distinct model.userAddress from PublicRepresentative model,TdpCadreCandidate model1 where model.candidate.candidateId = model1.candidate.candidateId" +
				" and model1.tdpCadreId = :tdpCadreId and  model1.tdpCadre.isDeleted='N' and  model1.tdpCadre.enrollmentYear ="+IConstants.CADRE_ENROLLMENT_NUMBER+"");
		query.setParameter("tdpCadreId",tdpCadreId);
		return query.list();
		
		
	}
	
	public List<Object[]> getUserAddressForCadre1(List<Long> tdpCadreIds)
	{
		StringBuilder str = new StringBuilder();
		
		str.append("select ST.state_id as stateId,ST.state_name as stateName, DT.district_id as districtId,DT.district_name as districtName" +
				",C.constituency_id as constituencyId,C.name as constName," +
				"PC.constituency_id as pcConId,PC.name as pcName,T.tehsil_id as tehsilId,T.tehsil_name as tehsilName," +
				"LEB.local_election_body_id as localBodyId,LEB.name as localBodyName,WC.constituency_id as wardId" +
				",WC.name as wardName,TCC.tdp_cadre_id as tdpCadreId" +
				" from public_representative PR " +
				"join tdp_cadre_candidate TCC on PR.candidate_id = TCC.candidate_id " +
				"join user_address UA on PR.address_id = UA.user_address_id " +
				"left join state ST on UA.state_id = ST.state_id " +
				"left join district DT on UA.district_id = DT.district_id " +
				"left join constituency C on UA.constituency_id = C.constituency_id " +
				"left join tehsil T on UA.tehsil_id = T.tehsil_id " +
				"left join constituency PC on UA.parliament_constituency_id = PC.constituency_id " +
				"left join local_election_body LEB on UA.local_election_body = LEB.local_election_body_id " +
				"left join constituency WC on UA.ward = WC.constituency_id " +
				" where PR.candidate_id = TCC.candidate_id " +
				" AND PR.address_id = UA.user_address_id " +
				"  " );
		if(tdpCadreIds !=null && tdpCadreIds.size()>0){
			str.append(" AND TCC.tdp_cadre_id in (:tdpCadreIds) ");
		}
		
		Query query = getSession().createSQLQuery(str.toString())
				 .addScalar("stateId",Hibernate.LONG)
				 .addScalar("stateName",Hibernate.STRING)
				 .addScalar("districtId",Hibernate.LONG)
				 .addScalar("districtName",Hibernate.STRING)
				 .addScalar("constituencyId",Hibernate.LONG)
				 .addScalar("constName",Hibernate.STRING)
				 .addScalar("pcConId",Hibernate.LONG)
				 .addScalar("pcName",Hibernate.STRING)
				 .addScalar("tehsilId",Hibernate.LONG)
				 .addScalar("tehsilName",Hibernate.STRING)
				 .addScalar("localBodyId",Hibernate.LONG)
				 .addScalar("localBodyName",Hibernate.STRING)
				 .addScalar("wardId",Hibernate.LONG)
				 .addScalar("wardName",Hibernate.STRING)
				 .addScalar("tdpCadreId",Hibernate.LONG);
		
		query.setParameterList("tdpCadreIds",tdpCadreIds);
		
		/*Query query = getSession().createQuery("select distinct model.userAddress " +
				"from PublicRepresentative model,TdpCadreCandidate model1 where model.candidate.candidateId = model1.candidate.candidateId" +
				" and model1.tdpCadreId = :tdpCadreId and  model1.tdpCadre.isDeleted='N' and  model1.tdpCadre.enrollmentYear ="+IConstants.CADRE_ENROLLMENT_NUMBER+"");
		query.setParameter("tdpCadreId",tdpCadreId);*/
		return query.list();		
	}
	
	public List<Object[]> getPulicRepresentativeInfoByLocation(Long locationId,String searchType){
		StringBuilder sb = new StringBuilder();
		sb.append("select model1.tdpCadre.tdpCadreId," +
					" model1.tdpCadre.firstname," +
					" model.publicRepresentativeType.type," +
					" model.representativeLevel.representativeLevelId," +
					" model.representativeLevel.representativeLevel," +
					" model1.tdpCadre.mobileNo," +
					" model.levelValue" +
					" from PublicRepresentative model,TdpCadreCandidate model1" +
					" where model.candidate.candidateId = model1.candidate.candidateId");
		if(searchType.equalsIgnoreCase("mandal"))
			sb.append(" and model1.tdpCadre.userAddress.tehsil.tehsilId = :locationId");
		else if(searchType.equalsIgnoreCase("leb"))
			sb.append(" and model1.tdpCadre.userAddress.localElectionBody.localElectionBodyId = :locationId");
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("locationId", locationId);
		return query.list();
	}
	
	public List<Object[]> getPublicRepresentativeLocationDetails(Long tdpCadreId){
		
		Query query = getSession().createQuery("" +
		" select tcc.tdpCadre.tdpCadreId," +//0
		"        district.districtId,district.districtName, " +//2
		"        constituency.constituencyId,constituency.name," +//4
		"        tehsil.tehsilId,tehsil.tehsilName,panchayat.panchayatId,panchayat.panchayatName," +//8
		"        localElectionBody.localElectionBodyId,localElectionBody.name,ward.constituencyId,ward.name," +//12
		"        constituency.areaType," +//13
		"        electionType.electionTypeId,electionType.electionType" +//15
		" from   PublicRepresentative pr,TdpCadreCandidate tcc  " +
		"        join pr.userAddress  ua " +
		"        left join ua.district district" +
		"        left join ua.constituency constituency  " +
		"        left join ua.tehsil tehsil " +
		"        left join ua.panchayat panchayat " +
		"        left join ua.localElectionBody localElectionBody " +
		"        left join ua.ward ward " +
		"        left join localElectionBody.electionType electionType " +
		" where  pr.candidate.candidateId =tcc.candidate.candidateId and " +
		"        tcc.tdpCadre.isDeleted='N' and tcc.tdpCadre.enrollmentYear=:enrollmentYear and tcc.tdpCadre.tdpCadreId=:tdpCadreId");
		query.setParameter("tdpCadreId",tdpCadreId);
		query.setParameter("enrollmentYear", IConstants.CADRE_ENROLLMENT_YEAR);
		return query.list();
	}
	
	
 }
