package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.NewsImportance;

public interface INewsImportanceDAO extends GenericDao<NewsImportance, Long>{
	public List<Object[]> getNewsImportanceDetails();
}
