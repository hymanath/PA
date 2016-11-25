package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AlertCategory;

public interface IAlertCategoryDAO extends GenericDao<AlertCategory, Long> {
	public List<Object[]> getAllCategory();
}
