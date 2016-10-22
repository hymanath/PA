package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreDateWiseInfoDAO;
import com.itgrids.partyanalyst.model.TdpCadreDateWiseInfo;

public class TdpCadreDateWiseInfoDAO extends GenericDaoHibernate<TdpCadreDateWiseInfo, Long> implements ITdpCadreDateWiseInfoDAO{

	public TdpCadreDateWiseInfoDAO() {
		super(TdpCadreDateWiseInfo.class);
	}
	
	 public int deleteAllRecords(){
	    	
    	Query query = getSession().createSQLQuery(" delete from tdp_cadre_date_wise_info ");
    	return query.executeUpdate();
    }
	 
    public int setPrimaryKeyAutoIncrementToOne(){
    	Query query = getSession().createSQLQuery(" ALTER TABLE tdp_cadre_date_wise_info AUTO_INCREMENT = 1 ");
    	return query.executeUpdate();
    }
}
