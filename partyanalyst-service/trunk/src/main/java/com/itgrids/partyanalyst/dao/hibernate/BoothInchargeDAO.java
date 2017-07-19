package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IBoothInchargeDAO;
import com.itgrids.partyanalyst.dto.InputVO;
import com.itgrids.partyanalyst.model.BoothIncharge;
import com.itgrids.partyanalyst.utils.IConstants;

public class BoothInchargeDAO extends GenericDaoHibernate<BoothIncharge, Long> implements IBoothInchargeDAO{

	public BoothInchargeDAO() {
		super(BoothIncharge.class);
	}
	
	public List<Object[]> getBoothUserDetails(Long constituencyId, Long mandalId, Long boothId,String cadreType){
		StringBuilder query = new StringBuilder("select distinct booth.partNo, " +
				    " booth.villagesCovered, " +
					" constituency.name, " +
					" panchayat.panchayatName," +
					" model.tdpCadre.firstname, " +
					" model.tdpCadre.mobileNo, " +
					" model.tdpCadre.memberShipNo, " +
					" model.tdpCadre.image, " +
					" tehsil.tehsilName," +
					" model.boothInchargeRoleConditionMapping.boothInchargeRoleCondition.boothInchargeRole.roleName," +
					" model.boothInchargeRoleConditionMapping.boothInchargeRoleConditionMappingId,model.boothInchargeId ," +
					" bpv.serialNo, ward.name ,localElectionBody.name   " +
					" from BoothPublicationVoter bpv," +
					" BoothIncharge model  " +
					" left join model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address address " +
					" left join address.state state  " +
					" left join address.district district  " +
					" left join address.constituency constituency  " +
					" left join address.tehsil tehsil  " +
					" left join address.localElectionBody localElectionBody  " +
					" left join address.panchayat panchayat  " +
					" left join address.ward ward  " +
					" left join address.booth booth  " +
					" where  booth.boothId = bpv.booth.boothId and ");
		if(cadreType != null && !cadreType.isEmpty()){
			if(cadreType.trim().equalsIgnoreCase("familyVoterId"))
				query.append(" bpv.voter.voterId = model.tdpCadre.familyVoterId ");
			else 
				query.append(" bpv.voter.voterId = model.tdpCadre.voterId ");
		}
		query.append("  and model.isActive='Y' and  model.isDeleted='N'  and model.tdpCadre.isDeleted='N' and model.tdpCadre.enrollmentYear=2014 and " +
					" booth.publicationDate.publicationDateId = :publicationDate");
		if(constituencyId !=null && constituencyId.longValue() > 0)
		query.append(" and constituency.constituencyId=:constituencyId");
		if(mandalId !=null && mandalId.longValue() > 0)
		query.append(" and tehsil.tehsilId=:mandalId");
		if(boothId !=null && boothId.longValue() > 0)
		query.append(" and booth.boothId=:boothId");
		
		Query query1=getSession().createQuery(query.toString());
		query1.setParameter("publicationDate", IConstants.BOOTH_INCHARGE_COMMITTEE_PUBLICATION_DATE_ID);
		if(constituencyId !=null && constituencyId.longValue() > 0){
			query1.setParameter("constituencyId", constituencyId);
		}
		if(mandalId !=null && mandalId.longValue() > 0){
			query1.setParameter("mandalId", mandalId);
		}
		if(boothId !=null && boothId.longValue() > 0){
			query1.setParameter("boothId", boothId);
		}
		return query1.list();
	}
	
	public List<Object[]> getCadreIdsForLocation(List<Long> tdpCadreIds){
		StringBuilder sb = new StringBuilder();
		sb.append("select " +
				" model.tdpCadre.tdpCadreId," +
				" b.partNo, " +
				" p.panchayatName, " +
				" t.tehsilId, " +
				" t.tehsilName, " +
				" l.localElectionBodyId, " +
				" l.name, model.boothInchargeRoleConditionMapping.boothInchargeRoleCondition.boothInchargeRole.roleName " +
				" from BoothIncharge model " +
				" left join model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.booth b " +
				" left join model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.panchayat p " +
				" left join model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.tehsil t " +
				" left join model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.localElectionBody l " +
				" where model.isActive = 'Y' and model.isDeleted = 'N'");
		if(tdpCadreIds != null && tdpCadreIds.size() > 0l)
		{ 
			sb.append(" and model.tdpCadreId in (:tdpCadreIds)");
		}
		
		Query query = getSession().createQuery(sb.toString());
		if(tdpCadreIds != null && tdpCadreIds.size() > 0l) 
			query.setParameterList("tdpCadreIds", tdpCadreIds);
		
		return query.list();
	}
	public BoothIncharge getExistingMember(Long tdpCadreId,String type){
		StringBuilder sb = new StringBuilder();
		sb.append("select model" +
				" from BoothIncharge model" );
		
		if(type != null && type.trim().equalsIgnoreCase("removeOption"))
			sb.append(" where model.isActive = 'Y' and model.isDeleted = 'N'");
		else if(type != null && type.trim().equalsIgnoreCase("addOption"))
			sb.append(" where model.isActive = 'N' and model.isDeleted = 'N'");
		
		if(tdpCadreId != null && tdpCadreId.longValue() > 0l){
			sb.append(" and model.tdpCadreId = :tdpCadreId");
		}
		
		Query query = getSession().createQuery(sb.toString());
		if(tdpCadreId != null && tdpCadreId.longValue() > 0l)
			query.setParameter("tdpCadreId", tdpCadreId);
		
		return (BoothIncharge) query.uniqueResult();
		
	}
	
	public Long getStartedBothCountByConstiId(Long constituencyId){
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select  count(distinct model.boothInchargeRoleConditionMapping.boothInchargeCommittee.booth.boothId) " +
				  " from BoothIncharge model " +
				  " where model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.constituency.constituencyId = :constituencyId " +
				  " and model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.booth.publicationDate.publicationDateId = :publicationDate " +
				  " and  model.isDeleted='N' and model.isActive='Y'   ");
		
		Query qry = getSession().createQuery(sb.toString());
		
		if(constituencyId != null && constituencyId.longValue()>0l){
			qry.setParameter("constituencyId", constituencyId);
		}
		
		qry.setParameter("publicationDate", IConstants.BOOTH_INCHARGE_COMMITTEE_PUBLICATION_DATE_ID);
		
		return (Long) qry.uniqueResult();
	}
   public Long getBoothAssignInchargeCount(Long userAccessLevelId,Set<Long> userAccessLevelValues,Date startDate,Date endDate,List<Long> committeeEnrollmentYearsIdsLst,List<Long> bothIds){
		
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append(" select  count(distinct model.boothId) " +
				  " from BoothIncharge model " +
				  " where " +
				  " model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.booth.publicationDate.publicationDateId = :publicationDate " +
				  " and model.isActive ='Y' and model.isDeleted = 'N' ");
		          if(bothIds != null && bothIds.size()>0){
			             queryStr.append(" and model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.boothId in (:bothIds) ");
		             }
				if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
				         queryStr.append(" and model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.state.stateId in (:userAccessLevelValues)");  
				       }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
				         queryStr.append(" and .boothInchargeRoleConditionMapping.boothInchargeCommittee.address.district.districtId in (:userAccessLevelValues)");  
				       }/*else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
				            queryStr.append(" and model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
				       }*/else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
				            queryStr.append(" and model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.constituency.constituencyId in (:userAccessLevelValues) ");  
				       }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
				          queryStr.append(" and model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.tehsil.tehsilId in (:userAccessLevelValues)");  
				       }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
				          queryStr.append(" and model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.localBody.localElectionBodyId in (:userAccessLevelValues)"); 
				       }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
				          queryStr.append(" and model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.panchayat.panchayatId in (:userAccessLevelValues)"); 
				       }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
				          queryStr.append(" and model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.ward.constituencyId in (:userAccessLevelValues)"); 
				       }
				if(startDate != null && endDate != null){
					queryStr.append(" and date(model.insertedTime) between :startDate and :endDate ");
				}
				if(committeeEnrollmentYearsIdsLst != null && committeeEnrollmentYearsIdsLst.size()>0){
					queryStr.append(" and model.boothInchargeEnrollment.boothInchargeEnrollmentId in(:committeeEnrollmentYearsIdsLst) ");
				}
	 	  Query qry = getSession().createQuery(queryStr.toString());
		
		qry.setParameter("publicationDate", IConstants.BOOTH_INCHARGE_COMMITTEE_PUBLICATION_DATE_ID);
		if(userAccessLevelValues != null && userAccessLevelValues.size()>0){
			qry.setParameterList("userAccessLevelValues", userAccessLevelValues);
		}
		if(startDate != null && endDate != null){
			 qry.setDate("startDate", startDate);
			 qry.setDate("endDate", endDate);
		}
		if(committeeEnrollmentYearsIdsLst != null && committeeEnrollmentYearsIdsLst.size()>0){
			qry.setParameterList("committeeEnrollmentYearsIdsLst", committeeEnrollmentYearsIdsLst);
		}
		if(bothIds != null && bothIds.size()>0){
			qry.setParameterList("bothIds", bothIds);
		}
		return (Long) qry.uniqueResult();
	}
	
	
	public List<Object[]> getBoothInchargeCountDetails(Long userAccessLevelId ,Set<Long> userAccessLevelValues,List<Long> boothCommEnrollYrIds,Date startDate,Date endDate){
		
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append(" select model.booth.boothId,model.tdpCadre.tdpCadreId,model.tdpCadre.gender  from BoothIncharge model " +
				" where  model.isActive ='Y' and model.isDeleted='N' " +
				" and model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.booth.publicationDate.publicationDateId = :publicationDate "); 
	
		
		if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
			   queryStr.append(" and model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.state.stateId in (:userAccessLevelValues)");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			   queryStr.append(" and model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.district.districtId in (:userAccessLevelValues)");  
			 }/*else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
		        queryStr.append(" and model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
			 }*/else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
		        queryStr.append(" and model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.constituency.constituencyId in (:userAccessLevelValues) ");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
			    queryStr.append(" and model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.tehsil.tehsilId in (:userAccessLevelValues)");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
			    queryStr.append(" and model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
			    queryStr.append(" and model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.panchayat.panchayatId in (:userAccessLevelValues)"); 
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
			    queryStr.append(" and model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.ward.constituencyId in (:userAccessLevelValues)"); 
			 }
		
		if(boothCommEnrollYrIds !=null && boothCommEnrollYrIds.size() > 0){
			 queryStr.append(" and model.boothInchargeEnrollment.boothInchargeEnrollmentId in (:boothCommEnrollYrIds) " );
		}
		
		if(startDate != null && endDate != null){
			queryStr.append(" and date(model.insertedTime) between :startDate and :endDate ");
		}
		Query qry = getSession().createQuery(queryStr.toString());	
		
		if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
			qry.setParameterList("userAccessLevelValues", userAccessLevelValues);
		 }
		qry.setParameter("publicationDate", IConstants.VOTER_DATA_PUBLICATION_ID);
		if(boothCommEnrollYrIds !=null && boothCommEnrollYrIds.size() > 0){
			qry.setParameterList("boothCommEnrollYrIds", boothCommEnrollYrIds);
		}
		
		if(startDate != null && endDate != null){
			qry.setDate("startDate", startDate);
			qry.setDate("endDate", endDate);
		}
		return qry.list();
	}

	public List<Object[]> getBoothInchargeCountByRoleIds(Set<Long> roleIds,List<Long> boothEnrollmentYrIds){
		
		StringBuilder sb = new StringBuilder();
		sb.append( " select model.boothInchargeRoleConditionMappingId, count(model.boothInchargeId) from BoothIncharge model " +
				"where model.isDeleted='N' and model.isActive='Y'  " );
		
		if(roleIds != null && roleIds.size() >0)
			sb.append(" and model.boothInchargeRoleConditionMapping.boothInchargeRoleConditionMappingId in (:roleIds) ");
		if(boothEnrollmentYrIds != null && boothEnrollmentYrIds.size() >0)
			sb.append(" and model.boothInchargeEnrollment.boothInchargeEnrollmentId in (:boothEnrollmentYrIds) ");
				sb.append("group by model.boothInchargeRoleConditionMappingId " ); 
		
		Query query=getSession().createQuery(sb.toString());
		
		if(roleIds != null && roleIds.size() >0)
			query.setParameterList("roleIds", roleIds);
		if(boothEnrollmentYrIds != null && boothEnrollmentYrIds.size() >0)
			query.setParameterList("boothEnrollmentYrIds", boothEnrollmentYrIds);
		
		return query.list();
	}
public List<Long> checkIsBoothAlreadySaved(Long boothId,Long boothInchrgRoleId,List<Long> boothEnrollmentYrIds){
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.boothInchargeId from BoothIncharge model where  model.isDeleted='N' and model.isActive='Y'  " );
		
		if(boothId != null && boothId.longValue() >0l)
			sb.append(" and model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.booth.boothId = :boothId ");
		if(boothInchrgRoleId != null && boothInchrgRoleId.longValue() >0l)
			sb.append("and  model.boothInchargeRoleConditionMapping.boothInchargeRoleConditionMappingId = :boothInchrgRoleId ");
		if(boothEnrollmentYrIds != null && boothEnrollmentYrIds.size() >0)
			sb.append(" and  model.boothInchargeEnrollment.boothInchargeEnrollmentId in (:boothEnrollmentYrIds) ");
		Query query=getSession().createQuery(sb.toString());
		
		if(boothId != null && boothId.longValue() >0l)
			query.setParameter("boothId", boothId);
		if(boothInchrgRoleId != null && boothInchrgRoleId.longValue() >0l)
			query.setParameter("boothInchrgRoleId", boothInchrgRoleId);
		if(boothEnrollmentYrIds != null && boothEnrollmentYrIds.size() >0)
			query.setParameterList("boothEnrollmentYrIds", boothEnrollmentYrIds);
		
		return query.list();
	}
	public List<Object[]> getAddedMemberInBoothRoleWise(Long boothId,Long boothInchargeEnrollmentId) {
		 StringBuilder queryStr = new StringBuilder();
		 queryStr.append(" select " +
		 				 " model.boothInchargeRoleConditionMapping.boothInchargeRoleCondition.boothInchargeRole.boothInchargeRoleId," +
		 				 " model.boothInchargeRoleConditionMapping.boothInchargeRoleCondition.boothInchargeRole.roleName," +
		 				 " count(distinct model.tdpCadreId) " +
		 				 " from BoothIncharge model " +
		 				 " where " +
		 				 " model.isDeleted='N' and  model.boothInchargeRoleConditionMapping.isDeleted='N' and model.isActive ='Y'" +
		 				 " and model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.booth.boothId=:boothId " +
		 				 " and model.boothInchargeRoleConditionMapping.boothInchargeEnrollmentId=:boothInchargeEnrollmentId " +
		 				 " group by " +
		 				 " model.boothInchargeRoleConditionMapping.boothInchargeRoleCondition.boothInchargeRole.boothInchargeRoleId ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("boothId", boothId);
		query.setParameter("boothInchargeEnrollmentId", boothInchargeEnrollmentId);
		return query.list();
	}
	
public List<Object[]> gettingBoothInchargeFinalCount(Long boothId,Long boothInchargeEnrollmentId,Long locationValue){
		
		StringBuilder sb = new StringBuilder();
		sb.append( " select model.boothInchargeRoleConditionMapping.boothInchargeRoleConditionMappingId," +
				" count(model.boothInchargeId) from BoothIncharge model where " ); 
		
		if(boothId != null && boothId.longValue() >0l)
			sb.append( "  model.boothInchargeRoleConditionMapping.boothInchargeCommittee.booth.boothId = :boothId " );
		if(boothInchargeEnrollmentId != null && boothInchargeEnrollmentId.longValue() >0l)
			sb.append( " and model.boothInchargeEnrollment.boothInchargeEnrollmentId = :boothInchargeEnrollmentId " );
		if(locationValue != null && locationValue.longValue() >0l){
			sb.append( " and model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.constituency.constituencyId = :locationValue ");
		}
		sb.append( " and  model.isDeleted='N' and model.isActive='Y'   ");
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
public List<Object[]> getBoothInchargeRangeIds(Long boothId,Long boothInchrgRoleId,List<Long> boothEnrollmentYrIds){
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.boothInchargeId,model2.serialNo from BoothIncharge model " +
				"left join model.tdpCadre.voter voter , BoothPublicationVoter model2 where " +
				"  model.isDeleted='N' and model.isActive='Y'  and model2.voter.voterId=voter.voterId " );
		
		if(boothId != null && boothId.longValue() >0l)
			sb.append(" and model2.booth.boothId = :boothId ");
		if(boothId != null && boothId.longValue() >0l)
			sb.append("and  model.boothInchargeRoleConditionMapping.boothInchargeRoleConditionMappingId = :boothInchrgRoleId ");
		if(boothEnrollmentYrIds != null && boothEnrollmentYrIds.size() >0)
			sb.append(" and  model.boothInchargeEnrollment.boothInchargeEnrollmentId in (:boothEnrollmentYrIds) ");
		Query query=getSession().createQuery(sb.toString());
		
		if(boothId != null && boothId.longValue() >0l)
			query.setParameter("boothId", boothId);
		if(boothInchrgRoleId != null && boothInchrgRoleId.longValue() >0l)
			query.setParameter("boothInchrgRoleId", boothInchrgRoleId);
		if(boothEnrollmentYrIds != null && boothEnrollmentYrIds.size() >0)
			query.setParameterList("boothEnrollmentYrIds", boothEnrollmentYrIds);
		
		return query.list();
	}
	public List<Object[]> getLocationSerialNoRangeWiseVoterCount(InputVO inputVO) {

		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select ");
		if(inputVO.getLocationLevel().equalsIgnoreCase(IConstants.STATE)){
			queryStr.append(" model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.state.stateId ");
		}else if (inputVO.getLocationLevel().equalsIgnoreCase(IConstants.DISTRICT)) {
			queryStr.append(" model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.district.districtId ");
		} else if (inputVO.getLocationLevel().equalsIgnoreCase(IConstants.PARLIAMENT_CONSTITUENCY)) {
			queryStr.append(" model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.parliamentConstituency.constituencyId ");
		} else if (inputVO.getLocationLevel().equalsIgnoreCase(IConstants.CONSTITUENCY)) {
			queryStr.append(" model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.constituency.constituencyId ");
		} else if(inputVO.getLocationLevel().equalsIgnoreCase(IConstants.LOCALELECTIONBODY)){
			queryStr.append(" model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.localElectionBody.localElectionBodyId ");
		} else if (inputVO.getLocationLevel().equalsIgnoreCase(IConstants.TEHSIL)) {
			queryStr.append(" model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.tehsil.tehsilId ");
		} else if (inputVO.getLocationLevel().equalsIgnoreCase(IConstants.PANCHAYAT)) {
			queryStr.append(" model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.panchayat.panchayatId ");
		}
		queryStr.append(",model.boothInchargeSerialNoRange.boothInchargeSerialNoRangeId ");
		queryStr.append(",count(distinct model.tdpCadreId)");
		queryStr.append(" from BoothIncharge model ");
		queryStr.append(" where model.isDeleted='N' and model.isActive='Y' and model.boothInchargeSerialNoRange.isDeleted='N' and model.boothInchargeRoleConditionMapping.boothInchargeCommittee.isDeleted='N' ");
         
		if (inputVO.getFilterLevel().length() > 0 && inputVO.getFilterValueList() != null && inputVO.getFilterValueList().size() > 0) {

			if(inputVO.getFilterLevel().equalsIgnoreCase(IConstants.STATE)){
				queryStr.append(" and model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.state.stateId in(:filterValues) ");
			}else if (inputVO.getFilterLevel().equalsIgnoreCase(IConstants.DISTRICT)) {
				queryStr.append(" and model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.district.districtId in(:filterValues) ");
			} else if (inputVO.getFilterLevel().equalsIgnoreCase(IConstants.PARLIAMENT_CONSTITUENCY)) {
				queryStr.append(" and model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.parliamentConstituency.constituencyId in(:filterValues) ");
			} else if (inputVO.getFilterLevel().equalsIgnoreCase(IConstants.CONSTITUENCY)) {
				queryStr.append(" and model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.constituency.constituencyId in(:filterValues)");
			} else if(inputVO.getFilterLevel().equalsIgnoreCase(IConstants.LOCALELECTIONBODY)){
				queryStr.append(" and model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.localElectionBody.localElectionBodyId in(:filterValues) ");
			} else if (inputVO.getFilterLevel().equalsIgnoreCase(IConstants.TEHSIL)) {
				queryStr.append(" and model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.tehsil.tehsilId in(:filterValues)");
			} else if (inputVO.getFilterLevel().equalsIgnoreCase(IConstants.PANCHAYAT)) {
				queryStr.append(" and model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.panchayat.panchayatId in(:filterValues)");
			}
		}
        
		if (inputVO.getFromDate() != null && inputVO.getToDate() != null) {
			queryStr.append(" and date(model.updatedTime) between :fromDate and :toDate ");
		}
		if(inputVO.getBoothRoleIds() != null && inputVO.getBoothRoleIds().size() > 0){
			queryStr.append(" and model.boothInchargeRoleConditionMapping.boothInchargeRoleCondition.boothInchargeRole.boothInchargeRoleId in(:boothRoleIds)");
		}
		if (inputVO.getBoothInchargeEnrollmentId() != null && inputVO.getBoothInchargeEnrollmentId().longValue() > 0) {
			queryStr.append(" and model.boothInchargeEnrollmentId =:boothInchargeEnrollmentId ");
		}
		queryStr.append(" group by ");
		if (inputVO.getLocationLevel().equalsIgnoreCase(IConstants.STATE)) {
			queryStr.append(" model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.state.stateId ");
		}else if (inputVO.getLocationLevel().equalsIgnoreCase(IConstants.DISTRICT)) {
			queryStr.append(" model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.district.districtId ");
		} else if (inputVO.getLocationLevel().equalsIgnoreCase(IConstants.PARLIAMENT_CONSTITUENCY)) {
			queryStr.append(" model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.parliamentConstituency.constituencyId ");
		} else if (inputVO.getLocationLevel().equalsIgnoreCase(IConstants.CONSTITUENCY)) {
			queryStr.append(" model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.constituency.constituencyId ");
		} else if(inputVO.getLocationLevel().equalsIgnoreCase(IConstants.LOCALELECTIONBODY)){
			queryStr.append(" model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.localElectionBody.localElectionBodyId ");
		} else if (inputVO.getLocationLevel().equalsIgnoreCase(IConstants.TEHSIL)) {
			queryStr.append(" model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.tehsil.tehsilId ");
		} else if (inputVO.getLocationLevel().equalsIgnoreCase(IConstants.PANCHAYAT)) {
			queryStr.append(" model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.panchayat.panchayatId ");
		}
		queryStr.append(",model.boothInchargeSerialNoRange.boothInchargeSerialNoRangeId ");
		Query query = getSession().createQuery(queryStr.toString());

		if (inputVO.getFromDate() != null && inputVO.getToDate() != null) {
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
	public List<Object[]> getLocationLevelWseCadreDetails(InputVO inputVO,String type) {

		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct ");
        queryStr.append(" model1.booth.boothId,model1.booth.partNo,model1.isConfirmed,model1.startDate,model1.completedDate");
		queryStr.append(",state.stateId,state.stateName,district.districtId,district.districtName,parliamentConstituency.constituencyId,parliamentConstituency.name");
		queryStr.append(",constituency.constituencyId,constituency.name,tehsil.tehsilId,tehsil.tehsilName,panc.panchayatId,panc.panchayatName ");
		queryStr.append(",localElectionBody.localElectionBodyId,localElectionBody.name,electionType.electionTypeId,electionType.electionType ");
		
		queryStr.append(",model.tdpCadre.tdpCadreId,model.tdpCadre.firstname,model.tdpCadre.lastname,model.tdpCadre.image ");
		queryStr.append(",model.tdpCadre.mobileNo,model.tdpCadre.memberShipNo,model2.serialNo" +
				        ",model.boothInchargeSerialNoRange.boothInchargeSerialNoRangeId,model.boothInchargeSerialNoRange.range ");

		queryStr.append(" from BoothIncharge model ");
		queryStr.append(" left join model.boothInchargeRoleConditionMapping.boothInchargeCommittee model1 ");
		queryStr.append(" left join model1.address.state state ");
		queryStr.append(" left join model1.address.district district ");
		queryStr.append(" left join model1.address.parliamentConstituency parliamentConstituency ");
		queryStr.append(" left join model1.address.constituency constituency ");
		queryStr.append(" left join model1.address.tehsil tehsil ");
		queryStr.append(" left join model1.address.localElectionBody localElectionBody ");
		queryStr.append(" left join model1.address.localElectionBody.electionType electionType ");
		queryStr.append(" left join model1.address.panchayat panc,BoothPublicationVoter model2 ");
		
		if(type.equalsIgnoreCase("voter")){
			queryStr.append("where model2.voter.voterId = model.tdpCadre.voterId ");
		}else if(type.equalsIgnoreCase("familyVoter")){
			queryStr.append("where model2.voter.voterId = model.tdpCadre.familyVoterId ");
		}  
		queryStr.append(" and model2.boothId=model1.booth.boothId");
		queryStr.append(" and model1.booth.publicationDate.publicationDateId=:publicationDateId ");

		queryStr.append(" and model.isDeleted='N' and model.boothInchargeRoleConditionMapping.isDeleted ='N' and model1.isDeleted='N' and model.isActive = 'Y' ");
		
		if(inputVO.getBoothRoleIds() != null && inputVO.getBoothRoleIds().size() > 0){
			queryStr.append(" and model.boothInchargeRoleConditionMapping.boothInchargeRoleCondition.boothInchargeRole.boothInchargeRoleId in(:boothRoleIds)");
		}
		if(inputVO.getSerialRangeId() != null && inputVO.getSerialRangeId().longValue() > 0){
			queryStr.append(" and model.boothInchargeSerialNoRange.boothInchargeSerialNoRangeId=:serialRangeId");
		}
		if(inputVO.getBoothId() != null && inputVO.getBoothId().longValue() > 0){
			queryStr.append(" and model1.booth.boothId=:boothId");
		}
		if (inputVO.getFromDate() != null && inputVO.getToDate() != null) {
			queryStr.append(" and date(model.updatedTime) between :fromDate and :toDate ");
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
		
		if (inputVO.getBoothInchargeEnrollmentId() != null && inputVO.getBoothInchargeEnrollmentId().longValue() > 0) {
			queryStr.append(" and model1.boothInchargeEnrollmentId =:boothInchargeEnrollmentId ");
		}
		
		Query query = getSession().createQuery(queryStr.toString());

		if (inputVO.getFromDate() != null && inputVO.getToDate() != null) {
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
		if(inputVO.getSerialRangeId() != null && inputVO.getSerialRangeId().longValue() > 0){
			query.setParameter("serialRangeId",inputVO.getSerialRangeId());
		}
		if(inputVO.getBoothId() != null && inputVO.getBoothId().longValue() > 0){
			query.setParameter("boothId",inputVO.getBoothId());
		}
		query.setParameter("publicationDateId", IConstants.BOOTH_INCHARGE_COMMITTEE_PUBLICATION_DATE_ID);
		
		return query.list();
	}
}
