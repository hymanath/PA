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
	
	public List<Object[]> getDocumentDetailsForMinutesAtr(Long partyMeetingId){
		Query query = getSession().createQuery(" select model.partyMeetingDocumentId,model.partyMeetingId,model.path,model.documentType,model.documentFormat," +
				"model.uploadedBy.userId,model.uploadedBy.firstName,model.updatedBy.userId,model.updatedBy.firstName,model.uploadedTime " +
				" from PartyMeetingDocument model " +
				" where model.partyMeetingId=:partyMeetingId ");
		query.setParameter("partyMeetingId", partyMeetingId);
		
		return query.list();
	}
}
