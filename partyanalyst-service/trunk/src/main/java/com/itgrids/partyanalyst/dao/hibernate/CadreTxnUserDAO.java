package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreTxnUserDAO;
import com.itgrids.partyanalyst.model.CadreTxnUser;

public class CadreTxnUserDAO extends GenericDaoHibernate<CadreTxnUser, Long> implements ICadreTxnUserDAO{

	public CadreTxnUserDAO() {
		super(CadreTxnUser.class);
	}
	
	
	public List checkUserExists(Long userId , String mobileNo)
	{
		
		Query query = getSession().createQuery("select model.user.userId from CadreTxnUser model where model.mobileNo =:mobileNo and model.user.userId =:userId");
		query.setParameter("mobileNo", mobileNo);
		query.setParameter("userId", userId);
		return query.list();
		
	}


}
