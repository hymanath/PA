package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.ComponentTargetConfiguration;

public interface IComponentTargetConfigurationDAO extends GenericDao<ComponentTargetConfiguration, Long>{

	public List<Object[]> getRangeWiseVillagesCounts();
	public List<Object[]> getRangeWiseVillageDetails(String type,String locationType,String locationIdStr);
}
