package com.itgrids.dao.impl;

import java.util.List;
import java.util.Map;

import org.appfuse.dao.SearchException;
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
			str.append(" select model.pmSubWorkDetailsId,model.reportType, model.document.path,model.documentId, model.petitionId from PmSubWorkCoveringLetter model where model.petitionId=:petiotionId " +
					" and model.isDeleted ='N' ");
			Query query = getSession().createQuery(str.toString());
			if(petiotionId.longValue()>0L)
				query.setParameter("petiotionId", petiotionId);
			return query.list();
		}
		return null;			
	}
	
	
}
