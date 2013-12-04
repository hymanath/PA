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
		
		str.append(" group by model.voterStatus.voterStatusId,model.voter.gender ");
		
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
		
		str.append(" group by model.voterStatus.voterStatusId");
		
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
	public List<Object[]> getGenderWiseVoterModificationByPublicationId(String locationType,List<Long> locationValuesList,Long constituencyId,Long publicationDateId, String queryString,String status,Long prevPubId)
	{
		StringBuilder str = new StringBuilder();
		str.append(queryString );
		str.append("  from VoterModification model, BoothPublicationVoter model2 where model.voter.voterId = model2.voter.voterId and ");
		str.append(" model.publicationDate.publicationDateId = :publicationDateId  and model2.booth.publicationDate.publicationDateId = :prevPubId ");
		
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
		
		if(status.equalsIgnoreCase("Deleted")){
			str.append(" and model.voterStatus.voterStatusId = 2  ");
			
		}else if(status.equalsIgnoreCase("Added")){
			str.append(" and model.voterStatus.voterStatusId = 1 ");
		}
		str.append(" group by model.voterStatus.voterStatusId,model.voter.gender ");
		
		Query query = getSession().createQuery(str.toString());
		query.setParameter("publicationDateId",publicationDateId);
		query.setParameterList("locationValuesList",locationValuesList);
		
		query.setParameter("prevPubId",prevPubId);
		
		
		if(locationType.equalsIgnoreCase("localElectionBody") || locationType.equalsIgnoreCase("Local Election Body") ||
				locationType.equalsIgnoreCase("ward"))
			query.setParameter("constituencyId",constituencyId);
		return query.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAgeWiseAddedAndDeletedVotersCountByPublicationDateIdInALocation(String locationType,List<Long> locationValuesList,Long constituencyId,Long publicationDateId,Long ageFrom, Long ageTo, String queryStr,String status,Long prevPubId)
	{
		StringBuilder str = new StringBuilder();
		str.append(queryStr);
		str.append("  from VoterModification model, BoothPublicationVoter model2 where model.voter.voterId = model2.voter.voterId and ");
		str.append(" model.publicationDate.publicationDateId = :publicationDateId  and model2.booth.publicationDate.publicationDateId = :prevPubId and ");
		
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
		if(status.equalsIgnoreCase("Deleted")){
			str.append(" and model.voterStatus.voterStatusId = 2  ");
			
		}else if(status.equalsIgnoreCase("Added")){
			str.append(" and model.voterStatus.voterStatusId = 1 ");
		}
		str.append(" group by model.voterStatus.voterStatusId,model.voter.gender");
		
		Query query = getSession().createQuery(str.toString());
		query.setParameter("publicationDateId",publicationDateId);
		query.setParameterList("locationValuesList",locationValuesList);
		query.setParameter("ageFrom",ageFrom);
		
		query.setParameter("prevPubId",prevPubId);
		
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
		stringBuilder.append(" from VoterModification model, Booth model2 where model.constituency.constituencyId = model2.constituency.constituencyId and model.partNo = model2.partNo and ");
		stringBuilder.append(" model.publicationDate.publicationDateId = model2.publicationDate.publicationDateId and model.publicationDate.publicationDateId in(:publicationIdsList) and ");
		
		if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))
			 stringBuilder.append(" model2.constituency.constituencyId in(:locationValuesList) group by model2.constituency.constituencyId ");
		 
		 else if(type.equalsIgnoreCase(IConstants.MANDAL))
			 stringBuilder.append(" model2.tehsil.tehsilId in(:locationValuesList) group by model2.tehsil.tehsilId ");
		 
		 else if(type.equalsIgnoreCase(IConstants.LOCALELECTIONBODY) || type.equalsIgnoreCase("localElectionBody"))
			 stringBuilder.append(" model2.localBody.localElectionBodyId in(:locationValuesList) group by model2.localBody.localElectionBodyId ");
		 
		 else if(type.equalsIgnoreCase(IConstants.PANCHAYAT))
			 stringBuilder.append(" model2.panchayat.panchayatId in(:locationValuesList) group by model2.panchayat.panchayatId ");
		 
		 else if(type.equalsIgnoreCase(IConstants.BOOTH))
			 stringBuilder.append(" model2.boothId in(:locationValuesList) group by model2.boothId ");
		 
		 else if(type.equalsIgnoreCase(IConstants.WARD))
			 stringBuilder.append(" model2.localBodyWard.constituencyId in(:locationValuesList) group by model2.localBodyWard.constituencyId ");
		
		stringBuilder.append(" , model.publicationDate.publicationDateId, model.voterStatus.voterStatusId, model.voter.gender order by cast(model2.partNo,int)");
		
		Query queryObj = getSession().createQuery(stringBuilder.toString());
		
		queryObj.setParameterList("publicationIdsList", publicationIdsList);
		queryObj.setParameterList("locationValuesList", locationValuesList);
		
		
		return queryObj.list();
	}
	public List<Object[]> getAllSelectedVotersDetails(Long constituencyId, List<Long> publicationIdsList, Long locationValue, String type, String queryStr){
		
	     StringBuffer str = new StringBuffer();
	     
	       /* str.append(" select model.voter.voterId,model.voter.name,model.voter.gender, model.voter.age, model.voter.relativeName, model.voter.relationshipType, ");
			str.append(" model2.booth.boothId, model2.booth.partNo, model2.booth.villagesCovered, ");
			str.append(" model.status, model.publicationDate.publicationDateId,model.publicationDate.name,model.voter.houseNo ");
			str.append(" from VoterModification model, BoothPublicationVoter model2 ");
			str.append(" where model.voter.voterId = model2.voter.voterId and ");
			str.append(" model.publicationDate.publicationDateId in(:publicationIdsList) and ");
	     */
	     
			str.append(" select model.voter.voterId,model.voter.name,model.voter.gender, model.voter.age, model.voter.relativeName, model.voter.relationshipType, ");
			str.append(" model2.booth.boothId, model2.booth.partNo, model2.booth.villagesCovered, ");
			str.append(" model.status, model.publicationDate.publicationDateId,model.publicationDate.name,model.voter.houseNo,model.voter.voterIDCardNo,model.voter.houseNo,model2.booth.villagesCovered ");
			str.append(" from VoterModification model, BoothPublicationVoter model2 where model.voter.voterId = model2.voter.voterId and ");
			str.append(" model.publicationDate.publicationDateId in(:publicationIdsList) and model.partNo = model2.booth.partNo and ");
			
			/*str.append(" select count(model.voter.voterId),model.status,model.voter.gender from VoterModification model, BoothPublicationVoter model2 where model.voter.voterId = model2.voter.voterId and ");
			str.append(" model.publicationDate.publicationDateId in(:publicationIdsList) and model.partNo = model2.booth.partNo and ");
			
			*/
	     if(type.equalsIgnoreCase(IConstants.CONSTITUENCY))     
	    	 str.append(" model.constituency.constituencyId  = :locationValue ");
	    	 //str.append(" model2.constituency.constituency.constituencyId  = :locationValue)");
	     else if(type.equalsIgnoreCase(IConstants.MANDAL))
	    	 str.append(" model2.booth.tehsil.tehsilId = :locationValue ");
	     else if(type.equalsIgnoreCase(IConstants.LOCALELECTIONBODY) || type.equalsIgnoreCase("localElectionBody"))
	    	 str.append(" model2.booth.localBody.localElectionBodyId = :locationValue ");
	     else if(type.equalsIgnoreCase(IConstants.PANCHAYAT))
	    	 str.append(" model2.booth.panchayat.panchayatId = :locationValue ");
	     else if(type.equalsIgnoreCase(IConstants.BOOTH))
	    	 str.append(" model2.booth.boothId = :locationValue ");
	     else if(type.equalsIgnoreCase(IConstants.WARD))
			 str.append(" model2.booth.localBodyWard.constituencyId = :locationValue ");
	     
	     str.append(queryStr);
	     
	     Query query = getSession().createQuery(str.toString());
	     
	     query.setParameter("locationValue", locationValue);
	     query.setParameterList("publicationIdsList", publicationIdsList);
	     
	     //query.setFirstResult(0);
	     //query.setMaxResults(100);
			
			return query.list();
			
		}
	@SuppressWarnings("unchecked")
	public List<VoterModification> getVoterModificationsByConstituencyId(Long constituencyId)
	{
		return getHibernateTemplate().find("select model from VoterModification model where model.constituency.constituencyId = ?",constituencyId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getVoterModificationsByConstituencyId2(Long constituencyId)
	{
		return getHibernateTemplate().find("select model.voterModificationId, model.voter.voterId, model.status from VoterModification model where model.constituency.constituencyId = ?",constituencyId);
	}
	
	public Integer updateVoterStatus(Long statusId, List<Long> values)
	{
		Query query = getSession().createQuery("update VoterModification model set model.voterStatus.voterStatusId = :statusId where model.voterModificationId in (:values)");
		query.setParameter("statusId",statusId);
		query.setParameterList("values", values);
		return query.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getBoothWiseVotersDataByBoothIds(Long constituencyId,Long publicationDateId,List<Long> partNosList)
	{
		Query queryObj = getSession().createQuery("select count(model.voter.voterId), model.voterStatus.status,model.partNo,model2.booth.boothId,model2.booth.villagesCovered " +
				" from VoterModification model,BoothPublicationVoter model2 where model.voter.voterId = model2.voter.voterId and model.partNo = model2.booth.partNo and model.publicationDate.publicationDateId =:publicationDateId " +
				" and model.partNo in(:partNosList) and model.constituency.constituencyId =:constituencyId " +
				" group by model.partNo,model.voterStatus.voterStatusId ");
		
		queryObj.setParameterList("partNosList", partNosList);
		queryObj.setParameter("constituencyId", constituencyId);
		queryObj.setParameter("publicationDateId", publicationDateId);
		
		return queryObj.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getSelectedVotersDetails(Long constituencyId, List<Long> publicationIdsList, List<Long> partNo, Long voterStatusId){
		
	     StringBuffer str = new StringBuffer();
			str.append(" select model.voter.voterId,model.voter.name,model.voter.gender, model.voter.age, model.voter.relativeName, model.voter.relationshipType, ");
			str.append(" model2.booth.boothId, model2.booth.partNo, model2.booth.villagesCovered, model.voter.houseNo,model.voter.voterIDCardNo,model.voter.relativeName ");
			str.append(" from VoterModification model, BoothPublicationVoter model2 where model.voter.voterId = model2.voter.voterId and ");
			str.append(" model.publicationDate.publicationDateId in(:publicationIdsList) and model.partNo = model2.booth.partNo and ");
			str.append(" model.partNo in(:partNo) and model.constituency.constituencyId =:constituencyId ");
			str.append(" and model.voterStatus.voterStatusId =:voterStatusId ");
	     
	     Query query = getSession().createQuery(str.toString());
	     
	     query.setParameterList("partNo", partNo);
	     query.setParameterList("publicationIdsList", publicationIdsList);
	     query.setParameter("voterStatusId", voterStatusId);
	     query.setParameter("constituencyId", constituencyId);
	     
			return query.list();
			
		}
	
	@SuppressWarnings("unchecked")
	public List<Long> getPartNoForMovedOrRelocatedVoter(Long voterId, Long publicationDateId, Long constituencyId, Long voterStatusId)
	{
		Query queryObj = getSession().createQuery("select model.partNo from VoterModification model where model.voter.voterId =:voterId " +
				"  and model.publicationDate.publicationDateId =:publicationDateId and model.constituency.constituencyId =:constituencyId and model.voterStatus.voterStatusId =:voterStatusId ");
		
		queryObj.setParameter("voterId", voterId);
		queryObj.setParameter("publicationDateId", publicationDateId);
		queryObj.setParameter("voterStatusId", voterStatusId);
		queryObj.setParameter("constituencyId", constituencyId);
	     
		return queryObj.list();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getMovedOrRelocatedVoterDetails(Long constituencyId, Long publicationDateId, List<Long> partNosList)
	{
		Query queryObj = getSession().createQuery(" select model.voter.voterId,model.voter.name,model.voter.gender,model.voter.age,model.voter.relativeName, model.voter.relationshipType," +
				" model2.booth.boothId, model2.booth.partNo, model2.booth.villagesCovered, model.voter.houseNo,model.voter.voterIDCardNo,model.voter.relativeName,model.voterStatus.status from VoterModification model, " +
				" BoothPublicationVoter model2 where model.voter.voterId = model2.voter.voterId and model.publicationDate.publicationDateId = :publicationDateId and model.partNo = model2.booth.partNo " +
				" and model.partNo in (:partNosList) and model.constituency.constituencyId =:constituencyId and " +
				" (model.voterStatus.status = '"+IConstants.STATUS_MOVED+"' or model.voterStatus.status = '"+IConstants.STATUS_RELOCATED+"') ");
		
		queryObj.setParameter("constituencyId", constituencyId);
		queryObj.setParameter("publicationDateId", publicationDateId);
		queryObj.setParameterList("partNosList", partNosList);
		
		return queryObj.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getVoterModificationDetailsOfAConstituencyForAPublication(Long constituencyId, Long publicationDateId)
	{
		Query query = getSession().createQuery("select model.voterModificationId,model.voter.voterId, model.status, model.partNo, model.constituency.constituencyId,model.publicationDate.publicationDateId,model.voterStatus.voterStatusId from VoterModification model, BoothPublicationVoter model2 where " +
				" model.voter.voterId = model2.voter.voterId and model2.booth.constituency.constituencyId = :constituencyId and model2.booth.publicationDate.publicationDateId = :publicationDateId ");
		query.setParameter("constituencyId",constituencyId);
		query.setParameter("publicationDateId",publicationDateId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getAvailableConstituenciesInAPublication(Long publicationDateId)
	{
		Query query = getSession().createQuery("select distinct model.constituency.constituencyId from VoterModification model where model.publicationDate.publicationDateId = :publicationDateId");
		query.setParameter("publicationDateId",publicationDateId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getListOfVoterIdsInAPublicationBasedOnCount(Long constituencyId, Long publicationDateId, Long count)
	{
		Query query = getSession().createQuery("select model.voter.voterId from VoterModification model where model.constituency.constituencyId = :constituencyId and " +
				" model.publicationDate.publicationDateId = :publicationDateId group by model.voter.voterId having count(model.voter.voterId) = :count ");
		query.setParameter("constituencyId",constituencyId);
		query.setParameter("publicationDateId",publicationDateId);
		query.setParameter("count",count);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<VoterModification> getObjectsByVoterIdsList(Long constituencyId, Long publicationDateId,List<Long> voterIdsList)
	{
		Query query = getSession().createQuery("Select model from VoterModification model where model.constituency.constituencyId = :constituencyId and " +
				" model.publicationDate.publicationDateId = :publicationDateId and model.voter.voterId in (:voterIdsList) ");
		query.setParameterList("voterIdsList", voterIdsList);
		query.setParameter("constituencyId",constituencyId);
		query.setParameter("publicationDateId",publicationDateId);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getVMVoterIdsAndStatusList(Long constituencyId, Long publicationDateId,List<Long> voterIdsList)
	{
		Query query = getSession().createQuery("Select model.voterModificationId,model.status from VoterModification model where model.constituency.constituencyId = :constituencyId and " +
				" model.publicationDate.publicationDateId = :publicationDateId and model.voter.voterId in (:voterIdsList) ");
		query.setParameterList("voterIdsList", voterIdsList);
		query.setParameter("constituencyId",constituencyId);
		query.setParameter("publicationDateId",publicationDateId);
		return query.list();
	}
	
	public Integer updateVoterStatus(List<Long> voterModificationIdsList, Long voterStatusId)
	{
		Query query = getSession().createQuery(" update VoterModification model set model.voterStatusId = :voterStatusId where model.voterModificationId in(:voterModificationIdsList) ");
		query.setParameterList("voterModificationIdsList",voterModificationIdsList);
		query.setParameter("voterStatusId",voterStatusId);
		return query.executeUpdate();
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAddedVotersByBoothIds(List<Long> boothIds,Long publicationId,Long constituencyId)
	{
		Query query = getSession().createQuery("select distinct model2.boothId,count(model.voter.voterId) from VoterModification model, Booth model2 where model.constituency.constituencyId = model2.constituency.constituencyId" +
			(" and model.publicationDate.publicationDateId=:publicationId and model.partNo = model2.partNo and model.voterStatus.status = '"+IConstants.STATUS_ADDED+"' and model2.boothId in( :boothIds) and model2.constituency.constituencyId = :constituencyId group by model2.boothId"));
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationId", publicationId);
		query.setParameterList("boothIds", boothIds);
		return query.list();
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getAddedVotersDetailsByPartNo(Long partNo,Long publicationId,Long constituencyId,Integer startIndex,Integer maxIndex)
	{
		Query query = getSession().createQuery("select model.voter.voterId,model.voter.name,model.voter.age,model.voter.gender,model.voter.houseNo from VoterModification model where" +
			
			(" model.publicationDate.publicationDateId=:publicationId and model.voterStatus.status = '"+IConstants.STATUS_ADDED+"' and model.partNo=:partNo and model.constituency.constituencyId = :constituencyId "));
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationId", publicationId);
		query.setParameter("partNo", partNo);
		if(startIndex!=null)
		query.setFirstResult(startIndex);
		if(maxIndex != null)
		query.setMaxResults(maxIndex);
		return query.list();
	}
	
	
}
