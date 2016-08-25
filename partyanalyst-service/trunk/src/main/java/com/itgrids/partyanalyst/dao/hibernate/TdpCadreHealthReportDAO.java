package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreHealthReportDAO;
import com.itgrids.partyanalyst.model.TdpCadreHealthReport;

public class TdpCadreHealthReportDAO extends GenericDaoHibernate<TdpCadreHealthReport, Long> implements ITdpCadreHealthReportDAO{

	public TdpCadreHealthReportDAO() {
		super(TdpCadreHealthReport.class);
		
	}
	public List<Object[]> getCadreHealthReport(Long tdpCadreId){
		Query query = getSession().createQuery("select TCHR.reportDate,TCHR.reportPath from TdpCadreHealthReport TCHR where TCHR.tdpCadreId = :tdpCadreId " +
				" order by TCHR.insertedTime desc");
		query.setParameter("tdpCadreId", tdpCadreId);
		return query.list();
	}

}
