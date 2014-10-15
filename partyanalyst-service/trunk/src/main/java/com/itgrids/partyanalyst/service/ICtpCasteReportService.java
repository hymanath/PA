package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.SurveyCompletionDetailsVO;
import com.itgrids.partyanalyst.dto.SurveyStatusVO;

import com.itgrids.partyanalyst.dto.VoterHouseInfoVO;
import com.itgrids.partyanalyst.excel.booth.VoterVO;


public interface ICtpCasteReportService {
	public VoterHouseInfoVO getVoterDetailsForSearch(VoterHouseInfoVO inputVo ,String locationType,Long id);
	public VoterHouseInfoVO getCTPVoterCount(Long userId);
	public VoterHouseInfoVO getVotersCountInRegion(Long constituencyId,String locationType,Long userId);
   
    public VoterHouseInfoVO getCatseVotersCountInRegion(Long constituencyId,String locationType,Long userId);
    public SurveyStatusVO getSurveyStatusDetailsInfo();
	 public VoterHouseInfoVO getVotersDetailsInCaste(Long id,String type,Long casteId,Long userId,Long constituencyId,String gender);
    
}
