package com.itgrids.eliteclub.dao.impl;

import com.itgrids.eliteclub.dao.ContactAkmtDAO;
import com.itgrids.eliteclub.model.ContactAkmt;

public class ContactAkmtDAOImpl extends AbstractDaoImpl<ContactAkmt	, Integer> implements ContactAkmtDAO{

	protected ContactAkmtDAOImpl()
	{
		super(ContactAkmt.class);
	}
}
