package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUserEntitlementDAO;
import com.itgrids.partyanalyst.model.UserEntitlement;

public class UserEntitlementDAO  extends GenericDaoHibernate<UserEntitlement,Long> implements IUserEntitlementDAO {

	public UserEntitlementDAO(){
		super(UserEntitlement.class);
	}
	
  public List<String> getAllUserEntitlements(Long userId){
	  Query query = getSession().createQuery("select model.entitlement.entitlementType from UserEntitlement model where model.user.userId =:userId");
	  query.setParameter("userId", userId);
	  return query.list();
  }
}
