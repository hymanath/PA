package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.INewsDetailsDAO;
import com.itgrids.partyanalyst.model.NewsDetails;

public class NewsDetailsDAO  extends GenericDaoHibernate<NewsDetails,Long> implements INewsDetailsDAO {
	public NewsDetailsDAO() {
		super(NewsDetails.class);
	}
}
