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
    public List getAllOpenedMessages(Date firstDate, Date secondDate){
    	Object[] params = {firstDate, secondDate };
    	
    	StringBuilder query = new StringBuilder();
    	query.append("select model.candidate.lastname,model.name,model.message,model.constituency.name,model.isApproved,model.messageToCandidateId from MessageToCandidate model where  date(model.time) >= ? and date(model.time) <= ?");
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
	public List<Object[]> getUserMessages(Long candidateId){		
		return getHibernateTemplate().find("select model.name , model.message ,model.constituency.name, model.time from MessageToCandidate model where model.candidate.candidateId=? and model.isApproved = 'true' and model.isDelete = 'false'",candidateId);
	}
	
    
	
	
    
    

}
