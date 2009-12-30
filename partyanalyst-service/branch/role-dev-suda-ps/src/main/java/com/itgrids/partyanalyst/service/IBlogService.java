package com.itgrids.partyanalyst.service;

import java.util.List;

import org.appfuse.service.GenericManager;

import com.itgrids.partyanalyst.model.Blog;

public interface IBlogService extends GenericManager<Blog, Integer>{

	List<Blog> getAllBlogs();
}
