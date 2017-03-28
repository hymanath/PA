package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IRtcDepotDAO;
import com.itgrids.partyanalyst.model.RtcDepot;

public class RtcDepotDAO extends GenericDaoHibernate<RtcDepot, Long> implements IRtcDepotDAO{

	public RtcDepotDAO() {
		super(RtcDepot.class);
		
	}
	
	public List<Object[]> getDepotsOfRegion(Long regionId){
		Query query = getSession().createQuery(" select model.rtcDepotId,model.depotName " +
				" from RtcDepot model " +
				" where model.rtcRegionId=:regionId ");
		query.setParameter("regionId", regionId);
		return query.list();
	}
	
	public List<Object[]> getDepotsOfAllRegions(List<Long> regionIds){
		Query query = getSession().createQuery(" select model.rtcDepotId,model.depotName " +
				" from RtcDepot model " +
				" where model.rtcRegionId in (:regionIds) ");
		query.setParameterList("regionIds", regionIds);
		return query.list();
	}
	
	public Object[] getRegionAndZoneByDepotId(Long depotId){
		Query query=getSession().createQuery("select  model.rtcDepotId,model.rtcRegion.rtcRegionId,model.rtcRegion.rtcZone.rtcZoneId  from RtcDepot model where model.rtcDepotId=:depotId");
		query.setParameter("depotId", depotId);
		return (Object[])query.uniqueResult();
	}
	
	public List<Object[]> getAllDepots(){
		Query query = getSession().createQuery(" select model.rtcDepotId,model.depotName " +
				" from RtcDepot model " );
		
		return query.list();
	}
}
