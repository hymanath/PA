package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IDistrictDAO; 
import com.itgrids.partyanalyst.dao.columns.enums.DistrictColumnNames; 
import com.itgrids.partyanalyst.dto.StateToHamletVO;
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
	
	@SuppressWarnings("unchecked")
	public List getStateDistrictByDistrictID(Long districtID){
		return getHibernateTemplate().find("Select model.state.stateId, model.state.stateName, model.districtName from District model where model.districtId=? order by model.state.stateName",districtID);
		
	}

	@SuppressWarnings("unchecked")
	public List getDistrictNameByDistrictId(Long districtId) {
		return getHibernateTemplate().find("Select model.districtName,model.state.stateId from District model where model.districtId=? ", districtId);
	}
	
	@SuppressWarnings("unchecked")
	public List<District> getDistrictIDByStateIDAndDistrictName(Long stateID, String districtName){
		Object[] params = {stateID, districtName};
		return getHibernateTemplate().find("from District model where model.state.stateId=? and model.districtName=? ", params);
	}
	@SuppressWarnings("unchecked")
	public List getStateToDistrictByDistrict(String districtIDs){
		return getHibernateTemplate().find("select model.state.stateId, model.state.stateName, " +
				"model.districtId, model.districtName " +
				" from District model where model.districtId in("+districtIDs+") ");
	}
	
	@SuppressWarnings("unchecked")
	public List getDistrictIdAndNameByState(Long stateId){
		return getHibernateTemplate().find("select model.districtId,model.districtName from District model where model.state.stateId = ? order by model.districtName",stateId);
	}
	

	@SuppressWarnings("unchecked")
	public List<Long> getAllDistrictByStateIds(List<Long> stateIds) {	
		StringBuilder query = new StringBuilder();
		query.append("select model.districtId from District model where model.state.stateId in ( :stateIds)");		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameterList("stateIds", stateIds);
		return queryObject.list();
	}

	@SuppressWarnings("unchecked")
	public List getDistrictIdByStateIdAndDistrictName(Long stateID,
			String districtName) {
		Object[] params = {stateID, districtName};
		return getHibernateTemplate().find("select model.districtId from District model where model.state.stateId = ? and model.districtName = ?", params);
	}
}
