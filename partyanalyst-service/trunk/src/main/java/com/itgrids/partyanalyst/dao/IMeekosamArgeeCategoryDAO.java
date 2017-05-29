package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.MeekosamArgeeCategory;

public interface IMeekosamArgeeCategoryDAO  extends GenericDao<MeekosamArgeeCategory, Long>{
	public List<Object[]> getmeekosamArgeeCategoryList();
}
