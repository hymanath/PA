
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICustomReportFileDAO;
import com.itgrids.partyanalyst.model.CustomReportFile;

public class CustomReportFileDAO extends GenericDaoHibernate<CustomReportFile, Long> implements ICustomReportFileDAO {

	public CustomReportFileDAO() {
		super(CustomReportFile.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getFileDetails(Long programId){
		Query query = getSession().createQuery("select crf.customReportId,crf.fileName,crf.path,crf.customReportFileId " +
				"from CustomReportFile crf where crf.customReport.customReportProgramId = :programId " +
				"and crf.isDeleted='N'");
		query.setParameter("programId", programId);
			 return query.list();
	}	
	@SuppressWarnings("unchecked")
	  public List<Object[]>  getFileForAReport(Long reportId){
	    Query query=getSession().createQuery("select  model.customReportFileId,model.fileName,model.path " 
	        + " from CustomReportFile model"        
	        + " where model.customReportId = :reportId and model.isDeleted = 'N' ");
	    query.setParameter("reportId", reportId);
	     return query.list();
	  }
	@SuppressWarnings("unchecked")
	public List<Object[]> getFileDetailsForReportId(Long programId,String type){
		StringBuilder queryStr = new StringBuilder();
		queryStr.append("select model.customReportId," +
				" model.fileName,model.path," +
				" model.customReportFileId " +
				" from CustomReportFile model " +
				" where " +
				" model.customReport.customReportProgramId = :programId " +
				" and model.isDeleted='N '");
		if(type != null && type.length() > 0){
			queryStr.append(" and model.customReport.isSubmitted =:type ");
		}
		Query query = getSession().createQuery(queryStr.toString());
			  query.setParameter("programId", programId);
		if(type != null && type.length() > 0){
			query.setParameter("type", type);
		}
		 return query.list();
	}		
}