package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITehsilConstituencyDAO;
import com.itgrids.partyanalyst.model.TehsilConstituency;

public class TehsilConstituencyDAO extends GenericDaoHibernate<TehsilConstituency,Long> implements ITehsilConstituencyDAO {
	
	public TehsilConstituencyDAO() {
		super(TehsilConstituency.class);
	}

	public List<Long> getTehsilIdsByConstituencyId(List<Long> constituencyIds){
		StringBuilder sb = new StringBuilder();
		
		sb.append(" select model.tehsil.tehsilId from  TehsilConstituency model" );
		sb.append(" where model.constituency.constituencyId in(:constituencyIds) ");
		Query query = getSession().createQuery(sb.toString());
		
		if(constituencyIds !=null && constituencyIds.size() >0){
			query.setParameterList("constituencyIds", constituencyIds);
		}
		return query.list();
	}
}
