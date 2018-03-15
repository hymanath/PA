package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.TourTypeCategory;

public interface ITourTypeCategoryDAO extends GenericDao<TourTypeCategory, Long> {

	public List<Object[]> getTourTypeCategories();
}
