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
	 
	public List<Object[]> getPartyMeetingCountLevelWise(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> partyMeetingTypeValues){
		 
		 StringBuilder queryStr = new StringBuilder();
	    	
   	  queryStr.append("select model.partyMeeting.partyMeetingLevel.partyMeetingLevelId," +
   	  		         " model.partyMeeting.partyMeetingLevel.level," +
   	  		         " model.mettingStatus," +
   	  		         " count(distinct model.partyMeeting.partyMeetingId)  " +
   	  		         " from PartyMeetingStatus model " +
   	  		         " where " +
   	  		         " model.partyMeeting.isActive='Y' ");
   	
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
			sbM.append(" and model.partyMeetingTypeId in (:partyMeetingTypeIds) ");
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
   
}
