package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.PartyMeetingUpdationDetails;

public interface IPartyMeetingUpdationDetailsDAO extends GenericDao<PartyMeetingUpdationDetails, Long>{
	public List<Object[]> getCommentsAvailableByPartyMeetingId(List<Long> partyMeetingIds);
	public List<Object[]> getCommentsDetailsByPartyMeetingId(Long partyMeetingId);
	public List<Object[]> getUpdatedDetails(Long locationLevelId,Date startDate,Date endDate,String status);
	public List<Object[]> getDocumentList(Long partyMeetingId);
	public String getMemberShipNo(Long partyMeetingId);
	public List<Object[]> getUpdationDetailsCount(Date startDate,Date endDate);

}
