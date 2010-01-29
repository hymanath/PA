package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;

import com.itgrids.partyanalyst.dao.IEPaperUrlDAO;
import com.itgrids.partyanalyst.model.EPaperUrl;

public class EPaperUrlDAO extends GenericDaoHibernate<EPaperUrl, Long> implements
		IEPaperUrlDAO {

	public EPaperUrlDAO() {
		super(EPaperUrl.class);
	}

	@SuppressWarnings("unchecked")
	public List<EPaperUrl> findByDistrictUrl(String districtUrl) {
		return getHibernateTemplate().find("from EPaperUrl model where model.districtUrl = ?", districtUrl);
	}

	@SuppressWarnings("unchecked")
	public List<EPaperUrl> findByEpaperUrlId(Long epaperUrlId) {
		return getHibernateTemplate().find("from EPaperUrl model where model.epaperUrlId = ?", epaperUrlId);
	}

	@SuppressWarnings("unchecked")
	public List<EPaperUrl> findEPapersForDistrictByDistrictId(Long districtId) {
		return getHibernateTemplate().find("from EPaperUrl model where model.districtId = ?", districtId);
	}
}
