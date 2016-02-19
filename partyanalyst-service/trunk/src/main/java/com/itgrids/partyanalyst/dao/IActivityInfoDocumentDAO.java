package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.EventDocumentVO;
import com.itgrids.partyanalyst.dto.SearchAttributeVO;
import com.itgrids.partyanalyst.model.ActivityInfoDocument;

public interface IActivityInfoDocumentDAO extends GenericDao<ActivityInfoDocument, Long>{
	public List<Object[]> getEventDocuments(EventDocumentVO inputVO,Date startDate,Date endDate);
	public List<Object[]> getLocations(EventDocumentVO inputVO,Date startDate,Date endDate,String type);
	
	
	public Integer deleteEventUploadFilebyActivityInfoDocId(List<Long> activityInfoDocIdList);
	public List<Object[]> getAvailableDates(EventDocumentVO inputVO,Date startDate,Date endDate,String type);
	public Long getEventDocumentsCount(EventDocumentVO inputVO,Date startDate,Date endDate);
	public List<Object[]> getLocationWiseImageCount(EventDocumentVO inputVO,Date startDate,Date endDate,String type);
	
	public List<Object[]> getActivityDocumentsImagesByLevelIdAndLevelValue(Long levelId,Long levelValue,Long day,Long activityScopeId,Date activityDate,Integer startIndex,Integer maxIndex);
	
	public Long getActivityDocumentsImagesCountByLevelIdAndLevelValue(Long levelId,Long levelValue,Long day,Long activityScopeId,Date activityDate);
	public List<Object[]> getActivityInfoImagesCount(SearchAttributeVO inputVO,Long stateId);
	
	public List<Object[]> getEventDocumentsByLocationInfo(EventDocumentVO inputVO,Date startDate,Date endDate);
	public Long getEventDocumentsCountByLocationInbfo(EventDocumentVO inputVO,Date startDate,Date endDate);
	public List<Object[]> getDayWiseActivityInfoImagesCount(SearchAttributeVO inputVO,Long stateId);
	public List<Object[]> getActivityQuestionnairesCountsByLocation(SearchAttributeVO searchAttributeVO,Long stateId);
	
	
}
