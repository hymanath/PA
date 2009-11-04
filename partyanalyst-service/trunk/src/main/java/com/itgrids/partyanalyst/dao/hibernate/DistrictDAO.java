package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import javax.persistence.Query;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.appfuse.dao.jpa.GenericDaoJpa;

import com.itgrids.partyanalyst.dao.IDistrictDAO; 
import com.itgrids.partyanalyst.dao.columns.enums.DistrictColumnNames; 
import com.itgrids.partyanalyst.model.District;

public class DistrictDAO extends GenericDaoHibernate<District, Long> implements
IDistrictDAO {

	public DistrictDAO() {
		super(District.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<District> findByProperty(DistrictColumnNames propertyName, Object value){
		return getHibernateTemplate().find("from District where " + propertyName + "=? order by districtName", value);
	}
	

	public List<District> findByDistrictName(Object districtName){
		return findByProperty(DistrictColumnNames.DISTRICT_NAME, districtName);
	}

	public List<District> findByDistrictCapital(Object districtCapital){
		return findByProperty(DistrictColumnNames.DISTRICT_CAPITAL, districtCapital);
	}

	public List<District> findByArea(Object area){
		return findByProperty(DistrictColumnNames.AREA, area);
	}

	public List<District> findByPopulation(Object population){
		return findByProperty(DistrictColumnNames.POPULATION, population);
	}

	public List<District> findByDistrictCode(Object districtCode){
		return findByProperty(DistrictColumnNames.DISTRICT_CODE, districtCode);
	}

	@SuppressWarnings("unchecked")
	public List<District> findByStateId(Long stateId){
		return getHibernateTemplate().find("from District model where model.state.stateId=? order by districtName", stateId);
	}

	@SuppressWarnings("unchecked")
	public List<District> getAllOrderByName() {
		return getHibernateTemplate().find("from District model order by districtName");
	}
	
	public List getStateDistrictByDistrictID(Long districtID){
		return getHibernateTemplate().find("Select model.state.stateId, model.state.stateName, model.districtName from District model where model.districtId=?",districtID);
		
	}
	
	
}
