package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreHourRegInfoTempDAO;
import com.itgrids.partyanalyst.model.TdpCadreHourRegInfoTemp;

public class TdpCadreHourRegInfoTempDAO extends GenericDaoHibernate<TdpCadreHourRegInfoTemp,Long> implements ITdpCadreHourRegInfoTempDAO {
	
	public TdpCadreHourRegInfoTempDAO() {
		super(TdpCadreHourRegInfoTemp.class);
	}
	
	public int deleteAllRecords(){
		Query query = getSession().createSQLQuery(" delete from tdp_cadre_hour_reg_info_temp ");
		return query.executeUpdate();
	}
	
	public int setPrimaryKeyAutoIncrementToOne(){
   	  Query query = getSession().createSQLQuery(" ALTER TABLE tdp_cadre_hour_reg_info_temp AUTO_INCREMENT = 1 ");
   	  return query.executeUpdate();
	}
}
