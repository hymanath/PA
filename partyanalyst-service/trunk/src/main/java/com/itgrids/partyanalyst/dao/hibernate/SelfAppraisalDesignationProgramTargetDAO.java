package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISelfAppraisalDesignationProgramTargetDAO;
import com.itgrids.partyanalyst.model.SelfAppraisalDesignationProgramTarget;

public class SelfAppraisalDesignationProgramTargetDAO extends GenericDaoHibernate<SelfAppraisalDesignationProgramTarget, Long> implements ISelfAppraisalDesignationProgramTargetDAO {
 
	public SelfAppraisalDesignationProgramTargetDAO(){
		super(SelfAppraisalDesignationProgramTarget.class);
	}
	
	
	public List<Object[]> getDesignationWiseDetails(List<Long> desgIds,Long toursMonthId){
		
		Query query = getSession().createQuery("  SELECT model.selfAppraisalDesignation.selfAppraisalDesignationId,  " +
				" model.selfAppraisalDesignation.designation,model.selfAppraisalProgram.selfAppraisalProgramId," +
				" model.selfAppraisalProgram.program " +
		 
				" FROM SelfAppraisalDesignationProgramTarget model " +
				" WHERE  model.isActive='Y' " +
					" and model.selfAppraisalDesignation.isActive = 'Y' " +
					" and model.selfAppraisalProgram.isDeleted='N' " +
					" and model.selfAppraisalDesignation.selfAppraisalDesignationId in (:desgIds)" +
					" and model.selfAppraisalToursMonthId = :toursMonthId " +
				" GROUP BY  model.selfAppraisalDesignation.selfAppraisalDesignationId,model.selfAppraisalProgram.selfAppraisalProgramId " +
				" ORDER BY model.selfAppraisalDesignation.selfAppraisalDesignationId,model.selfAppraisalProgram.selfAppraisalProgramId ");
		
		query.setParameterList("desgIds", desgIds);
		query.setParameter("toursMonthId", toursMonthId);
		
		return query.list();
	}
	
	
}
