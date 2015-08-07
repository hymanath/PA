package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.ICampCallPurposeDAO;
import com.itgrids.partyanalyst.model.CampCallPurpose;

public class CampCallPurposeDAO extends GenericDaoHibernate<CampCallPurpose, Long> implements ICampCallPurposeDAO{

	public CampCallPurposeDAO() {
		super(CampCallPurpose.class);
		// TODO Auto-generated constructor stub
	}
	public List<Object[]>  getAllCampCallPurpose(){	
		return getHibernateTemplate().find("select model.campCallPurpose,model.purpose from CampCallPurpose model");
	}
}
