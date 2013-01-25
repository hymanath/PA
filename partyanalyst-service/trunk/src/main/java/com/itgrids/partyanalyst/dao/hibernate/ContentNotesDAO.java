package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IContentNotesDAO;
import com.itgrids.partyanalyst.model.ContentNotes;

public class ContentNotesDAO extends GenericDaoHibernate<ContentNotes, Long>implements IContentNotesDAO {
	
	public ContentNotesDAO(){
		super(ContentNotes.class);		
	}
	
	public List<Object[]> getContentNotesByContentId(Long contentId){		
		
		String queryString = "select model.contentNotesId ," +
				" model.notes from ContentNotes model where model.fileGallary.fileGallaryId = ? " +
				" and model.isDelete = ?";
		
		Query query = getSession().createQuery(queryString);
		
		query.setParameter(0, contentId);
		query.setParameter(1, "false");
		
		return query.list();
	}
	
	public List<Object[]> getContentNotesCountByContentIds(List<Long> contentIds){
		
		
		Query query = getSession().createQuery("select model.fileGallary.fileGallaryId , model.fileGallary.fileGallaryId " +
				" from ContentNotes model where " +
				"model.fileGallary.fileGallaryId in(:contentIds) and model.isDelete = :delInd");
		
		query.setParameterList("contentIds", contentIds);
		query.setParameter("delInd", "false");
		
		
		return query.list();
		
		
	}
	
	
	
}
