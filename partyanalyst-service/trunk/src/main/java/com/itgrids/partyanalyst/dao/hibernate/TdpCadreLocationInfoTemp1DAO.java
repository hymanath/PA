package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreLocationInfoTemp1DAO;
import com.itgrids.partyanalyst.model.TdpCadreLocationInfoTemp1;

public class TdpCadreLocationInfoTemp1DAO extends GenericDaoHibernate<TdpCadreLocationInfoTemp1, Long> implements ITdpCadreLocationInfoTemp1DAO{

	public TdpCadreLocationInfoTemp1DAO() {
		super(TdpCadreLocationInfoTemp1.class);
	}
	
	 public int deleteAllRecords(){
	    	
	   Query query = getSession().createSQLQuery(" delete from tdp_cadre_location_info_temp1 ");
	   return query.executeUpdate();
	}
	 
	 public int setPrimaryKeyAutoIncrementToOne(){
    	Query query = getSession().createSQLQuery(" ALTER TABLE tdp_cadre_location_info_temp1 AUTO_INCREMENT = 1 ");
    	return query.executeUpdate();
	}
}
