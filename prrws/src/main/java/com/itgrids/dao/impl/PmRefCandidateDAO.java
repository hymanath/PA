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
		sb.append( ",model.address.district.districtName from PmRefCandidate model ");
		sb.append(" order by model.address.district.districtName asc");
		Query query =getSession().createQuery(sb.toString());
		return query.list();
	}
	public List<Object[]> getAllConstituenciesByReferralAndDistrict(Long districtId){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.address.constituency.constituencyId ");
		sb.append( ",model.address.constituency.name from PmRefCandidate model where model.isDeleted ='N' ");
		if(districtId != null && districtId.longValue() > 0){
			sb.append("and model.address.districtId = :districtId");
		}
		sb.append(" order by model.address.constituency.name asc");
		Query query =getSession().createQuery(sb.toString());
		if(districtId != null && districtId.longValue() > 0){
			query.setParameter("districtId", districtId);
		}
		return query.list();
	}//
	
	public List<Object[]> getAllMandalsByReferralAndDistrict(Long constituencyId){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.address.tehsil.tehsilId ");
		sb.append( ",model.address.tehsil.tehsilName from PmRefCandidate model where model.isDeleted ='N' ");
		if(constituencyId != null && constituencyId.longValue() > 0){
			sb.append("and model.address.constituencyId = :constituencyId");
		}
		sb.append(" order by model.address.tehsil.tehsilName asc");
		Query query =getSession().createQuery(sb.toString());
		if(constituencyId != null && constituencyId.longValue() > 0){
			query.setParameter("constituencyId", constituencyId);
		}
		return query.list();
	}

}
