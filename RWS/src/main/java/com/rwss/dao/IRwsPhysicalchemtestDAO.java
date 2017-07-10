package com.rwss.dao;

import org.appfuse.dao.GenericDao;

import com.rwss.dto.InputVO;
import com.rwss.model.RwsPhysicalchemtest;

public interface IRwsPhysicalchemtestDAO extends GenericDao<RwsPhysicalchemtest,Long> {

	public Long getPhysicalchemtestCountYearWise(InputVO inputVO);
}
