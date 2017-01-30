package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPartyMeetingUpdationDocumentsDAO;
import com.itgrids.partyanalyst.model.PartyMeetingUpdationDocuments;



public class PartyMeetingUpdationDocumentsDAO extends GenericDaoHibernate<PartyMeetingUpdationDocuments, Long> implements IPartyMeetingUpdationDocumentsDAO{

	public PartyMeetingUpdationDocumentsDAO(){
		super(PartyMeetingUpdationDocuments.class);
	}
	
	public Long getDocumentsCountByMeetingIds(List<Long> partyMeetingIds){
		Query query = getSession().createQuery("select count(model.partyMeetingUpdationDocumentsId)" +
													" from PartyMeetingUpdationDocuments model" +
													" where model.partyMeetingUpdationDetails.partyMeeting.partyMeetingId in (:partyMeetingIds)" +
													" and model.isDeleted = 'N'");
		query.setParameterList("partyMeetingIds", partyMeetingIds);
		return (Long) query.uniqueResult();
	}
}
