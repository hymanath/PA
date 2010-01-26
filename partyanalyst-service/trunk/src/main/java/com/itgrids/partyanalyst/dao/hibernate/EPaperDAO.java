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
	public List<EPaper> findByEpaperUrl(String epaperUrl) {
		return getHibernateTemplate().find("from EPaper model where model.epaperUrl = ?", epaperUrl);
	}
	
	@SuppressWarnings("unchecked")
	public List<EPaper> findByMainUrl(String mainUrl) {
		return getHibernateTemplate().find("from EPaper model where model.mainUrl = ?", mainUrl);
	}

	@SuppressWarnings("unchecked")
	public List findEPapersForDistrictByDistrictId(Long districtId) {
		return getHibernateTemplate().find("select model.epaperUrl,model.mainUrl,model.district.districtName,model.language,model.image from EPaper model where model.district.districtId = ?", districtId);
	}

	@SuppressWarnings("unchecked")
	public List findMainEPapersForStateByStateId(Long districtId) {
		return getHibernateTemplate().find("select model.mainUrl,model.language,model.image from EPaper model where model.district.districtId = ?", districtId);
	}

}
