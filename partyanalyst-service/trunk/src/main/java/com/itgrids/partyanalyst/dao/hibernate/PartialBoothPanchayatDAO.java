package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IPartialBoothPanchayatDAO;
import com.itgrids.partyanalyst.model.PartialBoothPanchayat;

public class PartialBoothPanchayatDAO extends
GenericDaoHibernate<PartialBoothPanchayat, Long> implements IPartialBoothPanchayatDAO{
	
	public PartialBoothPanchayatDAO(){
		super(PartialBoothPanchayat.class);		
	}

	@SuppressWarnings("unchecked")
	public List<PartialBoothPanchayat> getPartialBoothPanchayatDetailsByPanchayatId(Long panchayatId) {
		String queryString = " from PartialBoothPanchayat model where model.panchayat.panchayatId = :panchayatId  " ;
				//" and model.booth.isPartial like '%y%'";
		Query query = getSession().createQuery(queryString);
		query.setParameter("panchayatId", panchayatId);
	return query.list();
	}
}
