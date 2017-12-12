package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.dto.PartyMeetingsInputVO;
import com.itgrids.partyanalyst.model.PartyMeetingDocument;

public interface IPartyMeetingDocumentDAO extends GenericDao<PartyMeetingDocument,Long>{

	public List<Object[]> getPartyMeetingsDocumentsDetls(Long partyMeetingTypeId);
	public List<Object[]> getDocumentDetailsForMinutesAtr(Long partyMeetingId,String accessType,List<Long> accessValues);
	public Integer deletePartyMeetingDocument(Long docId);
	public List<Object[]> getPartyMeetingDocsOf(Long partyMeetingId, String documentSourceType,String accessType,List<Long> accessValue);
	public List<Object[]> getPartyMeetingDocsOfMeetingIds(List<Long> partyMeetingIds);
	public List<Object[]> getMinuteAtrDocumentSummaryForAMeeting(Long meetingId,String type,String accessType,List<Long> accessValue);
	public List<Long> getDocDetails(List<Long> meetingIds,String type);
	public List<Object[]> getPartyMeetingdocList(PartyMeetingsInputVO inputVO,Long locationId,Set<Long> locationValuesSet);
	public List<Object[]>  getDistrictsForCustomMeetingImgesLst(Long partyMeetingLevelId,Long stateId,Date fromDate,Date toDate,Long locationId,Set<Long> locationValuesSet,Long meetingId,Long meetingGrpId,Long locationValue);
	public List<Object[]>  getConstByDistrictIdForWiseCustomPartyMeetings(Long partymeetingLevelId,Long districtId);
	public List<Object[]>  getMuncipalityNamesByConstiencyIdForWiseCustomPartyMeetings(Long partyMeetingLevelId,Long constitencyId);
	public List<Object[]>  getMandalsByconstituencyIdForWiseCustomPartyMeetings(Long partyMeetingLevelId,Long constitencyId);
	public List<Object[]>  getWardNamesByMuncipalityIdForWiseCustomPartyMeetings(Long partyMeetingLevelId,Long muncipalityId);
	public List<Object[]>  getPanchaytNamesByMandalIdForWiseCustomPartyMeetings(Long partyMeetingLevelId,Long mandalId);
	public List<Object[]> getCustomWisePartyMeetingDocuments(Date startDate,Date endDate,Long locationLevelId,Set<Long> locationLevelvalues,int startIndex,int maxIndex,Long stateId,Long partyMeetingLevelId,Long meetingId,Long meetingGrpId,Long locationValue);
	public Long getCustomWisePartyMeetingDocumentsCount(Date startDate,Date endDate,Long locationLevelId,Set<Long> locationLevelvalues,int startIndex,int maxIndex,Long stateId,Long partyMeetingLevelId,Long meetingId,Long meetingGrpId,Long locationValue);
	public List<Object[]> getSessionWisePartyMeetingDocumentsCount(Date startDate,Date endDate,Long locationLevelId,Set<Long> locationLevelvalues,int startIndex,int maxIndex,Long stateId,Long partyMeetingLevelId,Long meetingId,Long meetingGrpId);
	
	public List<Object[]> getImagesCountsForPartyMeetings(List<Long> partyMeetingIds);
	public List<Object[]> getMinuteAtrDocumentSummaryForMeetingsList(List<Long> partyMeetingIds,String type,String accessType,List<Long> accessValue);
	public List<Object[]> getPartyMeetingFilesId(List<Long> partMeetingIds,String type);
	public List<Object[]> getLocationWiseStateMeetingImages(List<Long> locationValues,Long locationTypeId,Date fromDate,Date toDate,Long partyMeetingMainTypeid,Long partyMeetingTypeId);
	public List<Object[]> getPartyMeetingMomDocumentByUserAccessLevel(Long userAccessLevelId,List<Long> userAccessLevelValues,Integer monthId,Integer yearId,String type,Long partyMeetingId);
	public Integer deletePartyMeetingMOMDocument(Long docId,Long loginUserId,Date updatedDate);
	public List<String> getMomDocuments(Long partyMeetingMomId);
}
