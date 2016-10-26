package com.itgrids.partyanalyst.dao.hibernate;

import java.util.Date;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreDateWiseInfoTempDAO;
import com.itgrids.partyanalyst.model.TdpCadreDateWiseInfoTemp;

public class TdpCadreDateWiseInfoTempDAO extends GenericDaoHibernate<TdpCadreDateWiseInfoTemp, Long> implements ITdpCadreDateWiseInfoTempDAO{

	public TdpCadreDateWiseInfoTempDAO() {
		super(TdpCadreDateWiseInfoTemp.class);
	}
	
	public int deleteAllRecords(){ 
		 
		Query query = getSession().createSQLQuery(" delete from tdp_cadre_date_wise_info_temp ");
		return query.executeUpdate();
   }
	
	public int setPrimaryKeyAutoIncrementToOne(){
    	Query query = getSession().createSQLQuery(" ALTER TABLE tdp_cadre_date_wise_info_temp AUTO_INCREMENT = 1 ");
    	return query.executeUpdate();
	}
	 
}