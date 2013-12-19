package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IActivityReportDAO;
import com.itgrids.partyanalyst.model.ActivityReport;

public class ActivityReportDAO   extends GenericDaoHibernate<ActivityReport, Long>  implements IActivityReportDAO{
    public ActivityReportDAO(){
    	super(ActivityReport.class);
    }
}
