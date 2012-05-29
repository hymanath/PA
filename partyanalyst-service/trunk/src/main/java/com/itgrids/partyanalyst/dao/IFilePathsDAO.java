package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.FilePaths;

public interface IFilePathsDAO extends GenericDao<FilePaths, Long> {

	public List<Object> getMaxOrderNo();
}
