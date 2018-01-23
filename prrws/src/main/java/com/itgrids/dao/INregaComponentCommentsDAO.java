package com.itgrids.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.model.NregaComponentComments;

public interface INregaComponentCommentsDAO extends GenericDao<NregaComponentComments, Long>{
	public List<Object[]> getNregaComponentComments(List<String> uniqueCode);
	public List<Object[]> getNregaComponentCommentsByComponent(String componentName);
}
