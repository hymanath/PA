package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAccommodationTrackingDAO;
import com.itgrids.partyanalyst.model.AccommodationTracking;

public class AccommodationTrackingDAO extends GenericDaoHibernate<AccommodationTracking, Long> implements IAccommodationTrackingDAO{

	public AccommodationTrackingDAO() {
		super(AccommodationTracking.class);
		
	}

	public List<Object[]> getAccommodationTrackingInfoByNotificationType(Long typeId, Long locationType,Long lastAccommodationTrackingId){
		StringBuilder sb = new StringBuilder();
		sb.append("select model.notificationType.notificationTypeId," +  //0
						" model.notificationType.notificationType," +  //1
						" model.locationTypeId," +	//2
						" constituency.constituencyId," +	//3
						" constituency.name," +	//4
						" constituency.district.districtId," +	//5
						" constituency.district.districtName," +	//6
						" model.locationName," +	//7
						" model.address," +	//8
						" model.contactPerson," +	//9
						" model.mobileNo," +	//10
						" model.longitude," +	//11
						" model.latitude," +	//12
						" model.insertedTime," +	//13
						" model.updatedTime," +//14
						" model.accommodationTrackingId" +	//15
						" from AccommodationTracking model,Constituency constituency" +
						" where constituency.constituencyId = model.locationValue" +
						" and model.isActive = 'true'" +
						" and model.locationTypeId = :locationType" +
						" and model.notificationType.isActive = 'true'");
		if(typeId !=null && typeId.longValue() > 0l)
			sb.append(" and model.notificationType.typeId = :typeId");
		if(lastAccommodationTrackingId != null && lastAccommodationTrackingId.longValue()>0L)
			sb.append(" and model.accommodationTrackingId > :lastAccommodationTrackingId");
		
		Query query = getSession().createQuery(sb.toString());
		if(typeId !=null &&  typeId.longValue() > 0l)
			query.setParameter("typeId", typeId);
		if(lastAccommodationTrackingId != null && lastAccommodationTrackingId.longValue()>0L)
			query.setParameter("lastAccommodationTrackingId", lastAccommodationTrackingId);
		query.setParameter("locationType", locationType);
		if(lastAccommodationTrackingId == null || lastAccommodationTrackingId.longValue() == 0l)
			return null;
		return query.list();
	}
	
	public List<Long> getInactiveAccommodationTrackingInfoByNotificationType(Long typeId, Long locationType,Long lastAccommodationTrackingId){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.accommodationTrackingId " +	
						" from AccommodationTracking model where  model.locationTypeId = :locationType" +
						" and model.isActive = 'false'");
		if(typeId !=null && typeId.longValue() > 0l)
			sb.append(" and model.typeId = :typeId");
		if(lastAccommodationTrackingId != null && lastAccommodationTrackingId.longValue()>0L)
			sb.append(" and model.accommodationTrackingId <= :lastAccommodationTrackingId");
		
		Query query = getSession().createQuery(sb.toString());
		if(typeId !=null &&  typeId.longValue() > 0l)
			query.setParameter("typeId", typeId);		
		if(lastAccommodationTrackingId != null && lastAccommodationTrackingId.longValue()>0L)
			query.setParameter("lastAccommodationTrackingId", lastAccommodationTrackingId);
		query.setParameter("locationType", locationType);
		
		return query.list();
	}
	
}
