package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreLanguageEfficiency;

public interface ICadreLanguageEfficiencyDAO extends GenericDao<CadreLanguageEfficiency, Long> {

	public List<CadreLanguageEfficiency> findByCadreId(Long cadreId); 
	public List<CadreLanguageEfficiency> findByCadreIdandLanguage(Long cadreId, String language);
	public Integer deleteLanguageDetailsByCadre(Long cadreId, Long languageId);
}
