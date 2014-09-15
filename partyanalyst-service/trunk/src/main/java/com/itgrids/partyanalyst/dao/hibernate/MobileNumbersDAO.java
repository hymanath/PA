package com.itgrids.partyanalyst.dao.hibernate;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IMobileNumbersDAO;
import com.itgrids.partyanalyst.model.MobileNumbers;
import com.itgrids.partyanalyst.utils.IConstants;

public class MobileNumbersDAO extends GenericDaoHibernate<MobileNumbers, Long> implements IMobileNumbersDAO{

	public MobileNumbersDAO() {
		super(MobileNumbers.class);
		// TODO Auto-generated constructor stub
	}
	
	public List<Long> getConstituencysByDistictID(Long districtId)
	{
		Query query = getSession().createQuery("Select distinct model.acId from MobileNumbers model where model.distNo = :districtId");
		query.setParameter("districtId", districtId);
		return query.list();
		
	}
	@SuppressWarnings("unchecked")
	public List<Object[]> getMobileNoforVoter(Long constituencyId)
	{
		Query query = getSession().createQuery("Select model1.mobile,BPV.voter.voterId from MobileNumbers model1,BoothPublicationVoter BPV where " +
			"model1.idcardNO = BPV.voter.voterIDCardNo and " +
			"BPV.booth.publicationDate.publicationDateId = 10 AND model1.acId = :constituencyId");
		
		query.setParameter("constituencyId", constituencyId);
		return query.list();
		
	}
	public List<Object[]> getUservoterDetailsByUserId(Long userId,List<Long> voterIds)
	{
		Query query = getSession().createSQLQuery("Select uvd.voter_id,uvd.mobile_no,uvd." +
			"user_voter_details_id from user_voter_details uvd where uvd.user_id = :userId and uvd.voter_id in (:voterIds) ");
		query.setParameter("userId", userId);
		query.setParameterList("voterIds", voterIds);
		return query.list();
		
	}
	
	public Integer updateMobileNo(String mobileNo,Long userVoterDetailsId)
	{
		Query query = getSession().createQuery("update UserVoterDetails model set model.mobileNo = :mobileNo where model.userVoterDetailsId = :userVoterDetailsId");
		query.setParameter("mobileNo", mobileNo);
		query.setParameter("userVoterDetailsId", userVoterDetailsId);
		return query.executeUpdate();
	}

	public List<Object[]> getMobileNumberDetailsByBoothId(Long boothId)
	{
		Query query = getSession().createQuery(" select model.mobileSourceTypeId,model.voterId, model.mobileNumber from  MobileNumbers model " +
				" where model.boothId = :boothId ");
		
		query.setParameter("boothId", boothId);
		return query.list();
	}
	
	
	
	
	public Set<String> getVotersMobilenos(Long scopeId,Long location,int maxIndex) 
	{
		StringBuilder str = new StringBuilder(); 
		str.append("select distinct model.mobileNumber from MobileNumbers model where");
		if(scopeId == 1)
		 str.append(" model.constituency.district.districtId =:location")	;
		 else
		 str.append(" model.constituency.constituencyId =:location");
		 str.append(" and model.mobileNumber is not null and length(model.mobileNumber) = 10 and model.mobileNumber <> '9999999999' and model.voter.voterId is not null order by rand()");
		 Query query = getSession().createQuery(str.toString());
		 query.setParameter("location", location);
		 if(maxIndex > 0)
		 {
			 query.setMaxResults(maxIndex);	 
		 }
		 return new HashSet(query.list());
		 
	}
	
	
	public Set<String> getIvrMobilenosBasedOnPriority(Long scopeId,Long location,int maxIndex,String priority) 
	{
		StringBuilder str = new StringBuilder(); 
		str.append("select distinct model.mobileNumber from MobileNumbers model where");
		 if(scopeId == 1)
		 str.append(" model.constituency.district.districtId =:location")	;
		 else
		 str.append(" model.constituency.constituencyId =:location");
		 str.append(" and model.mobileNumber is not null and length(model.mobileNumber) = 10 and model.mobileNumber <> '9999999999' and model.voter.voterId is null");
		 
		 if(priority.equalsIgnoreCase(IConstants.PANCHAYAT))
		  str.append(" and model.panchayat.panchayatId is not null order by rand()");
		 else if(priority.equalsIgnoreCase(IConstants.TEHSIL))
			 str.append(" and model.panchayat.panchayatId is null and model.tehsil.tehsilId is not null order by rand()"); 
		 else if(priority.equalsIgnoreCase(IConstants.CONSTITUENCY))
			 str.append(" and model.panchayat.panchayatId is null and model.tehsil.tehsilId is null and constituency.constituencyId is not null order by rand()"); 
		 
		 Query query = getSession().createQuery(str.toString());
		 query.setParameter("location", location);
		 
		 if(maxIndex > 0)
		 {
			 query.setMaxResults(maxIndex);	 
		 }
		 return new HashSet(query.list());
		 
	}
	
	
			 
}
