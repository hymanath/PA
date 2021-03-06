package com.itgrids.partyanalyst.service;

import com.itgrids.partyanalyst.dto.GISUserTrackingVO;
import com.itgrids.partyanalyst.dto.GISVisualizationDetailsVO;
import com.itgrids.partyanalyst.dto.GISVisualizationParameterVO;

public interface IGISVisualizationService {
	  public GISVisualizationDetailsVO getMembershipDriveVisualizationDetails(GISVisualizationParameterVO inputVO);
	  public GISVisualizationDetailsVO getMembershipDriveDayWiseVisualizationDetails(GISVisualizationParameterVO inputVO);
	  public GISUserTrackingVO getLocationWiseTabUserTrackingDetails(GISVisualizationParameterVO inputVO);
}
