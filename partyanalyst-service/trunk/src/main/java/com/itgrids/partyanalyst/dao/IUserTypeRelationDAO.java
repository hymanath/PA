package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.UserTypeRelation;

public interface IUserTypeRelationDAO extends GenericDao<UserTypeRelation,Long>{
	
	public List<Object[]>  getParentUserTypesAndItsChildUserTypes();
	public List<Object[]>  getChildUserTypesByItsParentUserType(Long parentUserTypeId);
}
