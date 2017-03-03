package com.itgrids.partyanalyst.dao.hibernate;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPartyMeetingDAO;
import com.itgrids.partyanalyst.dto.CommitteeInputVO;
import com.itgrids.partyanalyst.dto.PartyMeetingsInputVO;
import com.itgrids.partyanalyst.model.PartyMeeting;
import com.itgrids.partyanalyst.utils.IConstants;


public class PartyMeetingDAO extends GenericDaoHibernate<PartyMeeting,Long> implements IPartyMeetingDAO{

	public PartyMeetingDAO()
	{
		super(PartyMeeting.class);
	}
	
	 public List<Object[]> getAllMeetings(Long meetingType,Long locationLevel,List<Long> stateList,List<Long> districtList,List<Long> constituencyList,List<Long> mandalList,List<Long> townList,List<Long> divisonList,List<Long> villageList,List<Long> wardList,Date startDate,Date endDate,Long meetingLevel){
		 StringBuilder sb = new StringBuilder();
	        
	        sb.append(" select model.partyMeetingType.partyMeetingTypeId," +//0 - MeetingTypeId
	        		" model.partyMeetingType.type, " +						//1 - Meeting Type
	                " model.partyMeetingLevel.partyMeetingLevelId," + 		// 2- Meeting Level Id
	                " model.partyMeetingLevel.level, " + 					// 3 - Meeting Level
	                " model.locationValue," + 								// 4 -- Location Value
	                " date(model.startDate)," + 							// 5 -- Start Date
	                " date(model.endDate)," + 								// 6 -- End Date
	                " model.meetingAddress.userAddressId," +				// 7 -- User Address Id
	                " model.meetingName," + 								// 8 -- Meeting Name
	                " model.partyMeetingId, " ); 							// 9 -- Party Meeting Id
	        
	       
	        sb.append(" model.meetingAddress.state.stateId ");				// 10 -- State Id
	        sb.append(" ,model.meetingAddress.state.stateName ");			// 11 -- State Name
	        
	        sb.append(" ,model.isConducted," +								//12.conducted or NotConducted
	        		" model.conductedDate," +								//13.conducted Date 
	        		" model.remarks ");										//14.remarks
	        
	        
	        if(meetingLevel>=2){
	        	sb.append(" ,model.meetingAddress.district.districtId ");	// 15 -- District Id
	        	sb.append(" ,model.meetingAddress.district.districtName ");	// 16 -- District Name
	        }
	        if(meetingLevel>=3){
	        	sb.append(" ,model.meetingAddress.constituency.constituencyId "); // 17 -- Constituency Id
	        	sb.append(" ,model.meetingAddress.constituency.name "); 		  // 18 -- Constituency Name
	        }
	        
	       
	        
	        
	         sb.append(" from PartyMeeting model " +
	                " where model.isActive='Y' " );
	        if(meetingType!=null && meetingType>0l){
	            sb.append(" and model.partyMeetingType.partyMeetingTypeId=:meetingType ");
	        }
	        
	        if(meetingLevel>0l){
	        	sb.append(" and model.partyMeetingLevel.partyMeetingLevelId=:meetingLevel ");
	        }else if(locationLevel!=null){
	            sb.append(" and model.partyMeetingLevel.partyMeetingLevelId=:locationLevel ");
	        }
	        
	        if(locationLevel!=null && locationLevel==1l){//state level
	            if(stateList.get(0)==0l){
	                sb.append(" and model.meetingAddress.state.stateId is not null ");
	            }else{
	                sb.append(" and model.meetingAddress.state.stateId in (:stateList) ");
	            }
	        }else if(locationLevel!=null && locationLevel==2l){//district level
	            if(stateList.get(0)==0l){
	                sb.append(" and model.meetingAddress.state.stateId is not null ");
	            }else{
	                sb.append(" and model.meetingAddress.state.stateId in (:stateList) ");
	            }
	            if(districtList.get(0)==0l){
	                sb.append(" and model.meetingAddress.district.districtId is not null ");
	            }else{
	                sb.append(" and model.meetingAddress.district.districtId in (:districtList) ");
	            }
	        }else if(locationLevel!=null && locationLevel==3l){//constituency level
	            if(stateList.get(0)==0l){
	                sb.append(" and model.meetingAddress.state.stateId is not null ");
	            }else{
	                sb.append(" and model.meetingAddress.state.stateId in (:stateList) ");
	            }
	            if(districtList.get(0)==0l){
	                sb.append(" and model.meetingAddress.district.districtId is not null ");
	            }else{
	                sb.append(" and model.meetingAddress.district.districtId in (:districtList) ");
	            }
	            if(constituencyList.get(0)==0l){
	                sb.append(" and model.meetingAddress.constituency.constituencyId is not null ");
	            }else{
	                sb.append(" and model.meetingAddress.constituency.constituencyId in (:constituencyList) ");
	            }
	        }else if(locationLevel!=null && locationLevel==4l){//mandal level
	            if(stateList.get(0)==0l){
	                sb.append(" and model.meetingAddress.state.stateId is not null ");
	            }else{
	                sb.append(" and model.meetingAddress.state.stateId in (:stateList) ");
	            }
	            if(districtList.get(0)==0l){
	                sb.append(" and model.meetingAddress.district.districtId is not null ");
	            }else{
	                sb.append(" and model.meetingAddress.district.districtId in (:districtList) ");
	            }
	            if(constituencyList.get(0)==0l){
	                sb.append(" and model.meetingAddress.constituency.constituencyId is not null ");
	            }else{
	                sb.append(" and model.meetingAddress.constituency.constituencyId in (:constituencyList) ");
	            }
	            if(mandalList !=null && mandalList.size()<=0){
	                sb.append(" and model.meetingAddress.tehsil.tehsilId is not null ");
	            }else if(mandalList !=null ){
	                sb.append(" and model.meetingAddress.tehsil.tehsilId in (:mandalList) ");
	            }
	        }else if(locationLevel!=null && locationLevel==5l){//town level
	            if(stateList.get(0)==0l){
	                sb.append(" and model.meetingAddress.state.stateId is not null ");
	            }else{
	                sb.append(" and model.meetingAddress.state.stateId in (:stateList) ");
	            }
	            if(districtList.get(0)==0l){
	                sb.append(" and model.meetingAddress.district.districtId is not null ");
	            }else{
	                sb.append(" and model.meetingAddress.district.districtId in (:districtList) ");
	            }
	            if(constituencyList.get(0)==0l){
	                sb.append(" and model.meetingAddress.constituency.constituencyId is not null ");
	            }else{
	                sb.append(" and model.meetingAddress.constituency.constituencyId in (:constituencyList) ");
	            }
	            if(townList !=null && townList.size()<=0){
	                sb.append(" and model.meetingAddress.localElectionBody.localElectionBodyId is not null ");
	            }else if(townList !=null){
	                sb.append(" and model.meetingAddress.localElectionBody.localElectionBodyId in (:townList) ");
	            }
	        }else if((locationLevel!=null) && (locationLevel==6l || locationLevel==8l)){//divison or ward level
	            if(stateList.get(0)==0l){
	                sb.append(" and model.meetingAddress.state.stateId is not null ");
	            }else{
	                sb.append(" and model.meetingAddress.state.stateId in (:stateList) ");
	            }
	            if(districtList.get(0)==0l){
	                sb.append(" and model.meetingAddress.district.districtId is not null ");
	            }else{
	                sb.append(" and model.meetingAddress.district.districtId in (:districtList) ");
	            }
	            if(constituencyList.get(0)==0l){
	                sb.append(" and model.meetingAddress.constituency.constituencyId is not null ");
	            }else{
	                sb.append(" and model.meetingAddress.constituency.constituencyId in (:constituencyList) ");
	            }
	            if(locationLevel==6l){
	                if(divisonList !=null && divisonList.size()<=0){
	                    sb.append(" and model.meetingAddress.ward.constituencyId is not null ");
	                }else if(divisonList !=null){
	                    sb.append(" and model.meetingAddress.ward.constituencyId in (:divisonList) ");
	                }
	            }else{
	            	 /*if(mandalList !=null && mandalList.size()<=0){
	                    sb.append(" and model.meetingAddress.tehsil.tehsilId is not null ");
	                }else if(mandalList !=null){
	                    sb.append(" and model.meetingAddress.tehsil.tehsilId in (:mandalList) ");
	                }*/
	            	 if(townList !=null && townList.size()<=0){
	 	                sb.append(" and model.meetingAddress.localElectionBody.localElectionBodyId is not null ");
	 	            }else if(townList !=null){
	 	                sb.append(" and model.meetingAddress.localElectionBody.localElectionBodyId in (:townList) ");
	 	            }
	            	
	                if(wardList !=null && wardList.size()<=0){
	                    sb.append(" and model.meetingAddress.ward.constituencyId is not null ");
	                }else if(wardList !=null ){
	                    sb.append(" and model.meetingAddress.ward.constituencyId in (:wardList) ");
	                }
	            }
	            
	        }else if(locationLevel!=null && locationLevel==7l){//village level
	            if(stateList.get(0)==0l){
	                sb.append(" and model.meetingAddress.state.stateId is not null ");
	            }else{
	                sb.append(" and model.meetingAddress.state.stateId in (:stateList) ");
	            }
	            if(districtList.get(0)==0l){
	                sb.append(" and model.meetingAddress.district.districtId is not null ");
	            }else{
	                sb.append(" and model.meetingAddress.district.districtId in (:districtList) ");
	            }
	            if(constituencyList.get(0)==0l){
	                sb.append(" and model.meetingAddress.constituency.constituencyId is not null ");
	            }else{
	                sb.append(" and model.meetingAddress.constituency.constituencyId in (:constituencyList) ");
	            }
	            if(mandalList !=null &&mandalList.size()<=0){
	                sb.append(" and model.meetingAddress.tehsil.tehsilId is not null ");
	            }else if(mandalList !=null ){
	                sb.append(" and model.meetingAddress.tehsil.tehsilId in (:mandalList) ");
	            }
	            if(villageList !=null &&villageList.size()<=0){
	                sb.append(" and model.meetingAddress.panchayat.panchayatId is not null ");
	            }else if(villageList !=null){
	                sb.append(" and model.meetingAddress.panchayat.panchayatId in (:villageList) ");
	            }
	        }
	        
	        if(startDate!=null && endDate!=null){
	            sb.append(" and  ( (date(model.startDate)>=:startDate and date(model.startDate)<=:endDate) or (date(model.endDate)>=:startDate and date(model.endDate)<=:endDate) ) ");
	        }
	        
	        sb.append(" order by model.startDate desc ");
	        
	        Query query = getSession().createQuery(sb.toString());
	        
	        if(meetingType!=null && meetingType>0l){
	            query.setParameter("meetingType", meetingType);
	        }
	        
	        if(meetingLevel>0l){
	        	query.setParameter("meetingLevel", meetingLevel);
	        }
	        else if(locationLevel!=null){
	            query.setParameter("locationLevel", locationLevel);
	        }
	        
	        if(locationLevel!=null && locationLevel==1l){
	            if(stateList.get(0)>0l){
	                query.setParameterList("stateList", stateList);
	            }
	        }
	        
	        if(locationLevel!=null && locationLevel==2l){
	            if(stateList.get(0)>0l){
	                query.setParameterList("stateList", stateList);
	            }
	            if(districtList.get(0)>0l){
	                query.setParameterList("districtList", districtList);
	            }
	        }
	        
	        if(locationLevel!=null && locationLevel==3l){
	            if(stateList.get(0)>0l){
	                query.setParameterList("stateList", stateList);
	            }
	            if(districtList.get(0)>0l){
	                query.setParameterList("districtList", districtList);
	            }
	            if(constituencyList.get(0)>0l){
	                query.setParameterList("constituencyList", constituencyList);
	            }
	        }
	        
	        if(locationLevel!=null && locationLevel==4l){
	            if(stateList.get(0)>0l){
	                query.setParameterList("stateList", stateList);
	            }
	            if(districtList.get(0)>0l){
	                query.setParameterList("districtList", districtList);
	            }
	            if(constituencyList.get(0)>0l){
	                query.setParameterList("constituencyList", constituencyList);
	            }
	            if(mandalList !=null && mandalList.size()>0){
	                query.setParameterList("mandalList", mandalList);
	            }
	        }
	        
	        if(locationLevel!=null && locationLevel==5l){
	            if(stateList.get(0)>0l){
	                query.setParameterList("stateList", stateList);
	            }
	            if(districtList.get(0)>0l){
	                query.setParameterList("districtList", districtList);
	            }
	            if(constituencyList.get(0)>0l){
	                query.setParameterList("constituencyList", constituencyList);
	            }
	            if(townList !=null && townList.size()>0){
	                query.setParameterList("townList", townList);
	            }
	        }
	        
	        if(locationLevel!=null && (locationLevel==6l || locationLevel==8l)){
	            if(locationLevel==6l){
	                if(stateList.get(0)>0l){
	                    query.setParameterList("stateList", stateList);
	                }
	                if(districtList.get(0)>0l){
	                    query.setParameterList("districtList", districtList);
	                }
	                if(constituencyList.get(0)>0l){
	                    query.setParameterList("constituencyList", constituencyList);
	                }
	                if(divisonList !=null && divisonList.size()>0){
	                    query.setParameterList("divisonList", divisonList);
	                }
	            }else{
	                if(stateList.get(0)>0l){
	                    query.setParameterList("stateList", stateList);
	                }
	                if(districtList.get(0)>0l){
	                    query.setParameterList("districtList", districtList);
	                }
	                if(constituencyList.get(0)>0l){
	                    query.setParameterList("constituencyList", constituencyList);
	                }
	                
	                if(townList !=null && townList.size()>0){
	                	query.setParameterList("townList", townList);
	                }
	                
	                /*if(mandalList !=null && mandalList.size()>0){
	                    query.setParameterList("mandalList", mandalList);
	                }*/
	                if(wardList !=null && wardList.size()>0){
	                    query.setParameterList("wardList", wardList);
	                }
	            }
	        }
	        
	        if(locationLevel!=null && locationLevel==7l){
	            if(stateList.get(0)>0l){
	                query.setParameterList("stateList", stateList);
	            }
	            if(districtList.get(0)>0l){
	                query.setParameterList("districtList", districtList);
	            }
	            if(constituencyList.get(0)>0l){
	                query.setParameterList("constituencyList", constituencyList);
	            }
	            if(mandalList !=null && mandalList.size()>0){
	                query.setParameterList("mandalList", mandalList);
	            }
	            if(villageList !=null && villageList.size()>0){
	                query.setParameterList("villageList", villageList);
	            }
	        }
	        
	        if(startDate!=null && endDate!=null){
	            query.setDate("startDate", startDate);
	            query.setDate("endDate", endDate);
	        }
	        return query.list();
	    }
	    
	    public List<Object[]> getPartyMeetingDetailsByMeetingIdList(List<Long> patyMeetingIdsList,Date toDayDate)
	    {
	        StringBuilder sb = new StringBuilder();
	        sb.append(" select distinct PM.partyMeetingId, PM.meetingName, PM.partyMeetingLevel.level, PM.partyMeetingType.type,PM.meetingAddress.localArea  from PartyMeeting PM where PM.partyMeetingId in (:patyMeetingIdsList) " +
	        		" and date(PM.startDate) <= :toDayDate and PM.isActive='Y' ");
	        Query query = getSession().createQuery(sb.toString());
	        query.setDate("toDayDate", toDayDate);
	        if(patyMeetingIdsList!=null && patyMeetingIdsList.size()>0){
	            query.setParameterList("patyMeetingIdsList", patyMeetingIdsList);
	        }
	        
	        return query.list();
	    }
	    
	    @SuppressWarnings("unchecked")
		//public List<Long> getPartyMeetingIdsByLevelAndLocation(Long partyMeetingLevelId,Date fromDate,Date toDate,Long partyMeetingTypeId,Long locationLevelId,Long locationValue)
	    public List<Long> getPartyMeetingIdsByLevelAndLocation(Long partyMeetingLevelId,Date fromDate,Date toDate,Long partyMeetingTypeId,Long locationLevelId,List<Long> stateList,List<Long> districtList,List<Long> constituencyList,List<Long> mandalList,List<Long> townList,List<Long> divisonList,List<Long> villageList,List<Long> wardList)
	    {
	    	StringBuilder sb = new StringBuilder("SELECT model.partyMeetingId FROM PartyMeeting model where ( date(model.startDate) between date(:fromDate) and date(:toDate) )and (date(model.endDate) between date(:fromDate) and date(:toDate) ) " +
	    			"  and model.isActive='Y'  ");
	    	//StringBuilder sb = new StringBuilder("SELECT model.partyMeetingId FROM PartyMeeting model where model.partyMeetingLevel.partyMeetingLevelId = :partyMeetingLevelId and date(model.startDate) between date(:fromDate) and date(:toDate) and date(model.endDate) between date(:fromDate) and date(:toDate)");
	    	
	    	if(partyMeetingTypeId != null && partyMeetingTypeId.longValue() != 0)
	    		sb.append(" and model.partyMeetingType.partyMeetingTypeId = :partyMeetingTypeId");
	    		
	    	if(locationLevelId!=null && locationLevelId==1l)
	    	{
	    		if(stateList.get(0)==0l){
	    			sb.append(" and model.meetingAddress.state.stateId is not null ");
	    		}else{
	    			sb.append(" and model.meetingAddress.state.stateId in (:stateList) ");
	    		}
	    	}
	    	else if(locationLevelId!=null && locationLevelId==2l)
	    	{
	    		if(stateList.get(0)==0l){
	    			sb.append(" and model.meetingAddress.state.stateId is not null ");
	    		}else{
	    			sb.append(" and model.meetingAddress.state.stateId in (:stateList) ");
	    		}
	    		if(districtList.get(0)==0l){
	    			sb.append(" and model.meetingAddress.district.districtId is not null ");
	    		}else{
	    			sb.append(" and model.meetingAddress.district.districtId in (:districtList) ");
	    		}
	    	}
	    	else if(locationLevelId!=null && locationLevelId==3l)
	    	{
	    		if(stateList.get(0)==0l){
	    			sb.append(" and model.meetingAddress.state.stateId is not null ");
	    		}else{
	    			sb.append(" and model.meetingAddress.state.stateId in (:stateList) ");
	    		}
	    		if(districtList.get(0)==0l){
	    			sb.append(" and model.meetingAddress.district.districtId is not null ");
	    		}else{
	    			sb.append(" and model.meetingAddress.district.districtId in (:districtList) ");
	    		}
	    		if(constituencyList.get(0)==0l){
	    			sb.append(" and model.meetingAddress.constituency.constituencyId is not null ");
	    		}else{
	    			sb.append(" and model.meetingAddress.constituency.constituencyId in (:constituencyList) ");
	    		}
	    	}
	    	else if(locationLevelId!=null && locationLevelId==4l)
	    	{
	    		if(stateList.get(0)==0l){
	    			sb.append(" and model.meetingAddress.state.stateId is not null ");
	    		}else{
	    			sb.append(" and model.meetingAddress.state.stateId in (:stateList) ");
	    		}
	    		if(districtList.get(0)==0l){
	    			sb.append(" and model.meetingAddress.district.districtId is not null ");
	    		}else{
	    			sb.append(" and model.meetingAddress.district.districtId in (:districtList) ");
	    		}
	    		if(constituencyList.get(0)==0l){
	    			sb.append(" and model.meetingAddress.constituency.constituencyId is not null ");
	    		}else{
	    			sb.append(" and model.meetingAddress.constituency.constituencyId in (:constituencyList) ");
	    		}
	    		if(mandalList.get(0)==0l){
	    			sb.append(" and model.meetingAddress.tehsil.tehsilId is not null ");
	    		}else{
	    			sb.append(" and model.meetingAddress.tehsil.tehsilId in (:mandalList) ");
	    		}
	    	}
	    	else if(locationLevelId!=null && locationLevelId==5l)
	    	{
	    		if(stateList.get(0)==0l){
	    			sb.append(" and model.meetingAddress.state.stateId is not null ");
	    		}else{
	    			sb.append(" and model.meetingAddress.state.stateId in (:stateList) ");
	    		}
	    		if(districtList.get(0)==0l){
	    			sb.append(" and model.meetingAddress.district.districtId is not null ");
	    		}else{
	    			sb.append(" and model.meetingAddress.district.districtId in (:districtList) ");
	    		}
	    		if(constituencyList.get(0)==0l){
	    			sb.append(" and model.meetingAddress.constituency.constituencyId is not null ");
	    		}else{
	    			sb.append(" and model.meetingAddress.constituency.constituencyId in (:constituencyList) ");
	    		}
	    		if(townList.get(0)==0l){
	    			sb.append(" and model.meetingAddress.localElectionBody.localElectionBodyId is not null ");
	    		}else{
	    			sb.append(" and model.meetingAddress.localElectionBody.localElectionBodyId in (:townList) ");
	    		}
	    	}
	    	else if((locationLevelId!=null) && (locationLevelId==6l || locationLevelId==8l))
	    	{
	    		if(stateList.get(0)==0l){
	    			sb.append(" and model.meetingAddress.state.stateId is not null ");
	    		}else{
	    			sb.append(" and model.meetingAddress.state.stateId in (:stateList) ");
	    		}
	    		if(districtList.get(0)==0l){
	    			sb.append(" and model.meetingAddress.district.districtId is not null ");
	    		}else{
	    			sb.append(" and model.meetingAddress.district.districtId in (:districtList) ");
	    		}
	    		if(constituencyList.get(0)==0l){
	    			sb.append(" and model.meetingAddress.constituency.constituencyId is not null ");
	    		}else{
	    			sb.append(" and model.meetingAddress.constituency.constituencyId in (:constituencyList) ");
	    		}
	    		if(locationLevelId==6l){
	    			if(divisonList.get(0)==0l){
	    				sb.append(" and model.meetingAddress.ward.constituencyId is not null ");
	    			}else{
	    				sb.append(" and model.meetingAddress.ward.constituencyId in (:divisonList) ");
	    			}
	    		}else{
	    			if(mandalList.get(0)==0l){
	    				sb.append(" and model.meetingAddress.tehsil.tehsilId is not null ");
	    			}else{
	    				sb.append(" and model.meetingAddress.tehsil.tehsilId in (:mandalList) ");
	    			}
	    			if(wardList.get(0)==0l){
	    				sb.append(" and model.meetingAddress.ward.constituencyId is not null ");
	    			}else{
	    				sb.append(" and model.meetingAddress.ward.constituencyId in (:wardList) ");
	    			}
	    		}
	    	}
	    	else if(locationLevelId!=null && locationLevelId==7l)
	    	{
	    		if(stateList.get(0)==0l){
	    			sb.append(" and model.meetingAddress.state.stateId is not null ");
	    		}else{
	    			sb.append(" and model.meetingAddress.state.stateId in (:stateList) ");
	    		}
	    		if(districtList.get(0)==0l){
	    			sb.append(" and model.meetingAddress.district.districtId is not null ");
	    		}else{
	    			sb.append(" and model.meetingAddress.district.districtId in (:districtList) ");
	    		}
	    		if(constituencyList.get(0)==0l){
	    			sb.append(" and model.meetingAddress.constituency.constituencyId is not null ");
	    		}else{
	    			sb.append(" and model.meetingAddress.constituency.constituencyId in (:constituencyList) ");
	    		}
	    		if(mandalList.get(0)==0l){
	    			sb.append(" and model.meetingAddress.tehsil.tehsilId is not null ");
	    		}else{
	    			sb.append(" and model.meetingAddress.tehsil.tehsilId in (:mandalList) ");
	    		}
	    		if(villageList.get(0)==0l){
	    			sb.append(" and model.meetingAddress.panchayat.panchayatId is not null ");
	    		}else{
	    			sb.append(" and model.meetingAddress.panchayat.panchayatId in (:villageList) ");
	    		}
	    	}
	    	
	    	Query query = getSession().createQuery(sb.toString());
	    	//query.setParameter("partyMeetingLevelId",partyMeetingLevelId);
	    	query.setParameter("fromDate",fromDate);
	    	query.setParameter("toDate",toDate);
	    	
	    	if(partyMeetingTypeId != null && partyMeetingTypeId.longValue() != 0)
	    		query.setParameter("partyMeetingTypeId",partyMeetingTypeId);
	    	
	    	if(locationLevelId!=null && locationLevelId==1l){
	            if(stateList.get(0)>0l){
	                query.setParameterList("stateList", stateList);
	            }
	        }
	        
	        if(locationLevelId!=null && locationLevelId==2l){
	            if(stateList.get(0)>0l){
	                query.setParameterList("stateList", stateList);
	            }
	            if(districtList.get(0)>0l){
	                query.setParameterList("districtList", districtList);
	            }
	        }
	        
	        if(locationLevelId!=null && locationLevelId==3l){
	            if(stateList.get(0)>0l){
	                query.setParameterList("stateList", stateList);
	            }
	            if(districtList.get(0)>0l){
	                query.setParameterList("districtList", districtList);
	            }
	            if(constituencyList.get(0)>0l){
	                query.setParameterList("constituencyList", constituencyList);
	            }
	        }
	        
	        if(locationLevelId!=null && locationLevelId==4l){
	            if(stateList.get(0)>0l){
	                query.setParameterList("stateList", stateList);
	            }
	            if(districtList.get(0)>0l){
	                query.setParameterList("districtList", districtList);
	            }
	            if(constituencyList.get(0)>0l){
	                query.setParameterList("constituencyList", constituencyList);
	            }
	            if(mandalList.get(0)>0l){
	                query.setParameterList("mandalList", mandalList);
	            }
	        }
	        
	        if(locationLevelId!=null && locationLevelId==5l){
	            if(stateList.get(0)>0l){
	                query.setParameterList("stateList", stateList);
	            }
	            if(districtList.get(0)>0l){
	                query.setParameterList("districtList", districtList);
	            }
	            if(constituencyList.get(0)>0l){
	                query.setParameterList("constituencyList", constituencyList);
	            }
	            if(townList.get(0)>0){
	                query.setParameterList("townList", townList);
	            }
	        }
	        
	        if(locationLevelId!=null && (locationLevelId==6l || locationLevelId==8l)){
	            if(locationLevelId==6l){
	                if(stateList.get(0)>0l){
	                    query.setParameterList("stateList", stateList);
	                }
	                if(districtList.get(0)>0l){
	                    query.setParameterList("districtList", districtList);
	                }
	                if(constituencyList.get(0)>0l){
	                    query.setParameterList("constituencyList", constituencyList);
	                }
	                if(divisonList.get(0)>0l){
	                    query.setParameterList("divisonList", divisonList);
	                }
	            }else{
	                if(stateList.get(0)>0l){
	                    query.setParameterList("stateList", stateList);
	                }
	                if(districtList.get(0)>0l){
	                    query.setParameterList("districtList", districtList);
	                }
	                if(constituencyList.get(0)>0l){
	                    query.setParameterList("constituencyList", constituencyList);
	                }
	                if(mandalList.get(0)>0l){
	                    query.setParameterList("mandalList", mandalList);
	                }
	                if(wardList.get(0)==0l){
	                    query.setParameterList("wardList", wardList);
	                }
	            }
	        }
	        
	        if(locationLevelId!=null && locationLevelId==7l){
	            if(stateList.get(0)>0l){
	                query.setParameterList("stateList", stateList);
	            }
	            if(districtList.get(0)>0l){
	                query.setParameterList("districtList", districtList);
	            }
	            if(constituencyList.get(0)>0l){
	                query.setParameterList("constituencyList", constituencyList);
	            }
	            if(mandalList.get(0)>0l){
	                query.setParameterList("mandalList", mandalList);
	            }
	            if(villageList.get(0)>0l){
	                query.setParameterList("villageList", villageList);
	            }
	        }
	        
	    	return query.list();
	    }
	    
	    public List<Object[]> invaildGetMontlyWiseMeetingsDetails(Long committeeLevelId,List<Long> committeeLevelValueList,Date fromDate,Date toDate,int firstRecord, int maxResult)
		{
			StringBuilder queryStr = new StringBuilder();
			
				queryStr.append(" select model.locationValue , model.partyMeetingId ");
				queryStr.append("");
				if(committeeLevelId != null && committeeLevelId.longValue()>0L)
				{
					if(committeeLevelId.longValue() == IConstants.VILLAGE_COMMITTEE_LEVEL_ID)
					{ 
						queryStr.append(", T.tehsilId, T.tehsilName,P.panchayatName,model.startDate from PartyMeeting model ," +
								"   Booth B left join B.panchayat P    left join B.tehsil T where  model.locationValue = P.panchayatId  and model.isActive='Y'  " +
								" and B.publicationDate.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+"  ");
						queryStr.append(" and model.partyMeetingLevelId = "+IConstants.VILLAGE_PARTY_MEETING_LEVEL_ID+" and  model.partyMeetingTypeId = "+IConstants.MONTHLY_VILLAGEorWARD_MEETING_ID+"  ");
					}
					else if(committeeLevelId.longValue() == IConstants.WARD_COMMITTEE_LEVEL_ID)
					{
						queryStr.append(", LEB.localElectionBodyId, concat(LEB.name,' ', LEB.electionType.electionType ) ,C.name,model.startDate from PartyMeeting model ," +
								"   Constituency C left join C.localElectionBody  LEB   where  model.locationValue = C.constituencyId ");
						
						queryStr.append(" and model.partyMeetingLevelId = "+IConstants.WARD_PARTY_MEETING_LEVEL_ID+"   and model.partyMeetingTypeId = "+IConstants.MONTHLY_VILLAGEorWARD_MEETING_ID+" ");
					}
					else if(committeeLevelId.longValue() == IConstants.MANDAL_COMMITTEE_LEVEL_ID)
					{
						queryStr.append(" C.constituencyId, C.name,T.tehsilName,, model.startDate from PartyMeeting model, Booth B left join B.tehsil T left join B.constituency C where model.locationValue = T.tehsilId and B.publicationDate.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+" ");
						queryStr.append(" and model.partyMeetingLevelId = "+IConstants.MANDAL_PARTY_MEETING_LEVEL_ID+"   and model.partyMeetingTypeId = "+IConstants.MONTHLY_MANDAL_TOWN_DIVISION_MEETING_ID+"  ");
					}
					else if(committeeLevelId.longValue() == IConstants.TOWN_COMMITTEE_LEVEL_ID)
					{
						queryStr.append(" C.constituencyId, C.name,concat(LEB.name,' ',LEB.electionType.electionType), model.startDate from PartyMeeting model, Booth B left join B.localBody LEB left join B.constituency C where model.locationValue = LEB.localElectionBodyId and B.publicationDate.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+" ");
						queryStr.append(" and model.partyMeetingLevelId = "+IConstants.TOWN_PARTY_MEETING_LEVEL_ID+"   and model.partyMeetingTypeId = "+IConstants.MONTHLY_MANDAL_TOWN_DIVISION_MEETING_ID+"  ");
					}
					else if(committeeLevelId.longValue() == IConstants.DIVISION_COMMITTEE_LEVEL_ID)
					{
						queryStr.append(" C.constituencyId, C.name,concat(LEB.name,' ',LEB.electionType.electionType), model.startDate from PartyMeeting model, Booth B left join B.localBody LEB left join B.constituency C where model.locationValue = LEB.localElectionBodyId and B.publicationDate.publicationDateId = "+IConstants.VOTER_DATA_PUBLICATION_ID+" ");
						queryStr.append(" and model.partyMeetingLevelId = "+IConstants.DIVISION_PARTY_MEETING_LEVEL_ID+"  and model.partyMeetingTypeId = "+IConstants.MONTHLY_MANDAL_TOWN_DIVISION_MEETING_ID+"  ");
					}
					else if(committeeLevelId.longValue() == IConstants.CONSTITUENCY_COMMITTEE_LEVEL_ID) // inchargers
					{
						queryStr.append(", C.district.districtId, C.district.districtName,c.name,model.startDate from PartyMeeting model ," +
								"   Constituency C where  model.locationValue = C.constituencyId  ");
						queryStr.append(" and model.partyMeetingLevelId = "+IConstants.CONSTITUENCY_PARTY_MEETING_LEVEL_ID+"   and model.partyMeetingTypeId = "+IConstants.MONTHLY_CONSTITUENCY_MEETING_ID+"  ");
					}
					else if(committeeLevelId.longValue() == IConstants.DISTRICT_COMMITTEE_LEVEL_ID)
					{
						queryStr.append(", D.state.stateId, D.state.stateName,D.districtName,model.startDate from PartyMeeting model ," +
								"   District C where  model.locationValue = D.districtId  ");
						queryStr.append(" and model.partyMeetingLevelId = "+IConstants.DISTRICT_PARTY_MEETING_LEVEL_ID+"  and model.partyMeetingTypeId = "+IConstants.MONTHLY_DISTRICT_MEETING_ID+"   ");
					}
					else if(committeeLevelId.longValue() == IConstants.STATE_COMMITTEE_LEVEL_ID)
					{
						queryStr.append(", 0, '',S.stateName,model.startDate from PartyMeeting model ," +
								"   State S where  model.locationValue = S.stateId  ");
						queryStr.append(" and model.partyMeetingLevelId = "+IConstants.STATE_PARTY_MEETING_LEVEL_ID+"  and model.partyMeetingTypeId = "+IConstants.MONTHLY_STATE_MEETING_ID+"  ");
					}
				}
				
				if(committeeLevelValueList != null && committeeLevelValueList.size()>0)
					queryStr.append(" and model.locationValue in (:committeeLevelValueList) ");
				
				if(fromDate != null && toDate != null)
				{
					queryStr.append(" and (model.startDate >= :fromDate and model.endDate <= :toDate) ");
				}
				queryStr.append(" and model.isActive = 'Y' and model.partyMeetingTypeId ");
				if(committeeLevelId.longValue() == IConstants.VILLAGE_COMMITTEE_LEVEL_ID )
				{ 
					queryStr.append(" order by  T.tehsilName ,P.panchayatName ");
				}
				else if(committeeLevelId.longValue() == IConstants.VILLAGE_COMMITTEE_LEVEL_ID )
				{ 
					queryStr.append(" order by  LEB.name ,C.name ");
				}
			
			Query query = getSession().createQuery(queryStr.toString());
			if(committeeLevelValueList != null && committeeLevelValueList.size()>0)
				query.setParameterList("committeeLevelValueList", committeeLevelValueList);
			if(fromDate != null && toDate != null)
			{
				query.setDate("fromDate", fromDate);
				query.setDate("toDate", toDate);
			}
			
			if(maxResult>0)
			{
				query.setFirstResult(firstRecord);
				query.setMaxResults(maxResult);
			}
			return query.list();
		}
	    public BigInteger getLocationWiseTotalMeetingsCount(Long committeeLevelId,List<Long> committeeLevelValueList,Date fromDate,Date toDate)
		{
			StringBuilder queryStr = new StringBuilder();

				queryStr.append(" select count(model.party_meeting_id) from party_meeting model where model.is_active='Y' ");
				
				if(committeeLevelId != null && committeeLevelId.longValue()>0L)
				{
					if(committeeLevelId.longValue() == IConstants.VILLAGE_COMMITTEE_LEVEL_ID)
					{ 
						queryStr.append(" and model.party_meeting_level_id = "+IConstants.VILLAGE_PARTY_MEETING_LEVEL_ID+" and  model.party_meeting_type_id = "+IConstants.MONTHLY_VILLAGEorWARD_MEETING_ID+"  ");
					}
					else if(committeeLevelId.longValue() == IConstants.WARD_COMMITTEE_LEVEL_ID)
					{
						queryStr.append(" and model.party_meeting_level_id = "+IConstants.WARD_PARTY_MEETING_LEVEL_ID+"   and model.party_meeting_type_id = "+IConstants.MONTHLY_VILLAGEorWARD_MEETING_ID+" ");
					}
					else if(committeeLevelId.longValue() == IConstants.MANDAL_COMMITTEE_LEVEL_ID)
					{
						queryStr.append(" and model.party_meeting_level_id = "+IConstants.MANDAL_PARTY_MEETING_LEVEL_ID+"   and model.party_meeting_type_id = "+IConstants.MONTHLY_MANDAL_TOWN_DIVISION_MEETING_ID+"  ");
					}
					else if(committeeLevelId.longValue() == IConstants.TOWN_COMMITTEE_LEVEL_ID)
					{
						queryStr.append(" and model.party_meeting_level_id = "+IConstants.TOWN_PARTY_MEETING_LEVEL_ID+"   and model.party_meeting_type_id = "+IConstants.MONTHLY_MANDAL_TOWN_DIVISION_MEETING_ID+"  ");
					}
					else if(committeeLevelId.longValue() == IConstants.DIVISION_COMMITTEE_LEVEL_ID)
					{
						queryStr.append(" and model.party_meeting_level_id = "+IConstants.DIVISION_PARTY_MEETING_LEVEL_ID+"  and model.party_meeting_type_id = "+IConstants.MONTHLY_MANDAL_TOWN_DIVISION_MEETING_ID+"  ");
					}
					else if(committeeLevelId.longValue() == IConstants.CONSTITUENCY_COMMITTEE_LEVEL_ID) // inchargers
					{
						queryStr.append(" and model.party_meeting_level_id = "+IConstants.CONSTITUENCY_PARTY_MEETING_LEVEL_ID+"   and model.party_meeting_type_id = "+IConstants.MONTHLY_CONSTITUENCY_MEETING_ID+"  ");
					}
					else if(committeeLevelId.longValue() == IConstants.DISTRICT_COMMITTEE_LEVEL_ID)
					{
						queryStr.append(" and model.party_meeting_level_id = "+IConstants.DISTRICT_PARTY_MEETING_LEVEL_ID+"  and model.party_meeting_type_id = "+IConstants.MONTHLY_DISTRICT_MEETING_ID+"   ");
					}
					else if(committeeLevelId.longValue() == IConstants.STATE_COMMITTEE_LEVEL_ID)
					{
						queryStr.append(" and model.party_meeting_level_id = "+IConstants.STATE_PARTY_MEETING_LEVEL_ID+"  and model.party_meeting_type_id = "+IConstants.MONTHLY_STATE_MEETING_ID+"  ");
					}
				}
				
				if(committeeLevelValueList != null && committeeLevelValueList.size()>0)
					queryStr.append(" and model.location_value in (:committeeLevelValueList) ");
				
				if(fromDate != null && toDate != null)
				{
					//queryStr.append(" and (model.start_date >= :fromDate and model.end_date <= :toDate) ");
					queryStr.append(" and (model.start_date between :fromDate and :toDate) ");
				}
			
			Query query = getSession().createSQLQuery(queryStr.toString());
			if(committeeLevelValueList != null && committeeLevelValueList.size()>0)
				query.setParameterList("committeeLevelValueList", committeeLevelValueList);
			if(fromDate != null && toDate != null)
			{
				query.setDate("fromDate", fromDate);
				query.setDate("toDate", toDate);
			}
			
			return (BigInteger) query.uniqueResult();
		}
	    
	    public List<Object[]> getMontlyWiseMeetingsDetails(Long committeeLevelId,List<Long> committeeLevelValueList,Date fromDate,Date toDate,List<String> searchDatesList,int firstRecord,int maxResult)
		{
			StringBuilder queryStr = new StringBuilder();
			
				queryStr.append(" select date(model.start_date) , count(model.party_meeting_id) from party_meeting model where model.is_active='Y' ");
				queryStr.append("");
				if(committeeLevelId != null && committeeLevelId.longValue()>0L)
				{
					if(committeeLevelId.longValue() == IConstants.VILLAGE_COMMITTEE_LEVEL_ID)
					{ 
						queryStr.append(" and model.party_meeting_level_id = "+IConstants.VILLAGE_PARTY_MEETING_LEVEL_ID+" and  model.party_meeting_type_id = "+IConstants.MONTHLY_VILLAGEorWARD_MEETING_ID+"  ");
					}
					else if(committeeLevelId.longValue() == IConstants.WARD_COMMITTEE_LEVEL_ID)
					{
						queryStr.append(" and model.party_meeting_level_id = "+IConstants.WARD_PARTY_MEETING_LEVEL_ID+"   and model.party_meeting_type_id = "+IConstants.MONTHLY_VILLAGEorWARD_MEETING_ID+" ");
					}
					else if(committeeLevelId.longValue() == IConstants.MANDAL_COMMITTEE_LEVEL_ID)
					{
						queryStr.append(" and model.party_meeting_level_id = "+IConstants.MANDAL_PARTY_MEETING_LEVEL_ID+"   and model.party_meeting_type_id = "+IConstants.MONTHLY_MANDAL_TOWN_DIVISION_MEETING_ID+"  ");
					}
					else if(committeeLevelId.longValue() == IConstants.TOWN_COMMITTEE_LEVEL_ID)
					{
						queryStr.append(" and model.party_meeting_level_id = "+IConstants.TOWN_PARTY_MEETING_LEVEL_ID+"   and model.party_meeting_type_id = "+IConstants.MONTHLY_MANDAL_TOWN_DIVISION_MEETING_ID+"  ");
					}
					else if(committeeLevelId.longValue() == IConstants.DIVISION_COMMITTEE_LEVEL_ID)
					{
						queryStr.append(" and model.party_meeting_level_id = "+IConstants.DIVISION_PARTY_MEETING_LEVEL_ID+"  and model.party_meeting_type_id = "+IConstants.MONTHLY_MANDAL_TOWN_DIVISION_MEETING_ID+"  ");
					}
					else if(committeeLevelId.longValue() == IConstants.CONSTITUENCY_COMMITTEE_LEVEL_ID) // inchargers
					{
						queryStr.append(" and model.party_meeting_level_id = "+IConstants.CONSTITUENCY_PARTY_MEETING_LEVEL_ID+"   and model.party_meeting_type_id = "+IConstants.MONTHLY_CONSTITUENCY_MEETING_ID+"  ");
					}
					else if(committeeLevelId.longValue() == IConstants.DISTRICT_COMMITTEE_LEVEL_ID)
					{
						queryStr.append(" and model.party_meeting_level_id = "+IConstants.DISTRICT_PARTY_MEETING_LEVEL_ID+"  and model.party_meeting_type_id = "+IConstants.MONTHLY_DISTRICT_MEETING_ID+"   ");
					}
					else if(committeeLevelId.longValue() == IConstants.STATE_COMMITTEE_LEVEL_ID)
					{
						queryStr.append(" and model.party_meeting_level_id = "+IConstants.STATE_PARTY_MEETING_LEVEL_ID+"  and model.party_meeting_type_id = "+IConstants.MONTHLY_STATE_MEETING_ID+"  ");
					}
				}
				
				if(committeeLevelValueList != null && committeeLevelValueList.size()>0)
					queryStr.append(" and model.location_value in (:committeeLevelValueList) ");
				
				if(searchDatesList != null && searchDatesList.size()>0)					
					queryStr.append(" and  date(model.start_date) in (:searchDatesList) ");
				else if(fromDate != null && toDate != null)
				{
					//queryStr.append(" and (model.start_date >= :fromDate and model.end_date <= :toDate) ");
					queryStr.append(" and (model.start_date between :fromDate and :toDate) ");
				}
				queryStr.append(" group by   date(model.start_date) ");
				queryStr.append(" order by   date(model.start_date) desc ");
			Query query = getSession().createSQLQuery(queryStr.toString());
			
			if(committeeLevelValueList != null && committeeLevelValueList.size()>0)
				query.setParameterList("committeeLevelValueList", committeeLevelValueList);
			
			if(searchDatesList != null && searchDatesList.size()>0)	
				query.setParameterList("searchDatesList", searchDatesList);
			else if(fromDate != null && toDate != null)
			{
				query.setDate("fromDate", fromDate);
				query.setDate("toDate", toDate);
			}
			if(maxResult >0)
			{
				query.setFirstResult(firstRecord);
				query.setMaxResults(maxResult);				
			}
			return query.list();
		}

	    public Object[] getMeetingNameByMeetingId(Long meetingId){
	    	
	    	Query query = getSession().createQuery(" select model.meetingName," +
	    						" model.partyMeetingLevel.level," +
	    						" date(model.startDate)" +
	    						" from PartyMeeting model" +
	    						" where model.partyMeetingId = :meetingId" +
	    						" and model.isActive = 'Y'");
	    	query.setParameter("meetingId", meetingId);
	    	
	    	return (Object[]) query.uniqueResult();
	    }
	    
	    public List<Object[]> getLevelWiseMeetingDetails(Date startDate,Date endDate,String level,List<Long> levelValues){
	    	
	    	StringBuilder str = new StringBuilder();
	    	str.append(" select model.partyMeetingLevel.partyMeetingLevelId,model.partyMeetingLevel.level,model.isConducted " +
	    			" ,count(model.partyMeetingId) " +
	    			" from  PartyMeeting model" +
	    			" where " +
	    			" model.isActive = 'Y'" );
	    	
	    	if(startDate !=null && endDate !=null){
	    		str.append(" and (date(model.startDate) between :startDate and  :endDate )");
	    	}
	    	
	    	if(levelValues !=null && levelValues.size()>0){
	    		if(level !=null && !level.isEmpty() && level.equalsIgnoreCase("STATE")){
		    		
	    			/*if(levelValues !=null && levelValues.size()==1){
	    				if(levelValues.contains(1l)){
	    					str.append(" and (model.meetingAddress.state.stateId = 1) ");
	    				}else if(levelValues.contains(36l)){
	    					str.append(" and (model.meetingAddress.district.districtId between 11 and 23 ) ");
	    				}
	    								
	    			}*/
	    			
	    			str.append(" and (model.meetingAddress.state.stateId in (:levelValues)) "); 
	    							    		
		    	}else if(level !=null && !level.isEmpty() && level.equalsIgnoreCase("DISTRICT")){
		    		
		    		str.append(" and model.meetingAddress.district.districtId in (:levelValues) ");
		    		
		    	}else if(level !=null && !level.isEmpty() && level.equalsIgnoreCase("CONSTITUENCY")){
		    		str.append(" and model.meetingAddress.constituency.constituencyId in (:levelValues) ");
		    	}
		    	else if(level !=null && !level.isEmpty() && level.equalsIgnoreCase("Parliament")){
		    		str.append(" and model.meetingAddress.parliamentConstituency.constituencyId in (:levelValues) ");
		    	}
	    	}
	    	
	    
	    	str.append(" group by  model.partyMeetingLevel.partyMeetingLevelId,model.isConducted " +
	    			" order by model.partyMeetingLevel.orderNo" );
	    	
	    	Query query = getSession().createQuery(str.toString());
	    	
	    	if(startDate !=null && endDate !=null){
	    		query.setParameter("startDate", startDate);
	    		query.setParameter("endDate", endDate);
	    	}
	    	
	    	if(levelValues !=null && levelValues.size()>0 && level !=null && !level.isEmpty() ){	    		
	    			query.setParameterList("levelValues", levelValues);	    			    	
	    	}
	    	
	    	return query.list();
	    }
	    public Integer updateConductedDetails(Long meetingId,String isConducted,String remarks,Date conductedDate){
	    	
	    	StringBuilder str = new StringBuilder();
	    	
	    	str.append(" update PartyMeeting model set ");
	    	
	    	if(isConducted !=null && !isConducted.toString().isEmpty()){
	    		str.append(" model.isConducted =:isConducted,  ");
	    		if(conductedDate !=null){
	    			str.append(" model.conductedDate = :conductedDate, ");
	    		}
	    	}
	    	
	    	str.append(" model.remarks=:remarks " +
	    			" where model.partyMeetingId = :meetingId ");	    	
	    		    	
	    	Query query = getSession().createQuery(str.toString());
	    	
	    	if(isConducted !=null && !isConducted.toString().isEmpty()){
	    		query.setParameter("isConducted",isConducted);
	    		if(conductedDate !=null){
	    			query.setParameter("conductedDate",conductedDate);
	    		}
	    	}
	    
	    	query.setParameter("remarks",remarks);
	    	query.setParameter("meetingId",meetingId);
	    
	    	 Integer c = query.executeUpdate();
	    	
	    	return c; 
	    }
	    
	    public Integer updateConductedStatus(Long meetingId,String isConducted,Long userId,Date presentDate){
	    	StringBuilder str = new StringBuilder();	    	
	    	
	    	str.append(" update PartyMeeting model set ");

	    	str.append(" model.isConducted =:isConducted, ");	    	
	    	
	    	str.append(" model.updatedBy.userId =:userId,model.updatedTime=:presentDate ");
	    	
	    	str.append(" where model.partyMeetingId = :meetingId");
	    	Query query = getSession().createQuery(str.toString());
	    	
	    	query.setParameter("isConducted",isConducted);
	    	query.setParameter("meetingId",meetingId);
	    	
	    	query.setParameter("userId",userId);
	    	query.setParameter("presentDate",presentDate);
	    	
	    	Integer c = query.executeUpdate();
	    	
	    	return c; 	    
	    }
	  
	    public Integer updateConductedDate(Long meetingId,Date conductedDate,Long userId,Date presentDate){
	    	StringBuilder str = new StringBuilder();	    	
	    	
	    	str.append(" update PartyMeeting model set ");

	    	str.append(" model.conductedDate = :conductedDate, ");
    		
	    	str.append("  model.updatedBy.userId =:userId,model.updatedTime=:presentDate ");
	    	str.append(" where model.partyMeetingId = :meetingId ");
	    	
	    	Query query = getSession().createQuery(str.toString());
	    	
    		query.setParameter("conductedDate",conductedDate);
    		
	    	query.setParameter("meetingId",meetingId);
	    	query.setParameter("userId",userId);
	    	query.setParameter("presentDate",presentDate);
	    	Integer c = query.executeUpdate();
	    	
	    	return c; 	    
	    }
	    
	    public Integer updateConductedReason(Long meetingId,String remarks,Long userId,Date presentDate){
	    	StringBuilder str = new StringBuilder();	    	
	    	
	    	str.append(" update PartyMeeting model set ");
	    	
	    	str.append(" model.remarks =:remarks,  ");
	    		
	    	str.append("  model.updatedBy.userId =:userId,model.updatedTime=:presentDate ");
	    	str.append(" where model.partyMeetingId = :meetingId ");
	    	
	    	Query query = getSession().createQuery(str.toString());
	    	query.setParameter("remarks",remarks);
	    	
	    	query.setParameter("meetingId",meetingId);
	    	query.setParameter("userId",userId);
	    	query.setParameter("presentDate",presentDate);
	    	Integer c = query.executeUpdate();
	    	
	    	return c; 	    
	    }
	    
	    //candidate page
        public List<Object[]> getTotalPlannedPartyMeetings(Date startDate,Date endDate,List<Long> locationIds,StringBuilder locationsPart){
        	
        	StringBuilder sb = new StringBuilder();
        	
        	sb.append(" " +
        	"select year(model.startDate),month(model.startDate)," +//1
            "       model.partyMeetingLevel.partyMeetingLevelId,model.partyMeetingLevel.level," +//3
            "       count(model.partyMeetingId)" +//4
        	"from   PartyMeeting model " +
            "where  model.isActive ='Y' and model.startDate is not null ");
        	
        	if(startDate != null && endDate != null){
        		sb.append(" and model.startDate between :startDate and :endDate ");
        	}
        	sb.append(locationsPart);
        	
        	sb.append(" group by year(model.startDate),month(model.startDate),model.partyMeetingLevel.partyMeetingLevelId ");
        	
        	Query query = getSession().createQuery(sb.toString());
        	
        	if(startDate != null && endDate != null){
        		query.setDate("startDate",startDate);
        		query.setDate("endDate",endDate);
        	}
        	query.setParameterList("locationIds",locationIds);
        	
        	return query.list();
        }
        
        public List<Object[]> getConductedNotConductedPartyMeetingsByDPO(Date startDate,Date endDate,List<Long> locationIds,StringBuilder locationsPart){
        	
        
        	StringBuilder sb = new StringBuilder();
        	
        	sb.append(" " +
        	"select year(model.startDate),month(model.startDate)," +//1
            "       model.partyMeetingLevel.partyMeetingLevelId,model.partyMeetingLevel.level," +//3
            "       model.isConducted,count(model.partyMeetingId)" +//5
        	"from   PartyMeeting model " +
            "where  model.isActive ='Y' ");
        	
        	if(startDate != null && endDate != null){
        		sb.append(" and model.startDate between :startDate and :endDate ");
        	}
        	sb.append(locationsPart);
        	
        	sb.append(" group by year(model.startDate),month(model.startDate),model.partyMeetingLevel.partyMeetingLevelId,model.isConducted");
        	
        	Query query = getSession().createQuery(sb.toString());
        	
        	if(startDate != null && endDate != null){
        		query.setDate("startDate",startDate);
        		query.setDate("endDate",endDate);
        	}
        	query.setParameterList("locationIds",locationIds);
        	
        	return query.list();
        }
        
        public List<Object[]> getConductedNotConductedPartyMeetingsByIVR(Date startDate,Date endDate,List<Long> locationIds,StringBuilder locationsPart){
        	
        
        	StringBuilder sb = new StringBuilder();
        	
        	sb.append(" " +
        	"select year(model.startDate),month(model.startDate)," +//1
            "       model.partyMeetingLevel.partyMeetingLevelId,model.partyMeetingLevel.level," +//3
            "       model1.isConductedByIvr,count(distinct model.partyMeetingId)" +//5
        	"from   PartyMeeting model,PartyMeetingIvrStatus model1 " +
            "where  model.partyMeetingId = model1.partyMeetingId and " +
            "       model.isActive ='Y' ");
        	
        	if(startDate != null && endDate != null){
        		sb.append(" and model.startDate between :startDate and :endDate ");
        	}
        	sb.append(locationsPart);
        	
        	sb.append(" group by year(model.startDate),month(model.startDate),model.partyMeetingLevel.partyMeetingLevelId,model1.isConductedByIvr");
        	
        	Query query = getSession().createQuery(sb.toString());
        	
        	if(startDate != null && endDate != null){
        		query.setDate("startDate",startDate);
        		query.setDate("endDate",endDate);
        	}
        	query.setParameterList("locationIds",locationIds);
        	
        	return query.list();
        }
        
        public List<Object[]> getConductedPartyMeetingsByAttendance(Date startDate,Date endDate,List<Long> locationIds,StringBuilder locationsPart){
        	
        	StringBuilder sb = new StringBuilder();
        	
        	sb.append(" " +
        	"select year(model.startDate),month(model.startDate)," +//1
            "       model.partyMeetingLevel.partyMeetingLevelId,model.partyMeetingLevel.level," +//3
            "       count(distinct model.partyMeetingId)" +//4
        	"from   PartyMeeting model,PartyMeetingAttendance model1 " +
            "where  model.partyMeetingId = model1.partyMeetingId  and" +
            "       model.isActive ='Y' ");
        	
        	if(startDate != null && endDate != null){
        		sb.append(" and model.startDate between :startDate and :endDate ");
        	}
        	sb.append(locationsPart);
        	
        	sb.append(" group by year(model.startDate),month(model.startDate),model.partyMeetingLevel.partyMeetingLevelId");
        	
        	Query query = getSession().createQuery(sb.toString());
        	
        	if(startDate != null && endDate != null){
        		query.setDate("startDate",startDate);
        		query.setDate("endDate",endDate);
        	}
        	query.setParameterList("locationIds",locationIds);
        	
        	return query.list();
        }
        
        public List<Object[]> getMeetingDetailsForALevelByLocationId(int month,int year,List<Long> partyMeetingLevelIds,List<Long> locationIds,StringBuilder locationsPart){
        
        	StringBuilder sbS = new StringBuilder();
        	StringBuilder sbM = new StringBuilder();
        	
        	sbS.append(" " +
        	"select model.partyMeetingId,model.meetingName," +//1
        	"       model.partyMeetingLevel.partyMeetingLevelId,model.conductedDate," +//3
        	"       model.isConducted ") ;//4
        	sbM.append(" from   PartyMeeting model ");
        	
        	if(partyMeetingLevelIds.contains(2l)){//district
        		
        		sbS.append(" ,district.districtId,district.districtName ");//6
        		
        		sbM.append(" left join model.meetingAddress.district district  ");
        		
        	}else if(partyMeetingLevelIds.contains(3l)){//constituency
        		
        		sbS.append(" ,constituency.constituencyId,constituency.name," +//6
        		           " district.districtId,district.districtName ");//8
        		sbM.append(" left join model.meetingAddress.constituency constituency" +
        				   " left join model.meetingAddress.district district ");
        		
        	}else if(partyMeetingLevelIds.contains(4l) || partyMeetingLevelIds.contains(5l) || partyMeetingLevelIds.contains(6l)){//mandal/town/division level.
        		
        		sbS.append(" ,tehsil.tehsilId,tehsil.tehsilName," +//6
        				   " leb.localElectionBodyId,leb.name," +//8
        				   " electionType.electionTypeId,electionType.electionType," +//10
        			 	   " constituency.constituencyId,constituency.name " );//12
        	    sbM.append(" left join model.meetingAddress.tehsil tehsil" +
        	    	 	   " left join model.meetingAddress.localElectionBody leb " +
        	    	 	   " left join leb.electionType electionType" +
        	    	 	   " left join model.meetingAddress.constituency constituency");
        	    
        	}else if(partyMeetingLevelIds.contains(7l) || partyMeetingLevelIds.contains(8l)){//village/ward
        		
        		sbS.append(",panchayat.panchayatId,panchayat.panchayatName, " +//6
        				   " tehsil.tehsilId,tehsil.tehsilName," +//8
        				   " ward.constituencyId,ward.name," +//10
     				       " leb.localElectionBodyId,leb.name," +//12
     				       " electionType.electionTypeId,electionType.electionType ");//14
        		sbM.append(" left join model.meetingAddress.panchayat panchayat  " +
     	    	 	       " left join model.meetingAddress.tehsil tehsil " +
     	    	 	       " left join model.meetingAddress.ward ward  " +
     	    	 	       " left join model.meetingAddress.localElectionBody leb " +
     	    	 	       " left join leb.electionType electionType ");
        	}
        	
        	sbM.append(" where  model.partyMeetingLevel.partyMeetingLevelId in (:partyMeetingLevelIds) " +
        	           "        and model.isActive = 'Y' ");
        	
        	sbM.append(locationsPart);
        	sbM.append(" and year(model.startDate) =:year and month(model.startDate)=:month ");
        	
        	StringBuilder sbf = new StringBuilder();
        	sbf.append(sbS.toString()).append(sbM.toString());
        	Query query = getSession().createQuery(sbf.toString());
        	query.setParameterList("partyMeetingLevelIds",partyMeetingLevelIds);
        	query.setParameter("year", year);
        	query.setParameter("month", month);
        	query.setParameterList("locationIds",locationIds);
        	
        	return query.list();
        }
        public List<Object[]> getMeetingDetailsForALevelByLocationIdByIVR(int month,int year,List<Long> partyMeetingLevelIds,List<Long> locationIds,StringBuilder locationsPart){
        
      	StringBuilder sb = new StringBuilder();
      	sb.append(" " +
      	"select model.partyMeetingId,model1.isConductedByIvr" +
        " from  PartyMeeting model,PartyMeetingIvrStatus model1 "+
      	" where model.partyMeetingId = model1.partyMeetingId and " +
      	"       model.partyMeetingLevel.partyMeetingLevelId in (:partyMeetingLevelIds) and" +
      	"       model.isActive = 'Y' ");
      
      	sb.append(locationsPart);
      	sb.append(" and year(model.startDate) =:year and month(model.startDate)=:month ");
      
      	Query query = getSession().createQuery(sb.toString());
      	query.setParameterList("partyMeetingLevelIds",partyMeetingLevelIds);
      	query.setParameter("year", year);
      	query.setParameter("month", month);
      	query.setParameterList("locationIds",locationIds);
      	
      	return query.list();
      }
    public List<Object[]> getPartyMeetingOverAllCountByUserAccessLevel(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> partyMeetingTypeValues){
    	
          StringBuilder queryStr = new StringBuilder();
    	
    	  queryStr.append(" select model.partyMeetingLevel.partyMeetingLevelId,model.partyMeetingLevel.level,count(distinct model.partyMeetingId)  " +
    	  		" from PartyMeeting model " +
    	  		" where " +
    	  		" model.isActive='Y' and model.startDate is not null ");
    	
    	  if(stateId != null && stateId.longValue() > 0){
				 queryStr.append(" and model.meetingAddress.state.stateId=:stateId ");
		  }
		  if(fromDate!= null && toDate!=null){
			  queryStr.append(" and (date(model.startDate) between :fromDate and :toDate ) ");	 
		 }
		 if(partyMeetingTypeValues != null && partyMeetingTypeValues.size() > 0){
			 queryStr.append(" and model.partyMeetingType.partyMeetingTypeId in (:partyMeetingTypeValues)");
		 }
		 if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		   queryStr.append(" and model.meetingAddress.state.stateId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		   queryStr.append(" and model.meetingAddress.district.districtId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
	        queryStr.append(" and model.meetingAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
	        queryStr.append(" and model.meetingAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
		    queryStr.append(" and model.meetingAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
		    queryStr.append(" and model.meetingAddress.userAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
		    queryStr.append(" and model.meetingAddress.panchayat.panchayatId in (:userAccessLevelValues)"); 
		 }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
		    queryStr.append(" and model.meetingAddress.ward.constituencyId in (:userAccessLevelValues)"); 
		 }
		 queryStr.append(" group by model.partyMeetingLevel.partyMeetingLevelId " +
         " order by " +
         " model.partyMeetingLevel.partyMeetingLevelId ");
		
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
    public List<Object[]> getPartyMeetingOverAllCountLocationWiseByUserAccessLevel(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> partyMeetingTypeValues){
    	
    	{
			  StringBuilder queryStr= new StringBuilder();
			  queryStr.append("select ");
		      
			  if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		         queryStr.append(" model.meetingAddress.state.stateId,");  //0
		      }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		        queryStr.append(" model.meetingAddress.district.districtId, ");  
		      }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
		          queryStr.append(" model.meetingAddress.parliamentConstituency.constituencyId, ");  
			  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
		          queryStr.append(" model.meetingAddress.constituency.constituencyId, ");  
			  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
		         queryStr.append(" model.meetingAddress.tehsil.tehsilId,");  
			  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
			         queryStr.append(" model.meetingAddress.localElectionBody.localElectionBodyId,"); 
			  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
			       queryStr.append(" model.meetingAddress.panchayat.panchayatId,"); 
			  }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
			      queryStr.append(" model.meetingAddress.ward.constituencyId,"); 
			  }
			   queryStr.append("count(distinct model.partyMeetingId) " +//1
				        " from PartyMeeting model " +
		    	  		" where " +
		    	  		" model.isActive='Y' and model.startDate is not null ");
	         if(stateId != null && stateId.longValue() > 0){
				 queryStr.append(" and model.meetingAddress.state.stateId=:stateId ");
			 }
			  if(fromDate!= null && toDate!=null){
				  queryStr.append(" and date(model.startDate) between :fromDate and :toDate ");	 
			 }
			 if(partyMeetingTypeValues != null && partyMeetingTypeValues.size() > 0){
					 queryStr.append(" and model.partyMeetingType.partyMeetingTypeId in (:partyMeetingTypeValues)");
			 }
		  /*  if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		      queryStr.append(" and model.meetingAddress.state.stateId in (:userAccessLevelValues)");  
		    }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		      queryStr.append(" and model.meetingAddress.district.districtId in (:userAccessLevelValues)");  
		    }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
		        queryStr.append(" and model.meetingAddress.parliamentConstituency.constituencyId in (:userAccessLevelValues) ");  
			}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
		         queryStr.append(" and model.meetingAddress.constituency.constituencyId in (:userAccessLevelValues) ");  
			}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
		      queryStr.append(" and model.meetingAddress.tehsil.tehsilId in (:userAccessLevelValues)");  
			}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
		      queryStr.append(" and model.meetingAddress.localElectionBody.localElectionBodyId in (:userAccessLevelValues)"); 
			}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
		      queryStr.append(" and model.meetingAddress.panchayat.panchayatId in (:userAccessLevelValues)"); 
			}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){ 
		      queryStr.append(" and model.meetingAddress.ward.constituencyId in (:userAccessLevelValues)"); 
			}*/
	        if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.STATE_LEVEl_ACCESS_ID){
		      queryStr.append(" group by  model.meetingAddress.state.stateId order by model.meetingAddress.state.stateId ");  
		    }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.DISTRICT_LEVEl_ACCESS_ID){
		      queryStr.append(" group by  model.meetingAddress.district.districtId order by  model.meetingAddress.district.districtId ");  
		    }else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.PARLIAMENT_LEVEl_ACCESS_ID){
		      queryStr.append(" group by  model.meetingAddress.parliamentConstituency.constituencyId order by model.meetingAddress.parliamentConstituency.constituencyId");  
			}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.ASSEMBLY_LEVEl_ACCESS_ID){
		      queryStr.append("group by model.meetingAddress.constituency.constituencyId order by model.meetingAddress.constituency.constituencyId");  
			}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MANDAL_LEVEl_ID){
		      queryStr.append(" group by  model.meetingAddress.tehsil.tehsilId order by model.partyMeeting.meetingAddress.tehsil.tehsilId");  
			}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.MUNCIPALITY_LEVEl_ID){ //  town/division
		      queryStr.append(" group by  model.meetingAddress.localElectionBody.localElectionBodyId order by model.meetingAddress.localElectionBody.localElectionBodyId"); 
			}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.VILLAGE_LEVEl_ID){ 
		      queryStr.append(" group by  model.meetingAddress.panchayat.panchayatId order by model.meetingAddress.panchayat.panchayatId"); 
			}else if(userAccessLevelId != null && userAccessLevelId.longValue()==IConstants.WARD_LEVEl_ID){
			  queryStr.append(" group by model.meetingAddress.ward.constituencyId order by model.meetingAddress.ward.constituencyId");	
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
        
    }
    
    
    public List<Object[]> getLocationWiseMeetingsCountByLocIds(CommitteeInputVO inputBO){
		
		StringBuilder sbS = new StringBuilder();
		sbS.append(" select count(distinct model.partyMeetingId) ");
		StringBuilder sbM = new StringBuilder();
		sbM.append(" from   PartyMeeting model ");
		
		sbM.append(" where model.isActive='Y' and model.startDate is not null ");
		StringBuilder sbE = new StringBuilder();
		if(inputBO.getStateIds()!=null && inputBO.getStateIds().size()>0){
			sbS.append(",model.meetingAddress.state.stateId ");
			sbE.append(" group by model.meetingAddress.state.stateId ");
		}
		else if(inputBO.getDistrictIds() != null && inputBO.getDistrictIds().size()>0){
			
			sbS.append(",model.meetingAddress.district.districtId ");
			sbE.append(" group by model.meetingAddress.district.districtId ");
			
		}else if(inputBO.getParliamentConstIds() != null && inputBO.getParliamentConstIds().size()>0){
			
			sbS.append(",model.meetingAddress.parliamentConstituency.constituencyId ");
			sbE.append(" group by model.meetingAddress.parliamentConstituency.constituencyId ");
			
		}else if(inputBO.getAssemblyConstIds() != null && inputBO.getAssemblyConstIds().size()>0){
			
			sbS.append(",model.meetingAddress.constituency.constituencyId");
			sbE.append(" group by model.meetingAddress.constituency.constituencyId ");
			
		}else if(inputBO.getTehsilIds()!= null && inputBO.getTehsilIds().size()>0){
			
			sbS.append(",model.meetingAddress.tehsil.tehsilId ");
			sbE.append(" group by model.meetingAddress.tehsil.tehsilId ");
		}
		
		 if(inputBO.getStartDate()!= null && inputBO.getEndDate()!=null){
			 sbM.append(" and date(model.startDate) between :startDate and :endDate ");	 
		 }
			
		if(inputBO.getStateId()!= null && inputBO.getStateId() > 0l ){
			sbM.append(" and model.meetingAddress.state.stateId = :stateId ");
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
    
    public List<Object[]> getTopPoorMeetingLocations(CommitteeInputVO committeeBO){
		
		StringBuilder sbS = new StringBuilder();
		sbS.append("select count(distinct model.partyMeetingId)");//0
		StringBuilder sbE = new StringBuilder();
		if(committeeBO.getGroupingLocation().equalsIgnoreCase("State")){
			sbS.append(" ,model.meetingAddress.state.stateId,model.meetingAddress.state.stateName ");//2
			sbE.append("  group by model.meetingAddress.state.stateId ");
		}else if(committeeBO.getGroupingLocation().equalsIgnoreCase("District")){
			sbS.append(" ,model.meetingAddress.district.districtId,model.meetingAddress.district.districtName ");//2
			sbE.append("  group by model.meetingAddress.district.districtId ");
		}else if(committeeBO.getGroupingLocation().equalsIgnoreCase("Constituency")){
			sbS.append(" ,model.meetingAddress.constituency.constituencyId,model.meetingAddress.constituency.name ");//2
			sbE.append("  group by model.meetingAddress.constituency.constituencyId ");
		}else if(committeeBO.getGroupingLocation().equalsIgnoreCase("Mandal")){
			sbS.append(" ,model.meetingAddress.tehsil.tehsilId,model.meetingAddress.tehsil.tehsilName ");//2
			sbE.append("  group by model.meetingAddress.tehsil.tehsilId ");
		}else if(committeeBO.getGroupingLocation().equalsIgnoreCase("localElectionBody")){
			sbS.append(" ,model.meetingAddress.localElectionBody.localElectionBodyId,model.meetingAddress.localElectionBody.name " +//2
					   " ,model.meetingAddress.localElectionBody.electionType.electionTypeId,model.meetingAddress.localElectionBody.electionType.electionType ");//4
			sbE.append(" group by model.meetingAddress.localElectionBody.localElectionBodyId ");
		}else if(committeeBO.getGroupingLocation().equalsIgnoreCase("Village")){
			sbS.append(" ,model.meetingAddress.panchayat.panchayatId,model.meetingAddress.panchayat.panchayatName ");//2
			sbE.append(" group by model.meetingAddress.panchayat.panchayatId ");
		}else if(committeeBO.getGroupingLocation().equalsIgnoreCase("Ward")){
			sbS.append(" ,model.meetingAddress.ward.constituencyId,model.meetingAddress.ward.name ");//2
			sbE.append(" group by model.meetingAddress.ward.constituencyId ");
		}
		StringBuilder sbM = new StringBuilder();
		sbM.append(" from  PartyMeeting model where model.isActive = 'Y' and model.startDate is not null ");
		
		//locations related.
		if(committeeBO.getStateIds()!=null && committeeBO.getStateIds().size()>0){
			sbM.append(" and model.meetingAddress.state.stateId in (:locationValues) ");
		}
		else if(committeeBO.getDistrictIds() != null && committeeBO.getDistrictIds().size()>0){
			
			sbM.append(" and model.meetingAddress.district.districtId in (:locationValues) ");
			
		}else if(committeeBO.getParliamentConstIds() != null && committeeBO.getParliamentConstIds().size()>0){
			
			sbM.append(" and model.meetingAddress.parliamentConstituency.constituencyId in (:locationValues) ");
			
		}else if(committeeBO.getAssemblyConstIds() != null && committeeBO.getAssemblyConstIds().size()>0){
			
			sbM.append(" and model.meetingAddress.constituency.constituencyId in (:locationValues) ");
			
		}else if(committeeBO.getTehsilIds()!= null && committeeBO.getTehsilIds().size()>0){
			
			sbM.append(" and model.meetingAddress.tehsil.tehsilId in (:locationValues) ");
		}
		
 		if(committeeBO.getStartDate()!= null && committeeBO.getEndDate()!=null){
			 sbM.append(" and date(model.startDate) between :startDate and :endDate ");	 
		 }	
		if(committeeBO.getStateId()!= null && committeeBO.getStateId() > 0l ){
			sbM.append(" and model.meetingAddress.state.stateId = :stateId ");
		}
		if(committeeBO.getPartyMeetingTypeIds()!=null && committeeBO.getPartyMeetingTypeIds().size()>0){
			sbM.append(" and model.partyMeetingTypeId in (:partyMeetingTypeIds) ");
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
    
    
    public List<Object[]> getNoOfMeetingsByPartyMeetingTypeIds(PartyMeetingsInputVO inputVO){
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.partyMeetingType.partyMeetingTypeId,model.partyMeetingType.type,count(distinct model.partyMeetingId) " +
				"   from   PartyMeeting model " +
				"   where  model.isActive='Y' and model.startDate is not null and " +
				"          model.partyMeetingType.partyMeetingMainType.partyMeetingMainTypeId = :partyMeetingMainTypeId   ");
		
		if(inputVO.getStartDate()!= null && inputVO.getEndDate()!=null){
			 sb.append(" and date(model.startDate) between :startDate and :endDate ");	 
		}
		if(inputVO.getStateId()!= null && inputVO.getStateId() > 0l ){
			sb.append(" and model.meetingAddress.state.stateId = :stateId ");
		}
		if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size()>0){
			sb.append(" and model.partyMeetingType.partyMeetingTypeId in (:partyMeetingTypeIds) ");	
		}
		if(inputVO.getPartyMeetingIds() != null && inputVO.getPartyMeetingIds().size()>0){
			sb.append(" and model.partyMeetingId in (:partyMeetingIds) ");
		}
		sb.append(" group by model.partyMeetingType.partyMeetingTypeId order by  model.partyMeetingId desc ");
		Query query = getSession().createQuery(sb.toString());
	    
		if(inputVO.getStartDate()!= null && inputVO.getEndDate()!=null){
			query.setDate("startDate",inputVO.getStartDate());
			query.setDate("endDate",inputVO.getEndDate());	 
		}
		if(inputVO.getStateId()!= null && inputVO.getStateId() > 0l ){
			query.setParameter("stateId",inputVO.getStateId());
		}
		if(inputVO.getPartyMeetingTypeIds() != null && inputVO.getPartyMeetingTypeIds().size()>0){
			query.setParameterList("partyMeetingTypeIds",inputVO.getPartyMeetingTypeIds());
		}
		query.setParameter("partyMeetingMainTypeId",inputVO.getPartyMeetingMainTypeId());
		if(inputVO.getPartyMeetingIds() != null && inputVO.getPartyMeetingIds().size()>0){
			query.setParameterList("partyMeetingIds",inputVO.getPartyMeetingIds());
		}
	    return query.list();
		
	}
    public List<Object[]> getPartyMeetingCommentsDtls(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> partyMeetingTypeIds,String meetingStatus,List<Long> PartyMeetingLevelIds,String isComment,Long locationId,String locationType,String meetingLevelType){
		 
		   StringBuilder queryStr = new StringBuilder();
	    	
	         queryStr.append("select model.partyMeetingId," + //0
	         " model1.meetingName," + //1
	         " model1.partyMeetingType.partyMeetingTypeId," + //2
	         " model1.partyMeetingType.type ");//3
	         queryStr.append(",model1.meetingAddress.district.districtId ");//4
	         queryStr.append(",model1.meetingAddress.district.districtName ");//5
	         queryStr.append(",model1.conductedDate" +//6
       		                ",model1.remarks " );//7
            if(meetingLevelType != null && meetingLevelType.equalsIgnoreCase("Constituency") || meetingLevelType.equalsIgnoreCase("Village/Ward") || meetingLevelType.equalsIgnoreCase("Mandal/Town/Division")){
             queryStr.append(",model1.meetingAddress.constituency.constituencyId " +
             		        " ,model1.meetingAddress.constituency.name ");	
            }
	        if(meetingLevelType != null && meetingLevelType.equalsIgnoreCase("Village/Ward") || meetingLevelType.equalsIgnoreCase("Mandal/Town/Division")){
  		          queryStr.append(",tehsil.tehsilId ");//8
  		          queryStr.append(",tehsil.tehsilName ");//9
  		          queryStr.append(",localElectionBody.localElectionBodyId ");//10
  		          queryStr.append(",localElectionBody.name ");//11
  		    }
	         queryStr.append(" from PartyMeetingStatus model " +
	         				 " join model.partyMeeting model1  " +
	         				 " left join model1.meetingAddress meetingAddress  " +
	         		         " left join meetingAddress.tehsil tehsil  " +
	         		         " left join meetingAddress.localElectionBody localElectionBody " +
	         " where " +
	         " model1.isActive='Y' ");
	  		         
	    if(meetingStatus != null && meetingStatus.trim().length() > 0){
	     	queryStr.append(" and model.mettingStatus=:mettingStatus"); 
	    }
	    if(isComment != null && isComment.equalsIgnoreCase("Yes")){
	   	 queryStr.append(" and model.partyMeeting.remarks is not null ");
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
		 if(partyMeetingTypeIds != null && partyMeetingTypeIds.size() > 0){
				 queryStr.append(" and model.partyMeeting.partyMeetingType.partyMeetingTypeId in (:partyMeetingTypeValues)");
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
	   
		 if(PartyMeetingLevelIds != null && PartyMeetingLevelIds.size() > 0){
			 query.setParameterList("partyMeetingLevelIds", PartyMeetingLevelIds); 
		 }
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
		 if(partyMeetingTypeIds != null && partyMeetingTypeIds.size() > 0){
			 query.setParameterList("partyMeetingTypeValues", partyMeetingTypeIds); 
		 }
		 if(meetingStatus != null && meetingStatus.trim().length() > 0){
			 query.setParameter("mettingStatus", meetingStatus); 
		 }
		 if(locationId != null && locationId > 0){
			 query.setParameter("locationId", locationId);
		 }
	return query.list(); 
	 }
    
    
    public List<Object[]> getPartyMeetingComulativeCount(List<Long> partyMeetingsIds,String reportType,String countType){
		 
		 StringBuilder queryStr = new StringBuilder();
	    	
		queryStr.append("select"); 
		
		if(reportType != null && reportType.equalsIgnoreCase("District")){
		 queryStr.append(" model.meetingAddress.district.districtId,");
		 queryStr.append(" model.meetingAddress.district.districtName,");
		}else if(reportType.equalsIgnoreCase("Constituency")){
		 queryStr.append(" model.meetingAddress.constituency.constituencyId,");
		 queryStr.append(" model.meetingAddress.constituency.name,");	
		}
	     queryStr.append(" count(distinct model.partyMeetingId)" +
	  		         " from PartyMeeting model " +
	  		         " where " +
	  		         " model.isActive='Y' ");
	   
	     if(countType != null && countType.equalsIgnoreCase("commentCount")){
	    	queryStr.append(" and model.remarks is not null "); 
	     }
	     if(partyMeetingsIds != null && partyMeetingsIds.size() > 0){
	    	 queryStr.append(" and model.partyMeetingId in (:partyMeetingsIds)");
	     }
	      if(reportType != null && reportType.equalsIgnoreCase("District")){
			 queryStr.append(" group by model.meetingAddress.district.districtId order by model.meetingAddress.district.districtId ");
			}else if(reportType.equalsIgnoreCase("Constituency")){
			 queryStr.append(" group by model.meetingAddress.constituency.constituencyId order by model.meetingAddress.constituency.constituencyId ");
			}
	     Query query = getSession().createQuery(queryStr.toString());
	
		 if(partyMeetingsIds != null && partyMeetingsIds.size() > 0){
			 query.setParameterList("partyMeetingsIds", partyMeetingsIds); 
		 }
		return query.list(); 
	 } 
   public List<Object[]> getDistrictByMeetingId(Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> partyMeetingTypeValues,String meetingStatus,List<Long> PartyMeetingLevelIds,String isComment){
	   
	      StringBuilder queryStr = new StringBuilder();
	   
		    queryStr.append("select distinct model1.meetingAddress.district.districtId,model1.meetingAddress.district.districtName " );
		 
		  	queryStr.append(" from PartyMeetingStatus model,PartyMeeting model1 " +
		  		         " where " +
		  		         " model1.isActive='Y' and model.partyMeetingId=model1.partyMeetingId ");
		 
		     if(meetingStatus != null && meetingStatus.trim().length() > 0){
		    	queryStr.append(" and model.mettingStatus=:mettingStatus"); 
		     }
		     if(isComment != null && isComment.equalsIgnoreCase("Yes")){
		    	 queryStr.append(" and model.partyMeeting.remarks is not null ");
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
			 return query.list(); 
		 
   }

   public List<Object[]> getConstituencyByMeetingId(Long districtId,Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> partyMeetingTypeValues,String meetingStatus,List<Long> PartyMeetingLevelIds,String isComment){
	   
	      StringBuilder queryStr = new StringBuilder();
	   
	      
		 
	        queryStr.append("select distinct model1.meetingAddress.constituency.constituencyId,model1.meetingAddress.constituency.name " );
			 
		  	queryStr.append(" from PartyMeetingStatus model,PartyMeeting model1 " +
		  		         " where " +
		  		         " model1.isActive='Y' and model.partyMeetingId=model1.partyMeetingId and model1.meetingAddress.constituency.electionScope.electionScopeId=2 ");
		 
		     if(meetingStatus != null && meetingStatus.trim().length() > 0){
		    	queryStr.append(" and model.mettingStatus=:mettingStatus"); 
		     }
		     if(isComment != null && isComment.equalsIgnoreCase("Yes")){
		    	 queryStr.append(" and model.partyMeeting.remarks is not null ");
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
			 if(districtId != null && districtId > 0){
				  queryStr.append(" and model1.meetingAddress.district.districtId=:districtId");
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
			 if(districtId != null && districtId > 0){
				 query.setParameter("districtId", districtId);
			  }
		  return query.list();
}
   public List<Object[]> getMandalByMeetingId(Long constituencyId,Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> partyMeetingTypeValues,String meetingStatus,List<Long> PartyMeetingLevelIds,String isComment){
	   
	      StringBuilder queryStr = new StringBuilder();
	   
	        queryStr.append("select distinct model1.meetingAddress.tehsil.tehsilId,model1.meetingAddress.tehsil.tehsilName " );
			 
		  	queryStr.append(" from PartyMeetingStatus model,PartyMeeting model1 " +
		  		         " where " +
		  		         " model1.isActive='Y' and model.partyMeetingId=model1.partyMeetingId ");
		 
		     if(meetingStatus != null && meetingStatus.trim().length() > 0){
		    	queryStr.append(" and model.mettingStatus=:mettingStatus"); 
		     }
		     if(isComment != null && isComment.equalsIgnoreCase("Yes")){
		    	 queryStr.append(" and model.partyMeeting.remarks is not null ");
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
			 if(constituencyId != null && constituencyId > 0){
				    queryStr.append(" and model1.meetingAddress.constituency.constituencyId=:constituencyId");
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
			  if(constituencyId != null && constituencyId > 0){
				 query.setParameter("constituencyId", constituencyId);
			  }
		 
		  return query.list();
}
   public List<Object[]> getTownDivisionByMeetingId(Long constituencyId,Long userAccessLevelId,List<Long> userAccessLevelValues,Long stateId,Date fromDate,Date toDate,List<Long> partyMeetingTypeValues,String meetingStatus,List<Long> PartyMeetingLevelIds,String isComment){
	   
	      StringBuilder queryStr = new StringBuilder();
	   
	        queryStr.append("select distinct model1.meetingAddress.localElectionBody.localElectionBodyId,model1.meetingAddress.localElectionBody.name " );
			 
		  	queryStr.append(" from PartyMeetingStatus model,PartyMeeting model1 " +
		  		         " where " +
		  		         " model1.isActive='Y' and model.partyMeetingId=model1.partyMeetingId ");
		 
		     if(meetingStatus != null && meetingStatus.trim().length() > 0){
		    	queryStr.append(" and model.mettingStatus=:mettingStatus"); 
		     }
		     if(isComment != null && isComment.equalsIgnoreCase("Yes")){
		    	 queryStr.append(" and model.partyMeeting.remarks is not null ");
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
			 if(constituencyId != null && constituencyId > 0){
				    queryStr.append(" and model1.meetingAddress.constituency.constituencyId=:constituencyId");
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
			 if(constituencyId != null && constituencyId > 0){
				 query.setParameter("constituencyId", constituencyId);
			  }
		  return query.list();
}
   public List<Object[]> getFinalAllMeetings(Long meetingType,Long locationLevel,List<Long> stateList,List<Long> districtList,List<Long> constituencyList,List<Long> mandalList,
		   List<Long> townList,List<Long> divisonList,List<Long> villageList,List<Long> wardList,Date startDate,Date endDate,Long meetingLevel,String status){
	        StringBuilder sb = new StringBuilder();
	        
	        sb.append(" select model.partyMeetingType.partyMeetingTypeId," +//0 - MeetingTypeId
	        		" model.partyMeetingType.type, " +						//1 - Meeting Type
	                " model.partyMeetingLevel.partyMeetingLevelId," + 		// 2- Meeting Level Id
	                " model.partyMeetingLevel.level, " + 					// 3 - Meeting Level
	                " model.locationValue," + 								// 4 -- Location Value
	                " date(model.startDate)," + 							// 5 -- Start Date
	                " date(model.endDate)," + 								// 6 -- End Date
	                " model.meetingAddress.userAddressId," +				// 7 -- User Address Id
	                " model.meetingName," + 								// 8 -- Meeting Name
	                " model.partyMeetingId, " ); 							// 9 -- Party Meeting Id
	        
	       
	        sb.append(" model.meetingAddress.state.stateId ");				// 10 -- State Id
	        sb.append(" ,model.meetingAddress.state.stateName ");			// 11 -- State Name
	        
	        sb.append(" ,model.isConducted," +								//12.conducted or NotConducted
	        		" model.isConductedByIvr," +								//13.conducted Date 
	        		" model.remarks,model.thirdPartyStatus ");										//14.remarks,15.thirdParty
	        
	        
	        //if(meetingLevel>=2){
	        	sb.append(" ,model.meetingAddress.district.districtId ");	// 16 -- District Id
	        	sb.append(" ,model.meetingAddress.district.districtName ");	// 17 -- District Name
	       // }
	        //if(meetingLevel>=3){
	        	sb.append(" ,model.meetingAddress.constituency.constituencyId "); // 18 -- Constituency Id
	        	sb.append(" ,model.meetingAddress.constituency.name "); 		  // 19 -- Constituency Name
	       // }
	        
	        
	         sb.append(" from PartyMeeting model " +
	                " where model.isActive='Y' " );
	        if(meetingType!=null && meetingType>0l){
	            sb.append(" and model.partyMeetingType.partyMeetingTypeId=:meetingType ");
	        }
	        
	        if(meetingLevel>0l){
	        	sb.append(" and model.partyMeetingLevel.partyMeetingLevelId=:meetingLevel ");
	        }else if(locationLevel!=null){
	            sb.append(" and model.partyMeetingLevel.partyMeetingLevelId=:locationLevel ");
	        }
	        
	        if(locationLevel!=null && locationLevel==1l){//state level
	            if(stateList.get(0)==0l){
	                sb.append(" and model.meetingAddress.state.stateId is not null ");
	            }else{
	                sb.append(" and model.meetingAddress.state.stateId in (:stateList) ");
	            }
	        }else if(locationLevel!=null && locationLevel==2l){//district level
	            if(stateList.get(0)==0l){
	                sb.append(" and model.meetingAddress.state.stateId is not null ");
	            }else{
	                sb.append(" and model.meetingAddress.state.stateId in (:stateList) ");
	            }
	            if(districtList.get(0)==0l){
	                sb.append(" and model.meetingAddress.district.districtId is not null ");
	            }else{
	                sb.append(" and model.meetingAddress.district.districtId in (:districtList) ");
	            }
	        }else if(locationLevel!=null && locationLevel==3l){//constituency level
	            if(stateList.get(0)==0l){
	                sb.append(" and model.meetingAddress.state.stateId is not null ");
	            }else{
	                sb.append(" and model.meetingAddress.state.stateId in (:stateList) ");
	            }
	            if(districtList.get(0)==0l){
	                sb.append(" and model.meetingAddress.district.districtId is not null ");
	            }else{
	                sb.append(" and model.meetingAddress.district.districtId in (:districtList) ");
	            }
	            if(constituencyList.get(0)==0l){
	                sb.append(" and model.meetingAddress.constituency.constituencyId is not null ");
	            }else{
	                sb.append(" and model.meetingAddress.constituency.constituencyId in (:constituencyList) ");
	            }
	        }else if(locationLevel!=null && locationLevel==4l){//mandal level
	            if(stateList.get(0)==0l){
	                sb.append(" and model.meetingAddress.state.stateId is not null ");
	            }else{
	                sb.append(" and model.meetingAddress.state.stateId in (:stateList) ");
	            }
	            if(districtList.get(0)==0l){
	                sb.append(" and model.meetingAddress.district.districtId is not null ");
	            }else{
	                sb.append(" and model.meetingAddress.district.districtId in (:districtList) ");
	            }
	            if(constituencyList.get(0)==0l){
	                sb.append(" and model.meetingAddress.constituency.constituencyId is not null ");
	            }else{
	                sb.append(" and model.meetingAddress.constituency.constituencyId in (:constituencyList) ");
	            }
	            if(mandalList !=null && mandalList.size()<=0){
	                sb.append(" and model.meetingAddress.tehsil.tehsilId is not null ");
	            }else if(mandalList !=null ){
	                sb.append(" and model.meetingAddress.tehsil.tehsilId in (:mandalList) ");
	            }
	        }else if(locationLevel!=null && locationLevel==5l){//town level
	            if(stateList.get(0)==0l){
	                sb.append(" and model.meetingAddress.state.stateId is not null ");
	            }else{
	                sb.append(" and model.meetingAddress.state.stateId in (:stateList) ");
	            }
	            if(districtList.get(0)==0l){
	                sb.append(" and model.meetingAddress.district.districtId is not null ");
	            }else{
	                sb.append(" and model.meetingAddress.district.districtId in (:districtList) ");
	            }
	            if(constituencyList.get(0)==0l){
	                sb.append(" and model.meetingAddress.constituency.constituencyId is not null ");
	            }else{
	                sb.append(" and model.meetingAddress.constituency.constituencyId in (:constituencyList) ");
	            }
	            if(townList !=null && townList.size()<=0){
	                sb.append(" and model.meetingAddress.localElectionBody.localElectionBodyId is not null ");
	            }else if(townList !=null){
	                sb.append(" and model.meetingAddress.localElectionBody.localElectionBodyId in (:townList) ");
	            }
	        }else if((locationLevel!=null) && (locationLevel==6l || locationLevel==8l)){//divison or ward level
	            if(stateList.get(0)==0l){
	                sb.append(" and model.meetingAddress.state.stateId is not null ");
	            }else{
	                sb.append(" and model.meetingAddress.state.stateId in (:stateList) ");
	            }
	            if(districtList.get(0)==0l){
	                sb.append(" and model.meetingAddress.district.districtId is not null ");
	            }else{
	                sb.append(" and model.meetingAddress.district.districtId in (:districtList) ");
	            }
	            if(constituencyList.get(0)==0l){
	                sb.append(" and model.meetingAddress.constituency.constituencyId is not null ");
	            }else{
	                sb.append(" and model.meetingAddress.constituency.constituencyId in (:constituencyList) ");
	            }
	            if(locationLevel==6l){
	                if(divisonList !=null && divisonList.size()<=0){
	                    sb.append(" and model.meetingAddress.ward.constituencyId is not null ");
	                }else if(divisonList !=null){
	                    sb.append(" and model.meetingAddress.ward.constituencyId in (:divisonList) ");
	                }
	            }else{
	            	 /*if(mandalList !=null && mandalList.size()<=0){
	                    sb.append(" and model.meetingAddress.tehsil.tehsilId is not null ");
	                }else if(mandalList !=null){
	                    sb.append(" and model.meetingAddress.tehsil.tehsilId in (:mandalList) ");
	                }*/
	            	 if(townList !=null && townList.size()<=0){
	 	                sb.append(" and model.meetingAddress.localElectionBody.localElectionBodyId is not null ");
	 	            }else if(townList !=null){
	 	                sb.append(" and model.meetingAddress.localElectionBody.localElectionBodyId in (:townList) ");
	 	            }
	            	
	                if(wardList !=null && wardList.size()<=0){
	                    sb.append(" and model.meetingAddress.ward.constituencyId is not null ");
	                }else if(wardList !=null ){
	                    sb.append(" and model.meetingAddress.ward.constituencyId in (:wardList) ");
	                }
	            }
	            
	        }else if(locationLevel!=null && locationLevel==7l){//village level
	            if(stateList.get(0)==0l){
	                sb.append(" and model.meetingAddress.state.stateId is not null ");
	            }else{
	                sb.append(" and model.meetingAddress.state.stateId in (:stateList) ");
	            }
	            if(districtList.get(0)==0l){
	                sb.append(" and model.meetingAddress.district.districtId is not null ");
	            }else{
	                sb.append(" and model.meetingAddress.district.districtId in (:districtList) ");
	            }
	            if(constituencyList.get(0)==0l){
	                sb.append(" and model.meetingAddress.constituency.constituencyId is not null ");
	            }else{
	                sb.append(" and model.meetingAddress.constituency.constituencyId in (:constituencyList) ");
	            }
	            if(mandalList !=null &&mandalList.size()<=0){
	                sb.append(" and model.meetingAddress.tehsil.tehsilId is not null ");
	            }else if(mandalList !=null ){
	                sb.append(" and model.meetingAddress.tehsil.tehsilId in (:mandalList) ");
	            }
	            if(villageList !=null &&villageList.size()<=0){
	                sb.append(" and model.meetingAddress.panchayat.panchayatId is not null ");
	            }else if(villageList !=null){
	                sb.append(" and model.meetingAddress.panchayat.panchayatId in (:villageList) ");
	            }
	        }
	        
	        if(startDate!=null && endDate!=null){
	            sb.append(" and  ( (date(model.startDate)>=:startDate and date(model.startDate)<=:endDate) or (date(model.endDate)>=:startDate and date(model.endDate)<=:endDate) ) ");
	        }
	        
	        if(status != null && status.trim().equalsIgnoreCase("maybe")){
	        	sb.append(" and model.isConducted is not null and model.isConductedByIvr is not null" +
	        				" and ((model.isConducted = 'Y' and model.isConductedByIvr = 'N')" +
	        				" or (model.isConducted = 'N' and model.isConductedByIvr = 'Y'))");
	        }
	        else if(status != null && status.trim().equalsIgnoreCase("no")){
	        	sb.append(" and ((model.isConducted = 'N' and model.isConductedByIvr = 'N')" +
	        				" or (model.isConducted is null and model.isConductedByIvr = 'N')" +
	        				" or (model.isConducted = 'N' and model.isConductedByIvr is null))");
	        }
	       /* else if(status != null && status.trim().equalsIgnoreCase("all")){
	        	sb.append(" and (model.isConducted is not null and model.isConductedByIvr is not null" +
	        				" and ((model.isConducted = 'Y' and model.isConductedByIvr = 'N')" +
	        				" or (model.isConducted = 'N' and model.isConductedByIvr = 'Y')))" +
	        				" OR ((model.isConducted = 'N' and model.isConductedByIvr = 'N')" +
	        				" or (model.isConducted is null and model.isConductedByIvr = 'N')" +
	        				" or (model.isConducted = 'N' and model.isConductedByIvr is null))");
	        	/*sb.append(" and ((model.isConducted != 'Y' and model.isConductedByIvr != 'Y')" +
	        				" and model.isConducted != 'Y' and model.isConductedByIvr is null" +
	        				" or (model.isConducted is null and model.isConductedByIvr != 'Y'))");
	        }*/
	        
	        sb.append(" order by model.startDate desc ");
	        
	        Query query = getSession().createQuery(sb.toString());
	        
	        if(meetingType!=null && meetingType>0l){
	            query.setParameter("meetingType", meetingType);
	        }
	        
	        if(meetingLevel>0l){
	        	query.setParameter("meetingLevel", meetingLevel);
	        }
	        else if(locationLevel!=null){
	            query.setParameter("locationLevel", locationLevel);
	        }
	        
	        if(locationLevel!=null && locationLevel==1l){
	            if(stateList.get(0)>0l){
	                query.setParameterList("stateList", stateList);
	            }
	        }
	        
	        if(locationLevel!=null && locationLevel==2l){
	            if(stateList.get(0)>0l){
	                query.setParameterList("stateList", stateList);
	            }
	            if(districtList.get(0)>0l){
	                query.setParameterList("districtList", districtList);
	            }
	        }
	        
	        if(locationLevel!=null && locationLevel==3l){
	            if(stateList.get(0)>0l){
	                query.setParameterList("stateList", stateList);
	            }
	            if(districtList.get(0)>0l){
	                query.setParameterList("districtList", districtList);
	            }
	            if(constituencyList.get(0)>0l){
	                query.setParameterList("constituencyList", constituencyList);
	            }
	        }
	        
	        if(locationLevel!=null && locationLevel==4l){
	            if(stateList.get(0)>0l){
	                query.setParameterList("stateList", stateList);
	            }
	            if(districtList.get(0)>0l){
	                query.setParameterList("districtList", districtList);
	            }
	            if(constituencyList.get(0)>0l){
	                query.setParameterList("constituencyList", constituencyList);
	            }
	            if(mandalList !=null && mandalList.size()>0){
	                query.setParameterList("mandalList", mandalList);
	            }
	        }
	        
	        if(locationLevel!=null && locationLevel==5l){
	            if(stateList.get(0)>0l){
	                query.setParameterList("stateList", stateList);
	            }
	            if(districtList.get(0)>0l){
	                query.setParameterList("districtList", districtList);
	            }
	            if(constituencyList.get(0)>0l){
	                query.setParameterList("constituencyList", constituencyList);
	            }
	            if(townList !=null && townList.size()>0){
	                query.setParameterList("townList", townList);
	            }
	        }
	        
	        if(locationLevel!=null && (locationLevel==6l || locationLevel==8l)){
	            if(locationLevel==6l){
	                if(stateList.get(0)>0l){
	                    query.setParameterList("stateList", stateList);
	                }
	                if(districtList.get(0)>0l){
	                    query.setParameterList("districtList", districtList);
	                }
	                if(constituencyList.get(0)>0l){
	                    query.setParameterList("constituencyList", constituencyList);
	                }
	                if(divisonList !=null && divisonList.size()>0){
	                    query.setParameterList("divisonList", divisonList);
	                }
	            }else{
	                if(stateList.get(0)>0l){
	                    query.setParameterList("stateList", stateList);
	                }
	                if(districtList.get(0)>0l){
	                    query.setParameterList("districtList", districtList);
	                }
	                if(constituencyList.get(0)>0l){
	                    query.setParameterList("constituencyList", constituencyList);
	                }
	                
	                if(townList !=null && townList.size()>0){
	                	query.setParameterList("townList", townList);
	                }
	                
	                /*if(mandalList !=null && mandalList.size()>0){
	                    query.setParameterList("mandalList", mandalList);
	                }*/
	                if(wardList !=null && wardList.size()>0){
	                    query.setParameterList("wardList", wardList);
	                }
	            }
	        }
	        
	        if(locationLevel!=null && locationLevel==7l){
	            if(stateList.get(0)>0l){
	                query.setParameterList("stateList", stateList);
	            }
	            if(districtList.get(0)>0l){
	                query.setParameterList("districtList", districtList);
	            }
	            if(constituencyList.get(0)>0l){
	                query.setParameterList("constituencyList", constituencyList);
	            }
	            if(mandalList !=null && mandalList.size()>0){
	                query.setParameterList("mandalList", mandalList);
	            }
	            if(villageList !=null && villageList.size()>0){
	                query.setParameterList("villageList", villageList);
	            }
	        }
	        
	        if(startDate!=null && endDate!=null){
	            query.setDate("startDate", startDate);
	            query.setDate("endDate", endDate);
	        }
	        return query.list();
	    }
   
   public List<Object[]> getThirdPartyStatusDetails(List<Long> partyMeetingIds){
	   Query query = getSession().createQuery("select model.partyMeetingId,model.thirdPartyStatus" +
	   											" from PartyMeeting model" +
	   											" where model.partyMeetingId in (:partyMeetingIds)" +
	   											" and model.thirdPartyStatus is not null");
	   query.setParameterList("partyMeetingIds", partyMeetingIds);
	   
	   return query.list();
   }
   
   public List<Object[]> totalMeetingsInConstituencyLevelWise(int month , int year){
	   
		
		  Query query = getSession().createQuery("" +
		  " select   model.meetingAddress.constituency.constituencyId , model.meetingAddress.constituency.name," +//1
		  "          model.partyMeetingLevel.partyMeetingLevelId,model.partyMeetingLevel.level,count(distinct  model.partyMeetingId) " +//4
		  " from     PartyMeeting model " +
		  " where    model.isActive = 'Y' and model.meetingAddress.constituency.constituencyId = 232 " +
		  "          month(model.startDate) =:month and year(model.startDate) =:year " +
		  " group by model.meetingAddress.constituency.constituencyId , model.partyMeetingLevel.partyMeetingLevelId " +
		  " order by  model.meetingAddress.constituency.name , model.partyMeetingLevel.partyMeetingLevelId ");
		  
		  query.setParameter("month", month);
		  query.setParameter("year", year);
		  return query.list();
	   }
	   
	   public List<Object[]> notConductedMeetingsInConstituencyLevelWise(int month , int year){
		  
		   Query query = getSession().createQuery("" +
		   " select  model.partyMeeting.meetingAddress.constituency.constituencyId , " +//0
		   "         model.partyMeeting.partyMeetingLevel.partyMeetingLevelId,model.partyMeeting.partyMeetingLevel.level," +//2
		   "         count(distinct  model.partyMeeting.partyMeetingId)" +//3
		   " from    PartyMeetingStatus model " +
		   " where   model.partyMeeting.isActive = 'Y' and model.mettingStatus = 'N' and " +
		   "         model.partyMeeting.meetingAddress.constituency.constituencyId = 232 and " +
		   "         month(model.partyMeeting.startDate) =:month and year(model.partyMeeting.startDate) =:year " +
		   " group by model.partyMeeting.meetingAddress.constituency.constituencyId ,model.partyMeeting.partyMeetingLevel.partyMeetingLevelId ");
		   
		   query.setParameter("month", month);
		   query.setParameter("year", year);
		   return query.list();
	   }
	 
	   public List<Object[]> notConductedMeetingsInConstituency(int month , int year){
		   
		   Query query = getSession().createQuery("" +
		   " select  model.partyMeeting.meetingAddress.constituency.constituencyId ,model.partyMeeting.partyMeetingLevel.partyMeetingLevelId,model.partyMeeting.partyMeetingLevel.level,  " +//2
		   "         model.partyMeeting.partyMeetingId ,model.partyMeeting.meetingName," +//4
		   "         model.partyMeeting.isConducted ,model.partyMeeting.isConductedByIvr,model.partyMeeting.thirdPartyStatus,model.partyMeeting.remarks " +//8
		   " from    PartyMeetingStatus model " +
		   " where   model.partyMeeting.isActive = 'Y' and model.mettingStatus = 'N' and " +
		   "         model.partyMeeting.meetingAddress.constituency.constituencyId = 232 and " +
		   "         month(model.partyMeeting.startDate) =:month and year(model.partyMeeting.startDate) =:year " +
		   " order by model.partyMeeting.partyMeetingLevel.partyMeetingLevelId ");
		   
		   query.setParameter("month", month);
		   query.setParameter("year", year);
		   return query.list(); 
		   
	   }
	   
	   
	   public List<Object[]> getConstWiseNotConductedPartyMeetings(int month , int year){
		 
		   Query query = getSession().createSQLQuery("" +
		   " select   PM.party_meeting_level_id as levelId, PML.level as level," +//1
		   "          C.constituency_id as constituencyId, T.tehsil_id as tehsilId, T.name_local as tehsilName,P.panchayat_id as panchayatId, P.name_local as panchayatName," +//6
		   "          LEB.local_election_body_id as lebId,LEB.name_local as lebName, ward.constituency_id as wardId, ward.name_local as wardName, " +//10
		   "          PM.party_meeting_id as partyMeetingId, PM.meeting_name as meetingName " +//12
		   " from     party_meeting PM " +
		   "          join party_meeting_level PML on PM.party_meeting_level_id = PML.party_meeting_level_id   " +
		   "          join party_meeting_status PMS on PM.party_meeting_id = PMS.party_meeting_id  " +
		   "          join user_address UA on PM.meeting_address_id = UA.user_address_id " +
		   "          join constituency C on UA.constituency_id = C.constituency_id " +
		   "          left join local_election_body LEB on  UA.local_election_body = LEB.local_election_body_id " +
		   "          left join constituency ward on UA.ward = ward.constituency_id  " +
		   "          left join tehsil T on UA.tehsil_id = T.tehsil_id   " +
		   "          left join panchayat P on UA.panchayat_id = P.panchayat_id " +
		   " where    PM.is_active = 'Y'   and PML.party_meeting_level_id in (4,5,6,7,8) and   PMS.meeting_status = 'N' and " +
		   "          month(PM.start_date)=:month and year(PM.start_date)= :year " +
		   " order by PM.party_meeting_level_id ")
		   .addScalar("levelId", Hibernate.LONG)
			.addScalar("level", Hibernate.STRING)
			.addScalar("constituencyId", Hibernate.LONG)
			.addScalar("tehsilId", Hibernate.LONG)
			.addScalar("tehsilName", Hibernate.STRING)
			.addScalar("panchayatId", Hibernate.LONG)
			.addScalar("panchayatName", Hibernate.STRING)
			.addScalar("lebId", Hibernate.LONG)
			.addScalar("lebName", Hibernate.STRING)
			.addScalar("wardId", Hibernate.LONG)
			.addScalar("wardName", Hibernate.STRING)
			.addScalar("partyMeetingId", Hibernate.LONG)
			.addScalar("meetingName", Hibernate.STRING);
		   query.setParameter("month",month);
		   query.setParameter("year", year);
		   return query.list(); 
	   }
	   
	  public List<Object[]> getConstInchargeTeluguNames(){
		  
		 Query query = getSession().createSQLQuery("" +
		 " select  C.constituency_id constId ,TC.tdp_cadre_id as cadreId, " +//1
		 "         TRIM(CONCAT(TRIM(VA.firstname),' ',TRIM(VA.lastname))) AS voter_name ,  TRIM(TCN.telugu_name) AS cadre_name " +//3
		 " from    self_appraisal_candidate_location_new SN,constituency C,self_appraisal_candidate SC  " +
		 "         JOIN  tdp_cadre TC  ON SC.tdp_cadre_id = TC.tdp_cadre_id " +
		 "         LEFT OUTER JOIN voter_names VA ON TC.voter_id = VA.voter_id " +
		 "         LEFT OUTER JOIN tdp_cadre_telugu_names TCN ON TC.tdp_cadre_id = TCN.tdp_cadre_id " +
		 " where   SC.self_appraisal_candidate_id=SN.self_appraisal_candidate_id and  " +
		 "         SN.self_appraisal_tour_category_id=4 and " +
		 "         SN.location_value=C.constituency_id and  " +
		 "         SC.self_appraisal_designation_id in (7,8) and SC.is_active='Y' ")
		 .addScalar("constId", Hibernate.LONG)
	     .addScalar("cadreId", Hibernate.LONG)
		 .addScalar("voter_name", Hibernate.STRING)
		 .addScalar("cadre_name", Hibernate.STRING);
	     return query.list();
	  }
	 public String getPartyMeetingName(Long partyMeetingId){
		 Query query = getSession().createQuery(" select model.meetingName from PartyMeeting model where model.partyMeetingId=:partyMeetingId  and model.isActive='Y'  ");
		 query.setParameter("partyMeetingId", partyMeetingId);
		 return (String)query.uniqueResult();
	 }
	 public List<Long> getPartyMeetingIdList(){
		 Query query = getSession().createQuery(" select model.partyMeetingId from PartyMeeting model where model.partyMeetingId > 3");
		 return query.list();
	 }
 }
