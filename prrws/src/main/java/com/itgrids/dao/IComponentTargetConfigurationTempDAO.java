package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.ComponentTargetConfigurationTemp;

public interface IComponentTargetConfigurationTempDAO extends GenericDao<ComponentTargetConfigurationTemp, Long>{

	public List<Object[]> getPanchayatTargetDetails(String type);
	public int updateoldData();
}
