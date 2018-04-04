package com.itgrids.dao.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmRepresenteeDAO;
import com.itgrids.dto.InputVO;
import com.itgrids.model.PmRepresentee;

@Repository
public class PmRepresenteeDAO extends GenericDaoHibernate<PmRepresentee, Long> implements IPmRepresenteeDAO {
	@Autowired
	SessionFactory sessionFactory;
	public PmRepresenteeDAO() {
		super(PmRepresentee.class);
		
	}

	public List<Long> getExistingPetitionRepresenteeDetailsById(String voterCardNo,String adharCardNo){
		
		if(voterCardNo != null && !voterCardNo.toString().trim().isEmpty()){
			StringBuilder str = new StringBuilder();
			str.append(" select distinct model.pmRepresenteeId from PmRepresentee model where model.isDeleted ='N' ");
			if(voterCardNo != null && voterCardNo.trim().length()>0){
				str.append(" and ( model.voterCardNo = '"+voterCardNo+"' ");
				if(adharCardNo != null && adharCardNo.trim().length()>0)
					str.append(" OR model.adharCardNo = '"+adharCardNo+"'");
			str.append(" )");
			}
			else if(adharCardNo != null && adharCardNo.trim().length()>0)
				str.append(" and  model.adharCardNo = '"+adharCardNo+"' ");
			
			Query query = getSession().createQuery(str.toString());
			return query.list();
		}
		return null;
	}

	public List<Long> getExistingPetitionRepresenteeDetailsByRefId(Long refCandidateId){
		StringBuilder str = new StringBuilder();
		str.append(" select distinct model.pmRepresenteeId from PmRepresentee model where model.isDeleted ='N' ");
		if(refCandidateId != null && refCandidateId.longValue()>0L)
			str.append(" and model.pmRefCandidateId = :refCandidateId ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("refCandidateId", refCandidateId);
		return query.list();
	}
	
	public List<Object[]> getAllDistrictsBySearchType(InputVO inputVO, Date fromDate,Date toDate,List<Long> deptIds,List<Long> desigIds,String desigType,List<Long> statIds,Set<Long> petitionIdsLst){
		StringBuilder sb = new StringBuilder();
		if(inputVO != null){
			sb.append("select distinct district.districtId,district.districtName ");
		}else{
			sb.append("select distinct district.districtId,district.districtName ");
		}
		if(inputVO != null){
			sb.append(" from PmRepresenteeRefDetails model ,PmSubWorkDetails model1 ");
			sb.append( " left join model1.locationAddress userAddress " +
					" left join userAddress.district district " +
					" left join userAddress.constituency constituency " +
					" left join userAddress.tehsil tehsil " +
					" left join userAddress.localElectionBody localElectionBody " );
		}else{
			sb.append(" from PmSubWorkDetails model1 ,PmRepresenteeRefDetails model ");
			sb.append( " left join model.pmRepresentee pmRepresentee " +
					" left join pmRepresentee.userAddress userAddress " +
					" left join userAddress.district district " +
					" left join userAddress.constituency constituency " +
					" left join userAddress.tehsil tehsil " +
					" left join userAddress.localElectionBody localElectionBody " );
		}
		sb.append( " where  model.isDeleted='N' and model.petition.petitionId = model1.petition.petitionId and model1.petition.isDeleted='N' ");
		if(deptIds != null && deptIds.size()>0){
			sb.append(" and model1.pmDepartment.pmDepartmentId in (:deptIds) ");
		}
		if(statIds != null && statIds.size() >0){
			sb.append(" and model1.pmStatus.pmStatusId in (:statIds) ");
		}	
		
		if(desigIds != null && desigIds.size() >0 && desigType != null && desigType.equalsIgnoreCase("referral")){
			sb.append(" and model.pmRefCandidateDesignation.pmDesignation.pmDesignationId in (:desigIds) ");
		}else if(desigIds != null && desigIds.size() >0 && desigType != null && desigType.equalsIgnoreCase("representee")){
			sb.append(" and model.pmRefCandidateDesignation.pmDesignation.pmDesignationId in (:desigIds) ");
		}else if(inputVO != null && inputVO.getFilterValue() != null && inputVO.getFilterType().trim().equalsIgnoreCase("name")){
			sb.append(" and model.pmRepresentee.name like '%"+inputVO.getFilterValue()+"%' ");
		}else if(inputVO != null && inputVO.getFilterValue() != null && inputVO.getFilterType().trim().equalsIgnoreCase("mail")){
			sb.append(" and model.pmRepresentee.email like '%"+inputVO.getFilterValue()+"%' ");
		}else if(inputVO != null && inputVO.getFilterValue() != null && inputVO.getFilterType().trim().equalsIgnoreCase("mobile")){
			sb.append(" and model.pmRepresentee.mobileNo like '%"+inputVO.getFilterValue()+"%' ");
		}
		
		if(petitionIdsLst != null && petitionIdsLst.size() >0){
			sb.append(" and model.petition.petitionId in(:petitionIdsLst) ");
		}
		if(fromDate != null && toDate != null){
			sb.append(" and (date(model1.insertedTime) between :fromDate and :toDate ) ");
		}
		sb.append( "order by district.districtName asc ");
		Query query =getSession().createQuery(sb.toString());
		if(deptIds != null && deptIds.size()>0){
			query.setParameterList("deptIds", deptIds);
		}
		if(desigIds != null && desigIds.size() >0){
			query.setParameterList("deptIds", desigIds);
		}
		if(statIds != null && statIds.size() >0){
			query.setParameterList("statIds", statIds);
		}
		if(petitionIdsLst != null && petitionIdsLst.size() >0){
			query.setParameterList("petitionIdsLst", petitionIdsLst);
		}
		if(fromDate != null && toDate != null){
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
		return query.list();
	}
	 
	public List<Object[]> getAlConstituenciesBySearchType(InputVO inputVO,Date fromDate,Date toDate,List<Long> districtIds,  List<Long> deptIds,List<Long> pmDesignationIds,String type,List<Long> statIds,Set<Long> petitionIdsLst){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct constituency.constituencyId,constituency.name ");
		
		if(inputVO != null){
			sb.append(" from PmRepresenteeRefDetails model ,PmSubWorkDetails model1 ");
			sb.append( " left join model1.locationAddress userAddress " +
					" left join userAddress.district district " +
					" left join userAddress.constituency constituency " +
					" left join userAddress.tehsil tehsil " +
					" left join userAddress.localElectionBody localElectionBody " );
		}else{
			sb.append(" from PmSubWorkDetails model1 ,PmRepresenteeRefDetails model ");
			sb.append( " left join model.pmRepresentee pmRepresentee " +
					" left join pmRepresentee.userAddress userAddress " +
					" left join userAddress.district district " +
					" left join userAddress.constituency constituency " +
					" left join userAddress.tehsil tehsil " +
					" left join userAddress.localElectionBody localElectionBody " );
		}
		
		sb.append( " where  model.isDeleted='N'and model1.isDeleted='N' and model.petition.petitionId = model1.petition.petitionId" +
				" and model1.petition.isDeleted='N'  ");
		if(districtIds != null && districtIds.size() >0 ){
			sb.append("and district.districtId in (:districtIds) ");
		}		
		if(deptIds != null && deptIds.size() >0 ){
			sb.append("and model1.pmDepartment.pmDepartmentId in (:deptIds)");
		}
		if(statIds != null && statIds.size() >0){
			sb.append(" and model1.pmStatus.pmStatusId in (:statIds) ");
		}	
		if(petitionIdsLst != null && petitionIdsLst.size() >0){
			sb.append(" and model.petition.petitionId in (:petitionIdsLst) ");
		}
		if(fromDate != null && toDate != null){
			sb.append(" and (date(model1.insertedTime) between :fromDate and :toDate ) ");
		}
		if(type != null  && type.equalsIgnoreCase("referral") && pmDesignationIds !=null && pmDesignationIds.size()>0){
			sb.append(" and model.pmRefCandidateDesignation.pmDesignation.pmDesignationId in(:pmDesignationIds) ");
		}
	   else if (type != null  && type.equalsIgnoreCase("representee") && pmDesignationIds !=null && pmDesignationIds.size()>0){
		sb.append(" and model.pmRepresenteeDesignation.pmDesignation.pmDesignationId in(:pmDesignationIds) ");
	    }
	   	else if(inputVO != null && inputVO.getFilterValue() != null && inputVO.getFilterType().trim().equalsIgnoreCase("name")){
			sb.append(" and model.pmRepresentee.name like '%"+inputVO.getFilterValue()+"%' ");
		}else if(inputVO != null && inputVO.getFilterValue() != null && inputVO.getFilterType().trim().equalsIgnoreCase("mail")){
			sb.append(" and model.pmRepresentee.email like '%"+inputVO.getFilterValue()+"%' ");
		}else if(inputVO != null && inputVO.getFilterValue() != null && inputVO.getFilterType().trim().equalsIgnoreCase("mobile")){
			sb.append(" and model.pmRepresentee.mobileNo like '%"+inputVO.getFilterValue()+"%' ");
		}
		sb.append(" order by constituency.name asc ");
		Query query =getSession().createQuery(sb.toString());
		if(deptIds != null && deptIds.size() >0 ){
			query.setParameterList("deptIds", deptIds);
		}
		if(districtIds != null && districtIds.size() >0L){
			query.setParameterList("districtIds", districtIds);
		}
		if (fromDate != null && toDate != null) {
			query.setDate("fromDate", fromDate);
			query.setDate("toDate", toDate);
		}
		if(pmDesignationIds != null && pmDesignationIds.size() >0L){
			query.setParameterList("pmDesignationIds", pmDesignationIds);
		}
		if(statIds != null && statIds.size() >0){
			query.setParameterList("statIds", statIds);
		}
		if(petitionIdsLst != null && petitionIdsLst.size() >0){
			query.setParameterList("petitionIdsLst",petitionIdsLst);
		}
		return query.list();
	}
	public List<Object[]> getAllMandalsBySearchType(InputVO inputVO,List<Long> constituencyIds,List<Long> deptIds,Date fromDate,Date toDate,List<Long> desigIds,String desigType,List<Long> statIds,Set<Long> petitionIdsList){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct tehsil.tehsilId,tehsil.tehsilName ");
		sb.append( " , localElectionBody.localElectionBodyId,localElectionBody.name," +
				"localElectionBody.electionTypeId ");
		
		if(inputVO != null){
			sb.append(" from PmRepresenteeRefDetails model ,PmSubWorkDetails model1 ");
			sb.append( " left join model1.locationAddress userAddress " +
					" left join userAddress.district district " +
					" left join userAddress.constituency constituency " +
					" left join userAddress.tehsil tehsil " +
					" left join userAddress.localElectionBody localElectionBody " );
		}else{
			sb.append(" from PmSubWorkDetails model1 ,PmRepresenteeRefDetails model ");
			sb.append( " left join model.pmRepresentee pmRepresentee " +
					" left join pmRepresentee.userAddress userAddress " +
					" left join userAddress.district district " +
					" left join userAddress.constituency constituency " +
					" left join userAddress.tehsil tehsil " +
					" left join userAddress.localElectionBody localElectionBody " );
		}
		
		sb.append( " where model.isDeleted='N'  and " +
				"model.pmRepresentee.isDeleted='N' and  model.petition.petitionId = model1.petition.petitionId and model1.petition.isDeleted='N'  ");
		
		if(constituencyIds != null && constituencyIds.size() >0 ){
			sb.append("and userAddress.constituencyId in (:constituencyIds) ");
		}
		 
		if(deptIds != null && deptIds.size() >0){
					sb.append(" and model1.pmDepartment.pmDepartmentId in (:deptIds) ");
		}
		if(statIds != null && statIds.size() >0){
			sb.append(" and model1.pmStatus.pmStatusId in (:statIds) ");
		 }
		
		if(fromDate != null && toDate != null){
			sb.append(" and date(model1.insertedTime) between :fromDate and :toDate "); 
		}
		if(desigIds != null && desigIds.size() >0 && desigType != null && desigType.equalsIgnoreCase("referral")){
			sb.append(" and model.pmRefCandidateDesignation.pmDesignation.pmDesignationId in (:desigIds) ");
		}else if(desigIds != null && desigIds.size() >0 && desigType != null && desigType.equalsIgnoreCase("representee")){
			sb.append(" and model.pmRefCandidateDesignation.pmDesignation.pmDesignationId in (:desigIds) ");
		}
		
		if(petitionIdsList != null && petitionIdsList.size() >0){
			sb.append(" and model1.petition.petitionId in (:petitionIdsList) ");
		}else if(inputVO != null && inputVO.getFilterValue() != null && inputVO.getFilterType().trim().equalsIgnoreCase("name")){
			sb.append(" and model.pmRepresentee.name like '%"+inputVO.getFilterValue()+"%' ");
		}else if(inputVO != null && inputVO.getFilterValue() != null && inputVO.getFilterType().trim().equalsIgnoreCase("mail")){
			sb.append(" and model.pmRepresentee.email like '%"+inputVO.getFilterValue()+"%' ");
		}else if(inputVO != null && inputVO.getFilterValue() != null && inputVO.getFilterType().trim().equalsIgnoreCase("mobile")){
			sb.append(" and model.pmRepresentee.mobileNo like '%"+inputVO.getFilterValue()+"%' ");
		}
		sb.append( "order by tehsil.tehsilName asc,localElectionBody.name asc ");
		Query query =getSession().createQuery(sb.toString());
		if(constituencyIds != null && constituencyIds.size() >0 ){
			query.setParameterList("constituencyIds", constituencyIds);
		}
		if(deptIds != null && deptIds.size() >0){
			query.setParameterList("deptIds", deptIds);
		}
		if(fromDate != null && toDate != null){
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
		if(desigIds != null && desigIds.size() >0){
			query.setParameterList("desigIds", desigIds);
		}
		if(statIds != null && statIds.size() >0){
			query.setParameterList("statIds", statIds);
		}
		if(petitionIdsList != null && petitionIdsList.size() >0){
			query.setParameterList("petitionIdsList", petitionIdsList);
		}
		return query.list();
	}
}

