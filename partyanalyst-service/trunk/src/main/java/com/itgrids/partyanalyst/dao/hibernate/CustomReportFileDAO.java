
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IcustomReportFileDAO;
import com.itgrids.partyanalyst.model.CustomReportFile;

public class CustomReportFileDAO extends GenericDaoHibernate<CustomReportFile, Long> implements IcustomReportFileDAO {

	public CustomReportFileDAO() {
		super(CustomReportFile.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getFileDetails(Long programId){
		Query query = getSession().createQuery("select crf.customReportFileId,crf.fileName,crf.path" +
				"from CustomReportFile crf where crf.customReport.customReportProgramId = :programId " +
				"and crf.isDeleted='N'");
		query.setParameter("programId", programId);
			 return query.list();
	}	
}