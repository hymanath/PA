package com.itgrids.partyanalyst.dao.hibernate;


import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;


import com.itgrids.partyanalyst.dao.ITdpCadreCallCenterCommentDAO;
import com.itgrids.partyanalyst.model.TdpCadreCallCenterComment;


public class TdpCadreCallCenterCommentDAO  extends GenericDaoHibernate<TdpCadreCallCenterComment, Long> implements ITdpCadreCallCenterCommentDAO {
	
	
	public TdpCadreCallCenterCommentDAO() {
		super(TdpCadreCallCenterComment.class);		
	}
	
	 public Integer updateComments(Long feedbackId){
			Query query = getSession().createQuery("update TdpCadreCallCenterComment model set model.isDelete = 'Y' where model.tdpCadreCallCenterFeedbackId = :feedbackId");
			query.setParameter("feedbackId", feedbackId);
			return query.executeUpdate();
		}
	
}
