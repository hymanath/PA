package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.FeedbackCategory;

public interface IFeedbackCategoryDAO extends GenericDao<FeedbackCategory, Long>{
	public List<Object[]> getAllNamesOfIds(List<Long> ids);
}
