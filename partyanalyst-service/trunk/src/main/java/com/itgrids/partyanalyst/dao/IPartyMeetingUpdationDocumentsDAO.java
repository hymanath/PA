package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PartyMeetingUpdationDocuments;

public interface IPartyMeetingUpdationDocumentsDAO extends GenericDao<PartyMeetingUpdationDocuments, Long>{

	public Long getDocumentsCountByMeetingIds(List<Long> partyMeetingIds);
}
