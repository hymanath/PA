package com.itgrids.dao.impl;

import java.util.List;
import java.util.Set;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPmSubWorkCoveringLetterDAO;
import com.itgrids.model.PmSubWorkCoveringLetter;

@Repository
public class PmSubWorkCoveringLetterDAO extends GenericDaoHibernate<PmSubWorkCoveringLetter, Long> implements IPmSubWorkCoveringLetterDAO {

	@Autowired
	SessionFactory sessionFactory;
	public PmSubWorkCoveringLetterDAO() {
		super(PmSubWorkCoveringLetter.class);
	}

	public List<String> getCoveringLetterDetailsByEndorsmentNo(String endorsmenNo){
		StringBuilder str = new StringBuilder();
		str.append("select distinct model.document.path from PmSubWorkCoveringLetter model where model.endorsmentNo ='"+endorsmenNo+"'  and model.isDeleted ='N' ");
		Query query = getSession().createQuery(str.toString());
		return query.list();				
	}
	
	public List<Object[]> getSubWorkWiseRequiredDocumentsDetailsByPetitionId(Long petiotionId){
		if(petiotionId != null){
			StringBuilder str = new StringBuilder();
			str.append(" select model.pmSubWorkDetailsId,model.reportType, model.document.path,model.documentId, model.petitionId,model.refNo from PmSubWorkCoveringLetter model where model.petitionId=:petiotionId " +
					" and model.isDeleted ='N' ");
			Query query = getSession().createQuery(str.toString());
			if(petiotionId.longValue()>0L)
				query.setParameter("petiotionId", petiotionId);
			return query.list();
		}
		return null;			
	}
	public int disableExistingCoveringLettersForPetition(Long petitionId,String reporttype){
		StringBuilder sb = new StringBuilder();
		sb.append(" update PmSubWorkCoveringLetter model set model.isDeleted ='Y'  "
				+ "where model.petitionId =:petitionId and model.reportType = :reporttype ");
		Query query=getSession().createQuery(sb.toString());
		
		query.setParameter("petitionId", petitionId);
		query.setParameter("reporttype", reporttype);
		return query.executeUpdate();
	}	
	public List<Object[]> getAllTypeOfDocumentsForPetition(Set<Long> petiotionIds,String reportType){
		if(petiotionIds != null){
			StringBuilder str = new StringBuilder();
			str.append(" select distinct  model.petitionId,model.reportType,model.documentTypeId,model.pmSubWorkDetails.pmSubWorkDetailsId,model.document.documentId,model.document.path from PmSubWorkCoveringLetter model " +
					"where  " +
					"  model.isDeleted ='N' ");
			if(petiotionIds.size()>0)
				str.append(" and model.petitionId in (:petiotionIds) ");
			if(reportType != null){
				str.append(" and model.reportType = :reportType ");
			}
			str.append(" group by model.petitionId,model.reportType,model.pmSubWorkDetails.pmSubWorkDetailsId ");
			Query query = getSession().createQuery(str.toString());
			if(reportType != null){
				query.setParameter("reportType", reportType);
			}
			if(petiotionIds.size()>0)
				query.setParameterList("petiotionIds", petiotionIds);
			return query.list();
		}
		return null;			
	}
	
}
