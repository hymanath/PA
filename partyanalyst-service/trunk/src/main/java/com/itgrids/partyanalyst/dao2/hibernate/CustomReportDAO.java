package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICustomReportDAO;
import com.itgrids.partyanalyst.model.CustomReport;

public class CustomReportDAO extends GenericDaoHibernate<CustomReport, Long> implements ICustomReportDAO {

	public CustomReportDAO() {
		super(CustomReport.class);
	}
	
	public List<Object[]> getTotalExpectedReports(Long customReportProgramId) {
		Query query = getSession().createQuery("select model.isSubmitted, count(distinct model.customReportId) " +
				" from CustomReport model " +
				" where model.customReportProgramId = :customReportProgramId " +
				" and model.isDeleted='N' " +
				" GROUP BY model.isSubmitted  ");
		query.setParameter("customReportProgramId", customReportProgramId);
		return query.list();	
}

}
