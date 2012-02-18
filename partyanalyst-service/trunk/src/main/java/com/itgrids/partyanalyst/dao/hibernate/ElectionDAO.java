package com.itgrids.partyanalyst.dao.hibernate;

import java.sql.SQLException;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Criteria;
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
		return getHibernateTemplate().find("select model.electionId,model.electionYear from Election model where model.electionScope.electionType.electionTypeId = ? and model.electionScope.state.stateId = ? order by model.electionYear",params);
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
				" model.electionScope.electionType.electionType = ? and model.elecSubtype = ? order by model.electionYear desc",params);
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

	public List getElectionIdsBasedOnStateId(Long stateId) {
		
		return getHibernateTemplate().find("select model.electionId from Election model where model.electionScope.state.stateId = ? and model.electionYear >'2009'",stateId);
	}
}
