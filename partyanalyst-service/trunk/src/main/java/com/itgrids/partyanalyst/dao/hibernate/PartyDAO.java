package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPartyDAO;
import com.itgrids.partyanalyst.dao.columns.enums.ElectionColumnNames;
import com.itgrids.partyanalyst.dao.columns.enums.PartyColumnNames;
import com.itgrids.partyanalyst.model.Election;
import com.itgrids.partyanalyst.model.Party;

public class PartyDAO extends GenericDaoHibernate<Party, Long> implements IPartyDAO{
	public PartyDAO(){
		super(Party.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Party> findByProperty(PartyColumnNames propertyName, Object value) {
		return getHibernateTemplate().find("from Party model where model." + propertyName.getValue() + "=?", value);		
	}
	
	
	public List<Party> findByLongName(Object longName){
		return findByProperty(PartyColumnNames.LONG_NAME, longName);
	}

	public List<Party> findByShortName(Object shortName){
		return findByProperty(PartyColumnNames.SHORT_NAME, shortName);
	}

	public List<Party> findBySymbol(Object symbol){
		return findByProperty(PartyColumnNames.SYMBOL, symbol);
	}

	public List<Party> findByAddress(Object address){
		return findByProperty(PartyColumnNames.ADDRESS, address);
	}

	public List<Party> findByComments(Object comments){
		return findByProperty(PartyColumnNames.COMMENTS, comments);
	}


	public List<Party> findByPartyRecognization(Object partyRecognization){
		return findByProperty(PartyColumnNames.PARTY_RECOGZATION, partyRecognization);
	}
	
	@SuppressWarnings("unchecked")
	public List<Party> findByPartyId(Long partyId){
		return getHibernateTemplate().find("from Party model where model.partyId=?", partyId);
	}

	
}
