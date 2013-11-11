package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IPartyFileKeywordDAO;
import com.itgrids.partyanalyst.model.PartyFileKeyword;

public class PartyFileKeywordDAO extends GenericDaoHibernate<PartyFileKeyword, Long> implements IPartyFileKeywordDAO{

	public PartyFileKeywordDAO() {
		super(PartyFileKeyword.class);
	}

}
