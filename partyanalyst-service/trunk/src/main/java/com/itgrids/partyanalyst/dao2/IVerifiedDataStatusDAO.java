package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.VerifiedDataStatus;

public interface IVerifiedDataStatusDAO extends GenericDao<VerifiedDataStatus,Long>{

	public List<Object[]> getExistingFamilyRecordStatus(List<String> uniqueKeys,List<Long> userIds);
	
	public void updateStatus(List<String> uniqueKeys,List<Long> userIds,Date currentTime,String status);
}
