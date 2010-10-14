package com.itgrids.partyanalyst.dao.hibernate;


import java.sql.SQLException;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.itgrids.partyanalyst.model.AnanymousUser;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.model.District;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.utils.IConstants;


import com.itgrids.partyanalyst.dao.IAnanymousUserDAO;

public class AnanymousUserDAO extends GenericDaoHibernate<AnanymousUser, Long> implements IAnanymousUserDAO {

	public AnanymousUserDAO() {
		super(AnanymousUser.class);
	}

	@SuppressWarnings("unchecked")
	public List<AnanymousUser> checkAnonymousUserLogin(String userId,
			String password) {
		Object[] parameters = {userId,password};
		return getHibernateTemplate().find("from AnanymousUser model where model.username = ? and model.password = ?",parameters);
	}

	@SuppressWarnings("unchecked")
	public List<AnanymousUser> checkForUserNameAvailabiity(String userName) {
		return getHibernateTemplate().find("select model.username from AnanymousUser model where model.username = ?",userName);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getAllUsersInSelectedLocations(List<Long> locationIds,String locationType,String retrivalCount) {
		StringBuilder query = new StringBuilder();
		query.append("select model.name,model.lastName,model.userId,model.constituency.constituencyId ");
		query.append(" from AnanymousUser model where ");
		if(locationType.equalsIgnoreCase(IConstants.STATE_LEVEL)){
			query.append("model.state.stateId in (:locationIds)");
		}else if(locationType.equalsIgnoreCase(IConstants.DISTRICT_LEVEL)){
			query.append("model.district.districtId in (:locationIds)");
		}else if(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY_LEVEL)){
			query.append("model.constituency.constituencyId in (:locationIds)");
		}	
		query.append("order by model.userId desc");
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameterList("locationIds", locationIds);
		if(!retrivalCount.equalsIgnoreCase("complete")){
			queryObject.setMaxResults(IConstants.MAX_ANONYMOUS_USER_DISPLAY.intValue());
		}		
		return queryObject.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<AnanymousUser> getDetailsForUsers(List<Long> userIds){
		StringBuilder query = new StringBuilder();				
		query.append(" select model.name,model.lastName,model.userId");
		query.append(" from AnanymousUser model where ");
		query.append(" model.userId in (:userIds)");	
		Query queryObject = getSession().createQuery(query.toString());
		queryObject.setParameterList("userIds", userIds);
		return queryObject.list();
	}
}
