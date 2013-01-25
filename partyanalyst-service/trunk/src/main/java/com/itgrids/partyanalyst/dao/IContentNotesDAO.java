package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ContentNotes;

public interface IContentNotesDAO extends GenericDao<ContentNotes,Long>{
	
	public List<Object[]> getContentNotesByContentId(Long contentId);
	
	public List<Object[]> getContentNotesCountByContentIds(List<Long> contentIds);

}
