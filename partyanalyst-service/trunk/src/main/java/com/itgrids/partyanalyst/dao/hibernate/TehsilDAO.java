package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import javax.persistence.Query;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.appfuse.dao.jpa.GenericDaoJpa;

import com.itgrids.partyanalyst.dao.ITehsilDAO;
import com.itgrids.partyanalyst.dao.ITownshipDAO;
import com.itgrids.partyanalyst.dao.columns.enums.TehsilColumnNames;
import com.itgrids.partyanalyst.dao.columns.enums.TownshipColumnNames;
import com.itgrids.partyanalyst.model.Tehsil;
import com.itgrids.partyanalyst.model.Township;

public class TehsilDAO extends GenericDaoHibernate<Tehsil, Long> implements ITehsilDAO
{

	public TehsilDAO() {
		super(Tehsil.class);
	}
	

	public List<Tehsil> findByProperty(TehsilColumnNames propertyName, Object value){
		return getHibernateTemplate().find("from Tehsil where " + propertyName.getValue() + "=?", value);
	}

	public List<Tehsil> findByTehsilName(Object tehsilName){
		return findByProperty(TehsilColumnNames.TEHSIL_NAME, tehsilName);
	}

	public List<Tehsil> findByTehsilCode(Object tehsilCode){
		return findByProperty(TehsilColumnNames.TEHSIL_CODE, tehsilCode);
	}

	public List<Tehsil> findByDeformDate(Object deformDate){
		return findByProperty(TehsilColumnNames.DEFORM_DATE, deformDate);
	}

}
