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
	public List<Object[]> getAllDistrictsByReferral(){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.address.district.districtId ");
		sb.append( ",model.address.district.districtName from PmRefCandidate model where model.isDeleted='N' ");
		sb.append(" order by model.address.district.districtName asc");
		Query query =getSession().createQuery(sb.toString());
		return query.list();
	}
	public List<Object[]> getAllConstituenciesByReferralAndDistrict(List<Long> districtIds){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.address.constituency.constituencyId ");
		sb.append( ",model.address.constituency.name from PmRefCandidate model where model.isDeleted ='N' ");
		if(districtIds != null && districtIds.size() > 0){
			sb.append("and model.address.districtId in (:districtIds) ");
		}
		sb.append(" order by model.address.constituency.name asc");
		Query query =getSession().createQuery(sb.toString());
		if(districtIds != null && districtIds.size() > 0){
			query.setParameterList("districtIds", districtIds);
		}
		return query.list();
	}//
	
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
