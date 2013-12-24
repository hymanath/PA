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
			
		if(locationType != null && locationType.equalsIgnoreCase(IConstants.CONSTITUENCY))
		 str.append(" and bpv.booth.constituency.constituencyId =:locationId");
		else if(locationType != null && locationType.equalsIgnoreCase(IConstants.MANDAL))
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
		else if(locationType != null && locationType.equalsIgnoreCase("wardBooth"))
			 str.append(" and uv.ward.constituencyId =:locationId");
		 str.append(" group by vf.flag.flagId,v.gender");
		 
		 query = getSession().createQuery(str.toString());
		query.setParameter("constituencyId", constituencyId);
		query.setParameter("publicationDateId", publicationDateId);
		query.setParameter("locationId", locationId);
		return query.list();
	}
}
