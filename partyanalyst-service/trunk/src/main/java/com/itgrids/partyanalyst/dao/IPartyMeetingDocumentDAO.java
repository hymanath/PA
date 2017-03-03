package com.itgrids.partyanalyst.dao;

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
}
