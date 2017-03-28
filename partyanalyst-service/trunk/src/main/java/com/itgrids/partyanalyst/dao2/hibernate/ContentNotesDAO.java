package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IContentNotesDAO;
import com.itgrids.partyanalyst.dto.FileVO;
import com.itgrids.partyanalyst.model.ContentNotes;
import com.itgrids.partyanalyst.model.FileGallary;

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

	/**
	 * This Dao Method Is Used For Getting All Noted News
	 * @param List<Long> contentIds
	 * @return List<FileGallary>
	 * @date 01-04-2013
	 */
	public List<FileGallary> getNotedNews(List<Long> contentIds)
	{
		Query query = getSession().createQuery("select DISTINCT model.fileGallary from ContentNotes model " +
				"where model.fileGallary.fileGallaryId in (:contentIds) and model.isDelete = :delInd");
		query.setParameterList("contentIds", contentIds);
		query.setParameter("delInd", "false");
		return query.list();
	}

	/**
	 * this DAO is used for getting count of all Noted news
	 * @param FileVO 
	 * @param List<Long> 
	 * @return Long
	 * @date 03-04-2013
	 */
	public Long getNotedNewsCount(FileVO inputs, List<Long> contentIds) {
		String queryString = "select count(model.fileGallary.fileGallaryId) from ContentNotes model " +
				"where model.fileGallary.fileGallaryId in (:contentIds) and model.isDelete = :delInd " +
				" and date(model.fileGallary.file.fileDate) >= :fromDate and " +
				"date(model.fileGallary.file.fileDate) <= :toDate";
		Query query = getSession().createQuery(queryString);
		query.setParameterList("contentIds", contentIds);
		query.setParameter("delInd", "false");
		query.setDate("fromDate", inputs.getExistingFrom());
		query.setDate("toDate", inputs.getIdentifiedOn());
		return (Long) query.uniqueResult();
	}
	
	
	
}
