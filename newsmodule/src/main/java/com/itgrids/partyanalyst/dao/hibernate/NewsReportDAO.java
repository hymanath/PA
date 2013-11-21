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
	
   public Long checkValidUserForReport(Long userId,Long reportId){
	   Query query = getSession().createQuery("select count(model.newsReportId) from NewsReport model where " +
	   		" model.user.userId = :userId and model.newsReportId = :reportId ");
	   query.setParameter("userId", userId);
	   query.setParameter("reportId", reportId);
	   return (Long)query.uniqueResult();
   }
   
   public Long checkValidReportKey(String key){
	   Query query = getSession().createQuery("select count(model.newsReportId) from NewsReport model where " +
	   		" model.reportKey = :key ");
	   query.setParameter("key", key);
	   return (Long)query.uniqueResult();
   }
   
   public void updateKey(String key){
	   Query query = getSession().createQuery("update NewsReport model set model.reportKey ='' where model.reportKey = :key ");
		   query.setParameter("key", key);
		   query.executeUpdate();
   }
   
   public void updateNewKey(String key,Long reportId){
	   Query query = getSession().createQuery("update NewsReport model set model.reportKey = :key where model.newsReportId = :reportId ");
		   query.setParameter("key", key);
		   query.setParameter("reportId", reportId);
		   query.executeUpdate();
   }
}
