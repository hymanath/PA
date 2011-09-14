package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUserDistrictAccessInfoDAO;
import com.itgrids.partyanalyst.model.UserDistrictAccessInfo;

public class UserDistrictAccessInfoDAO extends GenericDaoHibernate<UserDistrictAccessInfo, Long> implements IUserDistrictAccessInfoDAO{

	public UserDistrictAccessInfoDAO() {
		super(UserDistrictAccessInfo.class);
	}
	
	public List findByUser(Long userId){
		return getHibernateTemplate().find("select model.district.districtId, model.district.districtName " +
				"from UserDistrictAccessInfo model where model.user.registrationId = ?", userId);
	}
	public List findByUserAndState(Long stateId,Long userId){
		Object[] parameters = {userId,stateId};
		return getHibernateTemplate().find("select model.district.districtId, model.district.districtName " +
				"from UserDistrictAccessInfo model where model.user.registrationId = ? "+
				"  and model.district.state.stateId = ? ", parameters);
	}
	
	public Integer deleteAllDistrictAccessByStateIdUserId(Long userId,Long stateId) {		
		StringBuilder query = new StringBuilder();
		query.append(" delete from UserDistrictAccessInfo model ");
		query.append(" where model.user.registrationId = ? ");
		query.append(" and model.district.state.stateId = ? ");
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameter(0, userId);
		queryObject.setParameter(1, stateId);
		return queryObject.executeUpdate();		
	}
	public void deleteDistrictAccessByUserIdStateId(Long userId,Long stateId){
		Object[] parameters = {userId,stateId};
		List<Long> result = getHibernateTemplate().find("select model.userDistrictAccessInfoId from UserDistrictAccessInfo model " +
				"where model.user.registrationId = ? "+
				"  and model.district.state.stateId = ? ", parameters);
		
		for(Long o: result){
			getSession().getTransaction().begin();
			StringBuilder query = new StringBuilder();
			query.append(" delete from UserDistrictAccessInfo model ");
			query.append(" where model.userDistrictAccessInfoId = ? ");
			Query queryObject = getSession().createQuery(query.toString());
			queryObject.setParameter(0, o);
			queryObject.executeUpdate();
			getSession().flush();
			getSession().getTransaction().commit();
			
		}
	}
		public void deleteAllDistrictAccess(Long userId){
			Object[] parameters = {userId};
			List<Long> result = getHibernateTemplate().find("select model.userDistrictAccessInfoId from UserDistrictAccessInfo model " +
					"where model.user.registrationId = ? ", parameters);
			
			for(Long o: result){
				getSession().getTransaction().begin();
				StringBuilder query = new StringBuilder();
				query.append(" delete from UserDistrictAccessInfo model ");
				query.append(" where model.userDistrictAccessInfoId = ? ");
				Query queryObject = getSession().createQuery(query.toString());
				queryObject.setParameter(0, o);
				queryObject.executeUpdate();
				getSession().flush();
				getSession().getTransaction().commit();
				
			}
		 
		}
}
