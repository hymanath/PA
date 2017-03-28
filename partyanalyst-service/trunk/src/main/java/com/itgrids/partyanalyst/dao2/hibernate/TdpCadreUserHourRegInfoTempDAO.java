package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreUserHourRegInfoTempDAO;
import com.itgrids.partyanalyst.model.TdpCadreUserHourRegInfoTemp;

public class TdpCadreUserHourRegInfoTempDAO extends GenericDaoHibernate<TdpCadreUserHourRegInfoTemp,Long> implements ITdpCadreUserHourRegInfoTempDAO {
	
	public TdpCadreUserHourRegInfoTempDAO() {
		super(TdpCadreUserHourRegInfoTemp.class);
	}
	
	public int deleteAllRecords(){
		 
		 Query query = getSession().createSQLQuery(" delete from tdp_cadre_user_hour_reg_info_temp ");
		 return query.executeUpdate();
	}
	
	public int setPrimaryKeyAutoIncrementToOne(){
    	Query query = getSession().createSQLQuery(" ALTER TABLE tdp_cadre_user_hour_reg_info_temp AUTO_INCREMENT = 1 ");
    	return query.executeUpdate();
	 }
}
