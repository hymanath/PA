package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreDataVerificationDAO;
import com.itgrids.partyanalyst.model.TdpCadreDataVerification;
import com.itgrids.partyanalyst.utils.DateUtilService;
import com.itgrids.partyanalyst.utils.IConstants;

public class TdpCadreDataVerificationDAO extends GenericDaoHibernate<TdpCadreDataVerification, Long> implements ITdpCadreDataVerificationDAO{

	public TdpCadreDataVerificationDAO() {
		super(TdpCadreDataVerification.class);
		
	}
	public Long getActiveTeamMemberCnt(Long stateId){
		
		  StringBuilder queryStr = new StringBuilder();
		  queryStr.append("select  count(distinct model.cadreRegUser.cadreRegUserId)  from TdpCadreDataVerification model where model.tdpCadre.isDeleted='N' ");
		  
		  	if(stateId != null && stateId.longValue() == 1l){
	    	    queryStr.append("  and model.tdpCadre.userAddress.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");
			}else if(stateId != null && stateId.longValue() == 36l){
				queryStr.append("  and model.tdpCadre.userAddress.district.districtId in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ");
			}else if(stateId != null && stateId.longValue() == 0l){
				queryStr.append(" and  model.tdpCadre.userAddress.state.stateId = 1 ");
			} 
		  
		  queryStr.append(" and date(model.tdpCadre.surveyTime) = :today ");	 
		 
	    
		 Query query = getSession().createQuery(queryStr.toString());
	     query.setDate("today", new DateUtilService().getCurrentDateAndTime());
		
	     return (Long) query.uniqueResult();
	}

}
