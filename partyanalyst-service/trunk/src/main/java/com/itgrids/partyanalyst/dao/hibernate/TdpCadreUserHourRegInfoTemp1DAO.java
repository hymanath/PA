package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreUserHourRegInfoTemp1DAO;
import com.itgrids.partyanalyst.model.TdpCadreUserHourRegInfoTemp1;

public class TdpCadreUserHourRegInfoTemp1DAO extends GenericDaoHibernate<TdpCadreUserHourRegInfoTemp1, Long> implements ITdpCadreUserHourRegInfoTemp1DAO{

	public TdpCadreUserHourRegInfoTemp1DAO() {
		super(TdpCadreUserHourRegInfoTemp1.class);
	}
	
	public int deleteAllRecords(){
	    Query query = getSession().createSQLQuery(" delete from tdp_cadre_user_hour_reg_info_temp1 ");
		return query.executeUpdate();
	}
	
	public int setPrimaryKeyAutoIncrementToOne(){
   	  Query query = getSession().createSQLQuery(" ALTER TABLE tdp_cadre_user_hour_reg_info_temp1  AUTO_INCREMENT = 1 ");
      return query.executeUpdate();
	}
}
