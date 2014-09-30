package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.CadreSurveyUserAssignDetails;

public interface ICadreSurveyUserAssignDetailsDAO extends GenericDao<CadreSurveyUserAssignDetails, Long>{

	public List<CadreSurveyUserAssignDetails> getCadreAssinedDetails(Long cadreSurveyUserId);
}
