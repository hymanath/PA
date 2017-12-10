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
	
}
