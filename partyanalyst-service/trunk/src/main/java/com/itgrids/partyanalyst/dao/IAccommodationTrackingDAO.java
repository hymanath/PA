package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AccommodationTracking;

public interface IAccommodationTrackingDAO extends GenericDao<AccommodationTracking, Long>{
	public List<Object[]> getAccommodationTrackingInfoByNotificationType(Long notificationType, Long locationType,Long lastAccommodationTrackingId);
	public List<Long> getInactiveAccommodationTrackingInfoByNotificationType(Long typeId, Long locationType,Long lastAccommodationTrackingId);
}
