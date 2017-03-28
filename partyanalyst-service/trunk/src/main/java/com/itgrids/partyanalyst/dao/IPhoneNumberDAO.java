package com.itgrids.partyanalyst.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PhoneNumber;

public interface IPhoneNumberDAO extends GenericDao<PhoneNumber, Long>{

	public Object getPhoneNumberByPhoneCategoryId(Long phoneCategoryId);
}
