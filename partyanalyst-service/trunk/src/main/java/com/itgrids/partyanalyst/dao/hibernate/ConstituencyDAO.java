package com.itgrids.partyanalyst.dao.hibernate;

import java.sql.SQLException;
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
		return getHibernateTemplate().find("select model.constituencyId , model.name from Constituency model where model.district.districtId=? and model.electionScope.electionType.electionTypeId = 2 and model.deformDate IS NULL" +
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
		Query query = getSession().createQuery("select model.constituencyId,model.name from Constituency model " +
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
		return getHibernateTemplate().find("select model.district.districtId,model.state.stateId from Constituency model" +
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
		
		Query query = getSession().createQuery("select distinct model.constituencyId,model.name" +
				" from Constituency model where model.district.districtId in (:districtIds) and model.electionScope.electionType.electionTypeId = 2 and model.deformDate is null order by model.name");
		query.setParameterList("districtIds", districtIds);
		return query.list();
			
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
	public List getLatestConstituenciesByStateIdForregion(String electionType , Long stateID,String region)
	{
		/*Object[] params = {electionType, stateID};
		return getHibernateTemplate().find("select model.constituencyId , model.name from Constituency model" +
				" where model.electionScope.electionType.electionType = ?" +
				" and model.state.stateId=? and model.deformDate is null order by model.name",params);*/
		StringBuilder str = new StringBuilder();
		str.append("select model.constituencyId , model.name from Constituency model" +
				" where model.electionScope.electionType.electionType = :electionType" +
				" and model.state.stateId = :stateID and model.deformDate is null  ");
		if(region.equalsIgnoreCase("Telangana"))
		str.append("and model.district.districtId between 1 and 10 order by model.name");
		else
		{
			str.append("and model.district.districtId between 11 and 23 order by model.name");	
		}
		Query query = getSession().createQuery(str.toString());
		query.setParameter("stateID", stateID);
		query.setParameter("electionType", electionType);
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
						"C.electionScope.electionType.electionTypeId = 2 and C.deformDate is null order by C.name ");
				query.setParameter("stateId", stateId);
				result =  query.list();
			}		
			else if(stateTypeId.longValue() == 1L)
			{
				Query query = getSession().createQuery("select  distinct  C.constituencyId , C.name  from Constituency C where C.state.stateId = :stateId and " +
						"C.electionScope.electionType.electionTypeId = 2 and C.deformDate is null and C.district.districtId > 10 order by C.name ");	
				query.setParameter("stateId", stateId);
				result =   query.list();
			}
			else if(stateTypeId.longValue() == 2L)
			{
				Query query = getSession().createQuery("select  distinct  C.constituencyId , C.name  from Constituency C where C.state.stateId = :stateId and " +
						"C.electionScope.electionType.electionTypeId = 2 and C.deformDate is null and C.district.districtId < 11 order by C.name ");
				query.setParameter("stateId", stateId);
				result =   query.list();
			}
		}
		else
		{
			if(stateTypeId.longValue() == 0L)
			{
				Query query = getSession().createQuery("select  distinct C.constituencyId , C.name from Constituency C,ConstituencyElection CE where C.state.stateId = :stateId and " +
						"C.electionScope.electionType.electionTypeId = 2 and C.deformDate is null and CE.constituency.constituencyId =  C.constituencyId and CE.election.electionYear ="+electionYear+" order by C.name ");
				query.setParameter("stateId", stateId);

				result =  query.list();
			}		
			else if(stateTypeId.longValue() == 1L)
			{
				Query query = getSession().createQuery("select  distinct  C.constituencyId , C.name  from Constituency C ,ConstituencyElection CE where C.state.stateId = :stateId and " +
						"C.electionScope.electionType.electionTypeId = 2 and C.deformDate is null and C.district.districtId > 10 and CE.constituency.constituencyId =  C.constituencyId and CE.election.electionYear = "+electionYear+" order by C.name ");
				
				query.setParameter("stateId", stateId);
				
				result =   query.list();
			}
			else if(stateTypeId.longValue() == 2L)
			{
				Query query = getSession().createQuery("select  distinct  C.constituencyId , C.name  from Constituency C ,ConstituencyElection CE where C.state.stateId = :stateId and " +
						"C.electionScope.electionType.electionTypeId = 2 and C.deformDate is null and C.district.districtId < 11 and CE.constituency.constituencyId =  C.constituencyId and CE.election.electionYear = "+electionYear+" order by C.name ");
				
				query.setParameter("stateId", stateId);

				result =   query.list();
			}
		}
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List getParliamentConstituencyInfoByConstituencyIds(List<Long> constituencyIds) {

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
			if(stateId.longValue() == 1){
				str.append(" and model.district.districtId > 10 ");
			}else{
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
		Query query = getSession().createQuery("select distinct model.constituencyId, model.name from Constituency model where " +
				" model.district.districtId in (:districtIdList)  and model.electionScope.electionType.electionTypeId = 2 order by   model.name asc ");
		
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
}