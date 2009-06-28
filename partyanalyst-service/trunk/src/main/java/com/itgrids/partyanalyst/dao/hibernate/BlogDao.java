package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.model.Blog;

public class BlogDao extends GenericDaoHibernate<Blog, Integer> implements IBlogDao{
	public BlogDao() {		
		super(Blog.class);
	}
}
