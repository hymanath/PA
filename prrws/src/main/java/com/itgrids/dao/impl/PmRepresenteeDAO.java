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
	
	public List<Object[]> getAllDistrictsBySearchType(){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.userAddress.district.districtId,model.userAddress.district.districtName ");
		sb.append( " from PmRepresentee model where model.isDeleted='N' ");
		sb.append( "order by model.userAddress.district.districtName asc ");
		Query query =getSession().createQuery(sb.toString());
		return query.list();
	}
	
	public List<Object[]> getAlConstituenciesBySearchType(List<Long> districtIds){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.userAddress.constituency.constituencyId,model.userAddress.constituency.name ");
		sb.append( " from PmRepresentee model where model.isDeleted='N' ");
		if(districtIds != null && districtIds.size() >0 ){
			sb.append("and model.userAddress.districtId in (:districtIds) ");
		}
		sb.append(" order by model.userAddress.constituency.name asc ");
		Query query =getSession().createQuery(sb.toString());
		if(districtIds != null && districtIds.size() >0 ){
			query.setParameterList("districtIds", districtIds);
		}
		return query.list();
	}
	public List<Object[]> getAllMandalsBySearchType(List<Long> constituencyIds){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.userAddress.tehsil.tehsilId,model.userAddress.tehsil.tehsilName ");
		sb.append( " from PmRepresentee model where model.isDeleted='N' ");
		if(constituencyIds != null && constituencyIds.size() >0 ){
			sb.append("and model.userAddress.constituencyId in (:constituencyIds) ");
		}
		sb.append( "order by model.userAddress.tehsil.tehsilName asc ");
		Query query =getSession().createQuery(sb.toString());
		if(constituencyIds != null && constituencyIds.size() >0 ){
			query.setParameterList("constituencyIds", constituencyIds);
		}
		return query.list();
	}
}

