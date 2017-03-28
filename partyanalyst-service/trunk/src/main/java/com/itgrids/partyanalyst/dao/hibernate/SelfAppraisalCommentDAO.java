package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ISelfAppraisalCommentDAO;
import com.itgrids.partyanalyst.model.SelfAppraisalComment;

public class SelfAppraisalCommentDAO extends GenericDaoHibernate<SelfAppraisalComment, Long> implements ISelfAppraisalCommentDAO{

	public SelfAppraisalCommentDAO(){
		super(SelfAppraisalComment.class);
	}
	
	public List<String> getCandidateCommentDetails(Long tdpCadreId, Long tourMonthId){
		
			StringBuilder queryStr = new StringBuilder();
		   	
		   	queryStr.append(" select model.comment from SelfAppraisalComment model ");
		   	
			queryStr.append( " where " +
		   			" model.tdpCadreId = :tdpCadreId ");
			if(tourMonthId != null){
					 queryStr.append( " and model.selfAppraisalToursMonth.selfAppraisalToursMonthId = :tourMonthId ");
			}
			  queryStr.append( " and model.isDeleted ='N' ");
			   	
			  Query query = getSession().createQuery(queryStr.toString());
			  query.setParameter("tdpCadreId", tdpCadreId);
			  
			  if(tourMonthId != null)
			  query.setParameter("tourMonthId", tourMonthId);
				
			return query.list();
		}
	
}
