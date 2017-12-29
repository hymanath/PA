package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmRefCandidateDesignationDAO;
import com.itgrids.model.PmRefCandidateDesignation;
@Repository
public class PmRefCandidateDesignationDAO extends GenericDaoHibernate<PmRefCandidateDesignation, Long> implements IPmRefCandidateDesignationDAO {
	@Autowired
	SessionFactory sessionFactory;
	public PmRefCandidateDesignationDAO() {
		super(PmRefCandidateDesignation.class);
		
	}

	public List<PmRefCandidateDesignation> getPmRepresenteeDesignationByPmRefCandidateId(Long pmRefCandidateId){
		StringBuilder str = new StringBuilder();
		str.append(" select model from PmRefCandidateDesignation model where model.pmRefCandidateId =:pmRefCandidateId ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("pmRefCandidateId", pmRefCandidateId);
		return query.list();
	}
	
	
	public List<Object[]> getCandidatseDetailsByDesignationAndLocation(Long designationId,Long locationLevelId,Long locationValue){
		StringBuilder sb = new StringBuilder();
		sb.append("select model.pmRefCandidateId,model.pmRefCandidate.name,model.pmDesignation.designation ");//0,1,2
		sb.append(",state.stateId,state.stateName ");//3,4
		sb.append(",district.districtId,district.districtName ");//5,6
		sb.append(",constituency.constituencyId,constituency.name ");//7,8
		sb.append(",tehsil.tehsilId,tehsil.tehsilName ");//9,10
		sb.append(",panchayat.panchayatId,panchayat.panchayatName ");//11,12
		sb.append(",model.pmRefCandidate.mobileNo,model.pmRefCandidate.email,model.pmDesignation.pmDesignationId ");//13,14,15
		sb.append(",parliament.constituencyId,parliament.name ,model.pmRefCandidate.imagePath,model.pmRefCandidate.partyName ");//16,17,18,19
		
		sb.append(",natState.stateId,natState.stateName ");//20,21
		sb.append(",natDistrict.districtId,natDistrict.districtName ");//22,23
		sb.append(",natConstituency.constituencyId,natConstituency.name ");//24,25
		sb.append(",natTehsil.tehsilId,natTehsil.tehsilName ");//26,27
		sb.append(",natPanchayat.panchayatId,natPanchayat.panchayatName ");//28,29
		sb.append(",natParliament.constituencyId,natParliament.name ");//30,31
		sb.append(",localbody.localElectionBodyId, localbody.name ,''");//32,33,34
		sb.append(",natLocalbody.localElectionBodyId, natLocalbody.name ,'' ");//35,36,37
		
		sb.append(" from PmRefCandidateDesignation model ");
		sb.append(" left join model.pmRefCandidate pmRefCandidate ");
		sb.append("left join pmRefCandidate.address locationAddress ");
		sb.append("left join locationAddress.state state ");
		sb.append("left join locationAddress.district district ");
		sb.append("left join locationAddress.constituency constituency ");
		sb.append("left join locationAddress.parliament parliament ");
		sb.append("left join locationAddress.tehsil tehsil ");
		sb.append("left join locationAddress.panchayat panchayat " );
		sb.append("left join locationAddress.localElectionBody localbody" );
		//sb.append("left join localbody.electionType electionType " );
		
		sb.append(" left join pmRefCandidate.nativAddress nativAddress ");
		sb.append(" left join nativAddress.state natState ");
		sb.append(" left join nativAddress.district natDistrict ");
		sb.append(" left join nativAddress.constituency natConstituency ");
		sb.append(" left join nativAddress.parliament natParliament ");
		sb.append(" left join nativAddress.tehsil natTehsil ");
		sb.append(" left join nativAddress.panchayat natPanchayat ");
		sb.append(" left join nativAddress.localElectionBody natLocalbody " );
		//sb.append(" left join natLocalbody.electionType natElectionType " );
		
		sb.append(" where model.isDeleted ='N' ");
		if(designationId != null && designationId.longValue() > 0L){
			sb.append(" and model.pmDesignation.pmDesignationId =:designationId ");
		}
		if(locationLevelId != null && locationLevelId.longValue() == 3L && locationValue != null && locationValue.longValue() > 0){
			sb.append(" and model.pmRefCandidate.address.districtId = :locationValue ");
		}else if(locationLevelId != null && locationLevelId.longValue() == 4L && locationValue != null && locationValue.longValue() > 0){
			sb.append(" and model.pmRefCandidate.address.constituencyId = :locationValue ");
		}else if(locationLevelId != null && locationLevelId.longValue() == 10L && locationValue != null && locationValue.longValue() > 0){
			sb.append(" and model.pmRefCandidate.address.parliamentId = :locationValue ");
		}
		Query query = getSession().createQuery(sb.toString());
		if(designationId != null && designationId.longValue() > 0L){
			query.setParameter("designationId", designationId);
		}
		if(locationLevelId != null && locationLevelId.longValue() == 3L && locationValue != null && locationValue.longValue() > 0 ){
			query.setParameter("locationValue", locationValue);
		}else if(locationLevelId != null && ( locationLevelId.longValue() == 4L || locationLevelId.longValue() == 10L) && locationValue != null && locationValue.longValue() > 0 ){
			query.setParameter("locationValue", locationValue);
		}
	return query.list();
	}
	
	public List<Object[]> getAllDistrictsByReferalAndDesignation(List<Long> deptIds){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.pmRefCandidateDesignation.pmRefCandidate.address.district.districtId ");
		sb.append( ",model.pmRefCandidateDesignation.pmRefCandidate.address.district.districtName from PmRepresenteeRefDetails model,PmSubWorkDetails model1 where model.pmRefCandidateDesignation.isDeleted='N' " +
				"and model.isDeleted='N'  and model.pmRefCandidateDesignation.isDeleted='N' and model.petition.petitionId = model1.petition.petitionId ");
		if(deptIds != null && deptIds.size()>0){
			sb.append(" and model1.pmDepartment.pmDepartmentId in (:deptIds) ");
		}
		sb.append(" order by model.pmRefCandidateDesignation.pmRefCandidate.address.district.districtName asc");
		Query query =getSession().createQuery(sb.toString());
		if(deptIds != null && deptIds.size()>0){
			query.setParameterList("deptIds", deptIds);
		}
		return query.list();
	}
	public List<Object[]> getAlConstituenciesByReferalAndDesignationBydistrict(List<Long> districtIds,List<Long> deptIds){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.pmRefCandidateDesignation.pmRefCandidate.address.constituency.constituencyId ");
		sb.append( ",model.pmRefCandidate.address.constituency.name from PmRepresenteeRefDetails model,PmSubWorkDetails model1 where model.pmRefCandidateDesignation.isDeleted='N' " +
				"and model.isDeleted='N'and model1.isDeleted='N' and model.petition.petitionId = model1.petition.petitionId ");
		if(districtIds != null && districtIds.size() > 0){
			sb.append("and model.pmRefCandidateDesignation.pmRefCandidate.address.district.districtId  in (:districtIds) ");
		}
		if(deptIds != null && deptIds.size() > 0){
			sb.append("and model1.pmDepartment.pmDepartmentId in (:deptIds) ");
		}
		sb.append(" order by model.pmRefCandidateDesignation.pmRefCandidate.address.constituency.name asc ");
		Query query =getSession().createQuery(sb.toString());
		if(deptIds != null && deptIds.size() > 0){
			query.setParameterList("deptIds", deptIds);
		}
		if(districtIds != null && districtIds.size() >0L){
			query.setParameterList("districtIds", districtIds);
		}
		return query.list();
	}
	
	public List<Object[]> getAllMandalsByReferalAndDesignationBydistrict(List<Long> constituencyIds,List<Long> deptIds){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct tehsil.tehsilId ");
		sb.append( ",tehsil.tehsilName, localElectionBody.localElectionBodyId,localElectionBody.name," +
				"localElectionBody.electionTypeId from PmRepresenteeRefDetails model left join model.pmRefCandidateDesignation.pmRefCandidate.address.localElectionBody localElectionBody" +
				" left join model.pmRefCandidateDesignation.pmRefCandidate.address.tehsil tehsil ,PmSubWorkDetails model1 "
				+ "where model.isDeleted='N'  and model.pmRefCandidateDesignation.isDeleted='N' and  model.petition.petitionId = model1.petition.petitionId ");
		if(constituencyIds != null && constituencyIds.size() > 0L){
			sb.append("and model.pmRefCandidateDesignation.pmRefCandidate.address.constituencyId in (:constituencyIds) ");
		}
		if(deptIds != null && deptIds.size() >0){
			sb.append(" and model1.pmDepartment.pmDepartmentId in (:deptIds) ");
		}
		sb.append(" order by tehsil.tehsilName asc, localElectionBody.name asc");
		Query query =getSession().createQuery(sb.toString());
		if(constituencyIds != null && constituencyIds.size() > 0L){
			query.setParameterList("constituencyIds", constituencyIds);
		}
		if(deptIds != null && deptIds.size() >0){
			query.setParameterList("deptIds", deptIds);
		}
		return query.list();
	}
	
	public List<Object[]> getDesignationsByReferlDesigtion(){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.pmDesignation.pmDesignationId,model.pmDesignation.designation "
				+ "from PmRefCandidateDesignation model where model.pmDesignation.isDeleted ='N' order by "
				+ " model.pmDesignation.designation asc " );
		Query query =getSession().createQuery(sb.toString());
		return query.list();
	}
}
