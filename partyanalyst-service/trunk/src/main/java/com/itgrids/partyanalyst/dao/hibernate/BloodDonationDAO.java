package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IBloodDonationDAO;
import com.itgrids.partyanalyst.model.BloodDonation;

public class BloodDonationDAO extends GenericDaoHibernate<BloodDonation, Long> implements IBloodDonationDAO {
 
	public BloodDonationDAO(){
	   super(BloodDonation.class);
   }
}
