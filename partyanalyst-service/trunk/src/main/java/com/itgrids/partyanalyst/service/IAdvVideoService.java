package com.itgrids.partyanalyst.service;

import java.text.ParseException;
import java.util.List;

import com.itgrids.partyanalyst.dto.AdvVideoVO;
import com.itgrids.partyanalyst.dto.ResultStatus;



public interface IAdvVideoService {

	
	public List<AdvVideoVO> getTopAdvVideosForDisplaying();
	
	public AdvVideoVO getVideoForSelected(Long videoId);
	
	public ResultStatus saveVideos(final AdvVideoVO advVideoVO);
	
	public List<AdvVideoVO> getVideosForSelectedDates(String fromDate,String toDate) throws ParseException;
}
