package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPhoneNumberDAO;
import com.itgrids.partyanalyst.model.PhoneNumber;

public class PhoneNumberDAO extends GenericDaoHibernate<PhoneNumber, Long> implements IPhoneNumberDAO{
	
	public PhoneNumberDAO() {

		super(PhoneNumber.class);
		// TODO Auto-generated constructor stub
	}

	public Object getPhoneNumberByPhoneCategoryId(Long phoneCategoryId) {
		
		
		
		
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
