package com.itgrids.dao.impl;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmRepresenteeDesignationDAO;
import com.itgrids.model.PmRepresenteeDesignation;

@Repository
public class PmRepresenteeDesignationDAO extends GenericDaoHibernate<PmRepresenteeDesignation, Long> implements IPmRepresenteeDesignationDAO {

	@Autowired
	SessionFactory sessionFactory;
	public PmRepresenteeDesignationDAO() {
		super(PmRepresenteeDesignation.class);
		
	}

	public List<PmRepresenteeDesignation> getPmRepresenteeDesignationByRepresenteeId(Long representeeId){
		StringBuilder str = new StringBuilder();
		str.append(" select model from PmRepresenteeDesignation model where model.pmRepresenteeId =:representeeId and model.isActive ='Y' and model.isDeleted='N'  ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("representeeId", representeeId);
		return query.list();
	}
	
	public int inActiveExistingDesignationsByIds(List<Long> pmRepresenteeDesignationIdsList){
		if(pmRepresenteeDesignationIdsList != null && pmRepresenteeDesignationIdsList.size()>0){
			StringBuilder str = new StringBuilder();
			str.append(" update PmRepresenteeDesignation model set model.isActive='N',model.isDeleted='Y' where model.pmRepresenteeDesignationId in (:pmRepresenteeDesignationIdsList) and model.isActive ='Y' " +
					" and  model.isDeleted='N' ");
			Query query = getSession().createQuery(str.toString());
			query.setParameterList("pmRepresenteeDesignationIdsList", pmRepresenteeDesignationIdsList);
			return query.executeUpdate();
		}
		return 0;
	}
	
	public List<Object[]> getAllDistrictsByRepresenteeDesignationWise(Date fromDate,Date toDate,List<Long> deptIds,List<Long> desigIds,String desigType,List<Long> statIds){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.pmRepresenteeDesignation.pmRepresentee.userAddress.district.districtId,model.pmRepresenteeDesignation.pmRepresentee.userAddress.district.districtName ");
		sb.append( " from PmRepresenteeRefDetails model,PmSubWorkDetails model1 " +
				" where model.isDeleted='N' and model1.isDeleted='N' and model.petition.petitionId = model1.petition.petitionId  " +
				"and model1.petition.isDeleted='N' ");
		if(deptIds != null && deptIds.size()>0){
			sb.append(" and model1.pmDepartment.pmDepartmentId in (:deptIds) ");
		}
		if(fromDate != null && toDate != null){
			sb.append(" and (date(model1.insertedTime) between :fromDate and :toDate ) ");
			}
		if(statIds != null && statIds.size() >0){
			sb.append(" and model1.pmStatus.pmStatusId in (:statIds) ");
		}	
		if(desigIds != null && desigIds.size() >0 && desigType != null && desigType.equalsIgnoreCase("referral")){
			sb.append(" and model.pmRefCandidateDesignation.pmDesignation.pmDesignationId in (:desigIds) ");
		}else if(desigIds != null && desigIds.size() >0 && desigType != null && desigType.equalsIgnoreCase("representee")){
			sb.append(" and model.pmRefCandidateDesignation.pmDesignation.pmDesignationId in (:desigIds) ");
		}
		sb.append( "  and model.pmRepresenteeDesignation.isDeleted='N' order by model.pmRepresenteeDesignation.pmRepresentee.userAddress.district.districtName asc ");
		Query query =getSession().createQuery(sb.toString());
		if(deptIds != null && deptIds.size()>0){
			query.setParameterList("deptIds", deptIds);
		}
		if(desigIds != null && desigIds.size() >0){
			query.setParameterList("desigIds", desigIds);
		}
		if(statIds != null && statIds.size() >0){
			query.setParameterList("statIds", statIds);
		}
		if(fromDate != null && toDate != null){
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
		return query.list();
	}
	public List<Object[]> getDesignationsByRepresenteeDesigtion(List<Long> deptIds,Date fromDate ,Date toDate,Long desigId,List<Long> statusIds){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.pmRepresenteeDesignation.pmDesignation.pmDesignationId,model.pmRepresenteeDesignation.pmDesignation.designation "
				+ "from PmRepresenteeRefDetails model,PmSubWorkDetails model1 " +
				"where model.isDeleted='N' and model1.isDeleted='N' and model.petition.petitionId = model1.petition.petitionId and " +
				"model1.petition.isDeleted='N' ");
		if(deptIds != null && deptIds.size()>0){
			sb.append(" and model1.pmDepartment.pmDepartmentId in (:deptIds) ");
		}
		if(fromDate != null && toDate != null){
			sb.append(" and date(model1.insertedTime) between :fromDate and :toDate "); 
		}
		if(statusIds != null && statusIds.size() >0){
			sb.append(" and model1.pmStatus.pmStatusId in (:statusIds) ");
		}
		if(desigId != null && desigId.longValue() >0l){
			sb.append(" and  distinct model.pmRepresenteeDesignation.pmDesignation.pmDesignationId =:desigId ");
		}
		sb.append( " order by model.pmRepresenteeDesignation.pmDesignation.designation asc " );
		Query query =getSession().createQuery(sb.toString());
		if(deptIds != null && deptIds.size()>0){
			query.setParameterList("deptIds", deptIds);
		}
		if(statusIds != null && statusIds.size() >0){
			query.setParameterList("statusIds", statusIds);
		}
		if(fromDate != null && toDate != null){
			query.setParameter("fromDate", fromDate);
			query.setParameter("toDate", toDate);
		}
		if(desigId != null && desigId.longValue() >0l){
			query.setParameter("desigId", desigId);
		}
		return query.list();
	}
	
	public List<Object[]> getAllConstituenciesByRepresenteeDesignationWise(Date fromDate,Date toDate,List<Long> districtIds,  List<Long> deptIds,List<Long> pmDesignationIds,String type,List<Long> statIds){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.pmRepresenteeDesignation.pmRepresentee.userAddress.constituency.constituencyId ");
		sb.append( ",model.pmRepresenteeDesignation.pmRepresentee.userAddress.constituency.name from PmRepresenteeRefDetails model,PmSubWorkDetails model1 " +
				" where model.isDeleted='N' and model1.isDeleted='N' and model.petition.petitionId = model1.petition.petitionId " +
				"and model1.petition.isDeleted='N' ");
		if(districtIds != null && districtIds.size() > 0L){
			sb.append("and model.pmRepresenteeDesignation.pmRepresentee.userAddress.district.districtId in (:districtIds) ");
		}		
		if(deptIds != null && deptIds.size() > 0L){
			sb.append("and model1.pmDepartment.pmDepartmentId in (:deptIds) ");
		}
     	if(statIds != null && statIds.size() >0){
				sb.append(" and model1.pmStatus.pmStatusId in (:statIds) ");
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
		sb.append(" and model.pmRepresenteeDesignation.isDeleted='N' order by model.pmRepresenteeDesignation.pmRepresentee.userAddress.constituency.name asc ");
		Query query =getSession().createQuery(sb.toString());
		if(deptIds != null && deptIds.size() > 0L){
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
	
	public List<Object[]> getAllMandalsByRepresenteeDesignationAndconstincy(List<Long> constituencyIds,List<Long> deptIds,Date fromDate,Date toDate,List<Long> desigIds,String desigType,List<Long> statIds){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct tehsil.tehsilId ");
		sb.append( ",tehsil.tehsilName, localElectionBody.localElectionBodyId,localElectionBody.name," +
				"localElectionBody.electionTypeId from PmRepresenteeRefDetails model left join model.pmRepresenteeDesignation.pmRepresentee.userAddress.localElectionBody localElectionBody" +
				" left join model.pmRepresenteeDesignation.pmRepresentee.userAddress.tehsil  tehsil ,PmSubWorkDetails model1  "
				+ "where model.pmRepresenteeDesignation.pmRepresentee.isDeleted='N' and  model.isDeleted='N' and " +
				"model1.isDeleted='N' and model.petition.petitionId = model1.petition.petitionId and model1.petition.isDeleted='N' ");
		if(constituencyIds != null && constituencyIds.size() > 0L){
			sb.append("and model.pmRepresenteeDesignation.pmRepresentee.userAddress.constituencyId  in (:constituencyIds) ");
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
		sb.append(" order by tehsil.tehsilName asc,localElectionBody.name asc ");
		Query query =getSession().createQuery(sb.toString());
		if(constituencyIds != null && constituencyIds.size() > 0L){
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
		
		return query.list();
	}
}
