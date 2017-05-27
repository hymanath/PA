package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.TdpResolutionType;

public interface ITdpResolutionTypeDAO extends GenericDao<TdpResolutionType,Long>{

	public List<Object[]> getResolutions(List<String> daysList);

}
