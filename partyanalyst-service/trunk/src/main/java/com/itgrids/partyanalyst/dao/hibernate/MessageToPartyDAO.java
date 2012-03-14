package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IMessageToPartyDAO;
import com.itgrids.partyanalyst.model.MessageToParty;

public class MessageToPartyDAO extends GenericDaoHibernate<MessageToParty,Long> implements IMessageToPartyDAO{
	public MessageToPartyDAO()
	{
		super(MessageToParty.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> getMessageToParty(Long partyId, int startIndex,int resultsCount)
	{
		Query queryObject = getSession().createQuery("select model.name , model.message ,model.constituency.name, model.sentTime,model.constituency.constituencyId from MessageToParty  model where model.party.partyId =:partyId  and model.isDelete = 'false'and model.isPrivate = 'false'and model.isApproved = 'true' order by model.sentTime desc ");
		queryObject.setLong("partyId", partyId);
		
		queryObject.setFirstResult(startIndex);
		queryObject.setMaxResults(resultsCount);
			
						
		return queryObject.list(); 
	}
	
	@SuppressWarnings("unchecked")
	public List<Long> getPartyMessageCount(Long partyId)
	{
		return getHibernateTemplate().find("select count(*) from MessageToParty  model where model.party.partyId = ? and model.isDelete = 'false'and model.isPrivate = 'false' order by model.sentTime desc ",partyId);
	}
	
	public List getAllPartyMessages(Date firstDate, Date secondDate,String selectstatus)
	{
		 Object[] params = {firstDate, secondDate };
		 
		 StringBuilder query = new StringBuilder();
		 query.append("select model.party.shortName,model.name,model.message,model.constituency.name,model.isApproved,model.messageToPartyId,model.party.partyId,model.constituency.constituencyId,model.isPrivate from MessageToParty model where date(model.sentTime) >= ? and date(model.sentTime) <= ?");
		 if(!selectstatus.equals("All")){
	    		if(selectstatus.equals("New"))
	    		    query.append("and model.isApproved ='false'");
	    		else if(selectstatus.equals("Approved"))
	        		query.append("and model.isApproved ='true'");
	    		else if(selectstatus.equals("Rejected"))
	        		query.append("and model.isApproved is null");
	    	}
		 query.append(" order by model.sentTime desc ");
	    	Query queObject = getSession().createQuery(query.toString());
	    	queObject.setDate(0,firstDate);
	    	queObject.setDate(1,secondDate);
		 return queObject.list();
		 
	 }
	 
	 public Integer adminModifiedMessages(Long id,String message, String isApproved)
	 {
		 StringBuilder query = new StringBuilder();
		 query.append("update MessageToParty model set model.isApproved =?,model.message =? where model.messageToPartyId =?");
		 Query queObject = getSession().createQuery(query.toString());
		 queObject.setString(0,isApproved);
		 queObject.setString(1,message);
		 queObject.setLong(2,id);
		 return queObject.executeUpdate();
		 
	 }
	 
}
