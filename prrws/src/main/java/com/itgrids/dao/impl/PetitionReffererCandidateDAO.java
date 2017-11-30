package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPetitionReffererCandidateDAO;
import com.itgrids.model.PetitionReffererCandidate;

@Repository
public class PetitionReffererCandidateDAO extends GenericDaoHibernate<PetitionReffererCandidate, Long> implements IPetitionReffererCandidateDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	public PetitionReffererCandidateDAO(){
		super(PetitionReffererCandidate.class);
	}
	
	
	
	public List<Object[]> getCandidatseDetailsByDesignationAndLocation(Long designationId,Long locationLevelId,Long locationValue){
		StringBuilder sb = new StringBuilder();
		sb.append("select model.petitionReffererCandidateId,model.name,model.petitionDesignation.designationName");//
		sb.append(",state.stateId,state.stateName ");
		sb.append(",district.districtId,district.districtName ");
		sb.append(",constituency.constituencyId,constituency.name ");
		sb.append(",tehsil.tehsilId,tehsil.tehsilName ");
		sb.append(",panchayat.panchayatId,panchayat.panchayatName ");
		sb.append(",model.mobileNo,model.emailId,model.petitionDesignation.petitionDesignationId ");
		sb.append("from PetitionReffererCandidate model ");
		sb.append("left join model.locationAddress locationAddress ");
		sb.append("left join locationAddress.state state ");
		sb.append("left join locationAddress.district district ");
		sb.append("left join locationAddress.constituency constituency ");
		sb.append("left join locationAddress.tehsil tehsil ");
		sb.append("left join locationAddress.panchayat panchayat ");
		sb.append("where model.isDeleted ='N' ");
		if(designationId != null && designationId.longValue() > 0L){
			sb.append("and model.petitionDesignation.petitionDesignationId =:designationId ");
		}
		if(locationLevelId != null && locationLevelId.longValue() == 3L && locationValue != null && locationValue.longValue() > 0){
			sb.append("and model.locationAddress.districtId = :locationValue ");
		}else if(locationLevelId != null && locationLevelId.longValue() == 4L && locationValue != null && locationValue.longValue() > 0){
			sb.append("and model.locationAddress.constituencyId = :locationValue ");
		}
		Query query = getSession().createQuery(sb.toString());
		if(designationId != null && designationId.longValue() > 0L){
			query.setParameter("designationId", designationId);
		}
		if(locationLevelId != null && locationLevelId.longValue() == 3L && locationValue != null && locationValue.longValue() > 0 ){
			query.setParameter("locationValue", locationValue);
		}else if(locationLevelId != null && locationLevelId.longValue() == 4L && locationValue != null && locationValue.longValue() > 0 ){
			query.setParameter("locationValue", locationValue);
		}
	return query.list();
	}
}
