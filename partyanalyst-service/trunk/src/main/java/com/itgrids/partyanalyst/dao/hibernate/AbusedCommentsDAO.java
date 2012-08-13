package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IAbusedCommentsDAO;
import com.itgrids.partyanalyst.model.AbusedComments;
import com.itgrids.partyanalyst.utils.IConstants;

public class AbusedCommentsDAO extends GenericDaoHibernate<AbusedComments,Long> implements IAbusedCommentsDAO {
	
	public AbusedCommentsDAO() {
		super(AbusedComments.class);
	}
	@SuppressWarnings("unchecked")
	 public List<AbusedComments> getAllAbuseComment(Date firstDate, Date secondDate,String selectstatus){
	    	Object[] params = {firstDate, secondDate };
	    	
	    	StringBuilder query = new StringBuilder();
	    	query.append("select model from AbusedComments model where  date(model.time) >= ? and date(model.time) <= ?");
	    	
	    	  
	    		    if(selectstatus.equals("New"))
	    		       query.append(" and model.status ='false' and model.isDelete = 'false'");
	    		    else if(selectstatus.equals("Approved"))
	        		   query.append(" and model.status ='true' and model.isDelete = 'false'");
	    		    else if(selectstatus.equals("Rejected"))
	        		    query.append(" and model.isDelete ='true'");
	    	  
	    	   
	    	query.append(" order by model.time desc ");
	    	Query queObject = getSession().createQuery(query.toString());
	    	queObject.setDate(0,firstDate);
	    	queObject.setDate(1,secondDate);
	    	
	    	
	    	return queObject.list();
	    	
	    	/*return getHibernateTemplate().find("select model.candidate.lastname,model.name,model.message,"+
	    			"model.constituency.name,model.isApproved where date(model.time) >= ? "+
	    			"and date(model.time) <= ? "+params);*/
	    }
	@SuppressWarnings("unchecked")
	 public Integer controlAbuseComments(Long id,String status,String isDelete)
		{
			StringBuilder query = new StringBuilder();
	    	query.append("update AbusedComments model set ");   
	    	   
	           if(status != null)
	           {
	        	   if(isDelete != null)
	        		   query.append(" model.status =:status ,model.isDelete =:isDelete");
	           }
	           if(status == null)
	        	   query.append(" model.isDelete =:isDelete");
		    	   
	           if(isDelete == null)
	        	  query.append(" model.status =:status");
	           
	    	query.append(" where model.abusedCommentsId =:id");
	    	Query queObject = getSession().createQuery(query.toString());
	    	
	    	if(status != null)
	    		queObject.setString("status", status);
	    	if(isDelete != null)
	    		queObject.setString("isDelete", isDelete);
	    	
	    	queObject.setLong("id", id);
	    	
			return queObject.executeUpdate();
				
		}
	
public Long checkForAlreadyAbused(Long cmntId){		
		
		Query query = getSession().createQuery("select count(model.abusedCommentsId) from AbusedComments model where model.comment.commentId = ?");
		
		query.setParameter(0, cmntId);
		
		return (Long)query.uniqueResult();	
				
	}
	

}
