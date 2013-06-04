package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.FileSourceLanguage;

public interface IFileSourceLanguageDAO extends GenericDao<FileSourceLanguage, Long>  {

	public List<FileSourceLanguage> getFileSourceLanguageObject(Long fileId,Long sourceId ,Long languageId);
	
	public  List<Object> getFileLanguage(Long fileId);
}
