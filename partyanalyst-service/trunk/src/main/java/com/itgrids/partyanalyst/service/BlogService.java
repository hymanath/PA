package com.itgrids.partyanalyst.service;

import java.util.List;

import org.appfuse.service.impl.GenericManagerImpl;

import com.itgrids.partyanalyst.dao.hibernate.IBlogDao;
import com.itgrids.partyanalyst.model.Blog;

public class BlogService extends GenericManagerImpl<Blog, Integer> implements
		IBlogService {

	private IBlogDao iBlogDao;

	public BlogService(IBlogDao iBlogDao) {
		super(iBlogDao);
		setIBlogDao(iBlogDao);
	}

	public void setIBlogDao(IBlogDao iBlogDao) {
		this.iBlogDao = iBlogDao;
	}

	public IBlogDao getIBlogDao() {
		return iBlogDao;
	}

	public List<Blog> getAllBlogs() {
		return getIBlogDao().getAll();
	}

}
