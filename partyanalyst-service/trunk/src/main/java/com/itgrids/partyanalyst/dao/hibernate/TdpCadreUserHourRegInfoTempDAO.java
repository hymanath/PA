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
	
	public int deleteAllRecords(Date fromDate){
	    
		 StringBuilder sb = new StringBuilder();
		 
		 sb.append(" delete from tdp_cadre_user_hour_reg_info_temp  ");
		 if(fromDate != null){
			sb.append(" where survey_date = :fromDate "); 
		 }
		   Query query = getSession().createSQLQuery(sb.toString());
		   if(fromDate != null){
			  query.setDate("fromDate",fromDate );
		   }
		   return query.executeUpdate();
	}
	
	public int setPrimaryKeyAutoIncrementToOne(){
    	Query query = getSession().createSQLQuery(" ALTER TABLE tdp_cadre_user_hour_reg_info_temp AUTO_INCREMENT = 1 ");
    	return query.executeUpdate();
	 }
}
