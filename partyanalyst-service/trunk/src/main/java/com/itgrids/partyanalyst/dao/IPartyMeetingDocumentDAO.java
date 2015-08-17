package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.PartyMeetingDocument;

public interface IPartyMeetingDocumentDAO extends GenericDao<PartyMeetingDocument,Long>{

	public List<Object[]> getPartyMeetingsDocumentsDetls(Long partyMeetingTypeId);
	public List<Object[]> getDocumentDetailsForMinutesAtr(Long partyMeetingId);
	public Integer deletePartyMeetingDocument(Long docId);
}
