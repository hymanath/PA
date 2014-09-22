package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IDebateParticipantCharcsDAO;
import com.itgrids.partyanalyst.model.DebateParticipantCharcs;

public class DebateParticipantCharcsDAO extends GenericDaoHibernate<DebateParticipantCharcs, Long> implements IDebateParticipantCharcsDAO{

	public DebateParticipantCharcsDAO() {
		super(DebateParticipantCharcs.class);
		// TODO Auto-generated constructor stub
	}
	

	@SuppressWarnings("unchecked")
	public List<Object[]> getDebateCharcsDetails(Long debateId)
	{
		return getHibernateTemplate().find("select model.debateParticipant.candidate.candidateId ,model.debateParticipant.candidate.lastname ," +
				" model.debateParticipant.party.partyId,model.debateParticipant.party.shortName ," +
				" model.characteristics.name,model.scale from  DebateParticipantCharcs model where " +
				" model.debateParticipant.debate.debateId = ? ",debateId);
	}
	
	public List<Object[]> getPartyWiseTotalDebatesAndScales()
	{
		Query query = getSession().createQuery("select distinct model.debateParticipant.party.partyId ,model.debateParticipant.party.shortName ,count(distinct model.debateParticipant.debate.debateId) ,sum(model.scale) from DebateParticipantCharcs model group by model.debateParticipant.party.partyId");
		
		return query.list();
	}
	
	public List<Object[]> getPartyWiseDebatePartiCharsCount()
	{
		Query query = getSession().createQuery("select distinct model.debateParticipant.party.partyId ,model.debateParticipant.party.shortName ,model.characteristics.characteristicsId ,sum(model.scale),model.characteristics.name from DebateParticipantCharcs model group by model.debateParticipant.party.partyId,model.characteristics.characteristicsId");
		
		return query.list();
	}
	
	public List<Object[]> getPartyWiseEachDebateCharsCount()
	{
		Query query = getSession().createQuery("select distinct model.debateParticipant.party.partyId ,model.debateParticipant.party.shortName ,model.debateParticipant.debate.debateId ,sum(model.scale) from DebateParticipantCharcs model group by model.debateParticipant.party.partyId,model.debateParticipant.debate.debateId order by model.debateParticipant.debate.debateId , sum(model.scale) desc");
		
		return query.list();
	}
	
	public List<Object[]> getPartyCandidateDetailsTopicWise()
	{
		Query query = getSession().createQuery("select model.debateParticipant.party.partyId ,model.debateParticipant.party.shortName ,model.debateParticipant.debate.debateId ," +
				" model2.debateSubjectId ,model2.subject , model.debateParticipant.candidate.candidateId, model.debateParticipant.candidate.lastname , " +
				" sum(model.scale) from DebateParticipantCharcs model ,DebateSubject model2 where model.debateParticipant.debate.debateId = model2.debate.debateId" +
				" group by model.debateParticipant.party.partyId,model.debateParticipant.candidate.candidateId,model2.debateSubjectId order by model2.debateSubjectId ");
		
		return query.list();
	}
}
