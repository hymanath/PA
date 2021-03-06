package com.itgrids.partyanalyst.dao.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.itgrids.partyanalyst.dao.IConstituencyDAO;
import com.itgrids.partyanalyst.dao.columns.enums.ConstituencyColumnNames;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.utils.IConstants;

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
	public List<Constituency> findByStateIdForUser(Long stateId) {
		return getHibernateTemplate().find("from Constituency model where model.state.stateId = ? and model.deformDate is null order by model.name",stateId);
	}

	@SuppressWarnings("unchecked")
	public List<Constituency> findByDistrictIdForUser(Long districtId) {
		return getHibernateTemplate().find("from Constituency model where model.district.districtId = ? and model.deformDate is null order by model.name",districtId);
	}

	@SuppressWarnings("unchecked")
	public List<Constituency> findByStateId(Long stateId) {
		return getHibernateTemplate().find("from Constituency model where model.state.stateId = ? order by model.name",stateId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Constituency> findByConstituencyNamePattern(String constituencyType, String constituencyName,Long stateId){
		String cName = "%"+constituencyName+"%";
		Query queryObject = getSession().createQuery("from Constituency model where model.electionScope.electionType.electionType = ? and model.name like ? and model.state.stateId = ?");
		queryObject.setString(0, constituencyType);
		queryObject.setString(1, cName);
		queryObject.setLong(2, stateId);
		queryObject.setMaxResults(IConstants.MAX_SEARCH_RESULTS_DISPLAY.intValue());
		return queryObject.list();
		//Object[] params = {constituencyType, cName};
		//return getHibernateTemplate().find("from Constituency model where model.electionScope.electionType.electionType = ? and model.name like ?", params);
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
	public List<Constituency> findByConstituencyNameAndDistrictId(String constituencyName,Long districtId,String electionType){
		Object[] params = {constituencyName,districtId,electionType};
		return getHibernateTemplate().find("from Constituency model where model.name = ? and model.district.districtId = ? and model.electionScope.electionType.electionType = ?", params);
	}

	@SuppressWarnings("unchecked")
	public List findConstituencyByDistrictElectionType(Long districtId, String electionType) {
		Object[] params = {districtId, electionType};
		return getHibernateTemplate().find("select model.constituencyId, upper(model.name), " +
				"YEAR(model.startDate),YEAR(model.deformDate) from Constituency model where " +
				"model.district.districtId = ? and model.electionScope.electionType.electionType=? order by model.name",params);
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

	
	@SuppressWarnings("unchecked")
	public List getConstituencyTypeAndDelimitationInfoByConstituencyId(Long constituencyId)
	{
		return getHibernateTemplate().find("select model.electionScope.electionType.electionType , model.deformDate " +
				"from Constituency model where model.constituencyId = ?",constituencyId);
	}
	
	@SuppressWarnings("unchecked")
	public List getConstituenciesByElectionTypeAndStateId(Long electionTypeId , Long stateID)
	{
		if(stateID != 0){
			Object[] params = {electionTypeId, stateID};
		return getHibernateTemplate().find("select model.constituencyId , model.name from Constituency model" +
				" where model.electionScope.electionType.electionTypeId = ?" +
				" and model.state.stateId=? and model.deformDate is null order by model.name",params);
		}
		else{
			Object[] params = {electionTypeId};
			return getHibernateTemplate().find("select model.constituencyId , model.name from Constituency model" +
					" where model.electionScope.electionType.electionTypeId = ? " +
					" and model.state.stateId in (1,36) and model.deformDate is  null order by model.name",params);
			
		}
	}
	
	@SuppressWarnings("unchecked")
	public List getLatestConstituenciesByStateId(String electionType , Long stateID)
	{
		Object[] params = {electionType, stateID};
		return getHibernateTemplate().find("select model.constituencyId , model.name from Constituency model" +
				" where model.electionScope.electionType.electionType = ?" +
				" and model.state.stateId=? and model.deformDate is null order by model.name",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getConstituencyInfoByConstituencyIdElectionYearAndElectionType(Long constituencyId) {
		Object[] params = {constituencyId};	
		return getHibernateTemplate().find("select distinct model.constituencyId," +
				" model.name,model.district.districtId," +
				" model.district.districtName,model.state.stateId," +
				" model.state.stateName" +			
				" from Constituency model where model.constituencyId  = ? ",params);
			
	}
	
	@SuppressWarnings("unchecked")
	public List getConstituencyInfoByConstituencyIdElectionYearAndElectionTypeForMuncipalAndCorporationElection(Long constituencyId) {
		Object[] params = {constituencyId};	
		return getHibernateTemplate().find("select distinct model.constituencyId," +
				" model.name,model.localElectionBody.district.districtId," +
				" model.localElectionBody.district.districtName," +
				" model.localElectionBody.district.state.stateId," +
				" model.localElectionBody.district.state.stateName" +			
				" from Constituency model where model.constituencyId  = ? ",params);
			
	}
	
	@SuppressWarnings("unchecked")
	public List getParliamentConstituencyInfoByConstituencyIdElectionYearAndElectionType(Long constituencyId) {
		Object[] params = {constituencyId};	
		return getHibernateTemplate().find("select distinct model.constituencyId," +
				" model.name,model.state.stateId," +
				" model.state.stateName" +			
				" from Constituency model where model.constituencyId  = ? ",params);
			
	}
	
	public List getAllConstituenciesForAState(Long stateId,Long electionType,Long electionYear){
		Object[] params = {stateId,electionType,electionYear};	
		return getHibernateTemplate().find("select model.constituencyId,model.name " +
				"from Constituency model where model.state.stateId = ? and model.electionScope.electionType.electionType = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List findConstituencyByDistrictAndStateIds(Long districtId,Long stateId,String electionType) {
		Object[] params = {districtId, stateId, electionType};
		return getHibernateTemplate().find("select model.constituencyId, upper(model.name), " +
				"YEAR(model.startDate),YEAR(model.deformDate) from Constituency model where " +
				"model.district.districtId = ? and model.state.stateId = ? and model.electionScope.electionType.electionType=?",params);
	}

	@SuppressWarnings("unchecked")
	public List<Constituency> findByLocalElectionBodyAndElectionScope(
			Long localElectionBodyId, String wardNo) {
		Object[] params = {localElectionBodyId, wardNo};
		return getHibernateTemplate().find("from Constituency model where model.localElectionBody.localElectionBodyId = ? and " +
				"model.name = ?", params);
	}

	@SuppressWarnings("unchecked")
	public List<Constituency> findWardsAndIdsInMuncipality(Long localElectionBodyId) {
		return getHibernateTemplate().find("from Constituency model where model.localElectionBody.localElectionBodyId = ?", localElectionBodyId);
	}
	
	public List findWardsInLocalElectionBodies(String localElectionBodyIds) {
		return getHibernateTemplate().find("select model.constituencyId, model.name from Constituency model where model.localElectionBody.localElectionBodyId in (" + localElectionBodyIds + ")");
	}

	@SuppressWarnings("unchecked")
	public List findConstituenciesForBiElectionInADistrict(String query) {
		return getHibernateTemplate().find(query);
	}

	@SuppressWarnings("unchecked")
	public List findConstituencyIdByTehsil(Long tehsilId){
	  return getHibernateTemplate().find("select model.constituencyId,model.name from Constituency model "+
				"where model.tehsil.tehsilId = ? and model.election",tehsilId);
	}

	@SuppressWarnings("unchecked")
	public List<Constituency> getAllParliamentConstituenciesInCountry(Long electionScopeId, Long countryId) {
		Object []params={electionScopeId, countryId};
		return getHibernateTemplate().find("from Constituency model where model.electionScope.electionScopeId = ? and model.deformDate is null and model.state.country.countryId = ? order by model.name",params);
	}

	@SuppressWarnings("unchecked")
	public List<Constituency> findByConstituencyNameElectionScopeAndDistrictId(
			String constituencyName, Long districtId, Long electionScopeId) {
		Object []params={electionScopeId, districtId, constituencyName};
		return getHibernateTemplate().find("from Constituency model where model.electionScope.electionScopeId = ? and " +
				"model.district.districtId = ? and model.name = ?",params);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Long> getAllWardsByLocalElectionBodyIds(List<Long> localElectionBodyIds) {	
		StringBuilder query = new StringBuilder();
		query.append("select model.constituencyId from Constituency model where model.localElectionBody.localElectionBodyId in ( :localElectionBodyIds)");		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameterList("localElectionBodyIds", localElectionBodyIds);
		return queryObject.list();
	} 
	
	@SuppressWarnings("unchecked")
	public List<Constituency> getAllWardsObjsByLocalElectionBodyWardIds(List<Long> localElectionBodyWardIds) {	
		StringBuilder query = new StringBuilder();
		query.append("select model from Constituency model where model.constituencyId in ( :localElectionBodyIds)");		
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameterList("localElectionBodyIds", localElectionBodyWardIds);
		return queryObject.list();
	} 
	
	@SuppressWarnings("unchecked")
	public List<Long> getAllConstituencysByDistrictIds(List<Long> districtIds, String electionType) {	
		StringBuilder query = new StringBuilder();
		query.append(" select model.constituencyId from Constituency model where model.electionScope.electionType.electionType=? and model.district.districtId in ( :districtIds)");
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameter(0,electionType);
		queryObject.setParameterList("districtIds", districtIds);		
		return queryObject.list();
	}

	public List getConstituenciesBySearchString(Long electionTypeId,
			Long stateId, String searchString) {
		
		String cName = ""+searchString+"%";
		Query queryObject = getSession().createQuery("select model.constituencyId , model.name from Constituency model" +
				" where model.electionScope.electionType.electionTypeId = ? and model.state.stateId=? and model.name like ? order by model.name");
		queryObject.setLong(0,electionTypeId);
		queryObject.setLong(1,stateId);
		queryObject.setString(2, cName);
		queryObject.setMaxResults(IConstants.MAX_PROBLEMS_DISPLAY.intValue());
		return queryObject.list();
	}

	public List getStateToConstituencyByConstituency(String constituencyIds) {
		return getHibernateTemplate().find("select model.state.stateId, model.state.stateName, " +
				"model.district.districtId, model.district.districtName, " +
				"model.constituencyId, model.name " +
				"from Constituency  model where model.constituencyId in("+constituencyIds+") ");
		
	}

	public List getStateToWardByWard(String constituencyIds) {
		return getHibernateTemplate().find("select model.localElectionBody.district.state.stateId, model.localElectionBody.district.state.stateName, " +
				"model.localElectionBody.district.districtId, model.localElectionBody.district.districtName, " +
				"model.localElectionBody.localElectionBodyId, model.localElectionBody.name, " +
				"model.constituencyId, model.name " +
				"from Constituency  model where model.constituencyId in("+constituencyIds+") ");
	}
	
	@SuppressWarnings("unchecked")
	public List<Constituency> findConstituenciesByNameScopeAndStateId(String constituencyName, Long electionScopeId, Long stateId){
		Object[] params = {constituencyName, electionScopeId, stateId};
		return getHibernateTemplate().find("from Constituency  model where model.name = ? and model.electionScope.electionScopeId = ? and " +
				"model.state.stateId = ?", params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Constituency> findConstituenciesByNameScopeAndCountryId(String constituencyName, Long electionScopeId, Long countryId){
		Object[] params = {constituencyName, electionScopeId, countryId};
		return getHibernateTemplate().find("from Constituency  model where model.name = ? and model.electionScope.electionScopeId = ? and " +
				"model.state.country.countryId = ?", params);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findByConstituencyNamesForAssembly(String searchText,String stateStr,String sortoption,String order,Integer startIndex,Integer maxResult)
	{
	   StringBuffer queryBuffer=new StringBuffer("select model.constituencyId,model.name,");
	   queryBuffer.append("model.electionScope.electionType.electionType,model.state.stateName, ");
	   queryBuffer.append("model.deformDate,model.district.districtName,model.district.districtId from Constituency model where ");
	   queryBuffer.append("model.name like '"+searchText+"%' and model.electionScope.electionType.electionTypeId = 2 "+stateStr+" ");
	   queryBuffer.append("order by "+sortoption+" "+order);
	   Query queryobject=getSession().createQuery(queryBuffer.toString());
	   
	   queryobject.setFirstResult(startIndex);
	   queryobject.setMaxResults(maxResult);
	   return queryobject.list();
	   
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findByConstituencyNamesForPalriament(String searchText,String stateStr,String sortoption,String order,Integer startIndex,Integer maxResult)
	{
	   StringBuffer queryBuffer=new StringBuffer("select model.constituencyId,model.name,");
	   queryBuffer.append("model.electionScope.electionType.electionType,");
	   queryBuffer.append("model.state.stateName,model.deformDate from Constituency model where ");
	   queryBuffer.append("model.name like '"+searchText+"%' and model.electionScope.electionType.electionTypeId = 1 "+stateStr+" ");
	   queryBuffer.append("order by "+sortoption+" "+order);
	   Query queryobject=getSession().createQuery(queryBuffer.toString());
	   
	   queryobject.setFirstResult(startIndex);
	   queryobject.setMaxResults(maxResult);
	   return queryobject.list();
	   
	}
	
	
	@SuppressWarnings("unchecked")
	public Object totalSearchCount(String searchText,Long electionTypeId,String state){
		
		StringBuffer queryBuffer = new StringBuffer("select count(model.constituencyId) from Constituency model where ");
		queryBuffer.append("model.name like '"+searchText+"%'and model.electionScope.electionType.electionTypeId = "+electionTypeId +" "+state);
		Query queryObject = getSession().createQuery(queryBuffer.toString());
		
		return queryObject.uniqueResult();	
}

	@SuppressWarnings("unchecked")
	public List findByConstituencyIdConstituencyNameAndDistrictId(
			String constituencyName, Long districtId,String electionType) {
		Object[] params = {constituencyName,districtId,electionType};
		return getHibernateTemplate().find("select model.constituencyId from Constituency model where model.name = ? and model.district.districtId = ? and "+
				"model.electionScope.electionType.electionType = ?",params);
	}

	@SuppressWarnings("unchecked")
	public List getWardIdByWardNameAndLocalBodyId(String wardName,
			Long localBodyId) {
		Object[] params = {wardName,localBodyId};
		return getHibernateTemplate().find("select model.constituencyId from Constituency model where model.name = ? and model.localElectionBody.localElectionBodyId = ?",params);
	}
	
	public List getAssConstituenciesByElectionTypeAndStateIdAndDistrictAccess(Long electionTypeId , Long stateId,Long userId)
	{
		Object[] params = {electionTypeId, stateId,userId};
		return getHibernateTemplate().find("select model.constituencyId , model.name from Constituency model" +
				" where model.electionScope.electionType.electionTypeId = ?" +
				" and model.state.stateId= ?  and model.district.districtId not in(select model1.district.districtId from UserDistrictAccessInfo model1 where model1.user.userId = ? )" +
				"order by model.name ",params);
	}
	
	public List<Constituency> getAllParliamentConstituenciesInCountryByStateAccessAndCountryAccess(Long electionScopeId, Long countryId,Long userId) {
		Object []params={electionScopeId, countryId,userId,userId};
		return getHibernateTemplate().find("from Constituency model where model.electionScope.electionScopeId = ? and model.deformDate is null and model.state.country.countryId = ? "+
				" and model.state.country.countryId not in(select model1.country.countryId from UserCountryAccessInfo model1 where model1.user.userId = ?)" +
				" and model.state.stateId not in (select model2.state.stateId from UserStateAccessInfo model2 where model2.user.userId = ? )"+
				" order by model.name",params);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getConstituencyInfoByConstituencyIdList(List<Long> constituenciesList)
	{
		Query query = getSession().createQuery("select model.constituencyId, model.name from Constituency model where model.constituencyId in(:constituenciesList)");
		query.setParameterList("constituenciesList",constituenciesList);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getConstituencyInfoWithStartDateByConstituencyIdList(List<Long> constituenciesList)
	{
		Query query = getSession().createQuery("select model.constituencyId,model.name,model.startDate from Constituency model where model.constituencyId in(:constituenciesList)");
		query.setParameterList("constituenciesList",constituenciesList);
		return query.list();
	}
	
	public void flushAndclearSession(){
		getSession().flush();
		getSession().clear();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getConstituenciesByDistrictId(Long districtId)
	{
		return getHibernateTemplate().find("select model.constituencyId , model.name from Constituency model where model.district.districtId=? order by model.name",districtId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Constituency> getAllParliamentConstituenciesInAState(Long electionScopeId, Long StateId) {
		Object []params={electionScopeId, StateId};
		return getHibernateTemplate().find("from Constituency model where model.electionScope.electionScopeId = ? and model.deformDate is null and model.state.stateId= ? order by model.name",params);
	}
	
	
	public List<Constituency> getPresentAssemblyConstituencyDetailsByDistrictId(Long stateId,List<Long> districtIds){
		
		
		Query queryObject = getSession()
				.createQuery(
						"select model from Constituency model where  model.district.districtId in(:districtIds) and model.state.stateId = :stateId and model.deformDate is null ");
		
		queryObject.setParameterList("districtIds", districtIds);
		queryObject.setParameter("stateId", stateId);
		
		return queryObject.list();
		
	}
	
	public List<Constituency> findByConstituencyNameAndDistrictIdElectionType(String constituencyName,Long districtId,Long electionType){
		Object[] params = {constituencyName,districtId,electionType};
		return getHibernateTemplate().find("from Constituency model where model.name = ? and model.district.districtId = ? and model.electionScope.electionType.electionTypeId = ?", params);
	}
	
	public List<Constituency> findConstituenciesByDistrictId(Long districtId) {
		return getHibernateTemplate().find("from Constituency model where model.district.districtId = ? and model.deformDate is null and model.electionScope.electionType.electionTypeId = 2 order by model.name",districtId);
	}
	
	public List<Object[]> getConstituencyName(Long constituencyId)
	{
		return getHibernateTemplate().find("select model.name,model.electionScope.electionType.electionType from Constituency model where model.constituencyId =? ",constituencyId);
	}
	public List<Object[]> getWardsInMuncipality(Long assemblyElectionBodyId)
	{
		Query queryObject = getSession().createQuery("select  a.constituency.constituencyId,a.constituency.name  " +
				"from  AssemblyLocalElectionBodyWard a join a.assemblyLocalElectionBody model " +
				"where  model.assemblyLocalElectionBodyId = :id ").setParameter("id", assemblyElectionBodyId);
		
		//queryObject.setParameterList("districtIds", );
		//.setParameter("stateId", );
		
		return queryObject.list();
		}
	public List getNameByInfluenceScopeValue(Long locationValue,String type)
	{
		StringBuilder query = new StringBuilder();
		if(type.equalsIgnoreCase("CONSTITUENCY"))
		query.append("select model.name from Constituency model where model.constituencyId=:locationValue");
		if(type.equalsIgnoreCase("MANDAL"))
		query.append("select model.tehsil.tehsilName from Constituency model where model.tehsil.tehsilId=:locationValue");
		if(type.equalsIgnoreCase("MUNCIPALITY/CORPORATION")||type.equalsIgnoreCase("MUNICIPAL-CORP-GMC"))
		query.append("select model.localElectionBody.name from Constituency model where model.localElectionBody.localElectionBodyId=:locationValue");
		if(type.equalsIgnoreCase("WARD"))
			query.append("select model.localElectionBodyWard.wardName from Constituency model where model.localElectionBodyWard.constituency.constituencyId=:locationValue");
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameter("locationValue", locationValue);
		return queryObject.list();	
	}

	public String getLocalBodyElectionTypeByConstituencyId(
			Long constituencyId) {
		String query = "select model.areaType from Constituency model where model.constituencyId = :constituencyId";
		Query queryBuilder = getSession().createQuery(query);
		queryBuilder.setParameter("constituencyId", constituencyId);
		return (String) queryBuilder.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getConstituencyNameByConstituencyIdsList(List<Long> constituencyIdsList)
	{
		Query query = getSession().createQuery(" select model.constituencyId, model.name from Constituency model where model.constituencyId in (:constituencyIdsList) order by model.name asc ");
		query.setParameterList("constituencyIdsList", constituencyIdsList);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getDistrictsByConstituencyIds(List<Long> constituencyIdsList)
	{
	 
		Query query = getSession().createQuery(" select distinct model.district.districtId,model.district.districtName from Constituency model " +
				" where model.constituencyId in (:constituencyIdsList) order by model.district.districtName ");
		
		query.setParameterList("constituencyIdsList", constituencyIdsList);
	    return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getStateByConstituencyId(Long constituencyId){  
		return getHibernateTemplate().find("select model.state.stateId, model.state.stateName  from Constituency model where model.constituencyId = ?",constituencyId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getDistrictByConstituencyId(List<Long> constituencyIdsList)
	{
	  Query query = getSession().createQuery(" select distinct model.district.districtId,model.district.districtName from Constituency model " +
	  		" where model.constituencyId in (:constituencyIdsList) ");
	  
	  query.setParameterList("constituencyIdsList", constituencyIdsList);
	  return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getConstituencyByConstituencyIdsList(List<Long> constituencyIdsList,Long districtId)
	{
	  Query query = getSession().createQuery(" select distinct model.constituencyId,model.name from Constituency model " +
	  		" where model.constituencyId in (:constituencyIdsList) and model.district.districtId =:districtId order by model.name ");
	  
	  query.setParameterList("constituencyIdsList", constituencyIdsList);
	  query.setParameter("districtId", districtId);
	  return query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getStateDistrictConstituencyIds(Long constituencyID){  
		return getHibernateTemplate().find("select model.state.stateId, model.district.districtId  from Constituency model where model.constituencyId = ?",constituencyID);
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getConstituencyIdByTehsilId(Long mandalId)
	{
		Query query = getSession().createQuery(" select distinct model.constituencyId from Constituency model where model.tehsil.tehsilId =:mandalId ");
		query.setParameter("mandalId", mandalId);
		return query.list();
	}
	
	public List<Object[]> getConstituencyConstituencyId(Long constituencyId)
	{
		Query query = getSession().createQuery("select model.constituencyId, model.name,model.state.stateId,model.state.stateName from Constituency model where model.constituencyId = :constituencyId");
		query.setParameter("constituencyId",constituencyId);
		return query.list();
	}
	
	public List<Object[]> getStateAndDistrictDetails(Long constituencyId)
	{
		Query query = getSession().createQuery("select model.state.stateId, model.district.districtId from Constituency model where model.constituencyId = :constituencyId");
		query.setParameter("constituencyId",constituencyId);
		return query.list();
	}
	
	public List<Long> getPcConstituency(Long constituencyId)
	{
		Query query = getSession().createQuery("select distinct model.state.stateId from Constituency model where model.constituencyId = :constituencyId");
		query.setParameter("constituencyId",constituencyId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List findConstituencyByDistrictElectionTypeAndYear(Long districtId, String electionType,String electionYear) {
		Object[] params = {districtId,electionYear,electionType};
		return getHibernateTemplate().find("select distinct model.constituency.constituencyId, upper(model.constituency.name), " +
				"YEAR(model.constituency.startDate),YEAR(model.constituency.deformDate) from ConstituencyElection model where " +
				"model.constituency.district.districtId = ? and model.election.electionYear=? and model.constituency.electionScope.electionType.electionType=? order by model.constituency.name",params);
	
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getConstituencyes(List<Long> districtIds)
	{
		Query query = getSession().createQuery("select distinct model.constituencyId, model.name " +
				" from Constituency model where model.district.districtId in (:districtIds) " +
				"and model.electionScope.electionScopeId = 2 and model.deformDate is null");
		query.setParameterList("districtIds", districtIds);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getPCConstituencyes(List<Long> constiIds)
	{
		Query query = getSession().createQuery("select distinct model.constituencyId, model.name " +
				" from Constituency model where model.constituencyId in (:constiIds) " +
				"and model.electionScope.electionScopeId = 1 and model.deformDate is null");
		query.setParameterList("constiIds", constiIds);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllParlimentConstituencys()
	{
		return getHibernateTemplate().find("select distinct model.constituencyId, model.name " +
				" from Constituency model where  " +
				" model.electionScope.electionScopeId = 1 and model.deformDate is null " +
				" and model.state.stateId in (1,36)");
	}	
	
	public List<Object[]> getParliamentConstisByStateIds(List<Long> stateIds){
		Query query = getSession().createQuery("select distinct model.constituencyId, model.name " +
				" from Constituency model where  " +
				" model.electionScope.electionScopeId = 1 and model.deformDate is null " +
				" and model.state.stateId in(:stateIds) order by model.state.stateId,model.name");
		
		query.setParameterList("stateIds", stateIds);
		return query.list();
	}
	
	public List<Object[]> getAssemblyConstisByStateIds(List<Long> stateIds){
		Query query = getSession().createQuery("select distinct model.constituencyId, model.name " +
				" from Constituency model where  " +
				" model.electionScope.electionScopeId = 2 and model.deformDate is null " +
				" and model.state.stateId in(:stateIds) order by model.state.stateId,model.name");
		
		query.setParameterList("stateIds", stateIds);
		return query.list();
	}
	
	public List<Object[]> getConstituenciesByConstituencyIds(Set<Long> constituenciesList)
	{
		Query query = getSession().createQuery("select model.constituencyId, model.name from Constituency model where model.constituencyId in(:constituenciesList)");
		query.setParameterList("constituenciesList",constituenciesList);
		return query.list();
	}
}