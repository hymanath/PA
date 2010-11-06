package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreChildrenInfoDAO;
import com.itgrids.partyanalyst.model.CadreChildrenInfo;
import com.itgrids.partyanalyst.model.CadreLanguageEfficiency;

public class CadreChildrenInfoDAO extends GenericDaoHibernate<CadreChildrenInfo, Long> implements ICadreChildrenInfoDAO{
	
	public CadreChildrenInfoDAO() {
		super(CadreChildrenInfo.class);
	}
	
	@SuppressWarnings("unchecked")
	public List<Object[]> findByCadreId(Long cadreId) {
		
		return getHibernateTemplate().find("select model.cadre.cadreId, model.name, model.dateOfBirth from CadreChildrenInfo model where model.cadre.cadreId = ?", cadreId);
	}

}
