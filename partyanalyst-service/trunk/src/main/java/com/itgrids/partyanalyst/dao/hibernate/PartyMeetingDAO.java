package com.itgrids.partyanalyst.dao.hibernate;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPartyMeetingDAO;
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
	                if(mandalList !=null && mandalList.size()>0){
	                    query.setParameterList("mandalList", mandalList);
	                }
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
	        		" and date(PM.startDate) <= :toDayDate ");
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
	    	StringBuilder sb = new StringBuilder("SELECT model.partyMeetingId FROM PartyMeeting model where  date(model.startDate) between date(:fromDate) and date(:toDate) and date(model.endDate) between date(:fromDate) and date(:toDate)");
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
								"   Booth B left join B.panchayat P    left join B.tehsil T where  model.locationValue = P.panchayatId  " +
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
        	"       model.partyMeetingLevel.partyMeetingLevelId,model.startDate," +//3
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
        
}
