package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITownshipDAO;
import com.itgrids.partyanalyst.model.Township;

public class TownshipDAO extends GenericDaoHibernate<Township, Long> implements ITownshipDAO
{
	

	public TownshipDAO() {
		super(Township.class);
	}
	
/*	@SuppressWarnings("unchecked")
	public List<Township> findByProperty(TownshipColumnNames propertyName, Object value){

		return getHibernateTemplate().find("from Township where " + propertyName.getValue() + "=?", value);
	}

	public List<Township> findByTownshipName(Object townshipName){
		return findByProperty(TownshipColumnNames.TOWNSHIP_NAME, townshipName);
	}

	public List<Township> findByTownshipCode(Object townshipCode){
		return findByProperty(TownshipColumnNames.TOWNSHIP_CODE, townshipCode);
	}

	public List<Township> findByTownshipType(Object townshipType){
		return findByProperty(TownshipColumnNames.TOWNSHIP_TYPE, townshipType);
	}
	
	@SuppressWarnings("unchecked")
	public List<Township> findByTehsilID(Long mandalID){
		return getHibernateTemplate().find("from Township model where model.tehsil.tehsilId=? order by model.townshipName", mandalID);
		
	}
	@SuppressWarnings("unchecked")
	public List<Panchayat> getPanchayathies(Long mandalID)
	{
		return getHibernateTemplate().find("from Panchayat model where model.tehsil.tehsilId=? order by model.panchayatName",mandalID);
	}
	@SuppressWarnings("unchecked")
	public List<Township> findByTownshipNameAndTehsilId(String townshipName,Long tehsilId){
		Object[] params = {townshipName,tehsilId};
		return getHibernateTemplate().find("from Township model where model.townshipName=? and model.tehsil.tehsilId=?",params);
	}
	
	public List getStateToRevenueVillageByRV(String rvIDs){
		return getHibernateTemplate().find("select model.tehsil.district.state.stateId, model.tehsil.district.state.stateName, " +
				"model.tehsil.district.districtId, model.tehsil.district.districtName, model.tehsil.tehsilId, " +
				"model.tehsil.tehsilName, model.townshipId, model.townshipName " +
				" from Township model where model.townshipId in("+rvIDs+") ");
	}

	@SuppressWarnings("unchecked")
	public List<Township> findByTownshipNameTehsilNameDistrictId(Long districtId,
			String mandalName, String revenueVillage) {
		Object[] params = {districtId, mandalName, revenueVillage};
		return getHibernateTemplate().find("from Township model where " +
				"model.tehsil.district.districtId = ? and model.tehsil.tehsilName = ? and model.townshipName = ?", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Township> findTownsByTownNameAndTypeAndDistrict(String townName,String townType,Long districtId){
		
		Object[] params = {townName,townType,districtId};
		return getHibernateTemplate().find("from Township model where model.townshipName = ? and model.townshipType = ? and model.tehsil.district.districtId = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Township> findTownsByTownNameAndTypeAndDistrict(String townName,String townType,Long districtId,String mandal){
		
		Object[] params = {townName,townType,districtId,mandal};
		return getHibernateTemplate().find("from Township model where model.townshipName = ? and model.townshipType = ? and model.tehsil.district.districtId = ? and "+
				"model.tehsil.tehsilName = ?",params);
	}

	@SuppressWarnings("unchecked")
	public List findTownshipIdByTownshipNameAndTehsilId(String townshipName,
			Long tehsilId) {
		Object[] params = {townshipName,tehsilId};
		return getHibernateTemplate().find("select model.townshipId from Township model where model.townshipName = ? and model.tehsil.tehsilId = ?",params);
	}
	public List<String> getTehsilIdsByTownShip(List<Long> townShipIds)
	{
		Query query = getSession().createQuery("select distinct cast(model.tehsil.tehsilId,string) from Township model" +
				" where model.townshipId in (:townShipIds)");
		query.setParameterList("townShipIds", townShipIds);
		return query.list();
	}*/
	@SuppressWarnings("unchecked")
	public List findTownshipsByTehsilId(Long tehsilId) {
		
		return getHibernateTemplate().find("select model.townshipId,model.townshipName from Township model where model.tehsil.tehsilId = ?",tehsilId);
	}
	
	
}
