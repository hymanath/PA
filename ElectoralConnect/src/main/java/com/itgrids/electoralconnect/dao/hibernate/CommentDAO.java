package com.itgrids.electoralconnect.dao.hibernate;

import java.io.Serializable;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.electoralconnect.dao.ICommentDAO;
import com.itgrids.electoralconnect.dao.IUserRolesDAO;
import com.itgrids.electoralconnect.model.Comment;
import com.itgrids.electoralconnect.model.UserRoles;


public class CommentDAO extends GenericDaoHibernate<Comment, Long> implements ICommentDAO{

	public CommentDAO() {
		super(Comment.class);
	}

}
