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
				" where model.announcements.announcementId = :announcementid and  model.isDelete = 'NO' order by model.commentId desc");
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
				" where model.announcements.announcementId = :id and model.isDelete='NO'");
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
				"model.time,model.announcements,model.isDelete from Comment model where  " +
				" Date(model.time) between :startDate and :endDate and model.isDelete = 'NO'");
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
				" Date(model.time) between :startDate and :endDate and model.isDelete='NO'");
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
				"model.time,model.announcements,model.isDelete  from Comment model where model.isDelete = 'NO'");
		
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
		Query query = getSession().createQuery("select count(*) from Comment model where model.isDelete = 'NO'");
		
		return (Long) query.uniqueResult();
	}
	
	/**
	 * This DAO is used to abuse the comment
	 * @param Long commentId
	 * @return int
	 * @date 03-07-2013
	 */
	public int abuseTheComment(Long commentId)
	{
		Query query = getSession().createQuery("update Comment model set model.isDelete = 'YES' " +
				" where model.commentId = :commentId");
		query.setParameter("commentId", commentId);
		
		int x = query.executeUpdate();
		getSession().flush();
		return  x;	
	}
	/**
	 * This DAO is used to get Top 5 Notifications
	 * @param int startIndex
	 * @param int maxIndex
	 * @return List<Object[]>
	 * @date 04-07-2013
	 */
	/*@SuppressWarnings("unchecked")
	public List<Object[]> getTop5Notifications(int startIndex,int maxIndex)
	{
		Query query = getSession().createQuery("select count(*),model.announcements from Comment model " +
				"  where model.announcements.announcementType.announcementTypeId = 1" +
				"  group by model.announcements.announcementId order by model.announcements.announcementId desc");
		query.setFirstResult(startIndex);
		query.setMaxResults(maxIndex);
		return query.list();
	}*/
	/**
	 * This DAO is used to get Top 5 PressReleases
	 * @param int startIndex
	 * @param int maxIndex
	 * @return List<Object[]>
	 * @date 04-07-2013
	 */
	/*@SuppressWarnings("unchecked")
	public List<Object[]> getTop5PressReleases(int startIndex,int maxIndex)
	{
		Query query = getSession().createQuery("select count(*),model.announcements from Comment model " +
				"  where model.announcements.announcementType.announcementTypeId = 2" +
				"  group by model.announcements.announcementId order by model.announcements.announcementId desc");
		query.setFirstResult(startIndex);
		query.setMaxResults(maxIndex);
		return query.list();
	}*/
	/**
	 * This DAO is used to get the comments count based on announcement id
	 * @param Long announcementId
	 * @return Long
	 * @date 05-07-2013
	 */
	/*public Long getCommentCountByAnnouncementId(Long announcementId)
	{
		Query query = getSession().createQuery("select count(*) from Comment model where " +
				" model.announcements.announcementId = :announcementId");
		query.setParameter("announcementId", announcementId);
		
		return (Long) query.uniqueResult();
	}*/
}
