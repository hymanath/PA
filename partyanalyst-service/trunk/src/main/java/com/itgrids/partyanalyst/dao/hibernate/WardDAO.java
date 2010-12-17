package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import javax.persistence.Query;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.appfuse.dao.jpa.GenericDaoJpa;

import com.itgrids.partyanalyst.dao.IWardDAO;
import com.itgrids.partyanalyst.dao.columns.enums.WardColumnNames;
import com.itgrids.partyanalyst.model.Ward;

public class WardDAO extends GenericDaoHibernate<Ward, Long> implements
		IWardDAO {

	public WardDAO() {
		super(Ward.class);
	}
	
	public List<Ward> findByProperty(WardColumnNames propertyName, Object value){

		return getHibernateTemplate().find("from Ward where " + propertyName.getValue() + "=?", value);
	}

	public List<Ward> findByWardName(Object wardName){
		return findByProperty(WardColumnNames.WARD_NAME, wardName);
	}

	public List<Ward> findByWardCode(Object wardCode){
		return findByProperty(WardColumnNames.WARD_CODE, wardCode);
	}
	
	@SuppressWarnings("unchecked")
	public List<Ward> findByWardNameAndTownship(String wardName,Long townId){
		
		Object[] params = {wardName,townId};
		return getHibernateTemplate().find("from Ward model where model.wardName = ? and model.township.townshipId = ?",params);
	}

}
