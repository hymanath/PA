package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPetitionReffererDAO;
import com.itgrids.model.PetitionRefferer;

@Repository
public class PetitionReffererDAO extends GenericDaoHibernate<PetitionRefferer, Long> implements IPetitionReffererDAO {

	@Autowired
	SessionFactory sessionFactory;
	
	public PetitionReffererDAO() {
		super(PetitionRefferer.class);
	}
	
	public List<Object[]> getPetitionReffererDetailsByMemberId(Long petitionMemberId){
		StringBuilder sb = new StringBuilder();
		sb.append("  select model.petitionReffererId,model.petitionReffererCandidateId, ");
		sb.append("  petitionReffererCandidate.name,petitionReffererCandidate.petitionDesignation.petitionDesignationId,petitionReffererCandidate.petitionDesignation.designationName");
		sb.append(", petitionReffererCandidate.mobileNo, petitionReffererCandidate.emailId ");
		sb.append(", petitionReffererCandidate.addressId, petitionReffererCandidate.partyId,petitionReffererCandidate.partyName ");
		sb.append(" from PetitionRefferer model " );
		sb.append(" left join model.petitionMember petitionMember ");
		sb.append(" left join model.petitionReffererCandidate petitionReffererCandidate ");			
		sb.append(" where model.isDeleted = 'N' and model.petitionMemberId =:petitionMemberId and petitionMember.isDeleted='N' and petitionMember.isExpired='N' ");		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("petitionMemberId", petitionMemberId);
		return query.list();
	}
}
