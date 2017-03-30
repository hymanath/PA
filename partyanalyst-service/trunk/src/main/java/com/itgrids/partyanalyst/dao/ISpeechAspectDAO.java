package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.LeaderOccasion;
import com.itgrids.partyanalyst.model.SpeechAspect;

public interface ISpeechAspectDAO extends GenericDao<SpeechAspect, Long>{
	
	public List<Object[]> getAllSpeechAspectNames();

}
