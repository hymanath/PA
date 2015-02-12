package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITwoWayMessageDAO;
import com.itgrids.partyanalyst.model.TwoWayMessage;

public class TwoWayMessageDAO extends GenericDaoHibernate<TwoWayMessage, Long> implements ITwoWayMessageDAO {

	public TwoWayMessageDAO() {
		super(TwoWayMessage.class);
	}
	
	public List<Object[]> getMessagesInfo(Integer startIndex,Integer maxIndex){
		//0message,1insertedTime,2mobileNo,3partNo
		Query query = getSession().createQuery("select model.message,model.insertedTime,model.mobileNo,model.partNo" +
				" from TwoWayMessage model where model.isDeleted ='N' order by model.insertedTime desc");
		if(startIndex != null && maxIndex != null){
			query.setFirstResult(startIndex);
		    query.setMaxResults(maxIndex);
		}
		  return query.list();
	}
	
	public Long getMessagesCount(){
		Query query = getSession().createQuery(" select count(model.twoWayMessagesId) from TwoWayMessage model where model.isDeleted ='N' ");
		
		  return (Long)query.uniqueResult();
	}
}
