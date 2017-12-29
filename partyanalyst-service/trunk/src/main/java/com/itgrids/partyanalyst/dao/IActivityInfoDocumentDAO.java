package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.EventDocumentVO;
import com.itgrids.partyanalyst.dto.SearchAttributeVO;
import com.itgrids.partyanalyst.model.ActivityInfoDocument;

public interface IActivityInfoDocumentDAO extends GenericDao<ActivityInfoDocument, Long>{
	public List<Object[]> getEventDocuments(EventDocumentVO inputVO,Date startDate,Date endDate,Long userAccessLevelId,Set<Long> userAccessLevelValues);
	public List<Object[]> getLocations(EventDocumentVO inputVO,Date startDate,Date endDate,String type);
	
	
	public Integer deleteEventUploadFilebyActivityInfoDocId(List<Long> activityInfoDocIdList);
	public List<Object[]> getAvailableDates(EventDocumentVO inputVO,Date startDate,Date endDate,String type);
	public Long getEventDocumentsCount(EventDocumentVO inputVO,Date startDate,Date endDate,Long userAccessLevelId,Set<Long> userAccessLevelValues);
	public List<Object[]> getLocationWiseImageCount(EventDocumentVO inputVO,Date startDate,Date endDate,String type);
	
	public List<Object[]> getActivityDocumentsImagesByLevelIdAndLevelValue(Long levelId,Long levelValue,Long day,Long activityScopeId,Date activityDate,Integer startIndex,Integer maxIndex);
	
	public Long getActivityDocumentsImagesCountByLevelIdAndLevelValue(Long levelId,Long levelValue,Long day,Long activityScopeId,Date activityDate);
	public List<Object[]> getActivityInfoImagesCount(SearchAttributeVO inputVO,Long stateId);
	
	public List<Object[]> getEventDocumentsByLocationInfo(EventDocumentVO inputVO,Date startDate,Date endDate,
			Long userAccessLevelId,Set<Long> userAccessLevelValues);
	public Long getEventDocumentsCountByLocationInbfo(EventDocumentVO inputVO,Date startDate,Date endDate,
			Long userAccessLevelId,Set<Long> userAccessLevelValues);
	public List<Object[]> getDayWiseActivityInfoImagesCount(SearchAttributeVO inputVO,Long stateId);
	//public List<Object[]> getActivityQuestionnairesCountsByLocation(SearchAttributeVO searchAttributeVO,Long stateId);
	public List<Object[]> getLocationValue(Long activityScopeId);
	public List<Object[]> getDayWiseImagesCount(EventDocumentVO inputVO,Date startDate,Date endDate);
	//public List<Object[]> activityInfoDocumentCount(List<Long> scopeIds);
	public List<Object[]>  getDistrictNamesByScopeId(Long activityScopeId,Long stateId,Date stDate,Date endDate);
	public List<Object[]>  getConstituencyNamesByDistrictId(Long activityScopeId,Long districtId,Date startDate,Date endDate);
	public List<Object[]>  getMandalNamesByConstiencyId(Long activityScopeId,Long constitencyId,Date startDate,Date endDate);
	public List<Object[]>  getMuncipalityNamesByConstiencyId(Long activityScopeId,Long constitencyId,Date startDate,Date endDate);
	public List<Object[]>  getPanchaytNamesByMandalId(Long activityScopeId,Long mandalId,Date startDate,Date endDate);
	public List<Object[]>  getWardNamesByMuncipalityId(Long activityScopeId,Long muncipalityId,Date startDate,Date endDate);
	public List<Object[]> getDocumentsCuntByScopeId(Long activityScopeId,List<Long> villageIdsList,List<Long> wardIdsList);
	public List<Object[]> getDocumentCuntByScopeId(Long activityScopeId,List<Long> districtIds,List<Long> constiIdsList);
	
	public List<Object[]>  getDistrictNamesLocationsInfocoveredLocationsByScopeId(Long activityScopeId,Long stateId,Date stDate,Date endDate);
	public List<Object[]>  getDistrictNamesConductedInfocoveredLocationsByScopeId(Long activityScopeId,Long stateId,Date stDate,Date endDate);
	
	public Integer deleteEventUploadFilebyActivityConductedInfoId(List<Long> activityInfoIdList);
	public Integer deleteEventUploadFilebyActivityInfoId(List<Long> activityInfoIdList);
	public List<Object[]> setDayWiseImagesDetails(Long locationId);
	public List<Object[]> getEventsDocumentsCountByLocationInbfo(EventDocumentVO inputVO,Date startDate,Date endDate,Long userAccessLevelId,Set<Long> userAccessLevelValues);
	public List<Object[]> getConstituencyNamesLocationsInfocoveredLocationsByScopeId(Long activityScopeId,Long districtId,Date startDate,Date endDate);
	public List<Object[]> getMandalNamesLocationsInfocoveredLocationsByScopeId(Long activityScopeId,Long constituencyId,Date startDate,Date endDate);
	public List<Object[]> getMuncipalityNamesLocationsInfocoveredLocationsByScopeId(Long activityScopeId,Long constituencyId,Date startDate,Date endDate);
	public List<Object[]> getPanchaytNamesLocationsInfocoveredLocationsByScopeId(Long activityScopeId, Long mandalOrMuncId,Date startDate,Date endDate);
	public List<Object[]> getWardNamesLocationsInfocoveredLocationsByScopeId(Long activityScopeId, Long mandalOrMuncId,Date startDate,Date endDate);
	public List<Object[]> getDocumentsCuntForScopeId(Long activityScopeId,List<Long> mandalIdList,List<Long> muncipIdsList);
	public List<Object[]> getDocuemntDtlsByLocation(Long activityScopeId,Long activityLocationInfoId);
}
