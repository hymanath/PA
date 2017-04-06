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

	@SuppressWarnings("unchecked")
	public String getDescriptionForReportId(Long reportId) {
		Query query = getSession().createQuery("select model.description " +
				" from CustomReport model  where model.customReportId = :reportId and model.isDeleted='N' ");
		query.setParameter("reportId", reportId);
		return (String) query.uniqueResult();	
	}
	@SuppressWarnings("unchecked")
	public CustomReport getmodelForCustomreportId(Long reportId) {
		Query query = getSession().createQuery("select model  from CustomReport model  " +
				" where model.customReportId = :reportId ");
		query.setParameter("reportId", reportId);
		return (CustomReport) query.uniqueResult();
	}
	
	public List<Object[]> getReportsOfProgram(Long programId,String type){
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.customReportId,model.reportName,model.isSubmitted "
				+ " from  CustomReport model "
				+ " where model.isDeleted='N' and model.customReportProgramId=:programId ");
		
		if(type != null && !type.trim().isEmpty())
			sb.append(" and model.isSubmitted=:type ");
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("programId", programId);
		if(type != null && !type.trim().isEmpty())
			query.setParameter("type", type);
		return query.list();
		
	}
	
	public Integer updateCustomReportStatus(Long reportId){
		Query query = getSession().createQuery(" update CustomReport model set model.isSubmitted='N' where model.customReportId=:reportId ");
		query.setParameter("reportId", reportId);
		return query.executeUpdate();
	}
}
