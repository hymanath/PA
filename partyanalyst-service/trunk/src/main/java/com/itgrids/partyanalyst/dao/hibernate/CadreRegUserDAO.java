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

	public Long getCadreRegUserByUser(Long userId){
		
		StringBuilder sb = new StringBuilder();
		sb.append(" select model.cadreRegUserId" +
				" from CadreRegUser model" +
				" where model.user.userId = :userId" +
				" and (model.userType = 'FM' or model.userType = 'WM')");
		
		/*if(stateId != null && stateId.longValue() == 1l ){
			sb.append(" and model.user.district.districtId between 11 and 23 ");
		}else if(stateId != null && stateId.longValue() == 36l){
			sb.append(" and model.user.district.districtId between 1 and 10 ");
		}else if(stateId != null && stateId.longValue() == 0l){
			sb.append(" and model.user.state.stateId = 1 ");
		}*/
		
		Query query = getSession().createQuery(sb.toString());
		query.setParameter("userId", userId);
		return (Long) query.uniqueResult();
	}
	
	public Long getCadreRegUserByUserForDataMonitoring(Long userId){
		Query query = getSession().createQuery("select model.cadreRegUserId" +
				" from CadreRegUser model" +
				" where model.user.userId = :userId" +
				" and (model.userType = 'DV' or model.userType = 'WM')");
		query.setParameter("userId", userId);
		return (Long) query.uniqueResult();
	}
	
	public Long getCadreRegId(Long loginUserId){
		Query query = getSession().createQuery("select model.cadreRegUserId" +
				" from CadreRegUser model" +
				" where model.user.userId = :loginUserId" +
				" and model.userType = 'FM'");
		query.setParameter("loginUserId", loginUserId);
		return (Long) query.uniqueResult();
	}
}
