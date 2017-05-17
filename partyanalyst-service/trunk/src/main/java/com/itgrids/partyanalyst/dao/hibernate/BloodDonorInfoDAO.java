package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IBloodDonorInfoDAO;
import com.itgrids.partyanalyst.model.BloodDonorInfo;

public class BloodDonorInfoDAO extends GenericDaoHibernate<BloodDonorInfo, Long> implements IBloodDonorInfoDAO {
 
	public BloodDonorInfoDAO(){
		super(BloodDonorInfo.class);
	}
	
/*public List<Object[]> getBloodDonorCounts(Date campFromDate,Date campToDate ){
		
		Query query = getSession().createQuery(" select count(model.tdpCadreId)," +
				" model.bloodDonation.acceptanceStatusId " +
				" from BloodDonorInfo model " +
				" where " +
				" date(model.insertedTime) between :campFromDate and :campToDate " +
				" group by model.bloodDonation.acceptanceStatusId ");
		
		query.setDate("campFromDate",campFromDate);
		query.setDate("campToDate",campToDate);
		return query.list();
	}*/
/*public List<Object[]> getBloodDonorDayWiseCounts(Date campFromDate,Date campToDate ){
	
	Query query = getSession().createQuery(" select count(model.tdpCadre.tdpCadreId)," +
			" date(model.insertedTime)," +
			" model.bloodDonation.acceptanceStatusId " +
			" from BloodDonorInfo model " +
			" date(model.insertedTime) between :campFromDate and :campToDate " +
			" group by date(model.insertedTime),model.bloodDonation.acceptanceStatusId ");
	
	query.setDate("campFromDate",campFromDate);
	query.setDate("campToDate",campToDate);
	return query.list();
   }*/
	@SuppressWarnings("unchecked")
	public List<Object[]> getBloodDonorDetails(){
		
		Query query = getSession().createQuery(" select " +
				" model.bloodDonorInfoId," +
				" model.donorName,model.mobileNo," +
				" model.donationTime, " +
				" model.tdpCadre.memberShipNo " +
				" from BloodDonorInfo model where model.isDeleted = 'N' and model.registeredSource = 'app' ");
		
		return query.list();
	   }
}
