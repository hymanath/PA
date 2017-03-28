package com.itgrids.partyanalyst.dao.hibernate;

import java.util.List;

import org.appfuse.dao.hibernate.GenericDaoHibernate;
import org.hibernate.Query;

import com.itgrids.partyanalyst.dao.ICadreLanguageEfficiencyDAO;
import com.itgrids.partyanalyst.model.CadreLanguageEfficiency;

public class CadreLanguageEfficiencyDAO extends GenericDaoHibernate<CadreLanguageEfficiency, Long> implements ICadreLanguageEfficiencyDAO {

	public CadreLanguageEfficiencyDAO() {
		super(CadreLanguageEfficiency.class);
		 
	}

	@SuppressWarnings("unchecked")
	public List<CadreLanguageEfficiency> findByCadreId(Long cadreId) {
		
		return getHibernateTemplate().find("from CadreLanguageEfficiency model where model.cadre.cadreId = ?", cadreId);
	}

	@SuppressWarnings("unchecked")
	public List<CadreLanguageEfficiency> findByCadreIdandLanguage(Long cadreId, String language) {
		
		Object []params = {cadreId,language};
		return getHibernateTemplate().find("from CadreLanguageEfficiency model where model.cadre.cadreId = ? and model.language.language = ?", params);
	}

	public Integer deleteLanguageDetailsByCadre(Long cadreId, Long language) {
		Query queryObject = getSession().createQuery("delete from CadreLanguageEfficiency model where model.cadre.cadreId = ? and model.language.languageId = ?");
		queryObject.setParameter(0, cadreId);
		queryObject.setParameter(1, language);
		return queryObject.executeUpdate();
		
	}

}
