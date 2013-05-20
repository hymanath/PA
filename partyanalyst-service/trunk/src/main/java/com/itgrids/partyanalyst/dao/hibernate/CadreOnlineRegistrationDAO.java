package com.itgrids.partyanalyst.dao.hibernate;

import java.util.ArrayList;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreOnlineRegistrationDAO;
import com.itgrids.partyanalyst.model.CadreOnlineRegistration;

public class CadreOnlineRegistrationDAO extends GenericDaoHibernate<CadreOnlineRegistration, Long> implements ICadreOnlineRegistrationDAO{

	public CadreOnlineRegistrationDAO()
	{
		super(CadreOnlineRegistration.class);
	}
	/**
	 * This DAO is used for getting all the details of the user and online register details based on online registration id
	 * @param Long onlileRegId
	 * @return CadreOnlineRegistration
	 */
	public CadreOnlineRegistration getAllDetailsBasedOnOnlineRegId(Long onlileRegId)
	{
		Query query = getSession().createQuery("select model from CadreOnlineRegistration model where " +
						" model.cadreOnlineRegistrationId = :onlileRegId ");
		query.setParameter("onlileRegId", onlileRegId);
		return (CadreOnlineRegistration) query.uniqueResult();
	}
	
}
