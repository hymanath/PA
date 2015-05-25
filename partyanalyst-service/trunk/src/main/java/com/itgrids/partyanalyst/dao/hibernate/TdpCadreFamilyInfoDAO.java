package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ITdpCadreFamilyInfo;
import com.itgrids.partyanalyst.model.TdpCadreFamilyInfo;


public class TdpCadreFamilyInfoDAO extends GenericDaoHibernate<TdpCadreFamilyInfo, Long> implements ITdpCadreFamilyInfo{

	public TdpCadreFamilyInfoDAO() {
		super(TdpCadreFamilyInfo.class);
	}

}
