package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IGovtOrderDocumentsDAO;
import com.itgrids.partyanalyst.model.GovtOrderDocuments;

public class GovtOrderDocumentsDAO extends GenericDaoHibernate<GovtOrderDocuments, Long> implements IGovtOrderDocumentsDAO{

	public GovtOrderDocumentsDAO() {
		super(GovtOrderDocuments.class);
		// TODO Auto-generated constructor stub
	}
	public List<Object[]> getGovtOrderDocumentsPath(Long govtOrderId){
		Query query = getSession().createQuery("select distinct model.govtOrder.govtOrderId,model.path " +
				   							   " from GovtOrderDocuments model " +
				   							   " where  model.govtOrder.govtOrderId =:govtOrderId and " +
				   							   " model.isDeleted = 'N' ");
		query.setParameter("govtOrderId", govtOrderId);
		return query.list();
	}
	
public List<Object[]> getGoPassedDocuments(List<Long> postIds){
		
		StringBuilder queryStr = new StringBuilder();
		
		queryStr.append(" select model1.govtOrderDocumentsId,model1.path,date(model1.insertedTime),model1.govtOrderId from  NominatedPostGovtOrder model,GovtOrderDocuments model1 where   " +
				"  model1.govtOrderId = model.govtOrder.govtOrderId and  model.isDeleted = 'N' ");
		if(postIds != null && postIds.size() > 0){
			queryStr.append(" and model.nominatedPost.nominatedPostId in (:postIds) " );
		}
		Query query = getSession().createQuery(queryStr.toString());
		
		if(postIds != null && postIds.size() > 0){
	    	query.setParameterList("postIds", postIds);
	    }
		
		return query.list();
	}

}
