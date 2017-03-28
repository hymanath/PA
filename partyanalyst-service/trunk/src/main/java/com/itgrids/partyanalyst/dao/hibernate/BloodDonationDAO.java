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
								" bloodComponent.bloodComponentId,bloodComponent.component,model.emergencyDonation,model.willingToCallDonation," +
								" model.remarks,model.donorAge,model.bloodDonationId " +
						" FROM    BloodDonation model left join model.bloodComponent bloodComponent " +
						" WHERE  " +
								" model.bloodDonorInfo.tdpCadre.memberShipNo = :memberShipId " +
						" AND    model.bloodDonorInfo.tdpCadre.isDeleted ='N'" +
						" AND    model.bloodDonorInfo.tdpCadre.enrollmentYear = :enrollmentYear ");
		
		Query query = getSession().createQuery(queryStr.toString());	
		
		query.setParameter("memberShipId", memberShipId);
		query.setParameter("enrollmentYear", IConstants.CADRE_ENROLLMENT_YEAR);
		
		return  query.list();		
	}
	
	public List<Object[]> getBleedingCadreDetails(List<Long> statusIds,Long campId,List<Date> datesList)
	{		//0.tdpCadreId,1.memberShipNo,2.donorName,3.mobileNo,4.acceptanceStatusId,5.status,6.bagNo,
		//7.bloodBagTypeId,8.bagType,9.bloodBagQuantityId,10.type,11.quantity,12.remarks
		StringBuilder str = new StringBuilder();		
		str.append(" SELECT TC.tdpCadreId,TC.memberShipNo,BDI.donorName," +
							" BDI.mobileNo,ACTS.acceptanceStatusId,ACTS.status,model.bagNo," +
							" BBT.bloodBagTypeId,BBT.bagType," +
							" BBQ.bloodBagQuantityId,BBQ.type," +
							" model.quantity,model.remarks," +
							"  model.registrationNumber,D.districtId,D.districtName "+
				   " FROM " +
				   " 	BloodDonation model left join model.bloodDonorInfo BDI " +
				   "    left join BDI.tdpCadre TC" +
				   "    left join TC.userAddress UA " +
				   "    left join UA.district D " +
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
		if(datesList != null && datesList.size()>0){
			str.append(" AND date(model.insertedTime) in (:datesList) ");
		}
		str.append(" order by model.insertedTime desc ");
		
		Query query = getSession().createQuery(str.toString());		
		if(campId !=null && campId>0){
			query.setParameter("campId", campId);
		}
		if(statusIds !=null && statusIds.size()>0){
			query.setParameterList("statusIds", statusIds);
		}		
		if(datesList != null && datesList.size()>0){
			query.setParameterList("datesList", datesList);
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
		
		str.append(" update BloodDonation model set ");
		
		if(bloodBankVO !=null){
			if(bloodBankVO.getStatusId() !=null && bloodBankVO.getStatusId()>0){
				str.append(" model.acceptanceStatusId = :statusId ");
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
			if(bloodBankVO.getRegistrationNo()!=null && !bloodBankVO.getRegistrationNo().isEmpty()){
				str.append(",model.registrationNumber =:registrationNumber ");
			}
			if(bloodBankVO.getRemarks()!=null && !bloodBankVO.getRemarks().isEmpty()){
				str.append(",model.remarks =:remarks ");
			}
			str.append(" where model.bloodDonationId= :bloodDonationId ");
		}
		
		Query query=getSession().createQuery(str.toString());
		query.setParameter("bloodDonationId",bloodBankVO.getId());
		
		if(bloodBankVO.getStatusId() !=null && bloodBankVO.getStatusId()>0){
			query.setParameter("statusId",bloodBankVO.getStatusId());
		}
		if(bloodBankVO.getBagNo() !=null && !bloodBankVO.getBagNo().isEmpty()){
			query.setParameter("bagNo",bloodBankVO.getBagNo());
		}
		if(bloodBankVO.getBagTypeId() !=null && bloodBankVO.getBagTypeId()>0){
			query.setParameter("bloodBagTypeId",bloodBankVO.getBagTypeId());
		}
		if(bloodBankVO.getBloodBankQuantityId() !=null && bloodBankVO.getBloodBankQuantityId() >0){
			query.setParameter("bloodBagQuantityId",bloodBankVO.getBloodBankQuantityId());
		}
		if(bloodBankVO.getQuantity() !=null && bloodBankVO.getQuantity()>0){
			query.setParameter("quantity",bloodBankVO.getQuantity());
		}
		if(bloodBankVO.getRegistrationNo()!=null && !bloodBankVO.getRegistrationNo().isEmpty()){
			query.setParameter("registrationNumber", bloodBankVO.getRegistrationNo());
		}
		if(bloodBankVO.getRemarks()!=null && !bloodBankVO.getRemarks().isEmpty()){
			query.setParameter("remarks", bloodBankVO.getRemarks());
		}
		return query.executeUpdate();		
	}
public String isTdpCadreExistOrNot(String memberShipNO,Long campId){
		
	StringBuilder str =new StringBuilder();
	
	str.append(" select model.bloodDonorInfo.tdpCadre.memberShipNo " +
				 " from BloodDonation model " +
				 " where model.bloodDonorInfo.tdpCadre.memberShipNo=:memberShipNO " +
				 " and model.bloodDonorInfo.tdpCadre.isDeleted='N' " +
				 " and model.bloodDonorInfo.tdpCadre.enrollmentYear=:enrollmentYear ");
		if(campId !=null && campId>0){
			str.append(" and model.bloodDonationCampId = :campId  ");
		}
	
		Query query=getSession().createQuery(str.toString());
		
		 query.setParameter("enrollmentYear",IConstants.CADRE_ENROLLMENT_YEAR);
		 query.setParameter("memberShipNO", memberShipNO);
		 if(campId !=null && campId>0){
			 query.setParameter("campId", campId);
		 }
		
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
	
	public Long getBloodDonationIdByMemberShip(String memberShipNo){
		
		Query query=getSession().createQuery("select  model.bloodDonationId  from BloodDonation model  where model.bloodDonorInfo.tdpCadre.memberShipNo=:memberShipNo ");
		      query.setParameter("memberShipNo", memberShipNo);
		return (Long) query.uniqueResult();
	}
		
public List<Object[]> getBloodDonorDayWiseCounts(Date campFromDate,Date campToDate ){
		
		Query query = getSession().createQuery(" select count(model.bloodDonorInfo.tdpCadre.tdpCadreId), " +
				" date(model.bloodDonorInfo.insertedTime), " +
				" model.acceptanceStatusId " +
				" from BloodDonation model where " +
				" date(model.bloodDonorInfo.insertedTime) between :campFromDate and :campToDate " +
				" group by date(model.bloodDonorInfo.insertedTime),model.acceptanceStatusId ");
		
		query.setDate("campFromDate",campFromDate);
		query.setDate("campToDate",campToDate);
		return query.list();
	   }
	/*public Object[] getCampDates(Long campId){
		Query query = getSession().createQuery(" select model.bloodDonationCamp.fromDate,model.bloodDonationCamp.toDate " +
				" from BloodDonation model " +
				" where model.bloodDonationCamp.bloodDonationCampId=:campId ");
		query.setParameter("campId", campId);
		return (Object[]) query.uniqueResult();
	}*/
	
public List<Object[]> getBloodDonorCounts(Date campFromDate,Date campToDate ){
		
		Query query = getSession().createQuery(" select count(model.bloodDonorInfo.tdpCadre.tdpCadreId)," +
				" model.acceptanceStatusId " +
				" from BloodDonation model " +
				" where " +
				" date(model.bloodDonorInfo.insertedTime) between :campFromDate and :campToDate " +
				" group by model.acceptanceStatusId ");
		
		query.setDate("campFromDate",campFromDate);
		query.setDate("campToDate",campToDate);
		return query.list();
	}
	

public List<Object[]> getDistrictWiseBloodDonorCounts(Long campId){
	
	Query query = getSession().createQuery(" select model.bloodDonorInfo.tdpCadre.userAddress.district.districtId,model.bloodDonorInfo.tdpCadre.userAddress.district.districtName,count(distinct model.bloodDonorInfo.tdpCadre.tdpCadreId)" +
			" from BloodDonation model " +
			" where model.bloodDonationCamp.bloodDonationCampId = :campId" +
			" group by  model.bloodDonorInfo.tdpCadre.userAddress.district.districtId order by model.bloodDonorInfo.tdpCadre.userAddress.district.districtId ");
	
	query.setParameter("campId",campId);
	return query.list();
}

public List<Object[]> getThePrePopulateData(String searchType,Long statusId,List<Date> datesList){
	StringBuilder sb = new StringBuilder();
	
	sb.append("select model.bloodDonorInfo.tdpCadre.memberShipNo, " +
			" model.bloodDonorInfo.donorName, " +
			" model.bloodDonorInfo.mobileNo, " +
			" model.acceptanceStatus.acceptanceStatusId, " +
			" model.bagNo, " +
			" model.bloodBagTypeId, " +
			" model.bloodBagQuantity.bloodBagQuantityId, " +
			" model.quantity, " +
			" model.registrationNumber," +
			" model.remarks," +
			" model.bloodDonorInfo.tdpCadre.userAddress.district.districtId, " +
			" model.bloodDonorInfo.tdpCadre.userAddress.district.districtName " +
			" from BloodDonation model " +
			" where model.bloodDonorInfo.isDeleted ='N' " );
	
	if(searchType !=null && !searchType.trim().isEmpty() && searchType.trim() !=""){
		sb.append(" and (model.bloodDonorInfo.donorName like '%"+searchType+"%' or model.bloodDonorInfo.tdpCadre.memberShipNo=:searchType or model.bloodDonorInfo.mobileNo=:searchType) ");
	}
	if(statusId != null && statusId > 0l){
		sb.append(" and model.acceptanceStatusId = :statusId ");
	}
	if(datesList != null && datesList.size() > 0){
		sb.append(" and date(model.insertedTime) in (:datesList) ");
	}
	
	Query query = getSession().createQuery(sb.toString());
	if(searchType !=null && !searchType.trim().isEmpty() && searchType.trim() !=""){
		query.setParameter("searchType",searchType);
	}
	
	if(statusId != null && statusId > 0l){
		query.setParameter("statusId", statusId);
	}
	if(datesList != null && datesList.size() > 0){
		query.setParameterList("datesList", datesList);
	}
	return query.list();
}
}
