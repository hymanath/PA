package com.itgrids.electoralconnect.dao.hibernate;


import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.electoralconnect.dao.ICommentDAO;
import com.itgrids.electoralconnect.model.Comment;



public class CommentDAO extends GenericDaoHibernate<Comment, Long> implements ICommentDAO{

	public CommentDAO() {
		super(Comment.class);
	}

	/**
	 * This DAO is used for getting only top 5 Comments
	 * @param Long announcementid
	 * @return List<Object[]>
	 * @date 03-07-2013
	 */
	/*@SuppressWarnings("unchecked")
	public List<Object[]> getTop5Comments(Long announcementid)
	{
		Query query = getSession().createQuery("select model.commentId,model.comment,model.user,model.time from Comment model " +
				" where model.announcements.announcementId = :announcementid order by model.commentId desc ");
		query.setParameter("announcementid", announcementid);
		query.setFirstResult(0);
		query.setMaxResults(5);
		return query.list();
	}*/
	
	/**
	 * This DAO is Used to get all comments
	 * @param Long announcementid
	 * @param int minIndex
	 * @param int maxIndex
	 * @return List<Object[]>
	 * @date 03-07-2013
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllComments(Long announcementid,int minIndex,int maxIndex)
	{
		Query query = getSession().createQuery("select model.commentId,model.comment,model.user,model.time from Comment model " +
				" where model.announcements.announcementId = :announcementid order by model.commentId desc");
		query.setParameter("announcementid", announcementid);
		query.setFirstResult(minIndex);
		query.setMaxResults(maxIndex);
		return query.list();
	}
	/**
	 * This DAO is used to get the total Count for the selected announcement
	 * @param Long id
	 * @return Long
	 * @date 03-07-2013
	 */
	public Long getTotalCommentsCountByAnnouncementId(Long id)
	{
		Query query = getSession().createQuery("select count(*) from Comment model " +
				" where model.announcements.announcementId = :id");
		query.setParameter("id", id);
		return (Long) query.uniqueResult();
	}
	
	/**
	 * This DAO is used to get the comments between selected dates
	 * @param Long announcementId
	 * @param Date startDate
	 * @param Date endDate
	 * @param Long startIndex
	 * @param Long maxIndex
	 * @return List<Object[]>
	 * @date 03-07-2013
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getCommentsBetwnnSelectedDates(Date startDate,Date endDate,int statIndex,int maxIndex)
	{
		Query query = getSession().createQuery("select model.commentId,model.comment,model.user," +
				"model.time,model.announcements from Comment model where  Date(model.time) between :startDate and :endDate");
		//query.setParameter("announcementId", announcementId);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		query.setFirstResult(statIndex);
		query.setMaxResults(maxIndex);
		return query.list();
	} 
	
	/**
	 * This DAO is used to get the comments Count between selected dates
	 * @param Long announcementId
	 * @param Date startDate
	 * @param Date endDate
	 * @return Long
	 * @date 03-07-2013
	 */
	public Long getCommentsCountBetweenSelectedDates(Date startDate,Date endDate)
	{
		Query query = getSession().createQuery("select count(*) from Comment model where " +
				" Date(model.time) between :startDate and :endDate");
		//query.setParameter("announcementId", announcementId);
		query.setParameter("startDate", startDate);
		query.setParameter("endDate", endDate);
		return (Long) query.uniqueResult();
	} 
	/**
	 * This DAO is used for getting all comments 
	 * @param Long startIndex
	 * @param Long maxIndex
	 * @return List<Object[]>
	 * @date 03-07-2013
	 */
	@SuppressWarnings("unchecked")
	public List<Object[]> getAllComments(int startIndex,int maxIndex)
	{
		Query query = getSession().createQuery("select model.commentId,model.comment,model.user," +
				"model.time,model.announcements  from Comment model");
		
		query.setFirstResult(startIndex);
		query.setMaxResults(maxIndex);
		return query.list();
	}
	
	/**
	 * This DAO is used to get count of all comments
	 * @return Long
	 * @date 03-07-2013
	 */
	public Long getTotalComments()
	{
		Query query = getSession().createQuery("select count(*) from Comment model");
		
		return (Long) query.uniqueResult();
	}
}
