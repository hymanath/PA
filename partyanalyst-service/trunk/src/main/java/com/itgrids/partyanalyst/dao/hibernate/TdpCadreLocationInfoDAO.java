package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreLocationInfoDAO;
import com.itgrids.partyanalyst.model.TdpCadreLocationInfo;

public class TdpCadreLocationInfoDAO extends GenericDaoHibernate<TdpCadreLocationInfo, Long> implements ITdpCadreLocationInfoDAO{

	public TdpCadreLocationInfoDAO() {
		super(TdpCadreLocationInfo.class);
	}
	
	 public int deleteAllRecords(){
	    	
    	Query query = getSession().createSQLQuery(" delete from tdp_cadre_location_info ");
    	return query.executeUpdate();
    }
    public int setPrimaryKeyAutoIncrementToOne(){
    	Query query = getSession().createSQLQuery(" ALTER TABLE tdp_cadre_location_info AUTO_INCREMENT = 1 ");
    	return query.executeUpdate();
    }
}
