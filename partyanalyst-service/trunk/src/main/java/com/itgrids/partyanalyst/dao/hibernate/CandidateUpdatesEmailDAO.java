package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import com.itgrids.partyanalyst.dao.ICandidateUpdatesEmailDAO;
import com.itgrids.partyanalyst.model.CandidateUpdatesEmail;
import com.itgrids.partyanalyst.utils.IConstants;

public class CandidateUpdatesEmailDAO extends GenericDaoHibernate<CandidateUpdatesEmail,Long> implements ICandidateUpdatesEmailDAO{

	public CandidateUpdatesEmailDAO()
	{
		super(CandidateUpdatesEmail.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object> getCandidateUpdatesEmail(String emailId ,Long candidateId)
	{
		Object[] params = {candidateId,emailId};
		return getHibernateTemplate().find("select model from CandidateUpdatesEmail model where model.candidate.candidateId = ? and model.email = ?",params);
	}
	@SuppressWarnings("unchecked")
	public List<CandidateUpdatesEmail> getAllSubscriberDetails()
	{
		return getHibernateTemplate().find("select model from CandidateUpdatesEmail model where model.unsubscribed = ? ",IConstants.FALSE);
	}
}
