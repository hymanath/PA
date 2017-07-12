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
	
	public List<Object[]> getBoothUserDetails(Long constituencyId, Long mandalId, Long boothId){
		StringBuilder query = new StringBuilder("select model.booth.partNo, model.booth.villagesCovered, " +
					" model.booth.constituency.name, " +
					" model.booth.panchayat.panchayatName," +
					" model.tdpCadre.firstname, model.tdpCadre.mobileNo, model.tdpCadre.memberShipNo, " +
					" model.tdpCadre.image, " +
					" model.booth.tehsil.tehsilName " +
					" from BoothIncharge model " +
					" where " +
					" model.isDeleted='N' "+
					" and model.tdpCadre.isDeleted='N' and model.booth.publicationDate.publicationDateId = :publicationDate");
		if(constituencyId !=null && constituencyId.longValue() > 0)
		query.append(" and model.booth.constituency.constituencyId=:constituencyId");
		if(mandalId !=null && mandalId.longValue() > 0)
		query.append(" and model.booth.tehsil.tehsilId=:mandalId");
		if(boothId !=null && boothId.longValue() > 0)
		query.append(" and model.booth.boothId=:boothId");
		
		Query query1=getSession().createQuery(query.toString());
		query1.setParameter("publicationDate", IConstants.CADRE_REGISTRATION_2016_PUBLICATION_ID);
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
				" l.name " +
				" from BoothIncharge model " +
				" left join model.booth b " +
				" left join b.panchayat p " +
				" left join b.tehsil t " +
				" left join b.localBody l " +
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
		
		sb.append(" select  count(distinct model.boothId) " +
				  " from BoothIncharge model " +
				  " where model.booth.constituency.constituencyId = :constituencyId " +
				  " and model.booth.publicationDate.publicationDateId = :publicationDate " +
				  " and model.isActive ='Y' ");
		
		Query qry = getSession().createQuery(sb.toString());
		
		if(constituencyId != null && constituencyId.longValue()>0l){
			qry.setParameter("constituencyId", constituencyId);
		}
		
		qry.setParameter("publicationDate", IConstants.VOTER_DATA_PUBLICATION_ID);
		
		return (Long) qry.uniqueResult();
	}
   public Long getBoothAssignInchargeCount(Long userAccessLevelId,Set<Long> userAccessLevelValues,Date startDate,Date endDate,List<Long> committeeEnrollmentYearsIdsLst,List<Long> bothIds){
		
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append(" select  count(distinct model.boothId) " +
				  " from BoothIncharge model " +
				  " where " +
				  " model.booth.publicationDate.publicationDateId = :publicationDate " +
				  " and model.isActive ='Y' and model.isDeleted = 'N' ");
		          if(bothIds != null && bothIds.size()>0){
			             queryStr.append(" and model.boothId in (:bothIds) ");
		             }
				if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
				         queryStr.append(" and model.booth.constituency.state.stateId in (:userAccessLevelValues)");  
				       }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
				         queryStr.append(" and model.booth.constituency.district.districtId in (:userAccessLevelValues)");  
				       }/*else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
				            queryStr.append(" and model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
				       }*/else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
				            queryStr.append(" and model.booth.constituency.constituencyId in (:userAccessLevelValues) ");  
				       }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
				          queryStr.append(" and model.booth.tehsil.tehsilId in (:userAccessLevelValues)");  
				       }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
				          queryStr.append(" and model.booth.localBody.localElectionBodyId in (:userAccessLevelValues)"); 
				       }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
				          queryStr.append(" and model.booth.panchayat.panchayatId in (:userAccessLevelValues)"); 
				       }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
				          queryStr.append(" and model.booth.localBodyWard.constituencyId in (:userAccessLevelValues)"); 
				       }
				if(startDate != null && endDate != null){
					queryStr.append(" and date(model.insertedTime) between :startDate and :endDate ");
				}
				if(committeeEnrollmentYearsIdsLst != null && committeeEnrollmentYearsIdsLst.size()>0){
					queryStr.append(" and model.boothInchargeEnrollment.boothInchargeEnrollmentId in(:committeeEnrollmentYearsIdsLst) ");
				}
	 	  Query qry = getSession().createQuery(queryStr.toString());
		
		qry.setParameter("publicationDate", IConstants.VOTER_DATA_PUBLICATION_ID);
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
				" and model.booth.publicationDate.publicationDateId = :publicationDate "); 
	
		
		if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
			   queryStr.append(" and model.booth.constituency.state.stateId in (:userAccessLevelValues)");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			   queryStr.append(" and model.booth.constituency.district.districtId in (:userAccessLevelValues)");  
			 }/*else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
		        queryStr.append(" and model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
			 }*/else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
		        queryStr.append(" and model.booth.constituency.constituencyId in (:userAccessLevelValues) ");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
			    queryStr.append(" and model.booth.tehsil.tehsilId in (:userAccessLevelValues)");  
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
			    queryStr.append(" and model.booth.localBody.localElectionBodyId in (:userAccessLevelValues)"); 
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
			    queryStr.append(" and model.booth.panchayat.panchayatId in (:userAccessLevelValues)"); 
			 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
			    queryStr.append(" and model.booth.localBodyWard.constituencyId in (:userAccessLevelValues)"); 
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
public List<Object[]> getLocationLevelWiseBoothDetails(InputVO inputVO,String resultType){
	
	   	 StringBuilder queryStr = new StringBuilder();
	     queryStr.append("select ");
		if(inputVO.getLocationLevel().equalsIgnoreCase(IConstants.DISTRICT)){
			queryStr.append(" district.districtId ");
			queryStr.append(" ,district.districtName");
		}else if(inputVO.getLocationLevel().equalsIgnoreCase(IConstants.PARLIAMENT_CONSTITUENCY)){
			queryStr.append(" parliamentConstituency.constituencyId ");
			queryStr.append(" ,parliamentConstituency.name ");
		}else if(inputVO.getLocationLevel().equalsIgnoreCase(IConstants.CONSTITUENCY)){
			queryStr.append(" constituency.constituencyId ");
			queryStr.append(" ,constituency.name ");
		}else if(inputVO.getLocationLevel().equalsIgnoreCase(IConstants.TEHSIL)){
			queryStr.append(" tehsil.tehsilId ");
			queryStr.append(" ,tehsil.tehsilName");
		}else if(inputVO.getLocationLevel().equalsIgnoreCase(IConstants.PANCHAYAT)){
			queryStr.append(" panct.panchayatId ");
			queryStr.append(" ,panc.panchayatName");
		}
		queryStr.append(",count(distinct model.boothId)");
		
		queryStr.append(",district.districtId,district.districtName,parliamentConstituency.constituencyId,parliamentConstituency.name");
		queryStr.append(",constituency.constituencyId,constituency.name,tehsil.tehsilId,tehsil.tehsilName,panct.panchayatId,panc.panchayatName,");
		queryStr.append(",localElectionBody.localElectionBodyId,localElectionBody.name,electionType.electionTypeId,electionType.electionType ");
		
		queryStr.append(" from BoothIncharge model ");
		queryStr.append(" from left join model.boothInchargeRoleConditionMapping model1 ");
		queryStr.append(" left join model1.address.state state ");
		queryStr.append(" left join model1.address.district district ");
		queryStr.append(" left join model1.address.parliamentConstituency parliamentConstituency ");
		queryStr.append(" left join model1.address.constituency constituency ");
		queryStr.append(" left join model1.address.tehsil tehsil ");
		queryStr.append(" left join model1.address.localElectionBody localElectionBody ");
		queryStr.append(" left join model1.address.localElectionBody.electionType electionType ");
		queryStr.append(" left join model1.address.panchayat panc ");
		
	    queryStr.append(" where model.isActive ='Y' and model.isDeleted = 'N' ");
	    
	    if(inputVO.getFilterLevel().length() > 0 && inputVO.getFilterValue() != null && inputVO.getFilterValue().longValue() > 0){
	    	
	    	if(inputVO.getFilterLevel().equalsIgnoreCase(IConstants.DISTRICT)){
				queryStr.append(" and district.districtId =:filterValue ");
			}else if(inputVO.getFilterLevel().equalsIgnoreCase(IConstants.PARLIAMENT_CONSTITUENCY)){
				queryStr.append(" and parliamentConstituency.constituencyId =:filterValue ");
			}else if(inputVO.getFilterLevel().equalsIgnoreCase(IConstants.CONSTITUENCY)){
				queryStr.append(" and constituency.constituencyId =:filterValue");
			}else if(inputVO.getFilterLevel().equalsIgnoreCase(IConstants.TEHSIL)){
				queryStr.append(" and tehsil.tehsilId =:filterValue");
			}else if(inputVO.getFilterLevel().equalsIgnoreCase(IConstants.PANCHAYAT)){
				queryStr.append(" and model.booth.constituency.panchayat.panchayatId=:filterValue");
			}
	    }
	    
			if(resultType.equalsIgnoreCase("Not Started")){
				queryStr.append(" and model1.isConfirmed='N' and model1.startDate is null && model1.completedDate is null ");
			}else if(resultType.equalsIgnoreCase("Started")){
				queryStr.append(" and model1.isConfirmed='N' and model1.completedDate is null");
			}else if(resultType.equalsIgnoreCase("Completed")){
				queryStr.append(" and model1.isConfirmed='Y' ");
			}
			
		    if(inputVO.getFromDate() != null && inputVO.getToDate() != null){
				if(resultType.equalsIgnoreCase("Started")){
					queryStr.append(" and date(model1.startDate) between :fromDate and :toDate ");
				}else if(resultType.equalsIgnoreCase("Completed")){
					queryStr.append(" and date(model.completedDate) between :fromDate and :toDate ");
				}
		   }
		    if(inputVO.getBoothInchargeEnrollmentId() != null && inputVO.getBoothInchargeEnrollmentId().longValue()>0){
				queryStr.append(" and model.boothInchargeEnrollment.boothInchargeEnrollmentId =:boothInchargeEnrollmentId ");
			}
		queryStr.append(" group by ");
	    if(inputVO.getLocationLevel().equalsIgnoreCase(IConstants.DISTRICT)){
			queryStr.append(" district.districtId.districtId ");
		}else if(inputVO.getLocationLevel().equalsIgnoreCase(IConstants.PARLIAMENT_CONSTITUENCY)){
			queryStr.append(" parliamentConstituency.constituencyId ");
		}else if(inputVO.getLocationLevel().equalsIgnoreCase(IConstants.CONSTITUENCY)){
			queryStr.append(" constituency.constituencyId ");
		}else if(inputVO.getLocationLevel().equalsIgnoreCase(IConstants.TEHSIL)){
			queryStr.append(" tehsil.tehsilId ");
		}else if(inputVO.getLocationLevel().equalsIgnoreCase(IConstants.PANCHAYAT)){
			queryStr.append(" panct.panchayatId ");
		}
 	    Query query = getSession().createQuery(queryStr.toString());
 	    
 	   if(inputVO.getFromDate() != null && inputVO.getToDate() != null){
			query.setDate("fromDate", inputVO.getFromDate());
			query.setDate("toDate", inputVO.getToDate());
		}
		if(inputVO.getBoothInchargeEnrollmentId() != null && inputVO.getBoothInchargeEnrollmentId().longValue()>0){
			query.setParameter("boothInchargeEnrollmentId", inputVO.getBoothInchargeEnrollmentId());
		}
	    if(inputVO.getFilterValue() != null && inputVO.getFilterValue().longValue() > 0){
	    	query.setParameter("filterValue", inputVO.getFilterValue());
	    }
		return query.list();
	}
}
