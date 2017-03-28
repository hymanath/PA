
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICustomReportImageDAO;
import com.itgrids.partyanalyst.model.CustomReportFile;
import com.itgrids.partyanalyst.model.CustomReportImage;

public class CustomReportImageDAO extends GenericDaoHibernate<CustomReportImage, Long> implements ICustomReportImageDAO {

	public CustomReportImageDAO() {
		super(CustomReportImage.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getImageDetails(Long programId){
		Query query = getSession().createQuery("select cri.customReportimageId,cri.imageName,cri.path" +
		        " from CustomReportImage cri where cri.customReport.customReportProgramId = :programId " +
			    "and cri.isDeleted='N'");
		query.setParameter("programId", programId);
		 return query.list();
	}	
}