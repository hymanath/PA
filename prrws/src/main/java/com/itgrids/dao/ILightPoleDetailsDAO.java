package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.LightPoleDetails;

public interface ILightPoleDetailsDAO extends GenericDao<LightPoleDetails, Long>{

	public List<Object[]> getLocationWisePolesDetails(String locationType);
}
