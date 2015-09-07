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
				"model.uploadedBy.userId,model.uploadedBy.firstName,model.updatedBy.userId,model.updatedBy.firstName,model.uploadedTime, model.documentName " +
				" from PartyMeetingDocument model " +
				" where model.partyMeetingId=:partyMeetingId and model.isDeleted='N' ");
		query.setParameter("partyMeetingId", partyMeetingId);
		
		return query.list();
	}
	
	public Integer deletePartyMeetingDocument(Long docId){
		Query query = getSession().createQuery("update PartyMeetingDocument model set model.isDeleted = 'Y' where model.partyMeetingDocumentId = ?");
		
		query.setParameter(0, docId);
		
		return query.executeUpdate();
	}
	
	public List<Object[]> getPartyMeetingDocsOf(Long partyMeetingId, String documentSourceType){// ATR/MINUTE
		Query query = getSession().createQuery(" select model.partyMeetingDocumentId,model.partyMeetingId,model.path,model.documentType,model.documentFormat," +
				"model.uploadedBy.userId,model.uploadedBy.firstName,model.updatedBy.userId,model.updatedBy.firstName,model.uploadedTime, model.documentName " +
				" from PartyMeetingDocument model " +
				" where model.partyMeetingId=:partyMeetingId and model.isDeleted='N'" +
				" and model.documentType = :documentSourceType ");
		query.setParameter("partyMeetingId", partyMeetingId);
		query.setParameter("documentSourceType", documentSourceType);
		
		return query.list();
	}
	
	/*
	 * @author <a href="mailto:sasi.itgrids.hyd@gmail.com">SASI</a>
	 * @since 21-AUG-2015
	 * This DAO Call is to Get Total Documents Of Meeting
	 * @param List<Long> partyMeetingIds
	 * @return List<Object[]>  of PartyMeetingId, Count of Documents
	 */
	public List<Object[]> getPartyMeetingDocsOfMeetingIds(List<Long> partyMeetingIds){
		Query query = getSession().createQuery(" select model.partyMeeting.partyMeetingId," +
				" model.documentType," +
				" count(model.partyMeetingDocumentId)" +
				" from PartyMeetingDocument model" +
				" where " +
				" model.partyMeeting.partyMeetingId in(:partyMeetingIds)" +
				" and model.isDeleted = 'N'" +
				" group by model.partyMeeting.partyMeetingId," +
				" model.documentType ");
		
		query.setParameterList("partyMeetingIds", partyMeetingIds);
		return query.list();
	}
	
	public List<Object[]> getMinuteAtrDocumentSummaryForAMeeting(Long meetingId,String type){
		Query query = getSession().createQuery(" select model.path,model.documentName,model.partyMeeting.meetingName from PartyMeetingDocument model " +
				" where model.isDeleted='N' and model.partyMeeting.partyMeetingId=:meetingId and model.documentType=:type ");
		query.setParameter("meetingId", meetingId);
		query.setParameter("type", type);
		
		return query.list();
		
	}
	
	public List<Long> getDocDetails(List<Long> meetingIds,String type){
		Query query = getSession().createQuery(" select model.partyMeeting.partyMeetingId from PartyMeetingDocument model " +
				" where model.isDeleted='N' and model.partyMeeting.partyMeetingId in (:meetingIds) and model.documentType=:type ");
		query.setParameterList("meetingIds", meetingIds);
		query.setParameter("type", type);
		return query.list();
	}
}
