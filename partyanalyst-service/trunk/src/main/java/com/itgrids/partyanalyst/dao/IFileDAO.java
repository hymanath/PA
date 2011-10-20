package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.File;
import com.itgrids.partyanalyst.model.FileType;

public interface IFileDAO extends GenericDao<File, Long> {
	
	public List<Object[]> getFileByFileId(Long fileId);
}
