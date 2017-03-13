package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISelfAppraisalCandidateProgramDetailsDAO;
import com.itgrids.partyanalyst.model.SelfAppraisalCandidateProgramDetails;

public class SelfAppraisalCandidateProgramDetailsDAO extends GenericDaoHibernate<SelfAppraisalCandidateProgramDetails, Long>
		implements ISelfAppraisalCandidateProgramDetailsDAO {
        public SelfAppraisalCandidateProgramDetailsDAO(){
        	super(SelfAppraisalCandidateProgramDetails.class);
        }
        
        @SuppressWarnings("unchecked")
    	public List<Object[]> getToursProgramOverviewByCadre(Long tdpCadreId, Long tourMonthId){
    	       	StringBuilder queryStr = new StringBuilder();
    	       	
    	       	
    	       	queryStr.append(" SELECT model.selfAppraisalCandidateProgramDetailsId,model.selfAppraisalDesignation.selfAppraisalDesignationId," +
    	       			" model.selfAppraisalDesignation.designation,model.selfAppraisalProgram.selfAppraisalProgramId," +
    	       			" model.selfAppraisalProgram.program " +    	       	
    	       			" ,model.tourVisits,model.updatedTime,model.selfAppraisalCandidate.selfAppraisalCandidateId " +
    	       			" FROM " +
    	       			" SelfAppraisalCandidateProgramDetails model ");
    	       	
    	    	queryStr.append( " where " +
    	       			" model.selfAppraisalCandidate.tdpCadreId = :tdpCadreId and model.selfAppraisalToursMonthId = :tourMonthId " +
    	       			" and model.isDeleted ='N' and model.selfAppraisalProgram.isDeleted ='N' ");

    	       	
    	   Query query = getSession().createQuery(queryStr.toString());
    	   
    	   query.setParameter("tdpCadreId", tdpCadreId);
    	   query.setParameter("tourMonthId", tourMonthId);
    	      
           	return query.list();
           }
}
