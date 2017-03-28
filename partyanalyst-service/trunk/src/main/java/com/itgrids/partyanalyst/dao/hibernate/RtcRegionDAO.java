package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IRtcRegionDAO;
import com.itgrids.partyanalyst.model.RtcRegion;

public class RtcRegionDAO extends GenericDaoHibernate<RtcRegion, Long> implements IRtcRegionDAO{

	public RtcRegionDAO() {
		super(RtcRegion.class);
		
	}
	
	public List<Object[]> getRegionsOfZone(Long zoneId){
		Query query = getSession().createQuery(" select model.rtcRegionId,model.regionName " +
				" from RtcRegion model " +
				" where model.rtcZoneId=:zoneId ");
		query.setParameter("zoneId", zoneId);
		return query.list(); 
	}
	
	public List<Object[]> getAllRegionsWithZone(){
		Query query = getSession().createQuery(" select model.rtcRegionId,model.regionName,model.rtcZone.rtcZoneId,model.rtcZone.zoneName " +
				" from RtcRegion model " +
				" ");
		return query.list(); 
	}
	
}
