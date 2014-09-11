package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IDuplicateWrongMobileNumbersDAO;
import com.itgrids.partyanalyst.model.DuplicateWrongMobileNumbers;

public class DuplicateWrongMobileNumbersDAO extends GenericDaoHibernate<DuplicateWrongMobileNumbers, Long>implements IDuplicateWrongMobileNumbersDAO {

	public DuplicateWrongMobileNumbersDAO() {
		super(DuplicateWrongMobileNumbers.class);
	}
	
	public Long getIsExistMobileDetails(String mobileNo)
	{
		Query query = getSession().createQuery(" select count(model.mobileNo) from DuplicateWrongMobileNumbers model where model.mobileNo =:mobileNo ");		
		query.setParameter("mobileNo", mobileNo);
		
		return (Long) query.uniqueResult();
	}
	
	public List<Object[]> getInvalidMobileNumbers()
	{
		Query query = getSession().createQuery(" select distinct model.mobileNo, model.mobileType from DuplicateWrongMobileNumbers model ");
		return  query.list();
	}
}
