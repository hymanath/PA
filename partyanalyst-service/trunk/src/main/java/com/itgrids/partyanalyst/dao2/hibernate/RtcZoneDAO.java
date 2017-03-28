package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IRtcZoneDAO;
import com.itgrids.partyanalyst.model.RtcZone;

public class RtcZoneDAO extends GenericDaoHibernate<RtcZone, Long> implements IRtcZoneDAO{

	public RtcZoneDAO() {
		super(RtcZone.class);
		
	}
	
	public List<Object[]> getRtcZoneDetails(){
		
		org.hibernate.Query query = getSession().createQuery("select model.rtcZoneId,model.zoneName" +
				"  from RtcZone model ");
		
		return query.list();
	}

}
