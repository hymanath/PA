package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISelfAppraisalTourCategoryDAO;
import com.itgrids.partyanalyst.model.ActivityAttendance;
import com.itgrids.partyanalyst.model.SelfAppraisalTourCategory;

public class SelfAppraisalTourCategoryDAO extends GenericDaoHibernate<SelfAppraisalTourCategory, Long> implements ISelfAppraisalTourCategoryDAO {
		
	public SelfAppraisalTourCategoryDAO() {
		super(SelfAppraisalTourCategory.class);
	}
	
	public List<Object[]> getAllTourCategorys(Long candidateId){
		
		Query query = getSession().createQuery(" SELECT model1.selfAppraisalTourCategory.selfAppraisalTourCategoryId,model1.selfAppraisalTourCategory.tourCategory " +
				"  FROM SelfAppraisalCandidate model,SelfAppraisalDesignationTarget model1  " +
				"  WHERE model.selfAppraisalDesignationId = model1.selfAppraisalDesignationId " + 
				" and model.isDeleted ='N'" +
				" and model.selfAppraisalCandidateId=:candidateId " );
		
		return query.list();
	}
	
}
