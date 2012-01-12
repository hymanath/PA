package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.INewsImportanceDAO;
import com.itgrids.partyanalyst.model.NewsImportance;

public class NewsImportanceDAO extends GenericDaoHibernate<NewsImportance, Long> implements INewsImportanceDAO {
	public NewsImportanceDAO() {
		super(NewsImportance.class);
	}
}
