package com.itgrids.electoralconnect.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.electoralconnect.model.Comment;

public interface ICommentDAO  extends GenericDao<Comment, Long> {
	
	//public List<Object[]> getTop5Comments(Long announcementid);
	
	public List<Object[]> getAllComments(Long announcementid,int minIndex,int maxIndex);
	
	public Long getTotalCommentsCountByAnnouncementId(Long id);
}
