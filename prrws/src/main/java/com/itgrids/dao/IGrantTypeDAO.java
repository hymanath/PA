package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.GrantType;

public interface IGrantTypeDAO extends GenericDao<GrantType,Long>{
	public List<Object[]> getGrandTypeDtls();
	public List<Object[]> getGovtGrantTypeDetails(Long programId,Long govtSchemesId);
}
