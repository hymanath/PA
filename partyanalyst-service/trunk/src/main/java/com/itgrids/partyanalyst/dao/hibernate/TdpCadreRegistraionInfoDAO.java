package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITdpCadreRegistraionInfoDAO;
import com.itgrids.partyanalyst.model.TdpCadreRegistraionInfo;

public class TdpCadreRegistraionInfoDAO extends GenericDaoHibernate<TdpCadreRegistraionInfo, Long> implements ITdpCadreRegistraionInfoDAO{

	public TdpCadreRegistraionInfoDAO(){
		super(TdpCadreRegistraionInfo.class);
	}
	
}
