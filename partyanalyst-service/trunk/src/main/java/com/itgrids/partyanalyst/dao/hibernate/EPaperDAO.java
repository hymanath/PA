/* 
 * Copyright (c) 2009 IT Grids.
 * All Rights Reserved.
 *
 * IT Grids Confidential Information.
 * Created on January 20,2010
 */
package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IEPaperDAO;
import com.itgrids.partyanalyst.model.EPaper;

public class EPaperDAO extends GenericDaoHibernate<EPaper,Long> implements IEPaperDAO {

	public EPaperDAO() {
		super(EPaper.class);
	}

	
	@SuppressWarnings("unchecked")
	public List<EPaper> findByEpaperId(Long epaperId) {
		return getHibernateTemplate().find("from EPaper model where model.epaperId = ?", epaperId);
	}

	@SuppressWarnings("unchecked")
	public List<EPaper> findByClassification(String classification) {
		return getHibernateTemplate().find("from EPaper model where model.epaperId = ?", classification);
	}

	@SuppressWarnings("unchecked")
	public List<EPaper> findByCountryId(Long countryId) {
		return getHibernateTemplate().find("from EPaper model where model.countryId = ?", countryId);
	}

	@SuppressWarnings("unchecked")
	public List<EPaper> findByDescription(String description) {
		return getHibernateTemplate().find("from EPaper model where model.description = ?", description);
	}


	@SuppressWarnings("unchecked")
	public List<EPaper> findByImage(String image) {
		return getHibernateTemplate().find("from EPaper model where model.image = ?", image);
	}

	@SuppressWarnings("unchecked")
	public List<EPaper> findByLanguage(String language) {
		return getHibernateTemplate().find("from EPaper model where model.language = ?", language);
	}

	@SuppressWarnings("unchecked")
	public List<EPaper> findByName(String name) {
		return getHibernateTemplate().find("from EPaper model where model.name = ?", name);
	}


	@SuppressWarnings("unchecked")
	public List<EPaper> findByStateId(Long stateId) {
		return getHibernateTemplate().find("from EPaper model where model.stateId = ? order by epaperId desc", stateId);
	}


	@SuppressWarnings("unchecked")
	public List<EPaper> findByStateUrl(String stateUrl) {
		return getHibernateTemplate().find("from EPaper model where model.stateUrl = ?", stateUrl);
	}

	@SuppressWarnings("unchecked")
	public List<EPaper> findBycountryUrl(String countryUrl) {
		return getHibernateTemplate().find("from EPaper model where model.countryUrl = ?", countryUrl);
	}
}
