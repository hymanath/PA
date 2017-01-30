package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPartyMeetingUpdationDocumentsDAO;
import com.itgrids.partyanalyst.model.PartyMeetingUpdationDocuments;



public class PartyMeetingUpdationDocumentsDAO extends GenericDaoHibernate<PartyMeetingUpdationDocuments, Long> implements IPartyMeetingUpdationDocumentsDAO{

	public PartyMeetingUpdationDocumentsDAO(){
		super(PartyMeetingUpdationDocuments.class);
	}
}
