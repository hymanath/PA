package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IBoothInchargeRoleConditionMappingDAO;
import com.itgrids.partyanalyst.dto.InputVO;
import com.itgrids.partyanalyst.model.BoothInchargeRoleConditionMapping;
import com.itgrids.partyanalyst.utils.IConstants;

public class BoothInchargeRoleConditionMappingDAO extends GenericDaoHibernate<BoothInchargeRoleConditionMapping, Long> implements IBoothInchargeRoleConditionMappingDAO{

	public BoothInchargeRoleConditionMappingDAO() {
		super(BoothInchargeRoleConditionMapping.class);
	}
	
	public List<Object[]> getBoothInchargeRolesWithMinMAxCount(Long boothId,List<Long> enrollmentYrIds){
		
		StringBuilder sb = new StringBuilder();
		sb.append( " select model.boothInchargeRoleConditionMappingId, model.boothInchargeRoleCondition.minMembers," +
				"model.boothInchargeRoleCondition.maxMembers,model.boothInchargeRoleCondition.boothInchargeRole.boothInchargeRoleId" +
				",model.boothInchargeRoleCondition.boothInchargeRole.roleName from BoothInchargeRoleConditionMapping model where model.boothInchargeCommittee is not null " ); 
		
		if(boothId != null && boothId.longValue() >0l)
			sb.append( " and model.boothInchargeCommittee.address.booth.boothId = :boothId " );
		
		if(enrollmentYrIds != null && enrollmentYrIds.size() >0)
			sb.append( " and model.boothInchargeCommittee.boothInchargeEnrollmentId in (:enrollmentYrIds) " );
		Query query=getSession().createQuery(sb.toString()+" order by model.boothInchargeRoleCondition.boothInchargeRole.roleName");
		
		if(boothId != null && boothId.longValue() >0l)
			query.setParameter("boothId", boothId);
		if(enrollmentYrIds != null && enrollmentYrIds.size() >0)
			query.setParameterList("enrollmentYrIds", enrollmentYrIds);
		
		return query.list();
		
	}
	public List<Object[]> getBoothMinMaxRequiredMemberRoleWise(Long boothId,Long boothInchargeEnrollmentId) {
		 StringBuilder queryStr = new StringBuilder();
		 queryStr.append(" select " +
		 				 " model.boothInchargeRoleCondition.boothInchargeRole.boothInchargeRoleId," +
		 				 " model.boothInchargeRoleCondition.boothInchargeRole.roleName," +
		 				 " sum(model.boothInchargeRoleCondition.minMembers)," +
		 				 " sum(model.boothInchargeRoleCondition.maxMembers) " +
		 				 " from BoothInchargeRoleConditionMapping model " +
		 				 " where " +
		 				 " model.isDeleted='N' and model.boothInchargeCommittee.boothId=:boothId " +
		 				 " and model.boothInchargeEnrollmentId=:boothInchargeEnrollmentId " +
		 				 " group by " +
		 				 " model.boothInchargeRoleCondition.boothInchargeRole.boothInchargeRoleId");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("boothId", boothId);
		query.setParameter("boothInchargeEnrollmentId", boothInchargeEnrollmentId);
		return query.list();
	}
	public int updateBoothStatus(Long boothId,Long boothInchargeEnrollmentId,Date dateTime) {
		 StringBuilder queryStr = new StringBuilder();
		 queryStr.append(" update BoothInchargeCommittee model set model.isConfirmed='Y',model.completedDate=:completedDate" +
		 				 " where " +
		 				 " model.isDeleted='N' and  model.boothId=:boothId and model.boothInchargeEnrollmentId=:boothInchargeEnrollmentId ");
		 Query query = getSession().createQuery(queryStr.toString());
		  query.setParameter("boothId", boothId);
		  query.setParameter("boothInchargeEnrollmentId", boothInchargeEnrollmentId);
		  query.setParameter("completedDate", dateTime);
		  return query.executeUpdate();
	}
	
	public List<Object[]> getBoothDetailsForTehsilId(List<Long> tehsilIds,Long constituencyId){
		Query query = getSession().createQuery("select distinct model.boothInchargeCommittee.boothId," +
											" model.boothInchargeCommittee.booth.partNo," +
											" model.boothInchargeCommittee.booth.villagesCovered" +
											" from BoothInchargeRoleConditionMapping model" +
											" where model.boothInchargeCommittee.address.tehsil.tehsilId in (:tehsilIds)" +
											" and model.boothInchargeCommittee.address.constituency.constituencyId = :constituencyId" +
											" and model.boothInchargeCommittee.booth.publicationDate.publicationDateId = :publicationDate " +
											" and model.boothInchargeCommittee.address.localElectionBody is null ");
		
		query.setParameter("publicationDate", IConstants.BOOTH_INCHARGE_COMMITTEE_PUBLICATION_DATE_ID);
		query.setParameterList("tehsilIds", tehsilIds);
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}

	public List<Object[]> getBoothsDetailsForMuncipality(List<Long> lcalElcBdyId,Long constituencyId){
		Query query = getSession().createQuery("select distinct model.boothInchargeCommittee.boothId," +
				" model.boothInchargeCommittee.booth.partNo," +
				" model.boothInchargeCommittee.booth.villagesCovered" +
				" from BoothInchargeRoleConditionMapping model" +
				" where model.boothInchargeCommittee.address.localElectionBody.localElectionBodyId in (:lcalElcBdyId)" +
				" and model.boothInchargeCommittee.address.constituency.constituencyId = :constituencyId" +
				" and model.boothInchargeCommittee.booth.publicationDate.publicationDateId = :publicationDate");

			query.setParameter("publicationDate", IConstants.BOOTH_INCHARGE_COMMITTEE_PUBLICATION_DATE_ID);
			query.setParameterList("lcalElcBdyId", lcalElcBdyId);
			query.setParameter("constituencyId", constituencyId);
			return query.list();
	}

 public List<Object[]> gettingBoothInchargeMaxCount(Long boothId,Long boothInchargeEnrollmentId,Long locationValue){
		
		StringBuilder sb = new StringBuilder();
		sb.append( " select model.boothInchargeRoleConditionMappingId, model.boothInchargeRoleCondition.minMembers," +
				"model.boothInchargeRoleCondition.maxMembers,model.boothInchargeRoleCondition.boothInchargeRole.boothInchargeRoleId" +
				",model.boothInchargeRoleCondition.boothInchargeRole.roleName from BoothInchargeRoleConditionMapping model where " ); 
		
		if(boothId != null && boothId.longValue() >0l)
			sb.append( "  model.boothInchargeCommittee.booth.boothId = :boothId " );
		
		if(boothInchargeEnrollmentId != null && boothInchargeEnrollmentId.longValue() >0l)
			sb.append( " and model.boothInchargeEnrollment.boothInchargeEnrollmentId = :boothInchargeEnrollmentId " );
		if(locationValue != null && locationValue.longValue() >0l){
			sb.append( " and model.boothInchargeCommittee.address.constituency.constituencyId = :locationValue ");
		}
		sb.append( " group by model.boothInchargeRoleConditionMappingId ");
		Query query=getSession().createQuery(sb.toString());
		
		if(boothId != null && boothId.longValue() >0l)
			query.setParameter("boothId", boothId);
		if(boothInchargeEnrollmentId != null && boothInchargeEnrollmentId.longValue() >0l)
			query.setParameter("boothInchargeEnrollmentId", boothInchargeEnrollmentId);
		if(locationValue != null && locationValue.longValue() >0l){
			query.setParameter("locationValue", locationValue);
		}
		return query.list();
		
	}

	public List<Object[]> getLocationLevelWiseBoothCount(InputVO inputVO,String resultType) {

		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select ");
		if (inputVO.getLocationLevel().equalsIgnoreCase(IConstants.STATE)) {
			queryStr.append(" state.stateId ");
			queryStr.append(" ,state.stateName");
		} else if (inputVO.getLocationLevel().equalsIgnoreCase(IConstants.DISTRICT)) {
			queryStr.append(" district.districtId ");
			queryStr.append(" ,district.districtName");
		} else if (inputVO.getLocationLevel().equalsIgnoreCase(IConstants.PARLIAMENT_CONSTITUENCY)) {
			queryStr.append(" parliamentConstituency.constituencyId ");
			queryStr.append(" ,parliamentConstituency.name ");
		} else if (inputVO.getLocationLevel().equalsIgnoreCase(IConstants.CONSTITUENCY)) {
			queryStr.append(" constituency.constituencyId ");
			queryStr.append(" ,constituency.name ");
		} else if(inputVO.getLocationLevel().equalsIgnoreCase(IConstants.LOCALELECTIONBODY)){
			queryStr.append(" localElectionBody.localElectionBodyId ");
			queryStr.append(" ,localElectionBody.name");
		} else if (inputVO.getLocationLevel().equalsIgnoreCase(IConstants.TEHSIL)) {
			queryStr.append(" tehsil.tehsilId ");
			queryStr.append(" ,tehsil.tehsilName");
		} else if (inputVO.getLocationLevel().equalsIgnoreCase(IConstants.PANCHAYAT)) {
			queryStr.append(" panc.panchayatId ");
			queryStr.append(" ,panc.panchayatName");
		}
		queryStr.append(",count(distinct model1.boothId)");

		queryStr.append(",state.stateId,state.stateName,district.districtId,district.districtName,parliamentConstituency.constituencyId,parliamentConstituency.name");
		queryStr.append(",constituency.constituencyId,constituency.name,tehsil.tehsilId,tehsil.tehsilName,panc.panchayatId,panc.panchayatName");
		queryStr.append(",localElectionBody.localElectionBodyId,localElectionBody.name,electionType.electionTypeId,electionType.electionType ");

		queryStr.append(" from BoothInchargeRoleConditionMapping model ");
		queryStr.append(" left join model.boothInchargeCommittee model1 ");
		queryStr.append(" left join model1.address.state state ");
		queryStr.append(" left join model1.address.district district ");
		queryStr.append(" left join model1.address.parliamentConstituency parliamentConstituency ");
		queryStr.append(" left join model1.address.constituency constituency ");
		queryStr.append(" left join model1.address.tehsil tehsil ");
		queryStr.append(" left join model1.address.localElectionBody localElectionBody ");
		queryStr.append(" left join model1.address.localElectionBody.electionType electionType ");
		queryStr.append(" left join model1.address.panchayat panc ");

		queryStr.append(" where model.isDeleted='N' and model1.isDeleted='N' ");
           
		if (inputVO.getLocationLevel().equalsIgnoreCase(IConstants.TEHSIL)) {
        	 queryStr.append(" and localElectionBody.localElectionBodyId is null ");
         }
		if(inputVO.getBoothRoleIds() != null && inputVO.getBoothRoleIds().size() > 0){
			queryStr.append(" and model.boothInchargeRoleCondition.boothInchargeRole.boothInchargeRoleId in(:boothRoleIds)");
		}
		if (inputVO.getFilterLevel().length() > 0 && inputVO.getFilterValueList() != null && inputVO.getFilterValueList().size() > 0) {
			if (inputVO.getFilterLevel().equalsIgnoreCase(IConstants.STATE)){
				queryStr.append(" and state.stateId in(:filterValues)");
			}else if (inputVO.getFilterLevel().equalsIgnoreCase(IConstants.DISTRICT)) {
				queryStr.append(" and district.districtId in(:filterValues) ");
			} else if (inputVO.getFilterLevel().equalsIgnoreCase(IConstants.PARLIAMENT_CONSTITUENCY)) {
				queryStr.append(" and parliamentConstituency.constituencyId in(:filterValues) ");
			} else if (inputVO.getFilterLevel().equalsIgnoreCase(IConstants.CONSTITUENCY)) {
				queryStr.append(" and constituency.constituencyId in(:filterValues)");
			} else if(inputVO.getFilterLevel().equalsIgnoreCase(IConstants.LOCALELECTIONBODY)){
				queryStr.append(" and localElectionBody.localElectionBodyId in(:filterValues) ");
			} else if (inputVO.getFilterLevel().equalsIgnoreCase(IConstants.TEHSIL)) {
				queryStr.append(" and tehsil.tehsilId in(:filterValues)");
			} else if (inputVO.getFilterLevel().equalsIgnoreCase(IConstants.PANCHAYAT)) {
				queryStr.append(" and panc.panchayatId in(:filterValues)");
			}
		}
        
		if (resultType.equalsIgnoreCase("TotalBooth")) {//NotStarted
			//queryStr.append(" and model1.isConfirmed='N' and model1.startDate is null and model1.completedDate is null ");
		} else if (resultType.equalsIgnoreCase("Started")) {
			queryStr.append(" and model1.isConfirmed='N' and model1.startDate is not null and model1.completedDate is null");
		} else if (resultType.equalsIgnoreCase("Completed")) {
			queryStr.append(" and model1.isConfirmed='Y' and model1.startDate is not null and model1.completedDate is not null ");
		}

		if (inputVO.getFromDate() != null && inputVO.getToDate() != null && !resultType.equalsIgnoreCase("TotalBooth")) {
			if (resultType.equalsIgnoreCase("Started")) {
				queryStr.append(" and date(model1.startDate) between :fromDate and :toDate ");
			} else if (resultType.equalsIgnoreCase("Completed")) {
				queryStr.append(" and date(model1.completedDate) between :fromDate and :toDate ");
			}
		}
		if (inputVO.getBoothInchargeEnrollmentId() != null && inputVO.getBoothInchargeEnrollmentId().longValue() > 0) {
			queryStr.append(" and model1.boothInchargeEnrollmentId =:boothInchargeEnrollmentId ");
		}
		queryStr.append(" group by ");
		if (inputVO.getLocationLevel().equalsIgnoreCase(IConstants.STATE)) {
			queryStr.append(" state.stateId ");
		}if (inputVO.getLocationLevel().equalsIgnoreCase(IConstants.DISTRICT)) {
			queryStr.append(" district.districtId ");
		} else if (inputVO.getLocationLevel().equalsIgnoreCase(IConstants.PARLIAMENT_CONSTITUENCY)) {
			queryStr.append(" parliamentConstituency.constituencyId ");
		} else if (inputVO.getLocationLevel().equalsIgnoreCase(IConstants.CONSTITUENCY)) {
			queryStr.append(" constituency.constituencyId ");
		} else if(inputVO.getLocationLevel().equalsIgnoreCase(IConstants.LOCALELECTIONBODY)){
			queryStr.append(" constituency.constituencyId,localElectionBody.localElectionBodyId ");
		}  else if (inputVO.getLocationLevel().equalsIgnoreCase(IConstants.TEHSIL)) {
			queryStr.append(" tehsil.tehsilId ");
		} else if (inputVO.getLocationLevel().equalsIgnoreCase(IConstants.PANCHAYAT)) {
			queryStr.append(" panc.panchayatId ");
		}
		
		Query query = getSession().createQuery(queryStr.toString());

		if (inputVO.getFromDate() != null && inputVO.getToDate() != null && !resultType.equalsIgnoreCase("TotalBooth")) {
			query.setDate("fromDate", inputVO.getFromDate());
			query.setDate("toDate", inputVO.getToDate());
		}
		if (inputVO.getBoothInchargeEnrollmentId() != null && inputVO.getBoothInchargeEnrollmentId().longValue() > 0) {
			query.setParameter("boothInchargeEnrollmentId",inputVO.getBoothInchargeEnrollmentId());
		}
		if (inputVO.getFilterLevel().length() > 0 && inputVO.getFilterValueList() != null && inputVO.getFilterValueList().size() > 0) {
			query.setParameterList("filterValues", inputVO.getFilterValueList());
		}
		if(inputVO.getBoothRoleIds() != null && inputVO.getBoothRoleIds().size() > 0){
			query.setParameterList("boothRoleIds", inputVO.getBoothRoleIds());
		}
	
		return query.list();
	}
	public List<Object[]> getLocationBasedOnSelection(InputVO inputVO) {

		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select distinct ");
		if (inputVO.getLocationLevel().equalsIgnoreCase(IConstants.DISTRICT)) {
			queryStr.append(" district.districtId ");
			queryStr.append(" ,district.districtName");
		} else if (inputVO.getLocationLevel().equalsIgnoreCase(IConstants.PARLIAMENT_CONSTITUENCY)) {
			queryStr.append(" parliamentConstituency.constituencyId ");
			queryStr.append(" ,parliamentConstituency.name ");
		} else if (inputVO.getLocationLevel().equalsIgnoreCase(IConstants.CONSTITUENCY)) {
			queryStr.append(" constituency.constituencyId ");
			queryStr.append(" ,constituency.name ");
		} else if(inputVO.getLocationLevel().equalsIgnoreCase(IConstants.LOCALELECTIONBODY)){
			queryStr.append(" localElectionBody.localElectionBodyId ");
			queryStr.append(" ,localElectionBody.name");
		} else if (inputVO.getLocationLevel().equalsIgnoreCase(IConstants.TEHSIL)) {
			queryStr.append(" tehsil.tehsilId ");
			queryStr.append(" ,tehsil.tehsilName");
		} else if (inputVO.getLocationLevel().equalsIgnoreCase(IConstants.PANCHAYAT)) {
			queryStr.append(" panc.panchayatId ");
			queryStr.append(" ,panc.panchayatName");
		}
	
		queryStr.append(" from BoothInchargeRoleConditionMapping model ");
		queryStr.append(" left join model.boothInchargeCommittee model1 ");
		queryStr.append(" left join model1.address.state state ");
		queryStr.append(" left join model1.address.district district ");
		queryStr.append(" left join model1.address.parliamentConstituency parliamentConstituency ");
		queryStr.append(" left join model1.address.constituency constituency ");
		queryStr.append(" left join model1.address.tehsil tehsil ");
		queryStr.append(" left join model1.address.localElectionBody localElectionBody ");
		queryStr.append(" left join model1.address.localElectionBody.electionType electionType ");
		queryStr.append(" left join model1.address.panchayat panc ");

		queryStr.append(" where model.isDeleted='N' and model1.isDeleted='N' ");
		
		if(inputVO.getBoothRoleIds() != null && inputVO.getBoothRoleIds().size() > 0){
			queryStr.append(" and model.boothInchargeRoleCondition.boothInchargeRole.boothInchargeRoleId in(:boothRoleIds)");
		}
		if (inputVO.getFilterLevel().length() > 0 && inputVO.getFilterValueList() != null && inputVO.getFilterValueList().size() > 0) {

			if (inputVO.getFilterLevel().equalsIgnoreCase(IConstants.STATE)){
				queryStr.append(" and state.stateId in(:filterValues)");
			}else if (inputVO.getFilterLevel().equalsIgnoreCase(IConstants.DISTRICT)) {
				queryStr.append(" and district.districtId in(:filterValues) ");
			} else if (inputVO.getFilterLevel().equalsIgnoreCase(IConstants.PARLIAMENT_CONSTITUENCY)) {
				queryStr.append(" and parliamentConstituency.constituencyId in(:filterValues) ");
			} else if (inputVO.getFilterLevel().equalsIgnoreCase(IConstants.CONSTITUENCY)) {
				queryStr.append(" and constituency.constituencyId in(:filterValues)");
			} else if(inputVO.getFilterLevel().equalsIgnoreCase(IConstants.LOCALELECTIONBODY)){
				queryStr.append(" and localElectionBody.localElectionBodyId in(:filterValues)");
			} else if (inputVO.getFilterLevel().equalsIgnoreCase(IConstants.TEHSIL)) {
				queryStr.append(" and tehsil.tehsilId in(:filterValues)");
			} else if (inputVO.getFilterLevel().equalsIgnoreCase(IConstants.PANCHAYAT)) {
				queryStr.append(" and panc.panchayatId in(:filterValues)");
			}
		}

	   if (inputVO.getBoothInchargeEnrollmentId() != null && inputVO.getBoothInchargeEnrollmentId().longValue() > 0) {
			queryStr.append(" and model1.boothInchargeEnrollment.boothInchargeEnrollmentId =:boothInchargeEnrollmentId ");
		}
		
		Query query = getSession().createQuery(queryStr.toString());

		if (inputVO.getBoothInchargeEnrollmentId() != null && inputVO.getBoothInchargeEnrollmentId().longValue() > 0) {
			query.setParameter("boothInchargeEnrollmentId",inputVO.getBoothInchargeEnrollmentId());
		}
		if (inputVO.getFilterLevel().length() > 0 && inputVO.getFilterValueList() != null && inputVO.getFilterValueList().size() > 0) {
			query.setParameterList("filterValues", inputVO.getFilterValueList());
		}
		if(inputVO.getBoothRoleIds() != null && inputVO.getBoothRoleIds().size() > 0){
			query.setParameterList("boothRoleIds", inputVO.getBoothRoleIds());
		}
	
		return query.list();
	}
	
	public List<Object[]> getLocationLevelWiseBoothDetails(InputVO inputVO) {

		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct ");
        queryStr.append(" model1.booth.boothId,model1.booth.partNo,model1.isConfirmed,model1.startDate,model1.completedDate");
		queryStr.append(",state.stateId,state.stateName,district.districtId,district.districtName,parliamentConstituency.constituencyId,parliamentConstituency.name");
		queryStr.append(",constituency.constituencyId,constituency.name,tehsil.tehsilId,tehsil.tehsilName,panc.panchayatId,panc.panchayatName ");
		queryStr.append(",localElectionBody.localElectionBodyId,localElectionBody.name,electionType.electionTypeId,electionType.electionType ");

		queryStr.append(" from BoothInchargeRoleConditionMapping model ");
		queryStr.append(" left join model.boothInchargeCommittee model1 ");
		queryStr.append(" left join model1.address.state state ");
		queryStr.append(" left join model1.address.district district ");
		queryStr.append(" left join model1.address.parliamentConstituency parliamentConstituency ");
		queryStr.append(" left join model1.address.constituency constituency ");
		queryStr.append(" left join model1.address.tehsil tehsil ");
		queryStr.append(" left join model1.address.localElectionBody localElectionBody ");
		queryStr.append(" left join model1.address.localElectionBody.electionType electionType ");
		queryStr.append(" left join model1.address.panchayat panc ");

		queryStr.append(" where model.isDeleted='N' and model1.isDeleted='N' ");
		
		if(inputVO.getBoothRoleIds() != null && inputVO.getBoothRoleIds().size() > 0){
			queryStr.append(" and model.boothInchargeRoleCondition.boothInchargeRole.boothInchargeRoleId in(:boothRoleIds)");
		}
		if (inputVO.getFilterLevel().length() > 0 && inputVO.getFilterValueList() != null && inputVO.getFilterValueList().size() > 0) {
			if (inputVO.getFilterLevel().equalsIgnoreCase(IConstants.STATE)) {
				queryStr.append(" and state.stateId in(:filterValues) ");
			} else if (inputVO.getFilterLevel().equalsIgnoreCase(IConstants.DISTRICT)) {
				queryStr.append(" and district.districtId in(:filterValues) ");
			} else if (inputVO.getFilterLevel().equalsIgnoreCase(IConstants.PARLIAMENT_CONSTITUENCY)) {
				queryStr.append(" and parliamentConstituency.constituencyId in(:filterValues)");
			} else if (inputVO.getFilterLevel().equalsIgnoreCase(IConstants.CONSTITUENCY)) {
				queryStr.append(" and constituency.constituencyId in(:filterValues)");
			} else if(inputVO.getFilterLevel().equalsIgnoreCase(IConstants.LOCALELECTIONBODY)){
				queryStr.append(" and localElectionBody.localElectionBodyId in(:filterValues) ");
			} else if (inputVO.getFilterLevel().equalsIgnoreCase(IConstants.TEHSIL)) {
				queryStr.append(" and tehsil.tehsilId in(:filterValues)");
			} else if (inputVO.getFilterLevel().equalsIgnoreCase(IConstants.PANCHAYAT)) {
				queryStr.append(" and panc.panchayatId in(:filterValues)");
			}
		}
		if (inputVO.getFilterLevel().equalsIgnoreCase(IConstants.TEHSIL)) {
       	  queryStr.append(" and localElectionBody.localElectionBodyId is null ");
        }
		if (inputVO.getResultType().equalsIgnoreCase("NotStarted")) {
			queryStr.append(" and model1.isConfirmed='N' and model1.startDate is null and model1.completedDate is null ");
		} else if (inputVO.getResultType().equalsIgnoreCase("Started")) {
			queryStr.append(" and model1.isConfirmed='N' and model1.startDate is not null and model1.completedDate is null");
		} else if (inputVO.getResultType().equalsIgnoreCase("Completed")) {
			queryStr.append(" and model1.isConfirmed='Y' and model1.startDate is not null and model1.completedDate is not null ");
		}

		if (inputVO.getFromDate() != null && inputVO.getToDate() != null && !inputVO.getResultType().equalsIgnoreCase("NotStarted")) {
			if (inputVO.getResultType().equalsIgnoreCase("Started")) {
				queryStr.append(" and date(model1.startDate) between :fromDate and :toDate ");
			} else if (inputVO.getResultType().equalsIgnoreCase("Completed")) {
				queryStr.append(" and date(model1.completedDate) between :fromDate and :toDate ");
			}
		}
		if (inputVO.getBoothInchargeEnrollmentId() != null && inputVO.getBoothInchargeEnrollmentId().longValue() > 0) {
			queryStr.append(" and model1.boothInchargeEnrollmentId =:boothInchargeEnrollmentId ");
		}
		
		Query query = getSession().createQuery(queryStr.toString());

		if (inputVO.getFromDate() != null && inputVO.getToDate() != null && !inputVO.getResultType().equalsIgnoreCase("NotStarted")) {
			query.setDate("fromDate", inputVO.getFromDate());
			query.setDate("toDate", inputVO.getToDate());
		}
		if (inputVO.getBoothInchargeEnrollmentId() != null && inputVO.getBoothInchargeEnrollmentId().longValue() > 0) {
			query.setParameter("boothInchargeEnrollmentId",inputVO.getBoothInchargeEnrollmentId());
		}
		if (inputVO.getFilterLevel().length() > 0 && inputVO.getFilterValueList() != null && inputVO.getFilterValueList().size() > 0) {
			query.setParameterList("filterValues", inputVO.getFilterValueList());
		}
		if(inputVO.getBoothRoleIds() != null && inputVO.getBoothRoleIds().size() > 0){
			query.setParameterList("boothRoleIds", inputVO.getBoothRoleIds());
		}
		return query.list();
	}
	public List<Object[]> getBoothInchargeRoles(){
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select distinct model.boothInchargeRoleCondition.boothInchargeRole.boothInchargeRoleId," +
				    " model.boothInchargeRoleCondition.boothInchargeRole.roleName " +
				    " from BoothInchargeRoleConditionMapping model " +
				    " where model.isDeleted ='N'");
		Query query = getSession().createQuery(sb.toString());
		return query.list();
		
	}

	public List<Object[]> getLocationLevelBoothWiserRequieredMembersDetails(InputVO inputVO,String countType) {

		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct ");
		if(countType != null && !countType.isEmpty()){
			if(countType.trim().equalsIgnoreCase("rolesCount")){
				queryStr.append(" model1.booth.boothId, sum(model.boothInchargeRoleCondition.maxMembers) ");
				queryStr.append(" from BoothInchargeRoleConditionMapping model ");
			}else if(countType.trim().equalsIgnoreCase("addedCount")){
				queryStr.append(" model1.booth.boothId, count(model2.tdpCadreId)");
				queryStr.append(" from BoothIncharge model2 ");
				queryStr.append("  left join model2.boothInchargeRoleConditionMapping model ");
			}
		}
		queryStr.append(" left join model.boothInchargeCommittee model1 ");
		queryStr.append(" left join model1.address.state state ");
		queryStr.append(" left join model1.address.district district ");
		queryStr.append(" left join model1.address.parliamentConstituency parliamentConstituency ");
		queryStr.append(" left join model1.address.constituency constituency ");
		queryStr.append(" left join model1.address.tehsil tehsil ");
		queryStr.append(" left join model1.address.localElectionBody localElectionBody ");
		queryStr.append(" left join model1.address.localElectionBody.electionType electionType ");
		queryStr.append(" left join model1.address.panchayat panc ");
		queryStr.append(" where model.isDeleted='N' and model1.isDeleted='N' ");
		
		if(countType.trim().equalsIgnoreCase("addedCount")){
			queryStr.append(" and model2.isDeleted='N' and model2.isActive='Y' ");
		}
		if (inputVO.getFilterLevel().length() > 0 && inputVO.getFilterValueList() != null && inputVO.getFilterValueList().size() > 0) {
			if (inputVO.getFilterLevel().equalsIgnoreCase(IConstants.STATE)) {
				queryStr.append(" and state.stateId in(:filterValues) ");
			} else if (inputVO.getFilterLevel().equalsIgnoreCase(IConstants.DISTRICT)) {
				queryStr.append(" and district.districtId in(:filterValues) ");
			} else if (inputVO.getFilterLevel().equalsIgnoreCase(IConstants.PARLIAMENT_CONSTITUENCY)) {
				queryStr.append(" and parliamentConstituency.constituencyId in(:filterValues)");
			} else if (inputVO.getFilterLevel().equalsIgnoreCase(IConstants.CONSTITUENCY)) {
				queryStr.append(" and constituency.constituencyId in(:filterValues)");
			} else if(inputVO.getFilterLevel().equalsIgnoreCase(IConstants.LOCALELECTIONBODY)){
				queryStr.append(" and localElectionBody.localElectionBodyId in(:filterValues) ");
			} else if (inputVO.getFilterLevel().equalsIgnoreCase(IConstants.TEHSIL)) {
				queryStr.append(" and tehsil.tehsilId in(:filterValues)");
			} else if (inputVO.getFilterLevel().equalsIgnoreCase(IConstants.PANCHAYAT)) {
				queryStr.append(" and panc.panchayatId in(:filterValues)");
			}
		}
		if (inputVO.getFilterLevel().equalsIgnoreCase(IConstants.TEHSIL)) {
       	  queryStr.append(" and localElectionBody.localElectionBodyId is null ");
        }
		
		if (inputVO.getBoothInchargeEnrollmentId() != null && inputVO.getBoothInchargeEnrollmentId().longValue() > 0) {
			queryStr.append(" and model1.boothInchargeEnrollmentId =:boothInchargeEnrollmentId ");
		}
		queryStr.append(" group by model1.booth.boothId ");
		Query query = getSession().createQuery(queryStr.toString());

		if (inputVO.getBoothInchargeEnrollmentId() != null && inputVO.getBoothInchargeEnrollmentId().longValue() > 0) {
			query.setParameter("boothInchargeEnrollmentId",inputVO.getBoothInchargeEnrollmentId());
		}
		if (inputVO.getFilterLevel().length() > 0 && inputVO.getFilterValueList() != null && inputVO.getFilterValueList().size() > 0) {
			query.setParameterList("filterValues", inputVO.getFilterValueList());
		}
		return query.list();
	}
	public int updateBoothStarteDate(Long boothId,Long boothInchargeEnrollmentId) {
		 StringBuilder queryStr = new StringBuilder();
		 queryStr.append(" update BoothInchargeCommittee model set model.isConfirmed='N',model.startDate='NULL' " +
		 				 " where " +
		 				 " model.isDeleted='N' and  model.boothId=:boothId and model.boothInchargeEnrollmentId=:boothInchargeEnrollmentId ");
		  Query query = getSession().createQuery(queryStr.toString());
		  query.setParameter("boothId", boothId);
		  query.setParameter("boothInchargeEnrollmentId", boothInchargeEnrollmentId);
		  return query.executeUpdate();
	}
}
