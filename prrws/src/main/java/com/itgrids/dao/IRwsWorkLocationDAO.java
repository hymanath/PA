package com.itgrids.dao;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.RwsWorkLocation;

public interface IRwsWorkLocationDAO  extends GenericDao<RwsWorkLocation, Long>{

	public RwsWorkLocation getWorkdetailsByHabAndId(int WorkId, String habCode);

}
