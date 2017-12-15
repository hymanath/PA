package com.itgrids.dao.impl;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.itgrids.dao.IPrPanchayatDAO;
import com.itgrids.model.PrPanchayat;

@Repository
public class PrPanchayatDAO extends GenericDaoHibernate<PrPanchayat, Long>
		implements IPrPanchayatDAO {
	
	public PrPanchayatDAO()
	{
		super(PrPanchayat.class);
	}
	
	public List<Object[]> getpanchayatsFrTehsil(String tehsilId){
		StringBuilder sb = new StringBuilder();
		sb.append("select distinct model.uniquecode,"
				+ "model.panchayatName"
				+ " from PrPanchayat model");
		if(tehsilId  != null && !tehsilId.isEmpty()){
			sb.append(" where model.prTehsil.uniqueCode = :tehsilId order by model.panchayatName asc");
		}
		
		Query query = getSession().createQuery(sb.toString());
		if(tehsilId  != null && !tehsilId.isEmpty()){
			query.setParameter("tehsilId", tehsilId);
		}
		
		return query.list();
	}
}
