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
	
	public List<Object[]> getIsExistMobileDetails(List<String> mobileNosList)
	{
		Query query = getSession().createQuery(" select distinct model.mobileNo,count(model.mobileNo) from DuplicateWrongMobileNumbers model where model.mobileNo in(:mobileNosList) and model.mobileNo is not null and model.mobileNo != '' group by model.mobileNo");		
		query.setParameterList("mobileNosList", mobileNosList);
		
		return query.list();
	}
	
	public List<Object[]> getInvalidMobileNumbers()
	{
		Query query = getSession().createQuery(" select distinct model.mobileNo, model.mobileType from DuplicateWrongMobileNumbers model where  model.mobileNo is not null and model.mobileNo != '' ");
		return  query.list();
	}
}
