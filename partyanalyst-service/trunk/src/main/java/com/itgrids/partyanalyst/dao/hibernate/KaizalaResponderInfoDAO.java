package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IKaizalaResponderInfoDAO;
import com.itgrids.partyanalyst.model.KaizalaResponderInfo;

public class KaizalaResponderInfoDAO extends GenericDaoHibernate<KaizalaResponderInfo, Long> implements IKaizalaResponderInfoDAO {
	public KaizalaResponderInfoDAO() {
		super(KaizalaResponderInfo.class); 
	}
		
	public List<Long> getRespondentId(String modileNum){
		Query query = getSession().createQuery(" select model.kaizalaResponderInfoId " +
				" from KaizalaResponderInfo model " +
				" where model.mobileNumber=:modileNum and model.isDeleted='N' order by model.kaizalaResponderInfoId desc ");
		query.setParameter("modileNum", modileNum);
		return query.list();
	}
	
}