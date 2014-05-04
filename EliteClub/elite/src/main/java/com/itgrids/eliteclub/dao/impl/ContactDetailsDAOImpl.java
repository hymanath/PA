package com.itgrids.eliteclub.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.itgrids.eliteclub.dao.ContactDetailsDAO;
import com.itgrids.eliteclub.model.ContactDetails;

@Component("contactDetailsDAO")

public class ContactDetailsDAOImpl extends AbstractDaoImpl<ContactDetails, Integer> implements ContactDetailsDAO{

	protected ContactDetailsDAOImpl()
	{
		super(ContactDetails.class);
	}

	@SuppressWarnings("unchecked")
	
	public List<String> getMobileNumbersByUser(String imeiNo,Integer userId) {
		Query query = getCurrentSession().createQuery("select distinct CD.phoneNumber from ContactDetails CD where CD.user.imeiNo = :imeiNo and CD.user.userId = :userId");
		query.setParameter("imeiNo", imeiNo);
		query.setParameter("userId", userId);
		return query.list();
	}

}
