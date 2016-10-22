package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreLocationInfoCountDAO;
import com.itgrids.partyanalyst.model.TdpCadreLocationInfoCount;

public class TdpCadreLocationInfoCountDAO extends GenericDaoHibernate<TdpCadreLocationInfoCount, Long> implements  ITdpCadreLocationInfoCountDAO {
	
	public TdpCadreLocationInfoCountDAO() {
		super(TdpCadreLocationInfoCount.class);
	}
	
	public List<Object[]> getAllLocationsTdpCadreCount(Long enrollmentYearId){//2014 cadre data
		Query query = getSession().createQuery("" +
		" select model.locationScopeId,model.locationValue,model.cadreCount,model.cadrePercent " +
		" from  TdpCadreLocationInfoCount model " +
		" where model.enrollmentYearId = :enrollmentYearId ");
		query.setParameter("enrollmentYearId", enrollmentYearId);
		return query.list();
	}
}
