package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreCasteStateInfoTempDAO;
import com.itgrids.partyanalyst.model.TdpCadreCasteStateInfoTemp;

public class TdpCadreCasteStateInfoTempDAO extends GenericDaoHibernate<TdpCadreCasteStateInfoTemp,Long> implements ITdpCadreCasteStateInfoTempDAO {

	public TdpCadreCasteStateInfoTempDAO(){
		super(TdpCadreCasteStateInfoTemp.class);
	}
	
	public int deleteAllRecords(){
    	
	   Query query = getSession().createSQLQuery(" delete from tdp_cadre_caste_state_info_temp ");
	   return query.executeUpdate();
	}

}
