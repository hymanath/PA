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
	
	public Object getDistrictNameById(Long districtId)
	{
		Query query = getSession().createQuery("select model.districtName from District model where model.districtId = ?");
		query.setParameter(0,districtId);
		return query.uniqueResult();
	}
	
public List<Object[]> getAllDistrictDetails(Long stateId){
		
		Query query = getSession().createQuery("select model.districtId , model.districtName from District model where model.state.stateId=?");
		query.setParameter(0,stateId );
		return query.list();
		
		
	}

public List<Object[]> getAllDistrictInfoDetails(){
	
	Query query = getSession().createQuery("select model.districtId , model.districtName from District model");
	return query.list();
	
	
}
/**
 * This method is used for getting district details, only nominated districts 
 * based on state,party and electionIds.
 * @author srishailam
 * @param Long stateId
 * @param Long partyId
 * @param List<Long> electionIds
 * @return List<Object[]>
 * @date 20th April,2013
 */
@SuppressWarnings("unchecked")
public List<Object[]> findByPartyNominationDetails(Long stateId,Long partyId,List electionIds){
	StringBuilder query = new StringBuilder();
	query.append("select model.districtId , model.districtName from District model where model.state.stateId = :stateId ");
	query.append(" and model.districtId in( select distinct model1.district.districtId from PartyElectionDistrictResult model1 ");
	query.append(" where model1.party.partyId = :partyId and model1.election.electionId in (:electionIds) ) order by model.districtName");
	Query queryObject = getSession().createQuery(query.toString());
	queryObject.setParameter("stateId", stateId);
	queryObject.setParameter("partyId", partyId);
	queryObject.setParameterList("electionIds", electionIds);
	return queryObject.list();
}

@SuppressWarnings("unchecked")
public List<Object[]> getDistrictList()
{
	return getHibernateTemplate().find("select model.districtId,model.districtName,model.state.stateId from District model ");
}

@SuppressWarnings("unchecked")
public List<Object[]> getDistrictIdAndNameByConstituency(Long constituencyId){
	return getHibernateTemplate().find("select model.district.districtId,model.district.districtName from Constituency model where model.constituencyId = ?",constituencyId);
}


@SuppressWarnings("unchecked")
public List getDistrictIdAndNameByStateForRegion(Long stateId,String region){
	StringBuilder str = new StringBuilder();
	str.append("select model.districtId,model.districtName from District model where model.state.stateId = :stateId ");
	if(region.equalsIgnoreCase("Telangana"))
	str.append("and model.districtId between 1 and 10 order by model.districtName");
	else
	{
		str.append("and model.districtId between 11 and 23 order by model.districtName");	
	}
	Query query = getSession().createQuery(str.toString());
	query.setParameter("stateId", stateId);
	return query.list();
	
	
}

public List<Object[]> getDistrictDetailsByDistrictIds(List<Long> districtIds)
{
	Query query = getSession().createQuery("select D.districtId,D.districtName from District D where " +
			"D.districtId in(:districtIds)");
	query.setParameterList("districtIds", districtIds);
	return query.list();
}

}
