package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.columns.enums.ConstituencyColumnNames;
import com.itgrids.partyanalyst.model.Constituency;
import com.itgrids.partyanalyst.model.State;
import com.itgrids.partyanalyst.dao.IConstituencyDAO;

public class ConstituencyDAO extends GenericDaoHibernate<Constituency, Long>
		implements IConstituencyDAO {

	public ConstituencyDAO() {
		super(Constituency.class);
	}

	@SuppressWarnings("unchecked")
	public List<Constituency> findByProperty(String propertyName, Object value) {
		return getHibernateTemplate().find("from Constituency where " + propertyName + "=?", value);
	}

	public List<Constituency> findByName(Object name) {

		return findByProperty(ConstituencyColumnNames.NAME.getValue(), name);
	}

	public List<Constituency> findByCountryId(Object countryId) {

		return findByProperty(ConstituencyColumnNames.COUNTRY_ID.getValue(), countryId);
	}

	public List<Constituency> findByState(String property, State state) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<Constituency> findByStateId(Long stateId) {
		return getHibernateTemplate().find("from Constituency model where model.state.stateId = ?",stateId);
	}
	
	@SuppressWarnings("unchecked")
	public List<Constituency> findByConstituencyNamePattern(String constituencyName){
		return getHibernateTemplate().find("from Constituency model where model.name like ?","%"+constituencyName+"%");
	}
	
	@SuppressWarnings("unchecked")
	public List<Constituency> findByConstituencyId(Long constituencyId){
		return getHibernateTemplate().find("from Constituency model where model.constituencyId = ?",constituencyId);
	}

}