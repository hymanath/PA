package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IBoothInchargeDAO;
import com.itgrids.partyanalyst.dto.InputVO;
import com.itgrids.partyanalyst.dto.CommitteeInputVO;
import com.itgrids.partyanalyst.model.BoothIncharge;
import com.itgrids.partyanalyst.utils.IConstants;

public class BoothInchargeDAO extends GenericDaoHibernate<BoothIncharge, Long> implements IBoothInchargeDAO{

	public BoothInchargeDAO() {
		super(BoothIncharge.class);
	}
	
	public List<Object[]> getBoothUserDetails(Long constituencyId, Long mandalId, Long boothId,String cadreType){
		String tempId = mandalId.toString().substring(0,1);
		StringBuilder query = new StringBuilder("select distinct booth.partNo," +
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
					" bpv.serialNo, ward.name ,localElectionBody.name, model.boothInchargeRoleConditionMapping.boothInchargeCommittee.isConfirmed," +
					" bpv.booth.partNo,   " +
					" model.tdpCadre.relativename," +
					" model.tdpCadre.age " +
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
					//" where  booth.boothId = bpv.booth.boothId and ");
					" where  model.isActive='Y'  ");
		if(cadreType != null && !cadreType.isEmpty()){
			if(cadreType.trim().equalsIgnoreCase("familyVoterId"))
				query.append(" and bpv.voter.voterId = model.tdpCadre.familyVoterId ");
			else 
				query.append(" and bpv.voter.voterId = model.tdpCadre.voterId ");
		}
		query.append("   and  model.isDeleted='N'  and model.tdpCadre.isDeleted='N' and model.tdpCadre.enrollmentYear=2014 and " +
					   " booth.publicationDate.publicationDateId = :publicationDate and bpv.booth.publicationDate.publicationDateId = :publicationDate ");
		if(constituencyId !=null && constituencyId.longValue() > 0)
			query.append(" and constituency.constituencyId=:constituencyId");
		if(mandalId !=null && mandalId.longValue() > 0){
			if(tempId.trim().equalsIgnoreCase("1"))
				query.append(" and localElectionBody.localElectionBodyId=:mandalId  ");
			else if(tempId.trim().equalsIgnoreCase("2"))
				query.append(" and tehsil.tehsilId=:mandalId and localElectionBody.localElectionBodyId is null ");
		}
		if(boothId !=null && boothId.longValue() > 0)
			query.append(" and booth.boothId=:boothId");
		query.append(" group by model.tdpCadre.tdpCadreId ");
		Query query1=getSession().createQuery(query.toString());
		query1.setParameter("publicationDate", IConstants.BOOTH_INCHARGE_COMMITTEE_PUBLICATION_DATE_ID);
		if(constituencyId !=null && constituencyId.longValue() > 0){
			query1.setParameter("constituencyId", constituencyId);
		}
		if(mandalId !=null && mandalId.longValue() > 0){
			query1.setParameter("mandalId", Long.valueOf(mandalId.toString().substring(1,mandalId.toString().trim().length())));
		}
		if(boothId !=null && boothId.longValue() > 0){
			query1.setParameter("boothId", boothId);
		}
		return query1.list();
	}
	
	public List<Object[]> getCadreIdsForLocation(List<Long> boothIdsList,Long roleId){
		StringBuilder sb = new StringBuilder();
		sb.append("select " +
				" model.tdpCadre.tdpCadreId," +
				" b.partNo, " +
				" p.panchayatName, " +
				" t.tehsilId, " +
				" t.tehsilName, " +
				" l.localElectionBodyId, " +
				" l.name, model.boothInchargeRoleConditionMapping.boothInchargeRoleCondition.boothInchargeRole.roleName," +
				" model.tdpCadre.voterId, model.tdpCadre.familyVoterId , model.addedBoothInhcargeConditionId " +
				" from BoothIncharge model " +
				" left join model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.booth b " +
				" left join model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.panchayat p " +
				" left join model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.tehsil t " +
				" left join model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.localElectionBody l " +
				" where model.isActive = 'Y' and model.isDeleted = 'N' ");
		if(roleId != null && roleId.longValue()>0L)
			sb.append(" and model.boothInchargeRoleConditionMapping.boothInchargeRoleCondition.boothInchargeRoleId not in (:roleId)  ");
		if(boothIdsList != null && boothIdsList.size() > 0)
		{ 
			sb.append(" and model.boothInchargeRoleConditionMapping.boothInchargeCommittee.boothId in (:boothIdsList)");
		}
		
		Query query = getSession().createQuery(sb.toString());
		if(boothIdsList != null && boothIdsList.size() > 0l) 
			query.setParameterList("boothIdsList", boothIdsList);
		if(roleId != null && roleId.longValue()>0L)
			query.setParameter("roleId", roleId);
		return query.list();
	}
	public BoothIncharge getExistingMember(Long tdpCadreId,String type){
		StringBuilder sb = new StringBuilder();
		sb.append("select model" +
				" from BoothIncharge model" );
		
		if(type != null && type.trim().equalsIgnoreCase("removeOption"))
			sb.append(" where model.isActive = 'Y' and model.isDeleted = 'N'");
		else if(type != null && type.trim().equalsIgnoreCase("addOption"))
			sb.append(" where model.isActive = 'N' and model.isDeleted = 'Y'");
		
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
   public Long getBoothAssignInchargeCount(String searchType , Long userAccessLevelId,Set<Long> userAccessLevelValues,Date startDate,Date endDate,List<Long> committeeEnrollmentYearsIdsLst,List<Long> bothIds){
		
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append(" select  count(distinct model.boothId) " +
				  " from BoothInchargeCommittee model  " +
				  " where " +
				  " model.booth.publicationDate.publicationDateId = :publicationDate " +
				  " and model.isDeleted = 'N' ");
        if(bothIds != null && bothIds.size()>0){
            queryStr.append(" and model.address.boothId in (:bothIds) ");
       }
				if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
			         queryStr.append(" and model.address.state.stateId in (:userAccessLevelValues)");  
				}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			         queryStr.append(" and model.address.district.districtId in (:userAccessLevelValues)");  
				}/*else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
		            queryStr.append(" and model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
		       	}*/else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
		    	   queryStr.append(" and model.address.constituency.constituencyId in (:userAccessLevelValues) ");  
		       	}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
		       		queryStr.append(" and model.address.tehsil.tehsilId in (:userAccessLevelValues)");  
		       	}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
		       		queryStr.append(" and model.address.localBody.localElectionBodyId in (:userAccessLevelValues)"); 
		       	}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
		       		queryStr.append(" and model.address.panchayat.panchayatId in (:userAccessLevelValues)"); 
		       	}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
		       		queryStr.append(" and model.address.ward.constituencyId in (:userAccessLevelValues)"); 
		       	}
				
				if(searchType != null && searchType.trim().length()>0L){
					if(searchType.trim().equalsIgnoreCase("started")){
						queryStr.append(" and model.isConfirmed ='N' and model.startDate is not null and model.completedDate is null");
						if(startDate != null && endDate != null){
							queryStr.append(" and date(model.startDate) between :startDate and :endDate ");
						}
					}
					else if(searchType.trim().equalsIgnoreCase("completed")){
						queryStr.append(" and model.isConfirmed ='Y' and model.startDate is not null and model.completedDate is not null");
						if(startDate != null && endDate != null){
							queryStr.append(" and date(model.completedDate) between :startDate and :endDate ");
						}
					}
				}
				
				if(committeeEnrollmentYearsIdsLst != null && committeeEnrollmentYearsIdsLst.size()>0){
					queryStr.append(" and model.boothInchargeEnrollment.boothInchargeEnrollmentId in(:committeeEnrollmentYearsIdsLst) ");
				}
	 	  Query qry = getSession().createQuery(queryStr.toString());
		
			qry.setParameter("publicationDate", IConstants.BOOTH_INCHARGE_COMMITTEE_PUBLICATION_DATE_ID);
		if(userAccessLevelValues != null && userAccessLevelValues.size()>0){
			qry.setParameterList("userAccessLevelValues", userAccessLevelValues);
		}
		if(searchType != null && searchType.trim().length()>0L && !searchType.trim().equalsIgnoreCase("total")){
			if(startDate != null && endDate != null){
				 qry.setDate("startDate", startDate);
				 qry.setDate("endDate", endDate);
			}
		}
		
		if(committeeEnrollmentYearsIdsLst != null && committeeEnrollmentYearsIdsLst.size()>0){
			qry.setParameterList("committeeEnrollmentYearsIdsLst", committeeEnrollmentYearsIdsLst);
		}
		if(bothIds != null && bothIds.size()>0){
			qry.setParameterList("bothIds", bothIds);
		}
		return (Long) qry.uniqueResult();
	}
	
   public List<Object[]> getLocationWiseCommitteesCountByLocIds(CommitteeInputVO committeeBO){
		
		StringBuilder sbS = new StringBuilder();
		StringBuilder sbM = new StringBuilder();
		StringBuilder sbE = new StringBuilder();
		
		sbS.append(" select count(distinct model.address.booth.boothId) ");
		sbM.append(" from  BoothInchargeCommittee model ");
				
		sbM.append(" where model.isDeleted='N' and " +
				" model.booth.publicationDate.publicationDateId =:publicationDateId  ");
		if(committeeBO.getEnrollmentYearList() != null && committeeBO.getEnrollmentYearList().size()>0){
			sbM.append(" and model.boothInchargeEnrollmentId in (:tdpCommitteeEnrollmentId) ");
		}
		if(committeeBO.getStatus() != null && committeeBO.getStatus().trim().equalsIgnoreCase("notstarted")){
			//sbM.append(" and model.isConfirmed ='N' and model.startDate is null and model.completedDate is null ");
		}
		if(committeeBO.getStatus() != null && committeeBO.getStatus().trim().equalsIgnoreCase("started")){
			sbM.append(" and model.isConfirmed ='N' and model.startDate is not null and model.completedDate is null ");
			if(committeeBO.getStartDate() != null && committeeBO.getEndDate() != null){
				sbM.append(" and date(model.startDate) between :startDate and :endDate ");
			}
		}
		else if(committeeBO.getStatus() != null && committeeBO.getStatus().trim().equalsIgnoreCase("completed")){
			sbM.append(" and model.isConfirmed ='Y' and model.startDate is not null and model.completedDate is not null ");
			if(committeeBO.getStartDate() != null &&  committeeBO.getEndDate()  != null){
				sbM.append(" and date(model.completedDate) between :startDate and :endDate ");
			}
		}
		
		if(committeeBO.getStateIds()!=null && committeeBO.getStateIds().size()>0){
			sbS.append(",model.address.booth.constituency.state.stateId ");
			sbE.append(" group by model.address.booth.constituency.state.stateId ");
		}
		else if(committeeBO.getDistrictIds() != null && committeeBO.getDistrictIds().size()>0){
			
			sbS.append(",model.address.booth.constituency.district.districtId ");
			sbE.append(" group by model.address.booth.constituency.district.districtId ");
			
		}else if(committeeBO.getParliamentConstIds() != null && committeeBO.getParliamentConstIds().size()>0){
			
			sbS.append(",model.address.parliamentConstituency.constituencyId ");
			sbE.append(" group by model.address.parliamentConstituency.constituencyId ");
			
		}else if(committeeBO.getAssemblyConstIds() != null && committeeBO.getAssemblyConstIds().size()>0){
			
			sbS.append(",model.address.booth.constituency.constituencyId");
			sbE.append(" group by model.address.booth.constituency.constituencyId ");
			
		}else if(committeeBO.getTehsilIds()!= null && committeeBO.getTehsilIds().size()>0){
			
			sbS.append(",model.address.booth.tehsil.tehsilId ");
			sbE.append(" group by model.address.booth.tehsil.tehsilId ");
		}
		
		if(committeeBO.getStateId()!= null && committeeBO.getStateId() > 0l ){
			sbM.append(" and model.address.booth.constituency.state.stateId = :stateId ");
		}
		
		StringBuilder sbf = new StringBuilder().append(sbS).append(sbM).append(sbE);
		Query query = getSession().createQuery(sbf.toString());
		if(committeeBO.getStatus() != null && !committeeBO.getStatus().trim().equalsIgnoreCase("total")  
				&& !committeeBO.getStatus().trim().equalsIgnoreCase("notstarted")){
			if(committeeBO.getStartDate() != null && committeeBO.getEndDate() != null){
				query.setDate("startDate",committeeBO.getStartDate());
				query.setDate("endDate",committeeBO.getEndDate());
			}
		}
		if(committeeBO.getStateId()!= null && committeeBO.getStateId() > 0l ){
			query.setParameter("stateId",committeeBO.getStateId());
		}
		if(committeeBO.getEnrollmentYearList() != null && committeeBO.getEnrollmentYearList().size() > 0){
			query.setParameterList("tdpCommitteeEnrollmentId",committeeBO.getEnrollmentYearList());
		}
		query.setParameter("publicationDateId",IConstants.BOOTH_INCHARGE_COMMITTEE_PUBLICATION_DATE_ID);
		return query.list();
	}
	
	public List<Object[]> getBoothInchargeCountDetails(Long userAccessLevelId ,Set<Long> userAccessLevelValues,List<Long> boothCommEnrollYrIds,Date startDate,Date endDate){
		
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append(" select model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.booth.boothId,model.tdpCadre.tdpCadreId,model.tdpCadre.gender  from BoothIncharge model " +
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
		qry.setParameter("publicationDate", IConstants.BOOTH_INCHARGE_COMMITTEE_PUBLICATION_DATE_ID);
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
	//	if(boothInchrgRoleId != null && boothInchrgRoleId.longValue() >0l)
	//		sb.append("and  model.boothInchargeRoleConditionMapping.boothInchargeRoleConditionMappingId = :boothInchrgRoleId ");
		if(boothEnrollmentYrIds != null && boothEnrollmentYrIds.size() >0)
			sb.append(" and  model.boothInchargeEnrollment.boothInchargeEnrollmentId in (:boothEnrollmentYrIds) ");
		Query query=getSession().createQuery(sb.toString());
		
		if(boothId != null && boothId.longValue() >0l)
			query.setParameter("boothId", boothId);
		//if(boothInchrgRoleId != null && boothInchrgRoleId.longValue() >0l)
		//	query.setParameter("boothInchrgRoleId", boothInchrgRoleId);
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
				"  model.isDeleted='N' and model.isActive='Y'  and model2.voter.voterId=voter.voterId and " +
				" model.boothInchargeRoleConditionMapping.boothInchargeCommittee.booth.publicationDate.publicationDateId=:publicationDateId " +
				" and model2.booth.publicationDate.publicationDateId = :publicationDateId " );
		
		if(boothId != null && boothId.longValue() >0l)
			sb.append(" and model2.booth.boothId = :boothId ");
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
		query.setParameter("publicationDateId", IConstants.BOOTH_INCHARGE_COMMITTEE_PUBLICATION_DATE_ID);
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
			queryStr.append(" model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.constituency.constituencyId,model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.localElectionBody.localElectionBodyId ");
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
				queryStr.append(" and model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.tehsil.tehsilId in(:filterValues)  ");
			} else if (inputVO.getFilterLevel().equalsIgnoreCase(IConstants.PANCHAYAT)) {
				queryStr.append(" and model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.panchayat.panchayatId in(:filterValues)");
			}
		}
		if (inputVO.getLocationLevel().equalsIgnoreCase(IConstants.TEHSIL)) {
			queryStr.append(" and model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.localElectionBody.localElectionBodyId is null ");
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
			queryStr.append(" model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.constituency.constituencyId," +
					       "  model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.constituency.constituencyId ");
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
				        ",model.boothInchargeSerialNoRange.boothInchargeSerialNoRangeId,model.boothInchargeSerialNoRange.range,model2.booth.partNo," +
				        " model.boothInchargeRoleConditionMapping.boothInchargeRoleCondition.boothInchargeRole.roleName  ");

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
		//queryStr.append(" and model2.boothId=model1.booth.boothId");
		queryStr.append(" and model1.booth.publicationDate.publicationDateId=:publicationDateId and model2.booth.publicationDate.publicationDateId = :publicationDateId  ");

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
				queryStr.append(" and tehsil.tehsilId in(:filterValues) and localElectionBody.localElectionBodyId is null");
			} else if (inputVO.getFilterLevel().equalsIgnoreCase(IConstants.PANCHAYAT)) {
				queryStr.append(" and panc.panchayatId in(:filterValues)");
			}
		}
		
		if (inputVO.getBoothInchargeEnrollmentId() != null && inputVO.getBoothInchargeEnrollmentId().longValue() > 0) {
			queryStr.append(" and model1.boothInchargeEnrollmentId =:boothInchargeEnrollmentId ");
		}
		queryStr.append(" group by  model.tdpCadre.tdpCadreId ");
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
	public Long getRoleWiseTotalAddedMember(Long boothId,Long boothInchargeEnrollmentId,Long boothInchargeRoleId) {
		 StringBuilder queryStr = new StringBuilder();
		 queryStr.append(" select " +
		 				 " count(distinct model.tdpCadreId) " +
		 				 " from BoothIncharge model " +
		 				 " where " +
		 				 " model.isDeleted='N' and  model.boothInchargeRoleConditionMapping.isDeleted='N' and model.isActive ='Y'" +
		 				 " and model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.booth.boothId=:boothId " +
		 				 " and model.boothInchargeRoleConditionMapping.boothInchargeEnrollmentId=:boothInchargeEnrollmentId " +
		 				 " and model.boothInchargeRoleConditionMapping.boothInchargeRoleCondition.boothInchargeRole.boothInchargeRoleId=:boothInchargeRoleId");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("boothId", boothId);
		query.setParameter("boothInchargeEnrollmentId", boothInchargeEnrollmentId);
		query.setParameter("boothInchargeRoleId", boothInchargeRoleId);
		return (Long)query.uniqueResult();
	}

	public List<Object[]> levelWiseBasicCommitteesCount(CommitteeInputVO committeeBO) {
		

		StringBuilder str = new StringBuilder();
        
		str.append(" select 5,'boothIncharge'," +//1
				   "        1,'boothIncharge'," +//3
				   "        1,'boothIncharge'," +//5
				   "        count(distinct model.boothId)" +//6
				   " from   BoothInchargeCommittee model " +
				  "  where  model.isDeleted='N' and" +
				  "  model.isConfirmed ='N'and model.completedDate is null " );
				
		/*if(committeeBO.getBasicCommitteeIds() != null && committeeBO.getBasicCommitteeIds().size()>0){
			str.append(" and model.tdpBasicCommittee.tdpBasicCommitteeId in (:basicCommitteeIds) ");
		}*/
		
		//locations
		if(committeeBO.getStateIds()!=null && committeeBO.getStateIds().size()>0){
			str.append(" and model.address.booth.constituency.state.stateId in (:tdpCommitteeLevelValues) ");
		}else if(committeeBO.getDistrictIds() != null && committeeBO.getDistrictIds().size()>0){
			str.append(" and model.address.booth.constituency.district.districtId in (:tdpCommitteeLevelValues) ");
		}else if(committeeBO.getParliamentConstIds() != null && committeeBO.getParliamentConstIds().size()>0){
			//str.append(" and model.userAddress.parliamentConstituency.constituencyId in (:tdpCommitteeLevelValues) ");
		}else if(committeeBO.getAssemblyConstIds() != null && committeeBO.getAssemblyConstIds().size()>0){
			str.append(" and model.address.booth.constituency.constituencyId in (:tdpCommitteeLevelValues) ");
		}else if(committeeBO.getTehsilIds()!= null && committeeBO.getTehsilIds().size()>0){
			str.append(" and model.address.booth.tehsil.tehsilId in (:tdpCommitteeLevelValues) ");
		}
		
			
		if(committeeBO.getStateId()!= null && committeeBO.getStateId() > 0l ){
			str.append(" and model.address.booth.constituency.state.stateId = :stateId ");
		}
		if(committeeBO.getEnrollmentYearList() != null && committeeBO.getEnrollmentYearList().size()>0){
			str.append(" and model.boothInchargeEnrollmentId in (:tdpCommitteeEnrollmentId) ");
		}
	/*	if(committeeBO.getCommitteesQueryString()!=null && committeeBO.getCommitteesQueryString().length()>0){
			str.append(committeeBO.getCommitteesQueryString());
		}
		*/
		//str.append(" order by model.tdpBasicCommittee.tdpBasicCommitteeId ");

		Query query = getSession().createQuery(str.toString());
		
		/*if(committeeBO.getBasicCommitteeIds() != null && committeeBO.getBasicCommitteeIds().size()>0){
			query.setParameterList("basicCommitteeIds", committeeBO.getBasicCommitteeIds());
		}*/
		//query.setParameterList("tdpCommitteeLevelIds",committeeBO.getTdpCommitteeLevelIds()); 
		
		if(committeeBO.getStateIds()!=null && committeeBO.getStateIds().size()>0){
			
			query.setParameterList("tdpCommitteeLevelValues",committeeBO.getStateIds());
			
		}else if(committeeBO.getDistrictIds() != null && committeeBO.getDistrictIds().size()>0){
			
			query.setParameterList("tdpCommitteeLevelValues",committeeBO.getDistrictIds());
			
		}else if(committeeBO.getParliamentConstIds() != null && committeeBO.getParliamentConstIds().size()>0){
			
			query.setParameterList("tdpCommitteeLevelValues",committeeBO.getParliamentConstIds());
			
		}else if(committeeBO.getAssemblyConstIds() != null && committeeBO.getAssemblyConstIds().size()>0){
			
			query.setParameterList("tdpCommitteeLevelValues",committeeBO.getAssemblyConstIds());
			
		}else if(committeeBO.getTehsilIds()!= null && committeeBO.getTehsilIds().size()>0){
			
			query.setParameterList("tdpCommitteeLevelValues",committeeBO.getTehsilIds());
		}
		
		/*if(committeeBO.getStatus() != null && !committeeBO.getStatus().isEmpty()){
			if(committeeBO.getDate()!=null){
				query.setDate("givendate",committeeBO.getDate());
			}else if(committeeBO.getStartDate() != null && committeeBO.getEndDate() != null){
				query.setDate("insertedDate",committeeBO.getStartDate());
				query.setDate("updatedDate",committeeBO.getEndDate());
			}
		}*/
		
		if(committeeBO.getStateId()!= null && committeeBO.getStateId() > 0l ){
			query.setParameter("stateId",committeeBO.getStateId());
		}
		if(committeeBO.getEnrollmentYearList() != null && committeeBO.getEnrollmentYearList().size()>0){
			query.setParameterList("tdpCommitteeEnrollmentId",committeeBO.getEnrollmentYearList());
		}
		return query.list();
	
		
	}

	public List<Object[]> getTopPoorCommitteeLocations(CommitteeInputVO committeeBO) {
    	
    	StringBuilder sbS = new StringBuilder();
		StringBuilder sbM = new StringBuilder();
		StringBuilder sbE = new StringBuilder();
		
		sbS.append("select count(distinct model.address.booth.boothId)");//0
		
		if(committeeBO.getGroupingLocation().equalsIgnoreCase("State")){
			sbS.append(" ,model.address.booth.constituency.state.stateId,model.address.booth.constituency.state.stateName ");//2
			sbE.append("  group by model.address.booth.constituency.state.stateId ");
		}else if(committeeBO.getGroupingLocation().equalsIgnoreCase("District")){
			sbS.append(" ,model.address.booth.constituency.district.districtId,model.address.booth.constituency.district.districtName ");//2
			sbE.append("  group by model.address.booth.constituency.district.districtId ");
		}else if(committeeBO.getGroupingLocation().equalsIgnoreCase("Constituency")){
			sbS.append(" ,model.address.booth.constituency.constituencyId,model.address.booth.constituency.name ");//2
			sbE.append("  group by model.address.booth.constituency.constituencyId ");
		}else if(committeeBO.getGroupingLocation().equalsIgnoreCase("Mandal")){
			sbS.append(" ,model.address.booth.tehsil.tehsilId,model.address.booth.constituency.tehsil.tehsilName ");//2
			sbE.append("  group by model.address.booth.tehsil.tehsilId ");
		}else if(committeeBO.getGroupingLocation().equalsIgnoreCase("localElectionBody")){
			sbS.append(" ,model.address.booth.constituency.localElectionBody.localElectionBodyId,model.address.booth.constituency.localElectionBody.name " +//2
					   " ,model.address.booth.constituency.localElectionBody.electionType.electionTypeId,model.address.booth.constituency.localElectionBody.electionType.electionType ");//4
			sbE.append(" group by model.address.booth.constituency.localElectionBody.localElectionBodyId ");
		}else if(committeeBO.getGroupingLocation().equalsIgnoreCase("Village")){
			sbS.append(" ,model.address.booth.constituency.panchayat.panchayatId,model.address.booth.constituency.panchayat.panchayatName ");//2
			sbE.append(" group by model.address.booth.constituency.panchayat.panchayatId ");
		}else if(committeeBO.getGroupingLocation().equalsIgnoreCase("Ward")){
			sbS.append(" ,model.address.booth.constituency.ward.constituencyId,model.address.booth.constituency.ward.name ");//2
			sbE.append(" group by model.address.booth.constituency.ward.constituencyId ");
		}
		
		sbM.append(" from  BoothInchargeCommittee  model where model.boothInchargeCommitteeId is not null ");
		//locations related.
 		if(committeeBO.getQueryString() != null && committeeBO.getQueryString().length()>0){
 			//StringBuilder quercomm =  committeeBO.getQueryString();
 			sbM.append( "and model.address.booth.constituency.district.districtId in (:tdpCommitteeLevelValues) ");
 		}

 		if(committeeBO.getStateId()!= null && committeeBO.getStateId() > 0l ){
			sbM.append(" and model.address.booth.constituency.state.stateId = :stateId ");
		}
		if(committeeBO.getEnrollmentYearList() != null && committeeBO.getEnrollmentYearList().size() > 0){
			sbM.append(" and model.boothInchargeEnrollmentId in(:tdpCommitteeEnrollmentId) ");
		}
			
		if(committeeBO.getStatus() != null && committeeBO.getStatus().trim().equalsIgnoreCase("started")){
			sbM.append(" and model.isConfirmed ='N' and model.startDate is not null and model.completedDate is null ");
			if(committeeBO.getStartDate() != null && committeeBO.getEndDate() != null){
				sbM.append(" and date(model.startDate) between :startDate and :endDate ");
			}
		}
		else if(committeeBO.getStatus() != null && committeeBO.getStatus().trim().equalsIgnoreCase("completed")){
			sbM.append(" and model.isConfirmed ='Y' and model.startDate is not null and model.completedDate is not null ");
			if(committeeBO.getStartDate() != null &&  committeeBO.getEndDate()  != null){
				sbM.append(" and date(model.completedDate) between :startDate and :endDate ");
			}
		}
		StringBuilder sbf = new StringBuilder().append(sbS).append(sbM).append(sbE);
		Query query = getSession().createQuery(sbf.toString());
		
		if(committeeBO.getBasicCommitteeIds() != null && committeeBO.getBasicCommitteeIds().size() >0l){
			query.setParameterList("basicCommitteeIds", committeeBO.getBasicCommitteeIds());
		}
		
		if(committeeBO.getStateIds()!=null && committeeBO.getStateIds().size()>0){
			query.setParameterList("tdpCommitteeLevelValues",committeeBO.getStateIds());
		}
		else if(committeeBO.getDistrictIds() != null && committeeBO.getDistrictIds().size()>0){
			query.setParameterList("tdpCommitteeLevelValues",committeeBO.getDistrictIds());
		}else if(committeeBO.getParliamentConstIds() != null && committeeBO.getParliamentConstIds().size()>0){
			query.setParameterList("tdpCommitteeLevelValues",committeeBO.getParliamentConstIds());
		}else if(committeeBO.getAssemblyConstIds() != null && committeeBO.getAssemblyConstIds().size()>0){
			query.setParameterList("tdpCommitteeLevelValues",committeeBO.getAssemblyConstIds());
		}else if(committeeBO.getTehsilIds()!= null && committeeBO.getTehsilIds().size()>0){
			query.setParameterList("tdpCommitteeLevelValues",committeeBO.getTehsilIds());
		}
		
		/*if(committeeBO.getStatus() != null && !committeeBO.getStatus().isEmpty()){
 			if(committeeBO.getDate()!=null){
 				query.setDate("givendate",committeeBO.getDate());
 			}else if(committeeBO.getStartDate() != null && committeeBO.getEndDate() != null){
 				query.setDate("startDate",committeeBO.getStartDate());
 				query.setDate("endDate",committeeBO.getEndDate());
 			}
 		}*/
		if(committeeBO.getStateId()!= null && committeeBO.getStateId() > 0l ){
			query.setParameter("stateId",committeeBO.getStateId());
		}
		if(committeeBO.getEnrollmentYearList() != null && committeeBO.getEnrollmentYearList().size() > 0){
			query.setParameterList("tdpCommitteeEnrollmentId",committeeBO.getEnrollmentYearList());
		}
		if(committeeBO.getStatus() != null && !committeeBO.getStatus().trim().equalsIgnoreCase("total")  
				&& !committeeBO.getStatus().trim().equalsIgnoreCase("notstarted")){
			if(committeeBO.getStartDate() != null && committeeBO.getEndDate() != null){
				query.setDate("startDate",committeeBO.getStartDate());
				query.setDate("endDate",committeeBO.getEndDate());
			}
		}
		return query.list();
	}

	public List<Object[]> committeesPerformanceCohort(CommitteeInputVO committeeBO) {
    	
    	StringBuilder sbS = new StringBuilder();
		StringBuilder sbM = new StringBuilder();
		StringBuilder sbE = new StringBuilder();
		
		sbS.append("select count(distinct model.booth.boothId),1,'boothIncharge' ");//2
		sbE.append(" group by ");
		if(committeeBO.getGroupingLocation().equalsIgnoreCase("State")){
			sbS.append(" ,model.address.state.stateId,model.address.state.stateName ");//4
			sbE.append(" model.address.state.stateId ");
		}else if(committeeBO.getGroupingLocation().equalsIgnoreCase("District")){
			sbS.append(" ,model.address.district.districtId,model.address.district.districtName ");//4
			sbE.append(" model.address.district.districtId ");
		}else if(committeeBO.getGroupingLocation().equalsIgnoreCase("Parliament")){
			sbS.append(" ,model.address.parliamentConstituency.constituencyId,model.address.parliamentConstituency.name ");//4
			sbE.append(" ,model.address.parliamentConstituency.constituencyId ");
		}else if(committeeBO.getGroupingLocation().equalsIgnoreCase("Constituency")){
			sbS.append(" ,model.address.constituency.constituencyId,model.address.constituency.name ");//4
			sbE.append(" model.address.constituency.constituencyId ");
		}else if(committeeBO.getGroupingLocation().equalsIgnoreCase("Mandal")){
			sbS.append(" ,model.address.tehsil.tehsilId,model.address.tehsil.tehsilName ");//4
			sbE.append(" model.address.booth.tehsil.tehsilId ");
		}else if(committeeBO.getGroupingLocation().equalsIgnoreCase("localElectionBody")){
			sbS.append(" ,model.address.localElectionBody.localElectionBodyId,model.address.localElectionBody.name " +//4
					   " ,model.address.localElectionBody.electionType.electionTypeId, " +
					   " model.address.localElectionBody.electionType.electionType ");//6
			sbE.append(" model.address.localElectionBody.localElectionBodyId ");
		}else if(committeeBO.getGroupingLocation().equalsIgnoreCase("Village")){
			sbS.append(" ,model.address.panchayat.panchayatId,model.address.panchayat.panchayatName ");//10
			sbE.append(" model.address.panchayat.panchayatId ");
		}else if(committeeBO.getGroupingLocation().equalsIgnoreCase("Ward")){
			sbS.append(" ,model.address.ward.constituencyId,model.address.ward.name ");//12
			sbE.append(" model.address.ward.constituencyId ");
		}
		
		sbM.append(" from  BoothInchargeCommittee model where ");
		if(committeeBO.getEnrollmentYearList() != null && committeeBO.getEnrollmentYearList().size() > 0){
			sbM.append(" model.boothInchargeEnrollmentId in(:tdpCommitteeEnrollmentId) ");
		}
		
		//locations
		if(committeeBO.getStateIds()!=null && committeeBO.getStateIds().size()>0){
			sbS.append(",model.address.state.stateId,model.address.state.stateName ");
			sbM.append(" and model.address.state.stateId in (:userAccessLocationValues) ");
		}
		else if(committeeBO.getDistrictIds() != null && committeeBO.getDistrictIds().size()>0){
			
			sbS.append(",model.address.district.districtId,model.address.district.districtName ");
			sbM.append(" and model.address.district.districtId in (:userAccessLocationValues) ");
			
		}else if(committeeBO.getAssemblyConstIds() != null && committeeBO.getAssemblyConstIds().size()>0){
			
			sbS.append(" ,model.address.constituency.constituencyId,model.address.constituency.name ");
			sbM.append(" and model.address.constituency.constituencyId in (:userAccessLocationValues) ");
			
		}else if(committeeBO.getTehsilIds()!= null && committeeBO.getTehsilIds().size()>0){
			
			sbS.append(",model.address.tehsil.tehsilId,model.address.tehsil.tehsilName ");
			sbM.append(" and model.address.tehsil.tehsilId in (:userAccessLocationValues) ");
		}
			
		if(committeeBO.getStateId()!= null && committeeBO.getStateId() > 0l ){
			sbM.append(" and model.address.state.stateId = :stateId ");
		}
		
		if(committeeBO.getStatus() != null && committeeBO.getStatus().trim().equalsIgnoreCase("notstarted")){
			sbM.append(" and model.isConfirmed ='N' and model.startDate is null and model.completedDate is null ");
		}
		if(committeeBO.getStatus() != null && committeeBO.getStatus().trim().equalsIgnoreCase("started")){
			sbM.append(" and model.isConfirmed ='N' and model.startDate is not null and model.completedDate is null ");
			if(committeeBO.getStartDate() != null && committeeBO.getEndDate() != null){
				sbM.append(" and date(model.startDate) between :startDate and :endDate ");
			}
		}
		else if(committeeBO.getStatus() != null && committeeBO.getStatus().trim().equalsIgnoreCase("completed")){
			sbM.append(" and model.isConfirmed ='Y' and model.startDate is not null and model.completedDate is not null ");
			if(committeeBO.getStartDate() != null &&  committeeBO.getEndDate()  != null){
				sbM.append(" and date(model.completedDate) between :startDate and :endDate ");
			}
		}
		
		StringBuilder sbf = new StringBuilder().append(sbS).append(sbM).append(sbE);
		Query query = getSession().createQuery(sbf.toString());
	
		if(committeeBO.getStateIds()!=null && committeeBO.getStateIds().size()>0){
			query.setParameterList("userAccessLocationValues",committeeBO.getStateIds());
		}
		else if(committeeBO.getDistrictIds() != null && committeeBO.getDistrictIds().size()>0){
			query.setParameterList("userAccessLocationValues",committeeBO.getDistrictIds());
		}else if(committeeBO.getParliamentConstIds() != null && committeeBO.getParliamentConstIds().size()>0){
			query.setParameterList("userAccessLocationValues",committeeBO.getParliamentConstIds());
		}else if(committeeBO.getAssemblyConstIds() != null && committeeBO.getAssemblyConstIds().size()>0){
			query.setParameterList("userAccessLocationValues",committeeBO.getAssemblyConstIds());
		}else if(committeeBO.getTehsilIds()!= null && committeeBO.getTehsilIds().size()>0){
			query.setParameterList("userAccessLocationValues",committeeBO.getTehsilIds());
		}
		
		if(committeeBO.getStateId()!= null && committeeBO.getStateId() > 0l ){
			query.setParameter("stateId",committeeBO.getStateId());
		}
		
		if(committeeBO.getEnrollmentYearList() != null && committeeBO.getEnrollmentYearList().size() > 0){
			query.setParameterList("tdpCommitteeEnrollmentId",committeeBO.getEnrollmentYearList());
		}
		if(committeeBO.getStatus() != null && !committeeBO.getStatus().trim().equalsIgnoreCase("total")  
				&& !committeeBO.getStatus().trim().equalsIgnoreCase("notstarted")){
			if(committeeBO.getStartDate() != null && committeeBO.getEndDate() != null){
				query.setDate("startDate",committeeBO.getStartDate());
				query.setDate("endDate",committeeBO.getEndDate());
			}
		}
		return query.list();
	}
	
public List<Object[]> getBoothCommitteesCumulativeCommitteesCountsByLocIds(CommitteeInputVO committeeBO){
		
 		StringBuilder sb = new StringBuilder();
 		
 		sb.append(" " +
 	    " select 1,'Booth Committee',count(distinct model.boothId)" +//2
 		" from   BoothInchargeCommittee model " +
 	    " where  model.isDeleted='N'  ");
 		if(committeeBO.getEnrollmentYearList() != null && committeeBO.getEnrollmentYearList().size() > 0){
			sb.append(" and model.boothInchargeEnrollmentId in(:tdpCommitteeEnrollmentId) ");
		}
 		//locations related.
 		if(committeeBO.getQueryString() != null && committeeBO.getQueryString().length()>0){
 			sb.append( committeeBO.getQueryString() );
 		}
 		
 		if( committeeBO.getStatus() != null && !committeeBO.getStatus().isEmpty()){
 		
 			if(committeeBO.getStatus().equalsIgnoreCase("notStarted")){
 				sb.append("   and model.isConfirmed = 'N' and model.completedDate is null ");
 				if( committeeBO.getDate()!=null){
 					sb.append( " and ( date(model.startDate) > :givendate or model.startDate is null)" );
 				}else{
 					sb.append(" and model.startDate is null ");
 				}
 			}
 		   else if(committeeBO.getStatus().equalsIgnoreCase("started")){
 				sb.append(" and model.startDate is not null and  model.isConfirmed = 'N' ");
 				if(committeeBO.getDate()!=null){
 					sb.append( " and date(model.startDate)<= :givendate and ( date(model.completedDate)>=:givendate  or model.completedDate is null )  " );
 				}else{
 					sb.append(" and model.completedDate is null ");
 				}
 			}
 			else if(committeeBO.getStatus().equalsIgnoreCase("completed")){
 				sb.append(" and model.startDate is not null  and model.completedDate is not null and model.isConfirmed = 'Y' ");
 				if( committeeBO.getDate()!=null){
 					sb.append( " and date(model.completedDate) < :givendate " );
 				}
 			} 
 		}
 			
 		if(committeeBO.getStateId()!= null && committeeBO.getStateId() > 0l ){
 			sb.append(" and model.address.state.stateId = :stateId ");
 		}
 		if(committeeBO.getCommitteesQueryString()!=null && committeeBO.getCommitteesQueryString().length()>0){
 			sb.append(committeeBO.getCommitteesQueryString());
		}
 		
 		Query query = getSession().createQuery(sb.toString());
 		
 		//locations.
 		if(committeeBO.getStateIds()!=null && committeeBO.getStateIds().size()>0){
 			query.setParameterList("tdpCommitteeLevelValues",committeeBO.getStateIds());
 		}
 		else if(committeeBO.getDistrictIds() != null && committeeBO.getDistrictIds().size()>0){
 			query.setParameterList("tdpCommitteeLevelValues",committeeBO.getDistrictIds());
 		}else if(committeeBO.getParliamentConstIds() != null && committeeBO.getParliamentConstIds().size()>0){
 			query.setParameterList("tdpCommitteeLevelValues",committeeBO.getParliamentConstIds());
 		}else if(committeeBO.getAssemblyConstIds() != null && committeeBO.getAssemblyConstIds().size()>0){
 			query.setParameterList("tdpCommitteeLevelValues",committeeBO.getAssemblyConstIds());
 		}else if(committeeBO.getTehsilIds()!= null && committeeBO.getTehsilIds().size()>0){
 			query.setParameterList("tdpCommitteeLevelValues",committeeBO.getTehsilIds());
 		}
 		
 		if(committeeBO.getStatus() != null && !committeeBO.getStatus().isEmpty()){
 			if(committeeBO.getDate()!=null){
 				query.setDate("givendate",committeeBO.getDate());
 			}
 		}
 		if(committeeBO.getStateId()!= null && committeeBO.getStateId() > 0l ){
 			query.setParameter("stateId",committeeBO.getStateId());
 		}
 		if(committeeBO.getEnrollmentYearList() != null && committeeBO.getEnrollmentYearList().size() > 0){
 			query.setParameterList("tdpCommitteeEnrollmentId",committeeBO.getEnrollmentYearList());
		}
 		return query.list();
 	}

public List<Object[]> getBoothCommitteesCommitteeLevelWiseCountsByLocIds(CommitteeInputVO committeeBO){
	
	StringBuilder sb = new StringBuilder();
	
	sb.append(" " +
    "select  1,'Booth Committee '," +//1
    "1,'Booth Committee'," +//3
	"count(distinct model.boothId)" +//4
	" from   BoothInchargeCommittee model " +
    " where  model.isDeleted='N' " );
	
	if(committeeBO.getEnrollmentYearList() != null && committeeBO.getEnrollmentYearList().size() > 0){
		sb.append(" and model.boothInchargeEnrollmentId in(:tdpCommitteeEnrollmentId) ");
	}
	
	//locations related.
	if(committeeBO.getQueryString() != null && committeeBO.getQueryString().length()>0){
		sb.append( committeeBO.getQueryString() );
	}
	
	if( committeeBO.getStatus() != null && !committeeBO.getStatus().isEmpty()){
	
		if(committeeBO.getStatus().equalsIgnoreCase("notStarted")){
			sb.append("   and model.isConfirmed = 'N' and model.completedDate is null ");
			if( committeeBO.getDate()!=null){
				sb.append( " and ( date(model.startDate) > :givendate or model.startDate is null)" );
			}else if(committeeBO.getStartDate() != null && committeeBO.getEndDate() != null){
				sb.append( " and ( (date(model.startDate) between :startDate and :endDate ) or model.startDate is null)" );
			}else{
				sb.append(" and model.startedDate is null ");
			}
		}
	   else if(committeeBO.getStatus().equalsIgnoreCase("started")){
			sb.append(" and model.startDate is not null and  model.isConfirmed = 'N' ");
			if(committeeBO.getDate()!=null){
				sb.append( " and date(model.startDate)<= :givendate and ( date(model.completedDate)>=:givendate  or model.completedDate is null )  " );
			}else if(committeeBO.getStartDate() != null && committeeBO.getEndDate() != null){
				sb.append( " and ( (date(model.startDate) between :startDate and :endDate ) or model.completedDate is null)" );
			}else{
				sb.append(" and model.completedDate is null ");
			}
		}
		else if(committeeBO.getStatus().equalsIgnoreCase("completed")){
			sb.append(" and model.startDate is not null  and model.completedDate is not null and model.isConfirmed = 'Y' ");
			if( committeeBO.getDate()!=null){
				sb.append( " and date(model.completedDate) < :givendate " );
			}else if(committeeBO.getStartDate() != null && committeeBO.getEndDate() != null){
				sb.append( " and ( (date(model.completedDate) between :startDate and :endDate ) )" );
			}
		} 
	}
		
	if(committeeBO.getStateId()!= null && committeeBO.getStateId() > 0l ){
		sb.append(" and model.address.state.stateId = :stateId ");
	}
	if(committeeBO.getCommitteesQueryString()!=null && committeeBO.getCommitteesQueryString().length()>0){
		sb.append(committeeBO.getCommitteesQueryString());
	}
	
	Query query = getSession().createQuery(sb.toString());
	
	
	
	//locations.
	if(committeeBO.getStateIds()!=null && committeeBO.getStateIds().size()>0){
		query.setParameterList("tdpCommitteeLevelValues",committeeBO.getStateIds());
	}
	else if(committeeBO.getDistrictIds() != null && committeeBO.getDistrictIds().size()>0){
		query.setParameterList("tdpCommitteeLevelValues",committeeBO.getDistrictIds());
	}else if(committeeBO.getParliamentConstIds() != null && committeeBO.getParliamentConstIds().size()>0){
		query.setParameterList("tdpCommitteeLevelValues",committeeBO.getParliamentConstIds());
	}else if(committeeBO.getAssemblyConstIds() != null && committeeBO.getAssemblyConstIds().size()>0){
		query.setParameterList("tdpCommitteeLevelValues",committeeBO.getAssemblyConstIds());
	}else if(committeeBO.getTehsilIds()!= null && committeeBO.getTehsilIds().size()>0){
		query.setParameterList("tdpCommitteeLevelValues",committeeBO.getTehsilIds());
	}
	
	if(committeeBO.getStatus() != null && !committeeBO.getStatus().isEmpty()){
		if(committeeBO.getDate()!=null){
			query.setDate("givendate",committeeBO.getDate());
		}else if(committeeBO.getStartDate() != null && committeeBO.getEndDate() != null){
			query.setDate("startDate",committeeBO.getStartDate());
			query.setDate("endDate",committeeBO.getEndDate());
		}
	}
	if(committeeBO.getStateId()!= null && committeeBO.getStateId() > 0l ){
		query.setParameter("stateId",committeeBO.getStateId());
	}
	if(committeeBO.getEnrollmentYearList() != null && committeeBO.getEnrollmentYearList().size() > 0){
		query.setParameterList("tdpCommitteeEnrollmentId",committeeBO.getEnrollmentYearList());
	}
	return query.list();
}
public List<Object[]> getBoothRoleWiseAddedMemberCount(InputVO inputVO) {

	StringBuilder queryStr = new StringBuilder();
	queryStr.append("select ");
	queryStr.append(" model.boothInchargeRoleConditionMapping.boothInchargeCommittee.boothId ");
	queryStr.append(",model.boothInchargeRoleConditionMapping.boothInchargeRoleCondition.boothInchargeRoleId ");
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
			queryStr.append(" and model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.tehsil.tehsilId in(:filterValues)  ");
		} else if (inputVO.getFilterLevel().equalsIgnoreCase(IConstants.PANCHAYAT)) {
			queryStr.append(" and model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.panchayat.panchayatId in(:filterValues)");
		}
	}
	if (inputVO.getFilterLevel().equalsIgnoreCase(IConstants.TEHSIL)) {
		queryStr.append(" and model.boothInchargeRoleConditionMapping.boothInchargeCommittee.address.localElectionBody.localElectionBodyId is null ");
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
	queryStr.append(" model.boothInchargeRoleConditionMapping.boothInchargeCommittee.boothId," +
			        " model.boothInchargeRoleConditionMapping.boothInchargeRoleCondition.boothInchargeRoleId");
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
public Long getBoothTotalAddedMember(Long boothId,Long boothInchargeEnrollmentId) {

	StringBuilder queryStr = new StringBuilder();
	queryStr.append(" select ");
	queryStr.append(" count(distinct model.tdpCadreId)");
	queryStr.append(" from BoothIncharge model ");
	queryStr.append(" where model.isDeleted='N' and model.isActive='Y' ");
	queryStr.append(" and model.boothInchargeRoleConditionMapping.isDeleted='N' "); 
	queryStr.append(" and model.boothInchargeRoleConditionMapping.boothInchargeCommittee.isDeleted='N' ");
	queryStr.append(" and model.boothInchargeEnrollmentId =:boothInchargeEnrollmentId ");
	queryStr.append(" and model.boothInchargeRoleConditionMapping.boothInchargeCommittee.boothId =:boothId ");
	Query query = getSession().createQuery(queryStr.toString());
	query.setParameter("boothInchargeEnrollmentId",boothInchargeEnrollmentId);
	query.setParameter("boothId",boothId);
	return (Long)query.uniqueResult();
}
public List<Object[]> getActiveBoothMemeberDetails(Long boothId){
	StringBuilder sb = new StringBuilder();
	sb.append(" select distinct model.tdpCadre.voterId,model.tdpCadreId,model.tdpCadre.familyVoterId," +
			" model.boothInchargeRoleConditionMapping.boothInchargeRoleCondition.boothInchargeRoleId , model.addedBoothInhcargeConditionId from BoothIncharge model " +
			  " where model.boothInchargeRoleConditionMapping.boothInchargeCommittee.boothId =:boothId" +
			  " and model.tdpCadre.isDeleted ='N'" +
			  " and model.isActive = 'Y' and model.isDeleted= 'N' ");
	
	 Query qry = getSession().createQuery(sb.toString());
	 if(boothId != null && boothId.longValue() >0l){
		 qry.setParameter("boothId", boothId);
		}
	   return qry.list();
}
}
