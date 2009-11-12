package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;
import javax.persistence.Query;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.appfuse.dao.jpa.GenericDaoJpa;

import com.itgrids.partyanalyst.dao.ITownshipDAO; 
import com.itgrids.partyanalyst.dao.columns.enums.TownshipColumnNames;
import com.itgrids.partyanalyst.model.Township;

public class TownshipDAO extends GenericDaoHibernate<Township, Long> implements ITownshipDAO
{
	

	public TownshipDAO() {
		super(Township.class);
	}
	
	public List<Township> findByProperty(TownshipColumnNames propertyName, Object value){

		return getHibernateTemplate().find("from Township where " + propertyName.getValue() + "=?", value);
	}

	public List<Township> findByTownshipName(Object townshipName){
		return findByProperty(TownshipColumnNames.TOWNSHIP_NAME, townshipName);
	}

	public List<Township> findByTownshipCode(Object townshipCode){
		return findByProperty(TownshipColumnNames.TOWNSHIP_CODE, townshipCode);
	}

	public List<Township> findByTownshipType(Object townshipType){
		return findByProperty(TownshipColumnNames.TOWNSHIP_TYPE, townshipType);
	}
	
	public List<Township> findByTehsilID(Long mandalID){
		return getHibernateTemplate().find("from Township model where model.tehsil.tehsilId=?", mandalID);
		
	}

}
