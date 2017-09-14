 package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPartyMeetingStatusDAO;
import com.itgrids.partyanalyst.dto.CommitteeInputVO;
import com.itgrids.partyanalyst.model.PartyMeetingStatus;
import com.itgrids.partyanalyst.utils.IConstants;

public class PartyMeetingStatusDAO extends GenericDaoHibernate<PartyMeetingStatus, Long> implements IPartyMeetingStatusDAO {

	public PartyMeetingStatusDAO() {
		super(PartyMeetingStatus.class);
	}
	 
	public List<Object[]> getPartyMeetingCountLevelWise(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> partyMeetingTypeValues,String countType){
		 
		 StringBuilder queryStr = new StringBuilder();
	    	
   	  queryStr.append("select model.partyMeeting.partyMeetingLevel.partyMeetingLevelId," +
   	  		         " model.partyMeeting.partyMeetingLevel.level," +
   	  		         " model.mettingStatus," +
   	  		         " count(distinct model.partyMeeting.partyMeetingId)  " +
   	  		         " from PartyMeetingStatus model " +
   	  		         " where " +
   	  		         " model.partyMeeting.isActive='Y' " +
   	  		         " and model.partyMeeting.partyMeetingType.partyMeetingMainType.partyMeetingMainTypeId = 1");
   	      if(countType.equalsIgnoreCase("commentCount")){
   	    	  queryStr.append(" and model.partyMeeting.remarks is not null ");
   	      }
   	      if(stateId != null && stateId.longValue() > 0){
				 queryStr.append(" and model.partyMeeting.meetingAddress.state.stateId=:stateId");
		  }
		  if(fromDate!= null && toDate!=null){
			  queryStr.append(" and date(model.partyMeeting.startDate) between :fromDate and :toDate ");	 
		 }
		 if(partyMeetingTypeValues != null && partyMeetingTypeValues.size() > 0){
				 queryStr.append(" and model.partyMeeting.partyMeetingType.partyMeetingTypeId in (:partyMeetingTypeValues)");
		 }
		 if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		   queryStr.append(" and model.partyMeeting.meetingAddress.state.stateId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		   queryStr.append(" and model.partyMeeting.meetingAddress.district.districtId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
	        queryStr.append(" and model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
	        queryStr.append(" and model.partyMeeting.meetingAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
		    queryStr.append(" and model.partyMeeting.meetingAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
		    queryStr.append(" and model.partyMeeting.meetingAddress.userAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
		    queryStr.append(" and model.partyMeeting.meetingAddress.panchayat.panchayatId in (:userAccessLevelValues)"); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
		    queryStr.append(" and model.partyMeeting.meetingAddress.ward.constituencyId in (:userAccessLevelValues)"); 
		 }
		 queryStr.append(" group by model.partyMeeting.partyMeetingLevel.partyMeetingLevelId,model.mettingStatus " +
        " order by " +
        " model.partyMeeting.partyMeetingLevel.partyMeetingLevelId asc ");
		
	   Query query = getSession().createQuery(queryStr.toString());
	
		 if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
			   query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		 }
		 if(fromDate!= null && toDate!=null){
		   query.setDate("fromDate", fromDate);
		   query.setDate("toDate", toDate);
		 }
		 if(stateId != null && stateId.longValue() > 0){
			 query.setParameter("stateId", stateId);
		 }
		 if(partyMeetingTypeValues != null && partyMeetingTypeValues.size() > 0){
			 query.setParameterList("partyMeetingTypeValues", partyMeetingTypeValues); 
		 }
      return query.list(); 
	 }
 public List<Object[]> getPartyMeetingCountLocationWiseByUserAccess(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> partyMeetingTypeValues)
		{
			  StringBuilder queryStr= new StringBuilder();
			
			  queryStr.append("select ");
		      
			  if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		         queryStr.append(" model.partyMeeting.meetingAddress.state.stateId,");  
		      }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		        queryStr.append(" model.partyMeeting.meetingAddress.district.districtId, ");  
		      }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
		          queryStr.append(" model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId, ");  
			  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
		          queryStr.append(" model.partyMeeting.meetingAddress.constituency.constituencyId, ");  
			  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
		         queryStr.append(" model.partyMeeting.meetingAddress.tehsil.tehsilId,");  
			  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
			         queryStr.append(" model.partyMeeting.meetingAddress.localElectionBody.localElectionBodyId,"); 
			  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
			       queryStr.append(" model.partyMeeting.meetingAddress.panchayat.panchayatId,"); 
			  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
			      queryStr.append(" model.partyMeeting.meetingAddress.ward.constituencyId,"); 
			  }
			   queryStr.append(" model.mettingStatus,count(distinct model.partyMeeting.partyMeetingId) " +
			   " from PartyMeetingStatus model " +
  		         " where " +
  		         " model.partyMeeting.isActive='Y' ");   
	         if(stateId != null && stateId.longValue() > 0){
				 queryStr.append(" and model.partyMeeting.meetingAddress.state.stateId=:stateId ");
			 }
			  if(fromDate!= null && toDate!=null){
				  queryStr.append(" and date(model.partyMeeting.startDate) between :fromDate and :toDate ");	 
			 }
			 if(partyMeetingTypeValues != null && partyMeetingTypeValues.size() > 0){
				queryStr.append(" and model.partyMeeting.partyMeetingType.partyMeetingTypeId in (:partyMeetingTypeValues)");
			 }
			  
		   /* if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		      queryStr.append(" and model.partyMeeting.meetingAddress.state.stateId in (:userAccessLevelValues)");  
		    }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		      queryStr.append(" and model.partyMeeting.meetingAddress.district.districtId in (:userAccessLevelValues)");  
		    }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
		        queryStr.append(" and model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
			}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
		         queryStr.append(" and model.partyMeeting.meetingAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
			}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
		      queryStr.append(" and model.partyMeeting.meetingAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
			}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
		      queryStr.append(" and model.partyMeeting.meetingAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
			}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
		      queryStr.append(" and model.partyMeeting.meetingAddress.panchayat.panchayatId in (:userAccessLevelValues)"); 
			}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
		      queryStr.append(" and model.partyMeeting.meetingAddress.ward.constituencyId in (:userAccessLevelValues)"); 
			}*/
	        if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		      queryStr.append(" group by  model.partyMeeting.meetingAddress.state.stateId,model.mettingStatus order by model.partyMeeting.meetingAddress.state.stateId ");  
		    }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		      queryStr.append(" group by  model.partyMeeting.meetingAddress.district.districtId,model.mettingStatus order by  model.partyMeeting.meetingAddress.district.districtId ");  
		    }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
		        queryStr.append(" group by  model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId,model.mettingStatus order by model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId");  
			}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
		         queryStr.append("group by model.partyMeeting.meetingAddress.constituency.constituencyId,model.mettingStatus order by model.partyMeeting.meetingAddress.constituency.constituencyId");  
			}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
		      queryStr.append(" group by  model.partyMeeting.meetingAddress.tehsil.tehsilId,model.mettingStatus order by model.partyMeeting.meetingAddress.tehsil.tehsilId");  
			}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
		      queryStr.append(" group by  model.partyMeeting.meetingAddress.localElectionBody.localElectionBodyId,model.mettingStatus order by model.partyMeeting.meetingAddress.localElectionBody.localElectionBodyId"); 
			}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
		      queryStr.append(" group by  model.partyMeeting.meetingAddress.panchayat.panchayatId,model.mettingStatus order by model.partyMeeting.meetingAddress.panchayat.panchayatId"); 
			}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){
				 queryStr.append(" group by model.partyMeeting.meetingAddress.ward.constituencyId,model.mettingStatus order by model.partyMeeting.meetingAddress.ward.constituencyId");	
			} 
		    Query query = getSession().createQuery(queryStr.toString());
	    	
			/* if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
				   query.setParameterList("userAccessLevelValues", userAccessLevelValues);
			 }*/
			 if(fromDate!= null && toDate!=null){
			   query.setDate("fromDate", fromDate);
			   query.setDate("toDate", toDate);
			 }
			 if(stateId != null && stateId.longValue() > 0){
				 query.setParameter("stateId", stateId);
			 }
			 if(partyMeetingTypeValues != null && partyMeetingTypeValues.size() > 0){
				 query.setParameterList("partyMeetingTypeValues", partyMeetingTypeValues); 
			 }
	    return query.list();  
		}	
		
    public List<Object[]> getLocationWiseMeetingsStatusCountByLocIds(CommitteeInputVO inputBO){
		
		StringBuilder sbS = new StringBuilder();
		sbS.append(" select count(distinct model.partyMeeting.partyMeetingId) ");
		StringBuilder sbM = new StringBuilder();
		sbM.append(" from   PartyMeetingStatus model ");
		
		sbM.append(" where model.partyMeeting.isActive='Y' and model.partyMeeting.startDate is not null ");
		StringBuilder sbE = new StringBuilder();
		if(inputBO.getStateIds()!=null && inputBO.getStateIds().size()>0){
			sbS.append(",model.partyMeeting.meetingAddress.state.stateId ");
			sbE.append(" group by model.partyMeeting.meetingAddress.state.stateId,model.mettingStatus ");
		}
		else if(inputBO.getDistrictIds() != null && inputBO.getDistrictIds().size()>0){
			
			sbS.append(",model.partyMeeting.meetingAddress.district.districtId ");
			sbE.append(" group by model.partyMeeting.meetingAddress.district.districtId,model.mettingStatus ");
			
		}else if(inputBO.getParliamentConstIds() != null && inputBO.getParliamentConstIds().size()>0){
			
			sbS.append(",model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId ");
			sbE.append(" group by model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId,model.mettingStatus ");
			
		}else if(inputBO.getAssemblyConstIds() != null && inputBO.getAssemblyConstIds().size()>0){
			
			sbS.append(",model.partyMeeting.meetingAddress.constituency.constituencyId");
			sbE.append(" group by model.partyMeeting.meetingAddress.constituency.constituencyId,model.mettingStatus ");
			
		}else if(inputBO.getTehsilIds()!= null && inputBO.getTehsilIds().size()>0){
			
			sbS.append(",model.partyMeeting.meetingAddress.tehsil.tehsilId ");
			sbE.append(" group by model.partyMeeting.meetingAddress.tehsil.tehsilId,model.mettingStatus ");
		}
		 sbS.append(",model.mettingStatus");
		
		 if(inputBO.getStartDate()!= null && inputBO.getEndDate()!=null){
			 sbM.append(" and date(model.partyMeeting.startDate) between :startDate and :endDate ");	 
		 }
			
		if(inputBO.getStateId()!= null && inputBO.getStateId() > 0l ){
			sbM.append(" and model.partyMeeting.meetingAddress.state.stateId = :stateId ");
		}
		if(inputBO.getPartyMeetingTypeIds()!=null && inputBO.getPartyMeetingTypeIds().size()>0){
			sbM.append(" and model.partyMeeting.partyMeetingTypeId in (:partyMeetingTypeIds) ");
		}
		
		StringBuilder sbf = new StringBuilder().append(sbS).append(sbM).append(sbE);
		
		Query query = getSession().createQuery(sbf.toString());
		
		if(inputBO.getStateId()!= null && inputBO.getStateId() > 0l ){
			query.setParameter("stateId",inputBO.getStateId());
		}
		if(inputBO.getStartDate()!= null && inputBO.getEndDate()!=null){
			query.setDate("startDate",inputBO.getStartDate());
			query.setDate("endDate",inputBO.getEndDate());
		}
		if(inputBO.getPartyMeetingTypeIds()!=null && inputBO.getPartyMeetingTypeIds().size()>0){
			query.setParameterList("partyMeetingTypeIds",inputBO.getPartyMeetingTypeIds());
		}
		return query.list();
	}
    
    public List<Object[]> getTopPoorMeetingLocations(CommitteeInputVO committeeBO){
		
		StringBuilder sbS = new StringBuilder();
		sbS.append("select count(distinct model.partyMeeting.partyMeetingId)");//0
		StringBuilder sbE = new StringBuilder();
		if(committeeBO.getGroupingLocation().equalsIgnoreCase("State")){
			sbS.append(" ,model.partyMeeting.meetingAddress.state.stateId,model.partyMeeting.meetingAddress.state.stateName ");//2
			sbE.append("  group by model.partyMeeting.meetingAddress.state.stateId,model.mettingStatus ");
		}else if(committeeBO.getGroupingLocation().equalsIgnoreCase("District")){
			sbS.append(" ,model.partyMeeting.meetingAddress.district.districtId,model.partyMeeting.meetingAddress.district.districtName ");//2
			sbE.append("  group by model.partyMeeting.meetingAddress.district.districtId,model.mettingStatus ");
		}else if(committeeBO.getGroupingLocation().equalsIgnoreCase("Constituency")){
			sbS.append(" ,model.partyMeeting.meetingAddress.constituency.constituencyId,model.partyMeeting.meetingAddress.constituency.name ");//2
			sbE.append("  group by model.partyMeeting.meetingAddress.constituency.constituencyId,model.mettingStatus ");
		}else if(committeeBO.getGroupingLocation().equalsIgnoreCase("Mandal")){
			sbS.append(" ,model.partyMeeting.meetingAddress.tehsil.tehsilId,model.partyMeeting.meetingAddress.tehsil.tehsilName ");//2
			sbE.append("  group by model.partyMeeting.meetingAddress.tehsil.tehsilId,model.mettingStatus ");
		}else if(committeeBO.getGroupingLocation().equalsIgnoreCase("localElectionBody")){
			sbS.append(" ,model.partyMeeting.meetingAddress.localElectionBody.localElectionBodyId,model.partyMeeting.meetingAddress.localElectionBody.name " +//2
					   " ,model.partyMeeting.meetingAddress.localElectionBody.electionType.electionTypeId,model.partyMeeting.meetingAddress.localElectionBody.electionType.electionType ");//4
			sbE.append(" group by model.partyMeeting.meetingAddress.localElectionBody.localElectionBodyId,model.mettingStatus ");
		}else if(committeeBO.getGroupingLocation().equalsIgnoreCase("Village")){
			sbS.append(" ,model.partyMeeting.meetingAddress.panchayat.panchayatId,model.partyMeeting.meetingAddress.panchayat.panchayatName ");//2
			sbE.append(" group by model.partyMeeting.meetingAddress.panchayat.panchayatId,model.mettingStatus ");
		}else if(committeeBO.getGroupingLocation().equalsIgnoreCase("Ward")){
			sbS.append(" ,model.partyMeeting.meetingAddress.ward.constituencyId,model.partyMeeting.meetingAddress.ward.name ");//2
			sbE.append(" group by model.partyMeeting.meetingAddress.ward.constituencyId,model.mettingStatus ");
		}
		sbS.append(",model.mettingStatus");//3 or 5
		
		StringBuilder sbM = new StringBuilder();
		sbM.append(" from  PartyMeetingStatus model where model.partyMeeting.isActive = 'Y' and model.partyMeeting.startDate is not null ");
		
		//locations related.
		if(committeeBO.getStateIds()!=null && committeeBO.getStateIds().size()>0){
			sbM.append(" and model.partyMeeting.meetingAddress.state.stateId in (:locationValues) ");
		}
		else if(committeeBO.getDistrictIds() != null && committeeBO.getDistrictIds().size()>0){
			
			sbM.append(" and model.partyMeeting.meetingAddress.district.districtId in (:locationValues) ");
			
		}else if(committeeBO.getParliamentConstIds() != null && committeeBO.getParliamentConstIds().size()>0){
			
			sbM.append(" and model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId in (:locationValues) ");
			
		}else if(committeeBO.getAssemblyConstIds() != null && committeeBO.getAssemblyConstIds().size()>0){
			
			sbM.append(" and model.partyMeeting.meetingAddress.constituency.constituencyId in (:locationValues) ");
			
		}else if(committeeBO.getTehsilIds()!= null && committeeBO.getTehsilIds().size()>0){
			
			sbM.append(" and model.partyMeeting.meetingAddress.tehsil.tehsilId in (:locationValues) ");
		}
		
 		if(committeeBO.getStartDate()!= null && committeeBO.getEndDate()!=null){
			 sbM.append(" and date(model.partyMeeting.startDate) between :startDate and :endDate ");	 
		 }	
		if(committeeBO.getStateId()!= null && committeeBO.getStateId() > 0l ){
			sbM.append(" and model.partyMeeting.meetingAddress.state.stateId = :stateId ");
		}
		if(committeeBO.getPartyMeetingTypeIds()!=null && committeeBO.getPartyMeetingTypeIds().size()>0){
			sbM.append(" and model.partyMeeting.partyMeetingTypeId in (:partyMeetingTypeIds) ");
		}
		
		StringBuilder sbf = new StringBuilder().append(sbS).append(sbM).append(sbE);
		
		Query query = getSession().createQuery(sbf.toString());
		
		if(committeeBO.getPartyMeetingTypeIds()!=null && committeeBO.getPartyMeetingTypeIds().size()>0){
			query.setParameterList("partyMeetingTypeIds",committeeBO.getPartyMeetingTypeIds());
		}
		
		if(committeeBO.getStateId()!= null && committeeBO.getStateId() > 0l ){
			query.setParameter("stateId",committeeBO.getStateId());
		}
		if(committeeBO.getStartDate()!= null && committeeBO.getEndDate()!=null){
			query.setDate("startDate",committeeBO.getStartDate());
			query.setDate("endDate",committeeBO.getEndDate());
		}
		//locations
		if(committeeBO.getStateIds()!=null && committeeBO.getStateIds().size()>0){
			query.setParameterList("locationValues",committeeBO.getStateIds());
		}
		else if(committeeBO.getDistrictIds() != null && committeeBO.getDistrictIds().size()>0){
			query.setParameterList("locationValues",committeeBO.getDistrictIds());
		}else if(committeeBO.getParliamentConstIds() != null && committeeBO.getParliamentConstIds().size()>0){
			query.setParameterList("locationValues",committeeBO.getParliamentConstIds());
		}else if(committeeBO.getAssemblyConstIds() != null && committeeBO.getAssemblyConstIds().size()>0){
			query.setParameterList("locationValues",committeeBO.getAssemblyConstIds());
		}else if(committeeBO.getTehsilIds()!= null && committeeBO.getTehsilIds().size()>0){
			query.setParameterList("locationValues",committeeBO.getTehsilIds());
		}
		return query.list();
    }
    public List<Object[]> getPartyMeetingCntDetailstLevelWiseByUserAccessLevel(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> partyMeetingTypeValues,String levelType)
	{
		  StringBuilder queryStr= new StringBuilder();
		
		  queryStr.append("select ");
	      
		   queryStr.append(" model.partyMeeting.partyMeetingLevel.partyMeetingLevelId,");
		
		   if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
			if(userAccessLevelValues != null && userAccessLevelValues.size()==1){ 
			  queryStr.append(" model.partyMeeting.meetingAddress.district.districtId,model.partyMeeting.meetingAddress.district.districtName, "); 
		    }else{
	         queryStr.append(" model.partyMeeting.meetingAddress.state.stateId,model.partyMeeting.meetingAddress.state.stateName,");  
	       }
		  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
			  if(userAccessLevelValues != null && userAccessLevelValues.size()==1){
			  queryStr.append(" model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId,model.partyMeeting.meetingAddress.parliamentConstituency.name, "); 
		  }else{
			  queryStr.append(" model.partyMeeting.meetingAddress.district.districtId,model.partyMeeting.meetingAddress.district.districtName, ");  
	      }
		  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
			if(userAccessLevelValues != null && userAccessLevelValues.size()==1){
			  queryStr.append(" model.partyMeeting.meetingAddress.constituency.constituencyId,model.partyMeeting.meetingAddress.constituency.name, "); 
		  }else{
			  queryStr.append(" model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId,model.partyMeeting.meetingAddress.parliamentConstituency.name, ");  
	       }
		  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
			  if(userAccessLevelValues != null && userAccessLevelValues.size() == 1){
				  if(levelType != null && levelType.equalsIgnoreCase("tehsil")){
					  if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
						   queryStr.append(" model.partyMeeting.meetingAddress.tehsil.tehsilId,model.partyMeeting.meetingAddress.tehsil.tehsilName,");  //Assembley Sub level 
					  }
				  } 
				  if(levelType != null && levelType.equalsIgnoreCase("townDivision")){
					  if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
						  queryStr.append(" model.partyMeeting.meetingAddress.localElectionBody.localElectionBodyId,model.partyMeeting.meetingAddress.localElectionBody.name,");   //Assembley Sub level 
					  }  
				  }  
			  }else{
				 queryStr.append(" model.partyMeeting.meetingAddress.constituency.constituencyId,model.partyMeeting.meetingAddress.constituency.name, ");  
			  }
		  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID ){
			  if(userAccessLevelValues != null && userAccessLevelValues.size()==1){
				  queryStr.append(" model.partyMeeting.meetingAddress.panchayat.panchayatId,model.partyMeeting.meetingAddress.panchayat.panchayatName,");	  
			  }else{
				  queryStr.append(" model.partyMeeting.meetingAddress.tehsil.tehsilId,model.partyMeeting.meetingAddress.tehsil.tehsilName,");  
		      }
		  }if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){
			  if(userAccessLevelValues != null && userAccessLevelValues.size()==1){
				  queryStr.append(" model.partyMeeting.meetingAddress.ward.constituencyId,model.partyMeeting.meetingAddress.ward.name,"); //ward
			  }else{
				  queryStr.append(" model.partyMeeting.meetingAddress.localElectionBody.localElectionBodyId,model.partyMeeting.meetingAddress.localElectionBody.name,"); //  town/division 
		      }
		  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){
			  queryStr.append(" model.partyMeeting.meetingAddress.panchayat.panchayatId,model.partyMeeting.meetingAddress.panchayat.panchayatName,"); 
		  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){
			  queryStr.append(" model.partyMeeting.meetingAddress.ward.constituencyId,model.partyMeeting.meetingAddress.ward.name,"); 
		  }
		
		  queryStr.append(" model.mettingStatus,count(distinct model.partyMeeting.partyMeetingId) " +
		           " from PartyMeetingStatus model " +
		           " where " +
		           " model.partyMeeting.isActive='Y' ");   
         if(stateId != null && stateId.longValue() > 0){
			 queryStr.append(" and model.partyMeeting.meetingAddress.state.stateId=:stateId ");
		 }
		  if(fromDate!= null && toDate!=null){
			  queryStr.append(" and date(model.partyMeeting.startDate) between :fromDate and :toDate ");	 
		 }
		 if(partyMeetingTypeValues != null && partyMeetingTypeValues.size() > 0){
			queryStr.append(" and model.partyMeeting.partyMeetingType.partyMeetingTypeId in (:partyMeetingTypeValues)");
		 }
		  
	    if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
	      queryStr.append(" and model.partyMeeting.meetingAddress.state.stateId in (:userAccessLevelValues)");  
	    }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
	      queryStr.append(" and model.partyMeeting.meetingAddress.district.districtId in (:userAccessLevelValues)");  
	    }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
	        queryStr.append(" and model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
	         queryStr.append(" and model.partyMeeting.meetingAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
	      queryStr.append(" and model.partyMeeting.meetingAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
	      queryStr.append(" and model.partyMeeting.meetingAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
	      queryStr.append(" and model.partyMeeting.meetingAddress.panchayat.panchayatId in (:userAccessLevelValues)"); 
		}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
	      queryStr.append(" and model.partyMeeting.meetingAddress.ward.constituencyId in (:userAccessLevelValues)"); 
		}
       
          queryStr.append(" group by model.partyMeeting.partyMeetingLevel.partyMeetingLevelId ");
		  
		  if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
				if(userAccessLevelValues != null && userAccessLevelValues.size()==1){ 
					 queryStr.append(" ,model.partyMeeting.meetingAddress.district.districtId,model.mettingStatus " +
			  		                " order by model.partyMeeting.partyMeetingLevel.partyMeetingLevelId,model.partyMeeting.meetingAddress.district.districtId  "); 
			    }else{
			    	 queryStr.append("  ,model.partyMeeting.meetingAddress.state.stateId,model.mettingStatus " +
		         		             " order by model.partyMeeting.partyMeetingLevel.partyMeetingLevelId,model.partyMeeting.meetingAddress.state.stateId "); 
		       }
			  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
				  if(userAccessLevelValues != null && userAccessLevelValues.size()==1){
					  queryStr.append("  ,model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId,model.mettingStatus" +
						              "  order by model.partyMeeting.partyMeetingLevel.partyMeetingLevelId,model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId "); 
			  }else{
				  queryStr.append("  ,model.partyMeeting.meetingAddress.district.districtId,model.mettingStatus " +
					  		      " order by model.partyMeeting.partyMeetingLevel.partyMeetingLevelId,model.partyMeeting.meetingAddress.district.districtId ");    
		      }
			  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
				if(userAccessLevelValues != null && userAccessLevelValues.size()==1){
					queryStr.append("  ,model.partyMeeting.meetingAddress.constituency.constituencyId,model.mettingStatus " +
					  	            " order by model.partyMeeting.partyMeetingLevel.partyMeetingLevelId,model.partyMeeting.meetingAddress.constituency.constituencyId ");  
			  }else{
				  queryStr.append("  ,model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId,model.mettingStatus " +
					  		      " order by model.partyMeeting.partyMeetingLevel.partyMeetingLevelId,model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId ");  
		       }
			  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
				  if(userAccessLevelValues != null && userAccessLevelValues.size() == 1){
					  if(levelType != null && levelType.equalsIgnoreCase("tehsil")){
						  if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
							  queryStr.append("  ,model.partyMeeting.meetingAddress.tehsil.tehsilId,model.mettingStatus " +
								   		      " order by model.partyMeeting.partyMeetingLevel.partyMeetingLevelId,model.partyMeeting.meetingAddress.tehsil.tehsilId ");   //Assembley Sub level 
						  }
					  } 
					  if(levelType != null && levelType.equalsIgnoreCase("townDivision")){
						  if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
							  queryStr.append("  ,model.partyMeeting.meetingAddress.localElectionBody.localElectionBodyId,model.mettingStatus " +
								              " order by model.partyMeeting.partyMeetingLevel.partyMeetingLevelId,model.partyMeeting.meetingAddress.localElectionBody.localElectionBodyId");   //Assembley Sub level 
						  }  
					  }  
				  }else{
					 queryStr.append("  ,model.partyMeeting.meetingAddress.constituency.constituencyId,model.mettingStatus " +
						             "  order by model.partyMeeting.partyMeetingLevel.partyMeetingLevelId,model.partyMeeting.meetingAddress.constituency.constituencyId "); 
				  }
			  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID ){
				  if(userAccessLevelValues != null && userAccessLevelValues.size()==1){
					  queryStr.append("  ,model.partyMeeting.meetingAddress.panchayat.panchayatId,model.mettingStatus " +
						  		     " order by model.partyMeeting.partyMeetingLevel.partyMeetingLevelId,model.partyMeeting.meetingAddress.panchayat.panchayatId");	  
				  }else{
					  queryStr.append(" ,model.partyMeeting.meetingAddress.tehsil.tehsilId,model.mettingStatus " +
						  		      " order by model.partyMeeting.partyMeetingLevel.partyMeetingLevelId,model.partyMeeting.meetingAddress.tehsil.tehsilId"); 
			      }
			  }if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){
				  if(userAccessLevelValues != null && userAccessLevelValues.size()==1){
					  queryStr.append("  ,model.partyMeeting.meetingAddress.ward.constituencyId,model.mettingStatus " +
						  		      " order by model.partyMeeting.partyMeetingLevel.partyMeetingLevelId,model.partyMeeting.meetingAddress.ward.constituencyId"); //ward
				  }else{
					  queryStr.append("  ,model.partyMeeting.meetingAddress.localElectionBody.localElectionBodyId,model.mettingStatus" +
						  		      "  order by model.partyMeeting.partyMeetingLevel.partyMeetingLevelId,model.partyMeeting.meetingAddress.localElectionBody.localElectionBodyId"); //  town/division 
			      }
			  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){
				  queryStr.append("  ,model.partyMeeting.meetingAddress.panchayat.panchayatId,model.mettingStatus " +
					  		      " order by model.partyMeeting.partyMeetingLevel.partyMeetingLevelId,model.partyMeeting.meetingAddress.panchayat.panchayatId "); 
			  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){
				  queryStr.append(" ,model.partyMeeting.meetingAddress.ward.constituencyId,model.mettingStatus " +
					  		     " order by model.partyMeeting.partyMeetingLevel.partyMeetingLevelId,model.partyMeeting.meetingAddress.ward.constituencyId ");
			  }
		  
	  	Query query = getSession().createQuery(queryStr.toString());
    	
		 if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
			   query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		 }
		 if(fromDate!= null && toDate!=null){
		   query.setDate("fromDate", fromDate);
		   query.setDate("toDate", toDate);
		 }
		 if(stateId != null && stateId.longValue() > 0){
			 query.setParameter("stateId", stateId);
		 }
		 if(partyMeetingTypeValues != null && partyMeetingTypeValues.size() > 0){
			 query.setParameterList("partyMeetingTypeValues", partyMeetingTypeValues); 
		 }
    return query.list();  
	}
    
    public int deleteAllRecords(){
    	
    	Query query = getSession().createSQLQuery(" delete from party_meeting_status ");
    	return query.executeUpdate();
    }
    public int setPrimaryKeyAutoIncrementToOne(){
    	Query query = getSession().createSQLQuery(" ALTER TABLE party_meeting_status AUTO_INCREMENT = 1 ");
    	return query.executeUpdate();
    }
    
    public int insertPartyofficeAndIvrStatus(){
    	
    	Query query = getSession().createSQLQuery("" +
    	"  INSERT INTO party_meeting_status(party_meeting_id,party_office_status,ivr_status) " +
        "         SELECT PM.party_meeting_id,PM.is_conducted,IVR.is_conducted_by_ivr " +
        "         FROM   party_meeting PM " +
        "         LEFT OUTER JOIN party_meeting_ivr_status IVR ON PM.party_meeting_id = IVR.party_meeting_id "+
        "         WHERE PM.start_date IS NOT NULL AND "+
        "         PM.is_active = 'Y' ");
    	return query.executeUpdate();
    }
    
    public int insertPartyofficeAndIvrStatus1(){
    	
    	Query query = getSession().createSQLQuery("" +
    	"  INSERT INTO party_meeting_status(party_meeting_id,party_office_status,ivr_status,third_party_status) " +
        "         SELECT PM.party_meeting_id,PM.is_conducted,PM.is_conducted_by_ivr,PM.third_party_status " +
        "         FROM   party_meeting PM " +
        "         WHERE PM.start_date IS NOT NULL AND "+
        "         PM.is_active = 'Y' ");
    	return query.executeUpdate();
    }
    
    public int updatePartyMeetingStatus1(){
    	
    	Query query = getSession().createSQLQuery("" +
    	"  UPDATE party_meeting_status SET meeting_status = 'Y' "+
        "  WHERE  party_office_status IS NOT NULL AND " +
        "         ivr_status IS NOT NULL AND "+
        "         party_office_status = 'Y' AND "+
        "         ivr_status = 'Y' " ); 
    	return query.executeUpdate();
    }
    
    public int updatePartyMeetingStatus2(){
    	
    	Query query = getSession().createSQLQuery("" +
    	"  UPDATE party_meeting_status SET meeting_status = 'N' "+ 
		"   WHERE  party_office_status IS NOT NULL AND "+ 
		"	       ivr_status IS NOT NULL AND "+ 
		"	       party_office_status = 'N' AND "+ 
		"	       ivr_status = 'N' "); 
    	return query.executeUpdate();
    	
    }
    public int updatePartyMeetingStatus3(){
    	
    	Query query = getSession().createSQLQuery("" +
    	"  UPDATE party_meeting_status SET meeting_status = 'M' "+
        "   WHERE  party_office_status IS NOT NULL AND "+
        "   ivr_status IS NOT NULL AND "+
        "   party_office_status = 'Y' AND "+
        "  ivr_status = 'N' ");
    	return query.executeUpdate();
    	
    }
    public int updatePartyMeetingStatus4(){
    	
    	Query query = getSession().createSQLQuery("" +
		" UPDATE party_meeting_status SET meeting_status = 'M' "+ 
		" WHERE  party_office_status IS NOT NULL AND "+
		"       ivr_status IS NOT NULL AND "+
		"       party_office_status = 'N' AND "+
		"      ivr_status = 'Y' ");
    	return query.executeUpdate();
    	
    }
    
    public int updatePartyMeetingStatus5(){
    	
    	Query query = getSession().createSQLQuery("" +
		" UPDATE party_meeting_status SET meeting_status = 'Y' "+ 
		" WHERE  party_office_status IS NOT NULL AND "+
		"       ivr_status IS NULL AND "+
		"       party_office_status = 'Y' ");
    	return query.executeUpdate();
    	
    }
    
    public int updatePartyMeetingStatus6(){
    	
    	Query query = getSession().createSQLQuery("" +
		" UPDATE party_meeting_status SET meeting_status = 'N' "+ 
		" WHERE  party_office_status IS NOT NULL AND "+
		"       ivr_status IS NULL AND "+
		"       party_office_status = 'N' ");
    	return query.executeUpdate();
    }
    
    public int updatePartyMeetingStatus7(){
    	
    	Query query = getSession().createSQLQuery("" +
		"UPDATE party_meeting_status SET meeting_status = 'Y' "+ 
		"WHERE  party_office_status IS NULL AND "+
		"       ivr_status IS NOT NULL AND "+
		"       ivr_status = 'Y' ");
    	return query.executeUpdate();
    }
    
   public int updatePartyMeetingStatus8(){
    	
    	Query query = getSession().createSQLQuery("" +
		"UPDATE party_meeting_status SET meeting_status = 'N' "+
		"WHERE  party_office_status IS NULL AND "+
		"       ivr_status IS NOT NULL AND "+
		"       ivr_status = 'N' ");
    	return query.executeUpdate();
    }
   public int updatePartyMeetingStatus9(){
   	
   	Query query = getSession().createSQLQuery("" +
	" UPDATE party_meeting_status SET meeting_status = 'NU' "+ 
	" WHERE  party_office_status IS NULL AND "+
	"	     ivr_status IS NULL ");
   	return query.executeUpdate();
   }
   
   public int updatePartyMeetingStatus10(){
	 	Query query = getSession().createSQLQuery("" +
		" UPDATE party_meeting_status SET meeting_status = 'Y' "+ 
		" WHERE  third_party_status IS NOT NULL AND third_party_status = 'Y' and meeting_status = 'M' ");
		return query.executeUpdate();
   }
   public int updatePartyMeetingStatus11(){
	 	Query query = getSession().createSQLQuery("" +
		" UPDATE party_meeting_status SET meeting_status = 'N' "+ 
		" WHERE  third_party_status IS NOT NULL AND third_party_status = 'N' and meeting_status = 'M' ");
		return query.executeUpdate();
  }
   
   public int setInsertedDate(Date currentDateTime){
	   Query query = getSession().createSQLQuery(" UPDATE party_meeting_status SET inserted_time =:currentDateTime WHERE inserted_time IS NULL ");
	   query.setParameter("currentDateTime", currentDateTime);
	   return query.executeUpdate();
   }
   public Date getMeetingLastUpdatedTime()
	{
		Query query=getSession().createQuery("select max(model.insertedTime) from PartyMeetingStatus model");
		  return (Date) query.uniqueResult();
	} 
   public List<Long> getPartyMeetingCount(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> partyMeetingTypeValues,String meetingStatus,List<Long> PartyMeetingLevelIds,String isComment,Long locationId,String locationType){
		 
		 StringBuilder queryStr = new StringBuilder();
	    	
 	  queryStr.append("select distinct model.partyMeeting.partyMeetingId" +
 	  		         " from PartyMeetingStatus model " +
 	  		         " where " +
 	  		         " model.partyMeeting.isActive='Y' ");
 	   
 	     if(meetingStatus != null && meetingStatus.trim().length() > 0){
 	    	queryStr.append(" and model.mettingStatus=:mettingStatus"); 
 	     }
 	     if(isComment != null && isComment.equalsIgnoreCase("Yes")){
 	    	 queryStr.append(" and model.partyMeeting.remarks is not null ");
 	     }
 	     if(locationType != null && locationType.equalsIgnoreCase("District")){
 	    	queryStr.append(" and model.partyMeeting.meetingAddress.district.districtId=:locationId"); 
 	     }else if(locationType != null && locationType.equalsIgnoreCase("Constituency")){
 	    	queryStr.append(" and model.partyMeeting.meetingAddress.constituency.constituencyId=:locationId"); 
 	     }else if(locationType != null && locationType.equalsIgnoreCase("Mandal")){
 	    	queryStr.append(" and model.partyMeeting.meetingAddress.tehsil.tehsilId=:locationId"); 
 	     }else if(locationType != null && locationType.equalsIgnoreCase("TownDivision")){
 	    	queryStr.append(" and model.partyMeeting.meetingAddress.localElectionBody.localElectionBodyId=:locationId"); 
 	     }
 	     if(PartyMeetingLevelIds != null && PartyMeetingLevelIds.size() > 0){
 	    	 queryStr.append(" and model.partyMeeting.partyMeetingLevel.partyMeetingLevelId in (:partyMeetingLevelIds)");
 	     }
 	      if(stateId != null && stateId.longValue() > 0){
				 queryStr.append(" and model.partyMeeting.meetingAddress.state.stateId=:stateId");
		  }
		  if(fromDate!= null && toDate!=null){
			  queryStr.append(" and date(model.partyMeeting.startDate) between :fromDate and :toDate ");	 
		 }
		 if(partyMeetingTypeValues != null && partyMeetingTypeValues.size() > 0){
				 queryStr.append(" and model.partyMeeting.partyMeetingType.partyMeetingTypeId in (:partyMeetingTypeValues)");
		 }
		 if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		   queryStr.append(" and model.partyMeeting.meetingAddress.state.stateId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		   queryStr.append(" and model.partyMeeting.meetingAddress.district.districtId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
	        queryStr.append(" and model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
	        queryStr.append(" and model.partyMeeting.meetingAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
		    queryStr.append(" and model.partyMeeting.meetingAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
		    queryStr.append(" and model.partyMeeting.meetingAddress.userAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
		    queryStr.append(" and model.partyMeeting.meetingAddress.panchayat.panchayatId in (:userAccessLevelValues)"); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
		    queryStr.append(" and model.partyMeeting.meetingAddress.ward.constituencyId in (:userAccessLevelValues)"); 
		 }
		
	     Query query = getSession().createQuery(queryStr.toString());
	
		 if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
			   query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		 }
		 if(fromDate!= null && toDate!=null){
		   query.setDate("fromDate", fromDate);
		   query.setDate("toDate", toDate);
		 }
		 if(stateId != null && stateId.longValue() > 0){
			 query.setParameter("stateId", stateId);
		 }
		 if(partyMeetingTypeValues != null && partyMeetingTypeValues.size() > 0){
			 query.setParameterList("partyMeetingTypeValues", partyMeetingTypeValues); 
		 }
		 if(PartyMeetingLevelIds != null && PartyMeetingLevelIds.size() > 0){
			 query.setParameterList("partyMeetingLevelIds", PartyMeetingLevelIds); 
		 }
		 if(meetingStatus != null && meetingStatus.trim().length() > 0){
			 query.setParameter("mettingStatus", meetingStatus); 
		 }
		 if(locationId != null && locationId > 0){
			 query.setParameter("locationId", locationId);
		 }
    return query.list(); 
	 } 
   
   
   public List<Object[]> getPartyMeetingComulativeCommentDetails(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> partyMeetingTypeValues,String meetingStatus,List<Long> PartyMeetingLevelIds,String isComment,Long locationId,String locationType,String reportType,String type){
		 
		 StringBuilder queryStr = new StringBuilder();
	    	
		 queryStr.append("select " );
		 if(type != null && !type.equalsIgnoreCase("MeetingDtls")){
			  	if(reportType != null && reportType.equalsIgnoreCase("District")){
			  		queryStr.append(" model1.meetingAddress.district.districtId, model1.meetingAddress.district.districtName, count(distinct model1.partyMeetingId) ");
				}else if(reportType.equalsIgnoreCase("Constituency")){
					queryStr.append(" model1.meetingAddress.constituency.constituencyId, model1.meetingAddress.constituency.name, count(distinct model1.partyMeetingId) ");	
				}
			 }
			 else{
				 queryStr.append(" distinct model.partyMeetingId,model1.meetingAddress.district.districtId ");
			 }
		 
		 queryStr.append(" from PartyMeetingStatus model,PartyMeeting model1 " +
  		         " where " +
  		         " model1.isActive='Y' and model.partyMeetingId=model1.partyMeetingId ");
	  	if(type != null && type.equalsIgnoreCase("commentCount")){
	    	queryStr.append(" and model1.remarks is not null "); 
	     }
	     if(meetingStatus != null && meetingStatus.trim().length() > 0){
	    	queryStr.append(" and model.mettingStatus=:mettingStatus"); 
	     }
	     if(isComment != null && isComment.equalsIgnoreCase("Yes")){
	    	 queryStr.append(" and model.partyMeeting.remarks is not null ");
	     }
	     if(locationType != null && locationType.equalsIgnoreCase("District") && locationId != null && locationId > 0){
	    	queryStr.append(" and model.partyMeeting.meetingAddress.district.districtId=:locationId"); 
	     }else if(locationType != null && locationType.equalsIgnoreCase("Constituency") && locationId != null && locationId > 0){
	    	queryStr.append(" and model.partyMeeting.meetingAddress.constituency.constituencyId=:locationId"); 
	     }else if(locationType != null && locationType.equalsIgnoreCase("Mandal") && locationId != null && locationId > 0){
	    	queryStr.append(" and model.partyMeeting.meetingAddress.tehsil.tehsilId=:locationId"); 
	     }else if(locationType != null && locationType.equalsIgnoreCase("TownDivision") && locationId != null && locationId > 0){
	    	queryStr.append(" and model.partyMeeting.meetingAddress.localElectionBody.localElectionBodyId=:locationId"); 
	     }
	     if(PartyMeetingLevelIds != null && PartyMeetingLevelIds.size() > 0){
	    	 queryStr.append(" and model.partyMeeting.partyMeetingLevel.partyMeetingLevelId in (:partyMeetingLevelIds)");
	     }
	      if(stateId != null && stateId.longValue() > 0){
				 queryStr.append(" and model.partyMeeting.meetingAddress.state.stateId=:stateId");
		  }
		  if(fromDate!= null && toDate!=null){
			  queryStr.append(" and date(model.partyMeeting.startDate) between :fromDate and :toDate ");	 
		 }
		 if(partyMeetingTypeValues != null && partyMeetingTypeValues.size() > 0){
				 queryStr.append(" and model.partyMeeting.partyMeetingType.partyMeetingTypeId in (:partyMeetingTypeValues)");
		 }
		 if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		   queryStr.append(" and model.partyMeeting.meetingAddress.state.stateId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		   queryStr.append(" and model.partyMeeting.meetingAddress.district.districtId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
	        queryStr.append(" and model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
	        queryStr.append(" and model.partyMeeting.meetingAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
		    queryStr.append(" and model.partyMeeting.meetingAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
		    queryStr.append(" and model.partyMeeting.meetingAddress.userAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
		    queryStr.append(" and model.partyMeeting.meetingAddress.panchayat.panchayatId in (:userAccessLevelValues)"); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
		    queryStr.append(" and model.partyMeeting.meetingAddress.ward.constituencyId in (:userAccessLevelValues)"); 
		 }
		
		 if(type != null && !type.equalsIgnoreCase("MeetingDtls")){
			 if(reportType != null && reportType.equalsIgnoreCase("District")){
				queryStr.append(" group by model1.meetingAddress.district.districtId order by model1.meetingAddress.district.districtId ");
			 }else if(reportType.equalsIgnoreCase("Constituency")){
				queryStr.append(" group by model1.meetingAddress.constituency.constituencyId order by model1.meetingAddress.constituency.constituencyId ");
			 }
		 }
	     Query query = getSession().createQuery(queryStr.toString());
	
		 if(userAccessLevelValues != null && userAccessLevelValues.size() > 0){
			   query.setParameterList("userAccessLevelValues", userAccessLevelValues);
		 }
		 if(fromDate!= null && toDate!=null){
		   query.setDate("fromDate", fromDate);
		   query.setDate("toDate", toDate);
		 }
		 if(stateId != null && stateId.longValue() > 0){
			 query.setParameter("stateId", stateId);
		 }
		 if(partyMeetingTypeValues != null && partyMeetingTypeValues.size() > 0){
			 query.setParameterList("partyMeetingTypeValues", partyMeetingTypeValues); 
		 }
		 if(PartyMeetingLevelIds != null && PartyMeetingLevelIds.size() > 0){
			 query.setParameterList("partyMeetingLevelIds", PartyMeetingLevelIds); 
		 }
		 if(meetingStatus != null && meetingStatus.trim().length() > 0){
			 query.setParameter("mettingStatus", meetingStatus); 
		 }
		 if(locationId != null && locationId > 0){
			 query.setParameter("locationId", locationId);
		 }
		 return query.list(); 
	 }
   
   public PartyMeetingStatus getObjectByPartyMeetingId(Long partyMeetingId){
		  Query query = getSession().createQuery("select model" +
		  											" from PartyMeetingStatus model" +
		  											" where model.partyMeeting.partyMeetingId = :partyMeetingId");
		  query.setParameter("partyMeetingId", partyMeetingId);
		  return (PartyMeetingStatus) query.uniqueResult();
	  }
   
   public List<Object[]> getLocationWiseMeetings(List<Long> locationValues,Long locationTypeId){
	   
	   //0-meetingStatus,1-levelId,2-level,3-count
	   StringBuilder sb = new StringBuilder();
	   
	   sb.append(" select model.mettingStatus,model.partyMeeting.partyMeetingLevel.partyMeetingLevelId,model.partyMeeting.partyMeetingLevel.level,count(model.partyMeetingStatusId) " +
		   		" from PartyMeetingStatus model ");
	   
	   if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0){	
	        if(locationTypeId == 4l){
	        	sb.append(" where model.partyMeeting.meetingAddress.constituency.constituencyId in(:locationValues) " +
	        			"and model.partyMeeting.partyMeetingLevel.partyMeetingLevelId in (7,8,4,5,6,3)");
	        }else if(locationTypeId == 3l){
	        	sb.append(" where model.partyMeeting.meetingAddress.district.districtId in(:locationValues) " +
	        			"and model.partyMeeting.partyMeetingLevel.partyMeetingLevelId in (2,3,4,5,6,7,8)");
	        }else if(locationTypeId == 5l){
	        	sb.append(" where model.partyMeeting.meetingAddress.tehsil.tehsilId in(:locationValues) " +
	        			"and model.partyMeeting.partyMeetingLevel.partyMeetingLevelId in (7,8,4,5,6)");
	        }else if(locationTypeId == 6l){
	        	sb.append(" where model.partyMeeting.meetingAddress.panchayat.panchayatId in(:locationValues) " +
	        			"and model.partyMeeting.partyMeetingLevel.partyMeetingLevelId in (7,8)");
	        }else if(locationTypeId==10l){
	        	sb.append(" where model.partyMeeting.meetingAddress.parliamentConstituency.constituencyId in(:locationValues) " +
	        			"and model.partyMeeting.partyMeetingLevel.partyMeetingLevelId in (1,2,3,4,5,7,8,6,9)");
	        }else if(locationTypeId == 7l){
	        	sb.append(" where model.partyMeeting.meetingAddress.localElectionBody.localElectionBodyId in(:locationValues) " +
	        			"and model.partyMeeting.partyMeetingLevel.partyMeetingLevelId in (7,8,4,5,6)");
	        }else if(locationTypeId == 8l){
	        	sb.append(" where model.partyMeeting.meetingAddress.ward.constituencyId in(:locationValues) " +
	        			"and model.partyMeeting.partyMeetingLevel.partyMeetingLevelId in (7,8)");
	        }
	    }
	   
	  /* if(locationtype.equalsIgnoreCase("constituency")){
		   sb.append(" where model.partyMeeting.meetingAddress.constituency.constituencyId = :constituencyId " +
		   		" and model.partyMeeting.partyMeetingLevel.partyMeetingLevelId in (7,8,4,5,6,3) ");
	   }*/
	   
	   sb.append(" group by model.mettingStatus,model.partyMeeting.partyMeetingLevel.partyMeetingLevelId ");
	   
	   Query query = getSession().createQuery(sb.toString());
	   
	   if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0){
		   query.setParameterList("locationValues", locationValues);
	   }
	   
	   return query.list();
   }
   
	public List<Object[]> getLevelWiseMeetingStatusCount(Date fromDate, Date toDate, Long locationTypeId,List<Long> locationValues,String year) {
		StringBuilder sb = new StringBuilder();
		//0-partyMeetingLevelId,1-LevelName,2-meeting Status,3-Meeting Count
		sb.append("select model.partyMeeting.partyMeetingLevel.partyMeetingLevelId,model.partyMeeting.partyMeetingLevel.level ,"
				+ "model.mettingStatus,count(distinct model.partyMeetingId) "
				+ " from PartyMeetingStatus model where "
				+ "  model.partyMeeting.isActive = 'Y' ");
		
		
		if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0){	
 	        if(locationTypeId == 4l){
 	        	sb.append(" and model.partyMeeting.meetingAddress.constituency.constituencyId in(:locationValues) ");
 	        }else if(locationTypeId == 3l){
 	        	sb.append(" and model.partyMeeting.meetingAddress.district.districtId in(:locationValues) ");
 	        }else if(locationTypeId == 5l){
 	        	sb.append(" and model.partyMeeting.meetingAddress.tehsil.tehsilId in(:locationValues) ");
 	        }else if(locationTypeId == 6l){
 	        	sb.append(" and model.partyMeeting.meetingAddress.panchayat.panchayatId in(:locationValues) ");
 	        }
 	    }
		if(year != null && !year.trim().isEmpty()){
			sb.append(" and year(model.insertedTime) =:year ");   
 	    }
		
		if(fromDate !=null && toDate !=null){
   		sb.append(" and (date(model.startDate) between :startDate and  :endDate )");
   	}
		sb.append(" group by model.partyMeeting.partyMeetingLevel.partyMeetingLevelId,model.mettingStatus ");
		Query query = getSession().createQuery(sb.toString());
		if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0){
 	        if(locationTypeId == 4l){
 	        	query.setParameterList("locationValues", locationValues);
 	        }else if(locationTypeId == 3l){
 	        	query.setParameterList("locationValues", locationValues);
 	        }else if(locationTypeId == 5l){
 	        	query.setParameterList("locationValues", locationValues);
 	        }else if(locationTypeId == 6l){
 	        	query.setParameterList("locationValues", locationValues);
 	        }
 	    }
		if(year !=null && !year.trim().isEmpty()){
 			query.setParameter("year", year);
 	    }
		if(fromDate !=null && toDate !=null){
   		query.setDate("startDate", fromDate);
   		query.setDate("endDate", toDate);
   	}
		return query.list();
		
	}
}
