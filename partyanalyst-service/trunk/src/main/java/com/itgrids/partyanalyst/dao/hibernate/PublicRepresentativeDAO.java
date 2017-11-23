package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	
	public List<Object[]> getPartyLeadersDeatails(Long stateId, List<Long> enrollmentIdsList, Long levelId,List<Long> locationIdsList,List<Long> designationIds1List,int firstIndex,int maxIndex,String fetchTypeStr){
		List<Long> validIds = new ArrayList<Long>(0);
		if(designationIds1List !=null && designationIds1List.size()>0){
			for (Long id : designationIds1List) {
				if(id.toString().substring(0, 1).equalsIgnoreCase("2"))
					validIds.add(Long.valueOf(id.toString().substring(1)));
			}
			if(validIds == null || validIds.size()==0){
					return null;
			}
		}
		
		Set<Long> urbanIdsList = new HashSet<Long>();
		Set<Long> ruralIdsList = new HashSet<Long>();
		if(locationIdsList != null && locationIdsList.size()>0){
			if(levelId.longValue() == 5L || levelId.longValue() == 7L){
				for (Long id : locationIdsList) {
					if(Long.valueOf(id.toString().substring(0,1)) == 2L)
						ruralIdsList.add(Long.valueOf(id.toString().substring(1)));
					if(Long.valueOf(id.toString().substring(0,1)) == 1L)
						urbanIdsList.add(Long.valueOf(id.toString().substring(1)));
				}
			}
			else if(levelId.longValue() == 6L || levelId.longValue() == 8L ){
				for (Long id : locationIdsList) {
					if(Long.valueOf(id.toString().substring(0,1)) == 2L)
						urbanIdsList.add(Long.valueOf(id.toString().substring(1)));
					if(Long.valueOf(id.toString().substring(0,1)) == 1L)
						ruralIdsList.add(Long.valueOf(id.toString().substring(1)));
				}
			}
		}
		
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" SELECT ");
		if(fetchTypeStr != null && fetchTypeStr.equalsIgnoreCase("count")){
			queryStr.append("  count(distinct tc.tdp_cadre_id) as count , '' as empty ");
		}
		else{			
			queryStr.append(" distinct tc.tdp_cadre_id as tdp_cadre_di, ");
			queryStr.append(" tc.membership_id as membership_id,  ");
			queryStr.append(" tc.first_name as first_name , ");
			queryStr.append(" tc.mobile_no as mobile_no ,  ");
			queryStr.append(" prt.position as position, ");
			queryStr.append(" s.state_id as state_id , ");
			queryStr.append(" s.state_name as state_name , ");
			queryStr.append(" d.district_id as district_id , ");
			queryStr.append(" d.district_name as district_name, ");
			queryStr.append(" pc.constituency_id as pId , ");
			queryStr.append(" pc.`name` as pName, ");
			queryStr.append(" c.constituency_id as assemblyId, ");
			queryStr.append(" c.`name` as assemblyName, ");
			queryStr.append(" t.tehsil_id as tehsil_id, ");
			queryStr.append(" t.tehsil_name as tehsil_name , ");
			queryStr.append(" l.local_election_body_id as local_election_body_id , ");
			queryStr.append(" l.`name` as lebName, ");
			queryStr.append(" p.panchayat_id as panchayat_id, ");
			queryStr.append(" p.panchayat_name as panchayat_name, ");
			queryStr.append(" w.constituency_id as wardId, ");//19
			queryStr.append(" w.`name` as wardName,");
			queryStr.append(" tc.image as image ");

		}
		queryStr.append(" from  ");
		queryStr.append(" public_representative pr,  ");
		queryStr.append(" public_representative_type prt, ");
		queryStr.append(" tdp_cadre_enrollment_year tcey,  ");
		queryStr.append(" tdp_cadre tc ,  ");
		queryStr.append(" tdp_cadre_candidate tcc ,  ");
		queryStr.append(" user_address ua  ");
		queryStr.append(" left outer join state s on ua.state_id = s.state_id ");
		queryStr.append(" left outer join district d on ua.district_id = d.district_id  ");
		queryStr.append(" left outer join constituency c on ua.constituency_id = c.constituency_id  ");
		queryStr.append(" left outer join constituency pc on ua.parliament_constituency_id = pc.constituency_id ");
		queryStr.append(" left outer join tehsil t on ua.tehsil_id = t.tehsil_id  ");
		queryStr.append(" left outer join local_election_body l on ua.local_election_body = l.local_election_body_id  ");
		queryStr.append(" left outer join panchayat  p on ua.panchayat_id = p.panchayat_id  ");
		queryStr.append(" left outer join constituency w on ua.ward = w.constituency_id ");
		queryStr.append(" where  ");
		queryStr.append(" pr.address_id = ua.user_address_id and  ");
		queryStr.append(" pr.public_representative_type_id = prt.public_representative_type_id and  ");
		queryStr.append(" pr.candidate_id = tcc.candidate_id and  ");
		queryStr.append(" tcc.tdp_cadre_id = tcey.tdp_cadre_id and  ");
		queryStr.append(" tcey.tdp_cadre_id = tc.tdp_cadre_id  ");
		
		if(enrollmentIdsList != null && enrollmentIdsList.size()>0)
			queryStr.append("  and tcey.enrollment_year_id in (:enrollmentIdsList)   ");
		if(levelId != null && levelId.longValue()>0L && locationIdsList != null && locationIdsList.size()>0){
			if(levelId.longValue() == 2L)
				queryStr.append(" and ua.state_id in (:locationIdsList)   ");
			else if(levelId.longValue() == 3L)
				queryStr.append(" and ua.district_id in (:locationIdsList)   "); 
			else if(levelId.longValue() == 4L)
				queryStr.append(" and ua.constituency_id in (:locationIdsList)   "); 
			else if(levelId.longValue() == 5L){
				queryStr.append(" and ( ");
				if(ruralIdsList != null && ruralIdsList.size()>0){
					queryStr.append("  ua.tehsil_id in (:ruralIdsList)   ");
					if(urbanIdsList != null && urbanIdsList.size()>0){
						queryStr.append(" or ua.local_election_body in (:urbanIdsList)   ");
					}
				}else if(urbanIdsList != null && urbanIdsList.size()>0){
					queryStr.append(" ua.local_election_body in (:urbanIdsList)   ");
				}
				queryStr.append(" ) ");
			}
			else if(levelId.longValue() == 6L){
				queryStr.append(" and ( ");
				if(ruralIdsList != null && ruralIdsList.size()>0){
					queryStr.append("  ua.panchayat_id in (:ruralIdsList)   ");
					if(urbanIdsList != null && urbanIdsList.size()>0){
						queryStr.append(" or ua.ward in (:urbanIdsList)   ");
					}
				}else if(urbanIdsList != null && urbanIdsList.size()>0){
					queryStr.append(" ua.ward in (:urbanIdsList)   ");
				}
				queryStr.append(" ) ");
			}
			else if(levelId.longValue() == 7L){
				queryStr.append(" and ( ");
				if(ruralIdsList != null && ruralIdsList.size()>0){
					queryStr.append("  ua.tehsil_id in (:ruralIdsList)   ");
					if(urbanIdsList != null && urbanIdsList.size()>0){
						queryStr.append(" or ua.local_election_body in (:urbanIdsList)   ");
					}
				}else if(urbanIdsList != null && urbanIdsList.size()>0){
					queryStr.append(" ua.local_election_body in (:urbanIdsList)   ");
				}
				queryStr.append(" ) ");
			}
			else if(levelId.longValue() == 8L){
				queryStr.append(" and ( ");
				if(ruralIdsList != null && ruralIdsList.size()>0){
					queryStr.append("  ua.panchayat_id in (:ruralIdsList)   ");
					if(urbanIdsList != null && urbanIdsList.size()>0){
						queryStr.append(" or ua.ward in (:urbanIdsList)   ");
					}
				}else if(urbanIdsList != null && urbanIdsList.size()>0){
					queryStr.append(" ua.ward in (:urbanIdsList)   ");
				}
				queryStr.append(" ) ");
			}
		}
		
		if(stateId != null && stateId.longValue()>0L)
			queryStr.append(" and ua.state_id =:stateId  ");
		if(validIds != null && validIds.size()>0)
			queryStr.append(" and pr.public_representative_type_id in (:designationIdsList)   ");
		
		queryStr.append("  order by  ");
		queryStr.append("  tc.tdp_cadre_id ");
		
		Query query =  null;
		if(fetchTypeStr != null && fetchTypeStr.equalsIgnoreCase("count")){
			query = getSession().createSQLQuery(queryStr.toString())
					.addScalar("count", Hibernate.LONG)
					.addScalar("empty", Hibernate.STRING);
		}
		else{
			query = getSession().createSQLQuery(queryStr.toString())
					.addScalar("tdp_cadre_di", Hibernate.LONG)
					.addScalar("membership_id", Hibernate.STRING)
					.addScalar("first_name", Hibernate.STRING)
					.addScalar("mobile_no", Hibernate.STRING)
					.addScalar("position", Hibernate.STRING)
					.addScalar("state_id", Hibernate.LONG)
					.addScalar("state_name", Hibernate.STRING)
					.addScalar("district_id", Hibernate.LONG)
					.addScalar("district_name", Hibernate.STRING)
					.addScalar("pId", Hibernate.LONG)
					.addScalar("pName", Hibernate.STRING)
					.addScalar("assemblyId", Hibernate.LONG)
					.addScalar("assemblyName", Hibernate.STRING)
					.addScalar("tehsil_id", Hibernate.LONG)
					.addScalar("tehsil_name", Hibernate.STRING)
					.addScalar("local_election_body_id", Hibernate.LONG)
					.addScalar("lebName", Hibernate.STRING)
					.addScalar("panchayat_id", Hibernate.LONG)
					.addScalar("panchayat_name", Hibernate.STRING)
					.addScalar("wardId", Hibernate.LONG)
					.addScalar("wardName", Hibernate.STRING)
					.addScalar("image", Hibernate.STRING);
		}
		
		if(stateId != null && stateId.longValue()>0L)
			query.setParameter("stateId", stateId);
		if(validIds != null && validIds.size()>0)
			query.setParameterList("designationIdsList", validIds);
		if(enrollmentIdsList != null && enrollmentIdsList.size()>0)
			query.setParameterList("enrollmentIdsList", enrollmentIdsList);
		
		/*if(locationIdsList != null && locationIdsList.size()>0){
			Set<Long> idsList = new HashSet<Long>();
			if(levelId.longValue() == 5L || levelId.longValue() == 6L || levelId.longValue() == 7L || levelId.longValue() == 8L ){
				for (Long id : locationIdsList) {
					idsList.add(Long.valueOf(id.toString().substring(1)));
				}
			}else{
				idsList.addAll(locationIdsList);
			}
			query.setParameterList("locationIdsList", idsList);
		}*/
	
		if(locationIdsList != null && locationIdsList.size()>0){
			if(levelId.longValue() == 5L || levelId.longValue() == 6L || levelId.longValue() == 7L || levelId.longValue() == 8L ){
				if(urbanIdsList != null && urbanIdsList.size()>0)
					query.setParameterList("urbanIdsList", urbanIdsList);
				if(ruralIdsList != null && ruralIdsList.size()>0)
					query.setParameterList("ruralIdsList", ruralIdsList);
			}
			else{
				query.setParameterList("locationIdsList", locationIdsList);
			}
			
		}
		
		if(maxIndex >0){
			query.setFirstResult(firstIndex);
			query.setMaxResults(maxIndex);
		}
		
		return query.list();
	}
	public List<Object[]> getLocationWiseCandidateDesignations(List<Long> condidateIds){
		StringBuilder queryStr=new StringBuilder();
		  queryStr.append("select PR.candidateId,PR.publicRepresentativeType.publicRepresentativeTypeId," +
				" PR.publicRepresentativeType.type from PublicRepresentative PR ");
		if(condidateIds != null && condidateIds.size()>0){
			queryStr.append("where PR.candidateId in(:condidateIds)");
		 }		
		Query query = getSession().createQuery(queryStr.toString());
		if(condidateIds != null && condidateIds.size()>0){
			query.setParameterList("condidateIds", condidateIds);
		}
		return query.list();
	}
	public List<Object[]> getStateWiseCandidateDesignations(Long locationValue,Long locationTypeId,List<Long> representativTypeIds){
		StringBuilder sb=new StringBuilder();
		sb.append(" select pr.candidate.candidateId,pr.candidate.lastname," +
				  " pr.representativeLevel.representativeLevelId,pr.representativeLevel.representativeLevel," +
				" pr.publicRepresentativeType.publicRepresentativeTypeId," +
				" pr.publicRepresentativeType.type" +
				" from PublicRepresentative pr" +
				//",TdpCadreCandidate TCC " +
				"  where  " );
		 if (locationTypeId != null && locationTypeId.longValue() == 2l){
			sb.append(" pr.representativeLevel.representativeLevelId in(6) " );
		}
		
		if(representativTypeIds != null && representativTypeIds.size() > 0){
			sb.append(" and pr.publicRepresentativeType.publicRepresentativeTypeId in (:representativTypeIds) ");
		}	
		//sb.append(" group by pr.publicRepresentativeType.publicRepresentativeTypeId,pr.candidate.candidateId ");
		Query query = getSession().createQuery(sb.toString());
	
		if(representativTypeIds != null && representativTypeIds.size() > 0){
			query.setParameterList("representativTypeIds", representativTypeIds);
		}
		
		return query.list();
		
	}
	
	public List<Object[]> getParticipantsCandidateDesgnination(List<Long> candidateIdsList)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.candidate.candidateId,model.publicRepresentativeType.publicRepresentativeTypeId,"+
				" model.publicRepresentativeType.type,model.candidate.lastname ");
		queryStr.append("  from PublicRepresentative model");
		if(candidateIdsList != null && candidateIdsList.size()>0l){
		queryStr.append(" where model.candidateId in (:candidateIdsList) ");
		}
		Query query = getSession().createQuery(queryStr.toString());
		if(candidateIdsList != null && candidateIdsList.size()>0l){
		query.setParameterList("candidateIdsList", candidateIdsList);
		}
		
		return query.list();
	}
 }
