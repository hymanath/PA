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
		
		Query query = getSession().createQuery("select distinct rf.file.fileId,rf.file.fileTitle,rf.file.fileDescription," +
				" rf.file.font.fontId,rf.file.descFont.fontId,rf.file.fileDate from ReportFiles rf,UserAddress ua " +
				" where rf.newsReport.newsReportId = :newsReportId  and rf.newsReport.user.userId = :userId and rf.file.fileId = ua.file.fileId and ua.regionScopes.regionScopesId = 2 and rf.file.isDeleted != 'Y'");
		query.setParameter("newsReportId", newsReportId);
		query.setParameter("userId", userId);
		
		return query.list();
	}
    public List<Object[]> getStateLvlReportDetailsByKey(Long newsReportId,String key){
		
    	Query query = getSession().createQuery("select distinct rf.file.fileId,rf.file.fileTitle,rf.file.fileDescription," +
				" rf.file.font.fontId,rf.file.descFont.fontId,rf.file.fileDate from ReportFiles rf,UserAddress ua " +
				" where  rf.newsReport.reportKey = :key and rf.file.fileId = ua.file.fileId and ua.regionScopes.regionScopesId = 2 and rf.file.isDeleted != 'Y'");
		
		query.setParameter("key", key);
		
		return query.list();
	}
    public List<Object[]> getOtherLvlReportDetails(Long newsReportId,Long userId){
		//0fileId 1fileTitle,2fileDesc,3newsdesc,4scopid,5locval,6distid,7distName,8scopeType,9fontId,10descFontId,11fileDate,12userAddId 
    	//0fileId 1fileTitle,2fileDesc                          ,3distid,4distName           ,5fontId,6descFontId, 7fileDate 
		Query query = getSession().createQuery("select distinct rf.file.fileId,rf.file.fileTitle,rf.file.fileDescription," +
				" ua.district.districtId,ua.district.districtName, " +
				" rf.file.font.fontId,rf.file.descFont.fontId,rf.file.fileDate from ReportFiles rf,UserAddress ua where rf.newsReport.newsReportId = :newsReportId" +
				" and rf.newsReport.user.userId = :userId  and rf.file.fileId = ua.file.fileId and ua.regionScopes.regionScopesId != 2 and rf.file.isDeleted != 'Y' order by ua.district.districtId ");
		query.setParameter("newsReportId", newsReportId);
		query.setParameter("userId", userId);
		
		return query.list();
	}
    
 public List<Object[]> getOtherLvlReportDetailsByKey(Long newsReportId,String key){
		
	 Query query = getSession().createQuery("select distinct rf.file.fileId,rf.file.fileTitle,rf.file.fileDescription," +
				" ua.district.districtId,ua.district.districtName, " +
				" rf.file.font.fontId,rf.file.descFont.fontId,rf.file.fileDate from ReportFiles rf,UserAddress ua where " +
				"   rf.newsReport.reportKey = :key and rf.file.fileId = ua.file.fileId and ua.regionScopes.regionScopesId != 2 and rf.file.isDeleted != 'Y' order by ua.district.districtId ");
		
		query.setParameter("key", key);
		
		return query.list();
	}
 
 @SuppressWarnings("unchecked")
	public List<Object[]> getFileNewsReports(Long userId,Integer startIndex,Integer maxIndex)
	{
		
		StringBuilder str = new StringBuilder();
		str.append("select model.newsReport.newsReportId,model.newsReport.description,model.newsReport.createdDate,model.file.fileId from ReportFiles model ");
		if(userId != null && userId != 0)
		str.append("where model.newsReport.user.userId=:userId");
		str.append(" order by model.newsReport.createdDate desc ");
		
		Query query = getSession().createQuery(str.toString());
		if(userId != null && userId != 0)
		query.setParameter("userId", userId);
		
		if(startIndex != null)
		 query.setFirstResult(startIndex);
		if(maxIndex != null)
		 query.setMaxResults(maxIndex);
		
		return query.list();
		
	}
	public Integer deleteReportFiles(Long newsReportId)
	 {
		 Query query = getSession().createQuery("delete from ReportFiles model where model.newsReport.newsReportId =:newsReportId");
		 query.setParameter("newsReportId", newsReportId);
		 return query.executeUpdate();
		 
	 }
	
}
