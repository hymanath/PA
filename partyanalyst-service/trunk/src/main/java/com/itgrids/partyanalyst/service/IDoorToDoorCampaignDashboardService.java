package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.DoorCampaignDashboardVO;
import com.itgrids.partyanalyst.dto.DoorToDoorInputVO;

public interface IDoorToDoorCampaignDashboardService {

	public DoorCampaignDashboardVO getUsersCountsByLocation(DoorToDoorInputVO inputVO);
	public DoorCampaignDashboardVO getHouseHoldsCounts(DoorToDoorInputVO inputVO);
	public DoorCampaignDashboardVO getGrievancesCounts(DoorToDoorInputVO inputVO);
	public DoorCampaignDashboardVO getRecentImagesList(DoorToDoorInputVO inputVO);
	public List<DoorCampaignDashboardVO> getLocationWiseCountDetails(DoorToDoorInputVO inputVO);
	public List<DoorCampaignDashboardVO> getDepartmentWiseGrievanceCounts(DoorToDoorInputVO inputVO);
	public List<DoorCampaignDashboardVO> getDepartmentIssueWiseGrievanceCounts(DoorToDoorInputVO inputVO);
	public List<DoorCampaignDashboardVO> getDepartmentSubIssueWiseGrievanceCounts(DoorToDoorInputVO inputVO);
	public DoorCampaignDashboardVO getLevelWiseCount(DoorToDoorInputVO inputVO);
	public DoorCampaignDashboardVO getCampaignCountFrMandalPancMuncip(DoorToDoorInputVO inputVO);
	
	public DoorCampaignDashboardVO getUserAccessLevelIdsAndValues(DoorToDoorInputVO inputVO);
	public List<DoorCampaignDashboardVO> getUserWiseCountsFrLoginUser(DoorToDoorInputVO inputVO);
}
