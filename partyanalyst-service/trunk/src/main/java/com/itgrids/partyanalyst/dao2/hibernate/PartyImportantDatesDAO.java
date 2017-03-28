package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPartyImportantDatesDAO;
import com.itgrids.partyanalyst.model.PartyImportantDates;

public class PartyImportantDatesDAO  extends GenericDaoHibernate<PartyImportantDates, Long> implements IPartyImportantDatesDAO{

	public PartyImportantDatesDAO() {
		super(PartyImportantDates.class);
			}

	@SuppressWarnings("unchecked")
	public List<PartyImportantDates> findByPartyId(Long partyId) {
		
		return getHibernateTemplate().find("from PartyImportantDates model where model.party.partyId = ?" , partyId);
	}

	@SuppressWarnings("unchecked")
	public List<PartyImportantDates> findTodaysPartyImportantDates(Long partyId) {
		return getHibernateTemplate().find("from PartyImportantDates model where model.party.partyId = ? " +
				"AND EXTRACT(DAY FROM IMPORTANT_DATE) = DAY(NOW()) " +
				"AND EXTRACT(MONTH FROM IMPORTANT_DATE) = MONTH(NOW())", partyId);
		
	}

	
}
