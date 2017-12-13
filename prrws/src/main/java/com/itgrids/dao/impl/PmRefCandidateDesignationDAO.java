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
		sb.append("select model.pmRefCandidateId,model.pmRefCandidate.name,model.pmDesignation.designation ");//
		sb.append(",state.stateId,state.stateName ");
		sb.append(",district.districtId,district.districtName ");
		sb.append(",constituency.constituencyId,constituency.name ");
		sb.append(",tehsil.tehsilId,tehsil.tehsilName ");
		sb.append(",panchayat.panchayatId,panchayat.panchayatName ");
		sb.append(",model.pmRefCandidate.mobileNo,model.pmRefCandidate.email,model.pmDesignation.pmDesignationId ");
		sb.append(",parliament.constituencyId,parliament.name ");
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
	
}
