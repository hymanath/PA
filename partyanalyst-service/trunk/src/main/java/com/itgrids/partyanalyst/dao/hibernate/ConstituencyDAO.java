package com.itgrids.partyanalyst.dao.hibernate;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Hibernate;
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
		return getHibernateTemplate().find("from Constituency model where model.state.stateId = ? and model.electionScope.electionType.electionTypeId = 2 and model.deformDate is null order by model.name",stateId);
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
		Object[] params = {electionTypeId, stateID};
		return getHibernateTemplate().find("select model.constituencyId , model.name from Constituency model" +
				" where model.electionScope.electionType.electionTypeId = ?" +
				" and model.state.stateId=? order by model.name",params);
	}
	
	@SuppressWarnings("unchecked")
	public List getConstituenciesByElectionTypeAndStateIdForMPTC(Long electionTypeId , Long stateID)
	{
		Object[] params = {electionTypeId, stateID};
		return getHibernateTemplate().find("select model.constituencyId , concat(model.name, ' (' ,model.tehsil.tehsilName, ')')  from Constituency model" +
				" where model.electionScope.electionType.electionTypeId = ?" +
				" and model.state.stateId=? order by model.name",params);
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
		Query query = getSession().createQuery("select model.constituencyId, model.name,model.district.districtId  from Constituency model where model.constituencyId in(:constituenciesList)");
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
		return getHibernateTemplate().find("select distinct model.constituencyId , model.name from Constituency model where model.district.districtId=? and model.electionScope.electionType.electionTypeId = 2 and model.deformDate IS NULL" +
				" order by model.name",districtId);
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
		return getHibernateTemplate().find("from Constituency model where model.name = ? and model.district.districtId = ? and model.electionScope.electionType.electionTypeId = ?  order by model.name asc ", params);
	}
	
	public List<Constituency> findConstituenciesByDistrictId(Long districtId) {
		return getHibernateTemplate().find("from Constituency model where model.district.districtId = ? and model.deformDate is null and model.electionScope.electionType.electionTypeId = 2 order by model.name asc ",districtId);
	}
	
	public List<Object[]> getConstituencyName(Long constituencyId)
	{
		return getHibernateTemplate().find("select model.name,model.electionScope.electionType.electionType from Constituency model where model.constituencyId =?  order by model.name asc ",constituencyId);
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
		if(type.equalsIgnoreCase("customWard"))
			query.append("select model.name from Constituency model where model.constituencyId=:locationValue");
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
	
	public List<Object[]> getStateToConstituencyByParlConstituency(String constituencyIds) {
		return getHibernateTemplate().find("select model.state.stateId, model.state.stateName, " +
				"model.constituencyId, model.name " +
				"from Constituency  model where model.constituencyId in("+constituencyIds+") ");
		
	}
	
	public List<Object[]> getWardsInMuncipalityFomConstituency(Long localBodyid)
	{
		
		Query query = getSession().createQuery("select model.constituencyId , model.name  from Constituency model" +
				" where model.localElectionBody.localElectionBodyId = ?");
		
		query.setParameter(0, localBodyid);
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getConstituencyType(Long constituencyId)
	{
		return getHibernateTemplate().find("select model.constituencyId,model.areaType from Constituency model " +
				" where model.constituencyId = ?",constituencyId);
				
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getConstituencyNameByConstituencyIdsList(List<Long> constituencyIdsList)
	{
		Query query = getSession().createQuery("select model.constituencyId,model.name,model.areaType from Constituency model " +
				" where model.constituencyId in (:constituencyIdsList) order by model.name ");
		query.setParameterList("constituencyIdsList", constituencyIdsList);
		return query.list();
				
	}
	
	public String getConstituencyNameByConstituencyId(Long constituencyId)
	{
		Query query = getSession().createQuery(" select model.name from Constituency model where model.constituencyId =:constituencyId ");
		query.setParameter("constituencyId", constituencyId);
		return (String) query.uniqueResult();
	}
	
	public List<Object[]> getWardsInALocalBody(Long localBodyId)
	{
		Query query = getSession().createQuery("select model.constituencyId,model.name from Constituency model " +
				" where model.localElectionBody.localElectionBodyId = :localBodyId");
		query.setParameter("localBodyId", localBodyId);
		
		return query.list();
	}
	
	@SuppressWarnings({ "unchecked", "unchecked" })
	public List<Object[]> getStateAndDistricts(Long constituencyId)
	{
		return getHibernateTemplate().find("select distinct model.district.districtId,model.state.stateId from Constituency model" +
				" where model.constituencyId = ?",constituencyId);
	}
	
	public List<Object[]> getConstituencyTypeByConstituencyList(List<Long> constituencyIds)
	{
		Query query = getSession().createQuery("select model.constituencyId,model.areaType from Constituency model " +
				" where model.constituencyId in (:constituencyIds)");
		query.setParameterList("constituencyIds", constituencyIds);
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getStateForSelectedConstituency(List<Long> constituencyIds)
	{
		Query query = getSession().createQuery("select distinct model.state.stateId,model.state.stateName from Constituency model" +
				" where model.constituencyId in (:constituencyIds)");
		query.setParameterList("constituencyIds", constituencyIds);
		return query.list();
		
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getDistrictForSelectedConstituency(List<Long> constituencyIds,Long stateId)
	{
		Query query = getSession().createQuery("select  distinct model.district.districtId,model.district.districtName from Constituency model" +
				" where model.constituencyId in (:constituencyIds) and model.state.stateId = :stateId order by model.district.districtName ");
		query.setParameterList("constituencyIds", constituencyIds);
		query.setParameter("stateId", stateId);
		return query.list();
		
	}
	
	public List<Object[]> getConstituencysForSelDistrict(List<Long> constituencyIds,Long districtId)
	{
		Query query = getSession().createQuery("select  distinct model.constituencyId,model.name from Constituency model" +
				" where model.constituencyId in (:constituencyIds) and model.district.districtId = :districtId");
		query.setParameterList("constituencyIds", constituencyIds);
		query.setParameter("districtId", districtId);
		return query.list();
		
	}
	
	public String getConstituencyNameByConstituencyIdInWards(Long constituencyId){
		String query = "select model1.name from Constituency model,LocalElectionBody model1 where model.localElectionBody.localElectionBodyId = model1.localElectionBodyId and model.constituencyId=:constituencyId";
		Query queryBuilder = getSession().createQuery(query);
		queryBuilder.setParameter("constituencyId", constituencyId);
		return (String) queryBuilder.uniqueResult();
	}
	/**
	 * This DAO is used to get all constituencys List From Constituences By ConstituencyIds
	 * @param List<Long> constituencyIds
	 * @return List<Object[]>
	 * @date 19-07-2013
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getConstityencyByConstituencyids(List<Long> constituencyIds)
	{
		Query query = getSession().createQuery("select distinct model.constituencyId,model.name from Constituency model" +
				" where model.constituencyId in (:constituencyIds) order by model.name ");
		
		query.setParameterList("constituencyIds", constituencyIds);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Constituency> getConstituencyDetails()
	{
		Query query = getSession().createQuery(" select model from Constituency model where model.electionScope.electionScopeId = 2 and model.deformDate is null");
		return query.list();
				
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getConstituencyByStateId(Long stateId)
	{
		Query query = getSession().createQuery(" select model.constituencyId,model.name,model.electionScope.electionType.electionType from Constituency model " +
				" where model.state.stateId =:stateId and model.deformDate is null and model.electionScope.electionType.electionTypeId in(1,2) order by model.name ");
		
		query.setParameter("stateId", stateId);
		return query.list();
	}
	
	public List<Object[]> constituencyName(Long constituencyId)
	{
		Query query = getSession().createQuery("select distinct model.name,model.district.districtName from Constituency model where model.constituencyId = :constituencyId");
		query.setParameter("constituencyId", constituencyId);
		return query.list();
		
	}	
	public String getConstituencyAreaType(Long constituencyId){
		Query query = getSession().createQuery(" select model.areaType from Constituency model where model.constituencyId = :constituencyId");
		query.setParameter("constituencyId", constituencyId);
		return (String)query.uniqueResult();
	}
	
	public List<Object[]> getRuralAndRuralUrbanConstiencies(List<Long> constituencyIds)
	{
		Query query = getSession().createQuery(" select model.constituencyId,model.name from Constituency model " +
				" where model.constituencyId in (:constituencyIds) and model.areaType != null and  model.areaType != 'URBAN'");
		
		query.setParameterList("constituencyIds", constituencyIds);
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getParliamentConstituencies() {
	
		return getHibernateTemplate().find("select distinct model.constituencyId,model.name" +
				" from Constituency model where model.state.stateId = 1 and model.electionScope.electionType.electionTypeId = 1 order by model.name");
			
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getDistrictConstituencies(Long districtId) {
	
		return getHibernateTemplate().find("select distinct model.constituencyId,model.name" +
				" from Constituency model where model.district.districtId =? and model.electionScope.electionType.electionTypeId = 2 and model.deformDate is null order by model.name",districtId);
			
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAreaTypesOfAConstituencyByElectionScope(Long electionScopeId)
	{
		Query query = getSession().createQuery("Select model.constituencyId,model.areaType from Constituency model where model.electionScope.electionScopeId = :electionScopeId and deformDate is null");
		query.setParameter("electionScopeId",electionScopeId);
		return query.list();
	}	
	public List<Object[]> getConstis(){
		Query query = getSession().createQuery(" select model.constituencyId,model.name from Constituency model " +
				" where  model.areaType != 'URBAN' and model.district.districtId > 10 and model.state.stateId = 1 and model.deformDate is null ");
		return query.list();
	}
	
	public List<Object[]> getAllMucipalAssembyConstiListInState(Long stateId){
		Query query = getSession().createQuery(" select model.constituencyId,model.name from Constituency model " +
				" where   model.state.stateId =:stateId and model.electionScope.electionScopeId = 2 and model.areaType != 'URBAN' and  model.deformDate is null " +
				"order by model.name asc");
		query.setParameter("stateId", stateId);
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getDistrictConstituenciesList(List<Long> districtIds) {	
		if(districtIds != null && districtIds.size()>0){
			Query query = getSession().createQuery("select distinct model.constituencyId,model.name" +
					" from Constituency model where model.district.districtId in (:districtIds) and model.electionScope.electionType.electionTypeId = 2 and model.deformDate is null order by model.name");
			query.setParameterList("districtIds", districtIds);
			return query.list();
		}
		return null;
			
	}
	
   public List<Object[]> getRuralAndRurlaUrbanConstis(Set<Long> assemblyIds) {	
		
		Query query = getSession().createQuery("select distinct model.constituencyId,model.name " +
				" from Constituency model where model.areaType != 'URBAN' and model.constituencyId in (:assemblyIds) and model.electionScope.electionType.electionTypeId = 2 and model.deformDate is null order by model.name");
		query.setParameterList("assemblyIds", assemblyIds);
		return query.list();
			
	}
   
   @SuppressWarnings("unchecked")
	public List<Object[]> getConstituencyByState(Long stateId)
	{
		Query query = getSession().createQuery(" select model.constituencyId,model.name,model.electionScope.electionType.electionType from Constituency model " +
				" where model.state.stateId =:stateId and model.deformDate is null and model.electionScope.electionType.electionTypeId =2 order by model.name ");
		
		query.setParameter("stateId", stateId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List getLatestConstituenciesByStateIdForregion(String electionType , Long stateID,String region,Long districtId,Long tehsilId)
	{
		StringBuilder str = new StringBuilder();
		boolean isDistrictApplied=false;
		if(electionType != null && (electionType.equalsIgnoreCase("MPTC") ||  electionType.equalsIgnoreCase("ZPTC")))
		{
			str.append("select distinct model.constituencyId , model.name,model.district.districtName,model.district.districtId from Constituency model" +
					" where model.electionScope.electionType.electionType in ('ZPTC','MPTC')" +
					" and model.state.stateId = :stateID and model.deformDate is null  ");
		}
		else if(electionType != null && (electionType.equalsIgnoreCase("MLC")))
		{
			str.append("select distinct model.district.districtId , model.district.districtName,model.district.districtName,model.district.districtId from Constituency model" +
					" where model.state.stateId = :stateID and model.deformDate is null  ");
		}
		else
		{
			str.append("select distinct model.constituencyId , model.name,model.district.districtName,model.district.districtId from Constituency model" +
					" where model.electionScope.electionType.electionType  in (:electionType)" +
					" and model.state.stateId = :stateID and model.deformDate is null  ");
		}
		if(districtId != null && districtId.longValue()>0L)
		{
			isDistrictApplied =true;
			str.append("and model.district.districtId=:districtId");
		}
		else if(region.equalsIgnoreCase("Telangana")){
			str.append("and model.district.districtId between 1 and 10 order by model.name");
		}
		else if(region.equalsIgnoreCase("ALL"))
		{
			str.append("and model.district.districtId between 1 and 23 order by model.name");
		}
		else
		{
			str.append("and model.district.districtId between 11 and 23 order by model.name");	
		}
		Query query = getSession().createQuery(str.toString());
		query.setParameter("stateID", stateID);
		if(electionType != null && (!electionType.equalsIgnoreCase("MPTC") &&  !electionType.equalsIgnoreCase("ZPTC") && !electionType.equalsIgnoreCase("MLC")))
		{
			query.setParameter("electionType", electionType);
		}
		if(districtId != null && districtId.longValue()>0L && isDistrictApplied)
		{
			query.setParameter("districtId", districtId);
		}
		return query.list();
		
	}
	
	public List<Object[]> getConstituencyDetaisByRegionid(List<Long> regionIds)
	{
		Query query = getSession().createQuery("select C.constituencyId,C.name from Constituency C,StateRegionDistrict SRD " +
				"where " +
				"C.district.districtId = SRD.district.districtId and " +
				"SRD.stateRegion.stateRegionId in(:regionIds) and C.electionScope.electionScopeId =2 and C.deformDate is null order by C.name");
		
		query.setParameterList("regionIds", regionIds);
		
		return query.list();
		
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getConstituencyIdsByDistrictId(Long districtId,Long electionTypeId)
	{
		Query query = getSession().createQuery("select model.constituencyId from Constituency model where model.district.districtId = :districtId and " +
				" model.electionScope.electionType.electionTypeId = :electionTypeId");
		query.setParameter("districtId",districtId);
		query.setParameter("electionTypeId",electionTypeId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getConstituncyIdsByDistrictId(List<Long> districtsIds,Long electionTypeId)
	{
		Query query = getSession().createQuery("select model.constituencyId from Constituency model where model.district.districtId in (:districtId) and " +
				" model.electionScope.electionType.electionTypeId = :electionTypeId");
		query.setParameter("districtsIds",districtsIds);
		query.setParameter("electionTypeId",electionTypeId);
		return query.list();
	}
	
	public List<Object[]> getAllAssemblyConstituenciesByStateId(Long stateId)
	{
		Query query = getSession().createQuery("select  C.constituencyId , C.name , C.district.districtId from Constituency C where C.state.stateId = ? and " +
				"C.electionScope.electionType.electionTypeId = 2 and C.deformDate is null and C.district.districtId > 10 order by C.name ");
		
		query.setParameter(0, stateId);
		
		return query.list();
		
		
	}
	
	public List<Object[]> getDistictWiseConstituencyListByConstiIds(List<Long> constituencyIds){
		Query query = getSession().createQuery("select model.constituencyId , model.name , model.district.districtId, model.district.districtName from Constituency model where " +
				" model.constituencyId in (:constituencyIds) order by model.name ");
		query.setParameterList("constituencyIds",constituencyIds);
		return query.list();
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findWardsAndIdsInlocalElectionBody(Long localElectionBodyId) {
		return getHibernateTemplate().find(" select model.constituencyId , model.name from Constituency model where model.localElectionBody.localElectionBodyId = ?", localElectionBodyId);
	}
	
	public List<Object[]> getConstituencies(List<Long> districtIds) {
		Query query = getSession().createQuery("select distinct model.constituencyId , model.name from Constituency model where model.district.districtId in(:districtIds)  and model.deformDate is null and model.electionScope.electionType.electionTypeId = 2 order by model.name");
		query.setParameterList("districtIds",districtIds);
		return query.list();
		
	}
	
	public List<Object[]> getAllAssemblyConstituenciesByStateTypeId(Long stateTypeId,Long stateId,Long electionYear)
	{
		
		List<Object[]> result = null;
		if(electionYear == null)
		{
			if(stateTypeId.longValue() == 0L)
			{
				Query query = getSession().createQuery("select  distinct C.constituencyId , C.name from Constituency C where C.state.stateId = :stateId and " +
						"C.electionScope.electionType.electionTypeId = 2 and C.deformDate is null order by C.name asc");
				query.setParameter("stateId", stateId);
				result =  query.list();
			}		
			else if(stateTypeId.longValue() == 1L)
			{
				Query query = getSession().createQuery("select  distinct  C.constituencyId , C.name  from Constituency C where C.state.stateId = :stateId and " +
						"C.electionScope.electionType.electionTypeId = 2 and C.deformDate is null and C.district.districtId > 10 order by C.name asc");	
				query.setParameter("stateId", stateId);
				result =   query.list();
			}
			else if(stateTypeId.longValue() == 2L)
			{
				Query query = getSession().createQuery("select  distinct  C.constituencyId , C.name  from Constituency C where C.state.stateId = :stateId and " +
						"C.electionScope.electionType.electionTypeId = 2 and C.deformDate is null and C.district.districtId < 11 order by C.name asc");
				query.setParameter("stateId", stateId);
				result =   query.list();
			}
		}
		else
		{
			if(stateTypeId.longValue() == 0L)
			{
				Query query = getSession().createQuery("select  distinct C.constituencyId , C.name from Constituency C,ConstituencyElection CE where C.state.stateId = :stateId and " +
						"C.electionScope.electionType.electionTypeId = 2 and C.deformDate is null and CE.constituency.constituencyId =  C.constituencyId and CE.election.electionYear ="+electionYear+" order by C.name asc");
				query.setParameter("stateId", stateId);

				result =  query.list();
			}		
			else if(stateTypeId.longValue() == 1L)
			{
				Query query = getSession().createQuery("select  distinct  C.constituencyId , C.name  from Constituency C ,ConstituencyElection CE where C.state.stateId = :stateId and " +
						"C.electionScope.electionType.electionTypeId = 2 and C.deformDate is null and C.district.districtId > 10 and CE.constituency.constituencyId =  C.constituencyId and CE.election.electionYear = "+electionYear+" order by C.name asc");
				
				query.setParameter("stateId", stateId);
				
				result =   query.list();
			}
			else if(stateTypeId.longValue() == 2L)
			{
				Query query = getSession().createQuery("select  distinct  C.constituencyId , C.name  from Constituency C ,ConstituencyElection CE where C.state.stateId = :stateId and " +
						"C.electionScope.electionType.electionTypeId = 2 and C.deformDate is null and C.district.districtId < 11 and CE.constituency.constituencyId =  C.constituencyId and CE.election.electionYear = "+electionYear+" order by C.name asc");
				
				query.setParameter("stateId", stateId);

				result =   query.list();
			}
		}
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getParliamentConstituencyInfoByConstituencyIds(List<Long> constituencyIds) {

		Query query = getSession().createQuery(" select distinct model.constituencyId, model.name " +
					" from Constituency model where model.constituencyId  in (:constituencyIds) ");			
		
		query.setParameterList("constituencyIds", constituencyIds);
		
		return query.list();
		}
	

	@SuppressWarnings("unchecked")
	public List getConstituenciesForRegion(String region){
		StringBuilder str = new StringBuilder();
		str.append("select distinct model.constituencyId,model.name from Constituency model where ");
		if(region.equalsIgnoreCase("Telangana"))
		str.append("model.district.districtId between 1 and 10");
		else if(region.equalsIgnoreCase("Andhra Pradesh"))
		{
			str.append("model.district.districtId between 11 and 23");	
		}
		else
		{
			str.append("model.district.districtId between 1 and 23");	
		}
		str.append(" and model.deformDate is null and model.electionScope.electionType.electionTypeId = 2 order by model.name");
		Query query = getSession().createQuery(str.toString());
		return query.list();
	}
	
	public List<Object[]> getAssemblyConstituenciesInAP(String type){
		StringBuilder str = new StringBuilder();
		str.append("select model.constituencyId,model.name from Constituency model where model.electionScope.electionType.electionTypeId = 2 and model.deformDate is null and model.state.stateId = 1 ");
		if(!type.equalsIgnoreCase("ALL")){
			str.append(" and model.areaType !='URBAN' ");
		}
		str.append("order by model.name");
		Query query = getSession().createQuery(str.toString());
		return query.list();
	}
	
	public List<Long> getConstituenciesInADistrict(Long districtId) {
		
		return getHibernateTemplate().find("select distinct model.constituencyId from Constituency model where model.district.districtId =? and " +
				"  model.electionScope.electionType.electionTypeId = 2 and model.deformDate is null order by model.name",districtId);
			
	}
	
    public List<Long> getConstituenciesInAState(Long stateId) {
	      StringBuilder str = new StringBuilder();
            str.append("select distinct model.constituencyId from Constituency model where model.state.stateId =1 and " +
			"  model.electionScope.electionType.electionTypeId = 2 and model.deformDate is null   ");
			if(stateId.longValue() == 1){
				str.append(" and model.district.districtId > 10 ");
			}else{
				str.append(" and model.district.districtId < 11 ");
			}
			str.append(" order by model.name ");
			Query query = getSession().createQuery(str.toString());
			return query.list();
	}
    
	public List<Long> getAllAssemblyConstituencyIdsByStateId(Long stateId)
	{
		Query query = null;
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select  C.constituencyId from Constituency C where C.state.stateId = 1 and " +
					" C.electionScope.electionType.electionTypeId = 2 and C.deformDate is null ");
		
		if(stateId == 1L) // AP
		{
			queryStr.append(" and C.district.districtId > 10  ");
		}
		else if(stateId == 2L) //Telangana
		{
			queryStr.append(" and C.district.districtId < 11 ");
		}
		else //All
		{
			queryStr.append(" and C.district.districtId between 1 and 23 ");
		}
		queryStr.append("  order by C.name ");
		
		query = getSession().createQuery(queryStr.toString());
		return query.list();
	}
	
	public List<Object[]> getConstituencysByLsitDistrictIds(List<Long> districtIdList){
		Query query = getSession().createQuery("select distinct model.constituencyId, model.name from Constituency model where " +
				" model.district.districtId in (:districtIdList)  and model.electionScope.electionType.electionTypeId = 2 and model.deformDate is null order by   model.district.districtId asc ");
		
		query.setParameterList("districtIdList", districtIdList);
		return query.list();
	}
	
	
    @SuppressWarnings("unchecked")
	public List<Object[]> findConstituenciesByStateId(Long stateId) {
    	 StringBuilder str = new StringBuilder();
         str.append("select distinct model.constituencyId,model.name from Constituency model where model.state.stateId =1 and " +
			"  model.electionScope.electionType.electionTypeId = 2 and model.deformDate is null  ");
			if(stateId.longValue() == 1L){
				str.append(" and model.district.districtId > 10 ");
			}else if(stateId.longValue() == 2L ||stateId.longValue() == 36L ){
				str.append(" and model.district.districtId < 11 ");
			}
			Query query = getSession().createQuery(str.toString());
			return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<Long> getDistrictIdsByConstituency(List<Long> constituencyIds){
		Query query = getSession().createQuery("select distinct model.district.districtId from Constituency model where model.constituencyId in(:constituencyIds)");
		
		query.setParameterList("constituencyIds", constituencyIds);
		return query.list();
		
	}
	
	public List<Long> getDistrictIdByConstituencyIds(List<Long> constituencyIds){
		Query query = getSession().createQuery("select distinct model.district.districtId from Constituency model where" +
				" model.constituencyId in (:constituencyIds) ");
		
		query.setParameterList("constituencyIds", constituencyIds);
		return query.list();
	}
	
	
	public List<Object[]> getConstituencysByDistrictId(List<Long> districtIdList){
		StringBuilder str = new StringBuilder();
		str.append("select distinct model.constituencyId, model.name from Constituency model " );
		str.append(	" where  model.electionScope.electionType.electionTypeId = 2  and model.deformDate is null " );
		if(districtIdList != null && districtIdList.size()>0)
		str.append(" and model.district.districtId in (:districtIdList) " );	
		str.append("   order by   model.name asc ");
		Query query = getSession().createQuery(str.toString());
		if(districtIdList != null && districtIdList.size()>0)
		query.setParameterList("districtIdList", districtIdList);
		return query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getConstituencyInfoByNotConstituencyIdList(List<Long> constituenciesList,String searchType)
	{
		Query query = null ;
		if(searchType != null)
		{
			if(searchType.equalsIgnoreCase("assembly"))
			{
				if(constituenciesList != null && constituenciesList.size()>0)
				{
					 query = getSession().createQuery("select model.constituencyId, model.name,model.district.districtId, model.district.districtName  " +
					 		"from Constituency model where model.state.stateId = 1 and model.constituencyId in(:constituenciesList) " +
					 		"and model.electionScope.electionType.electionTypeId = 2 and model.deformDate is null order by model.name  ");
					query.setParameterList("constituenciesList",constituenciesList);
					
				}
				else
				{
					 query = getSession().createQuery("select model.constituencyId, model.name,model.district.districtId , model.district.districtName  " +
					 		"from Constituency model where model.state.stateId = 1 and model.district.districtId <=23 and model.electionScope.electionType.electionTypeId = 2 " +
					 		"and model.deformDate is null order by model.name");
				}
			}
			else if(searchType.equalsIgnoreCase("district"))
			{
				if(constituenciesList != null && constituenciesList.size()>0)
				{
					 query = getSession().createQuery("select distinct model.district.districtId, model.district.districtName  from Constituency model where model.state.stateId = 1 " +
					 		"and model.district.districtId in(:constituenciesList) and model.electionScope.electionType.electionTypeId = 2 and model.deformDate is null order by " +
					 		"model.name  ");
					query.setParameterList("constituenciesList",constituenciesList);
				}
				else
				{
					 query = getSession().createQuery("select distinct model.district.districtId , model.district.districtName  from Constituency model where model.state.stateId = 1 and" +
					 		" model.district.districtId <=23 and model.electionScope.electionType.electionTypeId = 2 and model.deformDate is null order by model.name");
				}
			}
			
			else if(searchType.equalsIgnoreCase("Parliament"))
			{	
				StringBuilder str = new StringBuilder();
				str.append("select distinct model.delimitationConstituency.constituency.constituencyId , model.delimitationConstituency.constituency.name from " +
						" DelimitationConstituencyAssemblyDetails model " +
						" where model.delimitationConstituency.constituency.electionScope.electionType.electionTypeId = 1 " +
						" and model.delimitationConstituency.constituency.state.stateId = :stateID and model.delimitationConstituency.constituency.deformDate is null and" +
						"  model.delimitationConstituency.year = 2009 ");
				
					str.append(" and model.constituency.district.districtId between 1 and 23 and model.delimitationConstituency.constituency.constituencyId  in (:constituenciesList) " +
							" order by model.delimitationConstituency.constituency.name ");	
					
				query = getSession().createQuery(str.toString());
				
				query.setParameter("stateID", 1L);
				query.setParameterList("constituenciesList",constituenciesList);
			}
		}
		
		
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getConstituencyIdsByStateIdForAElectionType(Long stateId,Long electionTypeId)
	{
		Query query = getSession().createQuery("select model.constituencyId from Constituency model where model.state.stateId = :stateId and " +
				" model.electionScope.electionType.electionTypeId = :electionTypeId and model.deformDate is null ");
		query.setParameter("stateId",stateId);
		query.setParameter("electionTypeId",electionTypeId);
		return query.list();
	}
	
	public List<Object[]> getWardsInLocalElectionBody(List<Long> localBodyIds){
		Query query = getSession().createQuery("select distinct model.constituencyId,model.name,concat(model.localElectionBody.name,' ',model.localElectionBody.electionType.electionType) from " +
				" Constituency model where model.localElectionBody.localElectionBodyId in(:localBodyIds) order by model.localElectionBody.name,model.constituencyId ");
		query.setParameterList("localBodyIds",localBodyIds);

		return query.list();
	}
	public List<Object[]> getWardIdAndName(Long localElectionBodyId){
		Query query=getSession().createQuery("select model.constituencyId,model.name from Constituency model where model.localElectionBody.localElectionBodyId=:localElectionBodyId order by model.localElectionBody.name,model.constituencyId ");
		
		query.setParameter("localElectionBodyId", localElectionBodyId);
		return query.list();
	}
	
	public List<Object[]> getConstituencyByStateAndAreaType(Long stateId,Long level){
		// STATE - 1, DISTRICT - 2, MANDAL - 5, PANCHAYAT - 7,  MUNCIPAL-CORP-GHMC - 6, WARD - 9, INCHARGE - 10, DIVISION - 11
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.constituencyId," +
				" model.name " +
				" from Constituency model " +
				" where model.state.stateId =:stateId " +
				" and model.deformDate is null " +
				" and model.electionScope.electionType.electionTypeId =2 ");
		if(level.equals(5l)||level.equals(7l)){
			sb.append( " and model.areaType !='URBAN'");
		}
		if(level.equals(6l)||level.equals(9l)){
			sb.append( " and model.areaType !='RURAL'");
		}
				
		sb.append(" order by model.name ");
				
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("stateId", stateId);
		return query.list();
	}
	
	public List<Object[]> getWardsNameInLocalElectionBodyByWardIds(List<Long> wardIds){
		Query query = getSession().createQuery("select distinct model.constituencyId,model.name,concat(model.localElectionBody.name,' ',model.localElectionBody.electionType.electionType) from " +
				" Constituency model where model.constituencyId  in(:wardIds) order by model.localElectionBody.name, model.constituencyId ");
		query.setParameterList("wardIds",wardIds);

		return query.list();
	}
	
	public List<Object[]> getConstituenciesByStateId(Long stateId,Long stateTypeId){
		
		
		StringBuilder str = new StringBuilder();
		str.append("select distinct model.constituencyId,model.name,model.district.districtId,model.district.districtName,model.state.stateId,model.state.stateName from Constituency model where " +
				" model.state.stateId = :stateId  and model.deformDate is null and model.electionScope.electionType.electionTypeId = 2 ");
		if(stateTypeId.longValue() == 0L)
		{
			str.append(" and model.district.districtId between 1 and 23 ");
		}
		else if(stateTypeId.longValue() == 1L)
		{
			str.append(" and model.district.districtId between 11 and 23 ");
		}
		else if(stateTypeId.longValue() == 2L)
		{
			str.append(" and model.district.districtId between 1 and 10 ");
		}
		str.append(" order by model.name "); 
		
		Query query = getSession().createQuery(str.toString());
		query.setParameter("stateId", stateId);
		return query.list();
		
		
				
	}
	
public List<Constituency> getConstituenciesByStteIdStatTypeId(Long stateId,Long stateTypeId){
		
		
		StringBuilder str = new StringBuilder();
		str.append("select distinct model from Constituency model where " +
				" model.state.stateId = :stateId  and model.deformDate is null and model.electionScope.electionType.electionTypeId = 2 ");
		if(stateTypeId.longValue() == 0L)
		{
			str.append(" and model.district.districtId between 1 and 23 ");
		}
		else if(stateTypeId.longValue() == 1L)
		{
			str.append(" and model.district.districtId between 11 and 23 ");
		}
		else if(stateTypeId.longValue() == 2L)
		{
			str.append(" and model.district.districtId between 1 and 10 ");
		}
		str.append(" order by model.name "); 
		
		Query query = getSession().createQuery(str.toString());
		query.setParameter("stateId", stateId);
		return query.list();
		
		
				
	}

	public List<Object[]> getAllConstituenciesInADistrict(Long districtId) {
		
		if(districtId != null && districtId.longValue()>0L){
			return getHibernateTemplate().find("select distinct model.constituencyId,model.name from Constituency model where model.district.districtId =? and " +
				"  model.electionScope.electionType.electionTypeId = 2 and model.deformDate is null order by model.name",districtId);
		}else if(districtId == null || districtId.longValue() == 0L){
			return getHibernateTemplate().find("select distinct model.constituencyId,model.name from Constituency model where " +
					"  model.electionScope.electionScopeId = 2 and model.deformDate is null and (model.district.districtId between 11 and 23 ) " +
					" order by model.name ");
		}
		return null;
			
	}
	
	public List<Object[]> getWardsAndLEBIdsInLocalElectionBody(List<Long> localBodyIds){
		Query query = getSession().createQuery("select distinct model.constituencyId," +
				" model.name," +
				" concat(model.localElectionBody.name,' ',model.localElectionBody.electionType.electionType)," +
				" model.localElectionBody.localElectionBodyId " +
				" from Constituency model " +
				" where model.localElectionBody.localElectionBodyId in(:localBodyIds) " +
				" order by model.localElectionBody.name,constituencyId ");
		query.setParameterList("localBodyIds",localBodyIds);

		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAssemblyConstituencyDetlsByDistrictIds(List<Long> districtIds) {	
		StringBuilder query = new StringBuilder();
		query.append(" select model.constituencyId, model.name,model.district.districtId,model.district.districtName,model.state.stateId,model.state.stateName from Constituency model " +
				" where model.electionScope.electionScopeId = 2" +
				" and model.deformDate is null " +
				" and model.district.districtId in ( :districtIds) " +
				" order by model.name" );
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameterList("districtIds", districtIds);		
		return queryObject.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getParliamentConstituencyByParliamentId(Long constituencyId) {
		Object[] params = {constituencyId};
		return getHibernateTemplate().find("select distinct model.constituencyId,model.name" +
				" from Constituency model where model.state.stateId = 1 " +
				" and model.electionScope.electionType.electionTypeId = 1 " +
				" and model.constituencyId = ?", params);
			
	}
public List<Object[]> getTheConstituenciesInADistrict(Long districtId) {
		
		Query query=getSession().createQuery("select distinct model.constituencyId,model.name,model.district.districtName from Constituency model where model.district.districtId =:districtId and " +
				"  model.electionScope.electionType.electionTypeId = 2 and model.deformDate is null order by model.name");
	query.setParameter("districtId", districtId);
	return query.list();
			
	}

public List getConstituenciesByElectionTypeAndStateIdForMPTC1(Long electionTypeId , Long stateId)
{
	
	 StringBuilder str = new StringBuilder();
	 
	  str.append("select model.constituencyId , concat(model.name, ' (' ,model.tehsil.tehsilName, ')')  from Constituency model" +
			" where model.electionScope.electionType.electionTypeId =:electionTypeId and model.deformDate is null");
	  if(stateId == 1)
		  str.append(" and model.state.stateId =:stateId and model.district.districtId between 11 and 23 order by model.name");
	  else if(stateId == 0)
		  str.append(" and model.state.stateId = 1 and model.district.districtId between 1 and 10 order by model.name");
	  else
		 
	  str.append(" and model.district.state.stateId =:stateId order by model.name");
	  Query query = getSession().createQuery(str.toString());
	  query.setParameter("electionTypeId", electionTypeId);
	  if(stateId != 0)
	  query.setParameter("stateId", stateId);
	return query.list();
	
}

@SuppressWarnings("unchecked")
public List getConstituenciesByElectionTypeAndStateId1(Long electionTypeId , Long stateId)
{
	
	
	 StringBuilder str = new StringBuilder();
	 
	  str.append("select model.constituencyId , model.name from Constituency model" +
			" where model.electionScope.electionType.electionTypeId = :electionTypeId and model.deformDate is null");
	  if(stateId == 1)
		  str.append(" and model.state.stateId =:stateId and model.district.districtId between 11 and 23 order by model.name");
	  else if(stateId == 0)
		  str.append(" and model.state.stateId = 1 and model.district.districtId between 1 and 10 order by model.name");
	  else
		  str.append(" and model.district.state.stateId =:stateId order by model.name");
	  Query query = getSession().createQuery(str.toString());
	  query.setParameter("electionTypeId", electionTypeId);
	  if(stateId != 0)
	  query.setParameter("stateId", stateId);
	return query.list();
}

	@SuppressWarnings("unchecked")
	public List<Object[]> getConstituencyListByDistrictIdsList(List<Long> districtIdsList)
	{
		Query query = getSession().createQuery("select distinct model.constituencyId , model.name from Constituency model where model.district.districtId in (:districtIdsList) and " +
				" model.electionScope.electionType.electionTypeId = 2 and model.deformDate IS NULL order by model.name ");
		query.setParameterList("districtIdsList", districtIdsList);
		return query.list();
	}

@SuppressWarnings("unchecked")
public List<Object[]> getDistrictConstituenciesByState(Long districtId,Long stateId) {
	StringBuilder str = new StringBuilder();
	str.append("select distinct model.constituencyId,model.name" +
			" from Constituency model where model.district.districtId =:districtId and model.electionScope.electionType.electionTypeId = 2 and model.deformDate is null ");
	if(stateId == 1)
	{
		str.append(" and model.district.districtId between 11 and 23");
	}
	else if(stateId == 2)
	{
		str.append(" and model.district.districtId between 1 and 10");
	}
	else
	{
		str.append(" and model.district.districtId between 1 and 23");
	}
	str.append(" order by model.name");
	Query query = getSession().createQuery(str.toString());
	query.setParameter("districtId", districtId);
	return query.list();

	
}

@SuppressWarnings("unchecked")
public List<Long> getDistrictIdByConstituencyIdandState(Long constituencyId,Long stateId) {
	Object[] params = {constituencyId,stateId};
	return getHibernateTemplate().find("select model.district.districtId from Constituency model where" +
			" model.constituencyId = ? and  model.state.stateId = ?",params);
}
public List<Long> getConstituenciesByState(Long stateId) {
    StringBuilder str = new StringBuilder();
      str.append("select distinct model.constituencyId from Constituency model where model.state.stateId =:stateId and " +
		"  model.electionScope.electionType.electionTypeId = 2 and model.deformDate is null   ");
      	str.append(" order by model.name ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("stateId", stateId);
		return query.list();
}

  public List<Long> getDistrictByConstituencyId(Long constituencyId){
	  Query query = getSession().createQuery("select model.district.districtId from Constituency model where model.constituencyId =:constituencyId");
		query.setParameter("constituencyId", constituencyId);
		return query.list();
  }
  
  public List<Long> getStateByConstituencyId(Long constituencyId){
	  Query query = getSession().createQuery("select model.state.stateId from Constituency model where model.constituencyId =:constituencyId");
		query.setParameter("constituencyId", constituencyId);
		return query.list();
  }
  
  @SuppressWarnings("unchecked")
	public List<Object[]> getConstituenciesDetaildByDistrictId(Long districtId)
	{
		return getHibernateTemplate().find("select model.constituencyId , model.name, model.district.districtName,model.district.districtId from Constituency model where model.district.districtId=? and model.electionScope.electionType.electionTypeId = 2 and model.deformDate IS NULL" +
				" order by model.name",districtId);
	}
	
	public String getConstituencyNameById(Long constituencyId){
		String name = "";
		try{
			 Query query = getSession().createQuery("select model.name from Constituency model where model.constituencyId =:constituencyId");
				query.setParameter("constituencyId", constituencyId);
				name =  (String)query.uniqueResult();
		}catch(Exception e){

		}
		return name;
	}
	
	public Object[] getlocalbodyName(Long constituencyId){
		Query queryBuilder = getSession().createSQLQuery("select LEB.name,ET.election_type,LEB.local_election_body_id,LEB.tehsil_id from constituency C left join local_election_body LEB on C.local_election_body_id = LEB.local_election_body_id " +
				" left join election_type ET on LEB.election_type_id = ET.election_type_id where C.constituency_Id = :constituencyId");
		queryBuilder.setParameter("constituencyId", constituencyId);
		return (Object[]) queryBuilder.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getAllWardsForState(Long stateId) {	
		StringBuilder query = new StringBuilder();
		query.append("select model.constituencyId,model.name from Constituency model where model.localElectionBody.localElectionBodyId is not null and name like '%ward%'  and model.deformDate is null ");		
		Query queryObject = getSession().createQuery(query.toString());
		return queryObject.list();
	} 
	
	public List<Object[]> getMPTCZPTCLocationAreaDetails(List<Long> constituencyIds,List<Long> latestdelimitedTehsilIds){
		Query query = getSession().createQuery("select model2.constituency.constituencyId , model2.constituency.name , model.district.districtId, model.district.districtName, model2.tehsil.tehsilId," +
				" model2.tehsil.tehsilName,model.constituencyId from Constituency model, Booth model2  where model.tehsil.tehsilId = model2.tehsil.tehsilId and model2.publicationDate.publicationDateId =:publicationDateId and " +
				" model.constituencyId in (:constituencyIds) and model2.tehsil.tehsilId in (:latestdelimitedTehsilIds) ");
		query.setParameterList("constituencyIds",constituencyIds);
		query.setParameterList("latestdelimitedTehsilIds",latestdelimitedTehsilIds);
		query.setParameter("publicationDateId", IConstants.VOTER_DATA_PUBLICATION_ID);
		return query.list();
		
	}
	
	public List<Object[]> getConstituenciesByStateAndDistrict(Long stateId, List<Long> districtIds){
		 StringBuilder str = new StringBuilder();
			 
			  str.append("select model.constituencyId , model.name, model.district.districtId from Constituency model" +
					" where model.electionScope.electionType.electionTypeId = :electionTypeId and model.deformDate is null");
			  
				  if(stateId.equals(36l)){
					  str.append(" and model.district.state.stateId = 1 and model.district.districtId between 1 and 10 ");
				  }else if(stateId.equals(1l)){
					  str.append(" and model.district.state.stateId = 1 and model.district.districtId between 11 and 23 ");
				  }else{
					  //str.append(" and model.district.state.stateId in(1,36) ");
					  str.append(" and model.district.districtId between 1 and 23 ");
				  }
				  
				  if(districtIds!=null && districtIds.size()>0){
					  str.append(" and model.district.districtId in(:districtIds) order by model.name");
				  }else{
					  str.append(" order by model.name ");
				  }
			 
			  Query query = getSession().createQuery(str.toString());
			  query.setParameter("electionTypeId", 2l);
			  if(districtIds!=null && districtIds.size()>0){
				  query.setParameterList("districtIds", districtIds);
			  }
			return query.list();
	}
	
	public List<Object[]> getConstituenciesByStateAndDistrictNew(Long stateId, Long districtId){
		 StringBuilder str = new StringBuilder();
			 
			  str.append("select model.constituencyId , model.name from Constituency model" +
					" where model.electionScope.electionType.electionTypeId = :electionTypeId and model.deformDate is null");
			  if(districtId.equals(0l)){
				  if(stateId.equals(36l)){
					  str.append(" and model.state.stateId =36 and model.district.districtId between 1 and 10 order by model.name");
				  }else if(stateId.equals(1l)){
					  str.append(" and model.state.stateId = 1 and model.district.districtId between 11 and 23 order by model.name");
				  }else{
					  str.append(" and model.district.state.stateId in(1,36) order by model.name");
				  }
			  }else{
				  str.append(" and model.district.districtId=:districtId order by model.district.districtName");
			  }
			 
			  Query query = getSession().createQuery(str.toString());
			  query.setParameter("electionTypeId", 2l);
			  if(districtId != 0){
				  query.setParameter("districtId", districtId);
			  }
			return query.list();
	}
	
	public List<Object[]> getStateAndDistricsOfConstituency(List<Long> constituencyIds){
		Query query = getSession().createQuery("select model.constituencyId,model.district.districtId,model.state.stateId" +
				" from Constituency model" +
				" where model.constituencyId in (:constituencyIds)");
		query.setParameterList("constituencyIds", constituencyIds);
		return query.list();
		
	}
	
	public List<Object[]> getWardDetailsById(List<Long> locations) {
		Query query = getSession().createQuery("select model.constituencyId," +
				" model.name," +
				" model.localElectionBody.name," +
				" model.localElectionBody.electionType.electionType" +
				" from Constituency model" +
				" where model.constituencyId in (:locations)");
		
		query.setParameterList("locations", locations);
		return query.list();
	}
	public Long getElectionScopeByConstituency(Long constituencyId){
		Query query = getSession().createQuery(" select model.electionScope.electionScopeId from Constituency model where model.constituencyId =:constituencyId ");
		
		query.setParameter("constituencyId", constituencyId);
		return (Long) query.uniqueResult();
	}
	
	public Long getConstituencyIdByDistrictIdAndConstituencyName(Long districtId,String constituencyName)
	{
		Query query = getSession().createQuery("Select model.constituencyId from Constituency model where model.electionScope.electionScopeId = 2 and deformDate is null and model.district.districtId = :districtId and model.name = :constituencyName");
		query.setParameter("districtId", districtId);
		query.setParameter("constituencyName", constituencyName);
		return (Long) query.uniqueResult();
	}
	
	public Long getWardIdByTownIdAndWardName(Long localElectionBodyId,String wardName)
	{
		Query query = getSession().createQuery("Select model.constituencyId from Constituency model where model.localElectionBody.localElectionBodyId = :localElectionBodyId and model.name = :wardName");
		query.setParameter("localElectionBodyId", localElectionBodyId);
		query.setParameter("wardName", wardName);
		return (Long) query.uniqueResult();
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getConstituencyDetailsByCintiId(Long constiId){	
		return getHibernateTemplate().find("select model.constituencyId, model.name from Constituency model " +
				"where model.constituencyId = ? ", constiId);
	}

	public List<Object[]> getConstituenciesByDistId(Long districtId) {
		StringBuilder str = new StringBuilder();
		str.append("select distinct model.constituencyId,model.name" +
				   " from Constituency model where model.district.districtId =:districtId and model.electionScope.electionType.electionTypeId = 2 and model.deformDate is null ");
		str.append(" order by model.name");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("districtId", districtId);
		return query.list();	
	}
	
	public List<Object[]> getConstituenciesForADistrict(Long distId,Long stateId){
		Query query = getSession().createQuery(" select distinct model.constituencyId,model.name " +
				"from Constituency model where model.district.districtId =:distId and model.electionScope.electionType.electionTypeId = 2 and model.deformDate is null " +
				" and model.state.stateId=:stateId order by model.name");
		query.setParameter("distId", distId);
		query.setParameter("stateId", stateId);
		return query.list();
	}

	public List<Long> getDivisionIdsOfGreater(List<Long> greaterIds){
		Query query=getSession().createSQLQuery("select distinct c.constituency_id from constituency c " +
				" where " +
				" c.local_election_body_id in (:localBodyIds) ")
				.addScalar("constituency_id",Hibernate.LONG);	
		query.setParameterList("localBodyIds", greaterIds);	
		return query.list();
	}
	public List<Long> getAllTehsilsByConstituency(List<Long> constituencyIds)
	{
		Query query = getSession().createSQLQuery("select distinct t.tehsil_id as tehsilId from " +
				" tehsil t,delimitation_constituency_mandal_details dcmd where t.tehsil_id = dcmd.tehsil_id and delimitation_constituency_id in " +
				" (select delimitation_constituency_id from delimitation_constituency where constituency_id in (:constituencyIds) and year = 2009)")
				.addScalar("tehsilId",Hibernate.LONG);
		query.setParameterList("constituencyIds", constituencyIds);
		return query.list();
	}
	
	public List<Long> getAllLocalBodiesForConstituency(List<Long> constituencyIds){
		StringBuilder str = new StringBuilder();
		
		str.append(" select distinct alcb.local_election_body_id " +
				" from assembly_local_election_body alcb,local_election_body leb " +
				" where " +
				" leb.local_election_body_id = alcb.local_election_body_id " +
				" and leb.election_type_id  in (:Town) ");
		
		 if(constituencyIds !=null && constituencyIds.size()>0){
			 str.append(" and alcb.constituency_id in (:constituencyIds) ");
		 }
		 
		 Query query = getSession().createSQLQuery(str.toString())				
				 .addScalar("local_election_body_id",Hibernate.LONG);
		 
		 query.setParameterList("constituencyIds", constituencyIds);
		 query.setParameterList("Town", IConstants.TOWN_TYPE_IDS);
		 
		 return query.list();
		
	}
	
	public List<Long> getAllDivisions(List<Long> locationIds){
		
		  StringBuilder str = new StringBuilder();
		  
		 //str.append(" select distinct constituency_id from constituency " +
		  		//"where local_election_body_id in (select local_election_body_id from local_election_body where election_type_id in (:Division) and district_id in (:locationIds) ");
		  
		  str.append(" select distinct c.constituency_id from constituency c,local_election_body leb"
		  		+ " where"
		  		+ " c.local_election_body_id = leb.local_election_body_id "
		  		+ " and leb.election_type_id in (:Division)  "
		  		+ " and  leb.district_id in (:locationIds)"
		  		+ " and  leb.local_election_body_id is not null " );

		  
		  Query query = getSession().createSQLQuery(str.toString())
		  .addScalar("constituency_id",Hibernate.LONG);
		  query.setParameterList("locationIds", locationIds);
		  query.setParameterList("Division", IConstants.DIVISION_TYPE_IDS);
		  
		  return query.list();
	}
	public List<Long> getAllDivisionsOfConstituency(List<Long> constituencyIds){
		
		 StringBuilder str = new StringBuilder();
		 
		 str.append(" select distinct c.constituency_id" +
		 		" from " +
		 		" assembly_local_election_body alcb,local_election_body leb,constituency c" +
		 		" where " +
		 		" leb.local_election_body_id = alcb.local_election_body_id" +
		 		" and alcb.local_election_body_id = c.local_election_body_id " +
		 		" and  leb.election_type_id  in (:Division) ");
		 
		 if(constituencyIds !=null && constituencyIds.size()>0){
			 str.append(" and alcb.constituency_id in (:constituencyIds) ");
		 }
		 
		 Query query = getSession().createSQLQuery(str.toString())				
		 .addScalar("constituency_id",Hibernate.LONG);
		 query.setParameterList("constituencyIds", constituencyIds);
		 query.setParameterList("Division", IConstants.DIVISION_TYPE_IDS);
		 
		 return query.list();				
	}
	public List<Long> getAllPanchayatsForDistrict(List<Long> districtIds){
		
		Query query=getSession().createSQLQuery(" select distinct p.panchayat_id from panchayat p,tehsil t " +
				" where p.tehsil_id =  t.tehsil_id " +
				" and t.district_id in (:districtIds) " )
				
				.addScalar("panchayat_id",Hibernate.LONG);	
			query.setParameterList("districtIds", districtIds);
			return query.list();
	}
	public List<Long> getAllPanchayatsForConstituency(List<Long> constituencyIds){
		
		Query query=getSession().createSQLQuery("select distinct p.panchayat_id  from tehsil t,delimitation_constituency_mandal_details dcmd,panchayat p " +
				" where t.tehsil_id = dcmd.tehsil_id " +
				" and p.tehsil_id = t.tehsil_id" +
				" and dcmd.delimitation_constituency_id in " +
				" (select delimitation_constituency_id from delimitation_constituency " +
				" where year = :delimitationYear and constituency_id in (:constituencyIds)) ")
		
				.addScalar("panchayat_id",Hibernate.LONG);	
			
			query.setParameterList("constituencyIds", constituencyIds);	
			query.setParameter("delimitationYear", IConstants.DELIMITATION_YEAR);
			return query.list();		
	}
	public List<Long> getAllPanchayatsByTehsilId(List<Long> tehsilId)
	{
		Query query = getSession().createSQLQuery("select distinct model.panchayat_id as id from panchayat model where model.tehsil_id in (:tehsilId) ")
		.addScalar("id",Hibernate.LONG);
		query.setParameterList("tehsilId", tehsilId);
		
		return query.list();
		
	}
	public List<Long> getAllWardsForDistrict(List<Long> districtIds){
		
		Query query=getSession().createSQLQuery(" select distinct c.constituency_id from constituency c,local_election_body l " +
				"  where " +
				" c.local_election_body_id = l.local_election_body_id" +
				" and l.district_id  in (:districtIds) " +
				" and c.local_election_body_id is not null ")
		
				.addScalar("constituency_id",Hibernate.LONG);	
		query.setParameterList("districtIds", districtIds);	
		
		return query.list();	
	}
	public List<Long> getAllWardsForConstituency(List<Long> constituencyIds,Long publicationDateId){
		
		Query query=getSession().createSQLQuery("select distinct c.constituency_id from constituency c,local_election_body l,booth b  " +
				" where " +
				" c.local_election_body_id = l.local_election_body_id " +
				" and l.local_election_body_id = b.local_election_body_id" +
				" and b.constituency_id in (:constituencyIds) and b.publication_date_id = :publicationDateId " +
				" and c.local_election_body_id is not null ")
				
				.addScalar("constituency_id",Hibernate.LONG);	
		query.setParameterList("constituencyIds", constituencyIds);	
		query.setParameter("publicationDateId", publicationDateId);
		return query.list();	
	}
	public List<Long> getAllWardIdsForLocalBody(List<Long> localElectionBodyId){
		Query query=getSession().createSQLQuery("select distinct C.constituency_id as id from constituency C,local_election_body LEB where C.local_election_body_id in (:localElectionBodyId) " +
				"  and C.local_election_body_id = LEB.local_election_body_id  ")
		.addScalar("id",Hibernate.LONG);		
		query.setParameterList("localElectionBodyId", localElectionBodyId);
		return query.list();
	}

	public List<Object[]> getTehsilsByConstituency(Long constituencyId)
	{
		Query query = getSession().createSQLQuery("select distinct t.tehsil_id as tehsilId ,t.tehsil_name as name from " +
				" tehsil t,delimitation_constituency_mandal_details dcmd where t.tehsil_id = dcmd.tehsil_id and delimitation_constituency_id =" +
				" (select delimitation_constituency_id from delimitation_constituency where constituency_id =:constituencyId and year = 2009) order by t.tehsil_name ")
				.addScalar("tehsilId",Hibernate.LONG)
				.addScalar("name",Hibernate.STRING);
		query.setParameter("constituencyId", constituencyId);
		return query.list();
	}
	
	public List<Object[]> getLocalElectionBodiesByconstituency(Long constituencyId)
	{
		Query query = getSession().createSQLQuery("select localBody.local_election_body_id as id,concat(localBody.name,' ',et.election_type) as name from assembly_local_election_body assemblyLocalBody,local_election_body  localBody," +
				"election_type et  " +
				" where assemblyLocalBody.local_election_body_id = localBody.local_election_body_id and  year = " +
				" (select max(year) from assembly_local_election_body) and et.election_type_id = localBody.election_type_id " +
				"and assemblyLocalBody.constituency_id = :constituencyId order by localBody.name ")
				.addScalar("id",Hibernate.LONG)
				.addScalar("name",Hibernate.STRING);
		query.setParameter("constituencyId", constituencyId);
		return query.list();
		
	}
	public List<Object[]> getPanchayatsByTehsilId(Long tehsilId)
	{
		Query query = getSession().createSQLQuery("select model.panchayat_id as id , model.panchayat_name as name from panchayat model where model.tehsil_id = :tehsilId and model.is_deleted = 'N' ")
		.addScalar("id",Hibernate.LONG)
		.addScalar("name",Hibernate.STRING);
		query.setParameter("tehsilId", tehsilId);
		
		return query.list();
		
	}

	
	/* Temporarily message sending query 
	 * @Autor 	: srishailam Pittala
	 * @mailId 	: srishailam.itgrids.hyd@gmail.com
	 * @Date 	: 20th April,2016
	 * @return 	: Object[]	
	 * @input 	: date
	 * */
	
	public List<Object[]> getTemaporarilyMobileNoByDate(Date date){
		Query query = getSession().createSQLQuery(" select DISTINCT C.constituency_id,TSD.designation,TSD.mobile_no,TSD.invited_count,TSD.attended_count,TSD.percentage," +
				" TSD.ramark from temp_sms_details TSD,constituency C where TSD.constituency_id = C.constituency_id and date(TSD.inserted_time) =:date ");
		query.setDate("date", date);
		return query.list();
	}
	public List<Object[]> getConstituenciesByStateId(Long stateId){
		
		
		StringBuilder str = new StringBuilder();
		str.append("select distinct model.constituencyId,model.name from Constituency model where " +
				" model.state.stateId = :stateId  and model.deformDate is null and model.electionScope.electionType.electionTypeId = 2 ");
		if(stateId.longValue() == 1L)
		{
			str.append(" and model.district.districtId between 11 and 23 ");
		}
		else if(stateId.longValue() == 36L)
		{
			str.append(" and model.district.districtId between 1 and 10 ");
		}
		str.append(" order by model.name "); 
		
		Query query = getSession().createQuery(str.toString());
		query.setParameter("stateId", stateId);
		return query.list();
		
		
				
	}
	

	@SuppressWarnings("unchecked")
	public List<Object[]> getConstituenciesByDistrictIds(List<Long> districtIds)
	{
		StringBuilder str =new StringBuilder();
		str.append("select model.constituencyId , model.name from Constituency model where model.electionScope.electionType.electionTypeId = 2 and model.deformDate IS NULL");
		if(districtIds != null && districtIds.size() > 0)
		str.append(" and model.district.districtId in(:districtIds) "); 
		str.append("order by model.name");
		Query query = getSession().createQuery(str.toString());
		if(districtIds != null && districtIds.size() > 0)
			query.setParameterList("districtIds", districtIds);
		return query.list();
	}
	
	public List<Object[]> getDefaultConstituencyAndDistrictForAState(Long stateId)
	{
		Query query = getSession().createQuery("SELECT model.constituencyId,model.district.districtId FROM Constituency model where model.state.stateId = :stateId and " +
				" model.deformDate is null and model.electionScope.electionType.electionTypeId = 2");
		query.setParameter("stateId",stateId);
		query.setMaxResults(1);
		return query.list();
	}
	
	public List<Object[]> getConstituencyIdAndNameByStateForRegion(Long stateId,String region){
		StringBuilder str = new StringBuilder();
		str.append(" select model.constituencyId,model.name " +
				" from Constituency model where model.state.stateId=:stateId and model.deformDate is null and model.electionScope.electionType.electionTypeId = 2 ");
		
		if(region.equalsIgnoreCase("Telangana")){
			str.append("and model.district.districtId between 1 and 10 order by model.name");
		}
		else if(region.equalsIgnoreCase("Andhra Pradesh")){
			str.append("and model.district.districtId between 11 and 23 order by model.name");	
		}
		else{
			str.append("and model.district.districtId between 1 and 23 order by model.name");	
		}
		
		Query query = getSession().createQuery(str.toString());
		query.setParameter("stateId", stateId);
		return query.list();
	}
	public List<Object[]> getDistrictByDistrictId(Long districtId){
		Query query = getSession().createQuery("SELECT model.district.districtId,model.district.districtName FROM Constituency model where model.district.districtId = :districtId and " +
				" model.deformDate is null and model.electionScope.electionType.electionTypeId = 2");
		query.setParameter("districtId",districtId);
		return query.list();	
	}
	
	public List<Object[]> getMPTCConstituenciesForMandal(Long tehsilId){
		Query query = getSession().createQuery("select model.constituencyId," +
												" model.name" +
												" from Constituency model" +
												" where model.electionScope.electionScopeId = 3" +
												" and model.tehsil.tehsilId = :tehsilId" +
												" order by model.name");
		query.setParameter("tehsilId", tehsilId);
		return query.list();
	}
	
	public List<Object[]> getZPTCConstituenciesForMandal(List<Long> tehsilIds){
		Query query = getSession().createQuery("select model.constituencyId," +
												" model.name" +
												" from Constituency model" +
												" where model.electionScope.electionScopeId = 4" +
												" and model.tehsil.tehsilId in (:tehsilIds)" +
												" order by model.name");
		query.setParameterList("tehsilIds", tehsilIds);
		return query.list();
	}
	public List<Object[]> getMandalsForDistrictWiseDetails(List<Long> districtId){
		Query query = getSession().createQuery("select distinct  T.tehsilId,T.tehsilName from Booth B left join B.tehsil T where " +
				" B.constituency.district.districtId between 11 and 23 and B.publicationDate.publicationDateId=11 and B.constituency.district.districtId=:districtId ");
		query.setParameterList("districtId", districtId);
		return query.list();
	}
	
	public List<Object[]> getVillagesForDistrictWiseDetails(List<Long> districtId){
		Query query = getSession().createSQLQuery(" select distinct  P.panchayat_id as panId,P.panchayat_name as name from constituency C,booth B " +
				" left outer join panchayat P on B.panchayat_id=P.panchayat_id " +
				" where B.constituency_id=C.constituency_id " +
				" and C.district_id between 11 and 23 and B.publication_date_id=11 and C.district_id=:districtId")
				.addScalar("panId",Hibernate.LONG)
				.addScalar("name",Hibernate.STRING);
		query.setParameterList("districtId", districtId);
		return query.list();
	}
	
	public List<Object[]> getConstituenctNamesByIds(List<Long> constituencyIds){
		Query query = getSession().createQuery(" select model.constituencyId,model.name,model.district.districtName,model.district.districtId from Constituency model where model.constituencyId in (:constituencyIds) ");
		query.setParameterList("constituencyIds",constituencyIds);
		return query.list();
	}
	public List<Object[]> getAllConstituencyList(Long stateId){
		Query query = getSession().createQuery(" select distinct model.constituencyId,model.name from Constituency model where model.state.stateId =:stateId ");
		query.setParameter("stateId",stateId);
		return query.list();
	}
	public List<Object[]> getStateWiseConstituency() {
		Query query = getSession()
				.createQuery("select distinct  model.constituencyId,model.name from Constituency model"
						+ " where model.state.stateId in(1,36) and model.deformDate is null and model.electionScope.electionType.electionTypeId = 2 order by model.name");
		
		return query.list();
	}
	public List<Object[]> getDistrictWiseConstituency(Long districtId) {
		Query query = getSession()
				.createQuery("select distinct  model.constituencyId,model.name, model.areaType from Constituency model"
						+ " where model.district.districtId = :districtId and model.deformDate is null and model.electionScope.electionType.electionTypeId = 2 order by model.name");
		query.setParameter("districtId",districtId);
		return query.list();
	}
	
	public List<Object[]> getStateWiseAssemblyConstituency(Long stateId){
		
		StringBuilder str = new StringBuilder();
		
		str.append("SELECT distinct  model.constituencyId,model.name FROM Constituency model " +
				" WHERE " +
				" model.deformDate is null and model.electionScope.electionScopeId = :electionScopeId  " );
		
		if(stateId !=null && stateId.longValue()>0l){
			
			if(stateId ==1l){
				str.append(" and model.district.districtId between 11 and 23 ");
			}else if(stateId == 36l){
				str.append(" and model.district.districtId between 1 and 10 ");
			}			
		}
		
		str.append(" order by model.name  ");
		
		Query query = getSession().createQuery(str.toString());
			
		query.setParameter("electionScopeId", 2l);
		
		return query.list();
		
	}
	
	
	public List<Object[]> getAllASsemblyContsAndItsDistricts(){
		
	   Query query = getSession().createQuery("" +
	   " select model.constituencyId,model.name,model.district.districtId,model.district.districtName " +
	   " from   Constituency model " +
	   " where  model.district.state.stateId = 1 and model.deformDate is null and model.electionScope.electionScopeId = 2 ");
	   return query.list();
	}
	
    public List<Long> getStateConstituencyIds(Long stateId){
		
		Query query = getSession().createQuery("select model.constituencyId  from Constituency model where " +
				" model.state.stateId = :stateId  and model.deformDate is null and model.electionScope.electionType.electionTypeId = 2 ");
		
		query.setParameter("stateId", stateId);
		return query.list();
	}
public List<Object[]> getDistrictBasedOnConstituenciesId(Set<Long> constitueciesIds){
		
		StringBuilder str = new StringBuilder();
		
		str.append(" SELECT distinct  model.district.districtId,model.district.districtName FROM Constituency model " +
				  " WHERE " +
				  " model.deformDate is null and model.electionScope.electionScopeId = :electionScopeId  " );
		
		 if(constitueciesIds != null && constitueciesIds.size() > 0){
			  str.append(" and model.constituencyId in (:constitueciesIds )");
		 }
		 Query query = getSession().createQuery(str.toString());
		query.setParameter("electionScopeId", 2l);
		if(constitueciesIds != null && constitueciesIds.size() > 0){
			query.setParameterList("constitueciesIds", constitueciesIds);
		}
		return query.list();
		
	}
  public List<Object[]> getDistAndConDtslByConstituenciesIds(List<Long> constitueciesIds){
	
	StringBuilder str = new StringBuilder();
	
	str.append(" SELECT distinct  model.district.districtId,model.district.districtName,model.constituencyId,model.name FROM Constituency model " +
			  " WHERE " +
			  " model.deformDate is null and model.electionScope.electionScopeId = :electionScopeId  " );
	
	 if(constitueciesIds != null && constitueciesIds.size() > 0){
		  str.append(" and model.constituencyId in (:constitueciesIds )");
	 }
	 Query query = getSession().createQuery(str.toString());
	query.setParameter("electionScopeId", 2l);
	if(constitueciesIds != null && constitueciesIds.size() > 0){
		query.setParameterList("constitueciesIds", constitueciesIds);
	}
	return query.list();
	
  }
  public List<Object[]> getConstituenciesList(List<Long> distIds){
	  StringBuilder str = new StringBuilder();
	  str.append("select model.district.districtId,model.district.districtName,model.constituencyId,model.name FROM Constituency model " +
	  			" where " +
	  			" model.deformDate is null and model.electionScope.electionScopeId = 2 and model.district.districtId in (:distIds)");
	  Query query = getSession().createQuery(str.toString());
	  query.setParameterList("distIds",distIds);
	  return query.list();
  }
  public Long getDistId(Long locationId){
	  StringBuilder str = new StringBuilder();
	  str.append(" select distinct model.district.districtId from Constituency model where model.constituencyId = :locationId " +
	  		" and model.deformDate is null and model.electionScope.electionScopeId = 2");
	  Query query = getSession().createQuery(str.toString());
	  query.setParameter("locationId", locationId);
	  return (Long)query.uniqueResult();
  }
  
  public List<Object[]> getConstituenciesByStateForStateTypeId(Long stateId,Long stateTypeId,Long districtId){
		StringBuilder str = new StringBuilder();
		str.append("select distinct model.constituencyId,model.name from Constituency model where model.state.stateId = :stateId" +
				" and model.deformDate is null and model.electionScope.electionScopeId = 2 ");
		if(stateTypeId.longValue() == 0L)
		{
			str.append(" and model.district.districtId between 1 and 23 ");
		}
		else if(stateTypeId.longValue() == 1L)
		{
			str.append(" and model.district.districtId between 11 and 23 ");
		}
		else if(stateTypeId.longValue() == 36L)
		{
			str.append(" and model.district.districtId between 1 and 10 ");
		}
		if(districtId != null && districtId.longValue() > 0l)
			str.append(" and model.district.districtId = :districtId");
		str.append(" order by model.name asc"); 
		
		Query query = getSession().createQuery(str.toString());
		query.setParameter("stateId", stateId);
		if(districtId != null && districtId.longValue() > 0l)
			query.setParameter("districtId", districtId);
		return query.list();
	}
  public List<Object[]> getConstituenciesByStatTypeId(Long stateTypeId){
		
		
		StringBuilder str = new StringBuilder();
		str.append("select distinct model.constituencyId,model.name from Constituency model where " +
				"  model.deformDate is null and model.electionScope.electionType.electionTypeId = 2 ");
		if(stateTypeId.longValue() == 0L)
		{
			str.append(" and model.district.districtId between 1 and 23 ");
		}
		else if(stateTypeId.longValue() == 1L)
		{
			str.append(" and model.district.districtId between 11 and 23 ");
		}
		else if(stateTypeId.longValue() == 2L)
		{
			str.append(" and model.district.districtId between 1 and 10 ");
		}
		str.append(" order by model.name "); 
		
		Query query = getSession().createQuery(str.toString());
		
		return query.list();
	}
  public List<Object[]> getConstituenciesByConstituenciesIds(List<Long> constituencyIds,Long stateId){
		StringBuilder str = new StringBuilder();
		str.append("select distinct model.constituencyId,model.name from Constituency model where model.state.stateId = :stateId " +
				  " and model.deformDate is null and model.electionScope.electionScopeId = 2 and model.constituencyId in(:constituencyIds) ");
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("constituencyIds", constituencyIds);
		query.setParameter("stateId", stateId);
		return query.list();
	}
  public List<Object[]> getConstituenciesNamesByIds(List<Long> constituencyIds){
		Query query = getSession().createQuery(" select model.constituencyId,model.name from Constituency model where model.constituencyId in (:constituencyIds) ");
		query.setParameterList("constituencyIds",constituencyIds);
		return query.list();
	}
  public List<Long> getDistrictsByConstituenciesIds(Set<Long> constituenciesIds){
	  StringBuilder str = new StringBuilder();
	  str.append("select distinct model.district.districtId FROM Constituency model " +
	  			" where " +
	  			" model.deformDate is null and model.electionScope.electionScopeId = 2 and model.constituencyId in (:constituencyIds)");
	  Query query = getSession().createQuery(str.toString());
	  query.setParameterList("constituencyIds",constituenciesIds);
	  return query.list();  
  }
  public List<Object[]> getWardDetailsIdsForLocalBody(Long localElectionBodyId){
		Query query=getSession().createSQLQuery("select distinct C.constituency_id as id,C.name as name from constituency C,local_election_body LEB where C.local_election_body_id =:localElectionBodyId " +
				"  and C.local_election_body_id = LEB.local_election_body_id  ")
		.addScalar("id",Hibernate.LONG).addScalar("name",Hibernate.STRING);		
		query.setParameter("localElectionBodyId", localElectionBodyId);
		return query.list();
	}
  
  
  public List<Object[]> getConstituencyByStateDetails()
	{
		Query query = getSession().createQuery(" select distinct model.constituencyId,model.name from Constituency model " +
				" where model.state.stateId = 1 and model.deformDate is null and model.electionScope.electionType.electionTypeId =2" +
				" and model.district.districtId between 11 and 23 or model.district.districtId = 517 order by model.name ");
		
		
		return query.list();
	}
  public List<Long> getConstituenciesIds(Long distId){
	  StringBuilder str = new StringBuilder();
	  str.append("select distinct model.constituencyId FROM Constituency model " +
	  			" where " +
	  			" model.deformDate is null and model.electionScope.electionScopeId = 2 and model.district.districtId = :distId ");
	  Query query = getSession().createQuery(str.toString());
	  query.setParameter("distId",distId);
	  return query.list();
  }
  
  public List<Object[]> getAssemblyConstituencyTeluguNames(){
	  
	  Query query = getSession().createQuery("" +
	  " select model.constituencyId , model.localName , model.district.localName " +
	  " from   Constituency model " +
	  " where  model.deformDate is null and model.electionScope.electionScopeId = 2 and model.state.stateId = 1 ");
	  return query.list();
  }
  public List<Object[]> getConstituencyByConstituencyIds(List<Long> constituencyIds){
		StringBuilder str = new StringBuilder();
		str.append("select distinct model.constituencyId,model.name from Constituency model where " +
				  "  model.deformDate is null and model.electionScope.electionScopeId = 2 and model.constituencyId in(:constituencyIds) ");
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("constituencyIds", constituencyIds);
		return query.list();
	}
  public List<Object[]> getConstLebDetailsByConstIds(List<Long> constIds){
	  Query query = getSession().createQuery("" +
	  " select constituency.constituencyId , constituency.name , leb.localElectionBodyId , leb.name , electionType.electionTypeId , electionType.electionType,constituency.state.stateId" +//6
	  " from   Constituency constituency " +
	  "        left join constituency.localElectionBody leb " +
	  "        left join  leb.electionType electionType " +
	  " where  constituency.constituencyId in (:constIds)");
	  query.setParameterList("constIds", constIds);
	  return query.list();
  }
  public List<Object[]> getConstituencyListByDistrictId(Long districtId){
	  Query query = getSession().createQuery("" +
			  " select model.constituencyId , model.name " +
			  " from Constituency model " +
			  " where  model.deformDate is null and model.electionScope.electionScopeId = 2 " +
			  " and model.district.districtId = :districtId ");
	  query.setParameter("districtId", districtId);
	  return query.list();
	  
  }
  public List<Object[]> getConstituenciesByDistrict(List<Long> districtIds){
	  Query query = getSession().createQuery("select distinct model.constituencyId," +
	  		" model.electionScope.electionScopeId" +
	  		" from Constituency model" +
	  		" where model.district.districtId in (:districtIds)");
	  query.setParameterList("districtIds", districtIds);
	  return query.list();
  }
  public List<Object[]> getTehsilsByDistrict(Long districtId){
		Query query = getSession().createSQLQuery(" select distinct " +
				" T.tehsil_id as tehsilId , " +
				" T.tehsil_name as name from " +
				" tehsil T, constituency CON, constituency_tehsil CT  " +
				" where " +
				" CON.constituency_id = CT.constituency_id and " +
				" T.tehsil_id = CT.tehsil_id and " +
				" CON.district_id = :districtId order by T.tehsil_name")
				.addScalar("tehsilId",Hibernate.LONG)
				.addScalar("name",Hibernate.STRING);
		query.setParameter("districtId", districtId);
		return query.list();
	}
	
	public List<Object[]> getLocalElectionBodiesByDistrict(Long districtId){
		Query query = getSession().createSQLQuery("select distinct " +
				" localBody.local_election_body_id as id," +
				" concat(localBody.name,' ',et.election_type) as name " +
				" from " +
				" assembly_local_election_body assemblyLocalBody,local_election_body  localBody, election_type et,constituency CON  " +
				" where " +
				" assemblyLocalBody.local_election_body_id = localBody.local_election_body_id and  " +
				" year =  (select max(year) from assembly_local_election_body) and " +
				" et.election_type_id = localBody.election_type_id and" +
				" assemblyLocalBody.constituency_id = CON.constituency_id and" +
				" CON.district_id = :districtId " +
				" order by localBody.name ")
				.addScalar("id",Hibernate.LONG)
				.addScalar("name",Hibernate.STRING);
		query.setParameter("districtId", districtId);
		return query.list();
		
	}
	public List<Object[]> getHamletByPanchayat(Long panchayatId){
		Query query = getSession().createSQLQuery(" select H.hamlet_id as id, H.hamlet_name as name from hamlet H, panchayat_hamlet PH where H.hamlet_id = PH.hamlet_id and PH.panchayat_id=:panchayatId ")
				.addScalar("id",Hibernate.LONG)
				.addScalar("name",Hibernate.STRING);
		query.setParameter("panchayatId", panchayatId);
		return query.list();
		
	}
	public List<Long> getConstistuencyWiseParliamentIds(Set<Long> userLocationLevelValues){
		StringBuilder str = new StringBuilder();
		
		str.append(" select ac.constituency_id " +
				" from constituency pc,delimitation_constituency dc," +
				" delimitation_constituency_assembly_details dcad, " +
				" constituency ac where pc.constituency_id = dc.constituency_id and " +
				" pc.election_scope_id = 1 and pc.deform_date is null and " +
				" dc.delimitation_constituency_id = dcad.delimitation_constituency_id " +
				" and dcad.constituency_id = ac.constituency_id  " +
				"  and dc.year=2009  and pc.state_id=1 ");
		
		 if(userLocationLevelValues !=null && userLocationLevelValues.size()>0){
			 str.append(" and pc.constituency_id in (:userLocationLevelValues) ");
		 }
		 
		 Query query = getSession().createSQLQuery(str.toString());				
				
	     if(userLocationLevelValues !=null && userLocationLevelValues.size()>0){
		         query.setParameterList("userLocationLevelValues", userLocationLevelValues);
	          }
		 return query.list();
		
	}
	public List<Object[]> getConstituencyBasedOnDistrictId(Long districtId){
		  Query query = getSession().createQuery("" +
				  " select model.constituencyId , model.name " +
				  " from Constituency model " +
				  " where  model.district.districtId = :districtId ");
		  query.setParameter("districtId", districtId);
		  return query.list();
		  
	  }
	
	public List<Object[]> getConstituencyListByDistrictId(List<Long> districtId){
		  Query query = getSession().createQuery("select distinct model.constituencyId,model.name " +
				  " from Constituency model " +
				  " where  model.deformDate is null and model.electionScope.electionScopeId = 2 " +
				  " and model.district.districtId = :districtId ");
		  query.setParameterList("districtId", districtId);
		  return query.list();
		  
	  }
	
	public List<Object[]> getLocationsDetailsBySearchType(List<Long> locationIds,String searchType){
		  
		StringBuilder sb = new StringBuilder();
		if(searchType != null && searchType.equalsIgnoreCase("constituency")){
		sb.append(" select  model.district.districtId,model.district.districtName, model.constituencyId,model.name " +
				  " from Constituency model " +
				  " where model.electionScope.electionScopeId = 2 " +
				  " and model.constituencyId in (:locationIds) ");
		}
		Query query = getSession().createQuery(sb.toString());
		
		if(locationIds != null && locationIds.size() >0){
			query.setParameterList("locationIds", locationIds);
		}
				
		  return query.list();
	  }
	
	public List<Object[]> getConstituenciesIdsListByDistrictIds(Long districtId)
	{
		StringBuilder sb = new StringBuilder();		
		
		sb.append(" select distinct model.constituencyId , model.name from Constituency model" +
				  " where  model.district.districtId= :districtId" +
				  " and model.electionScope.electionScopeId = 2 " +
				  " order by model.name ");
		
	Query query = getSession().createQuery(sb.toString());
	if(districtId != null && districtId.longValue() >0l){
		query.setParameter("districtId", districtId);
	}
	 return query.list();
	}
	
	public List<Constituency> getConstituencyInfo(Long districtId,String constituencyName)
	{
		StringBuilder sb = new StringBuilder();		
		
		sb.append(" select distinct model from Constituency model" +
				  " where  model.district.districtId= :districtId" +
				  " and model.electionScope.electionScopeId = 2 " +
				  " and model.name =:constituencyName " +
				  " order by model.name desc ");
		
	Query query = getSession().createQuery(sb.toString());
		query.setParameter("districtId", districtId);
		query.setParameter("constituencyName", constituencyName);
	 return query.list();
	}
	public List<Constituency> getWardInfoOfLocalBody(Long localBodyId,String wardName)
	{
		StringBuilder sb = new StringBuilder();		
		
		sb.append(" select distinct model from Constituency model" +
				  " where  model.localElectionBody.localElectionBodyId= :localBodyId" +
				  " and model.name =:wardName " +
				  " order by model.name desc ");
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("localBodyId", localBodyId);
		query.setParameter("wardName", wardName);
		
	 return query.list();
	 
	}
	public List<Long> getConstituenciesIdsList(List<Long> districtIds)
	{
		StringBuilder sb = new StringBuilder();		
		
		sb.append(" select distinct model.constituencyId from Constituency model" +
				  " where " +
				  "  model.electionScope.electionScopeId = 2 " );
		if(districtIds != null && districtIds.size()>0l){
			sb.append(" and model.district.districtId in (:districtIds) ");	
		}
		sb.append(" order by model.name ");
		
	Query query = getSession().createQuery(sb.toString());
	if(districtIds != null && districtIds.size()>0l){
		query.setParameterList("districtIds", districtIds);
	}
	 return query.list();
	}
}

	
	
