package com.itgrids.service;

import java.util.List;

import com.itgrids.dto.DrainsVO;
import com.itgrids.dto.InputVO;
import com.itgrids.dto.KeyValueVO;
import com.itgrids.dto.StatusVO;

public interface IDrainsService 
{
	public List<DrainsVO> getDrainsInfoLocationWise(InputVO inputVO);
	public DrainsVO getDrainsInfoStateWise(InputVO inputVO);
	public List<StatusVO> getDrainsIvrStatusCounts(InputVO vo);
	public List<StatusVO> getOverAllIvrDetails(InputVO vo);
	public List<KeyValueVO> getIvrSurveyDates(InputVO inputVo);
	public List<KeyValueVO> getIvrSurveyQuestions(InputVO inputVo);
}

