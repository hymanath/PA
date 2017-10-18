package com.itgrids.partyanalyst.service;

import java.util.List;

import com.itgrids.partyanalyst.dto.CommentsDashBoardVO;
import com.itgrids.partyanalyst.dto.DoorCampaignDashboardVO;
import com.itgrids.partyanalyst.dto.DoorToDoorInputVO;
import com.itgrids.partyanalyst.dto.InputCommentVO;

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
	
	public List<DoorCampaignDashboardVO> getAssignedConstituenciesForUser(DoorToDoorInputVO inputVO);
	public List<CommentsDashBoardVO> getLocationWiseComments(InputCommentVO inputVO);
	public String saveConstituencyComments(InputCommentVO inputVO);
	public List<CommentsDashBoardVO> getConstituencyWiseCommentDetails(InputCommentVO inputVO);
}
