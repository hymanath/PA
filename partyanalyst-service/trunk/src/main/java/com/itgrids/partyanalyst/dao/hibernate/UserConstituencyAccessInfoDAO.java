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
				"from UserConstituencyAccessInfo model where model.user.registrationId = ?", userId);
	}
	public List findByElectionScopeUserState(Long electionScope,Long userId,Long stateId){
		   Object[] parameters = {userId,electionScope,stateId};
		return getHibernateTemplate().find("select model.constituency.constituencyId, model.constituency.name " +
				"from UserConstituencyAccessInfo model where model.user.registrationId = ?"+
				" and model.constituency.electionScope.electionScopeId = ? " +
				"  and model.constituency.state.stateId = ? ", parameters);
	}
	public List findByElectionScopeUser(Long electionScope,Long userId){
		   Object[] parameters = {userId,electionScope};
		return getHibernateTemplate().find("select model.constituency.constituencyId, model.constituency.name " +
				"from UserConstituencyAccessInfo model where model.user.registrationId = ?"+
				" and model.constituency.electionScope.electionScopeId = ? ", parameters);
	}
	public void deleteAllAssemblyAccessByScopeStateIdUserId(Long electionScope,Long userId,Long stateId) {
		Object[] parameters = {userId,electionScope,stateId};
		List<Long> result = getHibernateTemplate().find("select model.userConstituencyAccessInfoId  " +
				"from UserConstituencyAccessInfo model where model.user.registrationId = ?"+
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
				"from UserConstituencyAccessInfo model where model.user.registrationId = ?"+
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
				"from UserConstituencyAccessInfo model where model.user.registrationId = ?"+
				" and model.constituency.electionScope.electionType.electionTypeId = ? " +
				"  and model.constituency.state.stateId = ? ", parameters);
	}
	
	public void deleteAllConstituencyAccessByUserId(Long userId) {	
		Object[] parameters = {userId};
		List<Long> result = getHibernateTemplate().find("select model.userConstituencyAccessInfoId  " +
				"from UserConstituencyAccessInfo model where model.user.registrationId = ? ", parameters);
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
				"from UserConstituencyAccessInfo model where model.user.registrationId = ?"+
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
				"from UserConstituencyAccessInfo model where model.user.registrationId = ?"+
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
}
