package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreRegUserDAO;
import com.itgrids.partyanalyst.model.CadreRegUser;
import com.itgrids.partyanalyst.utils.IConstants;

public class CadreRegUserDAO extends GenericDaoHibernate<CadreRegUser, Long> implements ICadreRegUserDAO {

	public CadreRegUserDAO() {
		super(CadreRegUser.class);
		
	}

	public Long getCadreRegUserByUser(Long userId,Long stateId){
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.cadreRegUserId" +
				" from CadreRegUser model" +
				" where model.user.userId = :userId" +
				" and model.userType = 'FM'");
		
		if(stateId != null && stateId.longValue() == 1l ){
			sb.append(" and model.user.district.districtId in ("+IConstants.AP_NEW_DISTRICTS_IDS_LIST+") ");
		}else if(stateId != null && stateId.longValue() == 36l){
			sb.append(" and model.user.district.districtId in ("+IConstants.TS_NEW_DISTRICTS_IDS_LIST+") ");
		}else if(stateId != null && stateId.longValue() == 0l){
			sb.append(" and model.user.state.stateId = 1 ");
		}
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("userId", userId);
		return (Long) query.uniqueResult();
	}
	
	public Long getCadreRegUserByUserForDataMonitoring(Long userId){
		Query query = getSession().createQuery("select model.cadreRegUserId" +
				" from CadreRegUser model" +
				" where model.user.userId = :userId" +
				" and model.userType = 'DV'");
		query.setParameter("userId", userId);
		return (Long) query.uniqueResult();
	}
}
