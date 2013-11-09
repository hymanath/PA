package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.INewsReportDAO;
import com.itgrids.partyanalyst.model.NewsReport;

public class NewsReportDAO extends GenericDaoHibernate<NewsReport, Long> implements INewsReportDAO{

	public NewsReportDAO() {
		super(NewsReport.class);
	
	}
	public List<Object[]> getNewsReports(Long userId)
	{
		
		StringBuilder str = new StringBuilder();
		str.append("select model.newsReportId,model.description,model.createdDate from NewsReport model ");
		if(userId != null && userId != 0)
		str.append("where user.userId=:userId");
		Query query = getSession().createQuery(str.toString());
		if(userId != null && userId != 0)
		query.setParameter("userId", userId);
		return query.list();
		
	}
	

}
