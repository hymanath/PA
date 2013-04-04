package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.model.ContentNotes;
import com.itgrids.partyanalyst.model.FileGallary;

public interface IContentNotesDAO extends GenericDao<ContentNotes,Long>{
	
	public List<Object[]> getContentNotesByContentId(Long contentId);
	
	public List<Object[]> getContentNotesCountByContentIds(List<Long> contentIds);
	
	public List<FileGallary> getNotedNews(List<Long> contentIds);
	
	public Long getNotedNewsCount(FileVO inputs,List<Long> contentIds);

}
