package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICustomReportDAO;
import com.itgrids.partyanalyst.model.CustomReport;
import java.util.List;

public class CustomReportDAO extends GenericDaoHibernate<CustomReport, Long> implements ICustomReportDAO {

	public CustomReportDAO() {
		super(CustomReport.class);
	}

	public List<Object[]> getTotalExpertedReports(Long customReportProgramId) {
			Query query = getSession().createQuery("select " +
					" model.isSubmitted, " +
					" count(distinct model.customReportId) " +
					" from " +
					" CustomReport model " +
					" where " +
					" model.customReport.customReportProgramId = :customReportProgramId " +
					" and model.isDeleted='N' " +
					" GROUP BY customReport.isSubmitted  ");
			query.setParameter("customReportProgramId", customReportProgramId);
			return query.list();	
	}
}
