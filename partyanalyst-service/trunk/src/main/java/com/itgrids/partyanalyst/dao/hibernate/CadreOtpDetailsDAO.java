package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreOtpDetailsDAO;
import com.itgrids.partyanalyst.model.CadreOtpDetails;


public class CadreOtpDetailsDAO extends GenericDaoHibernate<CadreOtpDetails, Long> implements ICadreOtpDetailsDAO{

	public CadreOtpDetailsDAO() {
		super(CadreOtpDetails.class);
	}
	
	
	public Integer updateIsDeleted(String mobileNo)
	{
	 
		Query query = getSession().createQuery("update CadreOtpDetails model set model.isDeleted = 'Y' where model.isDeleted = 'N'" +
				" and model.mobileNo = :mobileNo");
		query.setParameter("mobileNo", mobileNo);
		return query.executeUpdate();
	}
}
