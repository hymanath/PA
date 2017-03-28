package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITirupathiByeleMobileBoothDAO;
import com.itgrids.partyanalyst.model.TirupathiByeleMobileBooth;

public class TirupathiByeleMobileBoothDAO extends GenericDaoHibernate<TirupathiByeleMobileBooth, Long> implements ITirupathiByeleMobileBoothDAO {

	public TirupathiByeleMobileBoothDAO() {
		super(TirupathiByeleMobileBooth.class);
	}
	
	public List<String> getMobileNosOfBooth(Long boothId){
		Query query = getSession().createQuery(" select distinct model.mobileNo from TirupathiByeleMobileBooth model " +
				" where model.booth.boothId= :boothId ");
		
		query.setParameter("boothId", boothId);
		return query.list();
	}
}
