package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IKaizalaPropertiesDAO;
import com.itgrids.partyanalyst.model.KaizalaProperties;

public class KaizalaPropertiesDAO extends GenericDaoHibernate<KaizalaProperties, Long> implements IKaizalaPropertiesDAO{

	public KaizalaPropertiesDAO() {
		super(KaizalaProperties.class);
		
	}

}
