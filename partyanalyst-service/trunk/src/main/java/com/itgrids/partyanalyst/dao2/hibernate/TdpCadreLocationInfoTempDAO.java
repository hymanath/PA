package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreLocationInfoTempDAO;
import com.itgrids.partyanalyst.model.TdpCadreLocationInfoTemp;

public class TdpCadreLocationInfoTempDAO extends GenericDaoHibernate<TdpCadreLocationInfoTemp, Long> implements ITdpCadreLocationInfoTempDAO{

	public TdpCadreLocationInfoTempDAO() {
		super(TdpCadreLocationInfoTemp.class);
	}
	
	 public int deleteAllRecords(){
	    	
    	Query query = getSession().createSQLQuery(" delete from tdp_cadre_location_info_temp ");
    	return query.executeUpdate();
	 }
	 
	 public int setPrimaryKeyAutoIncrementToOne(){
    	Query query = getSession().createSQLQuery(" ALTER TABLE tdp_cadre_location_info_temp AUTO_INCREMENT = 1 ");
    	return query.executeUpdate();
	 }

}
