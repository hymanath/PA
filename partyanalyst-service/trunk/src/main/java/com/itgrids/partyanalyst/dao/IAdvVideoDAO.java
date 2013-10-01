package com.itgrids.partyanalyst.dao;

import java.util.Date;
import java.util.List;

import org.appfuse.dao.GenericDao;
import com.itgrids.partyanalyst.model.AdvVideo;

public interface IAdvVideoDAO extends GenericDao<AdvVideo, Long>{

	public List<AdvVideo> getTopVideosForDisplaying(int startIndex,int maxIndex);
	
	public List<AdvVideo> getSelectdVideoToDisplay(Long videoId);
	
	public List<AdvVideo> getSelectedVideosForDates(Date fromDate,Date toDate);
}
