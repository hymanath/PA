package com.itgrids.partyanalyst.dao;

import java.util.List;

import org.appfuse.dao.GenericDao;

import com.itgrids.partyanalyst.model.FeedBack;

public interface IFeedbackDAO extends GenericDao<FeedBack, Long>{

	public Integer updateStatusToApproveOrReject(List<Long> commentIdList,String status);

}

