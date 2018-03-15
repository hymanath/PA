package com.itgrids.service;
import java.text.ParseException;
import java.util.List;

import com.itgrids.dto.InputVO;
import com.itgrids.dto.SolidWasteManagementVO;
import com.itgrids.dto.WebServiceDataVO;


public interface ISolidWasteManagementService 
{
	public List<SolidWasteManagementVO> getSolidInfoLocationWise(InputVO inputVO) throws ParseException;	
	public SolidWasteManagementVO getSolidWasteManagementOverAllCounts(InputVO inputVO) throws ParseException;
	public  WebServiceDataVO saveRfidTrackingOverAllTargets(); 
	public List<SolidWasteManagementVO> getRfidTrackingOverAllTargetsData(InputVO inputVO) throws ParseException;
    public List<SolidWasteManagementVO> getGpWiseRfidTrackingOverData(InputVO  inputVO) throws ParseException;
	
	
	
	
	
}

