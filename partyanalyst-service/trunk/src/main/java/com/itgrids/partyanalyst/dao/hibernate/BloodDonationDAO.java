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
	
	public List<Object[]> getBleedingCadreDetails(List<Long> statusIds,Long campId)
	{		//0.tdpCadreId,1.memberShipNo,2.donorName,3.mobileNo,4.acceptanceStatusId,5.status,6.bagNo,
		//7.bloodBagTypeId,8.bagType,9.bloodBagQuantityId,10.type,11.quantity,12.remarks
		StringBuilder str = new StringBuilder();		
		str.append(" SELECT model.bloodDonorInfo.tdpCadre.tdpCadreId,model.bloodDonorInfo.tdpCadre.memberShipNo,model.bloodDonorInfo.donorName," +
							" model.bloodDonorInfo.mobileNo,model.acceptanceStatus.acceptanceStatusId,model.acceptanceStatus.status,model.bagNo," +
							" model.bloodBagType.bloodBagTypeId,model.bloodBagType.bagType," +
							" model.bloodBagQuantity.bloodBagQuantityId,model.bloodBagQuantity.type," +
							" model.quantity,model.remarks" +
				   " FROM " +
				   " 	BloodDonation model" +
				   " WHERE" +
				   " 		 model.bloodDonorInfo.isDeleted= 'N' ");
		
		if(statusIds !=null && statusIds.size()>0){
			str.append(" AND model.acceptanceStatus.acceptanceStatusId in (:statusIds) ");
		}
		if(campId !=null && campId>0){
			str.append(" AND model.bloodDonationCamp.bloodDonationCampId =:campId ");
		}				
		Query query = getSession().createQuery(str.toString());		
		if(campId !=null && campId>0){
			query.setParameter("campId", campId);
		}
		if(statusIds !=null && statusIds.size()>0){
			query.setParameterList("statusIds", statusIds);
		}		
		return query.list();
	}
	
	
}
