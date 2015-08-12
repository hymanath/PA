package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPartyMeetingDocumentDAO;
import com.itgrids.partyanalyst.model.PartyMeetingDocument;

public class PartyMeetingDocumentDAO extends GenericDaoHibernate<PartyMeetingDocument,Long> implements IPartyMeetingDocumentDAO{

	public PartyMeetingDocumentDAO()
	{
		super(PartyMeetingDocument.class);
	}
	
	public List<Object[]> getPartyMeetingsDocumentsDetls(Long partyMeetingTypeId)
	{
		StringBuilder queryStr = new StringBuilder();
		queryStr.append(" select distinct PMI.partyMeeting.partyMeetingId, count(distinct PMI.partyMeetingDocumentId)  from PartyMeetingDocument PMI   where " +
				"  PMI.partyMeeting.partyMeetingType.partyMeetingTypeId=:partyMeetingTypeId ");
		queryStr.append(" group by PMI.partyMeeting.partyMeetingId order by PMI.partyMeeting.partyMeetingId ");
		Query query = getSession().createQuery(queryStr.toString());
		query.setParameter("partyMeetingTypeId", partyMeetingTypeId);
		return query.list();
	}
}
