package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.ILightPoleDetailsDAO;
import com.itgrids.model.LightPoleDetails;

@Repository
public class LightPoleDetailsDAO extends GenericDaoHibernate<LightPoleDetails, Long> implements ILightPoleDetailsDAO{

	public LightPoleDetailsDAO() {
		super(LightPoleDetails.class);
		
	}

	public List<Object[]> getLocationWisePolesDetails(String locationType){
		StringBuilder sb = new StringBuilder();
		sb.append("select");
		if(locationType != null && locationType.equalsIgnoreCase("state"))
			sb.append(" model.locationAddress.state.stateId,");
		else if(locationType != null && locationType.equalsIgnoreCase("district"))
			sb.append(" model.locationAddress.district.districtId,");
		else if(locationType != null && locationType.equalsIgnoreCase("parliament"))
			sb.append(" model.locationAddress.parliament.constituencyId,");
		else if(locationType != null && locationType.equalsIgnoreCase("constituency"))
			sb.append(" model.locationAddress.constituency.constituencyId,");
		else if(locationType != null && locationType.equalsIgnoreCase("mandal"))
			sb.append(" model.locationAddress.tehsil.tehsilId,");
		else if(locationType != null && locationType.equalsIgnoreCase("panchayat"))
			sb.append(" model.locationAddress.panchayat.panchayatId,");
		sb.append(" sum(model.totalPoles)"
				+ " from LightPoleDetails model"
				+ " where model.isDeleted = 'N'"
				+ " group by");
		if(locationType != null && locationType.equalsIgnoreCase("state"))
			sb.append(" model.locationAddress.state.stateId");
		else if(locationType != null && locationType.equalsIgnoreCase("district"))
			sb.append(" model.locationAddress.district.districtId");
		else if(locationType != null && locationType.equalsIgnoreCase("parliament"))
			sb.append(" model.locationAddress.parliament.constituencyId");
		else if(locationType != null && locationType.equalsIgnoreCase("constituency"))
			sb.append(" model.locationAddress.constituency.constituencyId");
		else if(locationType != null && locationType.equalsIgnoreCase("mandal"))
			sb.append(" model.locationAddress.tehsil.tehsilId");
		else if(locationType != null && locationType.equalsIgnoreCase("panchayat"))
			sb.append(" model.locationAddress.panchayat.panchayatId");
		
		Query query = getSession().createQuery(sb.toString());
		return query.list();
	}
}
