package com.itgrids.partyanalyst.dao;

import java.util.List;
import java.util.Map;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.FilePaths;

public interface IFilePathsDAO extends GenericDao<FilePaths, Long> {

	public List<Object> getMaxOrderNo();
	public List<Object[]> getProblemRelatedFiles(Long problemId,Long userId);
	public List<Object[]> getProblemFiles(final Long problemId,final Long userId,final String visibility);
	public List<Object> getFilePaths(long fileId);
	public List<Object> getFilePathsBasedOnFileTypeIds(List<?> fileTypeIds);
	public int  updateFilePathsThumbnails(List<String> dataToSave);
}
