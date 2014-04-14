package com.itgrids.eliteclub.dao;

import java.util.List;

import com.itgrids.eliteclub.model.ContactDetails;

public interface ContactDetailsDAO extends AbstractDao<ContactDetails, Integer>
{

	public List<String> getMobileNumbersByUser(String imeiNo,Integer userId);

}
