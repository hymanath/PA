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
		str.append(" model.publicationDate.publicationDateId in(:publicationIdsList) and model.partNo = model2.booth.partNo ");
		
		if(locationType.equalsIgnoreCase("constituency"))
			str.append(" and model2.booth.constituency.constituencyId = :locationValue ");
		else if(locationType.equalsIgnoreCase("mandal"))
			str.append(" and model2.booth.tehsil.tehsilId = :locationValue and model2.booth.localBody is null ");
		else if(locationType.equalsIgnoreCase("panchayat"))
			str.append(" and model2.booth.panchayat.panchayatId = :locationValue and model2.booth.localBody is null ");
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
		str.append(" model.publicationDate.publicationDateId in(:publicationIdsList) and model.partNo = model2.booth.partNo ");
		
		if(locationType.equalsIgnoreCase("constituency"))
			str.append(" and model2.booth.constituency.constituencyId = :locationValue ");
		else if(locationType.equalsIgnoreCase("mandal"))
			str.append(" and model2.booth.tehsil.tehsilId = :locationValue and model2.booth.localBody is null ");
		else if(locationType.equalsIgnoreCase("panchayat"))
			str.append(" and model2.booth.panchayat.panchayatId = :locationValue and model2.booth.localBody is null ");
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
		str.append(" model2.booth.boothId, model2.booth.partNo, model2.booth.villagesCovered, ");
		str.append(" model.status, model.publicationDate.publicationDateId,model.publicationDate.name,model.voter.houseNo ");
		str.append(" from VoterModification model, BoothPublicationVoter model2 ");
		str.append(" where model.voter.voterId = model2.voter.voterId and ");
		str.append(" model.publicationDate.publicationDateId in(:publicationIdsList) ");
		
		if(locationType.equalsIgnoreCase("constituency"))
			str.append(" and model2.booth.constituency.constituencyId = :locationValue ");
		else if(locationType.equalsIgnoreCase("mandal"))
			str.append(" and model2.booth.tehsil.tehsilId = :locationValue and model2.booth.localBody is null ");
		else if(locationType.equalsIgnoreCase("panchayat"))
			str.append(" and model2.booth.panchayat.panchayatId = :locationValue and model2.booth.localBody is null ");
		else if(locationType.equalsIgnoreCase("booth"))
			str.append(" and model2.booth.boothId = :locationValue ");
		else if(locationType.equalsIgnoreCase("localElectionBody") || locationType.equalsIgnoreCase("Local Election Body"))
			str.append(" and model2.booth.localBody.localElectionBodyId = :locationValue and model2.booth.constituency.constituencyId = :constituencyId ");
		else if(locationType.equalsIgnoreCase("ward"))
			str.append(" and model2.booth.localBodyWard.constituencyId = :locationValue and model2.booth.constituency.constituencyId = :constituencyId ");
		
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
		str.append(" model.publicationDate.publicationDateId in(:publicationIdsList) and model.partNo = model2.booth.partNo ");
		
		if(locationType.equalsIgnoreCase("constituency"))
			str.append(" and model2.booth.constituency.constituencyId = :locationValue ");
		else if(locationType.equalsIgnoreCase("mandal"))
			str.append(" and model2.booth.tehsil.tehsilId = :locationValue and model2.booth.localBody is null ");
		else if(locationType.equalsIgnoreCase("panchayat"))
			str.append(" and model2.booth.panchayat.panchayatId = :locationValue and model2.booth.localBody is null ");
		else if(locationType.equalsIgnoreCase("booth"))
			str.append(" and model2.booth.boothId = :locationValue ");
		else if(locationType.equalsIgnoreCase("localElectionBody") || locationType.equalsIgnoreCase("Local Election Body"))
			str.append(" and model.booth.localBody.localElectionBodyId = :locationValue and model2.booth.constituency.constituencyId = :constituencyId ");
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
		str.append(" model.publicationDate.publicationDateId in(:publicationIdsList) and model.partNo = model2.booth.partNo and ");
		
		if(ageTo == null)
			str.append(" model.voter.age > :ageFrom ");
		else
			str.append(" model.voter.age between :ageFrom and :ageTo ");
		
		if(locationType.equalsIgnoreCase("constituency"))
			str.append(" and model2.booth.constituency.constituencyId = :locationValue ");
		else if(locationType.equalsIgnoreCase("mandal"))
			str.append(" and model2.booth.tehsil.tehsilId = :locationValue and model2.booth.localBody is null ");
		else if(locationType.equalsIgnoreCase("panchayat"))
			str.append(" and model2.booth.panchayat.panchayatId = :locationValue and model2.booth.localBody is null ");
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
	
	@SuppressWarnings("unchecked")
	public List<Long> getModifiedVotersByPartNo(String partNo,Long constituencyId, Long publicationDateId, String status)
	{
		Query query = getSession().createQuery(" select distinct model.voter.voterId from VoterModification model, BoothPublicationVoter model2 where " +
				" model.voter.voterId = model2.voter.voterId and model.publicationDate.publicationDateId = :publicationDateId and model.partNo = model2.booth.partNo and " +
				" model2.booth.constituency.constituencyId = :constituencyId and model2.booth.partNo = :partNo and model.status = :status ");
		query.setParameter("partNo",partNo);
		query.setParameter("constituencyId",constituencyId);
		query.setParameter("publicationDateId",publicationDateId);
		query.setParameter("status",status);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getModifiedVotersByConstituency(Long constituencyId, Long publicationDateId, String status)
	{
		Query query = getSession().createQuery(" select model.voter.voterId,model.partNo from VoterModification model, BoothPublicationVoter model2 where " +
				" model.voter.voterId = model2.voter.voterId and model.publicationDate.publicationDateId = :publicationDateId and model.partNo = model2.booth.partNo and " +
				" model2.booth.constituency.constituencyId = :constituencyId and model.status = :status ");
		query.setParameter("constituencyId",constituencyId);
		query.setParameter("publicationDateId",publicationDateId);
		query.setParameter("status",status);
		return query.list();
	}
	
	public List<Object[]> getConstitunecyGenderWiseVoterModificationsForEachPublicationByMandal(
			List<Long> locationValues,Long constituencyId,List<Long> publicationIdsList)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select count(model.voter.voterId),model.status,model.voter.gender," +
				" model2.booth.tehsil.tehsilId,model2.booth.tehsil.tehsilName from VoterModification model, BoothPublicationVoter model2 ");
		str.append(" where model.voter.voterId = model2.voter.voterId and ");
		str.append(" model.publicationDate.publicationDateId in(:publicationIdsList) and model.partNo = model2.booth.partNo ");
		str.append(" and model2.booth.tehsil.tehsilId in(:locationValues) and model2.booth.localBody is null ");			
		str.append(" group by model.status,model.voter.gender , model2.booth.tehsil.tehsilId ");		
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("publicationIdsList",publicationIdsList);
		query.setParameterList("locationValues",locationValues);
	
		return query.list();
	}
	
	
	
	public List<Object[]> getConstitunecyGenderWiseVoterModificationsForEachPublicationByLocalElectionBody(
			List<Long> locationValues,Long constituencyId,List<Long> publicationIdsList)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select count(model.voter.voterId),model.status,model.voter.gender," +
				" model.booth.localBody.localElectionBodyId , model.booth.localBody.name from VoterModification model, BoothPublicationVoter model2 ");
		str.append(" where model.voter.voterId = model2.voter.voterId and ");
		str.append(" model.publicationDate.publicationDateId in(:publicationIdsList) and model.partNo = model2.booth.partNo ");
		str.append(" and model.booth.localBody.localElectionBodyId in(:locationValues) and model2.booth.constituency.constituencyId = :constituencyId ");
	    str.append(" group by model.publicationDate.publicationDateId,model.status,model.voter.gender,model.booth.localBody.localElectionBodyId");
		
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("publicationIdsList",publicationIdsList);
		query.setParameterList("locationValues",locationValues);
	
		query.setParameter("constituencyId",constituencyId);
		return query.list();
	}
	
	public List<Object[]> getMandalGenderWiseVoterModificationsForEachPublicationByPanchayat(
			List<Long> locationValues,Long constituencyId,List<Long> publicationIdsList)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select count(model.voter.voterId)," +
				"model.status,model.voter.gender," +
				"model2.booth.panchayat.panchayatId ,model2.booth.panchayat.panchayatName from VoterModification model, BoothPublicationVoter model2 ");
		str.append(" where model.voter.voterId = model2.voter.voterId and ");
		str.append(" model.publicationDate.publicationDateId in(:publicationIdsList) and model.partNo = model2.booth.partNo ");
		str.append(" and model2.booth.panchayat.panchayatId in(:locationValues) and model2.booth.localBody is null  ");
	    str.append(" group by model.status,model.voter.gender," +
	    		"model2.booth.panchayat.panchayatId");
		
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("publicationIdsList",publicationIdsList);
		query.setParameterList("locationValues",locationValues);
	
		return query.list();
	}
	
	public List<Object[]> getLocalElectionBodyGenderWiseVoterModificationsForEachPublicationByWard(
			List<Long> locationValues,Long constituencyId,List<Long> publicationIdsList)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select count(model.voter.voterId),model.status,model.voter.gender," +
				" model2.localBodyWard.constituencyId , model2.localBodyWard.name from VoterModification model, BoothPublicationVoter model2 ");
		str.append(" where model.voter.voterId = model2.voter.voterId and ");
		str.append(" model.publicationDate.publicationDateId in(:publicationIdsList) and model.partNo = model2.booth.partNo ");
		str.append(" and model2.localBodyWard.constituencyId in(:locationValues) and model2.booth.constituency.constituencyId = :constituencyId ");
	    str.append(" group by model.status,model.voter.gender," +
	    		"model2.booth.panchayat.panchayatId , model2.localBodyWard.constituencyId");
		
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("publicationIdsList",publicationIdsList);
		query.setParameterList("locationValues",locationValues);
		query.setParameter("constituencyId",constituencyId);
	
		return query.list();
	}
	
	public List<Object[]> getPanchayatGenderWiseVoterModificationsForEachPublicationByBooth(
			List<Long> locationValues,Long constituencyId,List<Long> publicationIdsList)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select count(model.voter.voterId),model.status,model.voter.gender," +
				" model2.booth.boothId , model2.booth.partNo from VoterModification model, BoothPublicationVoter model2 ");
		str.append(" where model.voter.voterId = model2.voter.voterId and ");
		str.append(" model.publicationDate.publicationDateId in(:publicationIdsList) and model.partNo = model2.booth.partNo ");
		str.append(" and model2.booth.boothId in(:locationValues)");
	    str.append(" group by model.status,model.voter.gender," +
	    		"model2.booth.panchayat.panchayatId ,model2.booth.panchayat.panchayatId ,  model2.booth.boothId");
		
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("publicationIdsList",publicationIdsList);
		query.setParameterList("locationValues",locationValues);
	
		return query.list();
	}
	
	public List<Object[]> getWardGenderWiseVoterModificationsForEachPublicationByBooth(
			List<Long> locationValues,Long constituencyId,List<Long> publicationIdsList)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select count(model.voter.voterId),model.status,model.voter.gender," +
				" model2.booth.boothId, model2.booth.partNo from VoterModification model, BoothPublicationVoter model2 ");
		str.append(" where model.voter.voterId = model2.voter.voterId and ");
		str.append(" model.publicationDate.publicationDateId in(:publicationIdsList) and model.partNo = model2.booth.partNo ");
		str.append(" and model2.booth.boothId in(:locationValues)");
	    str.append(" group by model.status,model.voter.gender," +
	    		"model2.booth.panchayat.panchayatId ,model2.booth.panchayat.panchayatId ,  model2.booth.boothId");
		
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("publicationIdsList",publicationIdsList);
		query.setParameterList("locationValues",locationValues);
	
		return query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getGenderWiseVoterModificationByPublicationId(String locationType,List<Long> locationValuesList,Long constituencyId,Long publicationDateId, String queryString)
	{
		StringBuilder str = new StringBuilder();
		str.append(queryString );
		str.append("  from VoterModification model, BoothPublicationVoter model2 where model.voter.voterId = model2.voter.voterId and ");
		str.append(" model.publicationDate.publicationDateId = :publicationDateId and model.partNo = model2.booth.partNo ");
		
		if(locationType.equalsIgnoreCase("constituency"))
			str.append(" and model2.booth.constituency.constituencyId in (:locationValuesList) ");
		else if(locationType.equalsIgnoreCase("mandal"))
			str.append(" and model2.booth.tehsil.tehsilId in (:locationValuesList) and model2.booth.localBody is null ");
		else if(locationType.equalsIgnoreCase("panchayat"))
			str.append(" and model2.booth.panchayat.panchayatId in (:locationValuesList) and model2.booth.localBody is null ");
		else if(locationType.equalsIgnoreCase("booth"))
			str.append(" and model2.booth.boothId in (:locationValuesList) ");
		else if(locationType.equalsIgnoreCase("localElectionBody") || locationType.equalsIgnoreCase("Local Election Body"))
			str.append(" and model2.booth.localBody.localElectionBodyId in (:locationValuesList) and model2.booth.constituency.constituencyId = :constituencyId ");
		else if(locationType.equalsIgnoreCase("ward"))
			str.append(" and model2.booth.localBodyWard.constituencyId in (:locationValuesList) and model2.booth.constituency.constituencyId = :constituencyId ");
		
		str.append(" group by model.status,model.voter.gender ");
		
		Query query = getSession().createQuery(str.toString());
		query.setParameter("publicationDateId",publicationDateId);
		query.setParameterList("locationValuesList",locationValuesList);
		
		if(locationType.equalsIgnoreCase("localElectionBody") || locationType.equalsIgnoreCase("Local Election Body") ||
				locationType.equalsIgnoreCase("ward"))
			query.setParameter("constituencyId",constituencyId);
		return query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAgeWiseAddedAndDeletedVotersCountByPublicationDateIdInALocation(String locationType,List<Long> locationValuesList,Long constituencyId,Long publicationDateId,Long ageFrom, Long ageTo, String queryStr)
	{
		StringBuilder str = new StringBuilder();
		str.append(queryStr);
		str.append("  from VoterModification model, BoothPublicationVoter model2 where model.voter.voterId = model2.voter.voterId and ");
		str.append(" model.publicationDate.publicationDateId = :publicationDateId and model.partNo = model2.booth.partNo and ");
		
		if(ageTo == null)
			str.append(" model.voter.age > :ageFrom ");
		else
			str.append(" model.voter.age between :ageFrom and :ageTo ");
		
		if(locationType.equalsIgnoreCase("constituency"))
			str.append(" and model2.booth.constituency.constituencyId in (:locationValuesList) ");
		else if(locationType.equalsIgnoreCase("mandal"))
			str.append(" and model2.booth.tehsil.tehsilId in (:locationValuesList) and model2.booth.localBody is null ");
		else if(locationType.equalsIgnoreCase("panchayat"))
			str.append(" and model2.booth.panchayat.panchayatId in (:locationValuesList) and model2.booth.localBody is null ");
		else if(locationType.equalsIgnoreCase("booth"))
			str.append(" and model2.booth.boothId in (:locationValuesList) ");
		else if(locationType.equalsIgnoreCase("localElectionBody") || locationType.equalsIgnoreCase("Local Election Body"))
			str.append(" and model2.booth.localBody.localElectionBodyId in (:locationValuesList) and model2.booth.constituency.constituencyId = :constituencyId ");
		else if(locationType.equalsIgnoreCase("ward"))
			str.append(" and model2.booth.localBodyWard.constituencyId in (:locationValuesList) and model2.booth.constituency.constituencyId = :constituencyId ");
		
		str.append(" group by model.status,model.voter.gender");
		
		Query query = getSession().createQuery(str.toString());
		query.setParameter("publicationDateId",publicationDateId);
		query.setParameterList("locationValuesList",locationValuesList);
		query.setParameter("ageFrom",ageFrom);
		
		if(ageTo != null)
			query.setParameter("ageTo",ageTo);
		
		if(locationType.equalsIgnoreCase("localElectionBody") || locationType.equalsIgnoreCase("Local Election Body") ||
				locationType.equalsIgnoreCase("ward"))
			query.setParameter("constituencyId",constituencyId);
		return query.list();
	}
	
	public Integer deleteVoterModifiedDataByCOnstituencyId(Long constituencyId,Long publicationDateId)
	{
		Query query = getSession().createQuery("delete from VoterModification model where model.constituency.constituencyId=:constituencyId and model.publicationDateId = :publicationDateId ");
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationDateId", publicationDateId);
		return query.executeUpdate();
		
	}
	
	
	
	public List<Object[]> getConstitunecyGenderWiseVoterModificationsForEachPublicationByLocalElectionBody1(
			List<Long> locationValues,Long constituencyId,List<Long> publicationIdsList)
	{
		StringBuilder str = new StringBuilder();
		str.append(" select count(model.voter.voterId),model.status,model.voter.gender," +
				" model.booth.localBody.localElectionBodyId , model.booth.localBody.name from VoterModification model, BoothPublicationVoter model2 ");
		str.append(" where model.voter.voterId = model2.voter.voterId and ");
		str.append(" model.publicationDate.publicationDateId in(:publicationIdsList) and model.partNo = model2.booth.partNo ");
		str.append(" and model.booth.localBody.localElectionBodyId in(:locationValues) and model2.booth.constituency.constituencyId = :constituencyId ");
	    str.append(" group by model.publicationDate.publicationDateId,model.status,model.voter.gender,model.booth.localBody.localElectionBodyId");
		
		Query query = getSession().createQuery(str.toString());
		query.setParameterList("publicationIdsList",publicationIdsList);
		query.setParameterList("locationValues",locationValues);
	
		query.setParameter("constituencyId",constituencyId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getSublevelVoterModificationDetails(Long constituencyId, List<Long> publicationIdsList, Long locationValue, String type, String queryStr)
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(queryStr.toString());
		stringBuilder.append(" from VoterModification model, BoothPublicationVoter model2 where model.voter.voterId = model2.voter.voterId and ");
		stringBuilder.append(" model.publicationDate.publicationDateId in(:publicationIdsList) and model.partNo = model2.booth.partNo and ");
		
		if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
			 stringBuilder.append(" model2.booth.constituency.constituencyId = :locationValue ");
		 
		 else if(type.equalsIgnoreCase(IConstants.MANDAL))
			 stringBuilder.append(" model2.booth.tehsil.tehsilId = :locationValue ");
		 
		 else if(type.equalsIgnoreCase(IConstants.LOCALELECTIONBODY) || type.equalsIgnoreCase("localElectionBody"))
			 stringBuilder.append(" model2.booth.localBody.localElectionBodyId = :locationValue ");
		 
		 else if(type.equalsIgnoreCase(IConstants.PANCHAYAT))
			 stringBuilder.append(" model2.booth.panchayat.panchayatId = :locationValue ");
		 
		 else if(type.equalsIgnoreCase(IConstants.BOOTH))
			 stringBuilder.append(" model2.booth.partNo = :locationValue ");
		 
		 else if(type.equalsIgnoreCase(IConstants.WARD))
			 stringBuilder.append(" model2.booth.localBodyWard.constituencyId = :locationValue ");
		stringBuilder.append("group by model.publicationDate.publicationDateId,model.status,model.voter.gender ");
		
		Query queryObj = getSession().createQuery(stringBuilder.toString());
		
		queryObj.setParameterList("publicationIdsList", publicationIdsList);
		queryObj.setParameter("locationValue", locationValue);
		
		
		return queryObj.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getSublevelVoterModificationDetailsByLocationValues(Long constituencyId, List<Long> publicationIdsList, List<Long> locationValuesList, String type, String queryStr)
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(queryStr.toString());
		stringBuilder.append(" from VoterModification model, BoothPublicationVoter model2 where model.voter.voterId = model2.voter.voterId and ");
		stringBuilder.append(" model.publicationDate.publicationDateId in(:publicationIdsList) and model.partNo = model2.booth.partNo and ");
		
		if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
			 stringBuilder.append(" model2.booth.constituency.constituencyId in(:locationValuesList) group by model2.booth.constituency.constituencyId ");
		 
		 else if(type.equalsIgnoreCase(IConstants.MANDAL))
			 stringBuilder.append(" model2.booth.tehsil.tehsilId in(:locationValuesList) group by model2.booth.tehsil.tehsilId ");
		 
		 else if(type.equalsIgnoreCase(IConstants.LOCALELECTIONBODY) || type.equalsIgnoreCase("localElectionBody"))
			 stringBuilder.append(" model2.booth.localBody.localElectionBodyId in(:locationValuesList) group by model2.booth.localBody.localElectionBodyId ");
		 
		 else if(type.equalsIgnoreCase(IConstants.PANCHAYAT))
			 stringBuilder.append(" model2.booth.panchayat.panchayatId in(:locationValuesList) group by model2.booth.panchayat.panchayatId ");
		 
		 else if(type.equalsIgnoreCase(IConstants.BOOTH))
			 stringBuilder.append(" model2.booth.boothId in(:locationValuesList) group by model2.booth.boothId ");
		 
		 else if(type.equalsIgnoreCase(IConstants.WARD))
			 stringBuilder.append(" model2.booth.localBodyWard.constituencyId in(:locationValuesList) group by model2.booth.localBodyWard.constituencyId ");
		
		stringBuilder.append(" , model.publicationDate.publicationDateId,model.status,model.voter.gender ");
		
		Query queryObj = getSession().createQuery(stringBuilder.toString());
		
		queryObj.setParameterList("publicationIdsList", publicationIdsList);
		queryObj.setParameterList("locationValuesList", locationValuesList);
		
		
		return queryObj.list();
	}
	
	
}
