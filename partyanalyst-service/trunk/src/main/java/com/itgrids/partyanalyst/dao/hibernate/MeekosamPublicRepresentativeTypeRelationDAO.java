package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IMeekosamPublicRepresentativeTypeRelationDAO;
import com.itgrids.partyanalyst.model.MeekosamPublicRepresentativeTypeRelation;

public class MeekosamPublicRepresentativeTypeRelationDAO extends
		GenericDaoHibernate<MeekosamPublicRepresentativeTypeRelation, Long>
		implements IMeekosamPublicRepresentativeTypeRelationDAO {
	public MeekosamPublicRepresentativeTypeRelationDAO() {
		super(MeekosamPublicRepresentativeTypeRelation.class);
		
	}
}
