package com.itgrids.dao.impl;

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

	public List<Long> getExistingPetitionRepresenteeDetailsByRefId(Long refCandidateId){
		StringBuilder str = new StringBuilder();
		str.append(" select distinct model.pmRepresenteeId from PmRepresentee model where model.isDeleted ='N' ");
		if(refCandidateId != null && refCandidateId.longValue()>0L)
			str.append(" and model.pmRefCandidateId = :refCandidateId ");
		Query query = getSession().createQuery(str.toString());
		query.setParameter("refCandidateId", refCandidateId);
		return query.list();
	}
	
	public List<Object[]> getAllDistrictsBySearchType(){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.userAddress.district.districtId,model.userAddress.district.districtName ");
		sb.append( " from PmRepresentee model where model.isDeleted='N' ");
		sb.append( "order by model.userAddress.district.districtName asc ");
		Query query =getSession().createQuery(sb.toString());
		return query.list();
	}
	
	public List<Object[]> getAlConstituenciesBySearchType(Long districtId){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.userAddress.constituency.constituencyId,model.userAddress.constituency.name ");
		sb.append( " from PmRepresentee model where model.isDeleted='N' ");
		if(districtId != null && districtId.longValue() >0 ){
			sb.append("and model.userAddress.districtId=:districtId");
		}
		sb.append(" order by model.userAddress.constituency.name asc ");
		Query query =getSession().createQuery(sb.toString());
		if(districtId != null && districtId.longValue() >0 ){
			query.setParameter("districtId", districtId);
		}
		return query.list();
	}
	public List<Object[]> getAllMandalsBySearchType(Long constituencyId){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.userAddress.tehsil.tehsilId,model.userAddress.tehsil.tehsilName ");
		sb.append( " from PmRepresentee model where model.isDeleted='N' ");
		if(constituencyId != null && constituencyId.longValue() >0 ){
			sb.append("and model.userAddress.constituencyId=:constituencyId ");
		}
		sb.append( "order by model.userAddress.tehsil.tehsilName asc ");
		Query query =getSession().createQuery(sb.toString());
		if(constituencyId != null && constituencyId.longValue() >0 ){
			query.setParameter("constituencyId", constituencyId);
		}
		return query.list();
	}
}

