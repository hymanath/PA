package com.itgrids.partyanalyst.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.itgrids.partyanalyst.dao.columns.enums.ConstituencyColumnNames;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;

public class ConstituencyDAO extends GenericDaoHibernate<Constituency, Long>
		implements IConstituencyDAO {

	public ConstituencyDAO() {
		super(Constituency.class);
	}

	@SuppressWarnings("unchecked")
	public List<Constituency> findByProperty(String propertyName, Object value) {
		return getHibernateTemplate().find("from Constituency where " + propertyName + "=?", value);
	}

	public List<Constituency> findByName(Object name) {

		return findByProperty(ConstituencyColumnNames.NAME.getValue(), name);
	}

	public List<Constituency> findByCountryId(Object countryId) {

		return findByProperty(ConstituencyColumnNames.COUNTRY_ID.getValue(), countryId);
	}

	public List<Constituency> findByState(String property, State state) {
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Constituency> findByStateId(Long stateId) {
		return getHibernateTemplate().find("from Constituency model where model.state.stateId = ? order by model.name",stateId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Constituency> findByConstituencyNamePattern(String constituencyName){
		return getHibernateTemplate().find("from Constituency model where model.name like ?","%"+constituencyName+"%");
	}
	
	@SuppressWarnings("unchecked")
	public List<Constituency> findByConstituencyId(Long constituencyId){
		return getHibernateTemplate().find("from Constituency model where model.constituencyId = ?",constituencyId);
	}
	
	@SuppressWarnings("unchecked")
    public List<Constituency> findConstitueniesByPartyAndElectionType(final Long partyId, final Long electionType, final String electionYear){
           
            return ( List<Constituency> ) getHibernateTemplate().execute( new HibernateCallback() {
                public Object doInHibernate( Session session ) throws HibernateException, SQLException {
                		List<Constituency> constElectionResults = session.createCriteria(Constituency.class)
                							.createAlias("constituencyElection", "constElec")
                							.createAlias("party", "p")
                							.createAlias("constElec.election", "elec")
                							.createAlias("elec.electionScope", "scope")
                							.createAlias("scope.electionType", "type")
                							.add(Expression.eq("type.electionTypeId", electionType))
                							.add(Expression.eq("elec.electionYear", electionYear))
                							.add(Expression.eq("p.partyId", partyId))
                							.list();
                		 return constElectionResults;
                }
            });
    }
	
	@SuppressWarnings("unchecked")
	public List<Constituency> findByElectionScope(Long scopeID){
		return getHibernateTemplate().find("from Constituency model where model.electionScope.electionScopeId = ? " +
				" order by model.name",scopeID);
	}

	@SuppressWarnings("unchecked")
	public List<Constituency> getConstituenciesByDistrictID (Long districtID)
	{
		return getHibernateTemplate().find("from Constituency model where model.district.districtId = ?  order by model.name",districtID);
	}
	
	@SuppressWarnings("unchecked")
	public List getStateDistrictConstituency(Long constituencyID){  
		return getHibernateTemplate().find("select model.state.stateId, model.state.stateName, model.district.districtId, model.district.districtName, model.name  from Constituency model where model.constituencyId = ?",constituencyID);
	}
	
	@SuppressWarnings("unchecked")
	public List getStateForParliamentConstituency(Long constituencyID){  
		return getHibernateTemplate().find("select model.state.stateId, model.state.stateName from Constituency model where model.constituencyId = ?",constituencyID);
	}
	
	@SuppressWarnings("unchecked")
	public List<Constituency> findByConstituencyNameAndDistrictId(String constituencyName,Long districtId){
		Object[] params = {constituencyName,districtId};
		return getHibernateTemplate().find("from Constituency model where model.name = ? and model.district.districtId = ?", params);
	}

	@SuppressWarnings("unchecked")
	public List findConstituencyByDistrictElectionType(Long districtId, String electionType) {
		Object[] params = {districtId, electionType};
		return getHibernateTemplate().find("select model.constituencyId, upper(model.name), " +
				"YEAR(model.startDate),YEAR(model.deformDate) from Constituency model where " +
				"model.district.districtId = ? and model.electionScope.electionType.electionType=?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Constituency> findByConstituencyNameDistrictIdTehsilName(String constituencyName, Long districtID, String tehsilName, Long electionScopeID){
		Object[] params = {constituencyName.toUpperCase(),districtID,tehsilName.toUpperCase(),  electionScopeID};
		return getHibernateTemplate().find("from Constituency model where upper(model.name)=? and model.district.districtId = ? " +
				"and upper(model.tehsil.tehsilName)=? and model.electionScope.electionScopeId=?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getDistrictIdByConstituencyId(Long constituencyId) {
		return getHibernateTemplate().find("select model.district.districtId from Constituency model where" +
				" model.constituencyId = ?",constituencyId);
	}

	public List<Long> getStateIdByConstituencyId(Long constituencyId){
		return getHibernateTemplate().find("select model.state.stateId from Constituency model where" +
				" model.constituencyId = ?",constituencyId);
	}
	
	public List<Constituency> findByElectionScopeState(Long scopeID, Long stateID){
		Object[] params = {scopeID, stateID};
		return getHibernateTemplate().find("from Constituency model where model.electionScope.electionScopeId = ? and model.state.stateId=?  order by model.name",params);
	}
	
	public List getAllConstituencyNamesAndIds(){
		return getHibernateTemplate().find("select model.constituencyId, model.name from Constituency model");
	}

	
	@SuppressWarnings("unchecked")
	public List basicElecDetailsForAConstituency(Long constituencyId){
		return getHibernateTemplate().find("select distinct model.electionScope.electionType.electionType,model.name,model.state.stateName from Constituency model where model.constituencyId = ?",constituencyId);
	}

	
	public List getConstituencyTypeAndDelimitationInfoByConstituencyId(Long constituencyId)
	{
		return getHibernateTemplate().find("select model.electionScope.electionType.electionType , model.deformDate " +
				"from Constituency model where model.constituencyId = ?",constituencyId);
	}
	

}