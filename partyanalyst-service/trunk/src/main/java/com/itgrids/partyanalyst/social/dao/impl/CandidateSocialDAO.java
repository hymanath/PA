package com.itgrids.partyanalyst.social.dao.impl;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.itgrids.partyanalyst.social.dao.ICandidateSocialDAO;
import com.itgrids.partyanalyst.social.model.CandidateSocial;

public class CandidateSocialDAO extends GenericDaoHibernate<CandidateSocial, Long> implements ICandidateSocialDAO{

	public CandidateSocialDAO() {
		super(CandidateSocial.class);
	} 
}
