package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IMeekosamPublicRepresentativeTypeDAO;
import com.itgrids.partyanalyst.model.MeekosamPublicRepresentativeType;

public class MeekosamPublicRepresentativeTypeDAO extends
		GenericDaoHibernate<MeekosamPublicRepresentativeType, Long> implements
		IMeekosamPublicRepresentativeTypeDAO {
	public MeekosamPublicRepresentativeTypeDAO() {
		super(MeekosamPublicRepresentativeType.class);
		
	}
}
