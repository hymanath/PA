package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.BoothLocalBodyWard;

public interface IBoothLocalBodyWardDAO extends GenericDao<BoothLocalBodyWard, Long>{
	public Integer deleteRecords(List<Long> boothIds, Long wardId);
	public List<Long> getBoothsForWard(Long wardId,Long publicationId);
	public List<Object[]> getBoothsDetailsForWard(Long wardId,Long publicationId);
	
	public List<Long> getBoothIds();
	
}
