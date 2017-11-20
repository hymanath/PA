package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUserConstituencyAccessInfoDAO;
import com.itgrids.partyanalyst.model.UserConstituencyAccessInfo;

public class UserConstituencyAccessInfoDAO extends GenericDaoHibernate< UserConstituencyAccessInfo, Long> implements  IUserConstituencyAccessInfoDAO{

	public UserConstituencyAccessInfoDAO() {
		super(UserConstituencyAccessInfo.class);
	}
	
	public List findByUser(Long userId){
		return getHibernateTemplate().find("select model.constituency.constituencyId, model.constituency.name, " +
				"model.constituency.electionScope.electionType.electionType " +
				"from UserConstituencyAccessInfo model where model.user.userId = ? order by model.constituency.name ", userId);
	}
	public List findByElectionScopeUserState(Long electionScope,Long userId,Long stateId){
		   Object[] parameters = {userId,electionScope,stateId};
		return getHibernateTemplate().find("select distinct model.constituency.constituencyId, model.constituency.name " +
				"from UserConstituencyAccessInfo model where model.user.userId = ?"+
				" and model.constituency.electionScope.electionScopeId = ? " +
				"  and model.constituency.state.stateId = ? order by model.constituency.name ", parameters);
	}
	public List findByElectionScopeUser(Long electionScope,Long userId){
		   Object[] parameters = {userId,electionScope};
		return getHibernateTemplate().find("select model.constituency.constituencyId, model.constituency.name " +
				"from UserConstituencyAccessInfo model where model.user.userId = ?"+
				" and model.constituency.electionScope.electionScopeId = ? ", parameters);
	}
	public void deleteAllAssemblyAccessByScopeStateIdUserId(Long electionScope,Long userId,Long stateId) {
		Object[] parameters = {userId,electionScope,stateId};
		List<Long> result = getHibernateTemplate().find("select model.userConstituencyAccessInfoId  " +
				"from UserConstituencyAccessInfo model where model.user.userId = ?"+
				" and model.constituency.electionScope.electionType.electionTypeId = ? " +
				"  and model.constituency.state.stateId = ? ", parameters);
		for(Long o: result){
			getSession().getTransaction().begin();
			StringBuilder query = new StringBuilder();
			query.append(" delete from UserConstituencyAccessInfo model ");
			query.append(" where model.userConstituencyAccessInfoId = ? ");
			Query queryObject = getSession().createQuery(query.toString());
			queryObject.setParameter(0, o);
			queryObject.executeUpdate();
			getSession().flush();
			getSession().getTransaction().commit();
			
		}
		
	}
	
	public void deleteAllParliamentAccessByScopeUserId(Long electionScope,Long userId) {	
		Object[] parameters = {userId,electionScope};
		List<Long> result = getHibernateTemplate().find("select model.userConstituencyAccessInfoId  " +
				"from UserConstituencyAccessInfo model where model.user.userId = ?"+
				" and model.constituency.electionScope.electionType.electionTypeId = ? " , parameters);
		for(Long o: result){
			getSession().getTransaction().begin();
			StringBuilder query = new StringBuilder();
			query.append(" delete from UserConstituencyAccessInfo model ");
			query.append(" where model.userConstituencyAccessInfoId = ? ");
			Query queryObject = getSession().createQuery(query.toString());
			queryObject.setParameter(0, o);
			queryObject.executeUpdate();
			getSession().flush();
			getSession().getTransaction().commit();
			
		}
		
	}
	
	public List<Object[]> findByElectionTypeUserState(Long electionType,Long userId,Long stateId){
		   Object[] parameters = {userId,electionType,stateId};
		return getHibernateTemplate().find("select model.constituency.constituencyId, model.constituency.name " +
				"from UserConstituencyAccessInfo model where model.user.userId = ?"+
				" and model.constituency.electionScope.electionType.electionTypeId = ? " +
				"  and model.constituency.state.stateId = ? ", parameters);
	}
	
	public void deleteAllConstituencyAccessByUserId(Long userId) {	
		Object[] parameters = {userId};
		List<Long> result = getHibernateTemplate().find("select model.userConstituencyAccessInfoId  " +
				"from UserConstituencyAccessInfo model where model.user.userId = ? ", parameters);
		for(Long o: result){
			getSession().getTransaction().begin();
			StringBuilder query = new StringBuilder();
			query.append(" delete from UserConstituencyAccessInfo model ");
			query.append(" where model.userConstituencyAccessInfoId = ? ");
			Query queryObject = getSession().createQuery(query.toString());
			queryObject.setParameter(0, o);
			queryObject.executeUpdate();
			getSession().flush();
			getSession().getTransaction().commit();
			
		}
		
	}
	public void deleteAllConstituencyAccessByUserIdStateId(Long userId,Long stateId) {	
		Object[] parameters = {userId,stateId};
		List<Long> result = getHibernateTemplate().find("select model.userConstituencyAccessInfoId  " +
				"from UserConstituencyAccessInfo model where model.user.userId = ?"+
				" and model.constituency.state.stateId = ? ",parameters);
		for(Long o: result){
			getSession().getTransaction().begin();
			StringBuilder query = new StringBuilder();
			query.append(" delete from UserConstituencyAccessInfo model ");
			query.append(" where model.userConstituencyAccessInfoId = ? ");
			Query queryObject = getSession().createQuery(query.toString());
			queryObject.setParameter(0, o);
			queryObject.executeUpdate();
			getSession().flush();
			getSession().getTransaction().commit();
			
		}
		
	}
	
	public void deleteAllAssemblyAccessByScopeStateIdUserIdDistrictId(Long electionScope,Long userId,Long stateId,Long districtId) {
		Object[] parameters = {userId,electionScope,stateId,districtId};
		List<Long> result = getHibernateTemplate().find("select model.userConstituencyAccessInfoId  " +
				"from UserConstituencyAccessInfo model where model.user.userId = ?"+
				" and model.constituency.electionScope.electionType.electionTypeId = ? " +
				"  and model.constituency.state.stateId = ? " +
				" and model.constituency.district.districtId = ?", parameters);
		for(Long o: result){
			getSession().getTransaction().begin();
			StringBuilder query = new StringBuilder();
			query.append(" delete from UserConstituencyAccessInfo model ");
			query.append(" where model.userConstituencyAccessInfoId = ? ");
			Query queryObject = getSession().createQuery(query.toString());
			queryObject.setParameter(0, o);
			queryObject.executeUpdate();
			getSession().flush();
			getSession().getTransaction().commit();
			
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllUserAccessConstituencies(Long userId)
	{
		return getHibernateTemplate().find("select model.constituency.state.stateId,model.constituency.state.stateName from UserConstituencyAccessInfo model where model.user.userId = ? ",userId);
	}
	public List<Object[]> getLocationIdList(Long userId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct model.constituency.constituencyId, model.constituency.name from UserConstituencyAccessInfo model where " +
						" model.userId = :userId ");
		Query query = getSession().createQuery(queryStr.toString());  
		query.setParameter("userId", userId);
		return query.list();
	}
	public List<Long> getConstituenciesByUser(List<Long> userIds){
		Query query = getSession().createQuery("select distinct model.constituency.constituencyId" +
				" from UserConstituencyAccessInfo model" +
				" where model.userId in (:userIds)");
		query.setParameterList("userIds", userIds);
		return query.list();
	}
	public List<Object[]> getConstituencyByUserId(Long userId){
	Query query = getSession().createQuery(" select model.constituency.constituencyId," +
				"model.constituency.electionScope.electionType.electionType" +
				" from UserConstituencyAccessInfo model" +
				" where model.userId=:userId");
		query.setParameter("userId", userId);
		return query.list();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> findAssembliesConstituenciesByParliaments(List<Long> parliamentConstituencyIds) 
	{
		StringBuilder query = new StringBuilder();
		query.append(" select distinct model.constituency.constituencyId,model.constituency.name from DelimitationConstituencyAssemblyDetails model where " +
				"model.delimitationConstituency.constituency.constituencyId in (:parliamentConstituencyIds) and model.delimitationConstituency.year = " +
				"(select max(model1.year) from DelimitationConstituency model1) order by model.constituency.name " );
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameterList("parliamentConstituencyIds", parliamentConstituencyIds);
		return queryObject.list();
	}
	
	
}
