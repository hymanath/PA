package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IMeekosamPublicRepresentativeDAO;
import com.itgrids.partyanalyst.model.MeekosamPublicRepresentative;

public class MeekosamPublicRepresentativeDAO extends
		GenericDaoHibernate<MeekosamPublicRepresentative, Long> implements
		IMeekosamPublicRepresentativeDAO {
	public MeekosamPublicRepresentativeDAO() {
		super(MeekosamPublicRepresentative.class);
		
	}
}
