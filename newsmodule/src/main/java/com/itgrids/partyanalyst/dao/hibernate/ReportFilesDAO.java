package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IReportFilesDAO;
import com.itgrids.partyanalyst.model.ReportFiles;

public class ReportFilesDAO extends GenericDaoHibernate<ReportFiles, Long> implements IReportFilesDAO{

	public ReportFilesDAO() {
		super(ReportFiles.class);
	
	}
   
	public List<Object[]> getStateLvlReportDetails(Long newsReportId,Long userId){
		
		Query query = getSession().createQuery("select distinct(rf.file.fileId),rf.file.fileTitle,rf.file.fileDescription," +
				" rf.file.newsDescription,rf.file.regionScopes.regionScopesId,rf.file.locationValue,rf.file.font.fontId from ReportFiles rf " +
				" where rf.newsReport.newsReportId = :newsReportId  and rf.file.regionScopes.regionScopesId = 2");
		query.setParameter("newsReportId", newsReportId);
		
		return query.list();
	}
    public List<Long> getStateLvlReportFontDetails(Long newsReportId,Long userId){
		
		Query query = getSession().createQuery("select distinct(rf.file.fileId) from ReportFiles rf " +
				" where rf.newsReport.newsReportId = :newsReportId  and rf.file.regionScopes.regionScopesId = 2 and rf.file.font.fontId is not null ");
		query.setParameter("newsReportId", newsReportId);
		
		return query.list();
	}
    public List<Object[]> getOtherLvlReportDetails(Long newsReportId,Long userId){
		
		Query query = getSession().createQuery("select distinct(rf.file.fileId),rf.file.fileTitle,rf.file.fileDescription," +
				" rf.file.newsDescription,rf.file.regionScopes.regionScopesId,rf.file.locationValue, " +
				" rf.file.userAddress.district.districtId,rf.file.userAddress.district.districtName, " +
				" rf.file.regionScopes.scope,rf.file.font.fontId from ReportFiles rf where rf.newsReport.newsReportId = :newsReportId" +
				"   and rf.file.regionScopes.regionScopesId != 2 order by rf.file.userAddress.district.districtId ");
		query.setParameter("newsReportId", newsReportId);
		
		return query.list();
	}
    
 public List<Long> getOtherLvlReportFontDetails(Long newsReportId,Long userId){
		
		Query query = getSession().createQuery("select distinct(rf.file.fileId) from ReportFiles rf where rf.newsReport.newsReportId = :newsReportId" +
				"   and rf.file.regionScopes.regionScopesId != 2   and rf.file.font.fontId is not null ");
		query.setParameter("newsReportId", newsReportId);
		
		return query.list();
	}
}
