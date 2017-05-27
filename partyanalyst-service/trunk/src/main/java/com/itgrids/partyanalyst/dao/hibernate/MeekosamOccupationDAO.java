package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IMeekosamOccupationDAO;
import com.itgrids.partyanalyst.model.MeekosamOccupation;

public class MeekosamOccupationDAO extends GenericDaoHibernate<MeekosamOccupation, Long> implements IMeekosamOccupationDAO {

	public MeekosamOccupationDAO(){
		super(MeekosamOccupation.class);
	}
   
}
