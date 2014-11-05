package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IVoterNamesDAO;
import com.itgrids.partyanalyst.model.VoterNames;

public class VoterNamesDAO extends GenericDaoHibernate<VoterNames, Long> implements IVoterNamesDAO{

	public VoterNamesDAO() {
		super(VoterNames.class);
	}
	
	public List<VoterNames> gerVoterNamesObjByVoterId(Long voterId)
	{
		Query query = getSession().createQuery("Select model from VoterNames model where model.voter.voterId = :voterId");
		query.setParameter("voterId",voterId);
		return query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getVoterNames(Long constituencyId,Long publicationDateId)
	{
		Query query = getSession().createQuery("Select distinct model.voter.voterId,model.firstName,model.lastName,model.relativeFirstName,model.relativeLastName from VoterNames model,BoothPublicationVoter BPV where BPV.booth.publicationDate.publicationDateId = :publicationDateId and model.voter.voterId = BPV.voter.voterId" +
				" and BPV.booth.constituency.constituencyId = :constituencyId");
		query.setParameter("constituencyId",constituencyId);	
		query.setParameter("publicationDateId",publicationDateId);
		return query.list();	
	}
	
	public List<Object[]> getVoterTeluguNames(Long voterId)
	{
		Query query = getSession().createQuery("Select model.firstName,model.lastName from VoterNames model where model.voter.voterId = :voterId");
		query.setParameter("voterId",voterId);
		return query.list();
	}
	
	
}
