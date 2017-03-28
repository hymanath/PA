package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreAgeInfoTempDAO;
import com.itgrids.partyanalyst.model.TdpCadreAgeInfoTemp;

public class TdpCadreAgeInfoTempDAO extends GenericDaoHibernate<TdpCadreAgeInfoTemp,Long> implements ITdpCadreAgeInfoTempDAO {
	
	public TdpCadreAgeInfoTempDAO() {
		super(TdpCadreAgeInfoTemp.class);
	}
	
	 public int deleteAllRecords(){
	    	
	   Query query = getSession().createSQLQuery(" delete from tdp_cadre_age_info_temp ");
	   return query.executeUpdate();
	}
}
