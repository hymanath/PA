package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ITwoWaySmsMobileDAO;
import com.itgrids.partyanalyst.model.TwoWaySmsMobile;

public class TwoWaySmsMobileDAO  extends GenericDaoHibernate<TwoWaySmsMobile, Long> implements  ITwoWaySmsMobileDAO {

	public TwoWaySmsMobileDAO() {
		super(TwoWaySmsMobile.class);
	}

	public List<TwoWaySmsMobile> getMobileInfo(String mobileNo){
		Query query = getSession().createQuery("select model from TwoWaySmsMobile model where model.mobileNo like '%"+mobileNo+"' ");
		return query.list();
	}
}
