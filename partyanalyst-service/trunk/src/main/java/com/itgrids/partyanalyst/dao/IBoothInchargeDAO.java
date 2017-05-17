package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.BoothIncharge;

public interface IBoothInchargeDAO extends GenericDao<BoothIncharge,Long>{
	
	public List<Long> getCadreIdsForLocation(List<Long> tdpCadreIds);
	public BoothIncharge getExistingMember(Long locationId,String type);
}
