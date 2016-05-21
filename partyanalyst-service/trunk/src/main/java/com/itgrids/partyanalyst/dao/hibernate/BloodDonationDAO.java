package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IBloodDonationDAO;
import com.itgrids.partyanalyst.model.BloodDonation;

public class BloodDonationDAO extends GenericDaoHibernate<BloodDonation, Long> implements IBloodDonationDAO {
 
	public BloodDonationDAO(){
	   super(BloodDonation.class);
   }
	
	public Object[] getCadreDetailsOfRegistered(String memberShipId){
		
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append(" SELECT model.bloodDonorInfo,model.donationsInBloodBank,model.donationsInOtherPlaces,model.lastDonationDate," +
								" model.bloodComponent.bloodComponentId,model.bloodComponent.component,model.emergencyDonation,model.willingToCallDonation," +
								" model.remarks,model.donorAge " +
						" FROM    BloodDonation model" +
						" WHERE  " +
								" model.bloodDonorInfo.tdpCadre.memberShipNo = :memberShipId " +
						" AND    model.bloodDonorInfo.tdpCadre.isDeleted ='N'" +
						" AND    model.bloodDonorInfo.tdpCadre.enrollmentYear = :enrollmentYear ");
		
		
		Query query = getSession().createQuery(queryStr.toString());
		
		return (Object[]) query.uniqueResult();
		
	}
}
