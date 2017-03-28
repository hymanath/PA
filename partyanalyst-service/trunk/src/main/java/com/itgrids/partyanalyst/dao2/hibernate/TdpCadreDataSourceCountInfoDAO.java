package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITdpCadreDataSourceCountInfoDAO;
import com.itgrids.partyanalyst.model.TdpCadreDataSourceCountInfo;

public class TdpCadreDataSourceCountInfoDAO extends GenericDaoHibernate<TdpCadreDataSourceCountInfo, Long> implements ITdpCadreDataSourceCountInfoDAO {
	public TdpCadreDataSourceCountInfoDAO(){
		super(TdpCadreDataSourceCountInfo.class);
	}  
}
