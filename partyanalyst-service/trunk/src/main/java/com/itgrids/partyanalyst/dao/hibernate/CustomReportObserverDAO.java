package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICustomReportObserverDAO;
import com.itgrids.partyanalyst.model.CustomReportObserver;
import com.itgrids.partyanalyst.model.CustomReportProgram;

public class CustomReportObserverDAO extends GenericDaoHibernate<CustomReportObserver, Long> implements ICustomReportObserverDAO {

	public CustomReportObserverDAO(){
		super(CustomReportObserver.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getObserverDetails(Long programId){
		Query query = getSession().createQuery(" select cro.customReportId,cro.tdpCadre.tdpCadreId,cro.tdpCadre.firstname," +
				" from CustomReportObserver cro " +
				" where cro.customReport.customReportProgramId = :programId and cro.isDeleted='N'");
	   query.setParameter("programId", programId);
	   return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getFileDetails(Long programId){
		Query query = getSession().createQuery("select crf.customReportFileId,crf.fileName,crf.path" +
				"from CustomReportFile crf where crf.customReport.customReportProgramId = :programId " +
				"and crf.isDeleted='N'");
		query.setParameter("programId", programId);
			 return query.list();
	}	
	@SuppressWarnings("unchecked")
	public List<Object[]> getImageDetails(Long programId){
		Query query = getSession().createQuery("select cri.customReportimageId,cri.imageName,cri.path" +
		        " from CustomReportImage cri where cri.customReport.customReportProgramId = :programId " +
			    "and cri.isDeleted='N'");
		query.setParameter("programId", programId);
		 return query.list();
	}	
	@SuppressWarnings("unchecked")
	public List<Object[]> getLocationDetails(Long programId){
		Query query =getSession().createQuery("select crl.customReportLocationId," +
				"crl.locationScope.locationScopeId," +
				"crl.locationScope.scope,crl.locationValue " +
				"from CustomReportLocation crl where crl.customReport.customReportProgramId = :programId" +
				"and crl.isDeleted='N'");
		query.setParameter("programId", programId);
		return query.list();
	}
}
