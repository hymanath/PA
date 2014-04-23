package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IMobileNumbersDAO;
import com.itgrids.partyanalyst.model.MobileNumbers;

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

}
