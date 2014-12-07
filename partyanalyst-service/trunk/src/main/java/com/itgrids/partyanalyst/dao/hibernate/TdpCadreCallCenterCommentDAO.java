package com.itgrids.partyanalyst.dao.hibernate;


import org.appfuse.dao.hibernate.GenericDaoHibernate;


import com.itgrids.partyanalyst.dao.ITdpCadreCallCenterCommentDAO;
import com.itgrids.partyanalyst.model.TdpCadreCallCenterComment;


public class TdpCadreCallCenterCommentDAO  extends GenericDaoHibernate<TdpCadreCallCenterComment, Long> implements ITdpCadreCallCenterCommentDAO {
	
	
	public TdpCadreCallCenterCommentDAO() {
		super(TdpCadreCallCenterComment.class);		
	}
	
}
