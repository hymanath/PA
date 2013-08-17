package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IVoiceSmsVerifiedNumbersDAO;
import com.itgrids.partyanalyst.model.VoiceSmsVerifiedNumbers;

public class VoiceSmsVerifiedNumbersDAO extends GenericDaoHibernate<VoiceSmsVerifiedNumbers,Long> implements IVoiceSmsVerifiedNumbersDAO {

	public VoiceSmsVerifiedNumbersDAO() {
		super(VoiceSmsVerifiedNumbers.class);
	}
	
	
	public List<Long> getVerifiedNumbersOfUser(Long userId)
	{
		
		Query query = getSession().createQuery("select model.senderMobileNumber from VoiceSmsVerifiedNumbers model where" +
				" model.user.userId = :userId");
		
		query.setParameter("userId", userId);
		
		return query.list();
		
	}

}
