package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.EventSurveyUserLoginDetails;

public interface IEventSurveyUserLoginDetailsDAO extends GenericDao<EventSurveyUserLoginDetails, Long>{
	public List checkUserExistence(Long userId,String imei);
}
