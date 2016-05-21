package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IBloodDonationCampDAO;
import com.itgrids.partyanalyst.model.BloodDonationCamp;

public class BloodDonationCampDAO extends GenericDaoHibernate<BloodDonationCamp, Long> implements
		IBloodDonationCampDAO {
	public BloodDonationCampDAO(){
		super(BloodDonationCamp.class);
	}
	
	public Object[] getCampDates(Long campId){
		Query query = getSession().createQuery(" select model.fromDate,model.toDate " +
				" from BloodDonationCamp model " +
				" where model.bloodDonationCampId=:campId ");
		query.setParameter("campId", campId);
		return (Object[]) query.uniqueResult();
	}
}
