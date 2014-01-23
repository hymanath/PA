package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICharacteristicsDAO;
import com.itgrids.partyanalyst.model.Characteristics;

public class CharacteristicsDAO extends GenericDaoHibernate<Characteristics, Long> implements ICharacteristicsDAO{

	public CharacteristicsDAO() {
		super(Characteristics.class);

	}

}
