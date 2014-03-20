package com.itgrids.partyanalyst.dao.hibernate;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IStrategyMergePanchayatDAO;
import com.itgrids.partyanalyst.model.StrategyMergePanchayat;

public class StrategyMergePanchayatDAO extends GenericDaoHibernate<StrategyMergePanchayat,Long> implements IStrategyMergePanchayatDAO {

	public StrategyMergePanchayatDAO(){
		super(StrategyMergePanchayat.class);
	}
}
