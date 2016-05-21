package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IBloodDonationAgeGroupDAO;
import com.itgrids.partyanalyst.model.BloodDonationAgeGroup;

public class BloodDonationAgeGroupDAO extends GenericDaoHibernate<BloodDonationAgeGroup, Long> implements IBloodDonationAgeGroupDAO {

	public BloodDonationAgeGroupDAO(){
		super(BloodDonationAgeGroup.class);
	}
	
	public List<BloodDonationAgeGroup> getAllAgeGroups(){
		
		Query queryStr = getSession().createQuery(" select model from BloodDonationAgeGroup model");		
		return queryStr.list();
	}
}
