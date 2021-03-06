package com.itgrids.partyanalyst.dao;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.FilePaths;

public interface IFilePathsDAO extends GenericDao<FilePaths, Long> {

	public List<Object> getMaxOrderNo();
	public List<Object[]> getProblemRelatedFiles(Long problemId,Long userId);
	public List<Object[]> getProblemFiles(final Long problemId,final Long userId,final String visibility);
	public List<Object> getFilePaths(long fileId);
	public List<Object> getFilePathsBasedOnFileTypeIds(List<?> fileTypeIds);
	public int  updateFilePathsThumbnails(List<String> dataToSave);
	public List<Object[]> getFilePathsBasedOnFileSource(List<Long> sourceIds);
	
	public List<Object[]> getEditionAndPageNoByFileSourceId(Long fileSourceLanguageId);
	
	public List<Object[]> getSourceIdsAndPageNos(Set<Long> fileIdsList);
	
	public List<FilePaths> getFileDetailsForSelected(List<Long> fileIds);

	public List<Object[]> getFileDetailsByFileId(Long fileId,Long fileSourceLanguageIdList);
	
	public List<FilePaths> getFilePathDetailsByFileSourceLangId(Long FileSourceLangId);
	
	public void deleteFilePathDetailsByFilePAthsId(Long filePathId);
	
	public void deleteFilePathsByFileSourceLangId(Long fileSourceLangId);
	
	public Long getMaxfilePathsOrderNo(Long fileSourceLanguageId);
	
	public List<Object[]> getAllFiles(List<Long> fileIds);
}
