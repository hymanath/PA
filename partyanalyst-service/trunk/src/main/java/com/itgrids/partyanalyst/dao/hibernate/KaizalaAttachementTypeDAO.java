package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IKaizalaAttachementTypeDAO;
import com.itgrids.partyanalyst.model.KaizalaAttachementType;

public class KaizalaAttachementTypeDAO extends GenericDaoHibernate<KaizalaAttachementType, Long> implements IKaizalaAttachementTypeDAO {

	public KaizalaAttachementTypeDAO() {
		super(KaizalaAttachementType.class);
	}

	public Long checkAttachementTypeExistence(String type) {
		Query query = getSession().createQuery(" select model.kaizalaAttachementTypeId "
				+ " from KaizalaAttachementType model "
				+ " where model.attachmentType=:type ");
		query.setParameter("type", type);
		return (Long) query.uniqueResult();
	}
}
