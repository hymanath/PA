package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.IMeekosamPublicRepresentativeTypeDAO;
import com.itgrids.partyanalyst.model.MeekosamPublicRepresentativeType;

public class MeekosamPublicRepresentativeTypeDAO extends GenericDaoHibernate<MeekosamPublicRepresentativeType, Long> implements	IMeekosamPublicRepresentativeTypeDAO {
	public MeekosamPublicRepresentativeTypeDAO() {
		super(MeekosamPublicRepresentativeType.class);
	}
	
}
