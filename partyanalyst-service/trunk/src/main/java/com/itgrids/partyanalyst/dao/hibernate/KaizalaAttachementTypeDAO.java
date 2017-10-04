package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IKaizalaAttachementTypeDAO;
import com.itgrids.partyanalyst.model.KaizalaAttachementType;

public class KaizalaAttachementTypeDAO extends GenericDaoHibernate<KaizalaAttachementType, Long> implements IKaizalaAttachementTypeDAO {

	public KaizalaAttachementTypeDAO() {
		super(KaizalaAttachementType.class);
	}
}
