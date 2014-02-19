package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;


import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IVoterFlagDAO;
import com.itgrids.partyanalyst.model.VoterFlag;
import com.itgrids.partyanalyst.utils.IConstants;

public class VoterFlagDAO extends GenericDaoHibernate<VoterFlag, Long> implements IVoterFlagDAO{

	public VoterFlagDAO() {
		super(VoterFlag.class);
		
	}
	public Integer deleteVoterFlag(Long flagId)
	{
		Query query = getSession().createQuery("delete from VoterFlag model where model.flag.flagId = :flagId");
		query.setParameter("flagId", flagId);
		return query.executeUpdate();
	}

	
	public List<Object[]> getFlagWiseVotersCountByLocationId(Long locationId,Long constituencyId,String locationType,Long publicationDateId)
	{
	StringBuilder str = new StringBuilder();
	Query query = null;
	str.append("select count(distinct vf.voter.voterId),vf.flag.flagId,vf.flag.name,vf.flag.color,vf.voter.gender");
	if(locationType != null && locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
	 str.append(",bpv.booth.constituency.constituencyId");
	else if(locationType != null && locationType.equalsIgnoreCase(IConstants.MANDAL))
	 str.append(",bpv.booth.tehsil.tehsilId");
	else if(locationType != null && locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
	 str.append(",bpv.booth.panchayat.panchayatId");
	else if(locationType != null && locationType.equalsIgnoreCase(IConstants.BOOTH))
	 str.append(",bpv.booth.boothId");
	else if(locationType != null && locationType.equalsIgnoreCase(IConstants.LOCALELECTIONBODY))
	 str.append(",bpv.booth.localBody.localElectionBodyId");
	else if(locationType != null && locationType.equalsIgnoreCase(IConstants.WARD))
	 str.append(",bpv.booth.localBodyWard.constituencyId");
	 str.append(" from BoothPublicationVoter bpv,VoterFlag vf,Voter v where bpv.booth.constituency.constituencyId =:constituencyId and bpv.booth.publicationDate.publicationDateId =:publicationDateId and vf.voter.voterId = bpv.voter.voterId and v.voterId = vf.voter.voterId");
			
		/*if(locationType != null && locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
		 str.append(" and bpv.booth.constituency.constituencyId =:locationId");*/
		 if(locationType != null && locationType.equalsIgnoreCase(IConstants.MANDAL))
		 str.append(" and bpv.booth.tehsil.tehsilId =:locationId");
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
		 str.append(" and bpv.booth.panchayat.panchayatId =:locationId and bpv.booth.localBody is null");
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.BOOTH))
		 str.append(" and bpv.booth.boothId =:locationId");
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.LOCALELECTIONBODY))
		 str.append(" and bpv.booth.localBody.localElectionBodyId =:locationId");
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.WARD))
		 str.append(" and bpv.booth.localBodyWard.constituencyId =:locationId");
		
		str.append(" group by vf.flag.flagId,v.gender");
		
		query = getSession().createQuery(str.toString());
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationDateId", publicationDateId);
		if(locationType != null && !(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY)))
		query.setParameter("locationId", locationId);
		return query.list();
	}
	
	
	public List<Object[]> getFlagWiseVotersCountByLocationIdForHamlet(Long locationId,Long constituencyId,String locationType,Long publicationDateId)
	{
	StringBuilder str = new StringBuilder();
	Query query = null;
	str.append("select count(distinct vf.voter.voterId),vf.flag.flagId,vf.flag.name,vf.flag.color,vf.voter.gender");
	if(locationType != null && locationType.equalsIgnoreCase(IConstants.HAMLET))
	 str.append(",uv.hamlet.hamletId");
	else if(locationType != null && locationType.equalsIgnoreCase(IConstants.MANDAL))
	 str.append(",bpv.booth.tehsil.tehsilId");
	 str.append(" from BoothPublicationVoter bpv, UserVoterDetails uv,VoterFlag vf,Voter v where bpv.booth.constituency.constituencyId =:constituencyId and bpv.booth.publicationDate.publicationDateId =:publicationDateId and vf.voter.voterId = bpv.voter.voterId and v.voterId = vf.voter.voterId and uv.voter.voterId = bpv.voter.voterId ");
		if(locationType != null && locationType.equalsIgnoreCase(IConstants.HAMLET))
		 str.append(" and uv.hamlet.hamletId =:locationId");
		else if(locationType != null && locationType.equalsIgnoreCase("customWard"))
			 str.append(" and uv.ward.constituencyId =:locationId");
		 str.append(" group by vf.flag.flagId,v.gender");
		 
		 query = getSession().createQuery(str.toString());
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("locationId", locationId);
		return query.list();
	}
	
	
	public List<Object[]> getFlagInfoByBoterIds(List<Long> voterIds)
	{
		Query query = getSession().createQuery("select distinct model.voter.voterId,model.flag.flagId,model.flag.name,model.flag.color from VoterFlag model where model.voter.voterId in (:voterIds)");
		query.setParameterList("voterIds", voterIds);
		return query.list();
		
		
	}
	
	public List<Long> getFlagsByVoterIds(Long voterId)
	{
		Query query = getSession().createQuery("select distinct model.flag.flagId from VoterFlag model where model.voter.voterId=:voterId");
		query.setParameter("voterId", voterId);
		return query.list();
		
		
	}
	
	public List<Object> getvoterFlagByFlagIdAndUser(Long flagId,Long userId,Long voterId)
	{
		Query query = getSession().createQuery("select model.voterFlagId from VoterFlag model where model.voter.voterId=:voterId and  model.flag.flagId =:flagId and model.user.userId =:userId");
		query.setParameter("flagId", flagId);
		query.setParameter("userId", userId);
		query.setParameter("voterId", voterId);
		return query.list();
	}
	
	public Integer deleteVoterFlagById(Long voterFlagId)
	{
		Query query = getSession().createQuery("delete from VoterFlag model where model.voterFlagId = :voterFlagId");
		query.setParameter("voterFlagId", voterFlagId);
		return query.executeUpdate();
	}
	
	 public List<Long> getFlagCountForSelectedLevel(List<Long> boothIds ,long constituencyId,Long userId)
	  {
		StringBuffer queryString = new StringBuffer();
		queryString.append("select distinct count(model.voterFlagId) from VoterFlag model,BoothPublicationVoter BPV where" +
		  		" model.voter.voterId = BPV.voter.voterId and model.user.userId = :userId" +
		  		" and BPV.booth.constituency.constituencyId = :constituencyId and BPV.booth.boothId in (:boothIds)");		  
		Query query = getSession().createQuery(queryString.toString());
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("userId", userId);
		query.setParameterList("boothIds", boothIds);
		return query.list();
	  }
	
	 @SuppressWarnings("unchecked")
		public List<Long> getCountForSelectedTypeInHamlet(Long hamletId,Long userId,String selLevel)
		{
			StringBuffer queryString = new StringBuffer();
			queryString.append("select count(model.voterFlagId) from VoterFlag model, UserVoterDetails UVD where " +
			" model.voter.voterId = UVD.voter.voterId  and UVD.user.userId = :userId  ");
			if(selLevel.equalsIgnoreCase("hamlet"))
			{
				queryString.append(" and UVD.hamlet.hamletId = :hamletId");
			}
			else
			{
				queryString.append(" and UVD.ward.constituencyId = :hamletId");
			}
			Query query = getSession().createQuery(queryString.toString());
			query.setParameter("hamletId", hamletId);
			query.setParameter("userId", userId);
			return query.list();
		}
	 
	 
	 public List<Object[]> getFlagVoterDetailsByLocationId(Long constituencyId,Long locationId,Long flagId,String locationType,Long publicationDateId, Integer startIndex,
				Integer maxRecords)
		{
         
		 StringBuilder str = new StringBuilder();
			Query query = null;
			str.append("select distinct vf.voter,vf.flag.flagId,vf.flag.name,vf.flag.color");
			if(locationType != null && locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
			 str.append(",bpv.booth.constituency.constituencyId");
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.MANDAL))
			 str.append(",bpv.booth.tehsil.tehsilId");
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
			 str.append(",bpv.booth.panchayat.panchayatId");
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.BOOTH))
			 str.append(",bpv.booth.boothId");
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.LOCALELECTIONBODY))
			 str.append(",bpv.booth.localBody.localElectionBodyId");
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.WARD))
			 str.append(",bpv.booth.localBodyWard.constituencyId");
			 str.append(" from BoothPublicationVoter bpv,VoterFlag vf,Voter v where bpv.booth.constituency.constituencyId =:constituencyId and bpv.booth.publicationDate.publicationDateId =:publicationDateId and vf.voter.voterId = bpv.voter.voterId " +
			 		" and v.voterId = vf.voter.voterId and vf.flag.flagId = :flagId");
				/*if(locationType != null && locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
				 str.append(" and bpv.booth.constituency.constituencyId =:locationId");*/
				if(locationType != null && locationType.equalsIgnoreCase(IConstants.MANDAL))
				 str.append(" and bpv.booth.tehsil.tehsilId =:locationId");
				else if(locationType != null && locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
				 str.append(" and bpv.booth.panchayat.panchayatId =:locationId and bpv.booth.localBody is null");
				else if(locationType != null && locationType.equalsIgnoreCase(IConstants.BOOTH))
				 str.append(" and bpv.booth.boothId =:locationId");
				else if(locationType != null && locationType.equalsIgnoreCase(IConstants.LOCALELECTIONBODY))
				 str.append(" and bpv.booth.localBody.localElectionBodyId =:locationId");
				else if(locationType != null && locationType.equalsIgnoreCase(IConstants.WARD))
				 str.append(" and bpv.booth.localBodyWard.constituencyId =:locationId");
				query = getSession().createQuery(str.toString());
				if(locationType != null && !(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY)))
				query.setParameter("locationId", locationId);
				query.setParameter("publicationDateId", publicationDateId);
				query.setParameter("flagId", flagId);
				query.setParameter("constituencyId", constituencyId);
				query.setFirstResult(startIndex);
				query.setMaxResults(maxRecords);
				return query.list();
		 }
		
		
		public List<Object[]> getFlagVoterDetailsForHamlet(Long constituencyId,Long locationId,Long flagId,String locationType,Long publicationDateId, Integer startIndex,
				Integer maxRecords)
		{
		StringBuilder str = new StringBuilder();
		Query query = null;
		str.append("select distinct vf.voter,vf.flag.flagId,vf.flag.name,vf.flag.color");
	    str.append(" from BoothPublicationVoter bpv, UserVoterDetails uv,VoterFlag vf,Voter v where bpv.booth.constituency.constituencyId =:constituencyId and bpv.booth.publicationDate.publicationDateId =:publicationDateId and vf.voter.voterId = bpv.voter.voterId and v.voterId = vf.voter.voterId " +
		 		"and uv.voter.voterId = bpv.voter.voterId and vf.flag.flagId = :flagId");
			if(locationType != null && locationType.equalsIgnoreCase(IConstants.HAMLET))
			 str.append(" and uv.hamlet.hamletId =:locationId");
			else if(locationType != null && locationType.equalsIgnoreCase("customWard"))
				 str.append(" and uv.ward.constituencyId =:locationId");
			 query = getSession().createQuery(str.toString());
			 query.setParameter("locationId", locationId);
			 query.setParameter("publicationDateId", publicationDateId);
			 query.setParameter("flagId", flagId);
			 query.setParameter("constituencyId", constituencyId);
			 query.setFirstResult(startIndex);
			 query.setMaxResults(maxRecords);
			 return query.list();
		}
		
		
		 public List getFlagVoterDetailsByLocationIdCount(Long constituencyId,Long locationId,Long flagId,String locationType,Long publicationDateId
					)
			{
	         
			 StringBuilder str = new StringBuilder();
				Query query = null;
				str.append("select count(distinct vf.voter.voterId)");
				
				 str.append(" from BoothPublicationVoter bpv,VoterFlag vf,Voter v where bpv.booth.constituency.constituencyId =:constituencyId and bpv.booth.publicationDate.publicationDateId =:publicationDateId and vf.voter.voterId = bpv.voter.voterId " +
				 		" and v.voterId = vf.voter.voterId and vf.flag.flagId = :flagId");
				/*	if(locationType != null && locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
					 str.append(" and bpv.booth.constituency.constituencyId =:locationId");*/
					 if(locationType != null && locationType.equalsIgnoreCase(IConstants.MANDAL))
					 str.append(" and bpv.booth.tehsil.tehsilId =:locationId");
					else if(locationType != null && locationType.equalsIgnoreCase(IConstants.PANCHAYAT))
					 str.append(" and bpv.booth.panchayat.panchayatId =:locationId and bpv.booth.localBody is null");
					else if(locationType != null && locationType.equalsIgnoreCase(IConstants.BOOTH))
					 str.append(" and bpv.booth.boothId =:locationId");
					else if(locationType != null && locationType.equalsIgnoreCase(IConstants.LOCALELECTIONBODY))
					 str.append(" and bpv.booth.localBody.localElectionBodyId =:locationId");
					else if(locationType != null && locationType.equalsIgnoreCase(IConstants.WARD))
					 str.append(" and bpv.booth.localBodyWard.constituencyId =:locationId");
					query = getSession().createQuery(str.toString());
					if(locationType != null && !(locationType.equalsIgnoreCase(IConstants.CONSTITUENCY)))
					query.setParameter("locationId", locationId);
					query.setParameter("publicationDateId", publicationDateId);
					query.setParameter("flagId", flagId);
					query.setParameter("constituencyId", constituencyId);
					
					return query.list();
			 }
			
			
			public List getFlagVoterDetailsForHamletCount(Long constituencyId,Long locationId,Long flagId,String locationType,Long publicationDateId
					)
			{
			StringBuilder str = new StringBuilder();
			Query query = null;
			str.append("select count(distinct vf.voter.voterId)");
			if(locationType != null && locationType.equalsIgnoreCase(IConstants.HAMLET))
			 str.append(",uv.hamlet.hamletId");
			else if(locationType != null && locationType.equalsIgnoreCase(IConstants.MANDAL))
			 str.append(",bpv.booth.tehsil.tehsilId");
			 str.append(" from BoothPublicationVoter bpv, UserVoterDetails uv,VoterFlag vf,Voter v where bpv.booth.constituency.constituencyId =:constituencyId and bpv.booth.publicationDate.publicationDateId =:publicationDateId and vf.voter.voterId = bpv.voter.voterId and v.voterId = vf.voter.voterId " +
			 		"and uv.voter.voterId = bpv.voter.voterId and vf.flag.flagId = :flagId");
				if(locationType != null && locationType.equalsIgnoreCase(IConstants.HAMLET))
				 str.append(" and uv.hamlet.hamletId =:locationId");
				else if(locationType != null && locationType.equalsIgnoreCase("customWard"))
					 str.append(" and uv.ward.constituencyId =:locationId");
				 query = getSession().createQuery(str.toString());
				 query.setParameter("locationId", locationId);
				 query.setParameter("publicationDateId", publicationDateId);
				 query.setParameter("flagId", flagId);
				 query.setParameter("constituencyId", constituencyId);
				 return query.list();
			}
			
			 public List<Long> checkFlagExistanceForVoter(Long flagId,Long voterId,Long userId)
			 {
				 Query query = getSession().createQuery("select count(model.voter.voterId) from VoterFlag model " +
				 		"where model.user.userId = :userId and " +
				 		"model.voter.voterId = :voterId and model.flag.flagId = :flagId");
				 
				 query.setParameter("userId", userId);
				 query.setParameter("voterId", voterId);
				 query.setParameter("flagId", flagId);
				 
				 return query.list();
				 
				 
			 }
}
