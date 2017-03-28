package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUserDistrictAccessInfoDAO;
import com.itgrids.partyanalyst.model.UserDistrictAccessInfo;

public class UserDistrictAccessInfoDAO extends GenericDaoHibernate<UserDistrictAccessInfo, Long> implements IUserDistrictAccessInfoDAO{

	private static final String GET_DISTRICT_ID_DISTRICT_NAME=" distinct model.district.districtId, model.district.districtName";
	private static final String GET_DISTRICT_ID = " distinct model.district.districtId";
	
	public UserDistrictAccessInfoDAO() {
		super(UserDistrictAccessInfo.class);
	}
	
	public List findByUser(Long userId){
		return getHibernateTemplate().find("select  "+GET_DISTRICT_ID_DISTRICT_NAME+"" +
				" from UserDistrictAccessInfo model where model.user.userId = ? order by model.district.districtName ", userId);
	}
	public List findByUserAndState(Long stateId,Long userId){
		Object[] parameters = {userId,stateId};
		return getHibernateTemplate().find("select "+GET_DISTRICT_ID_DISTRICT_NAME+"" +
				" from UserDistrictAccessInfo model where model.user.userId = ? "+
				"  and model.district.state.stateId = ? order by model.district.districtName  ", parameters);
	}
	
	public Integer deleteAllDistrictAccessByStateIdUserId(Long userId,Long stateId) {		
		StringBuilder query = new StringBuilder();
		query.append(" delete from UserDistrictAccessInfo model ");
		query.append(" where model.user.userId = ? ");
		query.append(" and model.district.state.stateId = ? ");
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameter(0, userId);
		queryObject.setParameter(1, stateId);
		return queryObject.executeUpdate();		
	}
	public void deleteDistrictAccessByUserIdStateId(Long userId,Long stateId){
		Object[] parameters = {userId,stateId};
		List<Long> result = getHibernateTemplate().find("select model.userDistrictAccessInfoId from UserDistrictAccessInfo model " +
				" where model.user.userId = ? "+
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
					"where model.user.userId = ? ", parameters);
			
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
		
		@SuppressWarnings("unchecked")
		public List<Object[]> getAllUserAccessStateList(Long userId)
		{
			return getHibernateTemplate().find("select model.district.state.stateId, model.district.state.stateName from UserDistrictAccessInfo model where model.user.userId = ?",userId);
					
		}
		public List<Object[]> getLocationIdList(Long userId){
			StringBuilder queryStr = new StringBuilder();
			queryStr.append(" select " +GET_DISTRICT_ID_DISTRICT_NAME+""+
			 " from UserDistrictAccessInfo model where " +
							" model.userId = :userId ");
			Query query = getSession().createQuery(queryStr.toString());
			query.setParameter("userId", userId);
			return query.list();
		}
		public List<Long> getDistrictIdsByUsrId(Long userId){
			Query query = getSession().createQuery("select " +GET_DISTRICT_ID+"" +
					" from UserDistrictAccessInfo model " +
					" where model.user.userId = :userId");
			query.setParameter("userId", userId);
			return query.list();
		}
}
