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
		queryStr.append(" select distinct TCR.reportType, TCR.reportName, TCR.reportPath, TCR.insertedTime, tdpCadreReportStatus.status from TdpCadreReport TCR " +
				" left join TCR.tdpCadreReportStatus tdpCadreReportStatus where TCR.tdpCadreId = :cadreId order by TCR.reportType");   
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("cadreId", cadreId);
		return query.list();
	}
	/*public List<Object[]> getCadreReportList(Long cadreId){  
		Query query = getSession().createQuery("select TCR.tdpCadreId, TCR.reportName, TCR.reportPath from TdpCadreReport TCR where TCR.tdpCadreId = :cadreId");
		query.setParameter("cadreId", cadreId);
		return query.list();
	}*/
	
	public List<Object[]> getCadreReportDetailsByCadreList(List<Long> cadreIds){
		Query query = getSession().createQuery("select model.tdpCadre.tdpCadreId," +
											" model.reportType," +
											" model.tdpCadreReportStatus.tdpCadreReportStatusId," +
											" model.tdpCadreReportStatus.status," +
											" model.reportPath" +
											" from TdpCadreReport model" +
											" where model.tdpCadre.tdpCadreId in (:cadreIds)");
		query.setParameterList("cadreIds", cadreIds);
		return query.list();
	}
}
