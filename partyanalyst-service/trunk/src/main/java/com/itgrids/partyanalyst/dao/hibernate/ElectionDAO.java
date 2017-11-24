package com.itgrids.partyanalyst.dao.hibernate;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.itgrids.partyanalyst.dao.IElectionDAO;
import com.itgrids.partyanalyst.dao.columns.enums.ElectionColumnNames;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.utils.IConstants;

public class ElectionDAO extends GenericDaoHibernate<Election, Long> implements
IElectionDAO {

	public ElectionDAO() {
		super(Election.class);
	}

	@SuppressWarnings("unchecked")
	public List<Election> findByProperty(ElectionColumnNames propertyName, Object value) {
		return getHibernateTemplate().find("from Election model where model." + propertyName.getValue() + "=?", value);		
	}

	public Object[] listOfColumn( final ElectionColumnNames propertyName) {       

		return ( Object[] ) getHibernateTemplate().execute( new HibernateCallback() {
			public Object doInHibernate( Session session ) throws HibernateException, SQLException {
				Criteria criteria = session.createCriteria( Election.class ).add( Expression.like( propertyName.getValue() , propertyName ) ); 
				// u can add more expressions by using criteria.add(Expression.like(...)); in next lines
				return ( criteria.list() );
			}
		} );
	}
	/*
	public Object[] listOfColumn(ElectionColumnNames propertyName){
		final String queryString = "select model "+propertyName.getValue()+" from Election model";
			Query q = super.entityManager.createQuery(queryString);
			return (Object [])q.getSingleResult();
	}
	 */
	@SuppressWarnings("unchecked")
	public List<String> listOfYears(){
		return getHibernateTemplate().find("select distinct election.electionYear from Election election");

	}


	public List<Election> findByEndDate(Object endDate){

		return findByProperty(ElectionColumnNames.END_DATE, endDate);
	}

	public List<Election> findByElectionYear(Object electionYear){

		return findByProperty(ElectionColumnNames.ELECTION_YEAR, electionYear);
	}

	@SuppressWarnings("unchecked")
	public List<Election> findByPropertyTypeId_CountryId_StateId(final Long typeID, final Long countryId, final Long stateId) {
		//Long[] params = {new (typeID), new Long(stateId), new Long(countryId)};
		//Long[] params = {typeID, countryId, stateId};
		//return getHibernateTemplate().find("from Election model where model.electionScope.electionType.electionTypeId =? and model.electionScope.state.stateId=? and model.electionScope.country.countryId=?", params);
		return ( List<Election> ) getHibernateTemplate().execute( new HibernateCallback() {
			public Object doInHibernate( Session session ) throws HibernateException, SQLException {
				List<Election> elections = session.createCriteria(Election.class)
						.createAlias("electionScope", "scope")
						.createAlias("scope.electionType", "type")
						.createAlias("scope.state", "state")
						.createAlias("scope.country", "country")
						.add(Expression.eq("type.electionTypeId", typeID))
						.add(Expression.eq("state.stateId", stateId))
						.add(Expression.eq("country.countryId", countryId))
						.list();
				return elections;
			}
		});
	}

	@SuppressWarnings("unchecked")
	public List<Election> findByElectionScopeId(Long electionScopeId,
			String electionSubtypes){
		Object params[] = {electionScopeId, electionSubtypes};
		return getHibernateTemplate().find("from Election model where model.electionScope.electionScopeId = ? " +
				"and model.elecSubtype = ? order by model.electionYear desc", params);
	}

	@SuppressWarnings("unchecked")
	public List<Election> findByElectionScopeId(Long electionScopeId){
		Object params[] = {electionScopeId};
		return getHibernateTemplate().find("from Election model where model.electionScope.electionScopeId = ? " +
				"order by model.electionYear desc", params);
	}

	@SuppressWarnings("unchecked")
	public List<Election> findByElectionType(Long typeID) {

		return getHibernateTemplate().find("from Election model where model.electionScope.electionType.electionTypeId =?", typeID);
	}

	@SuppressWarnings("unchecked")
	public Election findByElectionScopeIdElectionYear(Long electionScope,String electionYear, String elecSubtype){
		Election electionObj=null;
		Query query = getSession().createQuery("from Election model where model.electionScope.electionScopeId =? " +
				"and model.electionYear=? and model.elecSubtype = ?");
		query.setParameter(0, electionScope);
		query.setParameter(1, electionYear);
		query.setParameter(2, elecSubtype);
		List<Election> list= query.list();
		if(list!=null && list.size()>0){
			return electionObj=list.get(0);
		}
		return electionObj;		
	}

	public String findPreviousElectionYear(final String year, final Long typeId, final Long stateId, final Long countryId) {
		return (String) getHibernateTemplate().execute( new HibernateCallback() {
			public Object doInHibernate( Session session ) throws HibernateException, SQLException {
				return (String) session.createCriteria(Election.class)
						. setProjection(Projections.property("electionYear"))
						.createAlias("electionScope", "scope")
						.createAlias("scope.electionType", "type")
						.createAlias("scope.state", "state")
						.createAlias("scope.country", "country")
						.add(Expression.eq("type.electionTypeId", typeId))
						.add(Expression.eq("state.stateId", stateId))
						.add(Expression.eq("country.countryId", countryId))
						.add(Expression.eq("elecSubtype", IConstants.ELECTION_SUBTYPE_MAIN))
						.add(Restrictions.lt("electionYear", year))
						.addOrder(Order.desc("electionYear"))
						.setMaxResults(1).uniqueResult();
			}
		});
	}

	public String findPreviousParliamentElectionYear(final String year, final Long typeId, final Long countryId) {
		return (String) getHibernateTemplate().execute( new HibernateCallback() {
			public Object doInHibernate( Session session ) throws HibernateException, SQLException {
				return (String) session.createCriteria(Election.class)
						. setProjection(Projections.property("electionYear"))
						.createAlias("electionScope", "scope")
						.createAlias("scope.electionType", "type")
						.createAlias("scope.country", "country")
						.add(Expression.eq("type.electionTypeId", typeId))
						.add(Expression.eq("country.countryId", countryId))
						.add(Expression.eq("elecSubtype", IConstants.ELECTION_SUBTYPE_MAIN))
						.add(Restrictions.lt("electionYear", year))
						.addOrder(Order.desc("electionYear"))
						.setMaxResults(1).uniqueResult();
			}
		});
	}


	@SuppressWarnings("unchecked")
	public Election getElectionByCountryStateTypeIDElectionYear(Long typeID, Long countryID, Long stateID,String electionYear){
		Object[] params = {typeID, countryID, stateID, electionYear};
		List<Election> list = getHibernateTemplate().find("from Election model where model.electionScope.electionType.electionTypeId =? and " +
				" model.electionScope.country.countryId and model.electionScope.state.stateId and model.electionYear=?", params);
		if(list!=null && list.size()==1){
			return list.get(0);
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public List<Election> findByElectionTypeCountry(Long typeId, Long countryID){
		Object[] params = {typeId, countryID};
		return getHibernateTemplate().find("from Election model where model.electionScope.electionType.electionTypeId = ? " +
				"and model.electionScope.country.countryId=?", params);
	}

	@SuppressWarnings("unchecked")
	public List<Election> findByElectionTypeYearAndState(Long typeId,String year,Long stateId,Long countryId){
		Object[] params = {typeId,year,stateId,countryId};
		return getHibernateTemplate().find("from Election model where model.electionScope.electionType.electionTypeId = ? and model.electionYear = ? and model.electionScope.state.stateId = ? and model.electionScope.country.countryId = ?", params);
	}

	@SuppressWarnings("unchecked")
	public List<Election> findByElectionTypeYearAndCountryForParliament(
			Long typeId, String year, Long countryId) {
		Object[] params = {typeId,year,countryId};
		return getHibernateTemplate().find("from Election model where model.electionScope.electionType.electionTypeId = ? and model.electionYear = ? and model.electionScope.state is null and model.electionScope.country.countryId = ?", params);

	}


	@SuppressWarnings("unchecked")
	public List findLatestElectionYear(String electionType){
		return getHibernateTemplate().find("select max(model.electionYear) from Election model where model.electionScope.electionType.electionType = ?", electionType);
	}

	@SuppressWarnings("unchecked")
	public List findLatestElectionYearForGHMC(String electionType){
		return getHibernateTemplate().find("select max(model.electionId),max(model.electionYear) from Election model where model.electionScope.electionType.electionType = ?", electionType);
	}


	@SuppressWarnings("unchecked")
	public List findElectionIdAndYear(Long electionType,Long stateId){
		Object[] params = {electionType,stateId};
		return getHibernateTemplate().find("select max(model.electionId),max(model.electionYear) from Election model where model.electionScope.electionType.electionTypeId = ? and model.electionScope.state.stateId = ?",params);
	}

	@SuppressWarnings("unchecked")
	public List findRecentElectionIdAndYearForParliament(Long electionType){
		Object[] params = {electionType};
		return getHibernateTemplate().find("select max(model.electionId),max(model.electionYear) from Election model where model.electionScope.electionType.electionTypeId = ? and model.electionScope.state is null",params);
	}

	@SuppressWarnings("unchecked")
	public List findElectionAndYearForElectionTypeAndState(Long electionType,Long stateId){

		String isPartial = "0";
		Object[] params = {electionType,stateId,isPartial};
		return getHibernateTemplate().find("select model.electionId,model.electionYear from Election model where model.electionScope.electionType.electionTypeId = ? and model.electionScope.state.stateId = ? and "+
				"model.isPartial is null or model.isPartial = ? order by model.electionYear desc",params);
	}

	@SuppressWarnings("unchecked")
	public List findElectionAndYearForParliamentElectionType(Long electionType){

		String isPartial = "0";
		Object[] params = {electionType,isPartial};
		return getHibernateTemplate().find("select model.electionId,model.electionYear from Election model where model.electionScope.electionType.electionTypeId = ? and model.electionScope.state is null and "+
				"model.isPartial is null or model.isPartial = ? order by model.electionYear desc",params);
	}

	@SuppressWarnings("unchecked")
	public List findElectionYearsForElectionTypeAndState(Long electionType,Long stateId){
		Object[] params = {electionType,stateId};
		return getHibernateTemplate().find("select distinct model.electionId,model.electionYear,model.electionScope.electionType.electionType from Election model where model.electionScope.electionType.electionTypeId = ? and model.electionScope.state.stateId = ? order by model.electionYear desc ",params);
	}

	@SuppressWarnings("unchecked")
	public List findLatestElectionIdAndYear(Long electionType){
		return getHibernateTemplate().find("select model.electionId,model.electionYear from Election model where model.electionScope.electionType.electionTypeId = ? and model.electionYear = (select max(model.electionYear) from model)", electionType);
	}


	@SuppressWarnings("unchecked")
	public List findLatestElectionIdAndYear(Long electionType,Long stateId){
		Object[] params = {electionType,stateId};
		return getHibernateTemplate().find("select max(model.electionId),max(model.electionYear) from Election model where model.electionScope.electionType.electionTypeId = ? and model.electionScope.state.stateId = ?",params);
	}

	@SuppressWarnings("unchecked")
	public List findLatestElectionIdAndYearForAnElection(String electionType,Long stateId,String elecSubType){

		StringBuilder query = new StringBuilder();
		query.append(" select model.electionId from Election model ");	

		query.append(" where model.electionYear = (select max(model2.electionYear) from Election model2");				
		query.append(" where model2.electionScope.electionType.electionType = ? and model2.elecSubtype = ?  ");		
		if(electionType.equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE)){
			query.append(" and model2.electionScope.state.stateId = ?");
		}		
		query.append("  ) and model.electionScope.electionType.electionType = ? and model.elecSubtype = ?");		
		if(electionType.equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE)){
			query.append(" and model.electionScope.state.stateId = ?");
		}

		query.append(" order by model.electionId ");

		Query queryObject = getSession().createQuery(query.toString());	
		queryObject.setString(0,electionType);	
		queryObject.setString(1,elecSubType);	
		if(electionType.equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE)){
			queryObject.setLong(2,stateId);	
			queryObject.setString(3,electionType);	
			queryObject.setString(4,elecSubType);
			queryObject.setLong(5,stateId);	
		}else{
			queryObject.setString(2,electionType);	
			queryObject.setString(3,elecSubType);	
		}	



		return queryObject.list();

	}

	@SuppressWarnings("unchecked")
	public List findParliamentElectionIdByElectionTypeAndYear(String electionType,String year){
		Object[] params = {electionType,year};
		return getHibernateTemplate().find("select model.electionId from Election model where model.electionScope.electionType.electionType = ? and model.electionYear = ?",params);
	}

	@SuppressWarnings("unchecked")
	public List findElectionIdByElectionTypeAndYear(String electionType,String year,Long stateId){
		Object[] params = {electionType,year,stateId};
		return getHibernateTemplate().find("select model.electionId from Election model where model.electionScope.electionType.electionType = ? and model.electionYear = ? and model.electionScope.state.stateId = ?",params);
	}

	@SuppressWarnings("unchecked")
	public List findElectionIdByParliamentElectionTypeAndYear(String electionType,String year){
		Object[] params = {electionType,year};
		return getHibernateTemplate().find("select model.electionId from Election model where model.electionScope.electionType.electionType = ? and model.electionYear = ? and model.electionScope.state is null",params);
	}

	@SuppressWarnings("unchecked")
	public List findElectionIdForGivenElectionYearAndElectionYears(Long electionType,String year,Long stateId,String electionSubType){	
		Object[] params = {electionType,stateId,electionSubType};
		return getHibernateTemplate().find("select model.electionId from Election model where " +
				" model.electionScope.electionType.electionTypeId = ? and model.electionYear in ("+ year +") " +
				" and model.electionScope.state.stateId = ? and model.elecSubtype = ? ",params);
	}

	@SuppressWarnings("unchecked")
	public List findParliamentElectionIdForGivenElectionYearAndElectionYears(Long electionType,String year,String electionSubType){	
		Object[] params = {electionType,electionSubType};
		return getHibernateTemplate().find("select model.electionId from Election model where " +
				" model.electionScope.electionType.electionTypeId = ? and model.electionYear in ("+ year +") " +
				" and model.elecSubtype = ? ",params);
	}

	@SuppressWarnings("unchecked")
	public List getElectionTypeAndElectionYearByElectionId(Long electionId){
		return getHibernateTemplate().find("select model.electionScope.electionType.electionType,model.electionYear from Election model where model.electionId = ? order by model.electionYear desc",electionId);
	}

	@SuppressWarnings("unchecked")
	public List findByElectionType(Long typeID, String type) {

		Object[] params = {typeID, type};
		return getHibernateTemplate().find("from Election model where model.electionScope.electionType.electionTypeId =? and model.elecSubtype = ?", params);

	}

	@SuppressWarnings("unchecked")
	public List findLatestElectionAssemblyElectionYearForState(String electionType, Long stateId){
		Object[] params = {electionType, stateId};
		return getHibernateTemplate().find("select max(model.electionYear) from Election model where model.electionScope.electionType.electionType = ?" +
				"and model.electionScope.state.stateId = ?", params);
	}

	@SuppressWarnings("unchecked")
	public List findLatestElectionAssemblyElectionYearForState(String electionType, Long stateId,String electionSubType){
		Object[] params = {electionType, stateId,electionSubType};
		return getHibernateTemplate().find("select max(model.electionYear) from Election model where model.electionScope.electionType.electionType = ?" +
				"and model.electionScope.state.stateId = ? and model.elecSubtype = ?", params);
	}

	@SuppressWarnings("unchecked")
	public List findLatestParliamentElectionYear(String electionType,String electionSubType){
		Object[] params = {electionType,electionSubType};
		return getHibernateTemplate().find("select max(model.electionYear) from Election model where model.electionScope.electionType.electionType = ?" +
				" and model.elecSubtype = ?", params);
	}

	@SuppressWarnings("unchecked")
	public List findLatestElectionAssemblyElectionIdForState(String electionType, Long stateId,String electionSubType){
		Object[] params = {electionType, stateId,electionSubType};
		return getHibernateTemplate().find("select max(model.electionYear),model.electionId from Election model where model.electionScope.electionType.electionType = ?" +
				"and model.electionScope.state.stateId = ? and model.elecSubtype = ? group by model.electionScope.electionType.electionType", params);
	}


	@SuppressWarnings("unchecked")
	public List findStatesByElectionType(Long electionTypeId) {

		return getHibernateTemplate().find("select distinct model.electionScope.state.stateId, model.electionScope.state.stateName from Election model where model.electionScope.electionType.electionTypeId = ?", electionTypeId);

	}

	@SuppressWarnings("unchecked")
	public List getLocalBodyElectionsInAState(String electionType,Long stateId){
		Object[] params = {electionType,stateId};
		return getHibernateTemplate().find("select model.electionId,model.electionYear from Election model where model.electionScope.electionType.electionType = ?"+
				" and model.electionScope.state.stateId = ? order by model.electionYear desc",params);
	}

	@SuppressWarnings("unchecked")
	public List<Election> getRecentElectionHappendForAnElectionType(
			String electionType,Long stateId) {
		return getHibernateTemplate().find("from Election model where model.electionScope.electionType.electionType = ? and model.electionYear = (select max(model.electionYear) from Election model)", electionType);
	}

	@SuppressWarnings("unchecked")
	public List findElectionsByState(Long stateId) {

		String isPartial = "0";
		Object[] params = {stateId,isPartial, stateId,isPartial};
		return getHibernateTemplate().find("select model.electionId, model.electionScope.electionType.electionTypeId, " +
				"model.electionScope.electionType.electionType, model.elecSubtype, model.electionYear from Election model " +
				"where model.electionScope.state.stateId = ? and model.isPartial is null or model.isPartial = ? or (model.electionScope.country.countryId = (select model.country.countryId " +
				"from State model where model.stateId= ?) and model.electionScope.state is null and model.isPartial is null or model.isPartial = ?) order by electionYear desc", params);
	}

	@SuppressWarnings("unchecked")
	public List<Election> findElections(Long electionType_id, Long country_id,
			Long state_id) {

		Query queryObject = getSession().createQuery("from Election as model where model.electionScope.electionScopeId  in ( select electionScopeId from ElectionScope as newmodel where newmodel.electionType.electionTypeId = ? and newmodel.country.countryId = ? and newmodel.state.stateId = ?)");
		queryObject.setParameter(0, electionType_id);
		queryObject.setParameter(1, country_id);
		queryObject.setParameter(2, state_id);

		return queryObject.list(); 
	}


	@SuppressWarnings("unchecked")
	public List<Election> findElections(Long electionType_id, Long country_id) {

		Query queryObject = getSession().createQuery("from Election as model where model.electionScope.electionScopeId  in ( select electionScopeId from ElectionScope as newmodel where newmodel.electionType.electionTypeId = ? and newmodel.country.countryId = ? and newmodel.state.stateId = ?)");
		queryObject.setParameter(0, electionType_id);
		queryObject.setParameter(1, country_id);
		queryObject.setParameter(2, null);

		return queryObject.list(); 
	}

	@SuppressWarnings("unchecked")
	public List<Election> findElections(Long state_id) {

		Query queryObject = getSession().createQuery("from Election as model where model.electionScope.state.stateId = ?");
		queryObject.setParameter(0, state_id);

		return queryObject.list();
	}	

	@SuppressWarnings("unchecked")
	public List findLatestParliamentaryElectionYear(Long state_id) {

		Query queryObject = getSession().createQuery("select max(model.electionYear) from Election as model where model.electionScope.state.stateId = ? and model.electionScope.electionType.electionType='Parliament'");
		queryObject.setParameter(0, state_id);

		return queryObject.list();
	}	

	public List findLatestElectionIdForElectionType(String electionType, String subType){
		Object[] params = {electionType, subType, electionType, subType};
		return getHibernateTemplate().find("select model.electionId, model.electionYear from Election model where model.electionScope.electionType.electionType = ? " +
				"and model.elecSubtype = ? and model.electionYear = (select max(model1.electionYear) from Election model1 where model1.electionScope.electionType.electionType = ? " +
				"and model1.elecSubtype = ?) ", params);
	}

	public List findLatestElectionIdForElectionType(String electionType, String subType,Long stateId){
		Object[] params = {electionType, subType, electionType, subType, stateId, stateId};
		return getHibernateTemplate().find("select model.electionId, model.electionYear from Election model where model.electionScope.electionType.electionType = ? " +
				"and model.elecSubtype = ? and model.electionYear = (select max(model1.electionYear) from Election model1 where model1.electionScope.electionType.electionType = ? " +
				"and model1.elecSubtype = ? and model1.electionScope.state.stateId = ? ) and model.electionScope.state.stateId = ?", params);
	}


	public List<Long> getAllElectionYearsBasedOnElectionType(String electionType,String type,Long stateId){
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.electionYear from Election model ");
		sb.append(" where model.electionScope.electionType.electionType = ? and model.electionScope.state.stateId = ?");
		sb.append(" and model.elecSubtype = ? order by model.electionYear desc");

		Query queryObject = getSession().createQuery(sb.toString());
		queryObject.setString(0,electionType);
		queryObject.setLong(1,stateId);
		queryObject.setString(2,type);
		return queryObject.list();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getElectionYearsBasedOnElectionTypeAndState(Long stateId,String electionType)
	{
		Object[] params = {stateId,electionType,IConstants.ELECTION_SUBTYPE_MAIN};
		return getHibernateTemplate().find("select model.electionId,model.electionYear from Election model where (model.electionScope.state.stateId = ? or model.electionScope.state.stateId is null) and " +
				" model.electionScope.electionType.electionType = ? and model.elecSubtype = ? and model.electionYear >1982 order by model.electionYear desc",params);
	}

	public List getCountOfElectionYears(Long stateId,String electionType,String elecSubType){
		Object[] params = {stateId, electionType,elecSubType};
		return getHibernateTemplate().find("select count(model) from Election model " +
				"where model.electionScope.state.stateId = ? and model.electionScope.electionType.electionType = ? and model.elecSubtype = ? ", params);
	}

	public List getCountOfElectionYearsForParliament(Long stateId,String electionType,String elecSubType){
		Object[] params = {electionType,elecSubType};
		return getHibernateTemplate().find("select count(model) from Election model " +
				"where model.electionScope.electionType.electionType = ?  and model.elecSubtype = ?", params);
	}

	@SuppressWarnings("unchecked")
	public List<Long> getElectionYears(Long stateId,String electionType,String elecSubType){

		String isPartial = "0";
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.electionYear from Election model ");
		sb.append(" where model.electionScope.electionType.electionType = ? and model.elecSubtype = ? ");

		if(electionType.equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE)){
			sb.append(" and model.electionScope.state.stateId = ? ");
		}
		sb.append("and model.isPartial is null or model.isPartial = ? order by model.electionYear desc");

		Query queryObject = getSession().createQuery(sb.toString());
		queryObject.setString(0,electionType);
		queryObject.setString(1,elecSubType);

		if(electionType.equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE)){
			queryObject.setLong(2,stateId);
			queryObject.setString(3,isPartial);
		}
		else
			queryObject.setString(2,isPartial);

		return queryObject.list();
	}

	@SuppressWarnings("unchecked")
	public List<Long> getElectionIds(Long stateId,String electionType,String elecSubType){
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.electionId from Election model ");
		sb.append(" where model.electionScope.electionType.electionType = ? and model.elecSubtype = ? ");

		if(electionType.equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE)){
			sb.append(" and model.electionScope.state.stateId = ?");
		}
		sb.append("  and model.isPartial is null or model.isPartial = ? order by model.electionYear desc");

		Query queryObject = getSession().createQuery(sb.toString());
		queryObject.setString(0,electionType);
		queryObject.setString(1,elecSubType);

		if(electionType.equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE)){
			queryObject.setLong(2,stateId);
			queryObject.setString(3,"0");
		}
		else
			queryObject.setString(2,"0");


		return queryObject.list();
	}

	@SuppressWarnings("unchecked")
	public List getElectionDetailsForAnElection(Long electionId){


		StringBuilder query = new StringBuilder();
		query.append(" select model.electionYear,model.electionId,model.electionScope.electionType.electionTypeId");
		query.append(" from Election model where model.electionId = ?");

		Query queryObject = getSession().createQuery(query.toString());	
		queryObject.setLong(0,electionId);	
		return queryObject.list();	
	}

	/**
	 * DAO method to get latest electionId happened in a state election by electiontype and stateID
	 */
	@SuppressWarnings("unchecked")
	public List findRecentElectionIdByElectionTypeAndState(String electionType,
			Long stateId) {

		StringBuilder query = new StringBuilder();

		query.append("select max(model.electionYear) from Election model ");
		query.append("where model.electionScope.electionType.electionType = ? ");

		if(stateId != null && !stateId.equals(0L))
			query.append("and model.electionScope.state.stateId = ? ");

		query.append("order by model.electionYear desc");

		Query queryObject = getSession().createQuery(query.toString());	
		queryObject.setParameter(0,electionType);
		queryObject.setParameter(1, stateId);

		return queryObject.list();	
	}

	@SuppressWarnings("unchecked")
	public List findLatestElectionYearHappenedInState(Long stateId,String electionType) {

		Object[] params = {stateId,electionType};
		return getHibernateTemplate().find("select max(model.electionYear) from Election model where model.electionScope.state.stateId = ? and model.electionScope.electionType.electionType = ? and "+
				"model.isPartial is null",params);
	}

	@SuppressWarnings("unchecked")
	public List findLatestElectionYearHappenedInState(Long stateId,String electionType,String electionSubType) {

		String isPartial = "0";
		Object[] params = {stateId,electionType,electionSubType,isPartial};
		return getHibernateTemplate().find("select max(model.electionYear) from Election model where model.electionScope.state.stateId = ? and model.electionScope.electionType.electionType = ? and "+
				"model.elecSubtype = ? and model.isPartial is null or model.isPartial = ?",params);
	}

	@SuppressWarnings("unchecked")
	public List getLatestElectionYearForAStateBasedOnElectionType(Long stateId, String electionType, String subType)
	{
		Object params[] = {electionType, stateId, subType};
		return getHibernateTemplate().find("select max(model.electionYear) from Election model where model.electionScope.electionType.electionType = ? and model.electionScope.state.stateId = ? and model.elecSubtype = ? ", params);
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getPreviousElectionIdAndYear(Long electionId)
	{
		Object params[] = {electionId, electionId, IConstants.ELECTION_SUBTYPE_MAIN,electionId, IConstants.ELECTION_SUBTYPE_MAIN};
		return getHibernateTemplate().find(" select model.electionId,model.electionYear from Election model where model.electionYear = (" +
				" select  max(model2.electionYear) from Election model2 where model2.electionYear < (select model3.electionYear from Election model3 where model3.electionId = ?) and" +
				" model2.electionScope.electionScopeId = (select model5.electionScope.electionScopeId from Election model5 where model5.electionId = ?) and model2.elecSubtype = ? ) and " +
				" model.electionScope.electionScopeId = (select model4.electionScope.electionScopeId from Election model4 where model4.electionId = ?) and model.elecSubtype = ? ",params);
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getElectionYearsBasedOnElectionTypeIdAndStateId(Long stateId,Long electionTypeId)
	{
		Object[] params = {stateId,electionTypeId,IConstants.ELECTION_SUBTYPE_MAIN};
		return getHibernateTemplate().find("select model.electionId,model.electionYear from Election model where model.electionScope.state.stateId = ? and " +
				" model.electionScope.electionType.electionTypeId = ? and model.elecSubtype = ? order by model.electionYear desc",params);
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getElectionYearsBasedOnElectionType(Long stateId,Long electionTypeId)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select model.electionId,model.electionYear from Election model where model.electionScope.electionType.electionTypeId =:electionTypeId and model.elecSubtype =:elecSubtype ");
		if(electionTypeId != null && !electionTypeId.equals(1L))
			str.append(" and model.electionScope.state.stateId =:stateId ");
		str.append(" order by model.electionYear desc ");

		Query query = getSession().createQuery(str.toString());
		query.setParameter("electionTypeId", electionTypeId);
		query.setParameter("elecSubtype", IConstants.ELECTION_SUBTYPE_MAIN);
		if(electionTypeId != null && !electionTypeId.equals(1L))
			query.setParameter("stateId", stateId);

		return query.list();

	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getElectionYearsBasedOnElectionTypeId(Long electionTypeId) {
		Query query = getSession().createQuery("Select model.electionId,model.electionYear from Election model where model.electionScope.electionType.electionTypeId =:electionTypeId" +
				" and model.elecSubtype =:type ");

		query.setParameter("electionTypeId", electionTypeId);
		query.setParameter("type", IConstants.ELECTION_SUBTYPE_MAIN);

		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getStatesBasedOnElectionTypeId(Long electionTypeId,String electionType){

		StringBuilder queryStr = new StringBuilder();
		if(electionType.equalsIgnoreCase(IConstants.PARLIAMENT_ELECTION_TYPE)){
			queryStr.append("Select model.country.countryId ,model.country.countryName");
		}
		if(electionType.equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE)){
			queryStr.append("Select model.state.stateId ,model.state.stateName");
		}
		return getHibernateTemplate().find(queryStr.toString().concat("  from ElectionScope model where model.electionType.electionTypeId = ?"),electionTypeId);

	}

	public List<Object[]> getElectionYears(Long stateId,String electionType)
	{
		Object[] params = {stateId,electionType,"1",IConstants.ELECTION_SUBTYPE_MAIN};
		return getHibernateTemplate().find("select model.electionId,model.electionYear from Election model where (model.electionScope.state.stateId = ? or model.electionScope.state.stateId is null) and " +
				" model.electionScope.electionType.electionType = ? and model.isPartial = ? and model.elecSubtype = ?  order by model.electionYear desc",params);
	}

	public List getElectionIdsBasedOnStateId(Long stateId,String year) {
		Object[] params = {stateId,year};
		return getHibernateTemplate().find("select model.electionId from Election model where model.electionScope.state.stateId = ? and model.electionYear >=?",params);
	}

	public Object getCountOfElectionsAfterDelimitation(Long electionScopeId)
	{
		Query query = getSession().createQuery("select count(model.electionId) from Election model where model.electionScope.electionScopeId = ? and model.electionYear >= ? and model.elecSubtype = ? ");
		query.setParameter(0,electionScopeId);
		query.setParameter(1,IConstants.DELIMITATION_YEAR.toString());
		query.setParameter(2,IConstants.ELECTION_SUBTYPE_MAIN);

		return query.uniqueResult();
	}

	public List<Object[]> getStateDetailsForPartialElec()
	{
		Object[] params = {2l,"1"};
		return getHibernateTemplate().find("select distinct model.electionScope.state.stateId,model.electionScope.state.stateName from Election model where model.electionScope.electionType.electionTypeId =? and model.isPartial = ?  order by electionScope.state.stateName",params);

	}
	public List<Object[]> getNextElectionIdAndYear(Long electionId)
	{
		Object params[] = {electionId, electionId, IConstants.ELECTION_SUBTYPE_MAIN,electionId, IConstants.ELECTION_SUBTYPE_MAIN};
		return getHibernateTemplate().find(" select model.electionId,model.electionYear from Election model where model.electionYear = (" +
				" select  min(model2.electionYear) from Election model2 where model2.electionYear > (select model3.electionYear from Election model3 where model3.electionId = ?) and" +
				" model2.electionScope.electionScopeId = (select model5.electionScope.electionScopeId from Election model5 where model5.electionId = ?) and model2.elecSubtype = ? ) and " +
				" model.electionScope.electionScopeId = (select model4.electionScope.electionScopeId from Election model4 where model4.electionId = ?) and model.elecSubtype = ? ",params);
	}
	public List<Election> getElectionDetails(Long electionId)
	{
		return getHibernateTemplate().find("from Election where electionId=?",electionId);
	}

	public List<Object[]> getPartianValue() {

		return getHibernateTemplate().find("select distinct model.electionScope.state.stateId,model.electionScope.state.stateName from Election model where model.isPartial = 1 and model.electionScope.electionType.electionTypeId = 2");
	}

	public List<Long> getPreviousMainElectionByStateIdYear(Long stateId,String year){
		Object params[] = {stateId,IConstants.ELECTION_SUBTYPE_MAIN,IConstants.ELECTION_SUBTYPE_MAIN,stateId};
		return getHibernateTemplate().find(" select model.electionId from Election model where model.isPartial is null and model.electionScope.electionType.electionTypeId = 2 and model.electionScope.state.stateId = ? and model.elecSubtype = ? " +
				"and model.electionYear = (select max(model1.electionYear) from Election model1 where model1.isPartial is null and  model1.elecSubtype = ?  " +
				"  and model1.electionScope.electionType.electionTypeId = 2 and model1.electionScope.state.stateId = ? and model1.electionYear < "+year+" ) order by model.electionDate desc",params);
	}

	public List<Object[]> getPreviousElectionsByStateIdYearAndDate(Long stateId,String year,Date date){

		Query query = getSession().createQuery(" select model.electionId,model.elecSubtype from Election model where model.isPartial is null and model.electionScope.electionType.electionTypeId = 2 and model.electionScope.state.stateId = :stateId  " +
				"and model.electionYear >= (select max(model1.electionYear) from Election model1 where model1.isPartial is null and  model1.elecSubtype = :elecType  " +
				"  and model1.electionScope.electionType.electionTypeId = 2 and model1.electionScope.state.stateId = :stateId and model1.electionYear < "+year+" ) " +
				" and model.electionYear <= (select max(model2.electionYear) from Election model2 where model2.isPartial is null  and model2.electionScope.electionType.electionTypeId = 2 and " +
				"  model2.electionScope.state.stateId = :stateId and model2.electionYear < "+year+" ) and model.electionYear < :date order by model.electionDate desc");
		query.setParameter("stateId", stateId);
		query.setParameter("elecType", IConstants.ELECTION_SUBTYPE_MAIN);
		query.setDate("date", date);
		return query.list();
	}

	public List<Election> getPreviousElections(Long stateId,String year,Date date){

		Query query = getSession().createQuery(" select model from Election model where model.isPartial is null and model.electionScope.electionType.electionTypeId = 2 and model.electionScope.state.stateId = :stateId  " +
				"and model.electionYear >= (select max(model1.electionYear) from Election model1 where model1.isPartial is null and  model1.elecSubtype = :elecType  " +
				"  and model1.electionScope.electionType.electionTypeId = 2 and model1.electionScope.state.stateId = :stateId and model1.electionYear < "+year+" ) " +
				" and model.electionYear <= (select max(model2.electionYear) from Election model2 where model2.isPartial is null  and model2.electionScope.electionType.electionTypeId = 2 and " +
				"  model2.electionScope.state.stateId = :stateId and model2.electionYear < "+year+" ) and model.electionYear < :date order by model.electionDate desc");
		query.setParameter("stateId", stateId);
		query.setParameter("elecType", IConstants.ELECTION_SUBTYPE_MAIN);
		query.setDate("date", date);
		return query.list();
	}


	@SuppressWarnings("unchecked")
	public List<Election> getElectionDetailsForElections(List<Long> electionIds){

		Query query = getSession().createQuery("select model from Election model where " +
				"model.electionId in (:electionIds)");

		query.setParameterList("electionIds", electionIds);

		return query.list();

	}

	public List getCountOfElectionYearsForAssembly(Long stateId,String electionType,String elecSubType){
		Object[] params = {stateId, electionType,elecSubType,"0"};
		return getHibernateTemplate().find("select count(model) from Election model " +
				"where model.electionScope.state.stateId = ? and model.electionScope.electionType.electionType = ? and model.elecSubtype = ? and (model.isPartial is null or model.isPartial = ?)", params);
	}
	/**
	 * This method is used for getting electionIds details based on year.
	 * @author srishailam
	 * @param Long electionYear
	 * @return List<Long>
	 * @date 20th April,2013
	 */
	public List<Long> getElectionDetailsByYear(String year){
		Query query = getSession().createQuery("select model.electionId from Election model where " +
				"model.electionYear in (:year)");		
		query.setParameter("year", year);		
		return query.list();
	}
	public List<Object[]> findMptcZptcElections(Long stateId)
	{
		return getHibernateTemplate().find("select model.electionId,model.elecSubtype,model.electionYear,model.electionScope.electionType.electionType from Election model where model.electionScope.electionType.electionTypeId in (3,4) and model.electionScope.state.stateId = ? ",stateId);
	}

	@SuppressWarnings("unchecked")
	public List<Long> getElectionIdsByElectionYearsCount(Long stateId,String electionType,String elecSubType,Long electionyearsCount){
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.electionId from Election model ");
		sb.append(" where model.electionScope.electionType.electionType = ? and model.elecSubtype = ? ");

		if(electionType.equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE)){
			sb.append(" and model.electionScope.state.stateId = ?");
		}
		sb.append("  and model.isPartial is null or model.isPartial = ? order by model.electionYear desc");

		Query queryObject = getSession().createQuery(sb.toString());
		queryObject.setString(0,electionType);
		queryObject.setString(1,elecSubType);

		if(electionType.equalsIgnoreCase(IConstants.ASSEMBLY_ELECTION_TYPE)){
			queryObject.setLong(2,stateId);
			queryObject.setString(3,"0");
		}
		else
			queryObject.setString(2,"0");
		queryObject.setFirstResult(0);
		queryObject.setMaxResults(electionyearsCount.intValue());
		return queryObject.list();
	}

	@SuppressWarnings("unchecked")
	public List<Long> getSortedElectionIds(List<Long> electionIdsList)
	{
		Query query = getSession().createQuery(" select model.electionId from Election model where model.electionId in (:electionIdsList) order by model.electionDate desc ");
		query.setParameterList("electionIdsList", electionIdsList);
		return query.list();
	}

	public Long getElectionId(String electionYear,Long elecetionType ,Long stateId)
	{
		Query query = getSession().createQuery("select model.electionId from Election model where " +
				" model.electionScope.electionType.electionTypeId = :elecetionType and " +
				" model.electionYear = :electionYear and " +
				" model.electionScope.state.stateId = :stateId and " +
				" model.elecSubtype = 'MAIN' ");
		query.setParameter("electionYear", electionYear);
		query.setParameter("elecetionType", elecetionType);
		query.setParameter("stateId", stateId);
		return (Long)query.uniqueResult();

	}

	public List<Long> getAllAssemblyMainElectionsIdsInAP(){
		Query query = getSession().createQuery(" select model.electionId from Election model where model.electionScope.electionScopeId = 2 and model.elecSubtype ='MAIN' ");

		return query.list();
	}
	public List<Object[]> getCandidateDetailsForMPTC(Long publicationDateId,Long electionScopeId,Long electionId,List<Long> districtIds)
	{

		Query query = getSession().createQuery(
				"select distinct c2.constituencyId,b.constituency.district.districtName, " +
						" b.constituency.name,b.tehsil.tehsilName,c2.name,ca.lastname,cd.candidateDetailsId," +
						" cr.nomination.party.shortName,cr.rank, " +
						" cd.casteState.caste.casteName,cd.educationalQualifications.qualification, " +
						" cd.howLongWorkingInParty,cd.mobileno " +
						" from Booth b,Constituency c2,Candidate ca,CandidateResult cr,CandidateDetails cd " +
						" where b.tehsil.tehsilId=c2.tehsil.tehsilId and " +
						" c2.constituencyId=cr.nomination.constituencyElection.constituency.constituencyId and " +
						" ca.candidateId=cr.nomination.candidate.candidateId and" +
						" ca.candidateId=cd.candidate.candidateId and " +
						" b.publicationDate.publicationDateId=:publicationDateId and c2.electionScope.electionScopeId=:electionScopeId " +
						" and cr.nomination.constituencyElection.election.electionId=:electionId " +
				" and b.constituency.district.districtId in(:districtIds) ");		

		query.setParameter("publicationDateId",publicationDateId);
		query.setParameter("electionScopeId",electionScopeId);
		query.setParameter("electionId",electionId);
		query.setParameterList("districtIds", districtIds);
		return query.list();
	}

	public List<Object[]> getCandidateDetailsForZPTC(Long publicationDateId,Long electionScopeId,Long electionId,List<Long> districtIds)
	{

		Query query = getSession().createQuery(
				"select distinct c2.constituencyId,b.constituency.district.districtName, " +
						" b.constituency.name,b.tehsil.tehsilName,c2.name,ca.lastname,cd.candidateDetailsId, " +
						" cr.nomination.party.shortName,cr.rank, " +
						" cd.casteState.caste.casteName,cd.educationalQualifications.qualification, " +
						" cd.howLongWorkingInParty,cd.mobileno " +
						" from Booth b,Constituency c2,Candidate ca,CandidateResult cr,CandidateDetails cd " +
						" where b.tehsil.tehsilId=c2.tehsil.tehsilId and " +
						" c2.constituencyId=cr.nomination.constituencyElection.constituency.constituencyId and " +
						" ca.candidateId=cr.nomination.candidate.candidateId and" +
						" ca.candidateId=cd.candidate.candidateId and " +
						" b.publicationDate.publicationDateId=:publicationDateId and c2.electionScope.electionScopeId=:electionScopeId " +
						" and cr.nomination.constituencyElection.election.electionId=:electionId " +
				" and b.constituency.district.districtId in(:districtIds) ");		

		query.setParameter("publicationDateId",publicationDateId);
		query.setParameter("electionScopeId",electionScopeId);
		query.setParameter("electionId",electionId);
		query.setParameterList("districtIds", districtIds);
		return query.list();
	}

	public List<Object[]> getCandidateDetailsForMunicipality(Long electionId,List<Long> districtIds)
	{  

		Query query = getSession().createQuery("select " +
				"cer.constituencyElection.constituency.constituencyId,cer.constituencyElection.constituency.localElectionBody.tehsil.district.districtName, " +
				"cer.constituencyElection.constituency.localElectionBody.tehsil.tehsilName," +
				"cer.constituencyElection.constituency.localElectionBody.name,cer.constituencyElection.constituency.name," +
				"cr.nomination.candidate.lastname,cd.candidateDetailsId,cr.nomination.party.shortName," +
				" cr.votesEarned,cr.rank, cd.casteState.caste.casteName," +
				" cd.educationalQualifications.qualification,cd.howLongWorkingInParty,cd.mobileno  " +
				"from CandidateResult cr,ConstituencyElectionResult cer,CandidateDetails cd " +
				"where cr.nomination.constituencyElection.constiElecId=cer.constituencyElection.constiElecId " +
				" and cr.nomination.candidate.candidateId=cd.candidate.candidateId" +
				" and cer.constituencyElection.election.electionId=:electionId " +
				" and cer.constituencyElection.constituency.localElectionBody.tehsil.district.districtId in(:districtIds)" );

		query.setParameter("electionId",electionId);
		query.setParameterList("districtIds", districtIds);
		return query.list();


	}
	public List<Object[]> getCandidateDetailsForCorporation(Long electionId,List<Long> districtIds)
	{  

		Query query = getSession().createQuery("select " +
				"cer.constituencyElection.constituency.constituencyId,cer.constituencyElection.constituency.localElectionBody.tehsil.district.districtName, " +
				"cer.constituencyElection.constituency.localElectionBody.tehsil.tehsilName," +
				"cer.constituencyElection.constituency.localElectionBody.name,cer.constituencyElection.constituency.name," +
				"cr.nomination.candidate.lastname,cd.candidateDetailsId,cr.nomination.party.shortName," +
				" cr.votesEarned,cr.rank, cd.casteState.caste.casteName," +
				" cd.educationalQualifications.qualification,cd.howLongWorkingInParty,cd.mobileno  " +
				"from CandidateResult cr,ConstituencyElectionResult cer,CandidateDetails cd " +
				"where cr.nomination.constituencyElection.constiElecId=cer.constituencyElection.constiElecId " +
				" and cr.nomination.candidate.candidateId=cd.candidate.candidateId" +
				" and cer.constituencyElection.election.electionId=:electionId " +
				" and cer.constituencyElection.constituency.localElectionBody.tehsil.district.districtId in (:districtIds) " );

		query.setParameter("electionId",electionId);
		query.setParameterList("districtIds", districtIds);
		return query.list();


	}


	public List<Long> getElectionDetailsByYearAndElectionType(String year,Long electionTypeId,Long stateId)
	{
		Query query = getSession().createQuery("select model.electionId from Election model where " +
				"model.electionYear ="+year+" and model.electionScope.electionType.electionTypeId = :electionTypeId and  model.electionScope.state.stateId = :stateId ");		

		query.setParameter("electionTypeId", electionTypeId);
		query.setParameter("stateId", stateId);
		return query.list();
	}


	public List findElectionYearsForElectionTypeAndStateId(Long electionType,Long stateId){
		if(electionType.longValue() == 1 )
		{
			Object[] params = {electionType};
			return getHibernateTemplate().find("select distinct model.electionId,model.electionYear,model.elecSubtype from Election model where model.electionScope.electionType.electionTypeId = ?  group by model.electionYear order by model.electionYear desc ",params);

		}
		else
		{
			Object[] params = {electionType,stateId};
			return getHibernateTemplate().find("select distinct model.electionId,model.electionYear,model.elecSubtype from Election model where model.electionScope.electionType.electionTypeId = ? and model.electionScope.state.stateId = ? group by model.electionYear order by model.electionYear desc ",params);

		}
	}

	public List<String> getElectionTypeByElectionId(Long electionId){
		Query query = getSession().createQuery(" select model.electionScope.electionType.electionType from Election model where model.electionId = :electionId ");
		query.setParameter("electionId", electionId);
		return query.list();
	}
	public List<String> getElectionYears(List<String> electionSubTypes){
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct election_year from election ");
		if(electionSubTypes != null && electionSubTypes.size() >0){
			sb.append(" where  sub_type in (:electionSubTypes) and election_year > 1982 ");
		}

		sb.append(" order by election_year desc ");
		Query query = getSession().createSQLQuery(sb.toString());
		if(electionSubTypes != null && electionSubTypes.size() >0){
			query.setParameterList("electionSubTypes", electionSubTypes);
		}
		return query.list();
	}

	public List<Object[]> getElectionDetailsConstituencyWise(List<Long> electionYears,Long locationTypeId,List<Long>locationValues,Long electionId,List<String> subTypes,List<Long> partyIds,List<Long> electionScopeIds,String type){

		StringBuilder sb = new StringBuilder();	
		sb.append(" SELECT ");
		sb.append(" e.election_scope_id as election_scope_id , c.constituency_id as locationId,  c.name as locationName,  e.election_id as election_id, ");
		sb.append(" et.election_type as election_type,   ");
		sb.append(" e.election_year as election_year ,n.party_id as party_id, '' as short_name , sum(cbr.votes_earned)  as sumCount ");
		sb.append(" from  ");
		sb.append(" nomination n , ");
		sb.append(" constituency_election ce, ");
		if(type == null)
			sb.append(" candidate_result cbr, ");
		else{
			electionScopeIds.clear();
			electionScopeIds.add(1L);
			sb.append(" candidate_booth_result cbr,booth b, booth_constituency_election bce ,  ");
		}
		sb.append(" election e , ");
		sb.append(" constituency c , ");
		sb.append(" election_scope es ,  ");
		sb.append(" election_type et ");
		sb.append(" where  ");
		sb.append(" e.election_scope_id = es.election_scope_id and  ");
		sb.append(" et.election_type_id = es.election_type_id and  ");
		
		if(type != null){
			sb.append(" bce.consti_elec_id = ce.consti_elec_id and b.booth_id = bce.booth_id and cbr.booth_constituency_election_id = bce.booth_constituency_election_id and ");
			sb.append(" (c.district_id BETWEEN 11 and 23 ) and  b.constituency_id = c.constituency_id and  ");
		}else{
			sb.append(" ce.constituency_id = c.constituency_id and  ");
		}
		
		sb.append(" ce.election_id = e.election_id and  ");
		sb.append(" n.nomination_id = cbr.nomination_id and "); 
		sb.append(" n.consti_elec_id = ce.consti_elec_id and  ");
		sb.append(" e.election_year in  (:electionYears) and  ");
		sb.append(" e.sub_type in  (:subTypes) and  e.election_scope_id  in (:electionScopeIds)   ");
		if(partyIds != null && partyIds.size() > 0)
		sb.append(" and n.party_id in  (:partyIds) ");
		
		if (locationTypeId != null && locationValues != null && locationValues.size()>0) {
			if (locationTypeId.longValue() == 3L) 
				sb.append(" and  c.district_id  in (:locationValues)");
			else if (locationTypeId.longValue()==10L)
				sb.append(" and  c.constituency_id  in (:locationValues)");
		}

		sb.append("group by e.election_scope_id,e.election_id,et.election_type,e.election_scope_id,e.election_year,c.constituency_id,n.party_id order BY e.election_scope_id,e.election_id,et.election_type,e.election_scope_id,e.election_year,c.constituency_id,n.party_id");
		Query query = getSession().createSQLQuery((sb.toString()))
				.addScalar("election_scope_id",Hibernate.LONG)
				.addScalar("locationId",Hibernate.LONG)
				.addScalar("locationName",Hibernate.STRING)
				.addScalar("election_id",Hibernate.LONG)
				.addScalar("election_type",Hibernate.STRING)
				.addScalar("election_year",Hibernate.STRING)
				.addScalar("party_id",Hibernate.LONG)
				.addScalar("short_name",Hibernate.STRING)
				.addScalar("sumCount",Hibernate.LONG);

		if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0){
			query.setParameterList("locationValues", locationValues);
		} 

		if(electionId !=  null && electionId.longValue() > 0){
			//query.setParameter("electionId", electionId);
		}
		if(electionYears!=  null){
			query.setParameterList("electionYears", electionYears);
		}
		if(subTypes !=  null){
			query.setParameterList("subTypes", subTypes);
		}
		if(partyIds != null && partyIds.size() > 0){
			query.setParameterList("partyIds", partyIds);
		} 
		if(electionScopeIds != null && electionScopeIds.size()>0){
        	query.setParameterList("electionScopeIds", electionScopeIds);
        }
		return query.list();

	}

	@SuppressWarnings("unused")
	public List<Object[]> getElectionDetailsDistrictWise(List<Long> electionYears,Long locationTypeId,List<Long>locationValues,Long electionId,List<String> subTypes,List<Long> partyIds,List<Long> electionScopeIds,String type){
		StringBuilder sb = new StringBuilder();	
		List<Long> scopeIdsList = new ArrayList<Long>(0);
		scopeIdsList.addAll(electionScopeIds);
		sb.append(" SELECT ");
		sb.append(" e.election_scope_id as election_scope_id , ");
		if(type == null)
			sb.append(" d.district_id as locationId, d.district_name as locationName,  e.election_id as election_id, ");
		else if(type.trim().equalsIgnoreCase("parliament") || type.trim().equalsIgnoreCase("AC_WISE_PA_RESULTS") )
			sb.append(" c.constituency_id as locationId,  c.name as locationName,  e.election_id as election_id, ");
		sb.append(" et.election_type as election_type,   ");
		sb.append(" e.election_year as election_year ,n.party_id as party_id, '' as short_name , sum(cbr.votes_earned)  as sumCount ");
		sb.append(" from  ");
		sb.append(" nomination n , ");
		sb.append(" constituency_election ce, ");
		
		if(type != null && type.trim().equalsIgnoreCase("AC_WISE_PA_RESULTS")){
			scopeIdsList.clear();
			scopeIdsList.add(1L);
			sb.append(" booth_constituency_election bce , ");
			sb.append(" booth b, ");
			sb.append(" candidate_booth_result cbr, ");
			//return null;
		}else{
			sb.append(" candidate_result cbr, ");
		}
		
		sb.append(" election e , ");
		sb.append(" constituency c , ");
		if(type == null)
			sb.append(" district d , ");
		
		sb.append(" election_scope es ,  ");
		sb.append(" election_type et ");
		sb.append(" where  ");
		sb.append(" e.election_scope_id = es.election_scope_id and  ");
		sb.append(" et.election_type_id = es.election_type_id and  ");
		if(type == null){
			sb.append(" c.district_id = d.district_id and  ");
			sb.append(" (c.district_id BETWEEN 11 and 23 ) and  ");
			sb.append(" ce.constituency_id = c.constituency_id and  ");
		}else if(type.trim().equalsIgnoreCase("parliament")){
			
			sb.append(" ce.constituency_id in ("+IConstants.AP_PARLIAMENT_IDS_LIST_STR+") and ");
			sb.append(" ce.constituency_id = c.constituency_id and  ");
			//return null;
		}else if(type.trim().equalsIgnoreCase("AC_WISE_PA_RESULTS")){
			sb.append(" bce.consti_elec_id = ce.consti_elec_id and b.booth_id = bce.booth_id and cbr.booth_constituency_election_id = bce.booth_constituency_election_id and ");
			sb.append(" (c.district_id BETWEEN 11 and 23 ) and  b.constituency_id = c.constituency_id and  ");
		}
		
		sb.append(" ce.election_id = e.election_id and  ");
		sb.append(" n.nomination_id = cbr.nomination_id and "); 
		sb.append(" n.consti_elec_id = ce.consti_elec_id and  ");
		sb.append(" e.election_year in  (:electionYears) and  ");
		sb.append(" e.sub_type in  (:subTypes) and  e.election_scope_id  in (:electionScopeIds) and  ");
		sb.append(" c.state_id = :locationValues ");
		
		if(partyIds != null && partyIds.size() > 0)
			sb.append(" and n.party_id in  (:partyIds) ");
		
		sb.append(" GROUP BY  ");
		sb.append(" locationId,ce.election_id,n.party_id  ");

		Query query = getSession().createSQLQuery((sb.toString()))
				.addScalar("election_scope_id",Hibernate.LONG)
				.addScalar("locationId",Hibernate.LONG)
				.addScalar("locationName",Hibernate.STRING)
				.addScalar("election_id",Hibernate.LONG)
				.addScalar("election_type",Hibernate.STRING)
				.addScalar("election_year",Hibernate.STRING)
				.addScalar("party_id",Hibernate.LONG)
				.addScalar("short_name",Hibernate.STRING)
				.addScalar("sumCount",Hibernate.LONG);

		if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0){
			query.setParameterList("locationValues", locationValues);
		} 

		if(electionId !=  null && electionId.longValue() > 0){
			//query.setParameter("electionId", electionId);
		}
		if(electionYears !=  null){
			query.setParameterList("electionYears", electionYears);
		}
		if(subTypes !=  null && subTypes.size()>0 ){
			query.setParameterList("subTypes", subTypes);
		}
		if(partyIds != null && partyIds.size() > 0){
			query.setParameterList("partyIds", partyIds);
		} 
		if(scopeIdsList != null && scopeIdsList.size()>0){
        	query.setParameterList("electionScopeIds", scopeIdsList);
        }
		return query.list();

	}  
	public List<Object[]> getElectionDetailsMandalWise(List<Long> electionYears,Long locationTypeId,List<Long>locationValues,Long electionId,List<String> subTypes,List<Long> partyIds,List<Long> electionScopeIds,boolean islocalbody){
		StringBuilder sb = new StringBuilder();	
		
		sb.append(" SELECT ");
		sb.append(" e.election_scope_id as election_scope_id" );
		if(islocalbody){
			sb.append(" ,leb.local_election_body_id AS locationId,concat(leb.name,'-',et2.election_type) AS locationName");
		}else{
			sb.append(" ,t.tehsil_id as locationId ,t.tehsil_name as locationName ");
		}
		sb.append(" ,e.election_id as election_id, ");
		sb.append(" et.election_type as election_type,   ");
		sb.append(" e.election_year as election_year ,n.party_id as party_id, '' as short_name , sum(cbr.votes_earned)  as sumCount ");
		sb.append(" from  ");
		sb.append(" nomination n , ");
		sb.append(" constituency_election ce, ");
		sb.append(" candidate_booth_result cbr, ");
		sb.append(" booth_constituency_election bce , ");
		sb.append(" booth b, ");
		sb.append(" election e , ");
		sb.append(" constituency c , ");
		sb.append(" election_scope es ,  ");
		sb.append(" election_type et," );
		if(islocalbody){
			sb.append("local_election_body leb,election_type et2");
		}else{
			sb.append(" tehsil t  ");
		}
		sb.append(" where ");
		if(islocalbody){
			sb.append(" b.local_election_body_id = leb.local_election_body_id AND et2.election_type_id=leb.election_type_id  AND b.local_election_body_id IS Not NULL AND");
		}else{
			sb.append(" b.tehsil_id = t.tehsil_id and b.local_election_body_id is null AND ");
		}
		sb.append(" e.election_scope_id = es.election_scope_id and  ");
		sb.append(" et.election_type_id = es.election_type_id and  ");
		sb.append(" b.constituency_id = c.constituency_id and  ");
		sb.append(" (c.district_id BETWEEN 11 and 23 ) and  ");
		sb.append(" ce.election_id = e.election_id and  ");
		sb.append(" n.nomination_id = cbr.nomination_id and "); 
		sb.append(" cbr.booth_constituency_election_id = bce.booth_constituency_election_id and  ");
		sb.append(" bce.booth_id = b.booth_id and  ");
		sb.append(" n.consti_elec_id = ce.consti_elec_id and  ");
		sb.append(" ce.consti_elec_id = bce.consti_elec_id and  ");
		sb.append(" e.election_year in  (:electionYears) and  ");
		sb.append(" e.sub_type in  (:subTypes) and  e.election_scope_id  in (:electionScopeIds)   ");
		if(partyIds != null && partyIds.size() > 0)
		sb.append(" and n.party_id in  (:partyIds) ");
		
		if (locationTypeId != null && locationValues != null && locationValues.size()>0) {
			if (locationTypeId == 4L) {
				sb.append(" and  c.constituency_id  in (:locationValues) ");
			}
		}
		if(islocalbody){
			sb.append(" group by leb.local_election_body_id,e.election_scope_id,e.election_id,et.election_type," +
					"e.election_year,n.party_id ");
		}else{
			sb.append(" group by t.tehsil_id,e.election_scope_id,e.election_id,et.election_type," +
					"e.election_year,n.party_id ");
		}
		
		Query query = getSession().createSQLQuery((sb.toString()))
				.addScalar("election_scope_id",Hibernate.LONG)
				.addScalar("locationId",Hibernate.LONG)
				.addScalar("locationName",Hibernate.STRING)
				.addScalar("election_id",Hibernate.LONG)
				.addScalar("election_type",Hibernate.STRING)
				.addScalar("election_year",Hibernate.STRING)
				.addScalar("party_id",Hibernate.LONG)
				.addScalar("short_name",Hibernate.STRING)
				.addScalar("sumCount",Hibernate.LONG);

		if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0){
			query.setParameterList("locationValues", locationValues);
		} 
		if(electionId !=  null && electionId.longValue() > 0){
			//query.setParameter("electionId", electionId);
		}
		if(electionYears !=  null){
			query.setParameterList("electionYears", electionYears);
		}
		if(subTypes !=  null){
			query.setParameterList("subTypes", subTypes);
		}
		if(partyIds != null && partyIds.size() > 0){
			query.setParameterList("partyIds", partyIds);
		} 
        if(electionScopeIds != null && electionScopeIds.size()>0){
        	query.setParameterList("electionScopeIds", electionScopeIds);
        }
		return query.list();

	}  
	public List<Object[]> getElectionDetailsPanchayatWise(List<Long> electionYears,Long locationTypeId,List<Long>locationValues,Long electionId,List<String> subTypes,List<Long> partyIds,List<Long> electionScopeIds){
		StringBuilder sb = new StringBuilder();	
		sb.append(" SELECT ");
		sb.append(" e.election_scope_id as election_scope_id ,  p1.panchayat_id  as locationId ,p1.panchayat_name as locationName,  e.election_id as election_id, ");
		sb.append(" et.election_type as election_type,   ");
		sb.append(" e.election_year as election_year ,n.party_id as party_id, '' as short_name , sum(cbr.votes_earned)  as sumCount ");
		sb.append(" from  ");
		sb.append(" nomination n , ");
		sb.append(" constituency_election ce, ");
		sb.append(" candidate_booth_result cbr, ");
		sb.append(" booth_constituency_election bce , ");
		sb.append(" booth b, ");
		sb.append(" election e , ");
		sb.append(" constituency c , ");
		sb.append(" election_scope es ,  ");
		sb.append(" panchayat p1, ");
		sb.append(" election_type et ");
		sb.append(" where  ");
		sb.append(" b.panchayat_id  = p1.panchayat_id and  ");
		sb.append(" e.election_scope_id = es.election_scope_id and  ");
		sb.append(" et.election_type_id = es.election_type_id and  ");
		sb.append(" b.constituency_id = c.constituency_id and  ");
		sb.append(" (c.district_id BETWEEN 11 and 23 ) and  ");
		sb.append(" ce.election_id = e.election_id and  ");
		sb.append(" n.nomination_id = cbr.nomination_id and "); 
		sb.append(" cbr.booth_constituency_election_id = bce.booth_constituency_election_id and  ");
		sb.append(" bce.booth_id = b.booth_id and  ");
		sb.append(" n.consti_elec_id = ce.consti_elec_id and  ");
		sb.append(" ce.consti_elec_id = bce.consti_elec_id and  ");
		sb.append(" e.election_year in  (:electionYears) and  ");
		sb.append(" e.sub_type in  (:subTypes) and  e.election_scope_id  in (:electionScopeIds)   ");
		if(partyIds != null && partyIds.size() > 0)
		sb.append(" and n.party_id in  (:partyIds) ");
		
		if (locationTypeId != null && locationValues != null && locationValues.size()>0) {
			if (locationTypeId == 5L) {
				sb.append(" and  b.tehsil_id  in (:locationValues) ");
			} 		   
		}
		sb.append(" GROUP BY  ");
		sb.append(" p1.panchayat_id,e.election_scope_id,e.election_id,et.election_type,e.election_scope_id,e.election_year,n.party_id  ");
		Query query = getSession().createSQLQuery(sb.toString())
				.addScalar("election_scope_id",Hibernate.LONG)
				.addScalar("locationId",Hibernate.LONG)
				.addScalar("locationName",Hibernate.STRING)
				.addScalar("election_id",Hibernate.LONG)
				.addScalar("election_type",Hibernate.STRING)
				.addScalar("election_year",Hibernate.STRING)
				.addScalar("party_id",Hibernate.LONG)
				.addScalar("short_name",Hibernate.STRING)
				.addScalar("sumCount",Hibernate.LONG);
		if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0){
			query.setParameterList("locationValues", locationValues);
		} 
		if(electionId !=  null && electionId.longValue() > 0){
			//query.setParameter("electionId", electionId);
		}
		if(electionYears !=  null){
			query.setParameterList("electionYears", electionYears);
		}
		if(subTypes !=  null && subTypes.size() > 0 ){
			query.setParameterList("subTypes", subTypes);
		}
		if(partyIds != null && partyIds.size() > 0){
			query.setParameterList("partyIds", partyIds);
		} 
		if(electionScopeIds != null && electionScopeIds.size()>0){
        	query.setParameterList("electionScopeIds", electionScopeIds);
        }
		return query.list();
	}  
	public List<Object[]> getElectionDetailsMuncipalityWise(List<Long> electionYears,Long locationTypeId,List<Long>locationValues,Long electionId,List<String> subTypes,List<Long> partyIds,List<Long> electionScopeIds){
		StringBuilder sb = new StringBuilder();	
		sb.append(" SELECT ");
		sb.append(" e.election_scope_id as election_scope_id ,  leb.local_election_body_id as locationId , concat(leb.name,' ',et1.election_type) as locationName, e.election_id as election_id, ");
		sb.append(" et.election_type as election_type,   ");
		sb.append(" e.election_year as election_year ,n.party_id as party_id, '' as short_name , sum(cbr.votes_earned)  as sumCount ");
		sb.append(" from  ");
		sb.append(" nomination n , ");
		sb.append(" constituency_election ce, ");
		sb.append(" candidate_booth_result cbr, ");
		sb.append(" booth_constituency_election bce , ");
		sb.append(" booth b, ");
		sb.append(" election e , ");
		sb.append(" constituency c , ");
		sb.append(" election_scope es ,  ");
		sb.append(" election_type et, local_election_body leb,election_type et1  ");
		sb.append(" where   leb.election_type_id  = et1.election_type_id and b.local_election_body_id = leb.local_election_body_id and b.local_election_body_id is not null AND ");
		sb.append(" e.election_scope_id = es.election_scope_id and  ");
		sb.append(" et.election_type_id = es.election_type_id and  ");
		sb.append(" b.constituency_id = c.constituency_id and  ");
		sb.append(" (c.district_id BETWEEN 11 and 23 ) and  ");
		sb.append(" ce.election_id = e.election_id and  ");
		sb.append(" n.nomination_id = cbr.nomination_id and "); 
		sb.append(" cbr.booth_constituency_election_id = bce.booth_constituency_election_id and  ");
		sb.append(" bce.booth_id = b.booth_id and  ");
		sb.append(" n.consti_elec_id = ce.consti_elec_id and  ");
		sb.append(" ce.consti_elec_id = bce.consti_elec_id and  ");
		sb.append(" e.election_year in  (:electionYears) and  ");
		sb.append(" e.sub_type in  (:subTypes) and  e.election_scope_id  in (:electionScopeIds)   ");
		if(partyIds != null && partyIds.size() > 0)
		sb.append(" and n.party_id in  (:partyIds) ");
		
		if (locationTypeId != null && locationValues != null && locationValues.size()>0) {
			if (locationTypeId == 4L) {
				sb.append(" and  c.constituency_id  in (:locationValues) ");
			} 		   
		}
		sb.append("  group by leb.local_election_body_id,e.election_scope_id,e.election_id,et.election_type,e.election_year,n.party_id ");
		Query query = getSession().createSQLQuery((sb.toString()))
				.addScalar("election_scope_id",Hibernate.LONG)
				.addScalar("locationId",Hibernate.LONG)
				.addScalar("locationName",Hibernate.STRING)
				.addScalar("election_id",Hibernate.LONG)
				.addScalar("election_type",Hibernate.STRING)
				.addScalar("election_year",Hibernate.STRING)
				.addScalar("party_id",Hibernate.LONG)
				.addScalar("short_name",Hibernate.STRING)
				.addScalar("sumCount",Hibernate.LONG);
		if(locationTypeId != null && locationTypeId.longValue() > 0l && locationValues != null && locationValues.size() > 0){
			query.setParameterList("locationValues", locationValues);
		} 
		if(electionId !=  null && electionId.longValue() > 0){
			//query.setParameter("electionId", electionId);
		}
		if(electionYears !=  null){
			query.setParameterList("electionYears", electionYears);
		}
		if(subTypes !=  null && subTypes.size() > 0){
			query.setParameterList("subTypes", subTypes);
		}
		if(partyIds != null && partyIds.size() > 0){
			query.setParameterList("partyIds", partyIds);
		} 
		if(electionScopeIds != null && electionScopeIds.size()>0){
        	query.setParameterList("electionScopeIds", electionScopeIds);
        }
		return null;
	}

	@Override
	public List<Long> getElectionDetailsByYearAndElectionTypeForLocation(String year,Long electionTypeId,Long stateId)
	{
		Query query = null;
		if(electionTypeId == 1l){
			
			query = getSession().createQuery("select model.electionId from Election model where " +
					"model.electionYear ="+year+" and model.electionScope.electionType.electionTypeId = :electionTypeId ");	
		}else{

			query = getSession().createQuery("select model.electionId from Election model where " +
					"model.electionYear ="+year+" and model.electionScope.electionType.electionTypeId = :electionTypeId and  model.electionScope.state.stateId = :stateId ");		
			query.setParameter("stateId", stateId);
		}

		query.setParameter("electionTypeId", electionTypeId);
		
		return query.list();
	}
	 
	 
	public List<Object[]> getElectionWiseYears(Long stateId,String electionType)
	{
		
		Object[] params = {stateId,electionType,"1",IConstants.ELECTION_SUBTYPE_MAIN};
		return getHibernateTemplate().find("select model.electionId,model.electionYear from Election model where (model.electionScope.state.stateId = ? or model.electionScope.state.stateId is null) and " +
				" model.electionScope.electionType.electionType = ? and model.isPartial = ? and model.elecSubtype = ?  order by model.electionYear desc",params);
	}

	@Override
	public List<Object[]> getElectionDetailsForCrossVoting(List<Long> electionYears, Long locationTypeId,List<Long> locationValues, Long electionId, List<String> subTypes,
			List<Long> partyIds, List<Long> electionScopeIds,String searchType) {
		
		StringBuilder sb = new StringBuilder();
		
		if(searchType != null && searchType.equalsIgnoreCase("parliament") && electionScopeIds != null && electionScopeIds.contains(1L) && 
				 (locationTypeId.longValue() == 2l  || locationTypeId.longValue() == 3l || locationTypeId.longValue() == 4l || locationTypeId.longValue() == 10l )){
			
			
				sb.append(" SELECT ");
				sb.append(" e.election_scope_id AS election_scope_id, "); 
				sb.append(" c.constituency_id AS locationId,  ");
				sb.append(" c.name as locationName,  ");
				sb.append(" e.election_id AS election_id, ");
				sb.append(" et.election_type AS election_type, "); 
				sb.append(" e.election_year AS election_year,  ");
				sb.append(" n.party_id AS party_id,  ");
				sb.append(" '' AS short_name, ");
				sb.append(" n.candidate_id AS candidateId, ");  
				sb.append(" SUM(cr.votes_earned) AS sumCount " );
			sb.append(" from nomination n, ");
				sb.append("constituency_election ce, ");  
				sb.append("election e,   ");
				sb.append("constituency c,  ");
				sb.append("candidate_result cr  , ");
				sb.append("election_scope es ,  ");
				sb.append("election_type et  WHERE " +
					" e.election_scope_id = es.election_scope_id and "+
					" et.election_type_id = es.election_type_id and " +
					" e.election_id = ce.election_id and  "+
					" ce.consti_elec_id = n.consti_elec_id and  "+
					" cr.nomination_id = n.nomination_id and "+
					" ce.constituency_id = c.constituency_id AND  e.sub_type IN (:subTypes) ");
			if(electionYears != null && electionYears.size()>0)
					sb.append(" AND e.election_year IN (:electionYears)");
			if(electionScopeIds != null && electionScopeIds.size()>0)		
					sb.append(" AND e.election_scope_id IN (:electionScopeIds)");
			if(partyIds!=null && partyIds.size()>0)
					sb.append(" AND n.party_id IN (:partyIds)");
			if(locationTypeId.longValue() == 3L || locationTypeId.longValue() == 10L || locationTypeId.longValue() == 2L || locationTypeId.longValue() == 4l)
				sb.append(" and c.constituency_id in (:locationValues) ");
			
			sb.append("  GROUP BY c.constituency_id , ce.election_id , n.party_id ");
		
			Query query = getSession().createSQLQuery(sb.toString())
					.addScalar("election_scope_id",Hibernate.LONG)
					.addScalar("locationId",Hibernate.LONG)
					.addScalar("locationName",Hibernate.STRING)
					.addScalar("election_id",Hibernate.LONG)
					.addScalar("election_type",Hibernate.STRING)
					.addScalar("election_year",Hibernate.STRING)
					.addScalar("party_id",Hibernate.LONG)
					.addScalar("short_name",Hibernate.STRING)
					.addScalar("candidateId",Hibernate.LONG)
					.addScalar("sumCount",Hibernate.LONG);
			
			query.setParameterList("subTypes",subTypes);
			if(electionYears != null && electionYears.size()>0)
				query.setParameterList("electionYears",electionYears);
			if(electionScopeIds != null && electionScopeIds.size()>0)		
				query.setParameterList("electionScopeIds",electionScopeIds);
			if(partyIds!=null && partyIds.size()>0)
				query.setParameterList("partyIds",partyIds);
			if(locationTypeId != null && (locationTypeId.longValue() == 3L || locationTypeId.longValue() == 10L || locationTypeId.longValue() == 2L || locationTypeId.longValue() == 4l))
				query.setParameterList("locationValues",locationValues);
			return query.list();
		}else if(searchType != null &&  (locationTypeId.longValue() == 5l  || locationTypeId.longValue() == 6l || locationTypeId.longValue() == 7l )){
			sb.append(" SELECT e.election_scope_id AS election_scope_id ");	
			if(locationTypeId.longValue() == 5l )
				sb.append(" ,c.tehsil_id AS locationId, c.tehsil_name as locationName ");
			else if(locationTypeId.longValue() == 6l )
				sb.append(" ,c.panchayat_id AS locationId , c.panchayat_name  as locationName ");
			else if(locationTypeId.longValue() == 7l )
				sb.append(", c.local_election_body_id AS locationId , c.name  as locationName ");
			
			sb.append(", e.election_id AS election_id," +
					" et.election_type AS election_type, e.election_year AS election_year, n.party_id AS party_id, '' AS short_name,n.candidate_id AS candidateId, " +
					" SUM(cbr.votes_earned) AS sumCount FROM " );
			sb.append(" nomination n, constituency_election ce, candidate_booth_result cbr, booth_constituency_election bce, booth b, election e, " +
					"   election_scope es, election_type et " );
			if(locationTypeId.longValue() == 5l )
				sb.append(" ,tehsil c ");
			else if(locationTypeId.longValue() == 6l )
				sb.append(" ,panchayat c ");
			else if(locationTypeId.longValue() == 7l )
				sb.append(" ,local_election_body c ");
			sb.append(" WHERE  ");
			if(locationTypeId.longValue() == 5l )
				sb.append(" b.tehsil_id = c.tehsil_id and b.local_election_body_id is null and  ");
			else if(locationTypeId.longValue() == 6l )
				sb.append(" b.panchayat_id = c.panchayat_id and b.local_election_body_id is null and  ");
			else if(locationTypeId.longValue() == 7l )
				sb.append(" b.local_election_body_id = c.local_election_body_id and b.local_election_body_id is not null and ");
			
			sb.append(" e.election_scope_id = es.election_scope_id AND et.election_type_id = es.election_type_id AND " +
					"  ce.election_id = e.election_id" +
					" AND n.nomination_id = cbr.nomination_id AND cbr.booth_constituency_election_id = bce.booth_constituency_election_id" +
					" AND bce.booth_id = b.booth_id AND n.consti_elec_id = ce.consti_elec_id AND ce.consti_elec_id = bce.consti_elec_id AND e.sub_type IN (:subTypes) ");
			if(electionYears != null && electionYears.size()>0)
					sb.append(" AND e.election_year IN (:electionYears)");
			if(electionScopeIds != null && electionScopeIds.size()>0)		
					sb.append(" AND e.election_scope_id IN (:electionScopeIds)");
			if(partyIds!=null && partyIds.size()>0)
					sb.append(" AND n.party_id IN (:partyIds)");
			if(locationTypeId.longValue() == 5L )
				sb.append(" and b.tehsil_id in (:locationValues)  GROUP BY c.tehsil_id , ce.election_id , n.party_id ");		
			else if(locationTypeId.longValue() == 6l )
				sb.append(" and b.panchayat_id in (:locationValues) GROUP BY c.panchayat_id , ce.election_id , n.party_id ");
			else if(locationTypeId.longValue() == 7l )
				sb.append(" and b.local_election_body_id in (:locationValues) GROUP BY c.local_election_body_id , ce.election_id , n.party_id  ");
			Query query = getSession().createSQLQuery(sb.toString())
					.addScalar("election_scope_id",Hibernate.LONG)
					.addScalar("locationId",Hibernate.LONG)
					.addScalar("locationName",Hibernate.STRING)
					.addScalar("election_id",Hibernate.LONG)
					.addScalar("election_type",Hibernate.STRING)
					.addScalar("election_year",Hibernate.STRING)
					.addScalar("party_id",Hibernate.LONG)
					.addScalar("short_name",Hibernate.STRING)
					.addScalar("candidateId",Hibernate.LONG)
					.addScalar("sumCount",Hibernate.LONG);
			
			query.setParameterList("subTypes",subTypes);
			if(electionYears != null && electionYears.size()>0)
				query.setParameterList("electionYears",electionYears);
			if(electionScopeIds != null && electionScopeIds.size()>0)		
				query.setParameterList("electionScopeIds",electionScopeIds);
			if(partyIds!=null && partyIds.size()>0)
				query.setParameterList("partyIds",partyIds);
			if(locationValues != null && locationValues.size()>0)
				query.setParameterList("locationValues",locationValues);
			return query.list();
		}else if(searchType != null && searchType.equalsIgnoreCase("assembly") && electionScopeIds != null && electionScopeIds.contains(1L) && 
				 (locationTypeId.longValue() == 2l  || locationTypeId.longValue() == 3l || locationTypeId.longValue() == 4l || locationTypeId.longValue() == 10l )){
			sb.append(" SELECT e.election_scope_id AS election_scope_id, c.constituency_id AS locationId, c.name as locationName, e.election_id AS election_id," +
					" et.election_type AS election_type, e.election_year AS election_year, n.party_id AS party_id, '' AS short_name,n.candidate_id AS candidateId, " +
					" SUM(cbr.votes_earned) AS sumCount FROM " );
			sb.append(" nomination n, constituency_election ce, candidate_booth_result cbr, booth_constituency_election bce, booth b, election e, " +
					" constituency c, district d, election_scope es, election_type et WHERE" +
					" e.election_scope_id = es.election_scope_id AND et.election_type_id = es.election_type_id AND c.district_id = d.district_id" +
					" AND b.constituency_id = c.constituency_id AND (c.district_id BETWEEN 11 AND 23) AND ce.election_id = e.election_id" +
					" AND n.nomination_id = cbr.nomination_id AND cbr.booth_constituency_election_id = bce.booth_constituency_election_id" +
					" AND bce.booth_id = b.booth_id AND n.consti_elec_id = ce.consti_elec_id AND ce.consti_elec_id = bce.consti_elec_id AND e.sub_type IN (:subTypes) ");
			if(electionYears != null && electionYears.size()>0)
					sb.append(" AND e.election_year IN (:electionYears)");
			if(electionScopeIds != null && electionScopeIds.size()>0)		
					sb.append(" AND e.election_scope_id IN (:electionScopeIds)");
			if(partyIds!=null && partyIds.size()>0)
					sb.append(" AND n.party_id IN (:partyIds)");
			if(locationTypeId.longValue() == 10L || locationTypeId.longValue() == 4l || locationTypeId.longValue() == 3l)
				sb.append(" and c.constituency_id in (:locationValues) ");
			
			sb.append("  GROUP BY c.constituency_id , ce.election_id , n.party_id ");
		
			Query query = getSession().createSQLQuery(sb.toString())
					.addScalar("election_scope_id",Hibernate.LONG)
					.addScalar("locationId",Hibernate.LONG)
					.addScalar("locationName",Hibernate.STRING)
					.addScalar("election_id",Hibernate.LONG)
					.addScalar("election_type",Hibernate.STRING)
					.addScalar("election_year",Hibernate.STRING)
					.addScalar("party_id",Hibernate.LONG)
					.addScalar("short_name",Hibernate.STRING)
					.addScalar("candidateId",Hibernate.LONG)
					.addScalar("sumCount",Hibernate.LONG);
			
			query.setParameterList("subTypes",subTypes);
			if(electionYears != null && electionYears.size()>0)
				query.setParameterList("electionYears",electionYears);
			if(electionScopeIds != null && electionScopeIds.size()>0)		
				query.setParameterList("electionScopeIds",electionScopeIds);
			if(partyIds!=null && partyIds.size()>0)
				query.setParameterList("partyIds",partyIds);
			if(locationTypeId != null && ( locationTypeId.longValue() == 10L || locationTypeId.longValue() == 4l || locationTypeId.longValue() == 3l ))
				query.setParameterList("locationValues",locationValues);
			return query.list();
		}
		else{
			sb.append( " SELECT e.election_scope_id AS election_scope_id, c.constituency_id AS locationId, c.name as locationName, e.election_id AS election_id, "); 
			sb.append( " et.election_type AS election_type, e.election_year AS election_year, n.party_id AS party_id, p.short_name AS short_name,n.candidate_id AS candidateId, ");
			sb.append( " SUM(cbr.votes_earned) AS sumCount ");
			sb.append( " FROM  ");
			sb.append( " nomination n, constituency_election ce, candidate_result cbr,  election e,  constituency c,  ");
			sb.append( " election_scope es, election_type et, party p  ");
			sb.append( " WHERE  ");
			sb.append( " e.election_scope_id = es.election_scope_id AND  ");
			sb.append( " et.election_type_id = es.election_type_id AND  ");
			sb.append( " ce.constituency_id = c.constituency_id AND  ");
			sb.append( " ce.election_id = e.election_id AND  ");
			sb.append( " n.nomination_id = cbr.nomination_id AND ");
			sb.append( " p.party_id=n.party_id AND  ");
			sb.append( " n.consti_elec_id = ce.consti_elec_id AND ");
			sb.append( " e.sub_type IN  (:subTypes)  AND  ");
			if(electionYears != null && electionYears.size()>0)
				sb.append( " e.election_year IN (:electionYears) AND  ");
			if(electionScopeIds != null && electionScopeIds.size()>0)		
				sb.append( " e.election_scope_id IN (:electionScopeIds) ");
			if(partyIds!=null && partyIds.size()>0)
				sb.append( " and n.party_id in (:partyIds)  ");
			if(electionScopeIds.contains(2L)){
				sb.append( " and ( c.district_id BETWEEN 11 and 23 )    ");
			}if(locationValues != null && locationValues.size()>0){
				sb.append( " and ce.constituency_id in (:locationValues) ");
			}
			sb.append( " GROUP BY  ");
			sb.append( " c.constituency_id , ");
			sb.append( " ce.election_id ,  ");
			sb.append( " n.party_id  ");
			
			Query query = getSession().createSQLQuery(sb.toString())
					.addScalar("election_scope_id",Hibernate.LONG)
					.addScalar("locationId",Hibernate.LONG)
					.addScalar("locationName",Hibernate.STRING)
					.addScalar("election_id",Hibernate.LONG)
					.addScalar("election_type",Hibernate.STRING)
					.addScalar("election_year",Hibernate.STRING)
					.addScalar("party_id",Hibernate.LONG)
					.addScalar("short_name",Hibernate.STRING)
					.addScalar("candidateId",Hibernate.LONG)
					.addScalar("sumCount",Hibernate.LONG);
			
			query.setParameterList("subTypes",subTypes);
			if(electionYears != null && electionYears.size()>0)
				query.setParameterList("electionYears",electionYears);
			if(electionScopeIds != null && electionScopeIds.size()>0)		
				query.setParameterList("electionScopeIds",electionScopeIds);
			if(partyIds!=null && partyIds.size()>0)
				query.setParameterList("partyIds",partyIds);
			if(locationValues != null && locationValues.size()>1)
				query.setParameterList("locationValues",locationValues);
			return query.list();
		}
		
	}

	@Override
	public List<Object[]> getMarginVotesForCrossVoting(Long locationTypeId ,List<Long> locationValues,List<Long> electionScopeIds,List<String> subTypes,List<Long> electionYear,boolean isParliment) {
		
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select e.election_scope_id as electionScopeId, CER.valid_votes as validVotes,CER.voting_percentage as votingPecentage," +
				"CR.margin_votes as marginVotes,CR.margin_votes_percentage as marginVotesPercentage, CR.rank as rank,CR.votes_earned as voteEarned," +
				"CR.votes_percentage as votesPecentage, N.party_id as partyId,c.constituency_id as locationId,c.name as locationName" +
				" from constituency_election CE, constituency_election_result CER, candidate_result CR, nomination N,constituency c, election e," +
				" parliament_assembly pa, constituency ac " +
				" where CE.consti_elec_id=CER.consti_elec_id AND CE.consti_elec_id=N.consti_elec_id AND N.nomination_id=CR.nomination_id and" +
				" c.constituency_id=CE.constituency_id AND e.election_id=CE.election_id and pa.parliament_id=c.constituency_id and " +
				" pa.assembly_id=ac.constituency_id ");
		if(locationTypeId != null && locationTypeId.longValue()>0 && locationValues != null && locationValues.size()>0){
			if(locationTypeId ==2l){
				if(isParliment){

					queryStr.append(" and c.constituency_id in (:locationValues)");
					
				}else{
					queryStr.append(" and ac.constituency_id in (:locationValues)");
				}
			}
		}
		if(electionYear != null && electionYear.size()>0){
			queryStr.append(" and e.election_year in(:electionYear) ");
		}
		if(electionScopeIds != null && electionScopeIds.size()>0){
			queryStr.append(" and  e.election_scope_id in(:electionScopeIds)");
		}
		if(subTypes != null && subTypes.size()>0){
			queryStr.append(" and e.sub_type in(:subTypes)");
		}
		queryStr.append(" GROUP BY e.election_scope_id,N.party_id,c.constituency_id,CR.rank");

		Query query = getSession().createSQLQuery(queryStr.toString())
				.addScalar("electionScopeId",Hibernate.LONG)
				.addScalar("validVotes",Hibernate.LONG)
				.addScalar("votingPecentage",Hibernate.BIG_DECIMAL)
				.addScalar("marginVotes",Hibernate.LONG)
				.addScalar("marginVotesPercentage")
				.addScalar("rank",Hibernate.LONG)
				.addScalar("voteEarned",Hibernate.LONG)
				.addScalar("votesPecentage",Hibernate.BIG_DECIMAL)
				.addScalar("partyId",Hibernate.LONG)
				.addScalar("locationId",Hibernate.LONG)
				.addScalar("locationName",Hibernate.STRING);
		
		if(locationTypeId != null && locationTypeId.longValue()>0 && locationValues != null && locationValues.size()>0){
			query.setParameterList("locationValues",locationValues);
		}
		if(electionYear != null && electionYear.size()>0){
			query.setParameterList("electionYear",electionYear);
		}
		if(electionScopeIds != null && electionScopeIds.size()>0){
			query.setParameterList("electionScopeIds",electionScopeIds);
		}
		if(subTypes != null && subTypes.size()>0){
			query.setParameterList("subTypes",subTypes);
		}
		return query.list();
	}
	
	public  List<String> getElectionYearByScopeIds(List<Long> electionScopeIds,List<String> subTypeArr){//ara
		StringBuilder sb = new StringBuilder();
		sb.append(" select distinct distinct e.election_year from nomination n ,constituency_election ce , " +
					"party p ,election e " +
				    " where n.consti_elec_id = ce.consti_elec_id and " +
				    "n.party_id = p.party_id and "  +
				    "ce.election_id = e.election_id ");
		if(electionScopeIds != null && electionScopeIds.size() >0){
	    sb.append(" and e.election_scope_id in (:electionScopeIds) ");
	    }
		if(subTypeArr.size()>0l && subTypeArr !=null){
			sb.append(" and e.sub_type in (:subTypeArr) ");
		}
		sb.append(" order by e.election_year desc ");
		Query qry = getSession().createSQLQuery(sb.toString());   
		if(electionScopeIds != null && electionScopeIds.size() >0){
			qry.setParameterList("electionScopeIds", electionScopeIds);
		}
		if(subTypeArr != null && subTypeArr.size() >0){
			qry.setParameterList("subTypeArr", subTypeArr);
		}
		return qry.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getWonConstituencyCountsInLocation(Set<Long> locationIds,List<Long> electionScopeIds,Set<Long> electionIds){
		StringBuilder sb = new StringBuilder();	
			sb.append("select e.election_id,c.district_id,pa.short_name,count(n.party_id),n.party_id ");
			sb.append(" from constituency_election ce, nomination n ,constituency c ,election e,candidate_result cr,party pa ");
			sb.append("where e.election_id = ce.election_id  and  n.nomination_id = cr.nomination_id and ");
			sb.append("ce.constituency_id = c.constituency_id and n.consti_elec_id = ce.consti_elec_id and pa.party_id=n.party_id  ");
			sb.append("and cr.nomination_id = n.nomination_id and  cr.rank =1 ");
			if(electionScopeIds !=null && electionScopeIds.size() >0)
				sb.append("and e.election_scope_id  in(:electionScopeIds) ");
			if(electionIds !=null && electionIds.size() >0)
				sb.append("and e.election_id  in(:electionIds) ");
			if(locationIds !=null && locationIds.size() >0)
				sb.append("and c.district_id  in(:locationIds) ");
			sb.append("group by  e.election_id,c.district_id,n.party_id ");
		Query query=getSession().createSQLQuery(sb.toString());
		if(electionScopeIds !=null && electionScopeIds.size() >0)
			query.setParameterList("electionScopeIds", electionScopeIds);
		if(electionIds !=null && electionIds.size() >0)
			query.setParameterList("electionIds", electionIds);
		if(locationIds !=null && locationIds.size() >0)
			query.setParameterList("locationIds", locationIds);
		return query.list();
	}
	
	

public List<Object[]> getElectionYearWisePartyList(List<Long> electionScopeIdsLst,List<String> subTypes,List<Long> yearList){
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(" SELECT DISTINCT p.party_id, p.short_name " +
                  " from  nomination n ,constituency_election ce ," +
				  " party p ,election e " +
                  " where n.consti_elec_id = ce.consti_elec_id and " +
                  " n.party_id = p.party_id and " +
                  " ce.election_id = e.election_id " );
		
		if(electionScopeIdsLst != null && electionScopeIdsLst.size() >0){
			sb.append(" and e.election_scope_id in (:electionScopeIds) ");
		}
		 if(subTypes != null && subTypes.size() >0){
			 sb.append(" and e.sub_type in (:subTypeArr ) ");
		 }
		 if(yearList != null && yearList.size() >0){
			 sb.append(" and e.election_year in (:yearList) ");
		 }

        sb.append(" ORDER BY p.short_name");
        
        Query qry = getSession().createSQLQuery(sb.toString());
        
        if(electionScopeIdsLst != null && electionScopeIdsLst.size() >0){
			qry.setParameterList("electionScopeIds", electionScopeIdsLst);
		}
		if(subTypes != null && subTypes.size() >0){
			qry.setParameterList("subTypeArr", subTypes);
		}
		if(yearList != null && yearList.size() >0){
			qry.setParameterList("yearList", yearList);
		}
		
        return qry.list();

	}
}