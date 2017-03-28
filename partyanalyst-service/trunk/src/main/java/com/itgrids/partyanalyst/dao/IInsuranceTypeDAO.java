package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.InsuranceType;

public interface IInsuranceTypeDAO extends GenericDao<InsuranceType, Long>{
	
	public List<Object[]> getAllInsuranceType();

}
