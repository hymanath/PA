package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ConstiCasteGroupPerc;

public interface IConstiCasteGroupPercDAO extends GenericDao<ConstiCasteGroupPerc, Long>{
	public List<Object[]> getData();
	
	public List<Object[]> getConstituencyCastePer();
	
	public List<Object[]> getConstituencyCastePerByConstiId(Long constituencyId);
}
