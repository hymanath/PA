package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AppDbUpdate;

public interface IAppDbUpdateDAO  extends GenericDao<AppDbUpdate,Long>{

	public List<Double> getAllVersionsOfAnApp(String appName,Double currentVerison,boolean includeTest);
	
	public List<Object[]> getAllUpdatesByVersion(String appName,Double version);
}
