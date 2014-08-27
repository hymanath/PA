package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IUserViewNewsDAO;
import com.itgrids.partyanalyst.model.UserViewNews;

public class UserViewNewsDAO extends GenericDaoHibernate<UserViewNews, Long> implements IUserViewNewsDAO{

	public UserViewNewsDAO() {
		super(UserViewNews.class);
		
	}
	
	public List checkFileExist(Long userId,Long fileId)
	{
		Query query = getSession().createQuery("select model.userViewNewsId from UserViewNews model where model.user.userId = :userId and model.file.fileId = :fileId");
		query.setParameter("userId", userId);
		query.setParameter("fileId", fileId);
		return query.list();
		
	}

}
