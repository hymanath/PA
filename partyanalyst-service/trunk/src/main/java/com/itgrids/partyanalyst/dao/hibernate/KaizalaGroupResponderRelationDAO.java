package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IKaizalaGroupResponderRelationDAO;
import com.itgrids.partyanalyst.dao.IKaizalaResponderInfoDAO;
import com.itgrids.partyanalyst.model.KaizalaGroupResponderRelation;
import com.itgrids.partyanalyst.model.KaizalaResponderInfo;

public class KaizalaGroupResponderRelationDAO extends GenericDaoHibernate<KaizalaGroupResponderRelation, Long> implements IKaizalaGroupResponderRelationDAO {
	public KaizalaGroupResponderRelationDAO() {
		super(KaizalaGroupResponderRelation.class); 
	}
	public int updateMemberRemoved(Long eventResponseId,Long kaizalaGroupsId,Long responderInfoId){
	    Query query = getSession().createQuery(" update KaizalaGroupResponderRelation model set model.isDeleted='Y', model.kaizalaEventsResponseId =:eventResponseId  " +
	        " where model.kaizalaGroupsId =:kaizalaGroupsId and model.kaizalaResponderInfoId =:responderInfoId " );
	    
	    query.setParameter("kaizalaGroupsId", kaizalaGroupsId);
	    query.setParameter("eventResponseId", eventResponseId);
	    query.setParameter("responderInfoId", responderInfoId);
	    return query.executeUpdate();
	  }
}