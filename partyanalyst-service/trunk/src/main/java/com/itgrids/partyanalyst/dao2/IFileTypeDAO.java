package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.FileType;

public interface IFileTypeDAO extends GenericDao<FileType, Long> {
	public List<FileType> getFileType(String type) ;
}
