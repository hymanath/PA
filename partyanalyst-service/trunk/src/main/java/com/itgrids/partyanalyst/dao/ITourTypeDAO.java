package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TourType;

public interface ITourTypeDAO extends GenericDao<TourType, Long> {
	public List<Object[]> getAllTourTypes();
	public List<Object[]> getChildTourType(Long tourTypeParentId);
}
