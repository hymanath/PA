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
	
	public List<Object[]> getAllMeetings(Long meetingType,Long locationLevel,Long stateId,Long districtId,Long constituencyId,Long mandalId,Long townId,Long divisonId,Long villageId,Long wardId,Date startDate,Date endDate){
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select model.partyMeetingType.partyMeetingTypeId,model.partyMeetingType.type, " +
				" model.partyMeetingLevel.partyMeetingLevelId,model.partyMeetingLevel.level, " +
				" model.locationValue, date(model.startDate), date(model.endDate),model.meetingAddress.userAddressId,model.meetingName " +
				"from PartyMeeting model " +
				" where model.isActive='Y' " );
		if(meetingType!=null && meetingType>0l){
			sb.append(" and model.partyMeetingType.partyMeetingTypeId=:meetingType ");
		}
		
		if(locationLevel!=null){
			sb.append(" and model.partyMeetingLevel.partyMeetingLevelId=:locationLevel ");
		}
		
		if(locationLevel!=null && locationLevel==1l){//state level
			if(stateId==0l){
				sb.append(" and model.meetingAddress.state.stateId is not null ");
			}else{
				sb.append(" and model.meetingAddress.state.stateId = :stateId ");
			}
		}else if(locationLevel!=null && locationLevel==2l){//district level
			if(districtId==0l){
				sb.append(" and model.meetingAddress.district.districtId is not null ");
			}else if(districtId>0l){
				sb.append(" and model.meetingAddress.district.districtId=:districtId ");
			}
		}else if(locationLevel!=null && locationLevel==3l){//constituency level
			if(constituencyId==0l){
				sb.append(" and model.meetingAddress.constituency.constituencyId is not null ");
			}else if(constituencyId>0l){
				sb.append(" and model.meetingAddress.constituency.constituencyId=:constituencyId ");
			}
		}else if(locationLevel!=null && locationLevel==4l){//mandal level
			if(mandalId==0l){
				sb.append(" and model.meetingAddress.tehsil.tehsilId is not null ");
			}else{
				sb.append(" and model.meetingAddress.tehsil.tehsilId = :mandalId ");
			}
		}else if(locationLevel!=null && locationLevel==5l){//town level
			if(townId>0l){
				sb.append(" and model.meetingAddress.localElectionBody.localElectionBodyId is not null ");
			}else{
				sb.append(" and model.meetingAddress.localElectionBody.localElectionBodyId=:localElectionBodyId ");
			}
		}else if((locationLevel!=null) && (locationLevel==6l || locationLevel==8l)){//divison or ward level
			if(locationLevel==6l){
				if(divisonId==0l){
					sb.append(" and model.meetingAddress.ward.wardId is not null ");
				}else{
					sb.append(" and model.meetingAddress.ward.wardId=:divisonId ");
				}
			}else{
				if(wardId==0l){
					sb.append(" and model.meetingAddress.ward.wardId is not null ");
				}else{
					sb.append(" and model.meetingAddress.ward.wardId=:wardId ");
				}
			}
			
		}else if(locationLevel!=null && locationLevel==7l){//village level
			if(villageId==0l){
				sb.append(" and model.meetingAddress.panchayat.panchayatId is not null ");
			}else{
				sb.append(" and model.meetingAddress.panchayat.panchayatId=:villageId ");
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
			if(stateId>0l){
				query.setParameter("stateId", stateId);
			}
		}
		
		if(locationLevel!=null && locationLevel==2l){
			if(districtId>0l){
				query.setParameter("districtId", districtId);
			}
		}
		
		if(locationLevel!=null && locationLevel==3l){
			if(constituencyId>0l){
				query.setParameter("constituencyId", constituencyId);
			}
		}
		
		if(locationLevel!=null && locationLevel==4l){
			if(mandalId>0l){
				query.setParameter("mandalId", mandalId);
			}
		}
		
		if(locationLevel!=null && locationLevel==5l){
			if(townId>0){
				query.setParameter("townId", townId);
			}
		}
		
		if(locationLevel!=null && (locationLevel==6l || locationLevel==8l)){
			if(locationLevel==6l){
				if(divisonId>0l){
					query.setParameter("divisonId", divisonId);
				}
			}else{
				query.setParameter("wardId", wardId);
			}
		}
		
		if(locationLevel!=null && locationLevel==7l){
			if(villageId>0l){
				query.setParameter("villageId", villageId);
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
