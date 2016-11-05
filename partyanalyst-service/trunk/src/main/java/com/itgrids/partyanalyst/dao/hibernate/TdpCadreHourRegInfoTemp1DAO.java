package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreHourRegInfoTemp1DAO;
import com.itgrids.partyanalyst.model.TdpCadreHourRegInfoTemp1;

public class TdpCadreHourRegInfoTemp1DAO extends GenericDaoHibernate<TdpCadreHourRegInfoTemp1,Long> implements ITdpCadreHourRegInfoTemp1DAO {
	
	public TdpCadreHourRegInfoTemp1DAO() {
		super(TdpCadreHourRegInfoTemp1.class);
	}
	
	public int deleteAllRecords(){
		 
		Query query = getSession().createSQLQuery(" delete from tdp_cadre_hour_reg_info_temp1 ");
		return query.executeUpdate();
	}
	
	public int setPrimaryKeyAutoIncrementToOne(){
   	  Query query = getSession().createSQLQuery(" ALTER TABLE tdp_cadre_hour_reg_info_temp1 AUTO_INCREMENT = 1 ");
   	  return query.executeUpdate();
	}
	
}
