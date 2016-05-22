package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IBloodDonationDAO;
import com.itgrids.partyanalyst.dto.BloodBankVO;
import com.itgrids.partyanalyst.model.BloodDonation;
import com.itgrids.partyanalyst.utils.IConstants;

public class BloodDonationDAO extends GenericDaoHibernate<BloodDonation, Long> implements IBloodDonationDAO {
 
	public BloodDonationDAO(){
	   super(BloodDonation.class);
   }
	
	public List<Object[]> getCadreDetailsOfRegistered(String memberShipId){
		
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
		
		query.setParameter("memberShipId", memberShipId);
		query.setParameter("enrollmentYear", IConstants.CADRE_ENROLLMENT_YEAR);
		
		return  query.list();		
	}
	
	public List<Object[]> getBleedingCadreDetails(List<Long> statusIds,Long campId)
	{		//0.tdpCadreId,1.memberShipNo,2.donorName,3.mobileNo,4.acceptanceStatusId,5.status,6.bagNo,
		//7.bloodBagTypeId,8.bagType,9.bloodBagQuantityId,10.type,11.quantity,12.remarks
		StringBuilder str = new StringBuilder();		
		str.append(" SELECT TC.tdpCadreId,TC.memberShipNo,BDI.donorName," +
							" BDI.mobileNo,ACTS.acceptanceStatusId,ACTS.status,model.bagNo," +
							" BBT.bloodBagTypeId,BBT.bagType," +
							" BBQ.bloodBagQuantityId,BBQ.type," +
							" model.quantity,model.remarks" +
				   " FROM " +
				   " 	BloodDonation model left join model.bloodDonorInfo BDI" +
				   "    left join BDI.tdpCadre TC" +
				   "    left join model.bloodDonorInfo BDI"+
				   "    left join model.acceptanceStatus ACTS"+
				   "	left join model.bloodBagType BBT"+
				   "	left join model.bloodBagQuantity BBQ"+
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
	
public List<Object[]> gettotalCollectedBloodDetails(Date fromDate,Date toDate){
		
		Query query = getSession().createQuery("select sum(model.quantity)," +
												" date(model.insertedTime)" +
												" from BloodDonation model" +
												" where (date(model.insertedTime) between :fromDate and :toDate)" +
												" group by date(model.insertedTime)" +
												" order by date(model.insertedTime)");
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);
		
		return query.list();
	}
	
	public Long getBloodDonatedOtherThanBloodBank(){
		
		Query query = getSession().createQuery("select count(distinct bdi.tdpCadreId)" +
												" from BloodDonation bd,BloodDonorInfo bdi" +
												" where bd.bloodDonorInfoId = bdi.bloodDonorInfoId" +
												" and bd.donationsInOtherPlaces= 'Yes'" +
												" and bdi.isDeleted = 'N'");
												
		return (Long) query.uniqueResult();
	}
	
	public Long getBloodDonarCountInEmergency(){
		
		Query query = getSession().createQuery("select count(distinct bdi.tdpCadreId)" +
												" from BloodDonation bd,BloodDonorInfo bdi" +
												" where bd.bloodDonorInfoId = bdi.bloodDonorInfoId" +
												" and bd.emergencyDonation= 'Yes'" +
												" and bdi.isDeleted = 'N'");
												
		return (Long) query.uniqueResult();
	}
	
	public Long getCalledForDonationCount(){
		
		Query query = getSession().createQuery("select count(distinct bdi.tdpCadreId)" +
												" from BloodDonation bd,BloodDonorInfo bdi" +
												" where bd.bloodDonorInfoId = bdi.bloodDonorInfoId" +
												" and bd.willingToCallDonation= 'Yes'" +
												" and bdi.isDeleted = 'N'");
												
		return (Long) query.uniqueResult();
	}
	
	public List<Object[]> gettotalCollectedBloodBagsInfo(Long campId){
		
		Query query = getSession().createQuery("select count(BBT.bloodBagTypeId)," +
												" BBT.bloodBagTypeId," +
												" BBT.bagType," +
												" BD.bloodBagQuantityId," +
												" BD.bloodBagQuantity.type," +
												" BD.quantity" +
												" from BloodDonation BD,BloodBagType BBT" +
												" where BD.bloodBagType.bloodBagTypeId = BBT.bloodBagTypeId" +
												" and BD.bloodDonationCamp.bloodDonationCampId = :campId" +
												" group by BBT.bloodBagTypeId,BD.bloodBagQuantityId,BD.quantity");
		query.setParameter("campId", campId);
		
		return query.list();
	}
	
	public List<Object[]> getBloodDonorDetailsByAgeGroupingInfo(Date fromDate,Date toDate){
		
		Query query = getSession().createQuery("select count(distinct BD.bloodDonorInfo.tdpCadreId)," +
												" BD.bloodDonationAgeGroup.bloodDonationAgeGroupId," +
												" BD.bloodDonationAgeGroup.ageGroup," +
												" date(BD.bloodDonorInfo.insertedTime)" +
												" from BloodDonation BD" +
												" where (date(BD.bloodDonorInfo.insertedTime) between :fromDate and :toDate)" +
												" group by date(BD.bloodDonorInfo.insertedTime),BD.bloodDonationAgeGroupId");
		query.setParameter("fromDate", fromDate);
		query.setParameter("toDate", toDate);
		
		return query.list();
	}	
	
	public Integer updateBloodDonationDetailsByMemberShip(BloodBankVO bloodBankVO){
		
		StringBuilder str = new StringBuilder();
		
		str.append("update BloodDonation model set ");
		
		if(bloodBankVO !=null){
			if(bloodBankVO.getStatusId() !=null && bloodBankVO.getStatusId()>0){
				str.append(" model.acceptanceStatus.acceptanceStatusId = :statusId ");
			}
			if(bloodBankVO.getBagNo() !=null && !bloodBankVO.getBagNo().isEmpty()){
				str.append(" ,model.bagNo =:bagNo ");
			}
			if(bloodBankVO.getBagTypeId() !=null && bloodBankVO.getBagTypeId()>0){
				str.append(",model.bloodBagTypeId =:bloodBagTypeId ");
			}
			if(bloodBankVO.getBloodBankQuantityId() !=null && bloodBankVO.getBloodBankQuantityId() >0){
				str.append(",model.bloodBagQuantityId =:bloodBagQuantityId ");
			}
			if(bloodBankVO.getQuantity() !=null && bloodBankVO.getQuantity()>0){
				str.append(",model.quantity =:quantity ");
			}
			
			str.append(" where model.tdpCadre.memberShipNo = :memberShipNo ");
		}
		
		Query query=getSession().createQuery(str.toString());
		query.setParameter("memberShipNo",bloodBankVO.getMembershipNo());
		
		if(bloodBankVO.getStatusId() !=null && bloodBankVO.getStatusId()>0){
			query.setParameter("statusId",bloodBankVO.getStatusId());
		}
		if(bloodBankVO.getBagNo() !=null && !bloodBankVO.getBagNo().isEmpty()){
			query.setParameter("bagNo",bloodBankVO.getBagNo());
		}
		
		return query.executeUpdate();		
	}
	public String isTdpCadreExistOrNot(String memberShipNO){
		
		Query query=getSession().createQuery("select model.bloodDonorInfo.tdpCadre.memberShipNo " +
				 " from BloodDonation model " +
				 " where model.bloodDonorInfo.tdpCadre.memberShipNo=:memberShipNO " +
				 " and model.bloodDonorInfo.tdpCadre.isDeleted='N' " +
				 " and model.bloodDonorInfo.tdpCadre.enrollmentYear=:enrollmentYear ");
		
		 query.setParameter("enrollmentYear",IConstants.CADRE_ENROLLMENT_YEAR);
		 query.setParameter("memberShipNO", memberShipNO.trim());
		
		 return (String) query.uniqueResult();
	}
	
	public List<Object[]> getNumberOfTimesCollectedBlood(Long campId){
		
			
			Query query = getSession().createQuery("select model.bloodDonorInfo.tdpCadre.tdpCadreId,model.donationsInBloodBank"+
													" from BloodDonation model" +
													" where model.bloodDonationCamp.bloodDonationCampId = :campId" +
													" group by  model.bloodDonorInfo.tdpCadre.tdpCadreId");
			query.setParameter("campId", campId);
			
			return query.list();
		}
}
