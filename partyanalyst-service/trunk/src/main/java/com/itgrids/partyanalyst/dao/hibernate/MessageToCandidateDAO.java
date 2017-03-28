package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IMessageToCandidateDAO;
import com.itgrids.partyanalyst.dto.CandidateCommentsVO;
import com.itgrids.partyanalyst.model.MessageToCandidate;

public class MessageToCandidateDAO extends GenericDaoHibernate<MessageToCandidate, Long> implements	IMessageToCandidateDAO {
	public MessageToCandidateDAO() {
		super(MessageToCandidate.class);
	}
    public List getAllOpenedMessages(Date firstDate, Date secondDate,String selectstatus){
    	Object[] params = {firstDate, secondDate };
    	
    	StringBuilder query = new StringBuilder();
    	query.append("select model.candidate.lastname,model.name,model.message,model.constituency.name,model.isApproved,model.messageToCandidateId,model.candidate.candidateId,model.constituency.constituencyId,model.isPrivate from MessageToCandidate model where  date(model.time) >= ? and date(model.time) <= ? ");
    	if(!selectstatus.equals("All")){
    		if(selectstatus.equals("New"))
    		    query.append("and model.isApproved ='false'");
    		else if(selectstatus.equals("Approved"))
        		query.append("and model.isApproved ='true'");
    		else if(selectstatus.equals("Rejected"))
        		query.append("and model.isApproved is null");
    	}
    	
    	query.append(" order by model.time desc ");
    	Query queObject = getSession().createQuery(query.toString());
    	queObject.setDate(0,firstDate);
    	queObject.setDate(1,secondDate);
    	
    	return queObject.list();
    	
    	/*return getHibernateTemplate().find("select model.candidate.lastname,model.name,model.message,"+
    			"model.constituency.name,model.isApproved where date(model.time) >= ? "+
    			"and date(model.time) <= ? "+params);*/
    }
    
    public Integer controlMessages(Long id,String message, String isApproved)
	{
		Query queryObject = getSession().createQuery("update MessageToCandidate model set model.isApproved = :isApproved,model.message =:message where model.messageToCandidateId =:id");
		queryObject.setString("isApproved", isApproved);
		queryObject.setString("message", message);
		queryObject.setLong("id", id);
		return queryObject.executeUpdate();
			
	}
    @SuppressWarnings("unchecked")
	public List<Object[]> getUserMessages(Long candidateId,int startIndex,int resultsCount){		
		Query queryObject = getSession().createQuery("select model.name , model.message ,model.constituency.name, model.time, model.constituency.constituencyId from MessageToCandidate model where model.candidate.candidateId =:candidateId and model.isApproved = 'true' and model.isDelete = 'false'and model.isPrivate = 'false' order by model.time desc ");
		queryObject.setLong("candidateId", candidateId);
		
		queryObject.setFirstResult(startIndex);
		queryObject.setMaxResults(resultsCount);
			
						
		return queryObject.list(); 
	}
	 @SuppressWarnings("unchecked")
	 public List<Long> getUserMessagesCount(Long candidateId)
	 {
		 return getHibernateTemplate().find("select count(*) from MessageToCandidate model where model.candidate.candidateId=? and model.isApproved = 'true' and model.isDelete = 'false'and model.isPrivate = 'false' order by model.time desc ",candidateId);
	 }
	 /*@SuppressWarnings("unchecked")
	 public Long getCandidateMessagesCount()
	 {
		 Query query = getSession().createQuery("select count(*) from MessageToCandidate model where model.isApproved = 'false' and model.isDelete = 'false' ");
		 return (Long)query.uniqueResult();
	 }*/
	 
	 public Long getCandidateMessagesCount(Date currentDate)
	 {
		 Query query = getSession().createQuery("select count(*) from MessageToCandidate model where Date(model.time)=? and model.isDelete = 'false' ");
		 query.setParameter(0, currentDate);
		 return (Long) query.uniqueResult();
	 }
	 
	
}
