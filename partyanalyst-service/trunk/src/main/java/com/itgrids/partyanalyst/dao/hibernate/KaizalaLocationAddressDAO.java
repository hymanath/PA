package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.itgrids.partyanalyst.dao.IKaizalaLocationAddressDAO;
import com.itgrids.partyanalyst.model.KaizalaLocationAddress;

@Repository
public class KaizalaLocationAddressDAO extends GenericDaoHibernate<KaizalaLocationAddress, Long> implements IKaizalaLocationAddressDAO{
	@Autowired
	SessionFactory sessionFactory;
	public KaizalaLocationAddressDAO() {
		super(KaizalaLocationAddress.class);
	}

}
