package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IArticleDAO;
import com.itgrids.partyanalyst.model.Article;

public class ArticleDAO extends GenericDaoHibernate<Article,Long> implements IArticleDAO{
	public ArticleDAO(){
		super(Article.class);
	}
	public List<String> getArticles(){
		Query query = getSession().createQuery("select content from Article where id > 511 and id < 520");
		return query.list();
	}
	
	 @SuppressWarnings("unchecked")
		public List<Article> getTotalArticleNews(){
			Query query = getSession().createQuery("select model from Article model");
			
			return query.list();
		 }
		 
	 
}
