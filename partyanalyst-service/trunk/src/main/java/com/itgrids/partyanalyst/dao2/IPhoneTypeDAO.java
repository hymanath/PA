package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PhoneType;

public interface IPhoneTypeDAO extends GenericDao<PhoneType, Long>{
	
	public Object getPhoneTypeNameByPhoneTypeId(Long phoneTypeId);
	
	public List<Object[]> getAllPhoneTypes();

}
