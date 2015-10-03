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
	        
	        if(meetingLevel>=2){
	        	sb.append(" ,model.meetingAddress.district.districtId ");	// 12 -- District Id
	        	sb.append(" ,model.meetingAddress.district.districtName ");	// 13 -- District Name
	        }
	        if(meetingLevel>=3){
	        	sb.append(" ,model.meetingAddress.constituency.constituencyId "); // 14 -- Constituency Id
	        	sb.append(" ,model.meetingAddress.constituency.name "); 		  // 15 -- Constituency Name
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
	            if(mandalList.get(0)==0l){
	                sb.append(" and model.meetingAddress.tehsil.tehsilId is not null ");
	            }else{
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
	            if(townList.get(0)==0l){
	                sb.append(" and model.meetingAddress.localElectionBody.localElectionBodyId is not null ");
	            }else{
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
	        
	        if(startDate!=null && endDate!=null){
	            sb.append(" and date(model.startDate)>=:startDate and date(model.endDate)<=:endDate ");
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
	            if(mandalList.get(0)>0l){
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
	            if(townList.get(0)>0){
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
	            if(mandalList.get(0)>0l){
	                query.setParameterList("mandalList", mandalList);
	            }
	            if(villageList.get(0)>0l){
	                query.setParameterList("villageList", villageList);
	            }
	        }
	        
	        if(startDate!=null && endDate!=null){
	            query.setParameter("startDate", startDate);
	            query.setParameter("endDate", endDate);
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
								" and B.publicationDate.publicationDateId = 11  ");
						queryStr.append(" and model.partyMeetingLevelId = "+IConstants.VILLAGE_PARTY_MEETING_LEVEL_ID+" and  model.partyMeetingTypeId = "+IConstants.MONTHLY_VILLAGEorWARD_MEETING_ID+"  ");
					}
					else if(committeeLevelId.longValue() == IConstants.WARD_COMMITTEE_LEVEL_ID)
					{
						queryStr.append(", LEB.localElectionBodyId, concat(LEB.name,' ', LEB.electionType.electionType ) ,C.name,model.startDate from PartyMeeting model ," +
								"   Constituency C left join C.localElectionBody  LEB   where  model.locationValue = C.constituencyId ");
						
						queryStr.append(" and model.partyMeetingLevelId = "+IConstants.WARD_PARTY_MEETING_LEVEL_ID+"   and model.partyMeetingTypeId = "+IConstants.MONTHLY_MANDAL_TOWN_DIVISION_MEETING_ID+" ");
					}
					else if(committeeLevelId.longValue() == IConstants.MANDAL_COMMITTEE_LEVEL_ID)
					{
						queryStr.append(" C.constituencyId, C.name,T.tehsilName,, model.startDate from PartyMeeting model, Booth B left join B.tehsil T left join B.constituency C where model.locationValue = T.tehsilId and B.publicationDate.publicationDateId = 11 ");
						queryStr.append(" and model.partyMeetingLevelId = "+IConstants.MANDAL_PARTY_MEETING_LEVEL_ID+"   and model.partyMeetingTypeId = "+IConstants.MONTHLY_MANDAL_TOWN_DIVISION_MEETING_ID+"  ");
					}
					else if(committeeLevelId.longValue() == IConstants.TOWN_COMMITTEE_LEVEL_ID)
					{
						queryStr.append(" C.constituencyId, C.name,concat(LEB.name,' ',LEB.electionType.electionType), model.startDate from PartyMeeting model, Booth B left join B.localBody LEB left join B.constituency C where model.locationValue = LEB.localElectionBodyId and B.publicationDate.publicationDateId = 11 ");
						queryStr.append(" and model.partyMeetingLevelId = "+IConstants.TOWN_PARTY_MEETING_LEVEL_ID+"   and model.partyMeetingTypeId = "+IConstants.MONTHLY_MANDAL_TOWN_DIVISION_MEETING_ID+"  ");
					}
					else if(committeeLevelId.longValue() == IConstants.DIVISION_COMMITTEE_LEVEL_ID)
					{
						queryStr.append(" C.constituencyId, C.name,concat(LEB.name,' ',LEB.electionType.electionType), model.startDate from PartyMeeting model, Booth B left join B.localBody LEB left join B.constituency C where model.locationValue = LEB.localElectionBodyId and B.publicationDate.publicationDateId = 11 ");
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
						queryStr.append(" and model.party_meeting_level_id = "+IConstants.WARD_PARTY_MEETING_LEVEL_ID+"   and model.party_meeting_type_id = "+IConstants.MONTHLY_MANDAL_TOWN_DIVISION_MEETING_ID+" ");
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
					queryStr.append(" and (model.start_date >= :fromDate and model.end_date <= :toDate) ");
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
						queryStr.append(" and model.party_meeting_level_id = "+IConstants.WARD_PARTY_MEETING_LEVEL_ID+"   and model.party_meeting_type_id = "+IConstants.MONTHLY_MANDAL_TOWN_DIVISION_MEETING_ID+" ");
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
					queryStr.append(" and (model.start_date >= :fromDate and model.end_date <= :toDate) ");
				}
				queryStr.append(" group by   date(model.start_date) ");
							
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

}
