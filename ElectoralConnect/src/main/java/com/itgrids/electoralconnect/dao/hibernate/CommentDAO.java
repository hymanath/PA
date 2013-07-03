package com.itgrids.electoralconnect.dao.hibernate;


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
	
	public Long getTotalCommentsCountByAnnouncementId(Long id)
	{
		Query query = getSession().createQuery("select count(*) from Comment model " +
				" where model.announcements.announcementId = :id");
		query.setParameter("id", id);
		return (Long) query.uniqueResult();
	}
}
