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
import com.itgrids.partyanalyst.model.ElectionScope;

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
	public List<Election> findByElectionScope(Long electionScopeID)
	{
		return getHibernateTemplate().find("from Election model where model.electionScope.electionScopeId=? order by model.electionYear desc", electionScopeID);	
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Election> findByElectionScope_OrderByAsc(Long electionScopeID)
	{
		return getHibernateTemplate().find("from Election model where model.electionScope.electionScopeId=? order by model.electionYear asc", electionScopeID);	
		
	}
	
	@SuppressWarnings("unchecked")
	public List<Election> findByPropertyTypeId(Long typeID) {
		return getHibernateTemplate().find("from Election model where model.electionScope.electionType.electionTypeId =?", typeID);
	}

	public void insertTest(Election ele) {
		//getHibernateTemplate().saveOrUpdateAll(ele);
	} 

	public Election findByESIdEleYear(ElectionScope eleScope,String eleYear){
		Election lelectionObj=null;
		Query query = getSession().createQuery("from Election model where model.electionScope.electionScopeId =? and model.electionYear=?");
		query.setParameter(0, eleScope.getElectionScopeId());
		query.setParameter(1, eleYear);
		List<Election> list= query.list();
		if(list!=null && list.size()>0){
			return lelectionObj=list.get(0);
		}
		return lelectionObj;		
	}
	
	@SuppressWarnings("unchecked")
	public Election findByElectionScopeIdElectionYear(Long electionScope,String electionYear){
		Election electionObj=null;
		Query query = getSession().createQuery("from Election model where model.electionScope.electionScopeId =? and model.electionYear=?");
		query.setParameter(0, electionScope);
		query.setParameter(1, electionYear);
		List<Election> list= query.list();
		if(list!=null && list.size()>0){
			return electionObj=list.get(0);
		}
	return electionObj;		
	}
	
	@SuppressWarnings("unchecked")
	public List<Election> findByElectionScopeId(Long electionScopeId){
		return getHibernateTemplate().find("from Election model where model.electionScope.electionScopeId = ?", electionScopeId);
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
	public List findLatestElectionYear(String electionType){
		return getHibernateTemplate().find("select max(model.electionYear) from Election model where model.electionScope.electionType.electionType = ?", electionType);
	}
	
	@SuppressWarnings("unchecked")
	public List findElectionIdAndYear(Long electionType,Long stateId){
		Object[] params = {electionType,stateId};
		return getHibernateTemplate().find("select max(model.electionId),max(model.electionYear) from Election model where model.electionScope.electionType.electionTypeId = ? and model.electionScope.state.stateId = ?",params);
	}

	@SuppressWarnings("unchecked")
	public List findElectionAndYearForElectionTypeAndState(Long electionType,Long stateId){
		Object[] params = {electionType,stateId};
		return getHibernateTemplate().find("select model.electionId,model.electionYear from Election model where model.electionScope.electionType.electionTypeId = ? and model.electionScope.state.stateId = ?",params);
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
	public List findParliamentElectionIdByElectionTypeAndYear(String electionType,String year){
		Object[] params = {electionType,year};
		return getHibernateTemplate().find("select model.electionId from Election model where model.electionScope.electionType.electionType = ? and model.electionYear = ?",params);
	}
	
	@SuppressWarnings("unchecked")
	public List findElectionIdByElectionTypeAndYear(String electionType,String year,Long stateId){
		Object[] params = {electionType,year,stateId};
		return getHibernateTemplate().find("select model.electionId from Election model where model.electionScope.electionType.electionType = ? and model.electionYear = ? and model.electionScope.state.stateId = ?",params);
	}

}
