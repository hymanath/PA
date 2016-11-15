package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITdpCadreDataSourceTypeInfoDAO;
import com.itgrids.partyanalyst.model.TdpCadreDataSourceTypeInfo;

public class TdpCadreDataSourceTypeInfoDAO extends GenericDaoHibernate<TdpCadreDataSourceTypeInfo, Long> implements ITdpCadreDataSourceTypeInfoDAO {

	public TdpCadreDataSourceTypeInfoDAO() {
		super(TdpCadreDataSourceTypeInfo.class);
	}
}
