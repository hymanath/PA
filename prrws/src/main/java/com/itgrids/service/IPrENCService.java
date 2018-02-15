package com.itgrids.service;

import java.util.List;

import com.itgrids.dto.EncTargetsVO;
import com.itgrids.dto.EncVO;
import com.itgrids.dto.EncWorksVO;
import com.itgrids.dto.IdNameVO;
import com.itgrids.dto.InputVO;

public interface IPrENCService {

	public List<EncVO> getLocationWiseRoadsInformation(InputVO inputVO);

	public EncVO getStateWiseRoadsInformation(InputVO inputVO);

	public List<EncWorksVO> getLocationWiseWorksInformation(InputVO inputVO);
	
	public List<EncTargetsVO> getEncTargetsAchievement(InputVO inputVO);

	public List<IdNameVO> getExceededEncWorks(InputVO inputVO);
	
	public EncWorksVO getLocationWiseWorksgraphInformation(InputVO inputVO);

	public List<IdNameVO> getLocationWiseNotGroundedWorks(InputVO inputVO);

}
