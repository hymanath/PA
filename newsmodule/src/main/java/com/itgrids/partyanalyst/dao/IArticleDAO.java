package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.Article;

public interface IArticleDAO  extends GenericDao<Article,Long>{

	public List<String> getArticles();
	public List<Article> getTotalArticleNews();
}
