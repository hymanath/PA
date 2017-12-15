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
		sb.append(",parliament.constituencyId,parliament.name ,model.pmRefCandidate.imagePath,pmRefCandidate.partyName ");//16,17,18,19
		sb.append("from PmRefCandidateDesignation model ");
		sb.append("left join model.pmRefCandidate.address locationAddress ");
		sb.append("left join locationAddress.state state ");
		sb.append("left join locationAddress.district district ");
		sb.append("left join locationAddress.constituency constituency ");
		sb.append("left join locationAddress.parliament parliament ");
		sb.append("left join locationAddress.tehsil tehsil ");
		sb.append("left join locationAddress.panchayat panchayat ");
		sb.append("where model.isDeleted ='N' ");
		if(designationId != null && designationId.longValue() > 0L){
			sb.append("and model.pmDesignation.pmDesignationId =:designationId ");
		}
		if(locationLevelId != null && locationLevelId.longValue() == 3L && locationValue != null && locationValue.longValue() > 0){
			sb.append("and model.pmRefCandidate.address.districtId = :locationValue ");
		}else if(locationLevelId != null && locationLevelId.longValue() == 4L && locationValue != null && locationValue.longValue() > 0){
			sb.append("and model.pmRefCandidate.address.constituencyId = :locationValue ");
		}else if(locationLevelId != null && locationLevelId.longValue() == 10L && locationValue != null && locationValue.longValue() > 0){
			sb.append("and model.pmRefCandidate.address.parliamentId = :locationValue ");
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
	
	public List<Object[]> getAllDistrictsByReferalAndDesignation(){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.pmRefCandidate.address.district.districtId ");
		sb.append( ",model.pmRefCandidate.address.district.districtName from PmRefCandidateDesignation model where model.isDeleted='N' ");
		sb.append(" order by model.pmRefCandidate.address.district.districtName asc");
		Query query =getSession().createQuery(sb.toString());
		return query.list();
	}
	public List<Object[]> getAlConstituenciesByReferalAndDesignationBydistrict(Long districtId){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.pmRefCandidate.address.constituency.constituencyId ");
		sb.append( ",model.pmRefCandidate.address.constituency.name from PmRefCandidateDesignation model "
				+ "where model.isDeleted='N' ");
		if(districtId != null && districtId.longValue() > 0L){
			sb.append("and model.pmRefCandidate.address.districtId =:districtId ");
		}
		sb.append(" order by model.pmRefCandidate.address.constituency.name asc ");
		Query query =getSession().createQuery(sb.toString());
		if(districtId != null && districtId.longValue() > 0L){
			query.setParameter("districtId", districtId);
		}
		return query.list();
	}
	
	public List<Object[]> getAllMandalsByReferalAndDesignationBydistrict(Long constituencyId){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.pmRefCandidate.address.tehsil.tehsilId ");
		sb.append( ",model.pmRefCandidate.address.tehsil.tehsilName from PmRefCandidateDesignation model "
				+ "where model.isDeleted='N' ");
		if(constituencyId != null && constituencyId.longValue() > 0L){
			sb.append("and model.pmRefCandidate.address.constituencyId =:constituencyId ");
		}
		sb.append(" order by model.pmRefCandidate.address.tehsil.tehsilName asc");
		Query query =getSession().createQuery(sb.toString());
		if(constituencyId != null && constituencyId.longValue() > 0L){
			query.setParameter("constituencyId", constituencyId);
		}
		return query.list();
	}
	
	public List<Object[]> getDesignationsByReferlDesigtion(){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.pmDesignation.pmDesignationId,model.pmDesignation.designation "
				+ "from PmRefCandidateDesignation model where model.pmDesignation.isDeleted ='N' order by "
				+ " model.pmDesignation.orderNo asc " );
		Query query =getSession().createQuery(sb.toString());
		return query.list();
	}
}
