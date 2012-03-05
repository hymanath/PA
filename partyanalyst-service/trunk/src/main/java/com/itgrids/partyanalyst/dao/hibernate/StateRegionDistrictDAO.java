package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IStateRegionDAO;
import com.itgrids.partyanalyst.dao.IStateRegionDistrictDAO;
import com.itgrids.partyanalyst.model.StateRegion;
import com.itgrids.partyanalyst.model.StateRegionDistrict;

public class StateRegionDistrictDAO extends GenericDaoHibernate<StateRegionDistrict, Long> implements
IStateRegionDistrictDAO {

	public StateRegionDistrictDAO( ) {
		super(StateRegionDistrict.class);
		
	}
	@SuppressWarnings("unchecked")
	public List <Long> getStateRegionDistrictByType(Long stateRegionId){
		return getHibernateTemplate().find("select model.districtName from District model where " +
				" model.districtId in(select model1.stateRegionDistrictId from StateRegionDistrict model1" +
				" where model1.stateRegion.stateRegionId = ?) ", stateRegionId);
				
	}
	
	@SuppressWarnings("unchecked")
	public List getDistrictsInARegion(Long regionId){
		
		return getHibernateTemplate().find("select model.district.districtId,model.district.districtName from "+
				"StateRegionDistrict model where model.stateRegion.stateRegionId = ?",regionId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getConstituenciesCountByDistrictRegion(Long regionId){
		return getHibernateTemplate().find("select count(model.constituencyId) from Constituency model where " +
				" model.district.districtId in (select model1.district.districtId from StateRegionDistrict model1" +
				" where model1.stateRegion.stateRegionId = ?)",regionId);
	}
}


