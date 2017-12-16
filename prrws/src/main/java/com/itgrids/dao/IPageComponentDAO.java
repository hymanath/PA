package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.PageComponent;

public interface IPageComponentDAO extends GenericDao<PageComponent, Long>{

	public List<Object[]> getPageWiseComponents(Long pageId);
}
