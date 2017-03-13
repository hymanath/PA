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
	public List<Object[]> getDesignationWiseTourProgramTarget(List<Long> monthIds){
		  StringBuilder queryStr = new StringBuilder();
		 queryStr.append(" select model.selfAppraisalDesignation.selfAppraisalDesignationId," +//0
		 				 " model.selfAppraisalDesignation.designation," +//1
		 				 " model.selfAppraisalProgram.selfAppraisalProgramId," +//2
		 				 " model.selfAppraisalProgram.program," +//3
		 				 " model.selfAppraisalToursMonth.selfAppraisalToursMonthId," +//4
		 				 " model.selfAppraisalToursMonth.monthName," +//5
		 				 " sum(model.targetDays)," +//6
		 				 " model.selfAppraisalToursMonth.year " +//7
		 			     " from SelfAppraisalDesignationProgramTarget model" +
		 			     " where model.isActive='Y' " +
		 			     " and model.selfAppraisalProgram.isDeleted='N' and model.selfAppraisalDesignation.isActive='Y' ");
		 if(monthIds != null && monthIds.size() > 0){
			 queryStr.append(" and model.selfAppraisalToursMonth.selfAppraisalToursMonthId in (:monthIds) ");
		 }
		 queryStr.append(" group by model.selfAppraisalDesignation.selfAppraisalDesignationId," +
		 				 " model.selfAppraisalProgram.selfAppraisalProgramId," +
		 				 " model.selfAppraisalToursMonth.selfAppraisalToursMonthId " +
		 				 " order by model.selfAppraisalDesignation.selfAppraisalDesignationId ," +
		 				 " model.selfAppraisalProgram.selfAppraisalProgramId," +
		 				 " model.selfAppraisalToursMonth.year desc," +
		 				 " model.selfAppraisalToursMonth.monthNo desc ");
		 Query query = getSession().createQuery(queryStr.toString());
		 if(monthIds != null && monthIds.size() > 0){
		   query.setParameterList("monthIds", monthIds); 
		 } 
		 return query.list();
		 
	}
	public List<Object[]> getCandidateWiseTourProgramTarget(List<Long> monthIds,Long selfAppraisalCandiateId){
		 StringBuilder queryStr = new StringBuilder();
		 queryStr.append(" select model.selfAppraisalDesignation.selfAppraisalDesignationId," +//0
		 				 " model.selfAppraisalDesignation.designation," +//1
		 				 " model2.selfAppraisalCandidateId," +//2
		 				 " model.selfAppraisalProgram.selfAppraisalProgramId," +//3
		 				 " model.selfAppraisalProgram.program," +//4
		 				 " model.selfAppraisalToursMonth.selfAppraisalToursMonthId," +//5
		 				 " model.selfAppraisalToursMonth.monthName," +//6
		 				 " sum(model.targetDays)," +//7
		 				 " model.selfAppraisalToursMonth.year " +//8
		 			     " from SelfAppraisalDesignationProgramTarget model,SelfAppraisalCandidate model2 " +
		 			     " where model.selfAppraisalDesignation.selfAppraisalDesignationId=model2.selfAppraisalDesignation.selfAppraisalDesignationId" +
		 			     " and model.isActive='Y' and model2.isActive='Y'  " +
		 			     " and model.selfAppraisalProgram.isDeleted='N' and model.selfAppraisalDesignation.isActive='Y' ");
		   if(monthIds != null && monthIds.size() > 0){
			 queryStr.append(" and model.selfAppraisalToursMonth.selfAppraisalToursMonthId in (:monthIds) ");
		   }
		  if(selfAppraisalCandiateId != null && selfAppraisalCandiateId.longValue() > 0){
	        	queryStr.append(" and model2.selfAppraisalCandidateId=:selfAppraisalCandidateId");
	      }
		 queryStr.append(" group by model.selfAppraisalDesignation.selfAppraisalDesignationId," +
		 		         " model2.selfAppraisalCandidateId," +
		 				 " model.selfAppraisalProgram.selfAppraisalProgramId," +
		 				 " model.selfAppraisalToursMonth.selfAppraisalToursMonthId " +
		 				 " order by model.selfAppraisalDesignation.selfAppraisalDesignationId ," +
		 				 " model.selfAppraisalProgram.selfAppraisalProgramId," +
		 				 " model.selfAppraisalToursMonth.year desc," +
		 				 " model.selfAppraisalToursMonth.monthNo desc ");
		 Query query = getSession().createQuery(queryStr.toString());
		 if(monthIds != null && monthIds.size() > 0){
		   query.setParameterList("monthIds", monthIds); 
		 } 
		 if(selfAppraisalCandiateId != null && selfAppraisalCandiateId.longValue() > 0){
	    	  query.setParameter("selfAppraisalCandidateId", selfAppraisalCandiateId); 
	      }
       return query.list();
}
	public List<Object[]> getDesignationWiseTourProgramTargetCnt(List<Long> monthIds,List<Long> designationIds){
		  StringBuilder queryStr = new StringBuilder();
		 queryStr.append(" select model.selfAppraisalDesignation.selfAppraisalDesignationId," +//0
		 				 " model.selfAppraisalDesignation.designation," +//1
		 				 " model.selfAppraisalProgram.selfAppraisalProgramId," +//2
		 				 " model.selfAppraisalProgram.program," +//3
		 				 " model.selfAppraisalToursMonth.selfAppraisalToursMonthId," +//4
		 				 " model.selfAppraisalToursMonth.monthName," +//5
		 				 " sum(model.targetDays)," +//6
		 				 " model.selfAppraisalToursMonth.year " +//7
		 			     " from SelfAppraisalDesignationProgramTarget model" +
		 			     " where model.isActive='Y' " +
		 			     " and model.selfAppraisalProgram.isDeleted='N' and model.selfAppraisalDesignation.isActive='Y' ");
		 if(monthIds != null && monthIds.size() > 0){
			 queryStr.append(" and model.selfAppraisalToursMonth.selfAppraisalToursMonthId in (:monthIds) ");
		 }
		 if(designationIds != null && designationIds.size() > 0){
			 queryStr.append(" and model.selfAppraisalDesignation.selfAppraisalDesignationId in (:designationIds) "); 
		 }
		 queryStr.append(" group by model.selfAppraisalDesignation.selfAppraisalDesignationId," +
		 				 " model.selfAppraisalProgram.selfAppraisalProgramId," +
		 				 " model.selfAppraisalToursMonth.selfAppraisalToursMonthId " +
		 				 " order by model.selfAppraisalDesignation.selfAppraisalDesignationId ," +
		 				 " model.selfAppraisalProgram.selfAppraisalProgramId," +
		 				 " model.selfAppraisalToursMonth.year desc," +
		 				 " model.selfAppraisalToursMonth.monthNo desc ");
		 Query query = getSession().createQuery(queryStr.toString());
		 if(monthIds != null && monthIds.size() > 0){
		   query.setParameterList("monthIds", monthIds); 
		 }
		 if(designationIds != null && designationIds.size() > 0){
			query.setParameterList("designationIds", designationIds);
		 }
		 return query.list();
		 
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
