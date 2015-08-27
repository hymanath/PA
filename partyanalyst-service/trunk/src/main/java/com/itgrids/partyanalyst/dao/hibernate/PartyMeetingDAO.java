package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPartyMeetingDAO;
import com.itgrids.partyanalyst.model.Mandal;
import com.itgrids.partyanalyst.model.PartyMeeting;


public class PartyMeetingDAO extends GenericDaoHibernate<PartyMeeting,Long> implements IPartyMeetingDAO{

	public PartyMeetingDAO()
	{
		super(PartyMeeting.class);
	}
	
	 public List<Object[]> getAllMeetings(Long meetingType,Long locationLevel,List<Long> stateList,List<Long> districtList,List<Long> constituencyList,List<Long> mandalList,List<Long> townList,List<Long> divisonList,List<Long> villageList,List<Long> wardList,Date startDate,Date endDate){
	        StringBuilder sb = new StringBuilder();
	        
	        sb.append(" select model.partyMeetingType.partyMeetingTypeId," +//0 - MeetingTypeId
	        		" model.partyMeetingType.type, " +//1 - Meeting Type
	                " model.partyMeetingLevel.partyMeetingLevelId," + // 2- Meeting Level Id
	                " model.partyMeetingLevel.level, " + // 3 - Meeting Level
	                " model.locationValue," + // 4 -- Location Value
	                " date(model.startDate)," + // 5 -- Start Date
	                " date(model.endDate)," + // 6 -- End Date
	                " model.meetingAddress.userAddressId," + // 7 -- User Address Id
	                " model.meetingName," + // 8 -- Meeting Name
	                " model.partyMeetingId " + // 9 -- Party Meeting Id 
	                " from PartyMeeting model " +
	                " where model.isActive='Y' " );
	        if(meetingType!=null && meetingType>0l){
	            sb.append(" and model.partyMeetingType.partyMeetingTypeId=:meetingType ");
	        }
	        
	        if(locationLevel!=null){
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
	        
	        if(locationLevel!=null){
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
	    	StringBuilder sb = new StringBuilder("SELECT model.partyMeetingId FROM PartyMeeting model where model.partyMeetingLevel.partyMeetingLevelId = :partyMeetingLevelId and date(model.startDate) between date(:fromDate) and date(:toDate) and date(model.endDate) between date(:fromDate) and date(:toDate)");
	    	
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
	    	query.setParameter("partyMeetingLevelId",partyMeetingLevelId);
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
}
