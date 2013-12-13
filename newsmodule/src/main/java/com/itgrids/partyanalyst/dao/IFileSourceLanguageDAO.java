package com.itgrids.partyanalyst.dao;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.FileSourceLanguage;

public interface IFileSourceLanguageDAO extends GenericDao<FileSourceLanguage, Long>  {

	public List<FileSourceLanguage> getFileSourceLanguageObject(Long fileId,Long sourceId ,Long languageId);
	
	public  List<Object> getFileLanguage(Long fileId);
	
	public List<Long> getFileSourceIdsBasedOnFile(Long fileId);
	
	public List<Object[]> getSourceDetailsByFileIds(Set<Long> fileIds);
	
	public List<Object[]> getFileSourceType(List<Long> fileIds);
	
	public List<Object[]> getEditionPartNoDetailsByFileIds(Set<Long> fileIds);
	
	public List<Object[]> getFileSourceByFileIds(Set<Long> fileIds);
	
	public List<Object[]> getCandidateDetailsByFileIds(Set<Long> fileIds);
	
}
