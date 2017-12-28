package com.itgrids.dao.impl;

import java.util.List;
import java.util.Map;

import org.appfuse.dao.SearchException;
import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmRefCandidateDAO;
import com.itgrids.model.PmRefCandidate;

@Repository
public class PmRefCandidateDAO extends GenericDaoHibernate<PmRefCandidate, Long> implements IPmRefCandidateDAO {
	@Autowired
	SessionFactory sessionFactory;
	public PmRefCandidateDAO() {
		super(PmRefCandidate.class);
		
	}
	public List<Object[]> getAllDistrictsByReferral(List<Long> deptIds){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.pmRefCandidate.address.district.districtId ");
		sb.append( ",model.pmRefCandidate.address.district.districtName from PmRepresenteeRefDetails model,PmSubWorkDetails model1 where " +
				"model.pmRefCandidate.isDeleted='N' and model.petition.petitionId = model1.petition.petitionId ");
		if(deptIds != null && deptIds.size()>0){
			sb.append(" and model1.pmDepartment.pmDepartmentId in (:deptIds) ");
		}
		sb.append(" order by model.pmRefCandidate.address.district.districtName asc");
		Query query =getSession().createQuery(sb.toString());
		if(deptIds != null && deptIds.size()>0){
			query.setParameterList("deptIds", deptIds);
		}
		return query.list();
	}
	public List<Object[]> getAllConstituenciesByReferralAndDistrict(List<Long> districtIds,  List<Long> deptIds){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.pmRefCandidate.address.constituency.constituencyId ");
		sb.append( ",model.pmRefCandidate.address.constituency.name from PmRepresenteeRefDetails model,PmSubWorkDetails model1 where " +
				"model.pmRefCandidate.isDeleted='N' and model1.isDeleted='N'and  model.petition.petitionId = model1.petition.petitionId ");
		if(districtIds != null && districtIds.size() > 0){
			sb.append("and model.pmRefCandidate.address.district.districtId in (:districtIds) ");
		}
		
		if(deptIds != null && deptIds.size() > 0){
			sb.append("and model1.pmDepartment.pmDepartmentId in (:deptIds) ");
		}
		sb.append(" order by model.pmRefCandidate.address.constituency.name asc");
		Query query =getSession().createQuery(sb.toString());
		if(deptIds != null && deptIds.size() > 0){
			query.setParameterList("deptIds", deptIds);
		}
		if(districtIds != null && districtIds.size() >0L){
			query.setParameterList("districtIds", districtIds);
		}

		return query.list();
	}
	
	public List<Object[]> getAllMandalsByReferralAndDistrict(List<Long> constituencyIds){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.address.tehsil.tehsilId ");
		sb.append( ",model.address.tehsil.tehsilName from PmRefCandidate model where model.isDeleted ='N' ");
		if(constituencyIds != null && constituencyIds.size() > 0){
			sb.append("and model.address.constituencyId in (:constituencyIds) ");
		}
		sb.append(" order by model.address.tehsil.tehsilName asc");
		Query query =getSession().createQuery(sb.toString());
		if(constituencyIds != null && constituencyIds.size() > 0){
			query.setParameterList("constituencyIds", constituencyIds);
		}
		return query.list();
	}

}
