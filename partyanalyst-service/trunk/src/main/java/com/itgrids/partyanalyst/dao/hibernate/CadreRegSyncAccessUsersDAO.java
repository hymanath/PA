package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreRegSyncAccessUsersDAO;
import com.itgrids.partyanalyst.model.CadreRegSyncAccessUsers;

public class CadreRegSyncAccessUsersDAO extends GenericDaoHibernate<CadreRegSyncAccessUsers, Long>  implements ICadreRegSyncAccessUsersDAO{
		public CadreRegSyncAccessUsersDAO() {
			super(CadreRegSyncAccessUsers.class);		
		}
	
		public Long checkHasAccess(Long userId){
			Query query = getSession().createQuery("select count(*) from CadreRegSyncAccessUsers model where model.userId=:userId ");
			query.setParameter("userId", userId);
			return (Long)query.uniqueResult(); 
		}
}
