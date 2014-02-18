package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.AssemblyPoliticalFeedback;

public interface IAssemblyPoliticalFeedbackDAO extends GenericDao<AssemblyPoliticalFeedback, Long>{

	public List<Object[]> getAssemblyPoliticalFeedBacks(List<Long> pfbIds);
}
