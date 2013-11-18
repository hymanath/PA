package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.INewsResponseDAO;
import com.itgrids.partyanalyst.model.NewsResponse;


public class NewsResponseDAO extends GenericDaoHibernate<NewsResponse,Long> implements INewsResponseDAO {

	public NewsResponseDAO(){
		super(NewsResponse.class);
	}
}
