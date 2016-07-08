package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreReportDAO;
import com.itgrids.partyanalyst.model.TdpCadreReport;

public class TdpCadreReportDAO extends
		GenericDaoHibernate<TdpCadreReport, Long> implements ITdpCadreReportDAO {
	public TdpCadreReportDAO(){
		super(TdpCadreReport.class);
	}
	
	public List<Object[]> getCadreReportDetails(Long cadreId){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct TCR.reportType, TCR.reportName, TCR.reportPath, TCR.insertedTime from TdpCadreReport TCR where TCR.tdpCadreId = :cadreId order by TCR.reportType");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("cadreId", cadreId);
		return query.list();
	}
	
}
