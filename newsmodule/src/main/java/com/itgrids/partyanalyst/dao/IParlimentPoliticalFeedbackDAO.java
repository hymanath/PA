package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.ParlimentPoliticalFeedback;

public interface IParlimentPoliticalFeedbackDAO extends GenericDao<ParlimentPoliticalFeedback, Long>{

	public List<Object[]> getParlimentPoliticalFeedBacks(Date date,Long pcId);
}
