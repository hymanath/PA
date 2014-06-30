package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IActivityReportDAO;
import com.itgrids.partyanalyst.model.ActivityReport;

public class ActivityReportDAO   extends GenericDaoHibernate<ActivityReport, Long>  implements IActivityReportDAO{
    public ActivityReportDAO(){
    	super(ActivityReport.class);
    }
    
    public String getCategoeryIds(String key)
    {
    	Query query = getSession().createQuery("select model.categories from ActivityReport model " +
    			" where model.reportKey = :key");
    	query.setParameter("key", key);
    	return (String) query.uniqueResult();
    }
    public Object[] getCategoeryAndPartyIds(String key)
    {
    	Query query = getSession().createQuery("select model.categories,model.partyIds from ActivityReport model " +
    			" where model.reportKey = :key");
    	query.setParameter("key", key);
    	return  (Object[]) query.uniqueResult();
    }
}
