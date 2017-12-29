package com.itgrids.dao.impl;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmRepresenteeDAO;
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
	
	public List<Object[]> getAllDistrictsBySearchType(Date fromDate,Date toDate,List<Long> deptIds,List<Long> desigIds,String desigType){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.pmRepresentee.userAddress.district.districtId,model.pmRepresentee.userAddress.district.districtName ");
		sb.append( " from PmRepresenteeRefDetails model,PmSubWorkDetails model1 where model.pmRepresentee.isDeleted='N' " +
				" and model.isDeleted='N' and model.petition.petitionId = model1.petition.petitionId ");
		if(deptIds != null && deptIds.size()>0){
			sb.append(" and model1.pmDepartment.pmDepartmentId in (:deptIds) ");
		}
		if(desigIds != null && desigIds.size() >0 && desigType != null && desigType.equalsIgnoreCase("referral")){
			sb.append(" and model.pmRefCandidateDesignation.pmDesignation.pmDesignationId in (:desigIds) ");
		}else if(desigIds != null && desigIds.size() >0 && desigType != null && desigType.equalsIgnoreCase("representee")){
			sb.append(" and model.pmRefCandidateDesignation.pmDesignation.pmDesignationId in (:desigIds) ");
		}
		if(fromDate != null && toDate != null){
			sb.append(" and (date(model1.insertedTime) between :fromDate and :toDate ) ");
		}
		sb.append( "order by model.pmRepresentee.userAddress.district.districtName asc ");
		Query query =getSession().createQuery(sb.toString());
		if(deptIds != null && deptIds.size()>0){
			query.setParameterList("deptIds", deptIds);
		}
		if(desigIds != null && desigIds.size() >0){
			query.setParameterList("deptIds", desigIds);
		}
		if(fromDate != null && toDate != null){
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
		return query.list();
	}
	
	public List<Object[]> getAlConstituenciesBySearchType(Date fromDate,Date toDate,List<Long> districtIds,  List<Long> deptIds,List<Long> pmDesignationIds,String type){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.pmRepresentee.userAddress.constituency.constituencyId,model.pmRepresentee.userAddress.constituency.name ");
		sb.append( " from PmRepresenteeRefDetails model,PmSubWorkDetails model1 where model.pmRepresentee.isDeleted='N' " +
				" and model.isDeleted='N'and model1.isDeleted='N' and model.petition.petitionId = model1.petition.petitionId ");
		if(districtIds != null && districtIds.size() >0 ){
			sb.append("and model.pmRepresentee.userAddress.district.districtId in (:districtIds) ");
		}		
		if(deptIds != null && deptIds.size() >0 ){
			sb.append("and model1.pmDepartment.pmDepartmentId in (:deptIds)");
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
		sb.append(" order by model.pmRepresentee.userAddress.constituency.name asc ");
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
		return query.list();
	}
	public List<Object[]> getAllMandalsBySearchType(List<Long> constituencyIds,List<Long> deptIds,Date fromDate,Date toDate,List<Long> desigIds,String desigType){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct tehsil.tehsilId,tehsil.tehsilName ");
		sb.append( " , localElectionBody.localElectionBodyId,localElectionBody.name," +
				"localElectionBody.electionTypeId " +
				"from PmRepresenteeRefDetails model left join model.pmRepresentee.userAddress.localElectionBody localElectionBody " +
				"left join model.pmRepresentee.userAddress.tehsil tehsil ,PmSubWorkDetails model1 where model.isDeleted='N'  and model.pmRepresentee.isDeleted='N' and  model.petition.petitionId = model1.petition.petitionId  ");
		if(constituencyIds != null && constituencyIds.size() >0 ){
			sb.append("and model.pmRepresentee.userAddress.constituencyId in (:constituencyIds) ");
		}
		 
		if(deptIds != null && deptIds.size() >0){
					sb.append(" and model1.pmDepartment.pmDepartmentId in (:deptIds) ");
				}
		if(fromDate != null && toDate != null){
			sb.append(" and date(model1.insertedTime) between :fromDate and :toDate "); 
		}
		if(desigIds != null && desigIds.size() >0 && desigType != null && desigType.equalsIgnoreCase("referral")){
			sb.append(" and model.pmRefCandidateDesignation.pmDesignation.pmDesignationId in (:desigIds) ");
		}else if(desigIds != null && desigIds.size() >0 && desigType != null && desigType.equalsIgnoreCase("representee")){
			sb.append(" and model.pmRefCandidateDesignation.pmDesignation.pmDesignationId in (:desigIds) ");
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
			query.setParameterList("deptIds", desigIds);
		}
		return query.list();
	}
}

