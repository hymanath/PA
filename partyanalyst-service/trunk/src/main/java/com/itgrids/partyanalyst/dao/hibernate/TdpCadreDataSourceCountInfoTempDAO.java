package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITdpCadreDataSourceCountInfoTempDAO;
import com.itgrids.partyanalyst.model.TdpCadreDataSourceCountInfoTemp;

public class TdpCadreDataSourceCountInfoTempDAO extends GenericDaoHibernate<TdpCadreDataSourceCountInfoTemp, Long> implements ITdpCadreDataSourceCountInfoTempDAO {
	public TdpCadreDataSourceCountInfoTempDAO(){
		super(TdpCadreDataSourceCountInfoTemp.class);  
	}
}
