package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IVoterModificationDAO;
import com.itgrids.partyanalyst.model.VoterModification;
import com.itgrids.partyanalyst.utils.IConstants;

public class VoterModificationDAO extends GenericDaoHibernate<VoterModification,Long> implements IVoterModificationDAO{
	
	public VoterModificationDAO()
	{
		super(VoterModification.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAddedAndDeletedVotersCountInBetweenPublicationsInALocation(String locationType,Long locationValue,Long constituencyId,List<Long> publicationIdsList)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select count(model.voter.voterId),model.status from VoterModification model, BoothPublicationVoter model2 where model.voter.voterId = model2.voter.voterId and ");
		str.append(" model.publicationDate.publicationDateId in(:publicationIdsList) ");
		
		if(locationType.equalsIgnoreCase("constituency"))
			str.append(" and model2.booth.constituency.constituencyId = :locationValue ");
		else if(locationType.equalsIgnoreCase("mandal"))
			str.append(" and model2.booth.tehsil.tehsilId = :locationValue and model2.booth.localBody is null ");
		else if(locationType.equalsIgnoreCase("panchayat"))
			str.append(" and model2.booth.pancahayat.pancahayatId = :locationValue and model2.booth.localBody is null ");
		else if(locationType.equalsIgnoreCase("booth"))
			str.append(" and model2.booth.boothId = :locationValue ");
		else if(locationType.equalsIgnoreCase("localElectionBody") || locationType.equalsIgnoreCase("Local Election Body"))
			str.append(" model.booth.localBody.localElectionBodyId = :locationValue and model2.booth.constituency.constituencyId = :constituencyId ");
		else if(locationType.equalsIgnoreCase("ward"))
			str.append(" and model2.localBodyWard.constituencyId = :locationValue and model2.booth.constituency.constituencyId = :constituencyId ");
		
		str.append(" group by model.status");
		
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("publicationIdsList",publicationIdsList);
		query.setParameter("locationValue",locationValue);
		
		if(locationType.equalsIgnoreCase("localElectionBody") || locationType.equalsIgnoreCase("Local Election Body") ||
				locationType.equalsIgnoreCase("ward"))
			query.setParameter("constituencyId",constituencyId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getGenderWiseVoterModificationsBetweenPublications(String locationType,Long locationValue,Long constituencyId,List<Long> publicationIdsList)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select count(model.voter.voterId),model.status,model.voter.gender from VoterModification model, BoothPublicationVoter model2 where model.voter.voterId = model2.voter.voterId and ");
		str.append(" model.publicationDate.publicationDateId in(:publicationIdsList) ");
		
		if(locationType.equalsIgnoreCase("constituency"))
			str.append(" and model2.booth.constituency.constituencyId = :locationValue ");
		else if(locationType.equalsIgnoreCase("mandal"))
			str.append(" and model2.booth.tehsil.tehsilId = :locationValue and model2.booth.localBody is null ");
		else if(locationType.equalsIgnoreCase("panchayat"))
			str.append(" and model2.booth.pancahayat.pancahayatId = :locationValue and model2.booth.localBody is null ");
		else if(locationType.equalsIgnoreCase("booth"))
			str.append(" and model2.booth.boothId = :locationValue ");
		else if(locationType.equalsIgnoreCase("localElectionBody") || locationType.equalsIgnoreCase("Local Election Body"))
			str.append(" model.booth.localBody.localElectionBodyId = :locationValue and model2.booth.constituency.constituencyId = :constituencyId ");
		else if(locationType.equalsIgnoreCase("ward"))
			str.append(" and model2.localBodyWard.constituencyId = :locationValue and model2.booth.constituency.constituencyId = :constituencyId ");
		
		str.append(" group by model.status,model.voter.gender ");
		
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("publicationIdsList",publicationIdsList);
		query.setParameter("locationValue",locationValue);
		
		if(locationType.equalsIgnoreCase("localElectionBody") || locationType.equalsIgnoreCase("Local Election Body") ||
				locationType.equalsIgnoreCase("ward"))
			query.setParameter("constituencyId",constituencyId);
		return query.list();
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getModifiedVotersInALocationBetweenPublucations(String locationType,Long locationValue,Long constituencyId,List<Long> publicationIdsList,String status)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select model.voter.voterId,model.voter.name,model.voter.gender, model.voter.age, model.voter.relativeName, model.voter.relationshipType, ");
		str.append(" model2.booth.boothId, model2.booth.partNo, model2.booth.location, model2.booth.panchayat.panchayatId, model2.booth.panchayat.panchayatName, ");
		str.append(" model.status, model.publicationDate.publicationDateId,model.publicationDate.name ");
		str.append(" from VoterModification model, BoothPublicationVoter model2 ");
		str.append(" where model.voter.voterId = model2.voter.voterId and ");
		str.append(" model.publicationDate.publicationDateId in(:publicationIdsList) ");
		
		if(locationType.equalsIgnoreCase("constituency"))
			str.append(" and model2.booth.constituency.constituencyId = :locationValue ");
		else if(locationType.equalsIgnoreCase("mandal"))
			str.append(" and model2.booth.tehsil.tehsilId = :locationValue and model2.booth.localBody is null ");
		else if(locationType.equalsIgnoreCase("panchayat"))
			str.append(" and model2.booth.pancahayat.pancahayatId = :locationValue and model2.booth.localBody is null ");
		else if(locationType.equalsIgnoreCase("booth"))
			str.append(" and model2.booth.boothId = :locationValue ");
		else if(locationType.equalsIgnoreCase("localElectionBody") || locationType.equalsIgnoreCase("Local Election Body"))
			str.append(" model.booth.localBody.localElectionBodyId = :locationValue and model2.booth.constituency.constituencyId = :constituencyId ");
		else if(locationType.equalsIgnoreCase("ward"))
			str.append(" and model2.localBodyWard.constituencyId = :locationValue and model2.booth.constituency.constituencyId = :constituencyId ");
		
		if(status != null && !status.equalsIgnoreCase(IConstants.ALL))
			str.append(" and model.status = :status ");
		
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("publicationIdsList",publicationIdsList);
		query.setParameter("locationValue",locationValue);
		
		if(locationType.equalsIgnoreCase("localElectionBody") || locationType.equalsIgnoreCase("Local Election Body") ||
				locationType.equalsIgnoreCase("ward"))
			query.setParameter("constituencyId",constituencyId);
		if(status != null && !status.equalsIgnoreCase(IConstants.ALL))
			query.setParameter("status",status);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getGenderWiseVoterModificationsForEachPublication(String locationType,Long locationValue,Long constituencyId,List<Long> publicationIdsList)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select model.publicationDate.publicationDateId,count(model.voter.voterId),model.status,model.voter.gender,model.publicationDate.name from VoterModification model, BoothPublicationVoter model2 ");
		str.append(" where model.voter.voterId = model2.voter.voterId and ");
		str.append(" model.publicationDate.publicationDateId in(:publicationIdsList) ");
		
		if(locationType.equalsIgnoreCase("constituency"))
			str.append(" and model2.booth.constituency.constituencyId = :locationValue ");
		else if(locationType.equalsIgnoreCase("mandal"))
			str.append(" and model2.booth.tehsil.tehsilId = :locationValue and model2.booth.localBody is null ");
		else if(locationType.equalsIgnoreCase("panchayat"))
			str.append(" and model2.booth.pancahayat.pancahayatId = :locationValue and model2.booth.localBody is null ");
		else if(locationType.equalsIgnoreCase("booth"))
			str.append(" and model2.booth.boothId = :locationValue ");
		else if(locationType.equalsIgnoreCase("localElectionBody") || locationType.equalsIgnoreCase("Local Election Body"))
			str.append(" model.booth.localBody.localElectionBodyId = :locationValue and model2.booth.constituency.constituencyId = :constituencyId ");
		else if(locationType.equalsIgnoreCase("ward"))
			str.append(" and model2.localBodyWard.constituencyId = :locationValue and model2.booth.constituency.constituencyId = :constituencyId ");
		
		str.append(" group by model.publicationDate.publicationDateId,model.status,model.voter.gender ");
		
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("publicationIdsList",publicationIdsList);
		query.setParameter("locationValue",locationValue);
		
		if(locationType.equalsIgnoreCase("localElectionBody") || locationType.equalsIgnoreCase("Local Election Body") ||
				locationType.equalsIgnoreCase("ward"))
			query.setParameter("constituencyId",constituencyId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAgeWiseAddedAndDeletedVotersCountInBetweenPublicationsInALocation(String locationType,Long locationValue,Long constituencyId,List<Long> publicationIdsList,Long ageFrom, Long ageTo)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select count(model.voter.voterId),model.status from VoterModification model, BoothPublicationVoter model2 where model.voter.voterId = model2.voter.voterId and ");
		str.append(" model.publicationDate.publicationDateId in(:publicationIdsList) and ");
		
		if(ageTo == null)
			str.append(" model.voter.age > :ageFrom ");
		else
			str.append(" model.voter.age between :ageFrom and :ageTo ");
		
		if(locationType.equalsIgnoreCase("constituency"))
			str.append(" and model2.booth.constituency.constituencyId = :locationValue ");
		else if(locationType.equalsIgnoreCase("mandal"))
			str.append(" and model2.booth.tehsil.tehsilId = :locationValue and model2.booth.localBody is null ");
		else if(locationType.equalsIgnoreCase("panchayat"))
			str.append(" and model2.booth.pancahayat.pancahayatId = :locationValue and model2.booth.localBody is null ");
		else if(locationType.equalsIgnoreCase("booth"))
			str.append(" and model2.booth.boothId = :locationValue ");
		else if(locationType.equalsIgnoreCase("localElectionBody") || locationType.equalsIgnoreCase("Local Election Body"))
			str.append(" model.booth.localBody.localElectionBodyId = :locationValue and model2.booth.constituency.constituencyId = :constituencyId ");
		else if(locationType.equalsIgnoreCase("ward"))
			str.append(" and model2.localBodyWard.constituencyId = :locationValue and model2.booth.constituency.constituencyId = :constituencyId ");
		
		str.append(" group by model.status");
		
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("publicationIdsList",publicationIdsList);
		query.setParameter("locationValue",locationValue);
		query.setParameter("ageFrom",ageFrom);
		
		if(ageTo != null)
			query.setParameter("ageTo",ageTo);
		
		if(locationType.equalsIgnoreCase("localElectionBody") || locationType.equalsIgnoreCase("Local Election Body") ||
				locationType.equalsIgnoreCase("ward"))
			query.setParameter("constituencyId",constituencyId);
		return query.list();
	}
}
