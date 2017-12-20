package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.INregaFAVacantPanchayatDAO;
import com.itgrids.model.NregaFAVacantPanchayat;
@Repository
public class NregaFAVacantPanchayatDAO extends GenericDaoHibernate<NregaFAVacantPanchayat, Long> implements INregaFAVacantPanchayatDAO{

	public NregaFAVacantPanchayatDAO() {
		super(NregaFAVacantPanchayat.class);
		
	}

	public List<Object[]> getLocationWiseEmptyVacencies(String locationType,Long locationId,String subLocationType){
		/*
		 * select la.state_id,t.nrega_fa_type_id,t.type,sum(vp.no_of_vacant)
				from nrega_fa_vacant_panchayat vp,nrega_fa_type t,location_address la
				where vp.nrega_fa_type_id = t.nrega_fa_type_id
				and vp.location_address_id = la.location_address_id
				and is_filled = 'N' and la.state_id = 1
				group by la.state_id,t.nrega_fa_type_id;
		 */
		StringBuilder sb = new StringBuilder();
		sb.append("select model.nregaFAType.nregaFaTypeId,model.nregaFAType.type,"
				+ " sum(model.noOfVacant),");
		if(subLocationType != null && subLocationType.trim().equalsIgnoreCase("state"))
			sb.append(" model.locationAddress.state.stateId,model.locationAddress.state.stateName");
		else if(subLocationType != null && subLocationType.trim().equalsIgnoreCase("district"))
			sb.append(" model.locationAddress.district.districtId,model.locationAddress.state.stateName,model.locationAddress.district.districtName");
		else if(subLocationType != null && subLocationType.trim().equalsIgnoreCase("constituency"))
			sb.append(" model.locationAddress.constituency.constituencyId,model.locationAddress.state.stateName,"
					+ " model.locationAddress.district.districtName,model.locationAddress.constituency.name");
		else if(subLocationType != null && subLocationType.trim().equalsIgnoreCase("mandal"))
			sb.append(" model.locationAddress.tehsil.tehsilId,model.locationAddress.state.stateName,model.locationAddress.district.districtName,"
					+ " model.locationAddress.constituency.name,model.locationAddress.tehsil.tehsilName");
		else if(subLocationType != null && subLocationType.trim().equalsIgnoreCase("panchayat"))
			sb.append(" model.locationAddress.panchayat.panchayatId,model.locationAddress.state.stateName,model.locationAddress.district.districtName,"
					+ " model.locationAddress.constituency.name,model.locationAddress.tehsil.tehsilName,model.locationAddress.panchayat.panchayatName");
		
		sb.append(" from NregaFAVacantPanchayat model"
				+ " where model.isFilled = 'N'");
		if(locationType != null && locationType.trim().equalsIgnoreCase("state") && locationId != null && locationId > 0L)
			sb.append(" and model.locationAddress.state.stateId = :locationId");
		else if(locationType != null && locationType.trim().equalsIgnoreCase("district") && locationId != null && locationId > 0L)
			sb.append(" and model.locationAddress.district.districtId = :locationId");
		else if(locationType != null && locationType.trim().equalsIgnoreCase("constituency") && locationId != null && locationId > 0L)
			sb.append(" and model.locationAddress.constituency.constituencyId = :locationId");
		
		sb.append(" group by");
		if(subLocationType != null && subLocationType.trim().equalsIgnoreCase("state"))
			sb.append(" model.locationAddress.state.stateId,");
		else if(subLocationType != null && subLocationType.trim().equalsIgnoreCase("district"))
			sb.append(" model.locationAddress.district.districtId,");
		else if(subLocationType != null && subLocationType.trim().equalsIgnoreCase("constituency"))
			sb.append(" model.locationAddress.constituency.constituencyId,");
		else if(subLocationType != null && subLocationType.trim().equalsIgnoreCase("mandal"))
			sb.append(" model.locationAddress.tehsil.tehsilId,");
		else if(subLocationType != null && subLocationType.trim().equalsIgnoreCase("panchayat"))
			sb.append(" model.locationAddress.panchayat.panchayatId,");
		sb.append(" model.nregaFAType.nregaFaTypeId");
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("locationId", locationId);
		
		return query.list();
	}
}
