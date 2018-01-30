package com.itgrids.service;
import java.util.List;

import com.itgrids.dto.InputVO;
import com.itgrids.dto.SolidWasteManagementVO;


public interface ISolidWasteManagementService 
{
	public List<SolidWasteManagementVO> getSolidInfoLocationWise(InputVO inputVO);	
	public SolidWasteManagementVO getSolidWasteManagementOverAllCounts(InputVO inputVO);
	public List<SolidWasteManagementVO> getRfidTrackingOverAllTargets(InputVO inputVO);
}

