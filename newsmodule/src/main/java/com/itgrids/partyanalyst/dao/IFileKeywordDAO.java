package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.FileKeyword;



public interface IFileKeywordDAO extends GenericDao<FileKeyword, Long>{
	public List<Long> getFilesForEachKeyWord(List<Long> keyWords);
}
