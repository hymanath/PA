package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreGenderInfoTempDAO;
import com.itgrids.partyanalyst.model.TdpCadreGenderInfoTemp;

public class TdpCadreGenderInfoTempDAO extends GenericDaoHibernate<TdpCadreGenderInfoTemp,Long> implements ITdpCadreGenderInfoTempDAO {

	public TdpCadreGenderInfoTempDAO(){
		super(TdpCadreGenderInfoTemp.class);
	}
	
	public int deleteAllRecords(){
    	
	   Query query = getSession().createSQLQuery(" delete from tdp_cadre_gender_info_temp ");
	   return query.executeUpdate();
	}
	
}
