package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITdpCadreDataSourceCountInfoTempDAO;
import com.itgrids.partyanalyst.model.TdpCadreDataSourceCountInfoTemp;

public class TdpCadreDataSourceCountInfoTempDAO extends GenericDaoHibernate<TdpCadreDataSourceCountInfoTemp, Long> implements ITdpCadreDataSourceCountInfoTempDAO {
	public TdpCadreDataSourceCountInfoTempDAO(){
		super(TdpCadreDataSourceCountInfoTemp.class);  
	}
	public int deleteAllRecords(){
    	Query query = getSession().createSQLQuery(" delete from tdp_cadre_data_source_count_info_temp ");
    	return query.executeUpdate();
	 }
	 public int setPrimaryKeyAutoIncrementToOne(){
    	Query query = getSession().createSQLQuery(" ALTER TABLE tdp_cadre_data_source_count_info_temp AUTO_INCREMENT = 1 ");
    	return query.executeUpdate();
	 }  
}
