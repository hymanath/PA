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
				" rf.file.newsDescription,rf.file.regionScopes.regionScopesId,rf.file.locationValue,rf.file.font.fontId,rf.file.descFont.fontId,rf.file.fileDate from ReportFiles rf " +
				" where rf.newsReport.newsReportId = :newsReportId  and rf.newsReport.user.userId = :userId and rf.file.regionScopes.regionScopesId = 2 and rf.file.isDeleted != 'Y'");
		query.setParameter("newsReportId", newsReportId);
		query.setParameter("userId", userId);
		
		return query.list();
	}
    public List<Object[]> getStateLvlReportDetailsByKey(Long newsReportId,String key){
		
    	Query query = getSession().createQuery("select distinct(rf.file.fileId),rf.file.fileTitle,rf.file.fileDescription," +
				" rf.file.newsDescription,rf.file.regionScopes.regionScopesId,rf.file.locationValue,rf.file.font.fontId,rf.file.descFont.fontId,rf.file.fileDate from ReportFiles rf " +
				" where  rf.newsReport.reportKey = :key  and rf.file.regionScopes.regionScopesId = 2 and rf.file.isDeleted != 'Y'");
		
		query.setParameter("key", key);
		
		return query.list();
	}
    public List<Object[]> getOtherLvlReportDetails(Long newsReportId,Long userId){
		
		Query query = getSession().createQuery("select distinct(rf.file.fileId),rf.file.fileTitle,rf.file.fileDescription," +
				" rf.file.newsDescription,rf.file.regionScopes.regionScopesId,rf.file.locationValue, " +
				" rf.file.userAddress.district.districtId,rf.file.userAddress.district.districtName, " +
				" rf.file.regionScopes.scope,rf.file.font.fontId,rf.file.descFont.fontId,rf.file.fileDate,rf.file.userAddress.userAddressId from ReportFiles rf where rf.newsReport.newsReportId = :newsReportId" +
				"     and rf.newsReport.user.userId = :userId  and rf.file.regionScopes.regionScopesId != 2 and rf.file.isDeleted != 'Y' order by rf.file.userAddress.district.districtId ");
		query.setParameter("newsReportId", newsReportId);
		query.setParameter("userId", userId);
		
		return query.list();
	}
    
 public List<Object[]> getOtherLvlReportDetailsByKey(Long newsReportId,String key){
		
	 Query query = getSession().createQuery("select distinct(rf.file.fileId),rf.file.fileTitle,rf.file.fileDescription," +
				" rf.file.newsDescription,rf.file.regionScopes.regionScopesId,rf.file.locationValue, " +
				" rf.file.userAddress.district.districtId,rf.file.userAddress.district.districtName, " +
				" rf.file.regionScopes.scope,rf.file.font.fontId,rf.file.descFont.fontId,rf.file.fileDate,rf.file.userAddress.userAddressId from ReportFiles rf where " +
				"   rf.newsReport.reportKey = :key and rf.file.regionScopes.regionScopesId != 2 and rf.file.isDeleted != 'Y' order by rf.file.userAddress.district.districtId ");
		
		query.setParameter("key", key);
		
		return query.list();
	}
}
