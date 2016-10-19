package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreDataVerificationDAO;
import com.itgrids.partyanalyst.model.TdpCadreDataVerification;

public class TdpCadreDataVerificationDAO extends GenericDaoHibernate<TdpCadreDataVerification, Long> implements ITdpCadreDataVerificationDAO{

	public TdpCadreDataVerificationDAO() {
		super(TdpCadreDataVerification.class);
		
	}
	public Long getActiveTeamMemberCnt(Date fromDate,Date toDate){
		
		  StringBuilder queryStr = new StringBuilder();
		  queryStr.append("select  distinct model.cadreRegUser.cadreRegUserId  from TdpCadreDataVerification model where model.tdpCadre.isDeleted='N' ");
		  
		  if(fromDate!= null && toDate!=null){
			  queryStr.append(" and date(model.tdpCadre.surveyTime) between :fromDate and :toDate ");	 
		  }
	    
		 Query query = getSession().createQuery(queryStr.toString());
	     if(fromDate!= null && toDate!=null){
			   query.setDate("fromDate", fromDate);
			   query.setDate("toDate", toDate);
			 }
	     return (Long) query.uniqueResult();
	}

}
