package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.INewsImportanceDAO;
import com.itgrids.partyanalyst.model.NewsImportance;

public class NewsImportanceDAO extends GenericDaoHibernate<NewsImportance, Long> implements INewsImportanceDAO {
	public NewsImportanceDAO() {
		super(NewsImportance.class);
	}
	public List<Object[]> getNewsImportanceDetails(){
		   return getHibernateTemplate().find("select model.newsImportanceId,model.importance from NewsImportance  model order by model.importance ");
	   }
}
