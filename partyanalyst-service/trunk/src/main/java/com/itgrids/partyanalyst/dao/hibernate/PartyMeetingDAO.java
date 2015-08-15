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
		
		sb.append(" select model.partyMeetingType.partyMeetingTypeId,model.partyMeetingType.type, " +
				" model.partyMeetingLevel.partyMeetingLevelId,model.partyMeetingLevel.level, " +
				" model.locationValue, date(model.startDate), date(model.endDate),model.meetingAddress.userAddressId,model.meetingName,model.partyMeetingId " +
				"from PartyMeeting model " +
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
			if(districtList.get(0)==0l){
				sb.append(" and model.meetingAddress.district.districtId is not null ");
			}else{
				sb.append(" and model.meetingAddress.district.districtId in (:districtList) ");
			}
		}else if(locationLevel!=null && locationLevel==3l){//constituency level
			if(constituencyList.get(0)==0l){
				sb.append(" and model.meetingAddress.constituency.constituencyId is not null ");
			}else{
				sb.append(" and model.meetingAddress.constituency.constituencyId in (:constituencyList) ");
			}
		}else if(locationLevel!=null && locationLevel==4l){//mandal level
			if(mandalList.get(0)==0l){
				sb.append(" and model.meetingAddress.tehsil.tehsilId is not null ");
			}else{
				sb.append(" and model.meetingAddress.tehsil.tehsilId in (:mandalList) ");
			}
		}else if(locationLevel!=null && locationLevel==5l){//town level
			if(townList.get(0)==0l){
				sb.append(" and model.meetingAddress.localElectionBody.localElectionBodyId is not null ");
			}else{
				sb.append(" and model.meetingAddress.localElectionBody.localElectionBodyId in (:townList) ");
			}
		}else if((locationLevel!=null) && (locationLevel==6l || locationLevel==8l)){//divison or ward level
			if(locationLevel==6l){
				if(divisonList.get(0)==0l){
					sb.append(" and model.meetingAddress.ward.wardId is not null ");
				}else{
					sb.append(" and model.meetingAddress.ward.wardId in (:divisonList) ");
				}
			}else{
				if(wardList.get(0)==0l){
					sb.append(" and model.meetingAddress.ward.wardId is not null ");
				}else{
					sb.append(" and model.meetingAddress.ward.wardId in (:wardList) ");
				}
			}
			
		}else if(locationLevel!=null && locationLevel==7l){//village level
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
			if(districtList.get(0)>0l){
				query.setParameterList("districtList", districtList);
			}
		}
		
		if(locationLevel!=null && locationLevel==3l){
			if(constituencyList.get(0)>0l){
				query.setParameterList("constituencyList", constituencyList);
			}
		}
		
		if(locationLevel!=null && locationLevel==4l){
			if(mandalList.get(0)>0l){
				query.setParameterList("mandalList", mandalList);
			}
		}
		
		if(locationLevel!=null && locationLevel==5l){
			if(townList.get(0)>0){
				query.setParameterList("townList", townList);
			}
		}
		
		if(locationLevel!=null && (locationLevel==6l || locationLevel==8l)){
			if(locationLevel==6l){
				if(divisonList.get(0)>0l){
					query.setParameterList("divisonList", divisonList);
				}
			}else{
				if(wardList.get(0)==0l){
					query.setParameterList("wardList", wardList);
				}
			}
		}
		
		if(locationLevel!=null && locationLevel==7l){
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
	
	public List<Object[]> getPartyMeetingDetailsByMeetingIdList(List<Long> patyMeetingIdsList)
	{
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct PM.partyMeetingId, PM.meetingName, PM.partyMeetingLevel.level, PM.partyMeetingType.type,PM.meetingAddress.localArea  from PartyMeeting PM where PM.partyMeetingId in (:patyMeetingIdsList) ");
		Query query = getSession().createQuery(sb.toString());
		if(patyMeetingIdsList!=null && patyMeetingIdsList.size()>0){
			query.setParameterList("patyMeetingIdsList", patyMeetingIdsList);
		}
		
		return query.list();
	}
}
